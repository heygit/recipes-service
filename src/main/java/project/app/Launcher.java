package project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import project.service.FormulaService;

import java.io.IOException;


@SpringBootApplication
public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        ApplicationContext context = SpringApplication.run(Config.class, args);
        FormulaService.init();
    }
}
