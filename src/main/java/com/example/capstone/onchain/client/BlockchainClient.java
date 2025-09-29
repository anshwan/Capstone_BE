// src/main/java/com/example/capstone/onchain/BlockchainClient.java
package com.example.capstone.onchain.client;

import com.example.capstone.onchain.dto.RegisterModelRequest;
import com.example.capstone.onchain.dto.RegisterModelResult;

public interface BlockchainClient {
    RegisterModelResult registerModel(RegisterModelRequest req);
}
