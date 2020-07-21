package offtop.AudioTransformation.Services;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Blob;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import offtop.AudioTransformation.Models.AudioData;



@Service
public class UploadAudioService {
    Logger logger = LoggerFactory.getLogger(UploadAudioService.class);
    public String postGCP(AudioData AudioObject) throws IOException{
        final String fileName =  AudioObject.getUserId() + "_"+ AudioObject.getTimeStamp() + ".wav";
        Bucket bucket = validateGCPStorage("off-top-firebase.appspot.com");
        InputStream inputStream = new FileInputStream(AudioObject.getFilePath());
        Blob blob = bucket.create(fileName, inputStream, "audio/wav");
        logger.info("File Successfully Uploaded");
        return blob.getMediaLink();
    }    
    
    private Bucket validateGCPStorage(String bucketName) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
        throw new IOException("Bucket not found:"+bucketName);
        }
        return bucket;
    }
}