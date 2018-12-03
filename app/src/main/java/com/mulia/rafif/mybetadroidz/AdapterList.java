package com.mulia.rafif.mybetadroidz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private ArrayList<AngkotPojo> dataList;
    Context context;

    public AdapterList(ArrayList<AngkotPojo> dataList, Context ctx){
        this.dataList = dataList;
        this.context = ctx;
    }

    @NonNull
    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolder holder, final int position) {
        holder.kodeAngkot.setText("Kode Angkot : "+dataList.get(position).getKode_angkot());
        holder.ruteAngkot.setText("Rute Angkot : "+dataList.get(position).getRute_angkot());
        holder.namaSupir.setText("Nama Supir : "+dataList.get(position).getNama_supir());
        holder.namaJuragan.setText("Nama Juragan : "+dataList.get(position).getNama_juragan());
        holder.tanggalPembuatan.setText("Tanggal Pembuatan : "+dataList.get(position).getTanggal_pembuatan());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SingleActivity.class);
                i.putExtra(SingleActivity.EXTRA_KEY , dataList.get(position).getKode_angkot());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kodeAngkot, ruteAngkot, namaSupir, namaJuragan, tanggalPembuatan;
        private LinearLayout card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            kodeAngkot = itemView.findViewById(R.id.kode_angkot);
            ruteAngkot = itemView.findViewById(R.id.rute_angkot);
            namaJuragan = itemView.findViewById(R.id.nama_juragan);
            namaSupir = itemView.findViewById(R.id.nama_supir);
            tanggalPembuatan = itemView.findViewById(R.id.tanggal_pembuatan);
        }
    }
}
