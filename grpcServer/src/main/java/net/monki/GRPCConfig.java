package net.monki;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GRPCConfig {
    @Bean
    @GRpcGlobalInterceptor
    public ServerInterceptor globalInterceptor(){
        return new ServerInterceptor(){

            private final Logger log = LoggerFactory.getLogger((getClass()));

            @Override
            public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
                // your logic here
                log.info("requested Method:[{}]", call.getMethodDescriptor().getFullMethodName());
                return next.startCall(call, headers);
            }
        };
    }
}
