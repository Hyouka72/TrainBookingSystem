package com.example.TrainBookingSystem;

import com.example.TrainBookingSystem.entities.User;
import com.example.TrainBookingSystem.services.UserBookingService;
import com.example.TrainBookingSystem.utils.UserServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class TrainBookingSystemApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TrainBookingSystemApplication.class, args);
		System.out.println("Running Train Booking System");
		Scanner scanner = new Scanner (System.in);
		int option = 0 ;


		UserBookingService userBookingService;
		try{
				//to load list of user when webpage is open
				userBookingService = new UserBookingService();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("khux toh gadbad hye daya");
			return;
		}

		while(option != 7){
			System.out.println("Choose option");
			System.out.println("1. Sign up");
			System.out.println("2. Login");
			System.out.println("3. fetch Bookings");
			System.out.println("4. Search Trains");
			System.out.println("5. Book a Seat");
			System.out.println("6. cancel my Booking");
			System.out.println("7. Exit");
			option = scanner.nextInt();
			switch(option){
				case 1:
					System.out.println("Enter the username to signup");
					String nameToSignUp = scanner.nextLine();
					System.out.println("Enter the password to signup");
					String passwordToSignUp = scanner.nextLine();
					User userToSignup = new User(nameToSignUp, passwordToSignUp,
							UserServiceUtil.hashPassword(passwordToSignUp),
							new ArrayList<>(), UUID.randomUUID().toString());
					userBookingService.signUp(userToSignup);
					break;

					case 2:
						System.out.println("Enter the username to login");
						String nameToLogin = scanner.nextLine();
						System.out.println("Enter the password to signup");
						String passwordToLogin = scanner.nextLine();
						User userToLogin = new User(nameToLogin, passwordToLogin,
								UserServiceUtil.hashPassword(passwordToLogin),
								new ArrayList<>(), UUID.randomUUID().toString()
								);
						try{
							userBookingService = new UserBookingService(userToLogin);
						}catch(Exception e){
							return;
						}
						break;


			}
		}
	}



}
