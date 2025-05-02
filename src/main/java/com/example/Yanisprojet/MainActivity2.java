package com.example.Yanisprojet;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Activité permettant à l'utilisateur de prendre un rendez-vous.
 * Cette activité gère l'affichage des professionnels disponibles, les heures,
 * ainsi que l'enregistrement et la gestion des rendez-vous.
 */

public class MainActivity2 extends AppCompatActivity {
    private Spinner spinnerDesPro;
    String curDate;
    private Spinner spinnerHeures;
    private CalendarView calendrier;
    private String[] Heure ={"8h", "9h", "10h", "11h", "13h", "14h", "15h", "16h", "17h", "18h" ,"19h","20h","21h","22h"};
    bd bd;
    List<String> ListeProf;
    private TextView texteListeTest;

    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise l'interface utilisateur et configure les composants tels que les Spinners
     * et le CalendarView.
     *
     * @param savedInstanceState état sauvegardé de l'activité (si disponible)
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        calendrier = findViewById(R.id.calendarView1);
        spinnerHeures= findViewById(R.id.spinnerHeure);
        spinnerDesPro = findViewById(R.id.spinnerProfessionnel);
        bd  = new bd(this);
         texteListeTest =  findViewById(R.id.textView50);

        // Configuration du CalendarView pour sélectionner une date
        calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                curDate = String.valueOf(i2) + "/" + String.valueOf(i1) + "/" + String.valueOf(i);
            }
            // Récupération des professionnels depuis la base de données
        });
        ListeProf = new ArrayList<>();
        ListeProf.add("");
        try {
            Cursor cursor = bd.getDonéesPro();

            if (cursor.moveToFirst()) {
                do {
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("NOM"));
                    String prenom = cursor.getString(cursor.getColumnIndexOrThrow("PRENOM"));
                    String type = cursor.getString(cursor.getColumnIndexOrThrow("SONTYPE"));

                    ListeProf.add(nom + " " + prenom + " " + type);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e)
        {
            ListeProf.add(e.getMessage());
        }
        // Configuration du Spinner pour les professionnels
        ArrayAdapter<String> Pro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,ListeProf);
        Pro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDesPro.setAdapter(Pro);

        spinnerDesPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Action à effectuer lorsqu'un professionnel est sélectionné
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> Heures = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,Heure);
        Heures.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHeures.setAdapter(Heures);

        spinnerHeures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Action à effectuer lorsqu'une heure est sélectionnée
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Action à effectuer lorsque rien n'est sélectionné
            }
        });


/**
 * Méthode appelée lorsque le bouton "Prendre RDV" est cliqué.
 * Enregistre un rendez-vous dans la base de données.
 *
 * @param view la vue qui a déclenché l'événement
 */

    }
    public void PrendreRDV1(View view){
        majListe();
        try {
        bd.insertRDV(spinnerDesPro.getSelectedItem().toString(),spinnerHeures.getSelectedItem().toString(),curDate);
            majListe();
        } catch (Exception e) {
            texteListeTest.setText("pb" + e.getMessage());

        }
    }
    /**
     * Méthode appelée lorsque le bouton "Accueil" est cliqué.
     * Redirige l'utilisateur vers l'activité principale (MainActivity).
     *
     * @param view la vue qui a déclenché l'événement
     */

    /**
     * Met à jour la liste des rendez-vous affichés dans l'interface.
     */

    public void majListe() {
        String texte = "";

        try {
            Cursor data = bd.getRDV();

            while (data.moveToNext()) {
                texte = texte + " " + String.valueOf(data.getString(1) + " " + data.getString(2));

            }
            texteListeTest.setText(texte);

        } catch (Exception e) {
            texteListeTest.setText(e.getMessage());
}
    }



}