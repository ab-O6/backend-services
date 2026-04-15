package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.docs.Documenter.CanvasOptions;
import org.springframework.modulith.docs.Documenter.DiagramOptions;

class ModulithTest {

    ApplicationModules modules = ApplicationModules.of(BackendService.class);

    @Test
    void verifyModules() {

        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {

        var diagramOptions = DiagramOptions.defaults();
        var canvasOptions = CanvasOptions.defaults();

        new Documenter(modules)
                .writeModulesAsPlantUml(diagramOptions)
                .writeIndividualModulesAsPlantUml(diagramOptions)
                .writeModuleCanvases(canvasOptions)
                .writeAggregatingDocument(diagramOptions, canvasOptions);
    }
}
