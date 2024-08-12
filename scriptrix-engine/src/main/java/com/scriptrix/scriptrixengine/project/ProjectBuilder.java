package com.scriptrix.scriptrixengine.project;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.oxm.Marshaller;

import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.scriptrix.scriptrixengine.project.Project.*;

public class ProjectBuilder {

  private final Marshaller marshaller;

  private String name;
  private Path projectDir;
  private Project project;

  public ProjectBuilder(Marshaller marshaller) {
    this.marshaller = marshaller;
  }

  public ProjectBuilder name(String name) {
    this.name = name;
    return this;
  }

  public ProjectBuilder location(Path projectDir) {
    this.projectDir = projectDir;
    return this;
  }

  private void createApplicationDataDir() throws CreateProjectException {
    try {
      Files.createDirectory(projectDir.resolve(METADATA_DIRECTORY));
    } catch (IOException e) {
      throw new CreateProjectException("Could not create scriptrix metadata directory", e);
    }
  }

  protected void initialiseGitRepo() throws CreateProjectException {
    try {
      Git.init().setDirectory(projectDir.toFile()).call();
    } catch (GitAPIException e) {
      throw new CreateProjectException("Error initialising Git repo", e);
    }
  }

  private void createProjectMetadata() throws CreateProjectException {
    try {
      project = new Project(name, projectDir);
      marshaller.marshal(project, new StreamResult(new FileWriter(METADATA_FILE)));
    } catch (IOException e) {
      throw new CreateProjectException("Error saving project metadata", e);
    }
  }

  public Project build() throws CreateProjectException {
    createApplicationDataDir();
    initialiseGitRepo();
    createProjectMetadata();
    return switch (project) {
      case null -> throw new CreateProjectException("Unspecified error creating project");
      default -> project;
    };
  }
}
