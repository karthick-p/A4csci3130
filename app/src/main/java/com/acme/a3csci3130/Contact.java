package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.w3c.dom.Node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String email;
    public  String address;
    public  String number;
    public  String province;
    public  String business;


    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public Contact(String uid, String number, String name, String business, String address,String province, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.number = number;
        this.address = address;
        this.province = province;
        this.business = business;
    }
        @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("number", number);
        result.put("name", name);
        result.put("business", business);
        result.put("address", address);
        result.put("province", province);
        result.put("email", email);

        return result;
    }
}
