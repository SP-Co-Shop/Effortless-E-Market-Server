package com.example.coshop.interceptor;

import com.example.coshop.configuration.KafkaProducerConfig;
import com.example.coshop.dto.log.LogDto;
import com.example.coshop.kafka.KafkaProducer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.example.coshop.constants.HeaderConstant.KEY_RESPONSE_HEADER_TRACE_ID;
import static com.example.coshop.constants.KafkaConstant.KAFKA_TOPIC_NAME;

public class KafkaHandlerInterceptor implements HandlerInterceptor {

    private final KafkaProducer producer;

    public KafkaHandlerInterceptor(KafkaProducer producer) {
        this.producer = producer;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        /**
         * Log TraceId를 기준으로 SharedData에서 Message를 가져온다.
         *  */
        LogDto message = LoggingHandlerInterceptor.sharedData.get(response.getHeader(KEY_RESPONSE_HEADER_TRACE_ID));
        producer.send(KAFKA_TOPIC_NAME,message.toString());

        LoggingHandlerInterceptor.sharedData.remove(response.getHeader(KEY_RESPONSE_HEADER_TRACE_ID));
    }

}
