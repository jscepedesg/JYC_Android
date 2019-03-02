package sebastian.ing.jyc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import sebastian.ing.jyc.Estructuras.Cliente_provicional;
import sebastian.ing.jyc.R;
import sebastian.ing.jyc.Utilidades.Utilidades;

/**
 * Created by Usuario on 1/03/2019.
 */

public class Cliente_lista_clientes extends AppCompatActivity
{
    private ListView listView;
    private ArrayList<String> listaInformacion;
    private ArrayList<Cliente_provicional> listaCliente;
    private ConexionSQLiteHelper conn;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_lista_clientes);

        conn = new ConexionSQLiteHelper(this, Utilidades.DATABASE_NAME,null,Utilidades.DATABASE_VERSION);
        listView = (ListView) findViewById(R.id.listView_Clientes);

        setConsultarListaCLientes();
        ArrayAdapter adaptador= new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listView.setAdapter(adaptador);

    }

    private void setConsultarListaCLientes()
    {
        SQLiteDatabase db = conn.getReadableDatabase();
        listaCliente = new ArrayList<Cliente_provicional>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_CLIENTE_PROVICIONAL,null);

        while (cursor.moveToNext())
        {
            listaCliente.add(new Cliente_provicional(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7)));

            Log.d("Base de datos Cliente",cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getInt(2)+" "+cursor.getString(3)
                    +" "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)+" "+cursor.getInt(7));
        }

        setObtenerLista();


    }

    private void setObtenerLista()
    {
        listaInformacion = new ArrayList<String>();

        for (int i=0;i<listaCliente.size();i++)
        {
            listaInformacion.add(listaCliente.get(i).getNom_r()+" - "+listaCliente.get(i).getNom_c()+" "+listaCliente.get(i).getApel_c());
        }
    }
}
