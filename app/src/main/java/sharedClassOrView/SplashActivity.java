package sharedClassOrView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.luckdust.MainTestAc;
import com.luckdust.R;

import sharedClassOrView.FirstRun.GuideActivity;

/**
 * Created by HP on 2015/5/30.
 */
public class SplashActivity extends Activity {
    //页面 公用的控件 集锦
    Context context;
    Handler handler;
    Bitmap bm ;     //背景图片
    public static SplashActivity instance = null;   //当前静态的instance
    public static boolean beCalled = false;   //当前静态的 记录是否被关闭
    LinearLayout allLL;
    ImageView topIv,bottomIv;   //上下的 图片控件

    int waitTime=2800;   //闪屏时长
    boolean firstRun;   //是否首次运行
    int isTimeOut=0;    //本页的时间是否走完 ,+1 +1 ；>1时执行关闭闪屏
    String ip= GlobalParameter.IP;
    String url=ip+GlobalParameter.LOGIN_PATH;//服务器数据处理接口的地址
    //特有的 控件
    boolean auto_login,verfctionPass=false;//定义String 用户名 密码，boolean checkbox的勾选状态 true为勾选
    String name=null;//记录 数据库返回的 登录人的姓名
    String user_name=null,pass_word=null,rightUserName,rightPwd;    //记录正确的用户名密码
    int userId,DepartmentId;
    int authority=0;//用户权限：0 普通权限，1 管理权限，2 高级管理权限，依次类推
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* // 隐藏状态栏，使内容全屏显示(必须要在setContentView方法执行前执行)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        // 使屏幕不显示标题栏(必须要在setContentView方法执行前执行)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.s_view_splash_lay_out);
        instance = this;    //
        context=SplashActivity.this;
        loadLocalData(this);    //加载本地数据

        allLL= (LinearLayout) findViewById(R.id.splash_layout); //获取总布局
        topIv= (ImageView) findViewById(R.id.freash_back_top);
        bottomIv= (ImageView) findViewById(R.id.freash_back_bottom);


        bm=GlobalParameter.readBitMap(context,R.drawable.freash_back_bottom); //freash_back_bottom
        bottomIv.getLayoutParams().height= (int) (GlobalParameter.getBitmapFullWidthH(context,bm));   //给图片控件设置高度
        bottomIv.setImageBitmap(bm);

        String picPath=GlobalParameter.ALBUM_PATH+GlobalParameter.SPLASH_NEW_NAME;     //新地址
        if (GlobalParameter.fileIsExists(GlobalParameter.ALBUM_PATH,GlobalParameter.SPLASH_NEW_NAME)) {    //存在新的闪屏 则显示 新的 不存在就显示旧的
            bm = GlobalParameter.lowMemoryDecodeFile(picPath);
        }else {
            /*bm = GlobalParameter.readBitMap(context, R.drawable.freash_back);*/
        }
       /* bm= GlobalParameter.centerSquareScaleBitmap(bm,
                GlobalParameter.getScreenWidth(context), GlobalParameter.getScreenHeight(context)); //根据屏幕比例截取*/
        bm=GlobalParameter.readBitMap(context,R.drawable.freash_back);
        topIv.setImageBitmap(bm);    //顶部 设置图片

       /* BitmapDrawable bd = new BitmapDrawable(context.getResources(),bm);  //总背景
        allLL.setBackgroundDrawable(bd);*/

        //闪屏的核心代码
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               if (false) {  //firstRun
                   Intent intent = new Intent(context, GuideActivity.class);
                   startActivity(intent);  //打开对应的菜单页
               } else {
                    Intent intent = new Intent(context, MainTestAc.class);
                    startActivity(intent);//打开对应的菜单页
               }
               finish();    //本地时间流完
           }

       }, waitTime);

//创建UI线程中的HandLer对象
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (context==null)
                    return;
                if (msg.what == GlobalParameter.GET_DATA) {    //验证通过，更新登录按钮的 显示
                   /* Bitmap bm= (Bitmap) msg.obj;
                    BitmapDrawable bd = new BitmapDrawable(context.getResources(),bm);
                    allLL.setBackgroundDrawable(bd);
                    System.out.println("收到 bitmap");*/
                }
            }
        };
      /*  HttpAssist newR =
                new HttpAssist(handler,GlobalParameter.GET_SPLASH_PIC);     //创建runnable接口实例
        new Thread(newR).start();   //开启子线程 后台获取闪屏图片*/
    }
    @Override
    public void finish(){
        isTimeOut++;
        if (isTimeOut<=0)
            return;     //不大于1时 直接返回不执行 关闭操作
        try {
                bm.recycle();   //bm!=null&&bm.isRecycled()
                bm=null;
        }catch (Exception e){}
        super.finish();
        System.gc();  //提醒系统及时回收 没有改语句 Bitmap.recycle()无效
    }
    //加载本地数据的方法
    public void loadLocalData(Context context){
        SharedPreferences sp=context.getSharedPreferences(context.getPackageName()+"_ld_"+GlobalParameter.getVersionCode(context), MODE_PRIVATE);
        auto_login=sp.getBoolean("auto_login", false);
        user_name=sp.getString("user_name", "");
        pass_word=sp.getString("pass_word", "");
        rightUserName=sp.getString("right_user_name", "-1");    //正确的 用户名密码 离线验证
        rightPwd=sp.getString("right_pwd", "");
        verfctionPass=sp.getBoolean("isLandSuccess", false);
        name = sp.getString("Name", "");
        userId = sp.getInt("UserId", -1);    //正确的 用户名密码 离线验证
        authority=sp.getInt("authority", -1);
        DepartmentId=sp.getInt("isPayModel", -1);
        firstRun=sp.getBoolean(GlobalParameter.getVersion(context) + "FirstRun", true);
    }
}
