// src/main/java/com/example/capstone/onchain/HttpBlockchainClient.java
package com.example.capstone.onchain.client;

import com.example.capstone.onchain.client.BlockchainClient;
import com.example.capstone.onchain.dto.RegisterModelRequest;
import com.example.capstone.onchain.dto.RegisterModelResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 온체인 백엔드 REST 호출용.
 * 실패하거나 disabled면 더미 응답 리턴.
 */
@Component
@RequiredArgsConstructor
public class HttpBlockchainClient implements BlockchainClient {

    private final WebClient blockchainWebClient;

    @Value("${onchain.enabled:false}")
    private boolean enabled;

    @Value("${onchain.endpoint.register:/register_model}")
    private String registerPath;

    @Override
    public RegisterModelResult registerModel(RegisterModelRequest req) {
        if (!enabled) {
            return dummy("disabled");
        }
        try {
            // 실제 온체인 백엔드의 API 스펙에 맞춰 경로/바디 조정
            return blockchainWebClient.post()
                    .uri(registerPath)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(req)
                    .retrieve()
                    .bodyToMono(RegisterModelResult.class)
                    .timeout(java.time.Duration.ofSeconds(10))
                    .onErrorResume(ex -> Mono.just(dummy("error:" + ex.getMessage())))
                    .block();
        } catch (Exception ex) {
            return dummy("exception:" + ex.getMessage());
        }
    }

    private RegisterModelResult dummy(String why) {
        return RegisterModelResult.builder()
                .success(false)
                .txSignature("mock-tx-".concat(Long.toHexString(System.currentTimeMillis())))
                .onchainModelId(null)
                .message("fallback(" + why + ")")
                .build();
    }
}
