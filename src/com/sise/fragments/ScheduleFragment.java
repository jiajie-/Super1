package com.sise.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sise.super1.AttendenceActivity;
import com.sise.super1.R;
import com.sise.utils.SQLTableUtils;
import com.sise.view.GVTable;
import com.sise.view.TitleView;
import com.sise.view.TitleView.OnRightButtonClickListener;

/**
 * @author yangyu 功能描述：首页fragment页面
 */
public class ScheduleFragment extends Fragment {

	private View mParent;
	private FragmentActivity mActivity;
	private TitleView mTitle;

	private GVTable table;
	private SQLTableUtils utils;

	private static final String TABLE_NAME = "CourseTable";

	// 测试数据
	List<String[]> course = new ArrayList<String[]>();
	String[] s1 = { "1 - 2", "数据仓库与数据挖掘技术", "", "", "", "" };
	String[] s2 = { "3 - 4", "", "", "", "", "" };
	String[] s3 = { "5 - 6", "", "", "Hadoop大数据处理", "", "" };
	String[] s4 = { "7 - 8", "", "", "企业资源计划（ERP）", "", "" };
	String[] s5 = { "9 - 10", "", "", "Spring2.0技术", "", "" };
	String[] s6 = { "11 - 12", "", "", "", "", "" };
	String[] s7 = { "13 - 14", "", "", "", "设计模式解析", "" };
	String[] s8 = { "15 - 16", "", "", "NoSQL数据库入门", "", "" };

	// 测试数据

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static ScheduleFragment newInstance(int index) {
		ScheduleFragment f = new ScheduleFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//
		course.add(s1);
		course.add(s2);
		course.add(s3);
		course.add(s4);
		course.add(s5);
		course.add(s6);
		course.add(s7);
		course.add(s8);
		//
		utils = new SQLTableUtils(getActivity().getApplicationContext());

		table = new GVTable(getActivity().getApplicationContext());
		table.gvSetTableRowCount(8);// 设置每个分页的ROW总数
		table.setTableOnClickListener(new GVTable.OnTableClickListener() {// 表格点击事件
			@Override
			public void onTableClickListener(int x, int y, Cursor c) {
				c.moveToPosition(y);
				// c.getString(x) 课程名称
				String str = c.getString(x) + " 位置:(" + String.valueOf(x) + ","+ String.valueOf(y) + ")";
				Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
			}
		});
		View view = inflater.inflate(R.layout.fragment_schedule, container,
				false);
		LinearLayout ly = (LinearLayout) view;
		ly.addView(table);

		// 查询数据库，查询到就读取，没有就插入
		List<String[]> s = utils.findAll();// 查询缓存
		if (s == null) {// 没有缓存数据
			// 请求服务器数据
			// course=xxx
			utils.insert(course);// course->从服务器获取的数据
			System.out.println("没有数据，正在插入");
			System.out.println("课程记录数：" + utils.getCount());
		} else {// 有缓存数据
			utils.deleteTable();
			utils.insert(s);// s->缓存数据
			System.out.println("有数据，显示缓存数据");
			System.out.println("课程记录数：" + utils.getCount());
		}
		table.gvReadyTable("select * from " + TABLE_NAME, utils.db);
		table.LoadTable(0);
		
		System.out.println("课程记录数：" + utils.getCount());

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mActivity = getActivity();
		mParent = getView();

		mTitle = (TitleView) mParent.findViewById(R.id.title);
		mTitle.setTitle(R.string.title_schedule);
		mTitle.setRightButton(R.string.attendance,
				new OnRightButtonClickListener() {
					@Override
					public void onClick(View button) {
						goAttendenceActivity();
					}
				});
	}

	private void goAttendenceActivity() {
		Intent intent = new Intent(mActivity, AttendenceActivity.class);
		startActivity(intent);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onDestroy() {
		utils.db.close();

		System.out.println("执行onDestory()");
		table.gvRemoveAll();
		super.onDestroy();
	}

	

}
