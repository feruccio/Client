package com.haurylenka.projects.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;

public class Rest {
	
	private static final String GET_CUSTOMERS = "http://localhost:8080/Rest/customer/json/1111";
	private static final String GET_CUSTOMER = "http://localhost:8080/Rest/customer/json/1111/1";
	private static final String ADD_CUSTOMER = "http://localhost:8080/Rest/customer/json/1111/email4/phone4";
	private static final String UPDATE_CUSTOMER = "http://localhost:8080/Rest/customer/json/1111/3/email3/phone3";
	private static final String DELETE_CUSTOMER = "http://localhost:8080/Rest/customer/json/1111/3";
	
	public static void main(String[] args) {
		List<HttpMethod> methods = null;
		try {
			methods = getMethods();
	        HttpClient httpclient = new HttpClient();
	        for (HttpMethod method : methods) {
	            int result = httpclient.executeMethod(method);
	            System.out.println("Response status code: " + result);
	            System.out.println("Response body: ");
	            System.out.println(method.getResponseBodyAsString());
	        }
        } catch (IOException e) {
			System.out.println("Client exception");
		} finally {
			if (methods != null) {
				for (HttpMethod method : methods) {
					method.releaseConnection();
				}
			}
        }
	}
	
	private static List<HttpMethod> getMethods() 
			throws UnsupportedEncodingException {
		List<HttpMethod> methods = new ArrayList<>();
		//get all customers
        HttpMethod allCustMethod = new GetMethod(GET_CUSTOMERS);
        methods.add(allCustMethod);
        //get customer by id
        HttpMethod custByIdMethod = new GetMethod(GET_CUSTOMER);
        methods.add(custByIdMethod);
        //add a customer
        HttpMethod addCustMethod = new PostMethod(ADD_CUSTOMER);
        methods.add(addCustMethod);
        //update customer
        HttpMethod updateCustMethod = new PutMethod(UPDATE_CUSTOMER);
        methods.add(updateCustMethod);
        //delete customer
        HttpMethod deleteCustMethod = new DeleteMethod(DELETE_CUSTOMER);
        methods.add(deleteCustMethod);
		return methods;
	}

}
