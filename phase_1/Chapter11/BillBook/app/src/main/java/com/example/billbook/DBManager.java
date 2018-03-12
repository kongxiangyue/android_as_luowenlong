package com.example.billbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by htx on 2016/2/20.
 */
public class DBManager {//自定义的类,用于封装数据操作
    private DBHelper helper;
    private SQLiteDatabase db;
    private static final String TABLE_NAME = "count";

    public DBManager(Context context) {
        //这部分代码在程序刚启动即执行
        // 在最开始实例化的时候，通过DBHelper创建数据库
        helper = new DBHelper(context);
        //因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0, mFactory);
        //所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }

    public void insert(Count count) {
        // insert操作,用于收支记录数据的插入,在"记一笔"那里调用
        db.beginTransaction();  //开始事务
        try {
            ContentValues cv = new ContentValues();
            cv.put("count",count.getMoney());
            cv.put("type",count.getType());
            cv.put("date", count.getDate());
            cv.put("describe",count.getDescribe());
            db.insert(TABLE_NAME,"id",cv);
            db.setTransactionSuccessful();  //设置事务成功完成
        }finally {
            db.endTransaction();    //结束事务
        }
        db.update()
    }

    public Double getResult(int type)//type要么是1要么是2 out 或in
    {
        //查询操作,将所有收/支信息查出来,通过type来区别收/支
        Double result = 0.0;
        Cursor c = db.rawQuery("select id,count,type,date,describe from "+ TABLE_NAME,null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            if (c.getInt(2) == type)//1为支出,2为收入,在MainActivity里定义了
                result += Double.parseDouble(c.getString(1));
        }//用于算出总收入/总支出
        c.close();
        return result;
    }
    public void closeDB(){
        db.close();
    }
}
