package com.loan.generation.dashboard.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loan.generation.dashboard.entity.LoanApplication;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomLoanApplicationDeserializer implements Deserializer<LoanApplication> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public LoanApplication deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, LoanApplication.class);
        } catch (IOException e) {
            // Handle deserialization exception
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        // No resources to release
    }
}
