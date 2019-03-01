package sebastian.ing.jyc;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import sebastian.ing.jyc.Estructuras.Producto;
import sebastian.ing.jyc.Utilidades.Utilidades;

/**
 * Created by Usuario on 12/02/2019.
 */

public class Pedido extends AppCompatActivity
{
    private Spinner combodias;
    private Spinner combocliente;
    private Spinner comboproducto;
    private TextView fecha;
    private ArrayList<String> comboProductos;
    private ArrayList<Producto> productoArralist;
    private ConexionSQLiteHelper  conn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);

        conn = new ConexionSQLiteHelper(this, Utilidades.DATABASE_NAME,null,Utilidades.DATABASE_VERSION);


        combodias = (Spinner) findViewById(R.id.spinner2);
        combocliente = (Spinner) findViewById(R.id.spinner3);
        comboproducto = (Spinner) findViewById(R.id.spinner4);
        fecha = (TextView) findViewById(R.id.textFecha);
        setConsultarLitasProductos();
        ArrayAdapter<CharSequence> adaptador_prductos = new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,comboProductos);
        comboproducto.setAdapter(adaptador_prductos);
        setFecha();



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo_dias,android.R.layout.simple_spinner_item);
        combodias.setAdapter(adapter);
    }

    private void setConsultarLitasProductos()
    {
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto producto = null;
        productoArralist = new ArrayList<Producto>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_PRODUCTO,null);

        while (cursor.moveToNext())
        {
            producto= new Producto();
            producto.setCod_p(cursor.getInt(0));
            producto.setNom_p(cursor.getString(1));
            producto.setPre_p(cursor.getInt(2));
            producto.setLinea(cursor.getString(3));
            producto.setCasa(cursor.getString(4));

            Log.d("Base de datos producto",cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getInt(2)+" "+cursor.getString(3)
            +" "+cursor.getString(4));
            productoArralist.add(producto);
        }

        setOptenerLista();

    }

    private void setOptenerLista()
    {
        comboProductos = new ArrayList<String>();
        comboProductos.add("Seleccione");

        for(int i=0;i<productoArralist.size();i++)
        {
           comboProductos.add(productoArralist.get(i).getCod_p()+" - "+productoArralist.get(i).getNom_p());
        }
    }

    public void setFecha()
    {
        Date fecha1 = new Date();
        CharSequence s = DateFormat.format("MMMM d, yyyy ", fecha1.getTime());
        fecha.setText(s);
    }
}
