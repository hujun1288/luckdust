package sharedClassOrView.FirstRun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luckdust.MainTestAc;
import com.luckdust.R;

import sharedClassOrView.GlobalParameter;


public class GuideActivity extends Activity {
    sharedClassOrView.FirstRun.ScrollLayout scrollayout;   //自定义的滑动切换布局的控件
    LinearLayout pointLayout,skipGuidLL;
    TextView curPagePoint;
    LinearLayout[] guideArray;
    Button accessAppBtn;   //进入应用的按钮
    Context context;
    private int layoutAllWidth;// 总布局的宽度

    Bitmap bm;  //便于释放内存

    private static final int MAX_OFFSET = 5;// 5个像素误差，滑动小于5个像素就没有动画
    private float downX;// 按下时的点
    private float downY;// 按下时的点
    private float distanceY;// 移动距离
    private float viewXdown;// 按下时View的位置
    private boolean firstRun=true; // 最后一次滑动的方向
    int currentscreen=0;    //当前的显示的布局 标记
    int lastscreen=-1;    // 上一个 的显示的布局 标记
    private float maxOffset = 0;// 最大的滑动距离
    public static int RIGHT_SLID=330;      //向右滑动
    public static int LEFT_SLID=320;    //向左滑动
    public static int INVALID_SLID=-1;    //无效 滑动
    private int slidDirection=INVALID_SLID;    //滑动方向标记 -1代表无效
    //点击事件
    View.OnClickListener myClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==accessAppBtn||v==skipGuidLL){
                accessApp();    //进入应用 并修改标记
            }
        }
    };
    //触摸事件
    View.OnTouchListener myTouchListener=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            scrollayout.onTouchEvent(event);    //执行本类的方法
            float x = event.getX();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = x;
                    downY=event.getY(); //记录按下时Y轴的位置
                    break;
                case MotionEvent.ACTION_MOVE:
                    float offset = (event.getX() - downX);  // 滑动距离
                    distanceY= event.getY()-downY;   // 滑动距离
                    viewXdown=offset;
                    if (!(scrollayout.getCurScreen()==0&&offset>=0||scrollayout.getCurScreen()==(scrollayout.getChildCount()-1)&&offset<=0)) {
                        float rate = (pointLayout.getChildAt(1).getX() - pointLayout.getChildAt(0).getX()) / layoutAllWidth;
                        int len = (int) (offset * -(rate) + pointLayout.getChildAt(scrollayout.getCurScreen()).getX() + 2);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) curPagePoint.getLayoutParams();// 获取当前蓝点的布局参数
                        params.leftMargin = len;// 设置左边距
                        curPagePoint.setLayoutParams(params);// 重新给蓝点设置布局参数
                    }

                    //float posX = viewXdown + offset;// 计算可能的位置
                    maxOffset = maxOffset > Math.abs(offset) ? maxOffset : Math
                            .abs(offset);
                    if (offset > layoutAllWidth/2) {   // 大于+屏幕宽度的一半
                        slidDirection=RIGHT_SLID;   //向右 滑动 有效
                    } else {    // 小于 -屏幕宽度的一半
                        if (offset < -layoutAllWidth/2) {
                            slidDirection = LEFT_SLID;   //向左 滑动 有效
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
//                    Log.e("TAG2", "mScroller.getCurrX:" + scrollayout.mScroller.getCurrX());
                    currentscreen=scrollayout.getCurScreen();
                    int len2 = (int) (pointLayout.getChildAt(currentscreen).getX()+2);
                    FrameLayout.LayoutParams params2 = (FrameLayout.LayoutParams) curPagePoint.getLayoutParams();// 获取当前蓝点的布局参数
                    params2.leftMargin = len2;// 设置左边距
                    curPagePoint.setLayoutParams(params2);// 重新给蓝点设置布局参数
                    if (currentscreen!=lastscreen){ //切换了
//
                        lastscreen=currentscreen;   //变更
                    }else { //没变的话 判断是否最后一页 最后一页的话 滑动 距离超过屏幕一半； 执行 进入应用的操作
                        //判断是否最后一页 且向右滑动有效slidDirection==LEFT_SLID
                        if ((currentscreen==pointLayout.getChildCount()-1)&& sharedClassOrView.FirstRun.ScrollLayout.curMoveSpeed<0&& sharedClassOrView.FirstRun.ScrollLayout.curMoveSpeed<-sharedClassOrView.FirstRun.ScrollLayout.SNAP_VELOCITY){
                           // accessApp();    //进入应用 并修改标记
                        }
                    }
                  /*  if ((currentscreen==pointLayout.getChildCount()-1)&&distanceY<-layoutAllWidth/3){
                       System.out.println("accessApp这是向上滑动触发的- -");  //
                    }*/
                    slidDirection = INVALID_SLID;   //滑动 无效
                    break;
                default:
                    break;
            }
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 使屏幕不显示标题栏(必须要在setContentView方法执行前执行)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scroll_layout);
        init();
    }

    private void init() {   //初始化当前视图
        context=GuideActivity.this;
        scrollayout= (sharedClassOrView.FirstRun.ScrollLayout) findViewById(R.id.scrollLayoutTest);   //获取自定义的滑动切换布局的控件
        pointLayout= (LinearLayout) findViewById(R.id.point_ll);
        curPagePoint= (TextView) findViewById(R.id.curPagePoint); //当前页的小点标识
        skipGuidLL= (LinearLayout) findViewById(R.id.skip_guid_ll); //跳过导航
        accessAppBtn= (Button) findViewById(R.id.access_app);   //进入应用的按钮

        int scrollCount=scrollayout.getChildCount();
        guideArray=new LinearLayout[scrollCount];   //获取有多少个页面
        for (int i=0;i<scrollayout.getChildCount();i++){
            guideArray[i]= (LinearLayout) scrollayout.getChildAt(i);
        }

        for (int i=0;i<scrollayout.getChildCount();i++){    //添加下方小点
            TextView pointTv=new TextView(context);
            pointTv.setTextColor(getResources().getColor(R.color.point_unselect));
            pointTv.setPadding(2,0,2,0);
            pointTv.setText("•");
            pointTv.setTextSize(getResources().getDimension(R.dimen.point_size)*0.4f);
            pointLayout.addView(pointTv);
        }   //设置完后设置第一个小点的颜色
//        ((TextView)pointLayout.getChildAt(0)).setTextColor(getResources().getColor(R.color.rela_white));
        accessAppBtn.setOnClickListener(myClickListener);
        skipGuidLL.setOnClickListener(myClickListener);
        scrollayout.setOnTouchListener(myTouchListener);

        layoutAllWidth=getScreenWidth(context); //获取屏幕宽度
        accessAppBtn.setWidth(layoutAllWidth / 2 );    //按钮宽度为屏幕的 1/2  + layoutAllWidth % 2
        int[] picReID=new int[]{R.drawable.guide01,R.drawable.guide02,R.drawable.guide03};  //数组

        for (int i=0;i<guideArray.length;i++) {    //循环设置图片
           // System.out.println("guid for 循环执行到："+(i+1)+"次");
            //用try catch 块包围住  显示图片
            try {
                bm= GlobalParameter.readBitMap(context, picReID[i]);
                bm=GlobalParameter.centerSquareScaleBitmap(bm, GlobalParameter.getScreenWidth(context),
                        GlobalParameter.getScreenHeight(context)-50);   //根据屏幕大小裁剪
                BitmapDrawable bd = new BitmapDrawable(this.getResources(), bm);
                guideArray[i].setBackgroundDrawable(bd);
            } catch (OutOfMemoryError e) {
                // 这里就是当内存泄露时 需要做的事情
                e.printStackTrace();
            }
        }
    }
    @Override
    public void finish(){
        super.finish();
    }
    //复写父类的onDestroy方法
    protected void onDestroy(){
        if (bm!=null&&!bm.isRecycled()) {  //判断
            bm.recycle();
            bm=null;
        }
        pointLayout=null;
        scrollayout=null;
        super.onDestroy();
        System.gc();  //提醒系统及时回收 没有改语句 Bitmap.recycle()无效
    }
    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }
    //保存本地数据的方法：
    public void saveLocalData(Context context){
        SharedPreferences sp=context.getSharedPreferences(context.getPackageName()+"_ld_"+GlobalParameter.getVersionCode(context), MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取SharedPreferences对象 获取他的Editor对象
        editor.putBoolean(GlobalParameter.getVersion(context) + "FirstRun", firstRun);
        editor.commit();//将数据保持到本地
    }
    private void accessApp(){
        firstRun=false; //更改首运标记
        saveLocalData(context);
        Intent intent=new Intent(context,MainTestAc.class);   //主页
        startActivity(intent);//打开对应的菜单页  slide_in_left
        //由右向左滑入的效果  fragment_slide_in_left 为自定义的动画
        overridePendingTransition(R.anim.fragment_slide_in_left,R.anim.fragment_slide_in_right);
        finish();   //关闭当前页面
    }
}
