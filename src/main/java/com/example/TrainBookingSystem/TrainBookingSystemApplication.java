package com.example.TrainBookingSystem;

import com.example.TrainBookingSystem.services.UserBookingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TrainBookingSystemApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TrainBookingSystemApplication.class, args);
		System.out.println("Running Train Booking System");
		Scanner scanner = new Scanner (System.in);
		int option = 0 ;
		UserBookingService userBookingService;
		try{
				userBookingService = new UserBookingService();
		}
		catch(Exception e){
			System.out.println("khux toh gadbad hye daya");
			return;
		}
	}

}
