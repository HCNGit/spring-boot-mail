/*
 * 文件名：MailService.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：HCN
 * 修改时间：2017年8月10日
 */

package hcn.springboot.mail.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {

    //发送简单文本邮件
    void sendSimpleMail(String to, String subject, String content);
    //发送html邮件
    void sendHtmlMail(String to, String subject, String content);
    //发送带附件的邮件
    void sendAttachmentsMail(String to, String subject, String content, String filePath);
    //发送带静态资源的邮件:页面两张图片
    void sendInlineResourceMail(String to, String subject, String content, String rscPath1, String rscId1, String rscPath2, String rscId2);
    
}
