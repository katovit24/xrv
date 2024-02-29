package com.example.xrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AdaptadorHorario extends RecyclerView.Adapter<AdaptadorHorario.ViewHolder> {

    LayoutInflater mInflater;
    ArrayList<RecyclerHorario> mData;
    Context context;

    public AdaptadorHorario(ArrayList<RecyclerHorario> itemlist, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mData=itemlist;
    }

    @NonNull
    @Override
    public AdaptadorHorario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = mInflater.inflate(R.layout.recycler_horario, parent, false);
        return new AdaptadorHorario.ViewHolder(vista);
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorHorario.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems ( ArrayList<RecyclerHorario> items){
        mData=items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView hrAsignatura, hrHorario;

        ViewHolder (View itemView){
            super(itemView);
            hrAsignatura = itemView.findViewById(R.id.hrAsignatura);
            hrHorario =itemView.findViewById(R.id.hrHorario);
        }
        void bindData (final RecyclerHorario item){

            hrAsignatura.setText(item.getHrAsignatura());
            hrHorario.setText(item.getHrHorario());
        }
    }


}
