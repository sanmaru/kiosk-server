package net.monki;

import com.google.rpc.context.AttributeContext;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.testing.StreamRecorder;
import io.grpc.stub.StreamObserver;
import net.monki.data.MemberService;
import net.monki.kiosk.HelloReply;
import net.monki.kiosk.HelloRequest;
import net.monki.kiosk.SimpleGrpc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class HelloworldServiceTest {

    @Test
    void contextLoaded() {
        assertThat(GRPCServer.class).isNotNull();
    }

    private static final HelloRequest REQUEST = HelloRequest.newBuilder()
            .setName("name")
            .build();

    private HelloWorldService serviceTest;

    @BeforeEach
    public void setup(){
        serviceTest = new HelloWorldService();
    }


    @Test
    void helloWorldServiceTest(){
        StreamRecorder<HelloReply> observer = StreamRecorder.create();
        serviceTest.sayHello(REQUEST, observer);
        List<HelloReply> result = observer.getValues();
        assertEquals(result.get(0).getMessage(),String.format("Hello %s","name"));
    }
}
