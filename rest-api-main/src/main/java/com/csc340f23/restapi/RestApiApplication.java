package com.csc340f23.restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        getActivity();
        System.exit(0);
    }

    public static void getActivity() {
        try {
            String url = "https://www.boredapi.com/api/activity";
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonPrice = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonPrice);

            //gets coin name
            String coinName = root.findValue("activity").asText();
            //gets coin value in USD
            String coinPrice = root.findValue("type").asText();
            //print vals
            System.out.println("activity: " + coinName);
            System.out.println("type: " + coinPrice);

        } catch (JsonProcessingException ex) {
            System.out.println("error in getActivity");
        }
    }

}
