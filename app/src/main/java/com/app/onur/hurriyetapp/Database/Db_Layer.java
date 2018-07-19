package com.app.onur.hurriyetapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.onur.hurriyetapp.Model.Article;
import com.app.onur.hurriyetapp.Model.MyArticle;

import java.util.ArrayList;

public class Db_Layer {

    SQLiteDatabase db;
    MyDb myDb;

    public Db_Layer(Context c){
        myDb = new MyDb(c);
    }

    public void openDb(){
        db = myDb.getWritableDatabase();
    }

    public void closeDb(){
        myDb.close();
    }

    public void FavoriteArticleAdd(Article article){
        ContentValues val = new ContentValues();
        val.put("id",article.getId());
        val.put("title",article.getTitle());
        val.put("url",article.getUrl());
        val.put("fileUrl",article.getFiles().get(0).getFileUrl());
        db.insert("Article",null,val);
    }

    public void FavoriteArticleRemove(Article article){
        int id = article.getId();
        db.delete("Article","id="+id,null);
    }

    public boolean FavoriteArticleFind(int id){
        boolean control = false;
        String columns[] = {"id","title","url","fileUrl"};
        Cursor c = db.query("Article",columns,null,null,null,null,null);
        c.moveToFirst();

        while (!c.isAfterLast()){

            if(id == c.getInt(0)){
                control = true;
                break;
            }
            c.moveToNext();
        }
        return control;
    }

    public ArrayList<MyArticle> FavoriteArticleList(){

        String columns[] = {"id","title","url","fileUrl"};
        ArrayList<MyArticle> articles = new ArrayList<MyArticle>();
        Cursor c = db.query("Article", columns,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            int id = c.getInt(0);
            String title = c.getString(1);
            String url = c.getString(2);
            String fileurl = c.getString(3);

            MyArticle myArticle = new MyArticle(id,title,url,fileurl);

            articles.add(myArticle);
            c.moveToNext();
        }
        c.close();
        return articles;
    }
}
