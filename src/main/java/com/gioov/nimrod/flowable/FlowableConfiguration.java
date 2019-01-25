package com.gioov.nimrod.flowable;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2019-01-15
 */
@Configuration
public class FlowableConfiguration implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        String fontName = "宋体";
        springProcessEngineConfiguration.setActivityFontName(fontName);
        springProcessEngineConfiguration.setLabelFontName(fontName);
        springProcessEngineConfiguration.setAnnotationFontName(fontName);
    }
}
