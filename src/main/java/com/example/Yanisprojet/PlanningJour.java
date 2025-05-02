package com.example.Yanisprojet;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
/**
 * Activité permettant d'afficher le planning des rendez-vous pour une journée donnée.
 * L'utilisateur peut sélectionner une date à l'aide d'un CalendarView et consulter
 * les rendez-vous correspondants dans un Spinner.
 */
public class PlanningJour extends AppCompatActivity {
    bd bd;
    List<String> listeduPlanning;

    private Spinner SpinnerPlanning;
    private CalendarView Calendar;
    String curDate;
    @SuppressLint("MissingInflatedId")
    @Override
    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise les éléments de l'interface utilisateur et configure les événements associés.
     *
     * @param savedInstanceState état sauvegardé de l'application
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_planning_jour);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bd = new bd(this);
        SpinnerPlanning = findViewById(R.id.spinnerPlanning);
        listeduPlanning = new ArrayList<>();
        Calendar = findViewById(R.id.calendarView3);


        Calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                curDate = String.valueOf(i2) + "/" + String.valueOf(i1) + "/" + String.valueOf(i);
            }
        });

        listeduPlanning = new ArrayList<>();
        listeduPlanning.add("");
        try {
            Cursor cursor = bd.getPlanning(curDate);

            if (cursor.moveToFirst()) {
                do {
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("NOMP"));
                    String prenom = cursor.getString(cursor.getColumnIndexOrThrow("PRENOM"));


                    listeduPlanning.add(nom + " " + prenom );
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            listeduPlanning.add(e.getMessage());
        }
        ArrayAdapter<String> Pro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeduPlanning);
        Pro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerPlanning.setAdapter(Pro);
        SpinnerPlanning.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    /**
     * Méthode appelée lorsqu'on clique sur le bouton d'accueil.
     * Redirige vers l'écran principal.
     *
     * @param view vue associée au bouton
     */

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Afficher Planning".
     * Met à jour la liste des rendez-vous pour la date sélectionnée.
     *
     * @param view vue associée au bouton
     */
    public void clicAfficherPlanning(View view) {
        listeduPlanning.clear();


        try {
            Cursor cursor = bd.getPlanning(curDate);
            if (cursor.moveToFirst()) {
                do {
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("NOMP"));
                    String date = cursor.getString(cursor.getColumnIndexOrThrow("DATER"));
                    String heure = cursor.getString(cursor.getColumnIndexOrThrow("HEURE"));

                    listeduPlanning.add(nom + " " + nom + " " + date + " " + heure);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {


        }
    }
}
