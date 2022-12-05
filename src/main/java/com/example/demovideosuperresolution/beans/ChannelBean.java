package com.example.demovideosuperresolution.beans;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelBean {
    @Bean(name = "assemblerChannel")
    ManagedChannel getAssemblerChannel() {
        return getLocalHostChannel(11201);
    }

    @Bean(name = "disassemblerChannel")
    ManagedChannel getDisassemblerChannel() {
        return getLocalHostChannel(11202);
    }

    @Bean(name = "boxUpscalingChannel")
    ManagedChannel getBoxUpscalingChannel() {
        return getLocalHostChannel(11203);
    }

    ManagedChannel getLocalHostChannel(int port) {
        return ManagedChannelBuilder
                .forAddress("localhost", port)
                .usePlaintext().build();
    }
}
