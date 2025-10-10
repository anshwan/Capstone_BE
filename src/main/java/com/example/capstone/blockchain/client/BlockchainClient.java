package com.example.capstone.blockchain.client;

import com.example.capstone.blockchain.dto.BlockchainRequest;
import com.example.capstone.blockchain.dto.BlockchainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BlockchainClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public BlockchainResponse uploadModel(BlockchainRequest req) {
        String url = "http://blockchain-backend:8081/api/register_model";

        ResponseEntity<BlockchainResponse> response =
                restTemplate.postForEntity(url, req, BlockchainResponse.class);

        return response.getBody();
    }
}
