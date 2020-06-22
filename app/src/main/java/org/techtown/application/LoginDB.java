package org.techtown.application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class LoginDB extends SQLiteOpenHelper {
    //생성자
    public LoginDB(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //DB 처음 만들때만 호출_ 테이블생성 초기처리
    @Override
    public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table LoginDB (" +
                    "name char(10) not null ," +
                    "email varchar(20) not null," +
                    "id char(20) not null primary key," +
                    "pw char(20) not null,"+
                    "area varchar(50)," +   //선호태그..인데 아직..
                    "taste varchar(50),"+
                    "etc varchar(50) );");

    }

    //DB업그레이드시  버전 변경될때 호출
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void insert(String name, String email, String id, String pw){ //기본정보만 insert하는 함수
        SQLiteDatabase db= getWritableDatabase(); //db열기

        db.execSQL("Insert into LoginDB values('"+
                name+"','"+ email+"','"+ id+"','"+ pw+"',"+ "null,null,null);");
        db.close(); //db닫기
    }

    public void insertTag(String id,String area, String taste, String etc){ // tag insert하는 함수
        SQLiteDatabase db= getWritableDatabase(); //db열기

        db.execSQL("Update LoginDB SET area='"+area+"'" +
                ","+"taste='"+taste+"'" + ","+"etc='"+etc+"' where id='"+id+"'");
        db.close(); //db닫기
    }

    public void update(String pw){ //회원정보 변경_나중에 태그 변경도 할 수 있게끔...??
        SQLiteDatabase db= getWritableDatabase();
        db.close(); //db닫기
    }

    public void delete(String id){ //회원정보 삭제
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("delete from LoginDB where id='"+id+"';");
        db.close();
    }

    public String getResult(){ //db전체출력
        SQLiteDatabase db= getWritableDatabase();
        String result="";
        Cursor cursor= db.rawQuery("select* from LoginDB",null);
        while (cursor.moveToNext()){
            result += "이름:" +cursor.getString(0)+" "
                    + "이메일:"+ cursor.getString(1) +" "
                    + "id:"+ cursor.getString(2) +" "
                    + "pw:"+ cursor.getString(3)+"\n"
                    + "(태그)지역:"+ cursor.getString(4) +" "
                    + "(태그)식사:"+ cursor.getString(5) +" "
                    + "(태그)etc:"+ cursor.getString(6);
        }
        return result;
    }

}
