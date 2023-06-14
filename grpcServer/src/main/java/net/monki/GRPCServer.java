package net.monki;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GRPCServer {
    private static final Logger log = LoggerFactory.getLogger((GRPCServer.class));

    public static void main(String[] args) {
        SpringApplication.run(GRPCServer.class, args);
        log.info("Start gRPC Server....");
    }
}