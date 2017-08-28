/*
 * 文件名：MailServiceImplTest.java 版权：Copyright by www.bonc.com.cn 描述： 修改人：HCN 修改时间：2017年8月18日
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import hcn.springboot.mail.service.MailService;
/*1.4.0版本前
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = SpringbootMailApplication.class)  
@WebAppConfiguration  */

//@RunWith(SpringRunner.class)表示要进行spring的全部流程启动单元测试，也就是说会类似于spring的正常启动启动服务
@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest   //1.4.0版本后， 表示使用spring-boot的方式启动单元测试，
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    public MailServiceImplTest() {
        System.out.println("构造方法");
    }

    /*
     * // 测试方法执行超过1000毫秒后算超时，测试将失败
     * @Test(timeout = 1000) 
     * // 测试方法期望得到的异常类，如果方法执行没有抛出指定的异常，则测试失败
     * @Test(expected = Exception.class) 
     * 常用断言： assertArrayEquals(expecteds, actuals) 查看两个数组是否相等。
     * assertEquals(expected, actual) 查看两个对象是否相等。类似于字符串比较使用的equals()方法
     * assertNotEquals(first,second) 查看两个对象是否不相等。
     * assertNull(object) 查看对象是否为空。 
     * assertNotNull(object) 查看对象是否不为空。
     * assertSame(expected, actual) 查看两个对象的引用是否相等。类似于使用“==”比较两个对象 
     * assertNotSame(unexpected, actual)查看两个对象的引用是否不相等。类似于使用“!=”比较两个对象 
     * assertTrue(condition) 查看运行结果是否为true。 assertFalse(condition)查看运行结果是否为false。 
     * assertThat(actual, matcher) 查看实际值是否满足指定的条件 
     * fail() 让测试失败
     * 其他注解：
     * @RunWith 指定测试类使用某个运行器 
     * @Parameters 指定测试类的测试数据集合
     */

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
     * @Before：每一个测试方法执行前自动调用一次
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
        mailService.sendSimpleMail("2207256221@qq.com", "test simple mail",
            " hello this is simple mail");
        // fail("Not yet implemented");
        System.out.println("发送普通的邮件");
    }

    @Test
    public void testSendHtmlMail() {
        String content = "<html>\n" + "<body>\n" + "    <h3>hello world ! 这是一封Html邮件!</h3>\n"
                         + "</body>\n" + "</html>";
        mailService.sendHtmlMail("2207256221@qq.com", "test html mail", content);
        System.out.println("发送html邮件");
    }

    @Ignore
    @Test
    public void testSendAttachmentsMail() {
        String filePath = "D:\\黄春宁\\Pictures\\桌面\\008.jpg";
        mailService.sendAttachmentsMail("2207256221@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);

    }

    @Ignore
    @Test
    public void testSendInlineResourceMail() {
        String rscId1 = "pic1";
        String rscId2 = "pic2";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId1
                         + "\' ><br/><img src=\'cid:" + rscId2 + "\' ></body></html>";
        String imgPath1 = "D:\\黄春宁\\Pictures\\桌面\\008.jpg";

        String imgPath2 = "D:\\黄春宁\\Pictures\\桌面\\4-141010161214.jpg";
        mailService.sendInlineResourceMail("2207256221@qq.com", "主题：这是有图片的邮件", content, imgPath1,
            rscId1, imgPath2, rscId2);

    }

    @Ignore
    @Test
    public void testSendTemplateMail() {
        // 创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("2207256221@qq.com", "主题：这是模板邮件", emailContent);

    }
}
