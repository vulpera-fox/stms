package com.project.stms.service.s3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

@Component
public class S3Service {

	@Autowired
	@Qualifier("s3Client")
	private S3Client s3;

	@Value("${aws_mybucket_name}")
	private String bucketName;

	public void getMyBucket() {

		ListBucketsRequest listBucketRequest = ListBucketsRequest.builder().build();
		ListBucketsResponse ListBucketsResponse = s3.listBuckets(listBucketRequest);
		ListBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));

	}
	

	public void uploadProfile(String originName, byte[] fileData) {

		try {

			Map<String, String> metadata = new HashMap<>();

			metadata.put("x-amz-meta-myVal", "test");
			PutObjectRequest putOb = PutObjectRequest.builder()
					.bucket(bucketName) // 버킷이름
					.key(originName) // 파일이름
					.contentType("image/jpeg")
					.metadata(metadata).build();

			PutObjectResponse response = s3.putObject(putOb, RequestBody.fromBytes(fileData));

			System.out.println("Successfully placed " + originName + " into bucket " + bucketName);

			System.out.println(response.sdkHttpResponse().statusCode());

		} catch (S3Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void uploadFiles(String originName, byte[] fileData) {

		try {

			Map<String, String> metadata = new HashMap<>();

			metadata.put("x-amz-meta-myVal", "test");
			PutObjectRequest putOb = PutObjectRequest.builder().bucket(bucketName) // 버킷이름
					.key(originName) // 파일이름
					.metadata(metadata).build();

			PutObjectResponse response = s3.putObject(putOb, RequestBody.fromBytes(fileData));

			System.out.println("Successfully placed " + originName + " into bucket " + bucketName);

			System.out.println(response.sdkHttpResponse().statusCode());

		} catch (S3Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public boolean readFiles(String keyName) {
		
		System.out.println("키네임 : " + keyName);
		try {
			ListObjectsRequest listObjects = ListObjectsRequest.builder().bucket(bucketName).build();

			ListObjectsResponse res = s3.listObjects(listObjects);
			List<S3Object> objects = res.contents();
			for (S3Object myValue : objects) {

				
				System.out.print("\n" + myValue.key().equals( keyName));
				System.out.print("\n The name of the key is " + myValue.key());
				System.out.print("\n The owner is " + myValue.owner());
				if(keyName.equals(myValue.key())) {
					return true;
				}
			}

		} catch (S3Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
		}
		
		
		return false;
	}
	
	
	public void checkFile(String keyName) {
		
		 try {
	            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
	                .bucket(bucketName)
	                .key(keyName)
	                .build();

	            HeadObjectResponse response = s3.headObject(headObjectRequest);
	            System.out.println("The Amazon S3 object restoration status is "+response.restore());

	        } catch (S3Exception e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	        }
		
	}

	public void downloadFiles(String keyName) {

		String home = System.getProperty("user.home");

		System.out.println(home);
		try {
            GetObjectRequest objectRequest = GetObjectRequest
                .builder()
                .key(keyName)
                .bucket(bucketName)
                .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3.getObjectAsBytes(objectRequest);
            byte[] data = objectBytes.asByteArray();

            // Write the data to a local file.
            File myFile = new File(home + File.separator + "Downloads" + File.separator + keyName);
            OutputStream os = new FileOutputStream(myFile);
            os.write(data);
            System.out.println("Successfully obtained bytes from an S3 object");
            os.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
		
		
		
		
		

	}

}
