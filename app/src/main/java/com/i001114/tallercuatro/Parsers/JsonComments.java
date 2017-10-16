package com.i001114.tallercuatro.Parsers;

import com.i001114.tallercuatro.Models.Comments;
import com.i001114.tallercuatro.Models.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 14/10/2017.
 */

public class JsonComments {

    public static List<Comments> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Comments> commentsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);
            Comments comments = new Comments();

            comments.setPostid(item.getInt("postId"));
            comments.setId(item.getInt("id"));
            comments.setEmail(item.getString("email"));
            comments.setBody(item.getString("body"));

            commentsList.add(comments);
        }
        return commentsList;
    }

}
