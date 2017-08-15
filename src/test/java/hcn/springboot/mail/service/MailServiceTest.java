/*
 * 文件名：MailServiceTest.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月10日
 */

package hcn.springboot.mail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    TemplateEngine templateEngine;
    /*@Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("2207256221@qq.com","test simple mail"," hello this is simple mail");
    }*/
    /*
    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("2207256221@qq.com","test html mail",content);
    }*/
    /*
    @Test
    public void sendAttachmentsMail() {
        String filePath="D:\\黄春宁\\Pictures\\桌面\\008.jpg";
        mailService.sendAttachmentsMail("2207256221@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }*/
    
    /*@Test
    public void sendInlineResourceMail() {
        String rscId1 = "pic1";
        String rscId2 = "pic2";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId1 + "\' ><br/><img src=\'cid:" + rscId2 + "\' ></body></html>";
        String imgPath1 = "D:\\黄春宁\\Pictures\\桌面\\008.jpg";

        String imgPath2 = "D:\\黄春宁\\Pictures\\桌面\\4-141010161214.jpg";
        mailService.sendInlineResourceMail("2207256221@qq.com", "主题：这是有图片的邮件", content, imgPath1, rscId1, imgPath2, rscId2);
    }*/
    
    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("2207256221@qq.com","主题：这是模板邮件",emailContent);
    }
    
}