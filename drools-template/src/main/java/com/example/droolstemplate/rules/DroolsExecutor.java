package com.example.droolstemplate.rules;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroolsExecutor {
    private static Logger logger = LoggerFactory.getLogger(DroolsContext.class);

    private final KieContainer kieContainer;

    public DroolsExecutor(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public void fireRules(Object... inputs) {
        var inputList = List.of(inputs);
        var session = getSession();
        logger.info("Firing rules for: {}", inputList);
        session.addEventListener(new RuleHitListener());
        session.execute(inputList);
    }

    private StatelessKieSession getSession() {
        return kieContainer.newStatelessKieSession();
    }
}
