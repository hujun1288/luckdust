package com.luckdust;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.VelocityTracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**用灯点亮的方式来实现随机分组 4 2分组、6 3分组、8 4分组；
 * 抽取时 （1）被点亮的灯先转两圈，灯点亮和熄灭时有渐变效果，用透明度动画实现
 * Created by jun on 2016/8/4.
 */
public class RotaryHasuoNewView extends SurfaceView implements Callback, Runnable {

    private SurfaceHolder mHolder;
    /**
     * 与SurfaceHolder绑定的Canvas
     */
    private Canvas mCanvas;
    /**
     * 用于绘制的线程
     */
    private Thread t;
    /**
     * 线程的控制开关
     */
    private boolean isRunning;
    /**画面的刷新时间间隔 单位 sm*/
    private int INTERVAL=30;
    /**渐变动画时间*/
    private final int PER_ANIM_TIME=300;
    private int perAnimTime=PER_ANIM_TIME;
    /**获取手指在屏幕上的滑动速度*/
    private VelocityTracker vt=null;
    /**
     * 抽奖的文字
     */
    private String[] mStrs = new String[] { "单反相机", "IPAD", "恭喜发财", "IPHONE",
            "妹子一只", "恭喜发财", "妹子一只", "恭喜发财"  };
    /**
     * 每个盘块的颜色
     */
    private int[] mColors = new int[] { 0xFFFFC300, 0xFFF17E01, 0xFFFFC300,
            0xFFF17E01, 0xFFFFC300, 0xFFF17E01 , 0xFFFFC300, 0xFFF17E01};
    /**
     * 与文字对应的图片
     */
    private int[] mImgs = new int[] { R.drawable.danfan, R.drawable.ipad,
            R.drawable.f040, R.drawable.iphone, R.drawable.meizi, R.drawable.f040, R.drawable.meizi, R.drawable.f040 };

    /**
     * 与文字对应图片的bitmap数组
     */
    private Bitmap[] mImgsBitmap;
    /**
     * 盘块的个数
     */
    private int mItemCount = 8;

    /**
     * 绘制盘块的范围
     */
    private RectF mRange = new RectF();
    /**
     * 圆的直径
     */
    private int mRadius;
    /**
     * 绘制盘快的画笔
     */
    private Paint mArcPaint;

    /**
     * 绘制文字的画笔
     */
    private Paint mTextPaint;

    /**
     * 滚动的速度
     */
    private double mSpeed;
    private volatile float mStartAngle = 0;
    /**
     * 是否点击了停止
     */
    private boolean isShouldEnd;

    /**
     * 控件的中心位置
     */
    private int mCenter;
    /**
     * 控件的padding，这里我们认为4个padding的值一致，以paddingleft为标准
     */
    private int mPadding;

    /**
     * 背景图的bitmap
     */
    private Bitmap mBgBitmap = BitmapFactory.decodeResource(getResources(),
            R.drawable.bg2); //bg2
    /**
     * 背景图的bitmap
     */
    private Bitmap mTopBitmap = BitmapFactory.decodeResource(getResources(),
            R.drawable.diraction);
    /**
     * 文字的大小
     */
    private float mTextSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());

    public RotaryHasuoNewView(Context context) {
        this(context, null);
    }

    public RotaryHasuoNewView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHolder = getHolder();
        mHolder.addCallback(this);

        // setZOrderOnTop(true);// 设置画布 背景透明
        // mHolder.setFormat(PixelFormat.TRANSLUCENT);

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

    }
    /**
     * 设置控件为正方形
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        // 获取圆形的直径
        mRadius = width - getPaddingLeft() - getPaddingRight();
        // padding值
        mPadding = getPaddingLeft();
        // 中心点
        mCenter = width / 2;
        setMeasuredDimension(width, width);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 初始化绘制圆弧的画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);
        // 初始化绘制文字的画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(0xFFffffff);
        mTextPaint.setTextSize(mTextSize);
        // 圆弧的绘制范围
        mRange = new RectF(getPaddingLeft(), getPaddingLeft(), mRadius
                + getPaddingLeft(), mRadius + getPaddingLeft());

        // 初始化图片
        mImgsBitmap = new Bitmap[mItemCount];
        for (int i = 0; i < mItemCount; i++) {
            mImgsBitmap[i] = BitmapFactory.decodeResource(getResources(),
                    mImgs[i]);
        }

        // 开启线程
        isRunning = true;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        // 通知关闭线程
        isRunning = false;
    }

    @Override
    public void run() {
        // 不断的进行draw
        while (isRunning) {  //||isTouching
            long start = System.currentTimeMillis();
            draw();
            long end = System.currentTimeMillis();
            try {
                if (end - start < INTERVAL) { //50ms执行一次 降低或者提高sleep并不能省下很多资源（主要耗CPU），
                    Thread.sleep(INTERVAL - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw(){
        try {
            // 获得canvas
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                // 绘制背景图
                drawBg();
//                drawTopIcon(mStartAngle);
                /**
                 * 绘制每个块块，每个块块上的文本，每个块块上的图片
                 */
                float tmpAngle = 0;  //mStartAngle
                float sweepAngle = (float) (360 / mItemCount);
                if (isStartAnim){
                    startAnim(isOpen);
                    for(int i=0;i<selectList.size()&&curArcNumb-mItemCount>=selectList.get(i);i++){
                        if (curArcNumb-mItemCount==selectList.get(i)&&alpha<255)
                            continue;
                        drawArc(selectList.get(i) * sweepAngle - 90 - sweepAngle / 2, 255);
//                        System.out.println("i:"+i+" curArcNumb:"+curArcNumb+" curArcNumb-mItemCount:"+(curArcNumb-mItemCount));
                    }
                }
