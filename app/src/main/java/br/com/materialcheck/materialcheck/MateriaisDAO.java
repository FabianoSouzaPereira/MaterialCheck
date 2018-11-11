package br.com.materialcheck.materialcheck;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;


public class MateriaisDAO {
    private SQLiteDatabase database;
    private String[] columns = {CustomSQLiteOpenHelper.COLUMN_ID,
            CustomSQLiteOpenHelper.COLUMN_DESCRICAO,
            CustomSQLiteOpenHelper.COLUMN_DESCRICAO,
            CustomSQLiteOpenHelper.COLUMN_MODELO,
            CustomSQLiteOpenHelper.COLUMN_DESCRICAO,
            CustomSQLiteOpenHelper.COLUMN_QUANTIDADE,
            CustomSQLiteOpenHelper.COLUMN_SN,
            CustomSQLiteOpenHelper.COLUMN_CODBARRAS,
            CustomSQLiteOpenHelper.COLUMN_CONDICAO,
            CustomSQLiteOpenHelper.COLUMN_RETIRADA,
            CustomSQLiteOpenHelper.COLUMN_DEVOLUCAO,
            CustomSQLiteOpenHelper.COLUMN_INSTALACAO,
            CustomSQLiteOpenHelper.COLUMN_CONTACLIENTE,
            CustomSQLiteOpenHelper.COLUMN_OS};
    private CustomSQLiteOpenHelper sqliteOpenHelper;

    public MateriaisDAO(Context context) {
        sqliteOpenHelper = new CustomSQLiteOpenHelper( context );
    }

    public void open() throws SQLException {
        database = sqliteOpenHelper.getWritableDatabase();
    }

    public void close() {
        sqliteOpenHelper.close();
    }

    public Material create(String material) {
        ContentValues values = new ContentValues();
        values.put( CustomSQLiteOpenHelper.COLUMN_CODIGO, material );
        long insertId = database.insert( CustomSQLiteOpenHelper.TABLE_MATERIAL, null, values );
        Cursor cursor = database.query( CustomSQLiteOpenHelper.TABLE_MATERIAL, columns,
                CustomSQLiteOpenHelper.COLUMN_ID + "=" + insertId, null, null, null, null );

        cursor.moveToFirst();
        Material newMaterial = new Material();
        newMaterial.setId( cursor.getLong( 0 ) );
        newMaterial.setMaterial( cursor.getString( 1 ) );
        cursor.close();

        return newMaterial;
    }


    public void delete(Material material) {
        long id = material.getId();
        database.delete( CustomSQLiteOpenHelper.TABLE_MATERIAL, CustomSQLiteOpenHelper.COLUMN_ID
                + " = " + id, null );
    }

    public List<Material> getAll() {
        List<Material> materiais = new ArrayList<Material>();

        Cursor cursor = database.query( CustomSQLiteOpenHelper.TABLE_MATERIAL,
                columns, null, null, null, null, null );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Material material = new Material();
            material.setId( cursor.getLong( 0 ) );
            material.setMaterial( cursor.getString( 1 ) );
            materiais.add( material );
            cursor.moveToNext();
        }
        cursor.close();
        return materiais;
    }

}
