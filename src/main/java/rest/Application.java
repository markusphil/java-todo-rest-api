package rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import persistance.DatabaseConnector;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // establish Database connection
        DatabaseConnector.connect();
        // start SpringBootApplication
        SpringApplication.run(Application.class, args);
    }
}
