package com.gioov;

import com.gioov.nimrod.common.others.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Nimrod 启动类
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@EnableAsync
@SpringBootApplication
public class NimrodBootstrap extends SpringBootServletInitializer {

    private static final String NIMROD_VERSION = "0.7.1";
    private static final String NIMROD_URL = "https://github.com/godcheese/nimrod";

    private static final Logger LOGGER = LoggerFactory.getLogger(NimrodBootstrap.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NimrodBootstrap.class);
        application.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext configurableApplicationContext = application.run(args);
        bootstrap((WebApplicationContext) configurableApplicationContext);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        application.setBannerMode(Banner.Mode.OFF);
        WebApplicationContext webApplicationContext = super.run(application);
        bootstrap(webApplicationContext);
        return webApplicationContext;
    }

    private static void bootstrap(WebApplicationContext webApplicationContext) {
        // @formatter:off
        String banner =
                "  .__   __.  __  .___  ___. .______        ______    _______  \n" +
                        "  |  \\ |  | |  | |   \\/   | |   _  \\      /  __  \\  |       \\ \n" +
                        "  |   \\|  | |  | |  \\  /  | |  |_)  |    |  |  |  | |  .--.  |\n" +
                        "  |  . `  | |  | |  |\\/|  | |      /     |  |  |  | |  |  |  |\n" +
                        "  |  |\\   | |  | |  |  |  | |  |\\  \\----.|  `--'  | |  '--'  |\n" +
                        "  |__| \\__| |__| |__|  |__| | _| `._____| \\______/  |_______/ ";

        String nimrod =
                "\n  -------------------------------------------------"  +
                        "\n  | Nimrod version: " + NIMROD_VERSION + "                         |" +
                        "\n  | Homepage: " + NIMROD_URL +" |" +
                        "\n  -------------------------------------------------";

        Common.getHost(webApplicationContext);
        String scheme = Common.Host.scheme;
        String port = Common.Host.port;
        String contextPath = Common.Host.contextPath;
        String ip = Common.Host.ip;
        String local = scheme + "://localhost" + ":" + port + contextPath + "/";
        String network = scheme + "://" + ip + ":" + port + contextPath + "/";
        if(ip == null) {
            network = "unavailable";
        }
        String appRunningAt =
                "\n  App running at:" +
                        "\n  - Server:  " + Common.Host.serverInfo +
                        "\n  - Local:   " + local +
                        "\n  - Network: " + network;
        // @formatter:on
        LOGGER.info("\n\n" + banner + "\n" + nimrod + "\n" + appRunningAt + "\n");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return  application.sources(NimrodBootstrap.class);
    }

    @Order
    @Component
    public static class ApplicationStartupRunner implements CommandLineRunner {
        @Autowired
        private Common common;
        @Override
        public void run(String... strings) {
            common.initialize();
        }
    }

}
