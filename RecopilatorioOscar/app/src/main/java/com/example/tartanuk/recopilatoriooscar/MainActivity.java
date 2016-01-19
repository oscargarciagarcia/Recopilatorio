package com.example.tartanuk.recopilatoriooscar;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    public Spinner spinner;
    double total, precioOrdenador;
    String ordenador;
    Integer imagen;

    private ordenadores[] ordenadoresarray = new ordenadores[]{
        new ordenadores("MappleBook Air", 900, R.drawable.air),
        new ordenadores("MappleBook Pro", 1100, R.drawable.pro),
        new ordenadores("iMapple", 2300, R.drawable.imac),
    };

    class AdaptadorOrdenadores extends ArrayAdapter<ordenadores> {

        public Activity AdaptadorOrdenadores;

        public AdaptadorOrdenadores(Activity Adaptadorordenadores) {
            super(Adaptadorordenadores, R.layout.spinner_ordenadores, ordenadoresarray);
            this.AdaptadorOrdenadores = Adaptadorordenadores;
        }
        public View getDropDownView (int position, View convertView, ViewGroup parent) {
            View vistaDesplegada = getView(position, convertView, parent);
            return vistaDesplegada;
        }
        //ADAPTACION PARA EL SPINNER
        public  View getView (int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = AdaptadorOrdenadores.getLayoutInflater();
            View item=inflater.inflate(R.layout.spinner_ordenadores, null);

            //VARIABLES JAVA ASIGNADAS AL XML (SIPNNER)
            TextView textView1 = (TextView) item.findViewById(R.id.textView1);
            TextView textView2 = (TextView) item.findViewById(R.id.textView2);
            ImageView imageView1 = (ImageView) item.findViewById(R.id.imageView1);

            textView1.setText(ordenadoresarray[position].getTipo());
            textView2.setText(String.valueOf(ordenadoresarray[position].getPrecio()));
            imageView1.setImageResource(ordenadoresarray[position].getImagen()); //EL ERROR ES POR QUE ANTES DE UNA VERSION DE LOLIPORP SE HACICA DE OTRA FORMA PE

            return item;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner1);
        AdaptadorOrdenadores adaptador = new AdaptadorOrdenadores(this);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView arg0, View arg1, int position, long id) {

                ordenador = ordenadoresarray[position].getTipo();
                precioOrdenador = ordenadoresarray[position].getPrecio();
                imagen = ordenadoresarray[position].getImagen();
                //String mensaje = "Ha seleccionado: " + bebidas[position].getNombre();
                //showToast(mensaje);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ordenador = "No ha seleccionado ningun ordenador";
            }
        });

                //Variables textView e Imagen del Titulo (primer layout)
        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);

        //variables RadioGroup (segundo layout)
        final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
    }
}
