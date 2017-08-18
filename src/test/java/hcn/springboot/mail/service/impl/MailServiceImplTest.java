/*
 * 文件名：MailServiceImplTest.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月18日
 */

package hcn.springboot.mail.service.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import hcn.springboot.mail.service.MailService;

//一般的junit Test没有这两个注解，但是在springboot项目中单元测试如果少了这两个注解经常会出错
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Autowired
    TemplateEngine templateEngine;
    
    public MailServiceImplTest() {
        System.out.println("构造方法");
    }

    /**
     * @BeforeClass：所有测试方法执行前执行一次，在测试类还没有实例化就已经被加载，所以用static修饰
     * @see
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("BeforeClass");
    }
    
    /**
     * @AfterClass：所有测试方法执行完执行一次，在测试类还没有实例化就已经被加载，所以用static修饰
     * @see
     */
    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("AfterClass");
    }


    /**
     *  @Before：每一个测试方法执行前自动调用一次
     * @see
     */
    @Before
    public void setUp() {
        System.out.println("Before Test");
    }
    
    /**
     * @After：每一个测试方法执行完自动调用一次
     * @see
     */
    @After
    public void tearDown() {
        System.out.println("After Test");
    }
    
    @Test
    public void testSendSimpleMail() {
        mailService.sendSimpleMail("2207256221@qq.com","test simple mail"," hello this is simple mail");
        //fail("Not yet implemented");
        System.out.println("发送普通的邮件");
    }

    @Test
    public void testSendHtmlMail() {
        String content="<html>\n" +
            "<body>\n" +
            "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
            "</body>\n" +
            "</html>";
        mailService.sendHtmlMail("2207256221@qq.com","test html mail",content);
       System.out.println("发送html邮件");
    }

    @Ignore
    public void testSendAttachmentsMail() {
        String filePath="D:\\黄春宁\\Pictures\\桌面\\008.jpg";
        mailService.sendAttachmentsMail("2207256221@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
        
    }

    @Ignore
    public void testSendInlineResourceMail() {
        String rscId1 = "pic1";
        String rscId2 = "pic2";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId1 + "\' ><br/><img src=\'cid:" + rscId2 + "\' ></body></html>";
        String imgPath1 = "D:\\黄春宁\\Pictures\\桌面\\008.jpg";

        String imgPath2 = "D:\\黄春宁\\Pictures\\桌面\\4-141010161214.jpg";
        mailService.sendInlineResourceMail("2207256221@qq.com", "主题：这是有图片的邮件", content, imgPath1, rscId1, imgPath2, rscId2);
        
    }

    @Ignore
    public void testSendTemplateMail() {
      //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("2207256221@qq.com","主题：这是模板邮件",emailContent);
        
    }
}