//                for (int i = 0; i < mItemCount; i++) {
//                    // 绘制快快
//                    mArcPaint.setColor(mColors[i]);
//                    mCanvas.drawArc(mRange, tmpAngle, 360/mItemCount, true, mArcPaint);
//
//                    // 绘制文本
//                    drawText(tmpAngle, sweepAngle, mStrs[i]);
//                    // 绘制Icon
//                    drawIcon(tmpAngle, i);
//
//                    tmpAngle += sweepAngle;
//                }

                // 如果mSpeed不等于0，则相当于在滚动
                mStartAngle += mSpeed;  //画面刷新一次执行
//                Log.d("TAG","mStartAngle:"+ mStartAngle+";mSpeed:"+mSpeed);

                // 点击停止时，设置mSpeed为递减，为0值转盘停止
                if (isShouldEnd) {
                    mSpeed -= (mSpeed/(2.0*INTERVAL));  //15*INTERVAL/1000.0
                }
                if (isOpen&&alpha==255||!isOpen&&alpha==0||!isStartAnim) { //要停止时 的误差范围不然会无限小 (mSpeed>=0&&mSpeed <= 0.06) || (mSpeed<=0&&mSpeed>=-0.06)
                    isOpen = alpha==0;
                    perAnimTime += perAnimTime/(2.0*20);
                    if (curArcNumb>=allCircle) {
                        mSpeed = 0;
                        isShouldEnd = false;
                        isRunning = false; //不再在子线程内无线循环，让线程自己停止，下次点击时重新开启线程
                        isStartAnim=false;
                        curArcNumb=0;
                        System.out.println("perAnimTime"+perAnimTime);
                        perAnimTime=PER_ANIM_TIME;
                    }else if (isStartAnim&&curArcNumb<allCircle&&(alpha==0||curArcNumb>=mItemCount&selectList.contains(curArcNumb-mItemCount))){
                        if (alpha==255) {
                            alpha=0;
                            isOpen = true;
                            Thread.sleep(perAnimTime);
                        }
                        curArcNumb++;
                    }
                }
                // 根据当前旋转的mStartAngle计算当前滚动到的区域
                calInExactArea(mStartAngle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }

    }

    /**
     * 根据当前旋转的mStartAngle计算当前滚动到的区域 绘制背景，不重要，完全为了美观
     */
    private void drawBg() {
        mCanvas.drawColor(0xFFFFFFFF);
        mCanvas.drawBitmap(mBgBitmap, null, new Rect(mPadding / 2,
                mPadding / 2, getMeasuredWidth() - mPadding / 2,
                getMeasuredWidth() - mPadding / 2), null);
    }

    /**
     * 根据当前旋转的mStartAngle计算当前滚动到的区域
     *
     * @param startAngle
     */
    public void calInExactArea(float startAngle) {
        // 让指针从水平向右开始计算
        float rotate = startAngle + 90;
        rotate %= 360.0;
        for (int i = 0; i < mItemCount; i++) {
            // 每个的中奖范围
            float from = 360 - (i + 1) * (360 / mItemCount);
            float to = from + 360 - (i) * (360 / mItemCount);

            if ((rotate > from) && (rotate < to)) {
//                Log.d("TAG", mStrs[i]);
                return;
            }
        }
    }

    /**
     * 绘制图片
     * @param startAngle
     * @param i
     */
    private void drawIcon(float startAngle, int i) {
        // 设置图片的宽度为直径的1/8
        int imgWidth = mRadius / 8;

        float angle = (float) ((360/mItemCount/2 + startAngle) * (Math.PI / 180));

        int x = (int) (mCenter + mRadius / 2 / 2 * Math.cos(angle));
        int y = (int) (mCenter + mRadius / 2 / 2 * Math.sin(angle));

        // 确定绘制图片的位置
        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth
                / 2, y + imgWidth / 2);

        mCanvas.drawBitmap(mImgsBitmap[i], null, rect, null);
    }
    /**
     * 绘制文本
     * @param startAngle
     * @param sweepAngle
     * @param string
     */
    private void drawText(float startAngle, float sweepAngle, String string) {
        Path path = new Path();
        path.addArc(mRange, startAngle, sweepAngle);
        float textWidth = mTextPaint.measureText(string);
        // 利用水平偏移让文字居中
        float hOffset = (float) (mRadius * Math.PI / mItemCount / 2 - textWidth / 2);// 水平偏移
        float vOffset = mRadius / 2 / 6;// 垂直偏移
        mCanvas.drawTextOnPath(string, path, hOffset, vOffset, mTextPaint);
    }

    /**
     * 绘制上部旋转的图片
     * @param startAngle
     */
    private void drawTopIcon(float startAngle) {
        // 设置图片的宽度
        int imgWidth = mTopBitmap.getWidth();
        //画布旋转后再添加图片，之前在该画布上添加的背景图片，不会跟着旋转
        mCanvas.rotate(startAngle,mCenter,mCenter);
        mCanvas.drawBitmap(mTopBitmap, mCenter - imgWidth / 2, mCenter - imgWidth / 2, null);
    }
    /**
     * 绘制顶部图片下的 扇形颜色 传入 起始角度、角度区域、开/关灯标记
     * @param startAngle
     */
    private int alpha=0;
    boolean isOpen=true;
    private int allCircle;  //mItemCount*2
    private int curArcNumb=0;
    private boolean isStartAnim=false;
    private ArrayList<Integer> selectList=new ArrayList<Integer>();

    private void startAnim(final boolean isOpen) {
        float sweepAngle = (float) (360 / mItemCount);
        // 绘制快快
        swichLight(curArcNumb * sweepAngle - 90 - sweepAngle / 2, isOpen);

    }
    /**开、关灯（修改一个扇形区域的透明度）*/
    private void swichLight(float tmpAngle,boolean isOpen){
        int addOrPlas = isOpen ? 1 : -1;
        alpha = (int) (alpha + addOrPlas * INTERVAL * 255.0 / perAnimTime); //渐变时间
        if (alpha > 255)
            alpha = 255;
        else if (alpha < 0)
            alpha = 0;

//        Log.d("TAG", "alpha:" + alpha);
        if (curArcNumb>mItemCount-1&&curArcNumb==allCircle)
            return;
       drawArc(tmpAngle,alpha);
    }
    /**根据当前角度。透明度绘制圆弧*/
    private void drawArc(float tmpAngle,int alpha){
        mArcPaint.setColor(0xFFFFC300);
        mArcPaint.setAlpha(alpha);
        mCanvas.drawArc(mRange, tmpAngle, 360 / mItemCount, true, mArcPaint);
    }
    /**触摸事件*/
    private boolean isTouching=false;   //是否手动旋转转盘
    private float mLastMotionX;
    private float mLastMotionY;
    float tmpAngle=0;
    public   boolean onTouchEvent(MotionEvent event){
        int action=event.getAction();
        final float x = event.getX();
        final float y = event.getY();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                mLastMotionY = y;

                isTouching=true;    //手动旋转转盘 线程开启刷新VIEW
                if (t.getState().equals(Thread.State.TERMINATED)) {
                    t = new Thread(this);
                    t.start();
                }

                if(vt==null){
                    //初始化velocityTracker的对象 vt 用来监测motionevent的动作
                    vt=VelocityTracker.obtain();
                }else{
                    vt.clear();
                }
                vt.addMovement(event);
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = (int)(x-mLastMotionX); //x方向的移动距离
                int deltaY = (int)(y-mLastMotionY);
                int direct=(y-mCenter)/(x-mCenter)-(mLastMotionY-mCenter)/(mLastMotionX-mCenter)>=0?1:-1; //转动方向

                float a=(float)Math.sqrt(Math.pow((mLastMotionX - mCenter),2)+Math.pow((mLastMotionY-mCenter),2));
                float b=(float)Math.sqrt(Math.pow((x - mCenter),2)+Math.pow((y-mCenter),2));
                float c=(float)Math.sqrt(Math.pow((x - mLastMotionX), 2) + Math.pow((y - mLastMotionY), 2));
                float cosAngle=(float)(Math.pow(b,2)+Math.pow(a,2)-Math.pow(c,2))/(2*b*a);
                tmpAngle=(float)(Math.acos(cosAngle)*180/Math.PI)*direct;
                mStartAngle += tmpAngle;
//                Log.d("TAG","a:"+ a+";b:"+b+";c:"+c+";cos:"+cosAngle);
//                Log.d("TAG","tmpAngle:"+ tmpAngle+";mStartAngle:"+mStartAngle);

                mLastMotionX = x;
                mLastMotionY = y;

                vt.addMovement(event);
                //代表的是监测每1000毫秒手指移动的距离（像素）即m/px，这是用来控制vt的单位，若括号内为1，则代表1毫秒手指移动过的像素数即ms/px
                vt.computeCurrentVelocity(30);
            break;

            case MotionEvent.ACTION_UP:
                float vX=vt.getXVelocity();
                float vY=vt.getYVelocity();

                if (vt != null) {
                    vt.recycle();
                    vt = null;
                }
                isTouching=false;
                if (tmpAngle>0&&tmpAngle > 0.02||(tmpAngle<0&&tmpAngle<-0.02)) {
                    mSpeed = tmpAngle * 10;
                    isRunning=true;
                    isShouldEnd = true;
                    if (t.getState().equals(Thread.State.TERMINATED)) {
                        t = new Thread(this);
                        t.start();
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            vt.recycle();
            break;
        }
        return true;
    }

    /**
     * 点击开始旋转
     *
     * @param luckyIndex
     */
    public void luckyStart(int luckyIndex) {
        selectList.clear();
        Random a=new Random();
        while (selectList.size()<mItemCount/2){
            int randomNum=a.nextInt(mItemCount);
            if (!selectList.contains(randomNum))
                 selectList.add(randomNum);
        }
        Collections.sort(selectList);
        allCircle =mItemCount+selectList.get(selectList.size()-1)+1;
        for (int m:selectList){
            Log.d("TAG","mo:"+m);
        }
        isRunning=true;
        isStartAnim=true;
        if (t.getState().equals(Thread.State.TERMINATED)) {
            t = new Thread(this);
            t.start();
        }
        // 每项角度大小
        float angle = (float) (360 / mItemCount);
        // 中奖角度范围（因为指针向上，所以水平第一项旋转到指针指向，需要旋转210-270；）
        float from = 270 - (luckyIndex + 1) * angle;
        float to = from + angle;
        // 停下来时旋转的距离
        float targetFrom = 4 * 360 + from;
        /**
         * <pre>
         *  (v1 + 0) * (v1+1) / 2 = target ;
         *  v1*v1 + v1 - 2target = 0 ;
         *  v1=-1+(1*1 + 8 *1 * target)/2;
         * </pre>
         */
        float v1 = (float) (Math.sqrt(1 * 1 + 8 * 1 * targetFrom) - 1) / 2;
        float targetTo = 4 * 360 + to;
        float v2 = (float) (Math.sqrt(1 * 1 + 8 * 1 * targetTo) - 1) / 2;

//        mSpeed = (float) (v1 + Math.random() * (v2 - v1));

        if(i<=0)
            i=12;
        --i;
        mSpeed=2; //speeds[i]
        Log.d("TAG", mSpeed+"");
        isShouldEnd = false;
    }

    int i=12;
    private double[] speeds={60,56,50,40,36,30,24,20,15,10,5,3};
    public void luckyEnd() {
        mStartAngle = 0;
        isShouldEnd = true;
    }

    public boolean isStart()
    {
        return mSpeed != 0;
    }

    public boolean isShouldEnd()
    {
        return isShouldEnd;
    }

}