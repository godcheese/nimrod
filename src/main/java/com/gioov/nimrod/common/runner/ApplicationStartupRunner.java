package com.gioov.nimrod.common.runner;

import com.gioov.nimrod.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Component
@Order
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private Common common;

    @Override
    public void run(String... strings) {
        common.initialize();
    }

}
