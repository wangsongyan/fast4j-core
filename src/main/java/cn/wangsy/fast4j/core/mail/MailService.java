/**
 * 
 */
package cn.wangsy.fast4j.core.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 邮件服务
 * 
 * @author wangsy
 * @date 2016年12月7日下午1:24:19
 */
public class MailService {

	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/***
	 * 发送简单邮件
	 * 
	 * @param from 发件人
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 内容
	 * @param html html格式
	 * @throws MessagingException 
	 */
	public void send(String from,String to,String subject,String text,boolean html) throws MessagingException{
		send(from, new String[]{to}, subject, text, html);
	}

	/***
	 * 发送简单邮件
	 * 
	 * @param from 发件人
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 内容
	 * @param html html格式
	 * @throws MessagingException 
	 */
	public void send(String from,String[] to,String subject,String text,boolean html) throws MessagingException{
		MimeMessage msg = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
	    helper.setFrom(from);
	    helper.setTo(to);
	    helper.setSubject(subject);
	    helper.setText(text, html);
	    mailSender.send(msg);
	}
	
}
