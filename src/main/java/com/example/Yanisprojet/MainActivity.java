package com.example.Yanisprojet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.content.Intent;

/**
 * Activité principale de l'application.
 * Cette classe gère l'interface utilisateur de l'écran principal
 * et les redirections vers d'autres activités.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Méthode appelée lors de la création de l'activité.
     * Configure l'interface utilisateur et gère les marges pour s'adapter aux barres système.
     *
     * @param savedInstanceState état sauvegardé de l'activité (si disponible)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

/**
 * Méthode appelée lorsque le bouton "Enregistrer" est cliqué.
 * Redirige l'utilisateur vers l'activité EnregistrerProfess.
 *
 * @param view la vue qui a déclenché l'événement
 */
    }
    public void clicEnregistrer1(View view){
// Redirection vers EnregistrerProfess
        Intent intent = new Intent(this, EnregistrerProfess.class);
        startActivity(intent);
        /**
         * Méthode appelée lorsque le bouton "Prendre RDV" est cliqué.
         * Redirige l'utilisateur vers l'activité MainActivity2.
         *
         * @param view la vue qui a déclenché l'événement
         */
    }
    public void clicPrendreRdv(View view){
        // Redirection vers MainActivity2 (PrendreRdvActivity)
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    /**
     * Méthode appelée lorsque le bouton "Planning" est cliqué.
     * Redirige l'utilisateur vers l'activité PlanningJour.
     *
     * @param view la vue qui a déclenché l'événement
     */
    public void Planning(View view){
// Redirection vers PlanningJour
        Intent intent = new Intent(this, PlanningJour.class);
        startActivity(intent);
    }
    /**
     * Méthode appelée lorsque le bouton "Recherche" est cliqué.
     * Redirige l'utilisateur vers l'activité RechercheProfe.
     *
     * @param view la vue qui a déclenché l'événement
     */
    public void clicRecherche(View view){
// Redirection vers RechercheProfe
        Intent intent = new Intent(this, RechercheProfe.class);
        startActivity(intent);
    }
}