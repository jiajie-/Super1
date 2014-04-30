package com.sise.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBOpenHelper extends SQLiteOpenHelper {

	// final String CREATE_TABLE_SQL =
	// "CREATE TABLE CourseTable(? text not null, ? text not null,? text not null,? text not null,? text not null,? text not null);";

	/**
	 * ����OpenHelper
	 * 
	 * @param context
	 *            ������
	 * @param name
	 *            ���ݿ���
	 * @param factory
	 *            �α깤��
	 * @param version
	 *            ���ݿ�汾, ��Ҫ����Ϊ0, ���Ϊ0���ÿ�ζ��������ݿ�
	 */
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * �����ݿ��һ�δ�����ʱ�򱻵���
	 */
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE CourseTable(PERIOD text not null, MONDAY text not null,TUESDAY text not null,WENESDAY text not null,THURSDAY text not null,FRIDAY text not null);");
	}

	/**
	 * �����ݿ�汾�����ı��ʱ�򱻵���
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// db.execSQL("ALTER TABLE person ADD balance");
//		db.execSQL("DROP TABLE IF EXISTS smslist");
//		onCreate(db);
	}

}
