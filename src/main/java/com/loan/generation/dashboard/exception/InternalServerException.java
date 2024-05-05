package com.loan.generation.dashboard.exception;

import java.io.Serial;

public class InternalServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InternalServerException(String msg) {
        super(msg);
    }
}
