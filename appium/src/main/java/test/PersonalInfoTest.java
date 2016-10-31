package test;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.appium.java_client.android.AndroidElement;
import main.ConstantValue;
import model.PersonalInformationBean;
import util.JsonUtils;
import util.StringUtils;

/**
 * @author lungtify
 * @Time 2016/10/27 18:17
 * @Describe
 */
public class PersonalInfoTest extends BaseTest {
    /**
     * 用户个人信息
     */
    @Test
    public void testPersonalInfo() throws MalformedURLException, InterruptedException, ParseException {
        setUp();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/teachers/" + ConstantValue.userId + "/info");
        PersonalInformationBean bean = JsonUtils.objectFromJson(result, PersonalInformationBean.class);
        AndroidElement tab2 = driver.findElementById("tab_img2");
        tab2.click();
        Time(1);
        //转到个人中心
        driver.findElementById("information").click();
        Time(2);
        String gender = "";
        if (!StringUtils.isNullOrBlanK(bean.getData().getGender())) {
            if (bean.getData().getGender().equals("male")) {
                gender = "男";
            } else {
                gender = "女";
            }
        } else {
            gender = "";
        }
        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/name").getText(), bean.getData().getName());
        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/sex").getText(), gender);
        SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String birth = "";
        if (!StringUtils.isNullOrBlanK(bean.getData().getBirthday())) {
            birth = format.format(parse.parse(bean.getData().getBirthday()));
        } else {
            birth = format.format(new Date());
        }
        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/birthday").getText(), birth);
        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/teaching_years").getText(), getTeachingYear(bean.getData().getTeaching_years()));
        String category;
        if (!StringUtils.isNullOrBlanK(bean.getData().getCategory())) {
            category = bean.getData().getCategory();
        } else {
            category = "";
        }
        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/category").getText(), category);
        String subject;
        if (!StringUtils.isNullOrBlanK(bean.getData().getSubject())) {
            subject = bean.getData().getSubject();
        } else {
            subject = "";
        }

        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/subject").getText(), subject);
        Assert.assertEquals(driver.findElementById("cn.qatime.teacher.player:id/describe").getText(), bean.getData().getDesc());


        println("个人信息测试完成");
    }

    private String getTeachingYear(String teaching_years) {
        switch (teaching_years) {
            case "within_three_years":
                return "3年以内";
            case "within_ten_years":
                return "3-10年";
            case "within_twenty_years":
                return "10-20年";
        }
        return "";
    }
}
