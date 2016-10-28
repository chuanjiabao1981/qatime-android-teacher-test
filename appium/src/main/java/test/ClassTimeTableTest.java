package test;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.appium.java_client.android.AndroidElement;
import main.ConstantValue;
import model.ClassTimeTableBean;
import model.SystemNotifyBean;
import util.JsonUtils;

/**
 * @author lungtify
 * @Time 2016/10/27 18:17
 * @Describe
 */
public class ClassTimeTableTest extends BaseTest {
    /**
     * 课程表一开课
     */
    @Test
    public void testClosed() throws MalformedURLException, InterruptedException {
        setUp();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/teachers/" + ConstantValue.userId + "/schedule?state=closed&month=" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        ClassTimeTableBean data = JsonUtils.objectFromJson(result, ClassTimeTableBean.class);
        AndroidElement tab2 = driver.findElementById("tab_img2");
        tab2.click();
        Time(1);
        //转到课程表
        driver.findElementById("class_table").click();
        Time(2);

        driver.findElementById("tab_text2").click();
        Time(1);
        List<ClassTimeTableBean.DataEntity.LessonsEntity> itemList = new ArrayList<>();
        for (int i = 0; i < data.getData().size(); i++) {
            itemList.addAll(data.getData().get(i).getLessons());
        }
        int count = driver.findElementsById("titles").size();
        for (int i = 0; i < count; i++) {
            Assert.assertEquals(driver.findElementsById("titles").get(i).getText(), itemList.get(i).getCourse_name());
            Assert.assertEquals(driver.findElementsById("grade").get(i).getText(), "科目：" + itemList.get(i).getSubject());
        }
        println("课程表一开课测试完成");
    }

    @Test
    public void testUnClosed() throws MalformedURLException, InterruptedException {
        setUp();
        String result = request.sendGet("http://testing.qatime.cn/api/v1/live_studio/teachers/" + ConstantValue.userId + "/schedule?state=unclosed&month=" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        ClassTimeTableBean data = JsonUtils.objectFromJson(result, ClassTimeTableBean.class);
        AndroidElement tab2 = driver.findElementById("tab_img2");
        tab2.click();
        Time(1);
        //转到课程表
        driver.findElementById("class_table").click();
        Time(2);

        List<ClassTimeTableBean.DataEntity.LessonsEntity> itemList = new ArrayList<>();
        for (int i = 0; i < data.getData().size(); i++) {
            itemList.addAll(data.getData().get(i).getLessons());
        }
        int count = driver.findElementsById("titles").size();
        for (int i = 0; i < count; i++) {
            Assert.assertEquals(driver.findElementsById("titles").get(i).getText(), itemList.get(i).getCourse_name());
            Assert.assertEquals(driver.findElementsById("grade").get(i).getText(), "科目：" + itemList.get(i).getSubject());
        }
        println("课程表未开课测试完成");
    }




}
