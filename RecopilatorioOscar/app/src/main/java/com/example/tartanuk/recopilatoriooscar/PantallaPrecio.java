package com.example.tartanuk.recopilatoriooscar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tartanuk on 20/1/16.
 */
public class PantallaPrecio extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_precio);

        final TextView textView6 = (TextView) findViewById(R.id.textView6);
        final TextView textView5 = (TextView) findViewById(R.id.textView5);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);

        Bundle miBundleRecoger = getIntent().getExtras();
        textView5.setText(miBundleRecoger.getString("ORDENADOR"));
        textView6.setText(miBundleRecoger.getString("PRECIOTOTAL"));

        imageView2.setBackgroundDrawable(getResources().getDrawable(miBundleRecoger.getInt("IMAGEN")));

    }

}
