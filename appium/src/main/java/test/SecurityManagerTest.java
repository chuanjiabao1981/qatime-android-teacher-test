package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import main.ClassName;
import main.ConstantValue;
import model.PersonalInformationBean;
import util.JsonUtils;

/**
 * Created by lenovo on 2016/8/30.
 */
public class SecurityManagerTest extends BaseTest {
    /**
     * 进入安全管理页面
     *
     * @throws InterruptedException
     * @throws MalformedURLException
     */


    public void toSecurityManager() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment2
        AndroidElement tab2 = driver.findElementById("tab_img2");
        tab2.click();
        Time(1);
        //转到设置
        driver.findElementById("setting").click();
        Time(2);
        //转到安全管理
        AndroidElement security = driver.findElementById("security");
        security.click();
        Time(2);
    }

    @Test
    public void testSecurityManagerPage() throws MalformedURLException, InterruptedException {
        toSecurityManager();

        AndroidElement phoneM = driver.findElementById("phone_number_m");
        AndroidElement email = driver.findElementById("email");

        String result = request.sendGet("http://testing.qatime.cn/api/v1/teachers/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        Assert.assertEquals(phoneM.getText(), (bean.getData().getLogin_mobile()) == null ? "未绑定" : bean.getData().getLogin_mobile());
        Assert.assertEquals(email.getText(), (bean.getData().getEmail()) == null ? "未绑定" : bean.getData().getEmail());

    }

    @Test
    public void testVerifyPhonePage() throws MalformedURLException, InterruptedException {
        toSecurityManager();
        //跳到验证手机页面
        AndroidElement bindPhone = driver.findElementById("bind_phone_number");
        bindPhone.click();
        Time(2);


        AndroidElement currentPhone = driver.findElementById("current_phone");
        AndroidElement code = driver.findElementById("code");
        AndroidElement getCode = driver.findElementById("text_getcode");
        AndroidElement next = driver.findElementById("button_next");


        String result = request.sendGet("http://testing.qatime.cn/api/v1/teachers/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        Assert.assertEquals(currentPhone.getText(), (bean.getData().getLogin_mobile()) == null ? "未绑定" : bean.getData().getLogin_mobile());

        getCode.click();
        Time(2);
        Assert.assertFalse(getCode.isEnabled());

    }

    @Test
    public void testChangePWD() throws MalformedURLException, InterruptedException {
        toSecurityManager();
        //跳到验证手机页面
        AndroidElement changePWD = driver.findElementById("change_password");
        changePWD.click();
        Time(2);


        AndroidElement forgetPwd = driver.findElementById("forget_password");


        forgetPwd.click();
        Time(2);

        String result = request.sendGet("http://testing.qatime.cn/api/v1/teachers/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);

        AndroidElement currentPhone = driver.findElementById("current_phone");
        AndroidElement newPass = driver.findElementById("new_pass");
        Assert.assertEquals(currentPhone.getText(), (bean.getData().getLogin_mobile()) == null ? "未绑定" : bean.getData().getLogin_mobile());

        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
        back.click();
        Time(2);

        AndroidElement password = driver.findElementById("password");
        AndroidElement newPwd1 = driver.findElementById("new_password");
        AndroidElement newPwd2 = driver.findElementById("confirm_new_password");
        AndroidElement over = driver.findElementById("button_over");

        password.sendKeys("123123");
        Time(2);
        newPwd1.sendKeys("111111");
        Time(2);
        newPwd2.sendKeys("111111");
        Time(2);
        over.click();
        Time(2);


        //判断是否在登录页面

        AndroidElement register = driver.findElementById("register");
        AndroidElement login = driver.findElementById("login");

        //使用新密码登陆
        ConstantValue.password = "111111";

        login();
        onHome();
    }

    @Test
    public void testBindPhonePage() throws MalformedURLException, InterruptedException {
        toSecurityManager();
//自动跳不过去
//        AndroidElement currentPhone = driver.findElementById("current_phone");
//        AndroidElement code = driver.findElementById("code");
//        AndroidElement getCode = driver.findElementById("text_getcode");
//        AndroidElement next = driver.findElementById("button_next");
//
//        String result = request.sendGet("http://testing.qatime.cn/api/v1/teachers/" + ConstantValue.userId + "/info");
//        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);
//
//        Assert.assertEquals(currentPhone.getText(), bean.getData().getLogin_mobile());
//
//        getCode.click();
//        Time(2);
//        Assert.assertFalse(getCode.isEnabled());

    }


}
