package com.mulia.rafif.mybetadroidz;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnfab;
    private AdapterList adapterList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnfab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.rv);

        btnfab.setOnClickListener(this);

        getAllData gets = new getAllData(this);
        gets.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent i = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(i);
                break;
        }
    }

    private void loadIntoRecycleView(String result) {
        try {
            ArrayList<AngkotPojo> angkotArrayList = new ArrayList<>();
            JSONObject jsonObj = new JSONObject(result);
            for (int i = 0; i < jsonObj.length(); i++) {
                String data = (jsonObj.getString(""+i+""));
                JSONObject obj = new JSONObject(data);
                AngkotPojo angkot = new AngkotPojo();
                angkot.setKode_angkot(obj.getString("0"));
                angkot.setRute_angkot(obj.getString("1"));
                angkot.setNama_supir(obj.getString("2"));
                angkot.setNama_juragan(obj.getString("3"));
                angkot.setTanggal_pembuatan(obj.getString("4"));
                angkotArrayList.add(angkot);
            }
            adapterList = new AdapterList(angkotArrayList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapterList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class getAllData extends AsyncTask<String, Void, String> {
        Context ctx;

        public getAllData(Context context) {
            this.ctx = context;
        }

        @Override
        protected void onPreExecute() {
            Log.d("LogInfo", "preExecute");
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("LogInfo", "Menjalankan Koneksi ke DB kemudian execute");
            String urlPath = "";
            urlPath = "http://mybetadroid.co.nf/getData.php";
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("notication");
            if (result == null) {
                Log.d("LogInfo", "Tidak bisa mendapatkan data angkot /n mungkin servernya sedang bermasalah");
                alertDialog.setMessage("Server Sedang Bermasalah Coba Nanti");
                alertDialog.show();
            } else {
                Log.d("LogInfo", "Berhasil Mendapatkan data kemudian mengalihkannya ke loadintorecycleview");
                loadIntoRecycleView(result);
//                alertDialog.setMessage(result);
//                alertDialog.show();
            }
        }
    }
}
