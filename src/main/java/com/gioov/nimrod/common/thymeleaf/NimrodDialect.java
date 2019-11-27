package com.gioov.nimrod.common.thymeleaf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-09-23
 */
@Component
public class NimrodDialect extends AbstractProcessorDialect {
    private static final String NAME = "Nimrod Dialect";
    private static final String PREFIX = "nimrod";
    private static final Logger LOGGER = LoggerFactory.getLogger(NimrodDialect.class);

    protected NimrodDialect() {
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new SecurityAuthorityElementProcessor(s));
        return processors;
    }
}
