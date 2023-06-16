package org.monki.grpc.v1.service;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.monki.kiosk.HelloReply;
import net.monki.kiosk.HelloRequest;
import net.monki.kiosk.SimpleGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Sample {
    private final Logger log = LoggerFactory.getLogger((getClass()));
    private final SimpleGrpc.SimpleStub asyncStub = SimpleGrpc.newStub(
            ManagedChannelBuilder.forAddress("localhost", 9091)
                    .usePlaintext()
                    .build()
    );

    public void asyncCall(){
        final HelloRequest helloRequest = HelloRequest.newBuilder().setName("Client").build();

        log.info("asyncCall : {} ", helloRequest.getName());
        asyncStub.sayHello(helloRequest, new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                log.info("asyncCall : {}", value);
            }

            @Override
            public void onError(Throwable t) {
                log.error("asyncCall Error: {}", t);
            }

            @Override
            public void onCompleted() {
                log.info("asyncCall - onCompleted");
            }
        });

        log.info("asyncCall End");
    }
}
