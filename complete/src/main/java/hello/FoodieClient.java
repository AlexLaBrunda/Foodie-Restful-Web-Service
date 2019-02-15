package hello;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FoodieClient {

	public static void main(String[] args) throws IOException {
		String foodieUrl = "http://localhost:8080/greeting?name=";
		
		System.out.println("Please enter the name of the street E.g. 1600 Pennsylvania Ave NW");
		Scanner k = new Scanner(System.in);
		String street = k.nextLine();
		foodieUrl += street.replaceAll(" ", "+");
		foodieUrl += "%2c+";
		
		System.out.println("Please enter the name of the city E.g. Washington");
		foodieUrl += k.nextLine() + "+";
		
		System.out.println("Please enter the initials of the state E.g. DC");
		foodieUrl += k.nextLine() + "+";
		
		System.out.println("Please enter the postal zip code E.g. 20500");
		foodieUrl += k.nextLine();
		
		System.out.println(foodieUrl);
		
		RestTemplate foodieTemplate = new RestTemplate();
		ResponseEntity<String> foodieResponse = foodieTemplate.getForEntity(foodieUrl, String.class);
		if(foodieResponse.getStatusCodeValue() == 400) {
			System.out.println("Bad input error code: 400.");
			System.exit(400);
		}else if(foodieResponse.getStatusCodeValue() == 422) {
			System.out.println("Unprocessable Entity over the limit for number of"
					+ " requests per day error code: 422");
			System.exit(422);
		}else if(foodieResponse.getStatusCodeValue() == 500) {
			System.out.println("One of the APIs failed on its end error code: 500");
			System.exit(500);
		}
		ObjectMapper foodieMapper = new ObjectMapper();
		JsonNode foodieRoot = foodieMapper.readTree(foodieResponse.getBody());
		System.out.println(foodieRoot.toString());
		
		//output needs better formatting
	}

}
