package com.example.matinal.fragment10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Fragmento1 extends ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ENT_LISTA){
            @Override
            public void onEntrada(Object entrada, View view){
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textotitulo);
                texto_superior_entrada.setText(((Contenido.Lista_entrada)entrada).textoEncima);
                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagenlista);
                imagen_entrada.setImageResource(((Contenido.Lista_entrada)entrada).idImagen);
            }
        });
    }
    public interface Callbacks{ public void onEntradaSeleccionada(String id);}

    private static Callbacks CallbacksVacios = new Callbacks() {
        @Override
        public void onEntradaSeleccionada(String id) {
            Intent detalleIntent = new Intent(this, Fragmento2.class);
            startActivity(detalleIntent);
        }
    };

    public Fragmento1() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        CallbacksVacios = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        CallbacksVacios = CallbacksVacios;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        CallbacksVacios.onEntradaSeleccionada(Contenido.ENT_LISTA.get(position).id);
    }
}
