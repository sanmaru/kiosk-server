package net.monki.grpc.v1.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.monki.kiosk.HelloReply;
import net.monki.kiosk.HelloRequest;
import net.monki.kiosk.SimpleGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@GRpcService
public class HelloWorldService extends SimpleGrpc.SimpleImplBase {
    private final Logger log = LoggerFactory.getLogger((getClass()));

    @Override
    public StreamObserver<HelloRequest> sayHelloStream(StreamObserver<HelloReply> responseObserver) {
        return new StreamObserver<HelloRequest>(){

            @Override
            public void onNext(HelloRequest value) {
                responseObserver.onNext(HelloReply.newBuilder().setMessage("First").build());
                responseObserver.onNext(HelloReply.newBuilder().setMessage("Second").build());
//                responseObserver.onCompleted();
                responseObserver.onNext(HelloReply.newBuilder().setMessage("Third").build());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        String returnVal = "Hello " + req.getName();
        log.debug(returnVal);
        HelloReply reply = HelloReply.newBuilder().setMessage(returnVal).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
