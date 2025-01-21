package com.hh.domain.movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

  private String resultCode;
  private T result;

  public static Response<Void> error(String errorCode) {

    return new Response<>(errorCode, null);
  }
  public static <T> Response<T> error(String errorCode,T errorName) {

    return new Response<>(errorCode, errorName);
  }

  public static Response<Void> success(){
    return new Response<>("SUCCESS", null);
  }

  public static <T> Response<T> success(T result) {
    return new Response<>("SUCCESS", result);
  }

  public String toStream() {
    if(resultCode != "SUCCESS"){
      return "{"+
              "\"resultCode\":" + "\"" +resultCode +"\","+
              "\"message\" + :"+result +"}";
    }

    if (result == null) {
      return "{"+
              "\"resultCode\":" + "\"" +resultCode +"\","+
              "\"result\" + :"+null +"}";
    }
    return "{"+
            "\"resultCode\":" + "\"" +resultCode +"\","+
            "\"result\":"+"\""+result+"\"" +"}";
  }
}

