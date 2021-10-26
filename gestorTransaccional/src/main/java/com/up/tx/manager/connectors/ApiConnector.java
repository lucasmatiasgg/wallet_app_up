package com.up.tx.manager.connectors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.up.tx.manager.login.model.User;
import com.up.tx.manager.login.service.UserService;
import com.up.tx.manager.model.Configuration;
import com.up.tx.manager.model.GenericApiResponse;
import com.up.tx.manager.model.Payment;
import com.up.tx.manager.model.Response;
import com.up.tx.manager.model.TokenApiResponse;
import com.up.tx.manager.model.Transaction;
import com.up.tx.manager.service.ConfigurationService;
import com.up.tx.manager.utils.DateUtils;


public class ApiConnector {
	
	private static final String BASE_PATH = "http://ec2-54-174-89-255.compute-1.amazonaws.com:8080";
	private static final String GET_TOKEN_PATH = "/auth";
	private static final String PAYMENT_PATH = "/transaction";
	private static final String CREATE_USER_PATH = "/user/create";
	private static final String CREATE_COMMERCE_PATH = "/commerce/create";
	public static final Integer CREATE_OK_CODE = 201;
	public static final Integer TRANSACTION_OK_CODE = 200;
	public static final Integer TRANSACTION_ERROR_CODE = 400;
	
//	public static String getToken(String userName, String pass) {
//	
//        RestTemplate restTemplate = new RestTemplate();
//     
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HashMap<String, Object> params = new HashMap<>();
//     
//        params.put("username", userName);
//        params.put("password", pass);
//        
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//        
//         
//        ResponseEntity<TokenApiResponse> response = restTemplate.exchange(BASE_PATH + GET_TOKEN_PATH, HttpMethod.POST, requestEntity, TokenApiResponse.class);
//        
//        
//        return response.getBody().getData().getToken().substring(7);
//        
//	}
//	
//	
//	public static Response processTransaction(Payment payment, Transaction transaction, ConfigurationService configService, UserService userService ) {
//		Response response = new Response();
//
//		
//		String apiToken = configService.getById("configuration.api.token");
//		if(StringUtils.isEmpty(apiToken)) {
//			apiToken = ApiConnector.getToken("fuelpay", "123qwerty");
//			
//			Configuration config = new Configuration("configuration.api.token", apiToken);
//			configService.create(config);
//		}
//		
//		HashMap<String, Object> params = new HashMap<>();
//		HashMap<String, Object> creditCard = new HashMap<>();
//		HashMap<String, Object> user = new HashMap<>();
//		HashMap<String, Object> commerce = new HashMap<>();
//		
//		params.put("idTransaction", transaction.getTransactionId());
//		params.put("date", DateUtils.formateDate(transaction.getCreatedAt(), "dd/MM/yyyy"));
//		params.put("amount", payment.getAmount());
//		
//		creditCard.put("verificationCode", payment.getVerificationCode());
//		creditCard.put("expirationDate", payment.getExpiry());
//		creditCard.put("name", payment.getName());
//		creditCard.put("number", payment.getNumber().trim().replace(" ", ""));
//		creditCard.put("type", payment.getBrand());
//		params.put("creditCard", creditCard);
//		
//		User userData = userService.getInfoById(payment.getOriginUserId());
//		user.put("dni", payment.getUserIdentificationNumber());
//		
//		if(userData != null) {
//			user.put("name", userData.getFirstName() + userData.getLastName());				
//		}
//		
//		params.put("user", user);
//		
//		User commerceData = userService.getInfoUserByCuit(payment.getCuit());
//		commerce.put("cuit", payment.getCuit());
//		if(commerceData != null) {
//			commerce.put("name", commerceData.getSocialReason());				
//		}
//		params.put("commerce", commerce);
//		
//        RestTemplate restTemplate = new RestTemplate();
//     
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiToken);
//
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//        
//        System.out.println(params);
//        
//        ResponseEntity<GenericApiResponse> responseApi = restTemplate.exchange(BASE_PATH + PAYMENT_PATH, HttpMethod.POST, requestEntity, GenericApiResponse.class);
//        
//        if (responseApi.getStatusCode().is2xxSuccessful()) {
//        	GenericApiResponse body = responseApi.getBody();
//    		response.putItem("statusCode", body.getData().getStatusCode());
//        	return response;
//        	
//        } else {
//        	response.putItem("statusCode", TRANSACTION_ERROR_CODE);
//        	return response;
//        }
//	}
//	
//	
//	public static Response createUser(String name, String dni) {
//		
//		Response response = new Response();
//        RestTemplate restTemplate = new RestTemplate();
//     
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HashMap<String, Object> params = new HashMap<>();
//     
//        params.put("name", name);
//        params.put("dni", dni);
//        
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//        
//         
//        ResponseEntity<GenericApiResponse> responseApi = restTemplate.exchange(BASE_PATH + CREATE_USER_PATH, HttpMethod.POST, requestEntity, GenericApiResponse.class);
//        
//        GenericApiResponse body = responseApi.getBody();
//        if (responseApi.getStatusCode().is2xxSuccessful()) {
//    		response.putItem("statusCode", body.getData().getStatusCode());
//        	return response;
//        	
//        } else {
//        	response.putItem("statusCode", TRANSACTION_ERROR_CODE);
//        	return response;
//        }
//        
//	}
//	
//		public static Response createCommerce(String name, String cuit) {
//		
//		Response response = new Response();
//        RestTemplate restTemplate = new RestTemplate();
//     
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HashMap<String, Object> params = new HashMap<>();
//     
//        params.put("name", name);
//        params.put("cuit", cuit);
//        
//        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, headers);
//        
//         
//        ResponseEntity<GenericApiResponse> responseApi = restTemplate.exchange(BASE_PATH + CREATE_COMMERCE_PATH, HttpMethod.POST, requestEntity, GenericApiResponse.class);
//        
//        if (responseApi.getStatusCode().is2xxSuccessful()) {
//        	GenericApiResponse body = responseApi.getBody();
//    		response.putItem("statusCode", body.getData().getStatusCode());
//        	return response;
//        	
//        } else {
//        	response.putItem("statusCode", TRANSACTION_ERROR_CODE);
//        	return response;
//        }
//        
//	}

}
	