package com.example.droolstemplate;

import com.example.droolstemplate.models.SampleRequest;
import com.example.droolstemplate.models.SampleResponse;
import com.example.droolstemplate.rules.DroolsExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/sample")
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    private final DroolsExecutor droolsExecutor;

    public SampleController(DroolsExecutor droolsExecutor) {
        this.droolsExecutor = droolsExecutor;
    }

    @PostMapping
    public SampleResponse processRequest(@RequestBody SampleRequest sampleRequest) {
        SampleResponse response = new SampleResponse();
        droolsExecutor.fireRules(sampleRequest, response);
        return response;
    }
}
