package com.scriptrix.engine.project;

import com.scriptrix.engine.jaxb.PathAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.nio.file.Path;

@XmlRootElement
public class Project {

  public static final String METADATA_DIRECTORY = ".scriptrix";
  public static final String METADATA_FILENAME = "project.xml";

  private String name;
  private Path projectDirectory;
  private ProjectSettings settings;

  public Project() {
    this.settings = new ProjectSettings();
  }

  public Project(String name, Path projectDirectory) {
    this();
    this.name = name;
    this.projectDirectory = projectDirectory;
  }

  @XmlElement
  @XmlJavaTypeAdapter(PathAdapter.class)
  public Path getProjectDirectory() {
    return projectDirectory;
  }

  public void setProjectDirectory(Path projectDirectory) {
    this.projectDirectory = projectDirectory;
  }

  @XmlElement
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSettings(ProjectSettings settings) {
    this.settings = settings;
  }

  @XmlElement
  public ProjectSettings getSettings() {
    return settings;
  }

  public Path getApplicationMetadataDirectory() {
    return projectDirectory.resolve(METADATA_DIRECTORY);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Project project)) return false;
    return new EqualsBuilder()
        .append(getName(), project.getName())
        .append(getProjectDirectory(), project.getProjectDirectory())
        .append(getSettings(), project.getSettings())
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(getName())
        .append(getProjectDirectory())
        .append(getSettings())
        .toHashCode();
  }
}
