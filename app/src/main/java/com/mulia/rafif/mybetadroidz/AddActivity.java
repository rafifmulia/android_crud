package com.mulia.rafif.mybetadroidz;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends Activity implements View.OnClickListener {

    private EditText edt_kodeAngkot, edt_ruteAngkot, edt_namaSupir, edt_namaJuragan, edt_tanggalPembuatan;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edt_kodeAngkot = findViewById(R.id.edt_kodeAngkot);
        edt_ruteAngkot = findViewById(R.id.edt_ruteAngkot);
        edt_namaSupir = findViewById(R.id.edt_namaSupir);
        edt_namaJuragan = findViewById(R.id.edt_namaJuragan);
        edt_tanggalPembuatan = findViewById(R.id.edt_tanggalPembuatan);

        SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateF.format(Calendar.getInstance().getTime());
        edt_tanggalPembuatan.setText(date);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                String kode_angkot = edt_kodeAngkot.getText().toString();
                String rute_angkot = edt_ruteAngkot.getText().toString();
                String nama_supir = edt_namaSupir.getText().toString();
                String nama_juragan = edt_namaJuragan.getText().toString();
                String tanggal_pembuatan = edt_tanggalPembuatan.getText().toString();

                boolean isEmptyFields = true;

                if (TextUtils.isEmpty(kode_angkot)) {
                    isEmptyFields = false;
                    edt_kodeAngkot.setError("kode angkot wajib diisi");
                }
                else if (TextUtils.isEmpty(rute_angkot)) {
                    isEmptyFields = false;
                    edt_ruteAngkot.setError("rute angkot wajib diisi");
                }
                else if (TextUtils.isEmpty(nama_supir)) {
                    isEmptyFields = false;
                    edt_namaSupir.setError("nama supir wajib diisi");
                }
                else if (TextUtils.isEmpty(nama_juragan)) {
                    isEmptyFields = false;
                    edt_namaJuragan.setError("nama juragan wajib diisi");
                }
                else if (TextUtils.isEmpty(tanggal_pembuatan)) {
                    isEmptyFields = false;
                    edt_tanggalPembuatan.setError("tanggal pembuatan wajib diisi");
                }

                if (isEmptyFields) {
                    String type = "addAngkot";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                    backgroundWorker.execute(type, kode_angkot, rute_angkot, nama_supir, nama_juragan, tanggal_pembuatan);
                }
                break;
        }
    }

}
