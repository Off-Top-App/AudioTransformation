package offtop.AudioTransformation.Services;


import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import offtop.AudioTransformation.Models.IncomingAudioEvent;

@Service
public class ConsumerService {
    @Autowired 
    AudioConversionService audioConversionService;


    public IncomingAudioEvent getIncomingAudioEvent(Map incomingAudioEvent) {
        System.out.println("\n\n\n\nIncoming List<Double>: " + incomingAudioEvent.get("audio_data")+ "\n\n\n\n");
        
        return new IncomingAudioEvent(
            (List<Double>) incomingAudioEvent.get("audio_data"),
            ((Double) incomingAudioEvent.get("user_id")).intValue(),
            incomingAudioEvent.get("time_exported").toString(),
            incomingAudioEvent.get("topic").toString()
        );  
        
    }

	public void uploadConsumedAudioData(Map consumedIncomingAudioEvent) {
        IncomingAudioEvent incomingAudioEvent  = getIncomingAudioEvent(consumedIncomingAudioEvent);
        audioConversionService.writeBytesToFile(incomingAudioEvent);
        

    }
    
}
