package com.project.stms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig2 {
	
	@Value("${aws_access_key_id_s3_jh}")
	private String aws_access_key_id;
	
	@Value("${aws_secret_access_key_s3_jh}")
	private String aws_secret_access_key;
	
	
	@Bean
	public StaticCredentialsProvider staticCredentialsProvider2() {
		
		AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);
		
		StaticCredentialsProvider staticCredentialProvider2 = StaticCredentialsProvider.create(credentials);
		
		return staticCredentialProvider2;
	}

	@Bean
	public S3Client s3Client2() {
		
		Region region = Region.AP_NORTHEAST_2;
		
		S3Client s3 = S3Client.builder().region(region).credentialsProvider(staticCredentialsProvider2()).build();
		
		return s3;
	}
	
	
}
