package util;

import java.util.Iterator;
import java.util.Map;

/**
 * url类
 */
public class UrlUtils {
    private static boolean isDebug = true;
    public static String baseUrl = isDebug ? "http://testing.qatime.cn/" : "http://qatime.cn/";
    //云信key
    public static String appKey = isDebug ? "95055b08a6ec75ca3b1a0b6b77c6b2ff" : "";
    //登录
    public static String urlLogin = baseUrl + "api/v1/sessions";
    //辅导班
    public static String urlRemedialClass = baseUrl + "api/v1/live_studio/courses";
    //我的辅导班列表
    public static String urlMyRemedialClass = baseUrl + "api/v1/live_studio/teachers/";
    //个人信息
    public static String urlPersonalInformation = baseUrl + "api/v1/teachers/";
    //基础信息
    public static String urlAppconstantInformation = baseUrl + "api/v1/app_constant";
    //注册
    public static String urlRegister = baseUrl + "api/v1/user/register";
    //獲取支付
    public static String urlPayPrepare = baseUrl + "api/v1/live_studio/courses/";
    //獲取支付结果
    public static String urlPayResult = baseUrl + "api/v1/payment/orders/";
    //订单列表
    public static String urlPaylist = baseUrl + "api/v1/payment/orders";
    //获取验证码
    public static String urlGetCode = baseUrl + "api/v1/captcha";
    //用户信息
    public static String urlUser = baseUrl + "api/v1/users/";
    //用户信息
    public static String urlfindPassword = baseUrl + "api/v1/password";
    public static String urlPayment = baseUrl +"api/v1/payment/users/";

    public static String getUrl(String function, Map<String, String> params) {
        String url;
        url = function + "?" + Map2String(params);
        url.trim();
        if (url.endsWith("&")) {
            url.trim().substring(0, url.length() - 1);
        }
        if (url.endsWith("&")) {
            url.substring(0, url.length() - 1);
        }
//        LogUtils.e("请求地址------    " + url);
        return url;
    }

    /***
     * 对内使用
     *
     * @param map
     * @return
     */
    private static String Map2String(Map<String, String> map) {
        Map.Entry entry;
        StringBuilder sb = new StringBuilder();
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (Map.Entry) iterator.next();
            if (!StringUtils.isNullOrBlanK(entry.getKey()) && !StringUtils.isNullOrBlanK(entry.getValue())) {
                sb.append(entry.getKey().toString()).append("=").append(entry.getValue().toString()).append(iterator.hasNext() ? "&" : "");
            }
        }
        return sb.toString();
    }
}
