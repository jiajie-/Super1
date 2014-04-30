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
		helper = new DBOpenHelper(context, DATABASE_NAME, null, 1);// 初始化数据库
	}

	/**
	 * 插入全部课程表数据
	 * 
	 * @param course
	 *            全部课程
	 */
	public void insert(List<String[]> course) {
		db = helper.getWritableDatabase(); // 获取到数据库
		for (String[] s : course) {
			db.execSQL(
					"INSERT INTO CourseTable(PERIOD,MONDAY,TUESDAY,WENESDAY,THURSDAY,FRIDAY) VALUES(?,?,?,?,?,?)",
					new Object[] { s[0], s[1], s[2], s[3], s[4], s[5] });
		}
		// db.close();
		
	}

	/**
	 * 查询所有课程
	 * 
	 * @return 所有课程
	 */

	public List<String[]> findAll() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM CourseTable", null);
		List<String[]> courses = new ArrayList<String[]>();
		if (cursor.getCount() == 0) {// 查询不到数据
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
	 * 删除表格
	 */
	public void deleteTable() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete(TABLE_NAME, null, null);
		// db.close();
	}

	/**
	 * 获取记录数
	 * 
	 * @return 记录数
	 */
	public int getCount() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM CourseTable", null);
		cursor.moveToNext();
		return cursor.getInt(0);
	}

}
