package com.example.chitfunds.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.chitfunds.DTO.UserDetailDTO;

public class DatabaseControllerForUserData extends SQLiteOpenHelper
{


    public DatabaseControllerForUserData(@Nullable Context context) {
        super(context,ContractForUserData.WholeDatabaseName,null,ContractForUserData.Database_Version);

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

     String CreateTable="CREATE TABLE "+ContractForUserData.TABLE_NAME+"( "+ContractForUserData.COl1_Email+" varchar(30) PRIMARY KEY,"
             +ContractForUserData.COl2_FullName+" varchar(30),"+ContractForUserData.Col3_PhoneNumber+" varchar(13),"+ContractForUserData.Col4_Password+" varchar(30),"
             +ContractForUserData.COl5_NomineeName+" varchar(30),"+ContractForUserData.Col6_NomineePhoneNumber+" varchar(13),"+ContractForUserData.Col7_UserAddress+" varchar(30),"
             +ContractForUserData.Col8_PlansSubscribed+" varchar(40)"+");";
     db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



     public boolean signUpWithUserData(UserDetailDTO userDetailDTO)
     {
       SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
       ContentValues contentValues=new ContentValues();
       contentValues.put(ContractForUserData.COl1_Email,userDetailDTO.getEmail());
       contentValues.put(ContractForUserData.COl2_FullName,userDetailDTO.getFullName());
       contentValues.put(ContractForUserData.Col3_PhoneNumber,userDetailDTO.getUserPhoneNumber());
       contentValues.put(ContractForUserData.Col4_Password,userDetailDTO.getPassword());
       contentValues.put(ContractForUserData.COl5_NomineeName,userDetailDTO.getNomineeName());
       contentValues.put(ContractForUserData.Col6_NomineePhoneNumber,userDetailDTO.getNomineePhoneNumber());
       contentValues.put(ContractForUserData.Col7_UserAddress,userDetailDTO.getUserAddress());
       contentValues.put(ContractForUserData.Col8_PlansSubscribed,userDetailDTO.getPlans());

       long chk=sqLiteDatabase.insert(ContractForUserData.TABLE_NAME,null,contentValues);
       sqLiteDatabase.close();
       if(chk==-1)
           return false;
       else
           return true;
      }

      public boolean isEmailExists(String email){
        String read = "SELECT * FROM "+ContractForUserData.TABLE_NAME+" WHERE "+ ContractForUserData.COl1_Email+" = ?";

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(read,new String[]{email});
        int noOfRows=cursor.getCount();
          sqLiteDatabase.close();
          if(noOfRows<=0)
            return false;
        else
            return true;
      }
      public String[] getUserNameAndPassword(String email){
          String read = "SELECT "+ContractForUserData.COl1_Email+","+ContractForUserData.Col4_Password+" FROM "+ContractForUserData.TABLE_NAME+" WHERE "+ ContractForUserData.COl1_Email+" = ?";

          SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
          Cursor cursor=sqLiteDatabase.rawQuery(read,new String[]{email});
          Log.e("noOfRows",""+cursor.getCount());
          if(cursor.getCount()>0)
          {
               cursor.moveToNext();
              String arr[]=new String[]{cursor.getString(cursor.getColumnIndex(ContractForUserData.COl1_Email)),cursor.getString(cursor.getColumnIndex(ContractForUserData.Col4_Password))};
              sqLiteDatabase.close();
              return arr;
          }
          else {
              sqLiteDatabase.close();
              return null;
          }
      }

    public boolean updateUserFullName(String updatedName,String email)
    {
      SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
      ContentValues contentValues=new ContentValues();
      contentValues.put(ContractForUserData.COl2_FullName,updatedName);
      int noOfrows=sqLiteDatabase.update(ContractForUserData.TABLE_NAME,contentValues,ContractForUserData.COl1_Email+" = ?",new String[]{email});
      sqLiteDatabase.close();
      if (noOfrows>0)
          return true;
      else
          return false;
    }

    public int getCurrentUserData(String email)
    {
      SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
      String query="SELECT * FROM "+ContractForUserData.TABLE_NAME+" WHERE "+ContractForUserData.COl1_Email+" = ?";
      Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{email});
      if (cursor.getCount()>0)
      {

      }
      else
      {
          sqLiteDatabase.close();
          return Integer.parseInt(null);
      }

    return 0;}
}
