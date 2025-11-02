package com.example.TrainBookingSystem;

import com.example.TrainBookingSystem.entities.Train;
import com.example.TrainBookingSystem.entities.User;
import com.example.TrainBookingSystem.services.UserBookingService;
import com.example.TrainBookingSystem.utils.UserServiceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

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
			option = Integer.parseInt(scanner.nextLine());
			switch(option){
				case 1:
					System.out.println("Enter the username to signup");
					String nameToSignUp = scanner.nextLine();
					System.out.println("Enter the password to signup");
					String passwordToSignUp = scanner.nextLine();

					//user data we obtained is converted into object
					User userToSignup = new User(nameToSignUp, passwordToSignUp,
							UserServiceUtil.hashPassword(passwordToSignUp),
							new ArrayList<>(), UUID.randomUUID().toString());

					//new user added to the DB
					userBookingService.signUp(userToSignup);
					break;

					case 2:
						System.out.println("Enter the username to login");
						String nameToLogin = scanner.nextLine();
						System.out.println("Enter the password to login");
						String passwordToLogin = scanner.nextLine();

						//user data we obtained is converted into the object
						User userToLogin = new User(nameToLogin, passwordToLogin,
								UserServiceUtil.hashPassword(passwordToLogin),
								new ArrayList<>(), UUID.randomUUID().toString()
								);
						try{
							// the user is load in to int system
							userBookingService = new UserBookingService(userToLogin);
							// for process login is send to the UserBooking Service
							if(userBookingService.loginUser()){
								System.out.println("Login successful");
							}
							else {
								System.out.println("Login failed");
							}
						}catch(Exception e){
							System.out.println("error logging in");
							return;
						}
						break;

				case 3:
					System.out.println("Fetching your bookings");
					userBookingService.fetchBooking();
					break;

				case 4:
					System.out.println("Type your source station");
					String source = scanner.next();
					System.out.println("Type your destination station");
					String dest = scanner.next();
					List<Train> trains = userBookingService.getTrains(source, dest);
					int index = 1;
					for (Train t: trains){
						System.out.println(index+" Train id : "+t.getTrainId());
						for (Map.Entry<String, String> entry: t.getStationTimes().entrySet()){
							System.out.println("station "+entry.getKey()+" time: "+entry.getValue());
						}
					}
//					System.out.println("Select a train by typing 1,2,3...");
//					trainSelectedForBooking = trains.get(scanner.nextInt());
					break;
//				case 5:
//					System.out.println("Select a seat out of these seats");
//					List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);
//					for (List<Integer> row: seats){
//						for (Integer val: row){
//							System.out.print(val+" ");
//						}
//						System.out.println();
//					}
//					System.out.println("Select the seat by typing the row and column");
//					System.out.println("Enter the row");
//					int row = scanner.nextInt();
//					System.out.println("Enter the column");
//					int col = scanner.nextInt();
//					System.out.println("Booking your seat....");
//					Boolean booked = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);
//					if(booked.equals(Boolean.TRUE)){
//						System.out.println("Booked! Enjoy your journey");
//					}else{
//						System.out.println("Can't book this seat");
//					}
//					break;
//				default:
//					break;
//

			}
		}
	}



}
