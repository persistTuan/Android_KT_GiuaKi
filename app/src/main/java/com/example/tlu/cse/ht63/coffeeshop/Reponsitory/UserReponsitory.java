package com.example.tlu.cse.ht63.coffeeshop.Reponsitory;

import android.content.Context;

import com.example.tlu.cse.ht63.coffeeshop.Database.DbUserHelper;
import com.example.tlu.cse.ht63.coffeeshop.Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserReponsitory {
    private static List<User> userList;
    private DbUserHelper db;

    public UserReponsitory(Context context) {
        userList = new ArrayList<>();
        db = new DbUserHelper(context);
        userList = db.getAll();
    }

    public List<User> getAll(){
        userList = db.getAll();
        return userList;
    }

    public void insert(User user){
        db.insertUser(user);
        userList.add(user);
    }
    public void update(User user){
        db.updateUser(user);
        userList.set(userList.indexOf(user), user);
    }
    public void delete(User user){
        db.deleteUser(user);
        userList.remove(user);
    }

    public User getUser(String userName, String password){
        for(User user : userList){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

}
