package offtop.AudioTransformation.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.common.collect.Lists;

import org.springframework.stereotype.Service;

import offtop.AudioTransformation.Models.AudioData;



@Service
public class UploadAudioService {
    final String projectID ="off-top-firebase";
    final String bucketName ="off-top-firebase.appspot.com";
    final String objectName ="wavFile #1";

    public String postGCP(AudioData AudioObject) throws IOException{
        final String fileName =  AudioObject.getUserId() + "_"+ AudioObject.getTimeStamp() + ".wav";
        Bucket bucket = validateGCPStorage("off-top-firebase.appspot.com");
        InputStream inputStream = new FileInputStream(AudioObject.getFilePath());
        
        Blob blob = bucket.create(fileName, inputStream, "audio/wav");
        System.out.println("\nGot here. Uploading File");
        return blob.getMediaLink();
    }    
    
    private Bucket validateGCPStorage(String bucketName) throws IOException {
        
            //code with user defined environment variable
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.get(bucketName);
        if (bucket == null) {
        throw new IOException("Bucket not found:"+bucketName);
        }
        return bucket;
    }
}