package com.sise.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLTableUtils {

	private static final String DATABASE_NAME = "student.db";
	private static final String TABLE_NAME = "CourseTable";

	private DBOpenHelper helper;
	public SQLiteDatabase db;

	public SQLTableUtils(Context context) {
		helper = new DBOpenHelper(context, DATABASE_NAME, null, 1);// ��ʼ�����ݿ�
	}

	/**
	 * ����ȫ���γ̱�����
	 * 
	 * @param course
	 *            ȫ���γ�
	 */
	public void insert(List<String[]> course) {
		db = helper.getWritableDatabase(); // ��ȡ�����ݿ�
		for (String[] s : course) {
			db.execSQL(
					"INSERT INTO CourseTable(PERIOD,MONDAY,TUESDAY,WENESDAY,THURSDAY,FRIDAY) VALUES(?,?,?,?,?,?)",
					new Object[] { s[0], s[1], s[2], s[3], s[4], s[5] });
		}
		// db.close();
		
	}

	/**
	 * ��ѯ���пγ�
	 * 
	 * @return ���пγ�
	 */

	public List<String[]> findAll() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM CourseTable", null);
		List<String[]> courses = new ArrayList<String[]>();
		if (cursor.getCount() == 0) {// ��ѯ��������
			return null;
		}
		while (cursor.moveToNext()) {
			String PERIOD = cursor.getString(0);
			String MONDAY = cursor.getString(1);
			String TUESDAY = cursor.getString(2);
			String WENESDAY = cursor.getString(3);
			String THURSDAY = cursor.getString(4);
			String FRIDAY = cursor.getString(5);
			String[] s = { PERIOD, MONDAY, TUESDAY, WENESDAY, THURSDAY, FRIDAY };
			courses.add(s);
		}
		cursor.close();
		// db.close();
		return courses;
	}

	/**
	 * ɾ�����
	 */
	public void deleteTable() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
		// db.close();
	}

	/**
	 * ��ȡ��¼��
	 * 
	 * @return ��¼��
	 */
	public int getCount() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM CourseTable", null);
		cursor.moveToNext();
		return cursor.getInt(0);
	}

}
