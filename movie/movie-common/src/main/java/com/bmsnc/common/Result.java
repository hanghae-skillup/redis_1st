package com.bmsnc.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class Result<T> {
    private int status;
    private T data;
}
