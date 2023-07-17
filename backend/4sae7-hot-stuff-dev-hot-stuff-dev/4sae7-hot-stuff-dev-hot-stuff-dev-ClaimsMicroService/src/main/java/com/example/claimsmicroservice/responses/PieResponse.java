package com.example.claimsmicroservice.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PieResponse {
    private List<String> labels;
    private List<Long> values;
}
