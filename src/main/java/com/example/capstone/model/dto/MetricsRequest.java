package com.example.capstone.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsRequest {
    private Integer mmlu;
    private Integer hellaswag;
    private Integer arc;
    private Integer truthfulqa;
    private Integer gsm8k;
    private Integer humaneval;
}
