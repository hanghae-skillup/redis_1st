package com.hh.application.config.cache.caffeine;


import lombok.Getter;

@Getter
public enum CacheType {

  PROGRAMS("movieScreening", 2*60,100);

  CacheType(String cacheName, int expiredAfterWrite, int maximumSize) {
    this.cacheName = cacheName;
    this.expiredAfterWrite = expiredAfterWrite;
    this.maximumSize = maximumSize;
  }

  private String cacheName;
  private int expiredAfterWrite;
  private int maximumSize;

}
