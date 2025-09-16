package com.example.capstone.model.dto;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ModelCreateRequest(
        @NotBlank @Size(max=200) String modelName,
        @NotBlank @Size(max=50)  String versionName,
        @NotBlank @Size(max=32)  String modalityCode,  // LLM/VLM/IMAGE
        @NotBlank @Size(max=50)  String licenseCode,   // RESEARCH/COMMERCIAL/ONPREM/FT

        String overview,
        String releaseNotes,
        LocalDate releaseDate,

        String cidRoot,
        String checksumRoot,
        String onchainTx,

        JsonNode storage,   // 자유 JSON
        JsonNode metrics,   // 배열 JSON 권장
        JsonNode samples,   // 배열 JSON 권장
        JsonNode lineage    // 배열 JSON 권장

) { }
