package com.godcheese.example.api;

import com.godcheese.example.Example;
import com.godcheese.example.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@RestController
@RequestMapping(Example.Api.EXAMPLE)
public class ExampleRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleRestController.class);

    private static final String EXAMPLE = "/EXAMPLE";

    @Autowired
    private ExampleService exampleService;

    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}