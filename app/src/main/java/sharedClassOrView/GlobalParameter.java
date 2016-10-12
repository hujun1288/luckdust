package sharedClassOrView;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.luckdust.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yunzhu on 2015/6/4.
 */
public class GlobalParameter {
    //获取到的验证代号vc verfctionCode：0 账号不存在，1 账号存在 密码错误,2 验证通过；-1 连接服务器失败;-2 服务器处理失败
    public static int NO_USER=0;    //账号不存在
    public static int WRONG_PASS_WORD=1;    //账号存在 密码错误
    public static int PASS=2;   //验证通过，获取数据成功
    public static int INSERT_OK=3;  //新增      数据成功
    public static int UPDATE_OK=4;  //修改      数据成功
    public static int DELETE_OK=5;  //删除      数据成功
    public static int RESET_PWD_FAIL=6;  //修改 密码失败
    public static int UPLOAD_OK=7;  //上传文件 成功
    public static int UPLOAD_FAIL=8;  //上传文件 失败
    public static int SEND_OK=9;  //找回密码 模块 发送验证码 成功
    public static int ISEXIST;  //验证数据是否存在 得到服务器反馈 标记
    public static int NETWORK_UNUSE=11;  //网络连接不可用
    public static int UPLAOD_PROGRESS=12;  //上传进度

    public static int GET_DATA=13;  //获取数据成功
    public static int WEB_LOAD_TIMEOUT=14;  //网页打开超时 标识
    public static int POST_DONE=15;  //发帖成功
    public static int CURRENT_PAGE_UPDATE01=16;  //当前页 handler 更新UI线程 操作01

    public static int FAIL_CONN=-1;
    public static int SERVERS_ERROR=-2;

    public  static int SYSTEM_UPGRADE_CHECK=133;     //检查更新
    public  static int UPLOAD_PRO_AND_BILL_IMG=132;     //上传 产品和发票图片 的 标识
    public  static int UPLOAD_BILL_IMG=131;     //上传 发票图片 的 标识
    public  static int UPLOAD_STU_CARD_F_IMG=130;     //上传学生证正面 的 标识
    public  static int UPLOAD_STU_CARD_B_IMG=129;     //上传学生证反面 的 标识
    public  static int UPLOAD_WITH_STU_CARD_IMG=128;     //和学生证合照 上传 标识
    public  static int UPLOAD_WITH_ID_CARD_IMG=127;     //和身份证合照 上传 标识
    public  static int UPLOAD_POST_IMG=126;     //发帖 发送图片 的 标识
    public  static int UPLOAD_ID_CARD_F_IMG=125;     //上传身份证正面 的 标识
    public  static int UPLOAD_ID_CARD_B_IMG=124;     //上传身份证反面 的 标识
    public  static int UPLOAD_HEAD_IMG=123;     //上传头像 的 标识

    public  static int OPEN_OPTION_MENU=122;     //打开OPTION_MENU的 标识
    public  static int FINISH=121;    //关闭当前页的 标识
    public  static int LOCATION_HERE=120;    //定位当前的位置 标识
    public  static int TOASK_OUT_APP=111;    //TOASK 退出应用 标识
    public  static int EDIT_CHANGE_DO=110;    //输入框内容改变 执行操作标识
    public  static int LOGIN_VERIFY=100;    //登陆验证
    public  static int GET_COMP_FIELD=0;    //获取客户领域信息

    public  static int GET_USER_DETAIL_INFO=1; //获取用户信息
    public  static int SAVE_USER_INFO=2; //保存   用户信息 注册
    public  static int UP_USER_INFO=3; //更新     用户信息
    public  static int DEL_USER_INFO=4; //删除    用户信息

    public  static int GET_POST_THEM_INFO=5; //获取 帖子主题 信息
    public  static int UPLOAD_POST_CONTENT=6;     //发帖 内容 的 标识
    public  static int GET_SPLASH_PIC=7;     //获取闪屏图片 的 标识

    public  static int GET_SLIP_LIST=8;     //获取我的保单 即获取订单列表
    public  static int GET_SLIP_BATCH_LIST=9;     //获取我的 批量投保 即获取订单列表
    public  static int GET_BATCH_SLIP_LIST=11;     //获取我的 批量投保详情列表

    public  static int ISEXIST_USER=10;    //验证用户是否存在


