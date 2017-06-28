package com.example.lzq.supperpictagview.view;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lzq.supperpictagview.ac.ChooseBrandActivity;
import com.example.lzq.supperpictagview.bean.AttentionBrand;
import com.example.lzq.supperpictagview.utils.StringUtils;
import com.example.lzq.supperpictagview.utils.dialog.CustomDialog;
import com.example.lzq.supperpictagview.utils.dialog.CustomEditTextDialog;


/*
* 加左右
*
* */
@SuppressLint("NewApi")
public class PictureTagLayout extends RelativeLayout implements OnTouchListener, View.OnClickListener {
	private static final int CLICKRANGE = 5;
	int startX ;
	int startY ;
	int startTouchViewLeft = 0;
	int startTouchViewTop = 0;
	private View touchView,clickView;
	private Context context;
	private CustomEditTextDialog editDialog;
	public String shareStr;
	private PopupWindow pop;
	private Button bt_finish;
	private Button bt_cancel;
	private TextView tv_brand;
	private EditText et_bask;
	private String brandStr;
	private AttentionBrand brand;
	private int height;
	private int width;
	private int endX;
	private int endY;
	private int toWhere;

	float xDown,yDown,xUp;
	boolean isLongClickModule = false;
	boolean isLongClicking = false;
	private String brandId;
	private String name;
	private String type;

	public PictureTagLayout(Context context) {
		super(context, null);
	}
	public PictureTagLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;

		init();
	}
	private void init(){
		this.setOnTouchListener(this);
	}
	//开始的位置小于结束的位置 向左滑动
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//此处是为了加入长按事件
			xDown= event.getX();
			yDown = event.getY();

			touchView = null;
			if(clickView!=null){
				clickView = null;
			}
			startX = (int) event.getX();
			startY = (int) event.getY();
			if(hasView(startX,startY)){//如果点击位置已经有了
				startTouchViewLeft = touchView.getLeft();
				startTouchViewTop = touchView.getTop();
			} else {
				showPopup();
			}
			Log.i("******点击的位置--x-", startX + "*----y"+startY);
			break;
		case MotionEvent.ACTION_MOVE:
			moveView((int) event.getX(),
					(int) event.getY());
			if((int) event.getX()>startX){//向右滑动标签view
				toWhere=1;
			}else{
				toWhere=2;
			}
			//此处是为了加入长按事件
			//当滑动时背景为选中状态 //检测是否长按,在非长按时检测
			if(!isLongClickModule)
			{
				isLongClickModule = isLongPressed(xDown, yDown, event.getX(),
						event.getY(),event.getDownTime() ,event.getEventTime(),300);
			}
			if(isLongClickModule && !isLongClicking){
				if(hasView(startX,startY)){//如果点击位置已经有了
					//处理长按事件
					isLongClicking = true;
					CustomDialog.Builder dialog = new CustomDialog.Builder(context);
					dialog.setTitle("提示");
					dialog.setMessage("您确定要删除标签么");
					dialog.setPositiveButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							dialogInterface.dismiss();
						}
					});
					dialog.setNegativeButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							dialogInterface.dismiss();
							//长按删除
							PictureTagLayout.this.removeView(touchView);
						}
					});
					dialog.create().show();
					Log.i("----长按","haha");
