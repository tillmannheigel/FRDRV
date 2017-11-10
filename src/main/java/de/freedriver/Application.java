package de.freedriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.freedriver.service.FreedriverService;

@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    private FreedriverService freedriverService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        freedriverService.crawlAllVendors();
    }
}

