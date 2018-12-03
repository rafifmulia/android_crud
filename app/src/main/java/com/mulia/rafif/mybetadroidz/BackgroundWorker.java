package com.mulia.rafif.mybetadroidz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    ProgressDialog mProgressDialog;
    AlertDialog alertDialog;
    private Context ctx;

    public BackgroundWorker (Context context) {
        this.ctx = context;
        mProgressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("LogInfo", "Menjalankan Progress Dialog");
        mProgressDialog.setMessage("Loading ...");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setProgressStyle(mProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("LogInfo","Menjalankan Koneksi ke DB kemudian execute");
        String type = params[0];
        String urlPath = "";
        if (type.equals("login")) {
            urlPath = "http://mybetadroid.co.nf/login.php";
            try {
                URL url = new URL(urlPath);
                String email = params[1];
                String password = params[2];
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("register")) {
            urlPath = "http://mybetadroid.co.nf/register.php";
            String username = params[1];
            String email = params[2];
            String password = params[3];
            String alamat = params[4];
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")
                        +"&"+URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")
                        +"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")
                        +"&"+URLEncoder.encode("alamat","UTF-8")+"="+URLEncoder.encode(alamat,"UTF-8");
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
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("addAngkot")) {
            urlPath = "http://mybetadroid.co.nf/addAngkot.php";
            try {
                URL url = new URL(urlPath);
                String kode_angkot = params[1];
                String rute_angkot = params[2];
                String nama_supir = params[3];
                String nama_juragan = params[4];
                String tanggal_pembuatan = params[5];
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("kode_angkot","UTF-8")+"="+URLEncoder.encode(kode_angkot,"UTF-8")
                        +"&"+URLEncoder.encode("rute_angkot","UTF-8")+"="+URLEncoder.encode(rute_angkot,"UTF-8")
                        +"&"+URLEncoder.encode("nama_supir","UTF-8")+"="+URLEncoder.encode(nama_supir,"UTF-8")
                        +"&"+URLEncoder.encode("nama_juragan","UTF-8")+"="+URLEncoder.encode(nama_juragan,"UTF-8")
                        +"&"+URLEncoder.encode("tanggal_pembuatan","UTF-8")+"="+URLEncoder.encode(tanggal_pembuatan,"UTF-8");
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
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("editAngkot")) {
            urlPath = "http://mybetadroid.co.nf/editAngkot.php";
            try {
                URL url = new URL(urlPath);
                String kode_angkot = params[1];
                String rute_angkot = params[2];
                String nama_supir = params[3];
                String nama_juragan = params[4];
                String tanggal_pembuatan = params[5];
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("kode_angkot","UTF-8")+"="+URLEncoder.encode(kode_angkot,"UTF-8")
                        +"&"+URLEncoder.encode("rute_angkot","UTF-8")+"="+URLEncoder.encode(rute_angkot,"UTF-8")
                        +"&"+URLEncoder.encode("nama_supir","UTF-8")+"="+URLEncoder.encode(nama_supir,"UTF-8")
                        +"&"+URLEncoder.encode("nama_juragan","UTF-8")+"="+URLEncoder.encode(nama_juragan,"UTF-8")
                        +"&"+URLEncoder.encode("tanggal_pembuatan","UTF-8")+"="+URLEncoder.encode(tanggal_pembuatan,"UTF-8");
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
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("delAngkot")) {
            urlPath = "http://mybetadroid.co.nf/delAngkot.php";
            try {
                URL url = new URL(urlPath);
                String kode_angkot = params[1];
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                OutputStream outputStream = connection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("kode_angkot","UTF-8")+"="+URLEncoder.encode(kode_angkot,"UTF-8");                bufferedWriter.write(post_data);
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
            }catch(MalformedURLException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("LogInfo", "Menampilkan hasil dari doInBackground");
        final Handler handler = new Handler();
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("notication");
        if (result.equals("login success")) {
            mProgressDialog.dismiss();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx,"login success",Toast.LENGTH_SHORT);
                    Intent next = new Intent(ctx, HomeActivity.class);
                    ctx.startActivity(next);
                    ((Activity)ctx).finish();
                }
            }, 800);
        } else if (result.equals("login error")) {
            mProgressDialog.dismiss();
            alertDialog.setMessage("mungkin email / password salah");
            alertDialog.show();
        }
        else if (result.equals("server error")) {
            mProgressDialog.dismiss();
            alertDialog.setMessage("Server Sedang Bermasalah Coba Nanti");
            alertDialog.show();
        }else if (result.equals("register success")) {
            mProgressDialog.dismiss();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx,"register success",Toast.LENGTH_SHORT);
                    Intent next = new Intent(ctx, MainActivity.class);
                    ctx.startActivity(next);
                    ((Activity)ctx).finish();
                }
            }, 800);
        }else if (result.equals("register error")) {
            mProgressDialog.dismiss();
            alertDialog.setMessage("Jangan diotak-atik yah");
            alertDialog.show();
        }
        else if (result.equals("add angkot success")) {
            mProgressDialog.dismiss();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx,"add angkot success",Toast.LENGTH_SHORT);
                    Intent next = new Intent(ctx, HomeActivity.class);
                    ctx.startActivity(next);
                    ((Activity)ctx).finish();
                }
            }, 800);
        } else if (result.equals("add angkot error")) {
            mProgressDialog.dismiss();
            alertDialog.setMessage("mungkin server sedang bermasalah");
            alertDialog.show();
        }
        else if (result.equals("edit angkot success")) {
            mProgressDialog.dismiss();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx,"edit angkot success",Toast.LENGTH_SHORT);
                    Intent next = new Intent(ctx, HomeActivity.class);
                    ctx.startActivity(next);
                    ((Activity)ctx).finish();
                }
            }, 800);
        } else if (result.equals("edit angkot error")) {
            mProgressDialog.dismiss();
            alertDialog.setMessage("mungkin server sedang bermasalah");
            alertDialog.show();
        }
        else if (result.equals("del angkot success")) {
            mProgressDialog.dismiss();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ctx,"edit angkot success",Toast.LENGTH_SHORT);
                    Intent next = new Intent(ctx, HomeActivity.class);
                    ctx.startActivity(next);
                    ((Activity)ctx).finish();
                }
            }, 800);
        } else if (result.equals("del angkot error")) {
            mProgressDialog.dismiss();
            alertDialog.setMessage("mungkin server sedang bermasalah");
            alertDialog.show();
        }
        else {
            mProgressDialog.dismiss();
            alertDialog.setMessage("Kesalahan tidak diketahui, Silahkan hubungi developer rafif:0812-8509-1879");
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