//					removeView(touchView);
				}
			}

		break;
		case MotionEvent.ACTION_UP:
			//此处是为了加入长按事件
			if(isLongClickModule){
				isLongClickModule = false;
				isLongClicking = false;
			}
			xUp = event.getX();

			Log.v("OnTouchListener", "Up");
			//按下和松开绝对值差当大于20时滑动，否则不显示
			if ((xUp - xDown) > 20)
			{
				//添加要处理的内容
			}
			else if((xUp - xDown ) < -20)
			{
				//添加要处理的内容
			}
			else if( 0 == (xDown - xUp))
			{
				int viewWidth = v.getWidth();
				if( xDown < viewWidth/3 )
				{
					//靠左点击
				}
				else if(xDown > viewWidth/3 && xDown < viewWidth * 2 /3)
				{
					//中间点击
				}
				else
				{
					//靠右点击
				}
			}
			endX = (int) event.getX();
			endY = (int) event.getY();
			//如果挪动的范围很小，则判定为单击   单击换背景图片
			if(touchView!=null&& Math.abs(endX - startX)<CLICKRANGE&& Math.abs(endY - startY)<CLICKRANGE){
				clickView = touchView;
				if(hasView(endX,endY)){//如果点击位置已经有了
					if ("L".equals(((PictureTagView)touchView).getDirection())){
						Log.i("----direct---L",((PictureTagView)touchView).getDirection());
						((PictureTagView)touchView).directionChange(PictureTagView.Direction.Right);
//						((TextView)((PictureTagView)touchView).findViewById(R.id.tv_direction)).setText("R");
					}else if ("R".equals(((PictureTagView)touchView).getDirection())){
						Log.i("----direct---R",((PictureTagView)touchView).getDirection());
						((PictureTagView)touchView).directionChange(PictureTagView.Direction.Left);
//						((TextView)((PictureTagView)touchView).findViewById(R.id.tv_direction)).setText("L");
					Log.i("---direct----",((PictureTagView)touchView).getDirection());
					}
				} else {
					showPopup();
				}
			}
