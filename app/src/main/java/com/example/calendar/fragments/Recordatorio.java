package com.example.calendar.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calendar.ConexionSQLiteHelper;
import com.example.calendar.R;
import com.example.calendar.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Recordatorio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Recordatorio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText edtTitulo, edtDescripcion, edtDia, edtHora, edtLocalizacion;
    private Button aceptar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Recordatorio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Recordatorio.
     */
    // TODO: Rename and change types and number of parameters
    public static Recordatorio newInstance(String param1, String param2) {
        Recordatorio fragment = new Recordatorio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_recordatorio, container, false);

        edtTitulo = vista.findViewById(R.id.edtTitulo);
        edtDescripcion = vista.findViewById(R.id.edtDescripcion);
        edtDia = vista.findViewById(R.id.edtDia);
        edtHora = vista.findViewById(R.id.edtHora);
        aceptar = vista.findViewById(R.id.btnAceptar);
        edtLocalizacion = vista.findViewById(R.id.edtLocalizacion);


        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarEvento();
            }
        });



        return vista;
    }

    private void registrarEvento(){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext(),"bd_usuarios",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();

        if (!edtDia.getText().toString().equals("") ) {
        try {


            String insert ="insert into "+ Utilidades.TABLA_EVENTOS+
                    " ( "+Utilidades.CAMPO_TITULO+","+ Utilidades.CAMPO_DESCRIPCION+","+Utilidades.CAMPO_FECHA+","+Utilidades.CAMPO_HORA+","+Utilidades.CAMPO_LOCALIZACION+","+Utilidades.CAMPO_TIPO+
                    ") values ('"+edtTitulo.getText().toString()+"' ,'"+edtDescripcion.getText().toString()+"', '"+edtDia.getText().toString()+"', '"+edtHora.getText().toString()+"', '"+edtLocalizacion.getText().toString()+"' ,'"+"Recordatorio"+"' )";
            db.execSQL(insert);
            Toast.makeText(getContext(),R.string.eventoAgregado,Toast.LENGTH_LONG).show();
            limpiar();
            db.close();
        }catch (Exception e){
            Toast.makeText(getContext(),R.string.ErrorCampoIncorrecto,Toast.LENGTH_LONG).show();
            limpiar();
        }

    }else{
        Toast.makeText(getContext(), R.string.rellenarDia, Toast.LENGTH_LONG).show();
    }

    }
    private void limpiar() {

        edtDia.setText(null);
        edtDescripcion.setText(null);
        edtTitulo.setText(null);
        edtHora.setText(null);
        edtLocalizacion.setText(null);
    }
}