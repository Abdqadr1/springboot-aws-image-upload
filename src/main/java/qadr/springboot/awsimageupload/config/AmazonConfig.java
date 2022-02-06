package qadr.springboot.awsimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 amazons3() {
        // the bucket credentials from aws s3
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                "AKIAWJX4HZLNVUPF3I5A",
                "Yiz083z203wKuBuag3qWb4ONnES2aHwbG99FlZUp"
        );
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}