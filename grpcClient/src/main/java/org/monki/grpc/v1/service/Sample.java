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
    public void clientStreamServerStream(){
        final HelloRequest helloRequest1 = HelloRequest.newBuilder().setName("Client1").build();
        final HelloRequest helloRequest2 = HelloRequest.newBuilder().setName("Client2").build();
        final HelloRequest helloRequest3 = HelloRequest.newBuilder().setName("Client3").build();

        StreamObserver<HelloReply> responseObserver = new StreamObserver<HelloReply>(){
            @Override
            public void onNext(HelloReply value) {
                log.info("Client Request asyncCall : {}", value);
            }

            @Override
            public void onError(Throwable t) {
                log.error("asyncCall Error: {}", t);
            }

            @Override
            public void onCompleted() {
                log.info("asyncCall - onCompleted");
            }
        };

        StreamObserver<HelloRequest> requestObserver = asyncStub.sayHelloStream(responseObserver);
        try{
            requestObserver.onNext(helloRequest1);
            requestObserver.onNext(helloRequest2);
            requestObserver.onNext(helloRequest3);
        }catch(Exception e){
            e.printStackTrace();
        }
        requestObserver.onCompleted();
    }
}