    public static String IP00="http://124.133.240.189";    //    http://192.168.1.253:8188 115.28.134.181 124.133.240.189
    public static String IP=IP00+":8092";
    public static String IP02=IP00+":8091";       //    http://192.168.1.253:8188
    public static String HOME=IP+"/Credit/app/circles/circle.html";
    public static String UPLOAD_HEAD_PIC_PATH="/Credit/uploadfile.servlet";
    //修改的
    public static String LOGIN_PATH="/api/Register/Login"; //获取用户信息的数据接口   /api/Personal/GetUserInfo

    public static String REGISTER_PATH="/api/Register/RegisterMember"; //注册 的数据接口
    public static String GET_USER_DETAIL_INFO_PATH="/api/Personal/GetMyInfo";
    public static String UP_USER_INFO_PATH="/api/Personal/SaveMyInfo";  //修改
    public static String ISEXIST_USER_PATH="/api/Register/IsExistUser";  //验证用户是否存在

    public static String GET_SLIP_LIST_PATH ="/api/Personal/GetSlipList";  //获取我的保单
    public static String GET_SLIP_BATCH_LIST_PATH ="/api/Personal/GetSlipBatchList";  //获取我的 批量投保列表
    public static String GET_BATCH_SLIP_LIST_PATH ="/api/Personal/GetBatchSlipList";  //批量保单详情


    public static String GET_SPLASH_PIC_PATH="/servers_interface/cell_interface/getSplashPic.action";      //获取闪屏图片
    public static String RESET_PWD_PATH="/User/ChangeUserPassword"; //个人中心修改密码的数据接口

    public  static String WEBVIEW_PROXY_PAGE="61.156.8.189/proxy.html?"; // 浏览器内核的 找不到页面的默认地址 拦截后自己处理

    public  static String SPLASH_NEW_NAME ="splash_new.jpg";    //新闪屏 图片的名称
    public  static String ALBUM_PATH = Environment.getExternalStorageDirectory()+ "/Android/data/";  //文件存储路径
    public  static String SYS_PATH = Environment.getExternalStorageDirectory()+"";  //系统存储路径
    public  static String WEBCACHE_PATH ="files.webcache/";  //webView 缓存路径 第一次调用 getAlbumPath() 时会赋值

    public static String DOWNLOAD_NEW_VERSION_PATH="http://www.iyunzhu.com:81/Credit.apk";

    public static String HEAD_IMG_SAVE_PATH="headPic"; // 头像在服务器的保存路径

    public static String JUMP_TO_LANDING_SP="JumpToLanding"; // 跳转到登陆的标识
    public static String JUMP_TO_PERSONAL_INFO_SP="jumpToPersonalInfo"; //

    public static String[] SEX_ARRAY={"先生","女士"}; //性别字符串数组
    public static String[] VISIT_STATE_ARRAY={"已保存","已提交","已完成"}; //拜访状态 字符串数组
    public static String[] POST_THEM_ARRAY={"休闲灌水","快乐购物","投资理财","外语学习","健康生活","时尚杂志"}; //发帖主题 字符串数组
    public static String[] MODULAR_ARRAY=  {"圈子","商品","零钱包","活动","我的"};

    public  static int SYNCHRONOUS_DATA_TIME=5;    //同步数据的 时间 间隔 单位分钟
    public  static int LOCATION_TIMEOUT=6000;    //定位超时 时间
    public  static int CONNECTION_TIMEOUT=93000;    //连接时间 请求网页时间
    public  static int SO_TIMEOUT=93000;    //数据传输时间
    public  static int EDITEXT_WITE_TIME=900;    //输入框 等待执行的 时间
    public  static int TOASK_BACK_TIME=3000;    //退出应用的TOAST 等待执行的 时间
    public  static int ALLOW_WRONGPWD_COUNT=3;    //输入框 等待执行的 时间
    public  static int REGET_VERIFY_CODE_TIME=60;    //重新获取验证码 的 时间
    public  static int LOW_LEVEL=0;    //登陆权限的  最低级别
    public  static int MIDDLE_LEVEL=1;    //登陆权限的  中级别
    public  static int HIGH_LEVEL=2;    //登陆权限的  高级别
    public  static int NOTICE_MSG=4;    //公告通知的 消息 类型 typeId
    public  static int UPLOAD_PIC_MAXCOUNT=1;    //最大上传图片数
    public  static int PAGE_SIZE=30;    //一页显示的数量
    public  static int RE_CONNECT_COUNT=3;    //重新连接/请求的次数
    //新添加的特有数据
    public  static int IS_LOGIN=-2;    //是否登陆的临界值 调试用 方便修改
    public  static int PERSONAL_MAX_BUY_COUNT=3;    //个人最大购买数量
    public  static int MIN_AGE=18;    //最小年龄
    public  static int MAX_AGE=65;
    public  static int BOTTOM_TOOL_OUT_TIME01=120;    //小助手的动画时间01

