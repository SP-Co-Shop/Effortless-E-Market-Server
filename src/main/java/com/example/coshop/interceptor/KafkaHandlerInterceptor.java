package com.example.coshop.interceptor;

import com.example.coshop.configuration.KafkaProducerConfig;
import com.example.coshop.dto.log.LogDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.example.coshop.constants.HeaderConstant.KEY_RESPONSE_HEADER_TRACE_ID;
import static com.example.coshop.constants.KafkaConstant.KAFKA_TOPIC_NAME;

public class KafkaHandlerInterceptor implements HandlerInterceptor {

    private final KafkaProducerConfig producerConfig;

    public KafkaHandlerInterceptor(KafkaProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        /**
         * Log TraceId를 기준으로 SharedData에서 Message를 가져온다.
         *  */
        LogDto message = LoggingHandlerInterceptor.sharedData.get(response.getHeader(KEY_RESPONSE_HEADER_TRACE_ID));
        producerConfig.kafkaTemplate().send(new ProducerRecord<>(KAFKA_TOPIC_NAME,message.toString()));

        LoggingHandlerInterceptor.sharedData.remove(response.getHeader(KEY_RESPONSE_HEADER_TRACE_ID));
    }


}
