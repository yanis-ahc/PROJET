package com.example.Yanisprojet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.Cursor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EnregistrerProfess extends AppCompatActivity {

    private EditText nomInput;
    private EditText prenomInput;
    private EditText TypeInput;
    private EditText AdresseInput;
    private EditText VilleInput;
    private EditText CPInput;
    private EditText MailInput;
    private EditText TelephoneInput;
    private TextView texteListe;
    bd bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enregistrer_profess);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bd = new bd(this);

        nomInput = (EditText) findViewById(R.id.editTextNom);
        prenomInput = (EditText) findViewById(R.id.editTextPrenom);
        TypeInput = (EditText) findViewById(R.id.editTextType);
        AdresseInput = (EditText) findViewById(R.id.editTextAdresse);
        VilleInput = (EditText) findViewById(R.id.editTextVille);
        CPInput = (EditText) findViewById(R.id.editTextCP);
        MailInput = (EditText) findViewById(R.id.editTextEmail);
        TelephoneInput = (EditText) findViewById(R.id.editTextTel);
        texteListe= findViewById(R.id.Liste);




    }

    public void clicEnregistrer(View view){

        try {
            texteListe.setText("ok");
bd.insertDataPro(nomInput.getText().toString(),prenomInput.getText().toString(),
        TypeInput.getText().toString(),AdresseInput.getText().toString(),VilleInput.getText().toString(),CPInput.getText().toString(),MailInput.getText().toString(), TelephoneInput.getText().toString());
        majListe();

        } catch (Exception e) {
            texteListe.setText("pb" + e.getMessage());

        }
    }
    public void majListe() {
        String texte = "";

        try {
            Cursor data = bd.getDonéesPro();

            while (data.moveToNext()) {
                texte = texte + " " + String.valueOf(data.getString(1) + " " + data.getString(2));

            }
            texteListe.setText(texte);

        } catch (Exception e) {
            texteListe.setText(e.getMessage());

        }
    }


}