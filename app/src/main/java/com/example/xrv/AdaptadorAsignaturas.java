package com.example.xrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorAsignaturas extends RecyclerView.Adapter<AdaptadorAsignaturas.ViewHolder> {


    private ArrayList<CardViewAsignaturas> mData;
    private LayoutInflater mInflater;
    private Context context;
    public AdaptadorAsignaturas(ArrayList<CardViewAsignaturas> itemlist, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mData=itemlist;

    }

    @NonNull
    @Override
    public AdaptadorAsignaturas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = mInflater.inflate(R.layout.cardview_asignaturas, parent, false);
        return new AdaptadorAsignaturas.ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorAsignaturas.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems ( ArrayList<CardViewAsignaturas> items){

        mData=items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView asAsignatura, asProfesor;

        ViewHolder (View itemView){
            super(itemView);
            asAsignatura = itemView.findViewById(R.id.cardAsAsig);
            asProfesor =itemView.findViewById(R.id.cardAsProfesor);
        }
        void bindData (final CardViewAsignaturas item){

            asAsignatura.setText(item.getAsAsignatura());
            asProfesor.setText(item.getAsProfesor());


        }
    }
}
