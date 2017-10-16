package com.i001114.tallercuatro;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i001114.tallercuatro.Adapters.AdapterComments;
import com.i001114.tallercuatro.Adapters.AdapterPost;
import com.i001114.tallercuatro.Adapters.AdapterUser;
import com.i001114.tallercuatro.Connection.HttpManagerUser;
import com.i001114.tallercuatro.Models.Comments;
import com.i001114.tallercuatro.Models.User;
import com.i001114.tallercuatro.Parsers.JsonComments;
import com.i001114.tallercuatro.Parsers.JsonPost;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterComments adapterComments;
    List<Comments> commentsList;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item2);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item2);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        showToolbar(getResources().getString(R.string.str_tb_p3), true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        Bundle a = getIntent().getExtras();
        loadData(Integer.toString(a.getInt("principalid2")));
    }

    public void showToolbar(String title, boolean flecha){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(flecha);
    }

    public boolean onCreateOptionsMenu(Menu menu){
       // getMenuInflater().inflate(R.menu.menucomments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Showpantallatres();
        return super.onOptionsItemSelected(item);
    }

    public void Showpantallatres(){
        Intent a = new Intent(getApplicationContext(), CommentsActivity.class);
        startActivity(a);
    }

    //###########################################TAREA ASYNTASK#############################################################
    public class TaskCountry extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManagerUser.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                commentsList = JsonComments.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            progressBar.setVisibility(View.GONE);

        }
    }

    public void processData(){
        adapterComments = new AdapterComments(commentsList, getApplicationContext());
        recyclerView.setAdapter(adapterComments);
    }
//#####################################################################################################

    public Boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(String str2){
        if (isOnLine()){
            CommentsActivity.TaskCountry taskCountry = new CommentsActivity.TaskCountry();
            taskCountry.execute("https://jsonplaceholder.typicode.com/comments?postId=" + str2);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
}

