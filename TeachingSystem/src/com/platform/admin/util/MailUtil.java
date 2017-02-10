package com.platform.admin.util;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

public class MailUtil {
	private static final Logger log = Logger.getLogger(MailUtil.class);

	public static final String HOSTNAME = PropertiesCache.getInstance().getProperties("oksdk", "mailHostName");
	public static final String ACCOUNT = PropertiesCache.getInstance().getProperties("oksdk", "fromAccount");
	public static final String PWD = PropertiesCache.getInstance().getProperties("oksdk", "fromAccountPwd");
	public static final String NICKNAME = PropertiesCache.getInstance().getProperties("oksdk", "nickName");

	public static void sendMail(String recipient, String subject, String content) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName(HOSTNAME); // 发送服务器
			email.setAuthentication(ACCOUNT, PWD); // 发送邮件的用户名和密码
			email.setFrom(ACCOUNT, NICKNAME); // 发送邮箱
			// 多收件人
			if (null != recipient) {
				for (String s : recipient.split(",")) {
					email.addTo(s); // 接收邮箱
				}
			}
			email.setSubject(subject);// 主题

			MimeMultipart mp = new MimeMultipart("related");// related意味着可以发送html格式的邮
			BodyPart body = new MimeBodyPart();// 正文
			body.setContent(content, "text/html;charset=UTF-8");
			mp.addBodyPart(body);// 发件内容
			email.setContent(mp);
			email.send();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 同步app信息线程异常发送邮件
	 */
	public static void sendAppSyncExceptionMail(Throwable e) {
		String subject = "同步app信息线程中断";
		String content = "同步app信息线程已中断，如非手动中断，请重新启动该线程！" + "<br/>" + e.getMessage();
		String receipts = PropertiesCache.getInstance().getString("okpay", "systemAdmin", "");
		sendMail(receipts, subject, content);
		log.info("send a resend Exception mail to " + receipts + " successed...");
	}

	public static void main(String[] args) {
		MailUtil.sendAppSyncExceptionMail(new Throwable("what`s fuck!"));
	}
}
