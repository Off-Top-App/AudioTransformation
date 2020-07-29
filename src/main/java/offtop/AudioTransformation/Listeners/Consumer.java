package offtop.AudioTransformation.Listeners;

import java.util.Map;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import offtop.AudioTransformation.Services.ConsumerService;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    ConsumerService consumerService;

    @KafkaListener(topics = "outgoingAudioEvent", groupId = "group_Id")
    public void recieveAudioEvent(String message) {
        logger.info("_message: " + 1);
        Map value = new Gson().fromJson(message, Map.class);
        logger.info("\nRecieved audio Bytes");
       consumerService.uploadConsumedAudioData(value);    
    }
}