package com.luckdust;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {

    ImageView arrow;
    Button startBtn;
    Handler handler;
    Context context;
    Message msg;

    float startDregree=0f;
    public static int START_ROTATION=456;   //开始旋转
    public static int STOP_ROTATION=457;    //停止旋转
    public static float MIN_DREGREE=1f;    //旋转度数

    View.OnClickListener myClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!startBtn.getText().toString().isEmpty()) {
                startBtn.setText("停止");
                  //模拟图片上传进度
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int sleepTime=1,countZ=4000,maxSleepTime=20;
                                for (int  longg=countZ;longg>0;longg--){  // startBtn.getText().toString().equals("停止")
                                    msg=handler.obtainMessage();
                                    msg.what=START_ROTATION;
                                    //handler.sendMessageDelayed(msg, 200); //一段时间后开始发送消息*//*
                                    handler.sendMessage(msg);

                                    //startDregree=rotateArrow(startDregree,MIN_DREGREE);
                                    if (sleepTime<21) { //sleep时间递增 不让其大于21
                                        sleepTime = (int) (20 * ((countZ / maxSleepTime) / (double) longg));
                                    }
                                    System.out.println("sleepTime:"+sleepTime+"  longg:"+longg);
                                    try {
                                        Thread.sleep(sleepTime);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }).start();

            }else {
                startBtn.setText("开始");
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        arrow= (ImageView) findViewById(R.id.arrowIm);
        startBtn= (Button) findViewById(R.id.startBTn);


        startBtn.setOnClickListener(myClickListener);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == START_ROTATION) {    //验证通过，更新登录按钮的 显示
                    startDregree=rotateArrow(startDregree,MIN_DREGREE);
                }
            }
        };
    }

    /**
     * 根据当前的状态来旋转箭头。点击一下按钮旋转180度
     */
    private void  rotateArrow() {
        float pivotX = 0;   // 绕 要旋转控件的 中间旋转 arrow.getWidth() / 2f
        float pivotY = arrow.getHeight()/ 2f;   // 绕 要旋转控件的 中间旋转 arrow.getHeight()/ 2f
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (!startBtn.getText().toString().equals("开始")) {
            fromDegrees = 180f;
            toDegrees = 360f;
        }else {
            fromDegrees = 0f;
            toDegrees = 180f;
        }

        /*if (currentStatus == STATUS_PULL_TO_REFRESH) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }*/
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(1);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }
    /**
     * 根据当前的状态来旋转箭头。点击一下 开始旋转
     */
    private float rotateArrow(float fromDegrees,float degress) {
        float pivotX = 0;   // 绕 要旋转控件的 中间旋转 arrow.getWidth() / 2f
        float pivotY = arrow.getHeight()/ 2f;   // 绕 要旋转控件的 中间旋转 arrow.getHeight()/ 2f
       //float fromDegrees = 0f;
        float toDegrees = fromDegrees+degress;
         if (toDegrees>=360f) {
            toDegrees=0f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(0);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
        /*arrow.setRotation(toDegrees);*/
        //System.out.println("fromDegrees:"+fromDegrees+"  toDegrees:"+toDegrees);
        return toDegrees;
    }
}
