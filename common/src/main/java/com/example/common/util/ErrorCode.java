package com.example.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // common error
    INVALID_INPUT_VALUE(400, "C0001", "Invalid input value"),
    METHOD_NOT_ALLOWED(405, "C0002", "Method not allowed"),
    ENTITY_NOT_FOUND(400, "C0003", "Cannot found entity"),
    INTERNAL_SERVER_ERROR(500, "C0004", "Internal server error"),
    INVALID_TYPE_VALUE(400, "C0005", " Invalid type value"),
    HANDLE_ACCESS_DENIED(403, "C0006", "Access denied"),
    INVALID_TOKEN(403, "C0007", "Invalid token"),
    DATABASE_ERROR(500, "C0008", "Database error"),
    LOCK_ACQUISITION_FAILED(409, "L0001", "Failed to acquire lock"),
    LOCK_RELEASE_FAILED(500, "L0002", "Failed to release lock"),
    LOCK_TIMEOUT(408, "L0003", "Lock acquisition timed out");


    private final int status;
    private final String code;
    private final String message;
}

