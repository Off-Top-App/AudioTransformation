package offtop.AudioTransformation.Services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import offtop.AudioTransformation.Models.AudioData;
import offtop.AudioTransformation.Models.IncomingAudioEvent;

@Service
public class AudioConversionService {
    private final Logger logger = LoggerFactory.getLogger(AudioConversionService.class);
    @Autowired
    UploadAudioService postFile;

    @Autowired
    ProducerService producer;

    
    public void writeBytesToFile(IncomingAudioEvent incomingAudioEvent){
        AudioData audioData = new AudioData(
        incomingAudioEvent.getAudioData(),
        incomingAudioEvent.getUserId(),
        incomingAudioEvent.getTimeStamp().toString(),
        incomingAudioEvent.getTopic().toString()
        );
        List<Double> audioDataList = (List<Double>) incomingAudioEvent.getAudioData();
        try {
            File someFile = new File("AudioFile.wav");
            FileOutputStream fos = new FileOutputStream(someFile);
            fos.write(writeToBytes(audioDataList));
            fos.flush();
            fos.close();
            logger.info("File created: setting <file> ->incomingAudioEvent");
            audioData.setFilePath(someFile.getAbsolutePath());
            postFile.postGCP(audioData);
            produceAudioData(audioData);//Produce data to python microservice
        }catch (Exception e) {
            logger.info("Error: " + e);
        }
    }
    byte[] writeToBytes(List<Double> audioData) {
        byte[] result = new byte[audioData.size()];
        for (int i = 0; i < audioData.size(); i++) {
            result[i] = audioData.get(i).byteValue();
        }
        return result;  
    }
    public void produceAudioData( AudioData outgoingAudioData ) {
        producer.sendAudioFile(outgoingAudioData);
    }

}