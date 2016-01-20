package com.example.tartanuk.recopilatoriooscar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public Spinner spinner;
    double total, precioOrdenador;
    String ordenador;
    Integer imagen;

    private ordenadores[] ordenadoresarray = new ordenadores[]{
        new ordenadores("MappleBook Air", 900, R.drawable.air2),
        new ordenadores("MappleBook Pro", 1100, R.drawable.pro),
        new ordenadores("iMapple", 2300, R.drawable.imac),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //variables textView y imageView (titulo)
        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);

        //variables RadioGroup
        final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);

        //variables checkBox
        final CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        final CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);

        //variables textView y editText (cantidad)
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        final EditText editText1 = (EditText) findViewById(R.id.editText1);

        //variable botton para ver total del pedido
        final Button button1 = (Button) findViewById(R.id.button1);


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

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);*/

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

        //Click del boton
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                DecimalFormat formateador = new DecimalFormat("##.##");
                String precioTotal;
                double total = 0;
                double cantidad = Double.parseDouble(editText1.getText().toString());

                String seleccionado = spinner.getSelectedItem().toString();

                //Condicion spinner seleccionado
                /*if (seleccionado.equals("MappleBookAir")) {
                    total = total + 900;
                }
                if (seleccionado.equals("MappleBookPro")) {
                    total = total + 1100;
                }
                if (seleccionado.equals("iMapple")) {
                    total = total + 2300;
                }*/

                //Condición radioButton
                if (radioGroup1.getCheckedRadioButtonId() == R.id.radioButton1) {
                    total = total;
                }
                if (radioGroup1.getCheckedRadioButtonId() == R.id.radioButton2) {
                    total = total + 50;
                }
                if (radioGroup1.getCheckedRadioButtonId() == R.id.radioButton3) {
                    total = total + 70;
                }

                //Condición checkbox seleccionado
                if (checkBox1.isChecked()) {
                    total = total + 65;
                }
                if (checkBox2.isChecked()) {
                    total = total + 45;
                }
                if (checkBox3.isChecked()) {
                    total = total + 55;
                }

                //Precio segun la cantidad
                total = total + precioOrdenador;
                total = total * cantidad;


                //Mostrar precio total
                precioTotal = (formateador.format(total));
                //textView4.setText("El total son "+precioTotal+" euros.");


                //Segunda Pantalla
                Intent miIntent = new Intent(MainActivity.this, PantallaPrecio.class);
                Bundle miBundle = new Bundle();
                miBundle.putString("ORDENADOR", ordenador);
                miBundle.putString("PRECIOTOTAL", precioTotal);
                miBundle.putInt("IMAGEN", imagen);
                miIntent.putExtras(miBundle);
                startActivity(miIntent);


            }

        });


    }
}
