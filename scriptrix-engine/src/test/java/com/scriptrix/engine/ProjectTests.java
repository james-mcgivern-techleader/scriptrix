package com.scriptrix.engine;

import com.scriptrix.engine.project.Project;
import com.scriptrix.engine.project.ProjectBuilder;
import org.assertj.core.api.Assertions;
import org.assertj.core.description.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
public class ProjectTests {

  public static final String NAME = "dnd";
  public static final String METADATA_DIRECTORY = ".scriptrix";
  public static final String METADATA_FILE = "project.xml";

  private StringBuilder reportBuilder;

  @Autowired
  private ScriptrixEngine engine;
  @Autowired
  private Unmarshaller unmarshaller;

  @BeforeEach
  public void setUp() {
    // initialize the description consumer
    reportBuilder = new StringBuilder(String.format("Assertions:%n"));
    Consumer<Description> descriptionConsumer = desc -> reportBuilder.append(String.format("-- %s%n", desc));
    // use the description consumer for any following assertions descriptions.
    Assertions.setDescriptionConsumer(descriptionConsumer);
  }

  @AfterEach
  public void tearDown() {
    System.out.append(reportBuilder.toString());
  }

  @Test
  public void shouldBeAbleToCreateBlankProject(@TempDir Path projectDir) throws Exception {
    //given
    assertThat(projectDir.toFile()).isDirectory();
    assertThat(Objects.requireNonNull(projectDir.toFile().listFiles())).isEmpty();
    //when
    ProjectBuilder project = engine.getProjectService().getProjectBuilder();
    Project dnd = project
        .name("dnd")
        .location(projectDir)
        .build();
    //then

    //Check project metadata stuff
    assertThat(projectDir.toFile().listFiles())
          .as("check application data directory is created")
          .filteredOnAssertions(file -> assertThat(file.getName()).isEqualTo(".scriptrix"))
          .extracting(
        File::getName,
        File::isDirectory
    ).containsExactly(tuple(METADATA_DIRECTORY, true));

    Path metadataFile = projectDir.resolve(METADATA_DIRECTORY, METADATA_FILE);
    assertThat(metadataFile).as("Check project metadata file exists").exists();
    assertThat(unmarshaller.unmarshal(
        new StreamSource(new FileInputStream(metadataFile.toFile())))
    ).isEqualTo(new Project(NAME, projectDir));



    //Check Git setup
    assertThat(projectDir.toFile().listFiles())
          .as("check git data directory is created")
          .filteredOnAssertions(file -> assertThat(file.getName()).isEqualTo(".git"))
          .extracting(
        File::getName,
        File::isDirectory
          ).containsExactly(tuple(".git", true));

  }
}
