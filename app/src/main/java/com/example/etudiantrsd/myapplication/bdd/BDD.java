package com.example.etudiantrsd.myapplication.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.etudiantrsd.myapplication.bdd.classes.Activite;
import com.example.etudiantrsd.myapplication.bdd.classes.Joueur;
import com.example.etudiantrsd.myapplication.bdd.classes.Sport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ib on 06/06/2017.
 */

public class BDD extends SQLiteOpenHelper {
    private  static  final  int DATABASE_VERSION=5;
    private  static  final  String DATABASE_NAME="bddsportif";

    private  static  final  String TABLE_activiter="sportpratiquer";
    private  static  final  String COLUMN_id="id";
    private  static  final  String COLUMN_codes="codesport";
    private  static  final  String COLUMN_codej="codejoueur";
    private  static  final  String TABLE_USER="joueur";
    private  static  final  String COLUMN_MATRICULE="matricule";
    private  static  final  String COLUMN_NAME="nom";
    private  static  final  String COLUMN_PRENOM="prenom";
    private static final	String COLUMN_MAIL = "mail";
    private static final	String COLUMN_dateNaissance = "dateNaissance";
    private static final	String COLUMN_LieuNaissance = "LieuNaissance";
    private static final	String COLUMN_tel = "tel";
    private  static  final  String COLUMN_codesport="codesport";
    private  static  final  String TABLE_Sport="sport";
    private  static  final  String COLUMN_libeller="libeller";
    private  static  final  String COLUMN_description="description";

    public BDD(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "   +TABLE_activiter +"(" +COLUMN_id + " INTEGER PRIMARY KEY   AUTOINCREMENT,"+ COLUMN_codes + " TEXT ," + COLUMN_codej +" TEXT" + ")";
        db.execSQL(CREATE_TABLE);
        String CREATE_USER_TABLE="CREATE TABLE " +TABLE_USER +"(" + COLUMN_MATRICULE + " TEXT PRIMARY KEY,"  + COLUMN_NAME + " TEXT,"+ COLUMN_PRENOM + " TEXT,"
                + COLUMN_dateNaissance +" TEXT," + COLUMN_LieuNaissance + " TEXT,"+ COLUMN_MAIL +" TEXT,"+COLUMN_tel+ ")";
        db.execSQL(CREATE_USER_TABLE);
        String CREATE_SPORT_TABLE="CREATE TABLE " +TABLE_Sport +"(" + COLUMN_codesport + " TEXT PRIMARY KEY," + COLUMN_libeller +" TEXT,"+COLUMN_description+" TEXT"+ ")";
        db.execSQL(CREATE_SPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_activiter );
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER );
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_Sport);
        onCreate(db);
    }
    public void addActiviter(Activite activite){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_codes,activite.getCodesport());
        values.put(COLUMN_codej,activite.getCodeJoueur());

        db.insert(TABLE_activiter,null,values);
        db.close();
    }
    public List<Activite> listActiver(){
        String sql = "select * from " + TABLE_activiter   ;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Activite> storeLiens = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                storeLiens.add(new Activite(Integer.valueOf(cursor.getString(0)), cursor.getString(1),cursor.getString(2)));

            }while(cursor.moveToNext());
        }
        cursor.close();
        return storeLiens;
    }

    public int UpdatActiviter(Activite activite){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_codes,activite.getCodesport());
        values.put(COLUMN_codej,activite.getCodeJoueur());

        return db.update(TABLE_activiter,values, COLUMN_id	+ "	= ?",new String[] { (String.valueOf(activite.getId()))});

    }
    public void DeleteActiviter(String code){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_activiter, COLUMN_id	+ "	= ?", new String[] { code});

    }

    public void addJoueur(Joueur joueur){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_MATRICULE,joueur.getMatricule());
        values.put(COLUMN_LieuNaissance,joueur.getLieuNaissance());
        values.put(COLUMN_dateNaissance, joueur.getDateNaissance());
        values.put(COLUMN_NAME,joueur.getNom());
        values.put(COLUMN_PRENOM,joueur.getPrenom());
        values.put(COLUMN_MAIL, joueur.getEmail());

        values.put(COLUMN_tel, joueur.getTel());

        db.insert(TABLE_USER,null,values);
        db.close();
    }
    public List<Joueur> listJoueur(){
        String sql = "select * from " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Joueur> storeUser = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{

                storeUser.add(new Joueur(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return storeUser;
    }

    public void deleteJoueur(String matricul){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USER, COLUMN_MATRICULE	+ "	= ?", new String[] { matricul});

    }
    public int  UpdateJoueur(Joueur joueur){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LieuNaissance,joueur.getLieuNaissance());
        values.put(COLUMN_dateNaissance, joueur.getDateNaissance());
        values.put(COLUMN_NAME,joueur.getNom());
        values.put(COLUMN_PRENOM,joueur.getPrenom());
        values.put(COLUMN_MAIL, joueur.getEmail());

        values.put(COLUMN_tel, joueur.getTel());
        return db.update(TABLE_USER,values, COLUMN_MATRICULE	+ "	= ?", new String[] { joueur.getMatricule()});

    }

    public void addsport(Sport sport){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_codesport,sport.getCode());
        values.put(COLUMN_libeller,sport.getNom());
        values.put(COLUMN_description,sport.getDescription());
        db.insert(TABLE_Sport,null,values);
        db.close();
    }
    public List<Sport> listSport(){
        String sql = "select * from " + TABLE_Sport ;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Sport> storeLang = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                storeLang.add(new Sport(cursor.getString(0), cursor.getString(1),cursor.getString(2)));

            }while(cursor.moveToNext());
        }
        db.close();
        return storeLang;
    }
    public Sport Infos(String codeS,String matricul){
        System.out.println(codeS+"  "+matricul);
        String sql = "select "+COLUMN_NAME+","+COLUMN_PRENOM+","+COLUMN_libeller +" from " + TABLE_Sport+","+TABLE_USER
                +" WHERE "+ COLUMN_MATRICULE +" ='"+ matricul+"' AND "+COLUMN_codesport+" ='"+codeS+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Sport sp=null ;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                 sp=(new Sport(cursor.getString(0), cursor.getString(1),cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        db.close();
        return sp;
    }

    public int UpdateSport(Sport sport){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_libeller,sport.getNom());
        values.put(COLUMN_description,sport.getDescription());
        return  db.update(TABLE_Sport,values, COLUMN_codesport	+ "	= ?", new String[] { (sport.getCode())});
    }
    public void DeleteSport()
    {   SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_Sport);
        db.close();
    }
    public void DeleteSport(String code){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_Sport, COLUMN_codesport	+ "	= ?", new String[] { code});

    }
}
