package test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import main.ClassName;
import main.ConstantValue;
import model.RemedialClassBean;
import model.SystemNotifyBean;
import util.JsonUtils;
import util.StringUtils;

/**
 * @author luntify
 * @date 2016/8/22 12:20
 * @Description
 */
public class ChatTest extends BaseTest {
    public void toMessagePage() throws MalformedURLException, InterruptedException {
        setUp();
        //转到fragment3
//        AndroidElement tab3 = driver.findElementById("tab_text1");
//        tab3.click();
//        Time(2);

        AndroidElement list = driver.findElementById("android:id/list");
        list.findElementsByClassName(ClassName.RelativeLayout).get(0).click();

    }

    /**
     *聊天
     */
    @Test
    public void testChat() throws MalformedURLException, InterruptedException {
        toMessagePage();

        Assert.assertEquals(".activity.MessageActivity", driver.currentActivity());

        List<AndroidElement> content = driver.findElementsById("content");
        AndroidElement send = driver.findElementById("send");
        List<AndroidElement> first = driver.findElementsById("right");

        setMessage(content, "testing");
        send.click();
        Time(2);
        List<AndroidElement> second = driver.findElementsById("right");

        Assert.assertTrue(second.size() > first.size());

        setMessage(content, "testmessage");
        send.click();
        Time(2);
        List<AndroidElement> three = driver.findElementsById("right");

        Assert.assertTrue(three.size() > second.size());

        println("聊天测试完成");
    }

    private void setMessage(List<AndroidElement> content, String str) {
        content.get(0).sendKeys(str);
        Assert.assertEquals(str, content.get(0).getText());
    }

    /**
     * 消息记录
     */
    @Test
    public void testMessageRecord() throws MalformedURLException, InterruptedException {
        toMessagePage();

        Assert.assertEquals(".activity.MessageActivity", driver.currentActivity());
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        List<AndroidElement> first = driver.findElementsById("right");

        driver.swipe(width / 2, 300, width / 2, height - 200, 500);
        Time(1);
        driver.swipe(width / 2, 300, width / 2, height - 200, 500);
        Time(3);
        List<AndroidElement> second = driver.findElementsById("right");
        Assert.assertTrue(second.size() >= first.size());
        println("消息记录测试完成");
    }

    /**
     * 系统消息测试
     *
     * @throws InterruptedException
     * @throws MalformedURLException
     */
    @Test
    public void testSystemNotify() throws InterruptedException, MalformedURLException {
        setUp();

        String result = request.sendGet("http://testing.qatime.cn/api/v1/users/" + ConstantValue.userId + "/notifications?user_id=" + ConstantValue.userId + "&page=1");
        SystemNotifyBean data = JsonUtils.objectFromJson(result, SystemNotifyBean.class);
        int width = driver.manage().window().getSize().width;
        driver.swipe(width - 100, 100, width / 2, 100, 200);
        Time(2);

        List<AndroidElement> items = driver.findElementsById("date_time");
        List<AndroidElement> details = driver.findElementsById("details");
        for (int i = 0; i < items.size(); i++) {
            Assert.assertEquals(data.getData().get(i).getCreated_at(), items.get(i).getText());
            Assert.assertEquals(data.getData().get(i).getNotice_content(), details.get(i).getText());
        }
        println("系统消息测试完成");
    }
}
