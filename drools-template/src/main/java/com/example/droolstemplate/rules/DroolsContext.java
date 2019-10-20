package com.example.droolstemplate.rules;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Configuration
public class DroolsContext {
    private static Logger logger = LoggerFactory.getLogger(DroolsContext.class);

    private final ResourceLoader resourceLoader;

    public DroolsContext(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieFileSystem kieFileSystem() throws IOException {
        var kieFileSystem = kieServices().newKieFileSystem();
        getRuleFiles().forEach(file -> kieFileSystem.write(ResourceFactory.newFileResource(file)));
        return kieFileSystem;
    }

    @Bean
    public KieContainer kieContainer() throws IOException {
        var kieRepository = kieServices().getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        kieServices().newKieBuilder(kieFileSystem()).buildAll();
        return kieServices().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    private List<File> getRuleFiles() throws IOException {
        var rules = resourceLoader.getResource("classpath:rules").getFile();
        return List.of(Objects.requireNonNull(rules.listFiles()));
    }
}
