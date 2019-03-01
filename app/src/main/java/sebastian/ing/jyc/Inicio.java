package sebastian.ing.jyc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Usuario on 3/02/2019.
 */

public class Inicio extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
    }


    public void setPedido(View view)
    {
        Intent i = new Intent(this, Pedido.class);
        startActivity(i);
    }

    public void setEstadistica(View view)
    {
        Intent i = new Intent(this, Estadistica.class);
        startActivity(i);
    }

    public void setFacturar(View view)
    {
        Intent i = new Intent(this, Facturar.class);
        startActivity(i);
    }

    public void setCliente(View view)
    {
        Intent i = new Intent(this, Cliente_Int.class);
        startActivity(i);
    }

    public void alerta(String cadena, int num)
    {
        //se prepara la alerta creando nueva instancia
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        //seleccionamos la cadena a mostrar
        dialogBuilder.setMessage(cadena);
        //elegimo un titulo y configuramos para que se pueda quitar
        if (num == 1) {
            dialogBuilder.setCancelable(true).setTitle("¡Atención!");
        } else if (num == 2) {
            dialogBuilder.setCancelable(true).setTitle("Correcto");
        }

        //mostramos el dialogBuilder
        dialogBuilder.create().show();

    }

}
