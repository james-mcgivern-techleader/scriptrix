package com.scriptrix.scriptrixengine;

import com.scriptrix.scriptrixengine.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultScriptrixEngine implements ScriptrixEngine {

    @Autowired
    private ProjectService projectService;

    public ProjectService getProjectService() {
        return projectService;
    }
}
