package com.example.coshop.dto.log;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LogDto {
    String traceId;
    String clientIp;
    String time;
    String path;
    String method;
    String requestBody;
    String response;
    String statusCode;
    Long elapsedTimeMillis;

    @Override
    public String toString() {
        return "{" +
                " \"traceId\": \"" + traceId + "\"," +
                " \"clientIp\": \"" + clientIp + "\"," +
                " \"time\": \"" + time + "\"," +
                " \"path\": \"" + path + "\"," +
                " \"method\": \"" + method + "\"," +
                " \"requestBody\": " + requestBody + "," +
                " \"responseBody\": " + response + "," +
                " \"statusCode\": \"" + statusCode + "\"," +
                " \"elapsedTimeMillis\": " + elapsedTimeMillis +
                '}';
    }
}
