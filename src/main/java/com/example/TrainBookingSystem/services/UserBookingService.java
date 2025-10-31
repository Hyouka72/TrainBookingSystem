package com.example.TrainBookingSystem.services;


import com.example.TrainBookingSystem.entities.User;
import com.example.TrainBookingSystem.utils.UserServiceUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private ObjectMapper objectMapper = new ObjectMapper();


    private static final String USERS_PATH = "src/main/java/com/example/TrainBookingSystem/LocalDb/users.json";


    //for the existing user
    public UserBookingService(User user) throws IOException {
        this.user = user;
        loadUsers();
    }

    //new user for signup
    public UserBookingService() throws IOException {
        loadUsers();
    }


    //loading users From file json
    public List<User> loadUsers() throws IOException {
        File users = new File(USERS_PATH);
        return userList = objectMapper.readValue(users, new TypeReference<List<User>>() { //deserialization
        });
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkpassword(user.getPassword(), user1.getHashPassword());
        }).findFirst();
        return foundUser.isPresent();
    }
    public Boolean signUp(User user){
        try{
            userList.add(user);
            saveUserListToFile();
            return Boolean.TRUE;
        }
        catch(Exception ex){
            return Boolean.FALSE;
        }
    }
    //saving the new user into the file
    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);  //serialization

    }

    //fetching user train ticket data
    public void fetchBooking(){
        user.printTickets();
    }

    //cancel Booking
     public void cancelBooking(){
         user.getTicketsBooked().clear();
     }







}
