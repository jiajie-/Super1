package com.sise.super1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sise.beans.Card;
import com.sise.beans.CardAdapter;
import com.sise.view.TitleView;
import com.sise.view.TitleView.OnLeftButtonClickListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author yangyu 考勤Activity界面
 */
public class AttendenceActivity extends FragmentActivity {

	private TitleView mTitle;
	private ListView mListView;
	private List<Card> data = new ArrayList<Card>();
	private CardAdapter mAdapter;
	
	private void initData() {
        for(int i=0;i<10;i++){
            Card card = new Card("Card "+i, "code"+i,"全勤");  
            data.add(card);  
        }
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_attendence);
		
		initData();  
        mListView = (ListView) findViewById(R.id.mListView);  
        
        mAdapter = new CardAdapter(data,this);
        mListView.setAdapter(mAdapter);
		
		mTitle = (TitleView) findViewById(R.id.title);//标题
		mTitle.setTitle(R.string.attendance);
		mTitle.setLeftButton(R.string.back, new OnLeftButtonClickListener() {
			@Override
			public void onClick(View button) {
				finish();
			}
		});

	}

}
