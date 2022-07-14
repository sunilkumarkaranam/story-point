package com.thanh.connect.springboot.controller;

import com.atlassian.connect.spring.AtlassianHostRestClients;
import com.atlassian.connect.spring.IgnoreJwt;

import com.bosch.callapi.model.StoryPoint;
import com.bosch.callapi.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;

import java.util.ArrayList;

@RestController
public class HelloWorldController {
	  @Autowired private AtlassianHostRestClients atlassianHostRestClients;
	  
	  @IgnoreJwt
	  
	  @GetMapping("callapi") public ModelAndView getAllStoryPoint() { ModelAndView
	  mv = new ModelAndView("listissues"); User currentUser =
	  atlassianHostRestClients.authenticatedAsHostActor().getForObject(
	  "https://woven-handbook-352309.el.r.appspot.com/all", User.class); String
	  dataResponse = atlassianHostRestClients. authenticatedAsHostActor().
	  getForObject("https://woven-handbook-352309.el.r.appspot.com/all",
	  String.class); JsonObject jsonObject = new
	  Gson().fromJson(dataResponse,JsonObject.class); StoryPoint storypoint = new
	  StoryPoint(dataResponse, null); //Get total storypoints
	  storypoint.setStoryPoint(jsonObject.get("storyPoint").getAsInt()); 
	  //issuekey
	  JsonArray array = jsonObject.getAsJsonArray("issuekey"); 
	  for (int i = 0;i < array.size(); i++) { //get key of issue
	  storypoint.setIssueKey(array.get(i).getAsJsonObject().get("issueKey").
	  getAsString());
	  
	  } mv.addObject("user", currentUser); mv.addObject("storypoint", storypoint);
	  return mv; }
	 
	
	
	 
   
		/*
		 * RestTemplate restTemplate = null; try { restTemplate =
		 * this.createRestTemplate(); } catch (Exception e) { throw new
		 * RuntimeException(e); } String url =
		 * "https://woven-handbook-352309.el.r.appspot.com/all".trim();
		 * ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,
		 * String.class); // responseEntity.getBody() is get success list story point
		 * 
		 * return responseEntity.getBody();
		 */
    
	
		/*
		 * @GetMapping("insert") public String insert(){ RestTemplate restTemplate =
		 * null; try { restTemplate = this.createRestTemplate(); } catch (Exception e) {
		 * throw new RuntimeException(e); } StoryPoint storyPoint = new
		 * StoryPoint("Key-123456",123456); String url =
		 * "https://woven-handbook-352309.el.r.appspot.com/insert";
		 * ResponseEntity<String> responseEntity =
		 * restTemplate.postForEntity(url,storyPoint, String.class); //
		 * responseEntity.getBody() ; //is get success list story point // GOOGLE CLOUD
		 * return responseEntity.getBody(); }
		 * 
		 * private RestTemplate createRestTemplate() throws Exception { final String
		 * username = "uql1kor"; final String password = "Sunimouni@1997"; final String
		 * proxyUrl = "rb-proxy-unix-apac.bosch.com"; final int port = 8080;
		 * 
		 * CredentialsProvider credsProvider = new BasicCredentialsProvider();
		 * credsProvider.setCredentials( new AuthScope(proxyUrl, port), new
		 * UsernamePasswordCredentials(username, password) );
		 * 
		 * HttpHost myProxy = new HttpHost(proxyUrl, port); HttpClientBuilder
		 * clientBuilder = HttpClientBuilder.create();
		 * 
		 * clientBuilder.setProxy(myProxy).setDefaultCredentialsProvider(credsProvider).
		 * disableCookieManagement();
		 * 
		 * HttpClient httpClient = clientBuilder.build();
		 * HttpComponentsClientHttpRequestFactory factory = new
		 * HttpComponentsClientHttpRequestFactory(); factory.setHttpClient(httpClient);
		 * 
		 * return new RestTemplate(factory); }
		 */
}
