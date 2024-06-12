package com.example.xrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorNotas extends RecyclerView.Adapter<AdaptadorNotas.ViewHolder> {

    private ArrayList<CardViewNotas> mData = new ArrayList<>();
    private LayoutInflater mInflater = null;
    private Context context;

    public AdaptadorNotas(ArrayList<CardViewNotas> itemlist, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = itemlist;
    }

    @Override
    public int getItemCount(){
        return  mData.size();
    }
    @NonNull
    @Override
    public AdaptadorNotas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.cardview_notas, parent, false);
        return new AdaptadorNotas.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorNotas.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems ( ArrayList<CardViewNotas> items){

        mData=items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView ntAsignatura, nota;

        ViewHolder (View itemView){
            super(itemView);
            ntAsignatura = itemView.findViewById(R.id.cardNtAsig);
            nota =itemView.findViewById(R.id.cardNota);
        }
        void bindData (final CardViewNotas item){

            ntAsignatura.setText(item.getNtAsignatura());
            nota.setText(item.getNtNotas());


        }
    }
}