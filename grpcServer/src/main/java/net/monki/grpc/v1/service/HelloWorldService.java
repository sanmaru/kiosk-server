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
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
