package com.example.capstone.model.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Modality {
    LLM("LLM"),
    IMAGE_GENERATION("image-generation"),
    AUDIO("audio"),
    MULTIMODAL("multimodal");

    private final String value;

    Modality(String value) {
        this.value = value;
    }

    @JsonValue // JSON 직렬화 시 이 값 사용
    public String getValue() {
        return value;
    }
}
