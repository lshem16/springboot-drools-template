package com.example.droolstemplate.rules;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleHitListener extends DefaultAgendaEventListener {
    private static final Logger logger = LoggerFactory.getLogger(RuleHitListener.class);

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        logger.info("Hit rule: {}", event.getMatch().getRule().getName());
    }
}
