package com.mulia.rafif.mybetadroidz;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SingleActivity extends AppCompatActivity implements View.OnClickListener {

    public static String EXTRA_KEY = "extra_key";
    String id;
    private EditText edt_kodeAngkot, edt_ruteAngkot, edt_namaSupir, edt_namaJuragan, edt_tanggalPembuatan;
    private Button btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);


        id = getIntent().getStringExtra(EXTRA_KEY);

        edt_kodeAngkot = findViewById(R.id.edt_kodeAngkot);
        edt_kodeAngkot.setEnabled(false);
        edt_ruteAngkot = findViewById(R.id.edt_ruteAngkot);
        edt_namaSupir = findViewById(R.id.edt_namaSupir);
        edt_namaJuragan = findViewById(R.id.edt_namaJuragan);
        edt_tanggalPembuatan = findViewById(R.id.edt_tanggalPembuatan);

        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateF.format(Calendar.getInstance().getTime());
        edt_tanggalPembuatan.setText(date);

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDel);

        btnDelete.setOnClickListener(this);
        btnEdit.setOnClickListener(this);

        getDetailData detail = new getDetailData(this);
        detail.execute();
    }

    private void appendToLayout(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            Log.d("LogInfo", "Tes data : "+obj);
            edt_kodeAngkot.setText(obj.getString("0"));
            edt_ruteAngkot.setText(obj.getString("1"));
            edt_namaSupir.setText(obj.getString("2"));
            edt_namaJuragan.setText(obj.getString("3"));
            edt_tanggalPembuatan.setText(obj.getString("4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEdit:
                String kodeAngkot = edt_kodeAngkot.getText().toString();
                String ruteAngkot = edt_ruteAngkot.getText().toString();
                String namaSupir = edt_namaSupir.getText().toString();
                String namaJuragan = edt_namaJuragan.getText().toString();
                String tanggalPembuatan = edt_tanggalPembuatan.getText().toString();

                String type = "editAngkot";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, kodeAngkot, ruteAngkot, namaSupir, namaJuragan, tanggalPembuatan);
                break;
            case R.id.btnDel:
                String type1 = "delAngkot";
                BackgroundWorker backgroundWorker1 = new BackgroundWorker(this);
                backgroundWorker1.execute(type1, id);
                break;
        }
    }

    public class getDetailData extends AsyncTask<String, Void, String> {

        Context ctx;

        public getDetailData(Context ctx) {
            this.ctx = ctx;
        }

        protected void onPreExecute() {
            Log.d("LogInfo", "preExecute");
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            Log.d("LogInfo","mengambil detail data");
            String urlPath = "http://mybetadroid.co.nf/detail.php";
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!=null) {
                    result+=line;
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
            if (result.equals("data tidak ditemukan")) {
                alertDialog.setMessage("Data Tidak ditemukan");
                alertDialog.show();
            }
            else if (result.equals("detail error")) {
                alertDialog.setMessage("Data Tidak ditemukan");
                alertDialog.show();
            }
            else {
                Log.d("LogInfo", "Menampilkan Detail Data");
                appendToLayout(result);
            }
        }
    }
}