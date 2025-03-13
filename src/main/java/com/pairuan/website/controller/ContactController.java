package com.pairuan.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pairuan.website.common.result.R;
import com.pairuan.website.entity.Contacts;
import com.pairuan.website.service.ContactsService;

import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * 联系我们Controller
 */
@Slf4j
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private ContactsService contactsService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${spring.mail.username}")
    private String mailTo;

    /**
     * 联系表单数据
     */
    @Data
    public static class ContactForm {
        private String name;
        private String email;
        private String message;
    }

    /**
     * 验证邮箱格式
     */
    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 发送邮件（带重试机制）
     */
    @Retryable(
        value = Exception.class,
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    private void sendEmail(MimeMessage message) {
        mailSender.send(message);
    }

    /**
     * 发送联系表单到邮箱
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
    public R<Contacts> sendContact(@RequestBody ContactForm form) {
        log.info("接收到联系我们表单: {}", form);
        
        // 参数验证
        if (form.getName() == null || form.getName().trim().isEmpty()) {
            log.warn("姓名为空");
            return R.error("姓名不能为空");
        }
        if (form.getEmail() == null || form.getEmail().trim().isEmpty()) {
            log.warn("邮箱为空");
            return R.error("邮箱不能为空");
        }
        if (!isValidEmail(form.getEmail().trim())) {
            log.warn("邮箱格式不正确: {}", form.getEmail());
            return R.error("邮箱格式不正确");
        }
        if (form.getMessage() == null || form.getMessage().trim().isEmpty()) {
            log.warn("内容为空");
            return R.error("内容不能为空");
        }
        
        try {
            log.info("开始配置邮件消息");
            // 创建MIME邮件消息
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            
            // 设置邮件属性
            log.info("设置邮件发件人: {}", mailFrom);
            helper.setFrom(mailFrom);
            log.info("设置邮件收件人: {}", mailTo);
            helper.setTo(mailTo);
            helper.setSubject("新的联系表单提交");
            
            // 构建HTML格式的邮件内容
            String htmlContent = String.format("""
                <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #333;">新的联系表单提交</h2>
                    <div style="background-color: #f5f5f5; padding: 15px; border-radius: 5px;">
                        <p><strong>姓名：</strong>%s</p>
                        <p><strong>邮箱：</strong>%s</p>
                        <p><strong>内容：</strong></p>
                        <div style="background-color: white; padding: 10px; border-radius: 3px;">
                            %s
                        </div>
                    </div>
                    <p style="color: #666; font-size: 12px; margin-top: 20px;">
                        此邮件由系统自动发送，请勿直接回复
                    </p>
                </body>
                </html>
                """,
                form.getName(),
                form.getEmail(),
                form.getMessage().replace("\n", "<br/>")
            );
            
            log.info("设置邮件内容");
            helper.setText(htmlContent, true);
            
            // 发送邮件（带重试机制）
            log.info("开始发送邮件");
            sendEmail(message);
            log.info("邮件发送成功");
            
            // 保存联系信息到数据库
            Contacts contact = new Contacts();
            contact.setName(form.getName());
            contact.setEmail(form.getEmail());
            contact.setMessage(form.getMessage());
            contact = contactsService.saveContact(contact);
            
            return R.success(contact);
            
        } catch (Exception e) {
            log.error("邮件发送失败: {}", e.getMessage(), e);
            return R.error("邮件发送失败：" + e.getMessage());
        }
    }
} 