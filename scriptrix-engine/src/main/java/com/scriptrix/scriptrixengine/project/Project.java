package com.scriptrix.scriptrixengine.project;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {

    public static final String METADATA_DIRECTORY = ".scriptrix";
    public static final String METADATA_FILE = "project.xml";

    @Getter @XmlElement
    private Path projectDirectory;

    @Getter @Setter @XmlElement
    private String name;

    @Getter @Setter @XmlElement
    private ProjectSettings settings;

    public Project(String name, Path projectDirectory) {
      this.name = name;
      this.projectDirectory = projectDirectory;
    }

    public Path getApplicationMetadataDirectory() {
        return projectDirectory.resolve(METADATA_DIRECTORY);
    }
}
