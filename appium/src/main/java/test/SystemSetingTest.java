package test;

import org.junit.Test;
import org.testng.Assert;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidElement;
import main.ClassName;

/**
 * Created by lenovo on 2016/8/30.
 */
public class SystemSetingTest extends BaseTest {

    /**
     * 进入系统设置页面
     *
     * @throws InterruptedException
     * @throws MalformedURLException
     */


    public void toSystemSetting() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment2
        AndroidElement tab2 = driver.findElementById("tab_img2");
        tab2.click();
        Time(1);
        //转到设置
        driver.findElementById("setting").click();
        Time(2);
        //转到安全管理
        AndroidElement setting = driver.findElementById("setting");
        setting.click();
        Time(2);
    }

    @Test
    public void testSystemSettingPage() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement learning = driver.findElementById("learning_process");
        AndroidElement notify = driver.findElementById("notify_setting");
        AndroidElement update = driver.findElementById("check_update");
        AndroidElement clean = driver.findElementById("clean_cache");
        AndroidElement about = driver.findElementById("about");


        update.click();
        Time(2);
//        AndroidElement download = driver.findElementById("download");
//
//        download.click();

    }

    @Test
    public void testNotifySettingPage() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement notify = driver.findElementById("notify_setting");

        notify.click();
        Time(2);

        AndroidElement message = driver.findElementById("notify_message");
        AndroidElement classes = driver.findElementById("notify_classes");


//        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
//        back.click();
//        Time(2);
    }

    @Test
    public void testMessageSettingPage() throws MalformedURLException, InterruptedException {
        testNotifySettingPage();

        AndroidElement notify = driver.findElementById("notify_message");
        notify.click();
        Time(2);

        AndroidElement cbVoice = driver.findElementById("cb_voice");
        AndroidElement cbShake = driver.findElementById("cb_shake");

        String checked1 = cbVoice.getAttribute("checked");
        String checked2 = cbShake.getAttribute("checked");

        AndroidElement voice = driver.findElementById("voice");
        AndroidElement shake = driver.findElementById("shake");
        voice.click();
        shake.click();
        Time(2);


        Assert.assertNotEquals(checked1, cbVoice.getAttribute("checked"));
        Assert.assertNotEquals(checked2, cbShake.getAttribute("checked"));


        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
        back.click();

        Time(2);
        notify.click();
        Time(2);

        Assert.assertNotEquals(checked1, cbVoice.getAttribute("checked"));
        Assert.assertNotEquals(checked2, cbShake.getAttribute("checked"));
    }

    @Test
    public void testCourseSettingPage() throws MalformedURLException, InterruptedException {
        testNotifySettingPage();

        AndroidElement classes = driver.findElementById("notify_classes");
        classes.click();
        Time(2);

        AndroidElement cb1 = driver.findElementById("cb_1");
        AndroidElement cb2 = driver.findElementById("cb_2");
        AndroidElement sms = driver.findElementById("sms");
        AndroidElement sys = driver.findElementById("sys");
        AndroidElement sHours = driver.findElementById("hours");
        AndroidElement sMinute = driver.findElementById("minute");




        String b1 = cb1.getAttribute("checked");
        String b2 = cb2.getAttribute("checked");
        String b3 = sms.getAttribute("checked");
        String b4 = sys.getAttribute("checked");

        cb1.click();
        cb2.click();
        sys.click();
        sms.click();

        Time(2);

        Assert.assertNotEquals(cb1.getAttribute("checked"), b1);
        Assert.assertNotEquals(cb2.getAttribute("checked"), b2);
        Assert.assertNotEquals(sms.getAttribute("checked"), b3);
        Assert.assertNotEquals(sys.getAttribute("checked"), b4);

        AndroidElement back = driver.findElementsByClassName(ClassName.ImageView).get(0);
        back.click();

        Time(2);
        classes.click();
        Time(2);
        
        Assert.assertNotEquals(cb1.getAttribute("checked"), b1);
        Assert.assertNotEquals(cb2.getAttribute("checked"), b2);
        Assert.assertNotEquals(sms.getAttribute("checked"), b3);
        Assert.assertNotEquals(sys.getAttribute("checked"), b4);
    }

    @Test
    public void testAboutUs() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement about = driver.findElementById("about");
        about.click();
        Time(2);

        AndroidElement callPhone = driver.findElementById("call_phone");
        AndroidElement phone1 = driver.findElementById("phone");
        String phone = phone1.getText();
        Assert.assertEquals(phone,"0353-2135828");
        callPhone.click();
        Time(2);
        //拨打电话
        AndroidElement button = driver.findElementById("android:id/button2");
        button.click();
        Time(2);
    }

    @Test
    public void exitLogin() throws MalformedURLException, InterruptedException {
        toSystemSetting();

        AndroidElement exit = driver.findElementById("exit");
        exit.click();
        Time(2);

        login();
        onHome();

    }
}
