package com.sise.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.sise.beans.Card;
import com.sise.beans.Func;
import com.sise.beans.FuncAdapter;
import com.sise.super1.MainActivity;
import com.sise.super1.R;
import com.sise.view.TitleView;
import com.sise.view.TitleView.OnLeftButtonClickListener;
import com.sise.view.TitleView.OnRightButtonClickListener;

/**
 * @author yangyu 功能描述：搜索fragment页面
 */
public class FoundFragment extends Fragment {

	private View mParent;
	private FragmentActivity mActivity;
	private TitleView mTitle;
	private GridView gridView;
	private List<Func> data = new ArrayList<Func>();
	private FuncAdapter funcAdapter;

	

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static FoundFragment newInstance(int index) {
		FoundFragment f = new FoundFragment();

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
		View view = inflater.inflate(R.layout.fragment_found, container, false);
		gridView = (GridView) view.findViewById(R.id.gridview);
		initFunc();
		funcAdapter = new FuncAdapter(data, getActivity()
				.getApplicationContext());
		gridView.setAdapter(funcAdapter);//设置适配器
		gridView.setOnItemClickListener(new gridViewListener());//设置事件监听器
		

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mParent = getView();
		mActivity = getActivity();

		mTitle = (TitleView) mParent.findViewById(R.id.title);
		mTitle.setTitle(R.string.title_found);
		mTitle.setRightButton(R.string.add, new OnRightButtonClickListener() {
			@Override
			public void onClick(View button) {
				
			}
		});
	}
	
	class gridViewListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			switch (arg2) {
			case 0:
				//跳转到相应的activity
				Toast.makeText(mActivity, "考试时间表", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(mActivity, "平时成绩", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(mActivity, "奖惩记录", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(mActivity, "广播消息", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(mActivity, "找同学", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 返回到首页
	 */
	private void backHomeFragment() {
		getFragmentManager().beginTransaction()
				.hide(MainActivity.mFragments[1])
				.show(MainActivity.mFragments[0]).commit();
		FragmentIndicator.setIndicator(0);
	}
	
	/**
	 * 初始化功能view
	 */
	private void initFunc() {
		Func func1 = new Func(R.drawable.ic_examschedual, "考试时间表");
		Func func2 = new Func(R.drawable.ic_peacetime, "平时成绩");
		Func func3 = new Func(R.drawable.ic_record, "奖惩记录");
		Func func4 = new Func(R.drawable.ic_launcher, "广播消息");
		Func func5 = new Func(R.drawable.ic_launcher, "找同学");	
		data.add(func1);
		data.add(func2);
		data.add(func3);
		data.add(func4);
		data.add(func5);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
		if (!hidden) {
		}
	}

}
