package com.i001114.tallercuatro.Parsers;

import com.i001114.tallercuatro.Models.Post;
import com.i001114.tallercuatro.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 14/10/2017.
 */

public class JsonPost {

    public static List<Post> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Post> userpost = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);
            Post post = new Post();

            post.setUserid(item.getInt("userId"));
            post.setId(item.getInt("id"));
            post.setTitle(item.getString("title"));
            post.setBody(item.getString("body"));

            userpost.add(post);
        }
        return userpost;
    }


}
