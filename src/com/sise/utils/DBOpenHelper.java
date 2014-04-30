package com.sise.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBOpenHelper extends SQLiteOpenHelper {

	// final String CREATE_TABLE_SQL =
	// "CREATE TABLE CourseTable(? text not null, ? text not null,? text not null,? text not null,? text not null,? text not null);";

	/**
	 * 创建OpenHelper
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            数据库名
	 * @param factory
	 *            游标工厂
	 * @param version
	 *            数据库版本, 不要设置为0, 如果为0则会每次都创建数据库
	 */
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * 当数据库第一次创建的时候被调用
	 */
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE CourseTable(PERIOD text not null, MONDAY text not null,TUESDAY text not null,WENESDAY text not null,THURSDAY text not null,FRIDAY text not null);");
	}

	/**
	 * 当数据库版本发生改变的时候被调用
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("ALTER TABLE person ADD balance");
//		db.execSQL("DROP TABLE IF EXISTS smslist");
//		onCreate(db);
	}

}
