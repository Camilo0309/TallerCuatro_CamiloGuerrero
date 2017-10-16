package com.i001114.tallercuatro.Parsers;

import com.i001114.tallercuatro.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 12/10/2017.
 */

public class JsonUser {

    public static List<User> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<User> userlist = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);
            User user = new User();

            user.setId(item.getInt("id"));
            user.setName(item.getString("name"));
            user.setUsername(item.getString("username"));

            JSONObject address = item.getJSONObject("address");
            user.setAddress(address.getString("city"));

            JSONObject company = item.getJSONObject("company");
            user.setCompany(company.getString("name"));

            userlist.add(user);
        }
        return userlist;
    }

}
