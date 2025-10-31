package com.example.TrainBookingSystem.utils;
import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {

    public static String hashPassword(String plainpassword) {
        return BCrypt.hashpw(plainpassword, BCrypt.gensalt());

    }

    public static boolean checkpassword(String plainpassword, String hashedPassword){
        return BCrypt.checkpw(plainpassword,hashedPassword);
    }


}
