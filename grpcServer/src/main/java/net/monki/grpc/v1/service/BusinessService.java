package net.monki.grpc.v1.service;

import com.ibexlab.pos.api.v1.BusinessServiceGrpc;
import com.ibexlab.pos.api.v1.Empty;
import com.ibexlab.pos.api.v1.Stores;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
@GRpcService
public class BusinessService extends BusinessServiceGrpc.BusinessServiceImplBase {
    private final Logger log = LoggerFactory.getLogger((getClass()));


    @Override
    public void getAvailableStores(Empty request, StreamObserver<Stores> responseObserver) {
//        super.getAvailableStores(request, responseObserver);
        log.info("GetAvailableStore");
        Stores reply = Stores.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}
