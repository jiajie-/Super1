package com.sise.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.sise.super1.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class GVTable extends LinearLayout {
	protected GridView gvTable;//
	protected SimpleAdapter saTable;// 适配器
	protected ArrayList<HashMap<String, String>> srcTable;// 数据源

	protected int TableRowCount = 8;//每页的Row总数
	protected int TableColCount = 0;// 每页col的数量

	protected SQLiteDatabase db;
	protected String rawSQL = "";
	protected Cursor curTable;// 分页时使用的Cursor
	protected OnTableClickListener clickListener;// 整个分页控件被点击时的回调函数
	

	public GVTable(Context context) {
		super(context);
		this.setOrientation(VERTICAL);// 垂直
		
		// ----------------------------------------
		gvTable = new GridView(context);
		
		addView(gvTable);// 宽长式样
		
		srcTable = new ArrayList<HashMap<String, String>>();
		saTable = new SimpleAdapter(context, srcTable,// 数据来源
				R.layout.table_item,// XML实现
				new String[] { "ItemText" },// 动态数组与ImageItem对应的子项
				new int[] { R.id.ItemText });
		// 添加并且显示
		gvTable.setSelector(R.drawable.func_selector);//设置点击样式
		gvTable.setAdapter(saTable);
		gvTable.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				int y = arg2 / curTable.getColumnCount();// 标题栏的不算
				int x = arg2 % curTable.getColumnCount();
				if (clickListener != null// 分页数据被点击
						&& y != -1) {// 点中的不是标题栏时
					clickListener.onTableClickListener(x, y, curTable);
				}
			}
		});
	
	}

	/**
	 * 清除所有数据
	 */
	public void gvRemoveAll() {
		if (this.curTable != null)
			curTable.close();
		srcTable.clear();
		saTable.notifyDataSetChanged();

	}

	/**
	 * 读取指定ID的分页数据,返回当前页的总数据 SQL:Select * From TABLE_NAME Limit 9 Offset 10;
	 * 表示从TABLE_NAME表获取数据，跳过10行，取9行
	 * 
	 * @param pageID
	 *            指定的分页ID
	 */
	public void LoadTable(int pageID) {
		if (curTable != null)// 释放上次的数据
			curTable.close();

		String sql = rawSQL + " Limit " + String.valueOf(TableRowCount)
				+ " Offset " + String.valueOf(pageID * TableRowCount);//
		System.out.println("TableRowCount："+TableRowCount);
		System.out.println("sql："+sql);
		curTable = db.rawQuery(sql, null);

		gvTable.setNumColumns(curTable.getColumnCount());// 表现为表格的关键点！
		TableColCount = curTable.getColumnCount();
		srcTable.clear();
		// 取得字段名称
		int colCount = curTable.getColumnCount();
//		for (int i = 0; i < colCount; i++) {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("ItemText", curTable.getColumnName(i));
//			srcTable.add(map);
//		}

		// 列举出所有数据
		int recCount = curTable.getCount();
		for (int i = 0; i < recCount; i++) {// 定位到一条数据
			curTable.moveToPosition(i);
			for (int ii = 0; ii < colCount; ii++)// 定位到一条数据中的每个字段
			{
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("ItemText", curTable.getString(ii));
				srcTable.add(map);
			}
		}
		saTable.notifyDataSetChanged();
	}

	/**
	 * 设置表格的最多显示的行数
	 * @param row
	 *            表格的行数
	 */
	public void gvSetTableRowCount(int row) {
		TableRowCount = row;
	}

	/**
	 * 取得表格的最大行数
	 * 
	 * @return 行数
	 */
	public int gvGetTableRowCount() {
		return TableRowCount;
	}

	/**
	 * 取得当前分页的Cursor
	 * 
	 * @return 当前分页的Cursor
	 */
	public Cursor gvGetCurrentTable() {
		return curTable;
	}

	/**
	 * 准备分页显示数据
	 * 
	 * @param rawSQL
	 *            sql语句
	 * @param db
	 *            数据库
	 */
	public void gvReadyTable(String rawSQL, SQLiteDatabase db) {
		this.rawSQL = rawSQL;
		this.db = db;
	}

	// ---------------------------------------------------------
	/**
	 * 表格被点击时的回调函数
	 */
	public void setTableOnClickListener(OnTableClickListener click) {
		this.clickListener = click;
	}

	public interface OnTableClickListener {
		public void onTableClickListener(int x, int y, Cursor c);
	}

}
