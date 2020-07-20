package offtop.AudioTransformation.Services;

import offtop.AudioTransformation.Models.AudioData;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
    private static final String TOPIC = "IncomingAudioEvent";
 
    @Autowired
    private KafkaTemplate<String, AudioData> kafkaTemplate;
 
    // produces and sends message by topic
    public void sendMessage(String message) {
       logger.info(String.format(" Producing message -> %s", message));
       // kafkaTemplate.send(TOPIC, message);
    }
    public void sendAudioFile(AudioData audioData) {
       kafkaTemplate.send(TOPIC, audioData);
       logger.info(String.format(" Producing message -> %s", audioData));

    }
}