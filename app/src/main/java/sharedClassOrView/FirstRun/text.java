package sharedClassOrView.FirstRun;


/**
 * Created by yunzhu on 2015/7/1.
 */
public class text {
    public static void main(String[] args){ //调试用程序
       /* String masterPassword = "niuniu025";
        String originalText = "9966558899";
        String encryptingCode = null;
        try {
            encryptingCode = MyAESEncrypt.encrypt(masterPassword, originalText);
            System.out.println("加密结果为 " + encryptingCode);
            String decryptingCode = MyAESEncrypt.decrypt(masterPassword, encryptingCode);
            System.out.println("解密结果为 " + decryptingCode);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("加密异常 " );
        }*/
      /*  String url="http://192.168.1.198:8080/Credit/app/circles/circle.html";
        String[] strArray=url.split("//")[1].split("/",2)[1].split("/");
        String path="";
        for (int i=0;i<strArray.length-1;i++){
            path=path+"/"+strArray[i];
        }
        System.out.println("path: "+path );*/
    }
   /* [{"CompanyName":"李宁服装","CustomerName":"李宁","ClientsVisitId":7,"VisitTime":"2015-06-19T08:38:01","Theme":"需求分析","Content":"初七","State":1,"CustomerId":20,"CreateUser":9,"CreateTime":"2015-06-23T04:26:05","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":13,"VisitTime":"2015-06-24T10:17:01","Theme":"Gfcg","Content":"Fggd","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-24T10:17:18","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":14,"VisitTime":"2015-06-24T10:29:01","Theme":"Ggbg","Content":"Gffv","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-24T10:29:17","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":15,"VisitTime":"2015-06-24T10:29:01","Theme":"Bhh","Content":"Hgg","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-24T10:29:42","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":25,"VisitTime":"2015-06-30T19:23:01","Theme":"你","Content":"你","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-30T04:24:15","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"华一伟业餐饮公司","CustomerName":"张芳","ClientsVisitId":9,"VisitTime":"2015-07-29T06:10:01","Theme":"gg4嘿嘿","Content":"dsds世群","State":1,"CustomerId":19,"CreateUser":9,"CreateTime":"2015-06-30T04:34:19","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":26,"VisitTime":"2015-08-01T09:30:01","Theme":"fwefewf","Content":"fefewf","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-30T09:30:34","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":27,"VisitTime":"2015-07-01T09:33:01","Theme":"23","Content":"fefaew","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-30T09:33:34","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":28,"VisitTime":"2015-07-01T09:38:01","Theme":"fwef","Content":"fwefw","State":1,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-30T09:38:38","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0},{"CompanyName":"美嘉时尚","CustomerName":"李党","ClientsVisitId":11,"VisitTime":"2015-05-23T03:27:01","Theme":"888dad","Content":"af～","State":2,"CustomerId":18,"CreateUser":9,"CreateTime":"2015-06-30T09:53:17","UpdateUser":null,"UpdateTime":null,"DelUser":null,"DelTime":null,"DelFlag":0}]
   *
   *# Use ${idea.home.path} macro to specify location relative to IDE installation home.
# Use ${xxx} where xxx is any Java property (including defined in previous lines of this file) to refer to its value.
# Note for Windows users: please make sure you're using forward slashes (e.g. c:/idea/system).

#---------------------------------------------------------------------
# Uncomment this option if you want to customize path to IDE config folder. Make sure you're using forward slashes.
#---------------------------------------------------------------------
 idea.config.path=D:/.AndroidStudio1.2/config

#---------------------------------------------------------------------
# Uncomment this option if you want to customize path to IDE system folder. Make sure you're using forward slashes.
#---------------------------------------------------------------------
 idea.system.path=D:/.AndroidStudio1.2/system

#---------------------------------------------------------------------
# Uncomment this option if you want to customize path to user installed plugins folder. Make sure you're using forward slashes.
#---------------------------------------------------------------------
 idea.plugins.path=D:/Program Files/Android/Android Studio/plugins

#---------------------------------------------------------------------
# Uncomment this option if you want to customize path to IDE logs folder. Make sure you're using forward slashes.
#---------------------------------------------------------------------
 idea.log.path=D:/.AndroidStudio1.2/log

#---------------------------------------------------------------------
# Maximum file size (kilobytes) IDE should provide code assistance for.
# The larger file is the slower its editor works and higher overall system memory requirements are
# if code assistance is enabled. Remove this property or set to very large number if you need
# code assistance for any files available regardless their size.
#---------------------------------------------------------------------
idea.max.intellisense.filesize=2500

#---------------------------------------------------------------------
# This option controls console cyclic buffer: keeps the console output size not higher than the specified buffer size (Kb).
# Older lines are deleted. In order to disable cycle buffer use idea.cycle.buffer.size=disabled
#---------------------------------------------------------------------
idea.cycle.buffer.size=1024

#---------------------------------------------------------------------
# Configure if a special launcher should be used when running processes from within IDE.
# Using Launcher enables "soft exit" and "thread dump" features
#---------------------------------------------------------------------
idea.no.launcher=false

#---------------------------------------------------------------------
# To avoid too long classpath
#---------------------------------------------------------------------
idea.dynamic.classpath=false

#---------------------------------------------------------------------
# Uncomment this property to prevent IDE from throwing ProcessCanceledException when user activity
# detected. This option is only useful for plugin developers, while debugging PSI related activities
# performed in background error analysis thread.
# DO NOT UNCOMMENT THIS UNLESS YOU'RE DEBUGGING IDE ITSELF. Significant slowdowns and lockups will happen otherwise.
#---------------------------------------------------------------------
#idea.ProcessCanceledException=disabled

#---------------------------------------------------------------------
# There are two possible values of idea.popup.weight property: "heavy" and "medium".
# If you have WM configured as "Focus follows mouse with Auto Raise" then you have to
# set this property to "medium". It prevents problems with popup menus on some
# configurations.
#---------------------------------------------------------------------
idea.popup.weight=heavy

#---------------------------------------------------------------------
# Use default anti-aliasing in system, i.e. override value of "Settings|Editor|Appearance|Use anti-aliased font"
# option. May be useful when using Windows Remote Desktop Connection for instance.
#---------------------------------------------------------------------
idea.use.default.antialiasing.in.editor=false

#---------------------------------------------------------------------
# Disabling this property may lead to visual glitches like blinking and fail to repaint
# on certain display adapter cards.
#---------------------------------------------------------------------
sun.java2d.noddraw=true

#---------------------------------------------------------------------
# Removing this property may lead to editor performance degradation under Windows.
#---------------------------------------------------------------------
sun.java2d.d3d=false

#---------------------------------------------------------------------
# Workaround for slow scrolling in JDK6
#---------------------------------------------------------------------
swing.bufferPerWindow=false

#---------------------------------------------------------------------
# Removing this property may lead to editor performance degradation under X Window.
#---------------------------------------------------------------------
sun.java2d.pmoffscreen=false

#---------------------------------------------------------------------
# Workaround to avoid long hangs while accessing clipboard under Mac OS X.
#---------------------------------------------------------------------
#ide.mac.useNativeClipboard=True

#---------------------------------------------------------------------
# Maximum size (kilobytes) IDEA will load for showing past file contents -
# in Show Diff or when calculating Digest Diff
#---------------------------------------------------------------------
#idea.max.vcs.loaded.size.kb=20480

#---------------------------------------------------------------------
# IDEA file chooser peeks inside directories to detect whether they contain a valid project
# (to mark such directories with a corresponding icon).
# Uncommenting the option prevents this behavior outside of user home directory.
#---------------------------------------------------------------------
#idea.chooser.lookup.for.project.dirs=false

#---------------------------------------------------------------------
# IDEA can copy library .jar files to prevent their locking.
# By default this behavior is enabled on Windows and disabled on other platforms.
# Uncomment this property to override.
#---------------------------------------------------------------------
# idea.jars.nocopy=false

#---------------------------------------------------------------------
# The VM option value to be used to start a JVM in debug mode.
# Some JREs define it in a different way (-XXdebug in Oracle VM)
#---------------------------------------------------------------------
idea.xdebug.key=-Xdebug

#-----------------------------------------------------------------------
# Change to 'enabled' if you want to receive instant visual notifications
# about fatal errors that happen to an IDE or plugins installed.
#-----------------------------------------------------------------------
idea.fatal.error.notification=disabled
disable.android.first.run=true


InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
 */
    /* if(newProgress==100){
                if(iamgeView!=null)
                iamgeView.setVisibility(View.GONE);
                //view.setVisibility(View.VISIBLE);

                //DroidGap.this.root.addView(view);

                System.out.println("加载完成");
                Animation translate_in=AnimationUtils.loadAnimation(DroidGap.this, R.drawable.translate_in);


                translate_in.setFillAfter(true);
                translate_in.setDuration(1000);
                translate_in.setDetachWallpaper(true);
            //  translate_in.
                view.setAnimation(translate_in);



    Animation translate_out=AnimationUtils.loadAnimation(DroidGap.this, R.drawable.translate_out);

    translate_out.setAnimationListener(new AnimationListener(){

        @Override
        public void onAnimationEnd(Animation animation) {
            if(null!=iamgeView){
                DroidGap.this.root.removeView(iamgeView);
                iamgeView=null;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

    });
    translate_out.setFillAfter(true);
    translate_out.setDuration(1000);
    translate_out.setDetachWallpaper(true);
            //  translate_in.
    if(null!=iamgeView){
        iamgeView.setAnimation(translate_out);
    }
            }else{

                if(null==iamgeView){

                    iamgeView=new ImageView(DroidGap.this);

                    view.setDrawingCacheEnabled(true);
                    Bitmap bitmap=view.getDrawingCache();
                    if(null!=bitmap){
                        Bitmap b=   Bitmap.createBitmap(bitmap);
                        iamgeView.setImageBitmap(b);
                    }
                            DroidGap.this.root.addView(iamgeView);
                }
                }
            super.onProgressChanged(view, newProgress);*/

}
