package br.xksoberbado.mongodbshardexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MongodbShardExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbShardExampleApplication.class, args);
    }
}
