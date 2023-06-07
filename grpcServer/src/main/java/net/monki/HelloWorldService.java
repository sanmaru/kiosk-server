package net.monki;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.monki.data.Member;
import net.monki.data.MemberService;
import net.monki.kiosk.HelloReply;
import net.monki.kiosk.HelloRequest;
import net.monki.kiosk.SimpleGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@GRpcService
public class HelloWorldService extends SimpleGrpc.SimpleImplBase {
    private final Logger log = LoggerFactory.getLogger((getClass()));

//    private final MemberService memberService;
    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {

//        List<Member> member = memberService.getMembers();
//        log.info("Member email:[{}]", new ArrayList(member));
//        log.info("recieved Name:[{}]",  req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
