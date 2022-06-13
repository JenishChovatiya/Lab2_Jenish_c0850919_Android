package com.example.lab2_jenish_c0850919_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dataBaseHelper extends SQLiteOpenHelper
{


    //declaring some variables for database
    private static final String  DATABASE_NAME="ProductDB";
    private static final String TABLE_NAME="Product";
    private static final String product_id ="productsid";
    private static final String product_name ="productsname";
    private static final String product_description ="productsdecription";
    private static final String product_price ="productsprice";
    private static final String created_at="created_at";

    public dataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB)
    {
        String table_query="CREATE TABLE if not EXISTS "+TABLE_NAME+
                "("+
                product_id +" INTEGER PRIMARY KEY ,"+
                product_name +" TEXT ,"+
                product_price + " TEXT ,"+
                product_description +" TEXT ,"+
                created_at+ " TEXT "+
                ")";
        DB.execSQL(table_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion)
    {
        DB.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }


    public void AddProduct(RawData rawData){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(product_id, rawData.getproduct_id());
        contentValues.put(product_name, rawData.getProduct_name());
        contentValues.put(product_description, rawData.getProduct_description());
        contentValues.put(product_price, rawData.getProduct_price());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }


    public RawData getProduct(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME,new String[]{product_id, product_name, product_description, product_price},product_id+" = ?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        RawData rawData =new RawData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        db.close();
        return rawData;
    }


    public List<RawData> getAllProducts(){
        List<RawData> productModelList =new ArrayList<>();
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                RawData rawData =new RawData(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
                productModelList.add(rawData);
            }
            while (cursor.moveToNext());

        }
        db.close();
        return productModelList;
    }


    public int updateProduct(RawData productModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(product_id, productModel.getproduct_id());
        contentValues.put(product_name, productModel.getProduct_name());
        contentValues.put(product_description, productModel.getProduct_description());
        contentValues.put(product_price, productModel.getProduct_price());
        return db.update(TABLE_NAME,contentValues,product_id+"=?",new String[]{String.valueOf(productModel.product_id())});

    }

    public void deleteProduct(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,product_id+"=?",new String[]{id});
        db.close();
    }

    public int getTotalCount(){
        String query="SELECT * from "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor.getCount();
    }



}
