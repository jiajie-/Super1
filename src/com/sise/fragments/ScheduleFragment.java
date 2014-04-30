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
 * @author yangyu ������������ҳfragmentҳ��
 */
public class ScheduleFragment extends Fragment {

	private View mParent;
	private FragmentActivity mActivity;
	private TitleView mTitle;

	private GVTable table;
	private SQLTableUtils utils;

	private static final String TABLE_NAME = "CourseTable";

	// ��������
	List<String[]> course = new ArrayList<String[]>();
	String[] s1 = { "1 - 2", "���ݲֿ��������ھ���", "", "", "", "" };
	String[] s2 = { "3 - 4", "", "", "", "", "" };
	String[] s3 = { "5 - 6", "", "", "Hadoop�����ݴ���", "", "" };
	String[] s4 = { "7 - 8", "", "", "��ҵ��Դ�ƻ���ERP��", "", "" };
	String[] s5 = { "9 - 10", "", "", "Spring2.0����", "", "" };
	String[] s6 = { "11 - 12", "", "", "", "", "" };
	String[] s7 = { "13 - 14", "", "", "", "���ģʽ����", "" };
	String[] s8 = { "15 - 16", "", "", "NoSQL���ݿ�����", "", "" };

	// ��������

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
		table.gvSetTableRowCount(8);// ����ÿ����ҳ��ROW����
		table.setTableOnClickListener(new GVTable.OnTableClickListener() {// ������¼�
			@Override
			public void onTableClickListener(int x, int y, Cursor c) {
				c.moveToPosition(y);
				// c.getString(x) �γ�����
				String str = c.getString(x) + " λ��:(" + String.valueOf(x) + ","+ String.valueOf(y) + ")";
				Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
			}
		});
		View view = inflater.inflate(R.layout.fragment_schedule, container,
				false);
		LinearLayout ly = (LinearLayout) view;
		ly.addView(table);

		// ��ѯ���ݿ⣬��ѯ���Ͷ�ȡ��û�оͲ���
		List<String[]> s = utils.findAll();// ��ѯ����
		if (s == null) {// û�л�������
			// �������������
			// course=xxx
			utils.insert(course);// course->�ӷ�������ȡ������
			System.out.println("û�����ݣ����ڲ���");
			System.out.println("�γ̼�¼����" + utils.getCount());
		} else {// �л�������
			utils.deleteTable();
			utils.insert(s);// s->��������
			System.out.println("�����ݣ���ʾ��������");
			System.out.println("�γ̼�¼����" + utils.getCount());
		}
		table.gvReadyTable("select * from " + TABLE_NAME, utils.db);
		table.LoadTable(0);
		
		System.out.println("�γ̼�¼����" + utils.getCount());

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

		System.out.println("ִ��onDestory()");
		table.gvRemoveAll();
		super.onDestroy();
	}

	

}
