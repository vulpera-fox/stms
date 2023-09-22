package com.project.stms.security.config;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JWTService {


	private static String secretKey = "projectstms"; //원래는 어렵게 설정 

	//토큰생성
	public static String createToken(String user_email) {

		//알고리즘생성
		Algorithm alg =  Algorithm.HMAC256(secretKey);

		//만료시간
		long expire = System.currentTimeMillis() + 3600000; // 1시간 뒤

		//토튼 생성
		Builder builder = JWT.create().withSubject(user_email)//주제
				.withIssuedAt(new Date())//발행일
				.withExpiresAt(new Date(expire))//만료시간
				.withIssuer("junhee") //발행자
				.withClaim("admin", "공개클레임~ 부가적으로 원하는거 설정"); // +공개클레임

		return builder.sign(alg); //객체 생성

	}


	//토큰복호화
	public static int decodeJwt(HttpServletRequest request) {


		try {
			Cookie[] list = request.getCookies();

			String token = "";//token이 쿠키에 저장 돼있음

			for(Cookie cookies:list) {
				if(cookies.getName().equals("Authorization")) {//쿠키 네임이 Authorization인 쿠키의 값을 가져옴

					//System.out.println("토큰값 쿠키로 가져옴"+cookies.getValue());
					token = cookies.getValue();
				}
			}

			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(token);

			//토큰 만료시간
			Date expirationDate = decodedJWT.getExpiresAt();
			long expirationTimeInMillis = expirationDate.getTime();

			//현재시간
			Date currentDate = new Date();
			long currentDateTimeInMillis = currentDate.getTime();

			//System.out.println(expirationDate);
			//System.out.println((expirationTimeInMillis-currentDateTimeInMillis)+" 이것이 만료시간인가??");
			int seconds = (int)((expirationTimeInMillis-currentDateTimeInMillis)/1000);
			
			return seconds;
		} catch (Exception e) {
			// JWT 디코딩에 실패한 경우 처리할 내용을 여기에 추가할 수 있습니다.
			return 0;
		}

	}


	//토큰의 유효성 확인
	public static boolean validateToken(String token) throws JWTVerificationException {
		Algorithm alg = Algorithm.HMAC256(secretKey);

		JWTVerifier verifier = JWT.require(alg).build(); //token을 검증할 객체

		verifier.verify(token); //토큰검사 - 만료기간이나 토큰의 위조 가 발생하면 throws 처리됨


		return true; // - 검증성공시 true, 검증실패시 false
	}

}
