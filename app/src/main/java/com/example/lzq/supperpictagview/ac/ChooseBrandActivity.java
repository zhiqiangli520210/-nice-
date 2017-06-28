package com.example.lzq.supperpictagview.ac;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lzq.supperpictagview.R;
import com.example.lzq.supperpictagview.adapter.RecomendLabelAdapter;
import com.example.lzq.supperpictagview.bean.AttentionBrand;
import com.example.lzq.supperpictagview.bean.RecordBean;
import com.example.lzq.supperpictagview.event.LocTagEvent;
import com.example.lzq.supperpictagview.utils.PermissionUtils;
import com.example.lzq.supperpictagview.utils.dialog.CustomDialogLoading;
import com.example.lzq.supperpictagview.utils.dialog.CustomEditTextDialog;
import com.example.lzq.supperpictagview.view.MyListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



/**
 * ---选择标签
 * Created by lzq 2017/6/28.
 */
public class ChooseBrandActivity extends Activity implements View.OnClickListener {

	private CustomDialogLoading.Builder builder;
	private CustomDialogLoading mDialog;

	private Context context;
	private String edit;

	private ArrayList<AttentionBrand> blist=new ArrayList<>();
	private MyListView lv_local_tag;//位置标签
	private MyListView lv_recom_tag;//推荐标签
	private RecomendLabelAdapter recommendAdapter;

	public List<RecordBean> recommendList;
	private ArrayList<RecordBean> localList;
	private RecomendLabelAdapter localAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_brand);
		context=this;
		builder = new CustomDialogLoading.Builder(this, "");
		mDialog = builder.create();
		initView();
		setListener();
	}


	private void initView() {
		lv_local_tag = (MyListView) findViewById(R.id.lv_local_tag);//位置
		lv_recom_tag = (MyListView) findViewById(R.id.lv_recom_tag);//推荐
		localList=new ArrayList<RecordBean>();
		RecordBean bean1=new RecordBean("SITE","北京饭桶天下");
		RecordBean bean2=new RecordBean("SITE","苏州街");
		RecordBean bean3=new RecordBean("SITE","中关村");
		RecordBean bean4=new RecordBean("SITE","国泰航空");
		RecordBean bean5=new RecordBean("SITE","银科大厦");
		RecordBean bean6=new RecordBean("SITE","中湾国际");
		localList.add(bean1);
		localList.add(bean2);
		localList.add(bean3);
		localList.add(bean4);
		localList.add(bean5);
		localList.add(bean6);
		recommendList=new ArrayList<RecordBean>();
		RecordBean bean11=new RecordBean("NORMAL","大约在冬季");
		RecordBean bean22=new RecordBean("NORMAL","你最美");
		RecordBean bean33=new RecordBean("NORMAL","帅到没朋友");
		RecordBean bean44=new RecordBean("NORMAL","我最帅");
		RecordBean bean55=new RecordBean("NORMAL","哇咔咔");
		RecordBean bean66=new RecordBean("NORMAL","互相伤害啊");
		recommendList.add(bean11);
		recommendList.add(bean22);
		recommendList.add(bean33);
		recommendList.add(bean44);
		recommendList.add(bean55);
		recommendList.add(bean66);
        //本地的位置
		lv_local_tag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				EventBus.getDefault().post(new LocTagEvent(localList.get(i).getType(),localList.get(i).getName()));
				Toast.makeText(context,localList.get(i).getName(),Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		localAdapter = new RecomendLabelAdapter(context, localList);
		lv_local_tag.setAdapter(localAdapter);
		//推荐的位置
		lv_recom_tag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				EventBus.getDefault().post(new LocTagEvent(recommendList.get(i).getType(),recommendList.get(i).getName()));
				Toast.makeText(context,recommendList.get(i).getName(),Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		recommendAdapter = new RecomendLabelAdapter(context, recommendList);
		lv_recom_tag.setAdapter(recommendAdapter);
		lv_recom_tag.setDividerHeight(28);
	}

	private void setListener() {

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		}

	}
	@Subscribe(threadMode = ThreadMode.MAIN) //注册一个在后台线程执行的方法,用于接收事件
	public void onUserEvent(CustomEditTextDialog.Builder.EventClass event) {//参数必须是ClassEvent类型, 否则不会调用此方法
// Toast.makeText(MainActivity.this,event.data,Toast.LENGTH_SHORT).show();
		edit=event.edit;
		Log.i("edit--", edit);
	}



	//在任意地方,注册事件类
	public static class ClassEvent{
		public AttentionBrand data;
		ClassEvent( AttentionBrand data){
			this.data=data;
		};
	}
	@Override
	public void onResume() {
		super.onResume();
	}
}