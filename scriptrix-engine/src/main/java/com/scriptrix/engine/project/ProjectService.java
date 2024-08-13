package com.scriptrix.engine.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;

@Component
public class ProjectService {

    @Autowired
    Marshaller marshaller;

    @Autowired
    Unmarshaller unmarshaller;

    public ProjectBuilder getProjectBuilder() {
        return new ProjectBuilder(marshaller);
    }

    public Project loadFromFileSystem(Path projectPath) throws IOException {
        return null;
    }
}