    public static final int WEBVIEW_FRAGMENT=1; // Webview fragment
    public static final int FIRST_ALL_FRAGMENT=2; // 总首页 fragment
    public static final int FIRST_MINE_FRAGMENT=3; // 我的首页 fragment
    public static final int LOGIN_FRAGMENT=4; // 登陆 fragment
    public static final int REG_FRAGMENT=5; // 注册 fragment
    public static final int PERSONAL_INFOR_FRAGMENT=6; // 用户信息 fragment
    public static final int NAME_PAGE_FRAGMENT=7; // 修改姓名 fragment
    public static final int SETTING_FRAGMENT=8; // 设置 fragment
    public static final int FEED_BACK_FRAGMENT=9; // 意见反馈 fragment
    public static final int SOFTWARE_SCORE_FRAGMENT=10; // 软件评分 fragment
    public static final int BATCH_POLICY_DETAIL_FRAGMENT=11; // 批量投保 订单详情页 fragment

    public  static String BUY_INSURANCE_URL="/WebApp/BuyInsurance/BuyInsurance.html";   //买保险 的URL
    public  static String SALE_INSURANCE_URL="/WebApp/SellInsurance/SellInsurance.html";   // 的URL
    public  static String MAKE_MONEY_URL="/WebApp/GoMakeMoney/GoMakeMoneyIndex.html";   // 的URL
    public  static String CLAIMTRACKING_URL="/WebApp/Myself/ClaimTracking.html";   //理赔追踪 的URL
    public  static String SLIP_DETAILS_URL="/WebApp/Myself/SlipDetails.html";   //保险详情 的URL
    public  static String BONUSLIST_URL="/WebApp/Myself/BonusList.html";   //红包清单 的URL
    public  static String MYREFERENCE_URL="/WebApp/Myself/MyReference.html";   //我的推荐 的URL
    public static String LEAVE_MESSAGE_URL="/WebApp/Other/Message.html";  //留言

    public  static String SALE_SUCCESSFULCASE_URL="/WebApp/SellInsurance/SuccessfulCase.html";   // 的URL
    public  static String SALE_CLASSROOM_URL="/WebApp/SellInsurance/ClassRoom.html";   // 的URL
    public  static String COMPANYINFO_URL="/WebApp/Us/CompanyInfo.html";   // 的URL
    public  static String INSURANCE_INFO_URL="/WebApp/BuyInsurance/InsuranceInfo.html?id=";   // 保险详情 的URL 需要拼接 ?id=
    public  static String PAYMENT_PAGE_URL="/WebApp/BuyInsurance/Payment.html";   // 付款页

