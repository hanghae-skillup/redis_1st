package com.example.application.SeatReservation.redis;


import org.springframework.stereotype.Service;

@Service
public interface DistributedLockManager {
    <T> T executeWithLock(String key, long leaseTime, long waitTime, LockCallback<T> callback) throws Exception;

    @FunctionalInterface
    interface LockCallback<T> {
        T execute() throws Exception;
    }
}
