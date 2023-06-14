package net.monki.grpc.v1.service;

import com.ibexlab.pos.api.v1.Empty;
import com.ibexlab.pos.api.v1.Stores;
import io.grpc.internal.testing.StreamRecorder;
import net.monki.GRPCServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

class BusinessServiceTest {
    @Test
    void contextLoaded() {
        assertThat(GRPCServer.class).isNotNull();
    }

    private static final Empty REQUEST = Empty.newBuilder().build();

    private BusinessService serviceTest;

    @BeforeEach
    public void setup(){
        serviceTest = new BusinessService();
    }

    @Test
    void businessServiceTest(){
        StreamRecorder<Stores> observer = StreamRecorder.create();
        serviceTest.getAvailableStores(REQUEST, observer);
        List<Stores> result = observer.getValues();
        assertEquals(result.get(0),Stores.newBuilder().build());
    }
}