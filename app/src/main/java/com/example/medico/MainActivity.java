package com.example.medico;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
Spinner sp_med,sp_tipo,sp_pre,sp_hora;
EditText et_peso ,et_dia,et_nota,et_talla,et_fc,et_fr,et_tc,et_ta,et_glucosa,et_SpO2,et_diagnostico;
    TextView tv_med,tv_tipo,tv_pre,tv_hora,tv_receta;
    Button btn_crear;
    float dosis,estandar,peso,presentacio;
    int horas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp_med = findViewById(R.id.sp_nom_med);
        sp_tipo = findViewById(R.id.sp_tipo_pas);
        sp_pre = findViewById(R.id.sp_pre_med);
        sp_hora = findViewById(R.id.sp_hora);

        et_peso = findViewById(R.id.txt_peso);
        et_dia = findViewById(R.id.txt_dias);
        et_nota = findViewById(R.id.txt_nota);
        et_talla = findViewById(R.id.txt_talla);
        et_fc = findViewById(R.id.txt_FC);
        et_fr = findViewById(R.id.txt_FR);
        et_tc = findViewById(R.id.txt_TC);
        et_ta = findViewById(R.id.txt_TA);
        et_glucosa = findViewById(R.id.txt_glucosa);
        et_SpO2 = findViewById(R.id.txt_SpO2);
        et_diagnostico = findViewById(R.id.txt_diag);

        tv_med = findViewById(R.id.tv_nom_med);
        tv_pre = findViewById(R.id.tv_pre_med);
        tv_tipo = findViewById(R.id.tv_tipo_pas);
        tv_hora = findViewById(R.id.tv_hora);
        tv_receta = findViewById(R.id.tv_Receta_creada);

        btn_crear=findViewById(R.id.btn_aceptar);

      String[] nombre = {"Nombre del medicamento","Paracetamol","Dextromertofano"};
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,nombre);
      sp_med.setAdapter(adapter);
        sp_med.setOnItemSelectedListener(this);

        final String[][] presentacion = {{"Presentacion del medicamento", "1000", "500"}};
        ArrayAdapter<String> adapterp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, presentacion[0]);
        sp_pre.setAdapter(adapterp);
        sp_pre.setOnItemSelectedListener(this);

        String[] tipo = {"Tipo de paciente","Pediatria","Adulto"};
        ArrayAdapter<String> adaptert = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,tipo);
        sp_tipo.setAdapter(adaptert);
        sp_tipo.setOnItemSelectedListener(this);

        String[] hora = {"Tomar cada","6","8","12","24"};
        ArrayAdapter<String> adapterh = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,hora);
        sp_hora.setAdapter(adapterh);
        sp_hora.setOnItemSelectedListener(this);

        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sp_med.getSelectedItem().toString().equals("Nombre del medicamento")&&!sp_pre.getSelectedItem().toString().equals("Presentacion del medicamento")&&!sp_tipo.getSelectedItem().toString().equals("Tipo de paciente")&&!sp_hora.getSelectedItem().toString().equals("Tomar cada")&&!et_dia.getText().toString().equals("")&&!et_peso.getText().toString().equals("")&&!et_nota.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Desea crear receta?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "Receta creada", Toast.LENGTH_SHORT).show();
                                    estandar = 0.15f;
                                    peso = Float.parseFloat(et_peso.getText().toString()) ;
                                    horas = Integer.parseInt(sp_hora.getSelectedItem().toString());
                                    presentacio = Float.parseFloat(sp_pre.getSelectedItem().toString())/1000 ;
                                    dosis = estandar *peso*horas*presentacio/24;
                                    Toast.makeText(getApplicationContext(), ""+dosis, Toast.LENGTH_SHORT).show();

                                    String receta;
                                    receta="Dr:(obtener de base de datos)\nMedico especializado (obtener de base de datos)\nCedula profesional(obtener de base de datos)\n"+
                                            "Datos del pasciente\nNombre:   Edad:\n"+
                                            "Sexo:   Fecha:\n"+
                                            "FC: "+et_fc.getText().toString()+  " FR: "+ et_fr.getText().toString()+" Tc: "+ et_tc.getText().toString()+" Peso: "+  et_peso.getText().toString()+" Talla: "+  et_talla.getText().toString()+" TA: "+et_ta.getText().toString()+"\n"+
                                            "Glucosa: "+et_glucosa.getText().toString()+" SpO2: "+et_SpO2.getText().toString()+" Alergias: \n"+
                                            "Dx: "+et_diagnostico.getText().toString()+"\n"+
                                            "Tratamiento:\n"+
                                            sp_med.getSelectedItem().toString()+" "+sp_pre.getSelectedItem().toString()+" mg via oral,"+dosis+" cada "+horas+"hrs por "+et_dia.getText().toString()+" dias\n"+
                                            "Notas:\n"+et_nota.getText().toString();

                                    tv_receta.setText(receta);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(getApplicationContext(), "Receta no creada", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alerta = builder.create();
                    alerta.setTitle("Alerta");
                    alerta.show();
                }else {
                    Toast.makeText(getApplicationContext(), "Debes llenar todos los datos", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        if(sp_med.isClickable()){
            tv_med.setText(""+sp_med.getSelectedItem().toString());
        }
        if(sp_pre.isClickable()){
            tv_pre.setText(""+sp_pre.getSelectedItem().toString());
        }
        if(sp_tipo.isClickable()){
            tv_tipo.setText(""+sp_tipo.getSelectedItem().toString());
        }
        if(sp_hora.isClickable()){
            tv_hora.setText(""+sp_hora.getSelectedItem().toString());
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}