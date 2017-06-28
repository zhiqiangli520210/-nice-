package com.example.lzq.supperpictagview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.lzq.supperpictagview.bean.SignBean;
import com.example.lzq.supperpictagview.event.LocTagEvent;
import com.example.lzq.supperpictagview.view.PictureTagLayout;
import com.example.lzq.supperpictagview.view.PictureTagView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static org.greenrobot.eventbus.ThreadMode.MAIN;

public class MainActivity extends Activity {

    private PictureTagLayout ptlayout;
    private LinearLayout ll_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        initView();
        setListener();

    }

    private void setListener() {
        ll_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击完成时 获取图片上所有标签信息对象集合
                List<SignBean> signBeanList = new ArrayList<SignBean>();
                for (int index = 0; index < ptlayout.getChildCount(); index++) {
                    View childView = ptlayout.getChildAt(index);
                    int w = View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
                    int h = View.MeasureSpec.makeMeasureSpec(0,
                            View.MeasureSpec.UNSPECIFIED);
                    childView.measure(w, h);
                    int signHeight = childView.getMeasuredHeight();
                    int signWidth = childView.getMeasuredWidth();
                    SignBean bean = new SignBean();
                    //这里需要注意 实际项目中 图片要按比例缩放  标签的位置也要是一个比例值  才能保证在不同尺寸手机上展示的位置不变

                    //图片   宽width   高heigh
                    //标签位置
//                    if (heigh>width) {//竖图
//                        if ("R".equals(((PictureTagView) childView).getDirection())){
//                           bean.setX((((PictureTagView) childView).getX()+signWidth-8)/ (scaleWidth));
//                            Log.i("---signWidth-完成--",((((PictureTagView) childView).getX()+signWidth-8)/ (scaleWidth))+"");
//                           bean.setY(((((PictureTagView) childView).getY())+signHeight/2) / screenWidth);
//                       } else{                     bean.setX((((PictureTagView) childView).getX()+8)/ (scaleWidth));
//                           bean.setY(((((PictureTagView) childView).getY())+signHeight/2) / screenWidth);
//                       }
//
//                    }else{//横图
//                        if ("R".equals(((PictureTagView) childView).getDirection())){
//                            bean.setX((((PictureTagView) childView).getX()+signWidth-8 )/ (screenWidth));
//                            Log.i("---signWidth-完成",((((PictureTagView) childView).getX()+signWidth-8)/ (screenWidth))+"");
//                            bean.setY(((((PictureTagView) childView).getY())+signHeight/2) / scaleHeight);
//                       } else{
//                            bean.setX((((PictureTagView) childView).getX()+8 )/ (screenWidth));
//                            bean.setY(((((PictureTagView) childView).getY())+signHeight/2) / scaleHeight);
//                        }
//                   }
//                    标签的x坐标
                    bean.setX((((PictureTagView) childView).getX()+8 ));
//                    标签的y坐标
                    bean.setY(((((PictureTagView) childView).getY())+signHeight/2));
                    //标签名字
                    bean.setDescribe(String.valueOf(((PictureTagView) childView).getShare()));
                    //标签id
                    bean.setBrandId(((PictureTagView) childView).getBrandId());
                    //标签方向
                    bean.setDirection(((PictureTagView) childView).getDirection());
                    //标签类型
                    bean.setType(((PictureTagView) childView).getType());
                    signBeanList.add(bean);
                }
                Log.i("获取图片中标签的所有信息-----",signBeanList.toString());
                finish();
            }
        });
    }

    private void initView() {
        ptlayout = ((PictureTagLayout) findViewById(R.id.ptlayout));
        ll_next = ((LinearLayout) findViewById(R.id.ll_next));

    }

    //用于接收位置、品牌、推荐、历史、活动标签
    @Subscribe(threadMode = MAIN) //注册一个在后台线程执行的方法,用于接收事件
    public void UserEvent(LocTagEvent event) {//参数必须是ClassEvent类型, 否则不会调用此方法
        String name = event.name;
        String type = event.type;
        ptlayout.addData("",name,type);
//        Toast.makeText(context,brandId+"---"+name+"--"+type,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
