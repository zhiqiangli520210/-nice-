package com.example.lzq.supperpictagview.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.example.lzq.supperpictagview.R;


/*
*
* 带左右方向
* */
public class PictureTagView extends RelativeLayout implements OnEditorActionListener {

    private Context context;
    private TextView tvPictureTagLabel;
    private TextView etPictureTagLabel;
    private View loTag;
    private View view;
    private ImageView iv_circle_r;
    private ImageView iv_circle_l;
    private TextView tv_direction;
    private TextView tv_type;


    public enum Direction {Left, Right,Measure}

    private Direction direction;
    private InputMethodManager imm;
    private static final int ViewWidth = 80;
    private static final int ViewHeight = 50;
    private String share;
    private String type;//标签类型
    private boolean isMesure;//是否让其自动测量左右
    private int toWhere;//向右滑动为1 向左滑动为2

    public PictureTagView(Context context) {
        super(context);

    }

    public PictureTagView(Context context, Direction direction, String share, String type) {
        super(context);
        this.context = context;
        this.direction = direction;
        this.share=share;
        this.type=type;
        initViews();
        init();
        initEvents();
    }
    public void addText(String text){
        tvPictureTagLabel.setText(text);
    }
    /*
    * 设置移动标签时 往哪个方向滑动
    * */
    public void setToWhere(int ee){
        //在PictureTagLayout 中  setToWhere  将滑动方向传回来
        toWhere=ee;
    }
    public void addBrandId(String brandId){
        etPictureTagLabel.setText(brandId);
    }
    /**
     * 初始化视图
     **/
    protected void initViews() {
        view= LayoutInflater.from(context).inflate(R.layout.picturetagview, this, true);
        tvPictureTagLabel = (TextView) view.findViewById(R.id.tvPictureTagLabel);
        etPictureTagLabel = (TextView) view.findViewById(R.id.etPictureTagLabel);
        tv_direction = (TextView) view.findViewById(R.id.tv_direction);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        iv_circle_l = (ImageView) view.findViewById(R.id.iv_circle_l);
        iv_circle_r = (ImageView) view.findViewById(R.id.iv_circle_r);
        tvPictureTagLabel.setText(share);
        loTag = findViewById(R.id.loTag);
        tv_type.setText(type);
        if (Direction.Left.equals(direction)){

            iv_circle_l.setVisibility(View.VISIBLE);
            iv_circle_r.setVisibility(View.GONE);
            if ("SITE".equals(type)){
                iv_circle_l.setBackgroundResource(R.drawable.iv_loc_tag);
            }else{
                iv_circle_l.setBackgroundResource(R.drawable.img_point);
            }
////            //加载动画
            Animation hyperspaceJumpAnimation =
                    AnimationUtils.loadAnimation(context, R.anim.circle);
//                //使用ImageView显示动画
            iv_circle_l.startAnimation(hyperspaceJumpAnimation);
            iv_circle_r.clearAnimation();
            tv_direction.setText("L");
            loTag.setBackgroundResource(R.drawable.bg_picturetagview_tagview_left);
        }else if (Direction.Right.equals(direction)){

            iv_circle_r.setVisibility(View.VISIBLE);
            iv_circle_l.setVisibility(View.GONE);
            if ("SITE".equals(type)){
                iv_circle_r.setBackgroundResource(R.drawable.iv_loc_tag);
            }else{
                iv_circle_r.setBackgroundResource(R.drawable.img_point);
            }
            //加载动画
            Animation hyperspaceJumpAnimation1 =
                    AnimationUtils.loadAnimation(context, R.anim.circle);
//                //使用ImageView显示动画
            iv_circle_r.startAnimation(hyperspaceJumpAnimation1);
            iv_circle_l.clearAnimation();
            tv_direction.setText("R");
            loTag.setBackgroundResource(R.drawable.bg_picturetagview_tagview_right);
        }
    }

    /**
     * 初始化
     **/
    protected void init() {
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        directionChange(direction);
    }
    public String getShare(){
        return tvPictureTagLabel.getText().toString();
    }
    public String getBrandId(){
        return etPictureTagLabel.getText().toString();
    }
    /*
    * tv_direction设置 标签的方向 L R   然后在获取标签时 直接调用getDirection 获取标签的方向
    * return  标签的方向
    * */
    public String getType(){
        return tv_type.getText().toString();
    }
    /*
   * tv_direction设置 标签的方向 L R   然后在获取标签时 直接调用getDirection 获取标签的方向
   * return  标签的方向
   * */
    public String getDirection(){
        return tv_direction.getText().toString();
    }
    /**
     * 初始化事件
     **/
    protected void initEvents() {
        etPictureTagLabel.setOnEditorActionListener(this);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return true;
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


    }

    public void directionChange(Direction direction) {
        switch (direction) {
            case Left:
                iv_circle_l.setVisibility(View.VISIBLE);
                iv_circle_r.setVisibility(View.GONE);
                if ("SITE".equals(type)){
                    iv_circle_l.setBackgroundResource(R.drawable.iv_loc_tag);
                }else{
                    iv_circle_l.setBackgroundResource(R.drawable.img_point);
                }
                //加载动画
                Animation hyperspaceJumpAnimation =
                        AnimationUtils.loadAnimation(context, R.anim.circle);
                //使用ImageView显示动画
                iv_circle_l.startAnimation(hyperspaceJumpAnimation);
                iv_circle_r.clearAnimation();

                    //将标签的方向赋值给tv_direction  然后在getDirection方法中返回 便于Sign_T_Activity里调用
                    tv_direction.setText("L");
                    loTag.setBackgroundResource(R.drawable.bg_picturetagview_tagview_left);
                break;
            case Right:
                iv_circle_r.setVisibility(View.VISIBLE);
                iv_circle_l.setVisibility(View.GONE);
                if ("SITE".equals(type)){
                    iv_circle_r.setBackgroundResource(R.drawable.iv_loc_tag);
                }else{
                    iv_circle_r.setBackgroundResource(R.drawable.img_point);
                }
                //加载动画
                Animation hyperspaceJumpAnimation1 =
                        AnimationUtils.loadAnimation(context, R.anim.circle);
                //使用ImageView显示动画
                iv_circle_r.startAnimation(hyperspaceJumpAnimation1);
                iv_circle_l.clearAnimation();
                    tv_direction.setText("R");
                    loTag.setBackgroundResource(R.drawable.bg_picturetagview_tagview_right);
                break;
        }
    }
    public static int getViewWidth(){
        return ViewWidth;
    }
    public static int getViewHeight(){
        return ViewHeight;
    }
}