package org.monki;

import org.monki.grpc.v1.service.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GRPCClient {
    private static final Logger log = LoggerFactory.getLogger(GRPCClient.class);

    public static void main(String[] args) {
        SpringApplication.run(GRPCClient.class, args);

        int sleepTime = 10000;
        try {
            Sample sample = new Sample();
            sample.clientStreamServerStream();

            log.info("Start Sleep : {} {}", sleepTime, new Date().getTime());
            Thread.sleep(sleepTime);
            log.info("End Sleep : {} {}", sleepTime, new Date().getTime());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}