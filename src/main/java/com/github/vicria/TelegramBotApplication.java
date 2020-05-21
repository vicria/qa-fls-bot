package com.github.vicria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramBotApplication {

    public static void main(String[] args) {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("socksProxyHost", "5.56.61.183");
        System.getProperties().put("socksProxyPort", "51295");
        SpringApplication.run(TelegramBotApplication.class, args);
    }
}