    public  static String US_URL="/WebApp/Us/about.html";   // 的URL
    /**比较两个字符串类型的时间大小，第一个时间大的话返回true 否则返回false*/
    public static boolean compareStrData(String data01,String data02){

        boolean flag=Long.valueOf(replaceDatemT(data01).replaceAll("[-\\s:]",""))>Long.valueOf(replaceDatemT(data02).replaceAll("[-\\s:]",""));
        return  flag;
    }
    /**获取时间字符串  yyyy-MM-dd HH:mm:ss*/
    public static String getCurrentTime(){ //获取当前时间的方法
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //自定义时间样式 HH表示24小时 hh表示12小时的
        String curtime=dateFormat.format(date);
        return  curtime;
    }
    /**获取日期 字符串  yyyyMMdd，如：20150602*/
    public static String getCurrentDateNumb(){ //获取当前时间的方法
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd"); //自定义时间样式 HH表示24小时 hh表示12小时的
        String curtime=dateFormat.format(date);
        return  curtime;
    }
    public static String getIntelligentTime(String timeStr){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        Date d2 = null;
        long seconds=0,days=0;
        try {
            d1 = df.parse(replaceDatemT(timeStr));  //传递过来的时间
            d2 = df.parse(removeDateminute(getCurrentTime())+" 00:00:01");  //当天的 零时
            long diff = d1.getTime() - d2.getTime();    //timeStr 减去 今天的零时 >=0&&<=24
            seconds=diff / 1000+diff%1000 ; //换算成秒
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (seconds>=0&&seconds<=60 * 60 * 24)
            return onlyHourMin(timeStr);    //只保留时分
        else if (seconds<0){    //不是今天 的时间，进一步判断是不是昨天
            /*System.out.println("-seconds<? 60 * 60 * 24  "+(-seconds)+"  "+60 * 60 * 24);*/
            if ((-seconds)<=(60 * 60 * 24))
                return "昨天 "+onlyHourMin(timeStr);   //昨天的 返回 昨天+12:05
            else if (getYear(getCurrentTime())==getYear(timeStr))
                return changeDateToShort(timeStr);  //同一年 变成 06-25的格式
            else
                return removeDateminute(timeStr);  //不是同一年的 返回 完整时间
        }else //传入的时间在未来 则返回原始时间
            return replaceDatemT(timeStr);

    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T)变成 06-25的格式
     * */
    public static String changeDateToShort(String tmp){
        String splitChar=tmp.indexOf('T')>=0?"T":" ";
        tmp=tmp.split(splitChar)[0];    //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
        tmp=tmp.split("-")[1]+"-"+tmp.split("-")[2];    //去掉年份 显示
        return tmp;
    }
    /**把时间2015-06-25T06:52:12 (中间可以是空格或T)变成 2015-06-25 06:52:12的格式,即替换T 为 空格
     * */
    public static String replaceDatemT(String tmp){
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " ";
            String[] str=tmp.split(splitChar);
            tmp = str[0]+" "+str[1];
            return tmp;
        }else {
            return tmp;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T)变成 2015-06-25的格式,即去除时分秒
     * */
    public static String removeDateminute(String tmp){
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " ";
            tmp = tmp.split(splitChar)[0];    //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            return tmp;
        }else {
            return tmp;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T)变成 2015-06-25 06:52的格式,即去除时 秒
     * */
    public static String removeTimeSecond(String tmp){
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String[] str,btr;
            String splitChar=tmp.indexOf('T')>=0?"T":" ";
            str=tmp.split(splitChar);    //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            tmp=str[0]+" "+str[1];  //去除 符号 T
            btr=tmp.split(":");
            tmp = btr[0]+":"+btr[1];
            return tmp;
        }else {
            return tmp;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T)变成 06:52 的格式,即只保留时 分
     * */
    public static String onlyHourMin(String tmp){
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " ";
            String[] str = tmp.split(splitChar)[1].split(":");    //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            tmp=str[0]+":"+str[1];  //拼接为 06:52
            return tmp;
        }else {
            return tmp;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T) 获取 年 返回Int
     * */
    public static int getYear(String tmp){
        int result=-2015;    //初始值
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " "; //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            result= Integer.parseInt(tmp.split(splitChar)[0].split("-")[0]);   //用 - 分割 获取第 一 个即 年
            return result;
        }else {
            return result;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T) 获取 月 返回Int
     * */
    public static int getMonth(String tmp){
        int result=-1;    //初始值
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " "; //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            result= Integer.parseInt(tmp.split(splitChar)[0].split("-")[1]);   //用 - 分割 获取第 2 个即 月
            return result;
        }else {
            return result;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T) 获取 日 返回Int
     * */
    public static int getDay(String tmp){
        int result=-1;    //初始值
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " "; //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            result= Integer.parseInt(tmp.split(splitChar)[0].split("-")[2]);   //用 - 分割 获取第 3 个即 日
            return result;
        }else {
            return result;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T) 获取 时 返回Int
     * */
    public static int getHour(String tmp){
        int result=-1;    //初始值
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " "; //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            result= Integer.parseInt(tmp.split(splitChar)[1].split(":")[0]);   //用 - 分割 获取第 1 个即 时
            return result;
        }else {
            return result;
        }
    }
    /**把时间2015-06-25 06:52:12 (中间可以是空格或T) 获取 分 返回Int
     * */
    public static int getMinute(String tmp){
        int result=-1;    //初始值
        if (!tmp.isEmpty()) {    //判断是否为 ""
            String splitChar = tmp.indexOf('T') >= 0 ? "T" : " "; //日期与时间之间如果是 "T"就用T 将字符串分割，取第一个字符串
            result= Integer.parseInt(tmp.split(splitChar)[1].split(":")[1]);   //用 - 分割 获取第 2 个即 分
            return result;
        }else {
            return result;
        }
    }
     /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        if (str==null)
            return false;
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 电话号码验证
     *
     * @param  //str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String phoneNumber) {
        boolean isValid = false;
	   /*可接受的电话格式有：
	    */
        String expression1 ="((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(" +
                "\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
        CharSequence inputStr = phoneNumber;
        Pattern pattern1 = Pattern.compile(expression1);
        Matcher matcher1 = pattern1.matcher(inputStr);
        if (matcher1.matches()){
            isValid = true;
        }
        return isValid;
    }
    /**判断email格式是否正确*/
    public static boolean isEmail(String email) {
        int length=email.length();   //邮箱长度
        if (length>0) {
            String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-" +
                    "zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            //"^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
            //自定义 添加 结尾 的com 或者cn验证  hujun1299@163.com   index all  16  length 17   . 13
            boolean tail= email.indexOf(".cn")==(length-3)||email.indexOf(".com")==(length-4); //首次查到的位置符合 来验证结尾
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(email);
            return m.matches()&&tail;
        }else {     //字符穿长度不大于0时 返回false
            return false;
        }

    }
    /**判断 银行卡 格式是否正确 简单验证16 或19位*/
    public static boolean isIcCard(String icCard) {
        if (icCard==null)
            return false;
        Pattern p = Pattern
                .compile("^(\\d{16}|\\d{19})$");
        Matcher m = p.matcher(icCard);
        System.out.println(m.matches() + "---");
        return m.matches();
    }

    /**判断 是否是 整数
     *@param type "0+"：非负整数；"+":正整数；"-0":非正整数;"-":负整数;"":整数*/
    public static boolean checkNumber(String num,String type) {
        if (num==null)  //传入为空的 返回false

            return false;
        String eL="";
        if (type.equals("0+"))
            eL="^\\d+$";    //非负整数
        else if (type.equals("+"))
            eL="^\\d*[1-9]\\d*$";    //正整数
        else if (type.equals(""))
            eL="^-?\\d+$";    //整数
        Pattern p=Pattern.compile(eL);
        Matcher matcher=p.matcher(num);
        boolean b=matcher.matches();
        return b;
    }
    /**去除jsonStr 中不需要的字段,通过值 value 过滤
     * @return 当前应用的版本号
     */
    public static String getJSONStringExceptValue(String jsonStr,String value) {
        try {
            JSONObject jb= JSON.parseObject(jsonStr);
            LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<LinkedHashMap<String, String>>() {
            });
            for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                if (entry.getValue().equals(value))    //排除掉的值
                    jb.remove(entry.getKey());
            }
            return JSON.toJSONString(jb);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }
    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
     public static String getVersion(Context context) {
            try {
                   PackageManager manager = context.getPackageManager();
                 PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                   String version = info.versionName;
                    return "version" + version;
                } catch (Exception e) {
                    e.printStackTrace();
                    return "无法获取版本号...";
                }
        }
    /**
     * 获取版本 code
     * @return 当前应用的版本号
     */
    public static String getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionCode+"";
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取版本号...";
        }
    }
    /**获得项目Android/data/项目包名/ 文件夹,没有则创建;并设置缓存文件路径*/
    public static String getAlbumPath(Context context) {
        try {
            String AlbumPath=GlobalParameter.ALBUM_PATH+context.getPackageName()+"/";
            File dirFile = new File(AlbumPath);
            if (!dirFile.exists()) {    // 判断 /Android/data/项目包名/ 文件夹是否存在
                dirFile.mkdir();
            }
            WEBCACHE_PATH=AlbumPath+"files.webcache/";  //设置缓存文件路径
            return AlbumPath;
        } catch (Exception e) { //报错的话 返回 ALBUM_PATH
            e.printStackTrace();
            WEBCACHE_PATH=ALBUM_PATH+"files.webcache/";  //设置缓存文件路径
            return ALBUM_PATH;
        }
    }
    /**
     * 获取手机本地cookie 的数据
     * @return cookieStr
     */
    public static String getCookieNative(Context context,String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String cookieStr = cookieManager.getCookie(url);    //url是页面全地址才可以获取到
        return cookieStr;
    }
    /**
     * 将cookie 注入webview @param cookieStr cookie串  @param domain 域名 主机地址 不带http 如：192.168.1.248:8091
     * @return cookieStr
     */
    public static void setCookieNative(Context context,ArrayList<String> cookieStr,String domain) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);    // cookieStr.get(i) 包含键值对 如:name=张三
        for (int i = 0; i < cookieStr.size(); i++) {  // cookieArray是多个cookie的数组变量  Max-Age=3600  +";Domain="+ "192.168.1.248:8091"
           String[] strs= cookieStr.get(i).split("=");
            try {   //URLEncoder.encode(URLEncoder.encode(strs[1],"utf-8"),"utf-8")
                cookieManager.setCookie(domain, strs[0]+"="+URLEncoder.encode(strs[1],"utf-8") + ";expires=960000" +";"+domain + ";Path = /");
            } catch (UnsupportedEncodingException e) {
                cookieManager.setCookie(domain, cookieStr.get(i)+ ";expires=960000" + ";" + domain + ";Path = /");
            }
        }
        CookieSyncManager.getInstance().sync();
       // cookieManager.setCookie(ip+"/", key + "=" + cookie.getValue() + ";domain=" + "xxxx.com"+";path=/");
    }

    /**清理手机cookie*/
    public static void removeCookie(Context context){
        // 清除cookie即可彻底清除缓存  防止记忆上次的登陆状态
        CookieSyncManager.createInstance(context); //进入应用时 这两行有效果
        CookieManager.getInstance().removeAllCookie();

        CookieManager.getInstance().removeSessionCookie();
        CookieSyncManager.getInstance().sync();
        CookieSyncManager.getInstance().startSync();
    }
    public static String getCookieNew(String url) {
        HttpClient httpClient=new DefaultHttpClient();
        /*HttpGet request = new HttpGet(url);*/
        HttpPost request = new HttpPost(url);
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //获得cookie
                //getCookie(httpClient);
                List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < cookies.size(); i++) {

                    Cookie cookie = cookies.get(i);
                    String cookieName = cookie.getName();
                    String cookieValue = cookie.getValue();
                    if (!TextUtils.isEmpty(cookieName)
                            && !TextUtils.isEmpty(cookieValue)) {
                        if (cookieName.contains(".ASPXAUTH")) {
                            sb.append(cookieName + "=");
                            sb.append(cookieValue );    //+ ";"
                        }
                    }
                }
                System.out.println("得到cookie- -");
                //URLDecoder.decode(sb.toString(), "utf-8");
                return URLDecoder.decode(sb.toString(), "utf-8");
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常了 哈哈");
            return null;
        }
    }

    /**保存本地数据的方法 都存文字符串:key和value 紧挨着，如：key01,value01,key02,value02*/
    public static void saveLocalData(Context context,String...keyAndValue){
        SharedPreferences sp=context.getSharedPreferences(context.getPackageName()+"_ld_"+GlobalParameter.getVersionCode(context), context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();//获取SharedPreferences对象 获取他的Editor对象
        for (int i=0;i<keyAndValue.length/2;i++) {
            editor.putString(keyAndValue[i], keyAndValue[i+1]);
            i++;
        }
        editor.commit();//将数据保持到本地
    }
    /**读取本地数据的方法*/
    public static String getLocalData(Context context,String key){
        SharedPreferences sp=context.getSharedPreferences(context.getPackageName()+"_ld_"+GlobalParameter.getVersionCode(context), context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
    /**
     * 获取正在运行的ActivityName
     * @return ActivityName
     */
    public static String getRunningActivityName(Context context) {
        String contextString = context.toString();
        return contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
    }
    /**
     * 以获取图片宽度充满后 的图片控件高度
     * @param context
     * @param bitmap
     * @return
     */
    public static int getBitmapFullWidthH(Context context, Bitmap bitmap){
        int screenWidth=getScreenWidth(context);
        double rate=  bitmap.getWidth()/(double)bitmap.getHeight();
        int newHeight= (int) (screenWidth/rate);
        return newHeight;
    }
/**根据图片路径 生成Bitap对象 设置参数减少内存占用 降低 outOf memory 发送的几率 */
    public static Bitmap lowMemoryDecodeFile(String mFilePath) {
        Bitmap bitmap=null;
        BitmapFactory.Options options = new BitmapFactory.Options(); //设置BitmapFactory 使其降低内存占用
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inPurgeable = true;
        options.inDither = true;
        if (mFilePath != null) {
            bitmap = BitmapFactory.decodeFile(mFilePath, options);
        }
        return bitmap;
    }
    /**
     * 以最省内存的方式读取本地资源的图片
     * @param context
     * @param resId
     * @return
     */
     public static Bitmap readBitMap(Context context, int resId){
           BitmapFactory.Options opt = new BitmapFactory.Options();
           opt.inPreferredConfig = Bitmap.Config.RGB_565;
           opt.inPurgeable = true;
           opt.inInputShareable = true;
              //获取资源图片
           InputStream is = context.getResources().openRawResource(resId);
             return BitmapFactory.decodeStream(is,null,opt);
        }
    /**
     * @param bitmap      原图
     * @param edgeLength  希望得到的正方形部分的边长
     * @return  缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if(null == bitmap || edgeLength <= 0) {
            return  null;
        }
        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if(widthOrg > edgeLength && heightOrg > edgeLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int)(edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try{
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            }
            catch(Exception e){
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try{
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            }
            catch(Exception e){
                return null;
            }
        }
        return result;
    }
    /**
     * @param bitmap      原图
     * @param width  height 希望得到的长方形部分的 宽 高
     * @return  缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int width,int height)
    {
        if(null == bitmap || (width <= 0||height<=0))
        {
            return  null;
        }
        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();   //原始图片的 宽、高
        int heightOrg = bitmap.getHeight();
        double HWRatio=heightOrg/(double)widthOrg;     //高 宽比例
        double HWRatioNew=height/(double)width;     //目标 高 宽比例
        //System.out.println("HWRatio HWRatioNew:"+HWRatio+"  "+HWRatioNew);
        //0.01精度参数，比例相近 不重新截取
        if ((HWRatioNew-HWRatio)<-0.01) {        //则截除 上下高度  width 不变；重新计算 高度--减小
            int scaledHeight= (int) ((HWRatioNew/HWRatio)*heightOrg);
            int heightReduce=(heightOrg-scaledHeight)/2;
            //从图中截取 部分。
            // int xTopLeft = widthReduce;
            int yTopLeft = heightReduce;
            try{
                result = Bitmap.createBitmap(bitmap, 0, yTopLeft, widthOrg, scaledHeight);
            }
            catch(Exception e){
                return null;
            }
        }else if ((HWRatioNew-HWRatio)>0.01) {    //目标比例小于原始比例 则截除 两边宽度 height 不变；重新计算 宽度--减小
            int scaledWidth= (int) ((HWRatio/HWRatioNew)*widthOrg);
            int widthReduce=(widthOrg-scaledWidth)/2;
            //从图中截取 部分。
            int xTopLeft = widthReduce;
            //int yTopLeft = (scaledHeight - edgeLength) / 2;
            try{
                result = Bitmap.createBitmap(bitmap, xTopLeft, 0, scaledWidth, heightOrg);
            }
            catch(Exception e){
                return null;
            }
        }
        return result;
    }
    /**Bitmap to Bytes[] PNG*/
    public static byte[] Bitmap2Bytes(Bitmap bm) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
                return baos.toByteArray();
            }
    /**Bytes[] to Bitmap  PNG*/
    public static Bitmap bytes2Bimap(byte[] b){
        if(b.length!=0){
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        else {
            return null;
        }
    }
    /**对 base64字符串格式图片 进行解码并生成图片Bitmap*/
    public static Bitmap base64ImgToBitmap(String imgStr) {
        if (imgStr == null) // 图像数据为空
            return null;
        byte[] bitmapArray= Base64.decode(imgStr, Base64.DEFAULT);
        return bytes2Bimap(bitmapArray);
    }
    /**延时加载网络图片 不阻塞主线程*/
    public static void asynSetImageViewFromNet(Context context,ImageView iv,String imgUrl){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader mImageLoader = ImageLoader.getInstance();
        //延时加载图片
        try {   //网路异常时 捕获异常
            mImageLoader.displayImage(imgUrl, iv, options); //GlobalParameter.IP+tmp
        }catch (Exception e){
            e.printStackTrace();
            iv.setImageBitmap(readBitMap(context, R.drawable.loading));
        }
    }
    /**检查 网路是否可用*/
    public static boolean checkNetworkAvailable(Context context) {
        try {       //异常捕获 防止Context为空
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        NetworkInfo netWorkInfo = info[i];
                        if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                            return true;
                        } else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                            return true;
                        }
                    }
                }
            }
        }
        }catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    /**避免Toast 重复显示*/
    public static Toast showToast(Context context,Toast mToast,String text) {
        if(mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
            }
        mToast.show();
        return mToast;
    }
    public static void showNetWorkNotUseToask(Context context){     //显示网络连接不可用
        Toast toast = Toast.makeText(context, "网络不可用...", Toast.LENGTH_SHORT);
        toast.show();
    }
    /**当前时间 是否大于 从服务器 下载数据的时间 +一定时间:SYNCHRONOUS_DATA_TIME*/
    public static boolean downdatetimeGreaterCurrtime(Context ctx,int dataCode) {
        long downloadtimeL=0;
        String downloadtime=null;
        SharedPreferences sp=ctx.getSharedPreferences("superbseting", ctx.MODE_PRIVATE);
        long currtimeL=System.currentTimeMillis();  //获取当前时间的毫秒值
        downloadtimeL=sp.getLong("downloadtimeL"+dataCode, 1);
        downloadtime=sp.getString("downloadtime"+dataCode, null);
        // System.out.println("downloadtimeL:"+downloadtimeL+"--"+"downloadtime:"+downloadtime+
        //	"--currtimeL:"+currtimeL);
        return (currtimeL-SYNCHRONOUS_DATA_TIME*60000)>downloadtimeL ;
    }
    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }
    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
    /**获取应用外的状态栏的高度*/
    public static int getTopHeight(Context context) {
        Rect outRect = new Rect();  //控件摆放完毕获取的高度才不为0
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.top;
    }
    /**获取应用外底部 smartBar的高度*/
    public static int getBottomHeight(Context context) {
        Rect outRect = new Rect();  //控件摆放完毕获取的高度才不为0
        ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
       /* System.out.println("outRect.height():" + outRect.height() + " .bottom:" + outRect.bottom +
                " .top:" + outRect.top + " bottom-top:" + (outRect.bottom - outRect.top)+
               " final:"+(getScreenHeight(context)- (outRect.bottom)));*/
        return getScreenHeight(context)- (outRect.bottom);  //-outRect.top
    }
    /**实现文本复制功能
     * add by wangqianzhou
     * @param content*/
    public static void copy(Context context,String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }
    /**实现粘贴功能
     * add by wangqianzhou
     * @param context
     * @return*/
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }
    /**判断应用是否前台运行 需要声明权限 uses-permission android:name =“android.permission.GET_TASKS” */
    public static boolean isRunningForeground (Context context) {
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        if(!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
             return true ;
             }
        //Log.e("MYLOG", "后台:");
        return false ;
    }

    /**  对字符串进行MD5加密     */
    public static String encodeByMD5(String originString){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    /**
     * 转换字节数组为十六进制字符串
     * @param字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    //
    public static JSONArray joinJSONArray(JSONArray mData, JSONArray array) {   //将两个JSONArray数据 合并
        JSONArray jssonarray;
        StringBuffer buffer = new StringBuffer();
        try {
            int len = mData.size();
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) mData.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            len = array.size();
            if (len > 0)
                buffer.append(",");
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            } buffer.insert(0, "[").append("]");
             jssonarray= new JSONArray().parseArray(buffer.toString());
            return jssonarray;
        } catch (Exception e) { }
        return null;
    }

    /*** 转化十六进制编码为字符串 */
    public static String toStringHex(String s)
    {
        byte[] baKeyword = new byte[s.length()/2];
        for(int i = 0; i < baKeyword.length; i++)
        {
            try
            {
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            s = new String(baKeyword, "utf-8");//UTF-16le:Not
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        return s;
    }
    /**判断手机中的 路径下的 文件 是否存在*/
    public static boolean fileIsExists(String filePath,String fileName){
        /*String ALBUM_PATH = Environment.getExternalStorageDirectory() + "/Android/data/com.xuexin/";  //文件存储路径*/
        try{
            String path=filePath;
            File file=new File(path);
            if(!file.exists()) {     //判断文件夹是否存在 不存在就创建 并返回 false
                file.mkdir();
                return false;
            }

            File f=new File(filePath+fileName);     //判断文件是否存在 不存在返回false
            if(!f.exists()){
                return false;
            }

        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
}
