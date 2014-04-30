package com.sise.beans;

import java.util.List;

import com.sise.beans.CardAdapter.ViewHolder;
import com.sise.super1.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FuncAdapter extends BaseAdapter {

	private List<Func> data;
	private Context context;
	private LayoutInflater mInflater;

	public FuncAdapter(List<Func> data, Context context) {
		this.data = data;
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.func_item, null);
			
			holder = new ViewHolder();
			holder.iv_func = (ImageView) convertView.findViewById(R.id.iv_func);
			holder.tv_func = (TextView) convertView.findViewById(R.id.tv_func);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		Func func=data.get(position);
		holder.iv_func.setImageResource(func.getFunc_img());
		holder.tv_func.setText(func.getFunc_name());
		return convertView;

	}
	
	static class ViewHolder{
		ImageView iv_func;
		TextView tv_func;
	}

}
