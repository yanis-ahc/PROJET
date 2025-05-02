package com.example.Yanisprojet;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe représentant la base de données SQLite utilisée dans l'application.
 * Cette classe gère les tables "PROFESSIONNEL" et "RDV", permettant de stocker
 * les informations sur les professionnels et les rendez-vous.
 */

public class bd extends SQLiteOpenHelper {
// Constantes pour les noms de la base de données et des tables

    public static final String DATABASE_NAME = "Visiteur";
    public static final String TablePro = "PROFESSIONNEL";
    public static final String idPro = "ID";
    public static final String nom = "NOM";
    public static final String prenom = "PRENOM";
    public static final String type = "SONTYPE";
    public static final String adresse = "ADRESSE";
    public static final String ville = "VILLE";
    public static final String CodeP = "CP";
    public static final String mail = "MAIL";
    public static final String Telephone= "TEL";

    public static final String TableRDV = "RDV";
    public static final String idRdv = "ID";
    public static final String nomPro = "NOMP";
    public static final String HeureRDV = "HEURE";
    public static final String DateR = "DATER";
    public static final String IDP = "IDP";

    /**
     * Constructeur de la classe bd.
     * @param context le contexte de l'application
     */
    public bd(Context context) {super(context, DATABASE_NAME, null, 1);
    }
    /**
     * Méthode appelée lors de la création initiale de la base de données.
     * Crée les tables "PROFESSIONNEL" et "RDV".
     * @param db l'instance de la base de données SQLite
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table "+ TablePro + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOM TEXT,PRENOM TEXT , SONTYPE TEXT, ADRESSE TEXT, VILLE TEXT , CP TEXT, MAIL TEXT, TEL TEXT )");
        db.execSQL("CREATE table "+ TableRDV + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOMP TEXT, HEURE TEXT , DATER TEXT,  IDP INTEGER,  FOREIGN KEY(IDP) REFERENCES PROFESSIONNEL(ID))");

    }
    /**
     * Méthode appelée lors de la mise à jour de la base de données (changement de version).
     * Supprime les anciennes tables et les recrée.
     * @param db l'instance de la base de données SQLite
     * @param oldVersion ancienne version de la base de données
     * @param newVersion nouvelle version de la base de données
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TablePro);
        db.execSQL("DROP TABLE IF EXISTS " + TableRDV);
        onCreate(db);

    }
     /**
     * Supprime toutes les tables existantes dans la base de données.
     */


    /**
     * Insère un professionnel dans la table "PROFESSIONNEL".
     * @param lenom le nom du professionnel
     * @param leprenom le prénom du professionnel
     * @param letype le type de professionnel (spécialité)
     * @param ladresse l'adresse du professionnel
     * @param laville la ville du professionnel
     * @param LeCp le code postal du professionnel
     * @param lemail l'adresse email du professionnel
     * @param leTel le numéro de téléphone du professionnel
     */

    public  void insertDataPro(String lenom, String leprenom, String letype , String ladresse , String laville , String LeCp,String lemail, String leTel) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nom, lenom);
        contentValues.put(prenom, leprenom);
        contentValues.put(type, letype);
        contentValues.put(adresse, ladresse);
        contentValues.put(ville, laville);
        contentValues.put(CodeP, LeCp);
        contentValues.put(mail, lemail);
        contentValues.put(Telephone, leTel);
        bd.insert(TablePro, null, contentValues);
        bd.close();
    }
    /**
     * Insère un rendez-vous dans la table "RDV".
     * @param lenomPro le nom du Professionnel
     * @param lheure l'heure du rendez-vous
     * @param ladate la date du rendez-vous
     */
    public  void insertRDV(String lenomPro, String lheure, String ladate ) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(nomPro, lenomPro);
        contentValues.put(HeureRDV, lheure);
        contentValues.put(DateR, ladate);
        bd.insert(TableRDV, null, contentValues);
        bd.close();
    }
    /**
     * Récupère tous les professionnels dans la table "PROFESSIONNEL".
     * @return un curseur contenant les données des professionnels
     */
    public Cursor getDonéesPro(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor result= bd.rawQuery("SELECT * FROM " +TablePro,null);
        return result;
    }
    /**
     * Récupère tous les rendez-vous dans la table "RDV".
     * @return un curseur contenant les données des rendez-vous
     */
    public Cursor getRDV(){
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor result= bd.rawQuery("SELECT NOMP , HEURE,  DATER FROM " + TableRDV,null);
        return result;
    }
    /**
     * Récupère les rendez-vous pour une date spécifique.
     * @param Date la date des rendez-vous à récupérer
     * @return un curseur contenant les rendez-vous pour la date donnée
     */


    public Cursor getPlanning(String Date)
    {
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor result;
        result = bd.rawQuery("SELECT * FROM " + TableRDV + " WHERE  DATER   LIKE  '" + Date + "'" , null);
        return result;
    }
    /**
     * Récupère les informations des professionnels correspondant à un code postal ou une ville.
     * @param cp10 le code postal recherché
     * @param ville la ville recherchée
     * @return un curseur contenant les professionnels correspondants
     */

    public Cursor getDonnéesPro(String cp10 , String ville)
    {
        SQLiteDatabase bd = this.getReadableDatabase();
        Cursor result;
        result = bd.rawQuery("SELECT * FROM " +TablePro +  "  WHERE " + CodeP + "  LIKE  '" + cp10 + "' OR VILLE LIKE '" + ville + "'", null);


        return result;

    }



}
