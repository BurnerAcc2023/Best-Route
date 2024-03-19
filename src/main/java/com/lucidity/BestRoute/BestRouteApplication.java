package com.lucidity.BestRoute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lucidity.BestRoute.entity.Restaurant;
import com.lucidity.BestRoute.service.CustomerService;
import com.lucidity.BestRoute.util.FileReaderUtils;
import com.lucidity.BestRoute.util.HaversineDistanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.lucidity.BestRoute"})
public class BestRouteApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(BestRouteApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		TypeReference<List<Restaurant>> typeReference = new TypeReference<>() {};
		List<Restaurant> list = FileReaderUtils.read("ExampleRestaurantData.json", typeReference);
		for(Restaurant r : list) {
			System.out.println(r.getAddress());
		}
	}
}
