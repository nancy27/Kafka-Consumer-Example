package com.kafka.consumerkafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumerkafka.Model.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerListener {

    private final ObjectMapper objectMapper;

    public KafkaConsumerListener(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "kafka-example", groupId = "group_id", containerFactory = "KakfaListenerContainerFactory")
    public void consume(String message){

        System.out.println("Consumed Message: " + message);
        try {
            Customer customer = objectMapper.readValue(message, Customer.class);
            System.out.println("Consumed for customer: " + customer.getName());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

//    @KafkaListener(topics = "kafka-example-json", groupId = "group_json",
//            containerFactory = "CustomerKafkaListenerFactory")
//    public void consume(Customer customer){
//        System.out.println("Consumed Message Json : " + customer);
//    }
}
