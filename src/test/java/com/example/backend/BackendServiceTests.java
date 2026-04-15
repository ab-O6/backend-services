package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class BackendServiceTests {

    @Test
    void contextLoads() {

    }

    @Test
    void testOne() {

        ApplicationModules modules = ApplicationModules.of(BackendService.class);

        modules.verify();
    }
}
