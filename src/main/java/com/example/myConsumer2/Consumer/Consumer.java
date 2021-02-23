package com.example.myConsumer2.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final List<String> msgs = new ArrayList<>();

    @KafkaListener(topics = "tdad.sw.interview", groupId = "group_id12")
    public void consume(String message) throws IOException {
        msgs.add(message);
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

    public List<String> getMsgs() {
        return msgs;
    }

}
