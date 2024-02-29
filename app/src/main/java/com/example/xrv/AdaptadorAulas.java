package com.example.xrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorAulas extends RecyclerView.Adapter<AdaptadorAulas.ViewHolder> {

    private ArrayList<CardViewAulas> mData;
    private LayoutInflater mInflater;
    private Context context;


    public AdaptadorAulas(ArrayList<CardViewAulas> itemlist, Context context){

        this.mInflater=LayoutInflater.from(context);
        this.mData= itemlist;
    }

    @Override
    public int getItemCount(){

        return mData.size();
    }

    @Override
    public AdaptadorAulas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.cardview_aulas, parent, false);
        return new AdaptadorAulas.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorAulas.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));

    }
    public void setItems ( ArrayList<CardViewAulas> items){

        mData=items;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView asignatura, aula;

        ViewHolder (View itemView){
            super(itemView);
            asignatura = itemView.findViewById(R.id.cardAsig);
            aula =itemView.findViewById(R.id.cardAula);
        }
        void bindData (final CardViewAulas item){

            asignatura.setText(item.getAsignatura());
            aula.setText(item.getAula());
            //asignatura.setText(asignatura.getText());
            //aula.setText(aula.getText());

        }
    }

}
