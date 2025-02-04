package com.example.application.lock;

import java.util.function.Supplier;

public interface DistributedLockExecutor {
    <T> T executeWithLock(String key, long waitTime, long leaseTime, Supplier<T> task);
}