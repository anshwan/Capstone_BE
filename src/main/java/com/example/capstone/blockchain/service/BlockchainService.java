package com.example.capstone.blockchain.service;

import com.example.capstone.blockchain.dto.BlockchainRequest;
import com.example.capstone.blockchain.dto.BlockchainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlockchainService {

    private final RestTemplate restTemplate = new RestTemplate();

    public BlockchainResponse registerOnChain(BlockchainRequest request) {
        try {
            String url = "http://localhost:4000/register_model"; // 블록체인 백엔드 API
            return restTemplate.postForObject(url, request, BlockchainResponse.class);
        } catch (Exception e) {
            System.err.println("⚠️ Blockchain 연동 실패: " + e.getMessage());

            // ✅ 더미값 반환
            BlockchainResponse dummy = new BlockchainResponse();
            dummy.setPda("dummy_pda_" + UUID.randomUUID());
            dummy.setTxSignature("dummy_tx_" + UUID.randomUUID());
            dummy.setStatus("DUMMY_SUCCESS");
            return dummy;
        }
    }
}
