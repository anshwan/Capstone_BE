package com.example.capstone.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineageRequest {
    private Integer step;
    private String from;
    private String to;
    private String relationship; // fine_tuned / iteration / version_upgrade / root_model
}

