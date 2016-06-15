package com.haurylenka.projects.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class Soap {
	
	private static final String GET_CUSTOMERS_BODY = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.soap.projects.haurylenka.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<ser:getCustomers>"
			+ "<code>1111</code>"
			+ "</ser:getCustomers>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	private static final String GET_CUSTOMER_BODY = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.soap.projects.haurylenka.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<ser:getCustomer>"
			+ "<code>1111</code>"
			+ "<id>2</id>"
			+ "</ser:getCustomer>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	private static final String ADD_CUSTOMER_BODY = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.soap.projects.haurylenka.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<ser:addCustomer>"
			+ "<code>1111</code>"
			+ "<email>email6</email>"
			+ "<phone>phone6</phone>"
			+ "</ser:addCustomer>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	private static final String UPDATE_CUSTOMER_BODY = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.soap.projects.haurylenka.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<ser:updateCustomer>"
			+ "<code>1111</code>"
			+ "<id>3</id>"
			+ "<email>email3</email>"
			+ "<phone>phone3</phone>"
			+ "</ser:updateCustomer>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";
	private static final String DELETE_CUSTOMER_BODY = 
			"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.soap.projects.haurylenka.com/\">"
			+ "<soapenv:Header/>"
			+ "<soapenv:Body>"
			+ "<ser:deleteCustomer>"
			+ "<code>1111</code>"
			+ "<id>3</id>"
			+ "</ser:deleteCustomer>"
			+ "</soapenv:Body>"
			+ "</soapenv:Envelope>";

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
        String strURL = "http://localhost:8080/Soap";
        RequestEntity entity;
		//get all customers
        PostMethod allCustMethod = new PostMethod(strURL);
        entity = new StringRequestEntity(
        		GET_CUSTOMERS_BODY, "text/xml", "UTF-8");
        allCustMethod.setRequestEntity(entity);
        allCustMethod.setRequestHeader("SOAPAction", "getCustomers");
        methods.add(allCustMethod);
        //get customer by id
        PostMethod custByIdMethod = new PostMethod(strURL);
        entity = new StringRequestEntity(
        		GET_CUSTOMER_BODY, "text/xml", "UTF-8");
        custByIdMethod.setRequestEntity(entity);
        custByIdMethod.setRequestHeader("SOAPAction", "getCustomer");
        methods.add(custByIdMethod);
        //add a customer
        PostMethod addCustMethod = new PostMethod(strURL);
        entity = new StringRequestEntity(
        		ADD_CUSTOMER_BODY, "text/xml", "UTF-8");
        addCustMethod.setRequestEntity(entity);
        addCustMethod.setRequestHeader("SOAPAction", "addCustomer");
        methods.add(addCustMethod);
        //update customer
        PostMethod updateCustMethod = new PostMethod(strURL);
        entity = new StringRequestEntity(
        		UPDATE_CUSTOMER_BODY, "text/xml", "UTF-8");
        updateCustMethod.setRequestEntity(entity);
        updateCustMethod.setRequestHeader("SOAPAction", "updateCustomer");
        methods.add(updateCustMethod);
        //delete customer
        PostMethod deleteCustMethod = new PostMethod(strURL);
        entity = new StringRequestEntity(
        		DELETE_CUSTOMER_BODY, "text/xml", "UTF-8");
        deleteCustMethod.setRequestEntity(entity);
        deleteCustMethod.setRequestHeader("SOAPAction", "deleteCustomer");
        methods.add(deleteCustMethod);
		return methods;
	}

}
