package com.loan.generation.dashboard.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailedResponse {

    private String failedCode;
    private String failedDesc;
}
