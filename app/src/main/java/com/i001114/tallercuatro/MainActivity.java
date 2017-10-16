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
import android.widget.Switch;
import android.widget.Toast;


import com.i001114.tallercuatro.Adapters.AdapterUser;
import com.i001114.tallercuatro.Connection.HttpManagerUser;

import com.i001114.tallercuatro.Models.User;
import com.i001114.tallercuatro.Parsers.JsonUser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterUser adapterUser;
    List<User> userList;
    Button button;
    ProgressBar progressBar;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item);
        button = (Button) findViewById(R.id.id_btn_listcountries);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        showToolbar(getResources().getString(R.string.str_tb_p1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void showToolbar(String title){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.maindos, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){

        Toast t = Toast.makeText(this, "Usuarios Actualizados", Toast.LENGTH_SHORT);
        return super.onOptionsItemSelected(menuItem);

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
                userList = JsonUser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            progressBar.setVisibility(View.GONE);

        }
    }

    public void processData(){
        adapterUser = new AdapterUser(userList, getApplicationContext());
        recyclerView.setAdapter(adapterUser);
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

    public void loadData(View view){
        if (isOnLine()){
            TaskCountry taskCountry = new TaskCountry();
            taskCountry.execute("https://jsonplaceholder.typicode.com/users");
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }



}
