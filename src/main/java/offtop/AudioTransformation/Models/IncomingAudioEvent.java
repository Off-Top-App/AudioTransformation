package offtop.AudioTransformation.Models;

import java.util.List;

public class IncomingAudioEvent {
    private List<Double> audioData;
    private int userId;
    private String timeStamp;
    private String topic;
    private String filePath;
    

    public IncomingAudioEvent(List<Double> audioData, int userId, String timeStamp, String topic) {
        this.audioData = audioData;
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.topic = topic;
        this.filePath = null;
    }

    public List<Double> getAudioData() {
        return audioData;
    }

    public void setAudioData(List<Double> audioData) {
        this.audioData = audioData;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    
}