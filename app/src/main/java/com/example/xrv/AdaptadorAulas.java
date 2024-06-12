package com.example.xrv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Clase que permite rellenar el reciclerview desde un Arraylist
public class AdaptadorAulas extends RecyclerView.Adapter<AdaptadorAulas.ViewHolder> {

    private ArrayList<CardViewAulas> mData;
    private LayoutInflater mInflater;
    private Context context;

    //Método constructor
    public AdaptadorAulas(ArrayList<CardViewAulas> itemlist, Context context){
        this.mInflater=LayoutInflater.from(context);
        this.mData= itemlist;
    }

    //Método que ofrece el tamaño del Arraylist
    @Override
    public int getItemCount(){
        return mData.size();
    }
    //Método que infla la vista
    @NonNull
    @Override
    public AdaptadorAulas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.cardview_aulas, parent, false);
        return new AdaptadorAulas.ViewHolder(view);
    }

    // Método que enlaza los datos en la posición correspondiente
    @Override
    public void onBindViewHolder(final AdaptadorAulas.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }
    //Método que permite actualizar el Arraylist con nuevos elementos.
    public void setItems ( ArrayList<CardViewAulas> items){
        mData=items;
    }

    // Clase que da valores al CardViewAulas
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView asignatura, aula;

        // Metodo que da la referencia de la vista al elemento
        ViewHolder (View itemView){
            super(itemView);
            asignatura = itemView.findViewById(R.id.cardAsig);
            aula =itemView.findViewById(R.id.cardAula);
        }
        //Método que asigna un valor a la vista
        void bindData (final CardViewAulas item){

            asignatura.setText(item.getAsignatura());
            aula.setText(item.getAula());
        }
    }

}