//			touchView = null;
			break;

		}
		return true;
	}
	public void showPopup(){
//		pop = new PopupWindow((Activity)context);
//		View view = LayoutInflater.from(context).inflate(R.layout.layout_share, null);
//		pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//		pop.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
//		pop.setBackgroundDrawable(new BitmapDrawable());
//		pop.setFocusable(true);
//		pop.setOutsideTouchable(true);
//		pop.setContentView(view);
//		pop.showAtLocation(view, Gravity.FILL, 0, 0);
//		bt_finish = (Button) view.findViewById(R.id.bt_finish);
//		bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
//		tv_brand = (TextView) view .findViewById(R.id.tv_brand);
//		et_bask = (EditText) view .findViewById(R.id.et_bask);
//		bt_cancel.setOnClickListener(this);
//		bt_finish.setOnClickListener(this);
//		tv_brand.setOnClickListener(this);
		Intent intent=new Intent(context, ChooseBrandActivity.class);
		((Activity)context).startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){

		}
	}
	/*
	* ChooseBrandActivity中选择完品牌 用eventbus 发送到Sign_T_Activity中 然后发送到Sign_T_Activity中调用setText 把值传过来
	* */
	public void setText(String string, AttentionBrand data){
		//在SignActivity中给品牌赋值
		tv_brand.setText(string);
		brand=data;
	}
	/*
* ChooseBrandActivity中选择完品牌、位置、推荐、活动bi标签 用eventbus 发送到Sign_T_Activity中 然后发送到Sign_T_Activity中调用addData 把值传过来
* */
	public void addData(String brandId, String name, String type){
		if(hasView(startX,startY)){//有view 就不添加到集合里
			startTouchViewLeft = touchView.getLeft();
			startTouchViewTop = touchView.getTop();

		} else {
			if (!StringUtils.isEmptyString(name)){
				addItem(startX, startY,name,brandId,toWhere,type);
				//完成的时候  将标签信息对象 返给SignActivity
			}

		}
	}

	private void addItem(int x, int y, String share, String brandId, int toWhere, String type){
		View view = null;
		LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		if(x>getWidth()*0.5){//Right是指 点在右边
			//第一次点击添加标签是  PictureTagView.Direction.Measure 让TagView自己测量方向   toWhere长按标签 向左滑动 还是向右
			view = new PictureTagView(getContext(), PictureTagView.Direction.Right,share,type);
			view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
			int w = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0,
					View.MeasureSpec.UNSPECIFIED);
			view.measure(w, h);
			height = view.getMeasuredHeight();
			width = view.getMeasuredWidth();
			//标签在右面 点击位置 x-标签宽度   右边的标签并不是以圆点开始的  而是以左边的wei
			params.leftMargin = x -width+10;
			((PictureTagView)view).addText(share);
			if (brandId!=null){
				if (!"0".equals(brandId)){//品牌id 默认为int 类型的  默认值为0  如果为0 证明是自定义的品牌  就不给品牌id 赋值
				((PictureTagView)view).addBrandId(brandId+"");
				}

			}
			//
			params.topMargin = y-height/2;
		}else{
			params.leftMargin = x-10;
			view = new PictureTagView(getContext(), PictureTagView.Direction.Left,share,type);
			view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
			int w = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0,
					View.MeasureSpec.UNSPECIFIED);
			view.measure(w, h);
			height = view.getMeasuredHeight();
			width = view.getMeasuredWidth();
			((PictureTagView)view).addText(share);
			if (brandId!=null){
				if ("0".equals(brandId)){//品牌id 默认为int 类型的  默认值为0  如果为0 证明是自定义的品牌  就不给品牌id 赋值
					((PictureTagView)view).addBrandId(brandId+"");
				}

			}
		params.topMargin = y-height/2;
		}
		//上下位置在视图内
		if(params.topMargin<=0){
			params.topMargin =0;
		} else if((params.topMargin+height)>getHeight()){
			params.topMargin = getHeight() -height;
		}
		if(params.leftMargin<=0){
			params.leftMargin=0;
		}
		if (params.rightMargin>=getWidth()){
			params.rightMargin=getWidth();
		}
		this.addView(view, params);
	}
	private void moveView(int x,int y){
		if(touchView == null) return;
		LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.leftMargin = x - startX + startTouchViewLeft;
		params.topMargin = y - startY + startTouchViewTop;
		//限制子控件移动必须在视图范围内
		if(params.leftMargin<0||(params.leftMargin+touchView.getWidth())>getWidth()){
			params.leftMargin = touchView.getLeft();
		}
		if(params.topMargin<0||(params.topMargin+touchView.getHeight())>getHeight()){
			params.topMargin = touchView.getTop();
		}
		touchView.setLayoutParams(params);
        //移动时将是向左滑动 还是向右滑动 赋值PictureTagView
		((PictureTagView)touchView).setToWhere(toWhere);
	}

	private boolean hasView(int x,int y){
		//循环获取子view，判断xy是否在子view上，即判断是否按住了子view
		for(int index = 0; index < this.getChildCount(); index ++){
			View view = this.getChildAt(index);
			int left = (int) view.getX();
			int top = (int) view.getY();
			int right = view.getRight();
			int bottom = view.getBottom();
			((PictureTagView)view).getShare();
//			Toast.makeText(context, "已经有的---"+((PictureTagView)view).getShare()+"-x-"+left+"--y--"+top, Toast.LENGTH_SHORT).show();
			Rect rect = new Rect(left, top, right, bottom);
			boolean contains = rect.contains(x, y);
			//如果是与子view重叠则返回真,表示已经有了view不需要添加新view了
			if(contains){
				touchView = view;
				touchView.bringToFront();
				return true;
			}

		}

		touchView = null;
		return false;
	}
	/* 判断是否有长按动作发生
* @param lastX 按下时X坐标
* @param lastY 按下时Y坐标
* @param thisX 移动时X坐标
* @param thisY 移动时Y坐标
* @param lastDownTime 按下时间
* @param thisEventTime 移动时间
* @param longPressTime 判断长按时间的阀值
*/
	private boolean isLongPressed(float lastX,float lastY,
								  float thisX,float thisY,
								  long lastDownTime,long thisEventTime,
								  long longPressTime){
		float offsetX = Math.abs(thisX - lastX);
		float offsetY = Math.abs(thisY - lastY);
		long intervalTime = thisEventTime - lastDownTime;
		if(offsetX <= 10 && offsetY <= 10 && intervalTime >= longPressTime){
			return true;
		}
		return false;
	}
}
