package com.example.lzq.supperpictagview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzq.supperpictagview.R;
import com.example.lzq.supperpictagview.bean.RecordBean;

import java.util.List;

/**
 * 推荐标签
 */
public class RecomendLabelAdapter extends BaseAdapter {
	 private Context mContext;
	    private List<RecordBean> mList;
	    public RecomendLabelAdapter(Context mContext, List<RecordBean> mList) {
	        this.mContext = mContext;
	        this.mList = mList;
	    }
	    public void update(List<RecordBean> mList) {
			this.mList=mList;
			notifyDataSetChanged();
		}

	    @Override
	    public int getCount() {
	        if (mList == null) {
	            return 0;
	        } else {
	            return mList.size();
	        }
	    }

	    @Override
	    public Object getItem(int i) {
	        return mList.get(i);
	    }

	    @Override
	    public long getItemId(int i) {
	        return i;
	    }

	    @Override
	    public View getView(int i, View view, ViewGroup viewGroup) {
	        ViewHolder viewHolder;
	        if (view == null) {
	            viewHolder = new ViewHolder();
	            view = LayoutInflater.from(mContext).inflate(R.layout.list_location_tag, null);
	            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
	            viewHolder.iv_loc = (ImageView) view.findViewById(R.id.iv_loc);
				if ("SITE".equals(mList.get(i).getType())){
					viewHolder.iv_loc.setImageResource(R.drawable.iv_loc);
				}else{
					viewHolder.iv_loc.setImageResource(R.drawable.iv_tag);
				}
	            view.setTag(viewHolder);
	        } else {
	            viewHolder = (ViewHolder) view.getTag();
	        }
	        initData(i, viewHolder);
	        return view;
	    }

	    private void initData(final int i, ViewHolder viewHolder) {
			viewHolder.tv_name.setText(mList.get(i).getName());
	    }

	    class ViewHolder {
	        private TextView tv_name;
	        private ImageView iv_loc;
	    }


}
