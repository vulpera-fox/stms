package com.project.stms.service.aws;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.Body;
import software.amazon.awssdk.services.sesv2.model.Content;
import software.amazon.awssdk.services.sesv2.model.Destination;
import software.amazon.awssdk.services.sesv2.model.EmailContent;
import software.amazon.awssdk.services.sesv2.model.Message;
import software.amazon.awssdk.services.sesv2.model.SendEmailRequest;
import software.amazon.awssdk.services.sesv2.model.SesV2Exception;

@Service
public class SesService {

	//어세스키
	@Value("${aws_access_key_id}")
	private String aws_access_key_id;
	//시크릿키
	@Value("${aws_secret_access_key}")
	private String aws_secret_access_key; 


	////////////////////ses//////////////////
	public void sendEmail(String sender,
			String recipient,
			String subject,
			String bodyHTML
			){

		//자격증명객체
		AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id, aws_secret_access_key);

		//ses클라이언트
		SesV2Client sesv2Client = SesV2Client.builder()
				.region(Region.AP_NORTHEAST_2)
				.credentialsProvider( StaticCredentialsProvider.create(credentials) )
				.build();



		Destination destination = Destination.builder()
				.toAddresses(recipient)
				.build();

		Content content = Content.builder()
				.data(bodyHTML)
				.build();

		Content sub = Content.builder()
				.data(subject)
				.build();

		Body body = Body.builder()
				.html(content)
				.build();

		Message msg = Message.builder()
				.subject(sub)
				.body(body)
				.build();

		EmailContent emailContent = EmailContent.builder()
				.simple(msg)
				.build();

		SendEmailRequest emailRequest = SendEmailRequest.builder()
				.destination(destination)
				.content(emailContent)
				.fromEmailAddress(sender)
				.build();

		try {

			System.out.println("Attempting to send an email through Amazon SES " + "using the AWS SDK for Java...");

			//ses호출
			sesv2Client.sendEmail(emailRequest);


			System.out.println("email was sent");

		} catch (SesV2Exception e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			//System.exit(1);
		}
	}


}
