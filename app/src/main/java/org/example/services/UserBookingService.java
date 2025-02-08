package org.example.services;
import org.example.entities.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String USERS_PATH = "C:\\Users\\Nogi2\\OneDrive\\Desktop\\bruh\\Java-Backend\\IRCTC-Backend\\app\\src\\main\\java\\org\\example\\localdb\\users.json";

    public UserBookingService(User passedUser) throws IOException{
        this.user = passedUser; 
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){}

    public Boolean signUp(User user){}
}
