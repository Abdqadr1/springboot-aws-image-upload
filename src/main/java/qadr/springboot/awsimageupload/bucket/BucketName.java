package qadr.springboot.awsimageupload.bucket;

public enum BucketName {
    AWS_PROFILE_IMAGE("qadr-springboot-image-upload");
    private String bucketName;

    BucketName (String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
