package com.example.Yanisprojet;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
/**
 * Activité permettant de rechercher des professionnels en fonction du code postal ou de la ville.
 * Affiche une liste des professionnels trouvés dans un Spinner.
 */
public class RechercheProfe extends AppCompatActivity {
bd bd;
private EditText Cp;
    private EditText Ville;
    List<String> donnée;

private Spinner SpinnerdesPro;
    /**
     * Méthode appelée lors de la création de l'activité.
     * Initialise les éléments de l'interface et configure le Spinner.
     * @param savedInstanceState l'état sauvegardé de l'application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recherche_profe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialisation des éléments de l'interface
        bd = new bd(this);
        Cp = findViewById(R.id.editTextRechercheCP);
        Ville = findViewById(R.id.editTextRechercheVille);
        SpinnerdesPro = findViewById(R.id.spinnerListeP);
        // Liste des professionnels trouvés
        donnée = new ArrayList<>();
        donnée.add("");
        // Configuration de l'adaptateur pour le Spinner
        ArrayAdapter<String> Pro = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, donnée);
        Pro.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerdesPro.setAdapter(Pro);

        SpinnerdesPro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton de recherche.
     * Recherche les professionnels en fonction du code postal et de la ville saisis,
     * puis met à jour le Spinner avec les résultats.
     * @param view la vue associée à l'événement de clic
     */
    public void clicRechercherProf(View view) {
donnée.clear();

        try {
            Cursor cursor = bd.getDonnéesPro(Cp.getText().toString(),Ville.getText().toString());
            if (cursor.moveToFirst()) {
                do {
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("NOM"));
                    String prenom = cursor.getString(cursor.getColumnIndexOrThrow("PRENOM"));
                    String type = cursor.getString(cursor.getColumnIndexOrThrow("SONTYPE"));

                    donnée.add(nom + " " + prenom + " " + type);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        catch (Exception e)
        {
            donnée.add("pas de docteur dans ce secteur");


        }

/**
 * Méthode appelée lorsque l'utilisateur clique sur le bouton d'accueil.
 * Redirige l'utilisateur vers l'écran principal (MainActivity).
 * @param view la vue associée à l'événement de clic
 */
        }

}
