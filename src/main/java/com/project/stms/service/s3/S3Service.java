package com.project.stms.service.s3;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Component
public class S3Service {

	@Autowired
	private S3Client s3;

	@Value("${aws_mybucket_name}")
	private String bucketName;

	public void getMyBucket() {

		ListBucketsRequest listBucketRequest = ListBucketsRequest.builder().build();
		ListBucketsResponse ListBucketsResponse = s3.listBuckets(listBucketRequest);
		ListBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));

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

}
