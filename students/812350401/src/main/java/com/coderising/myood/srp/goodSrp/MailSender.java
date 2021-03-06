package com.coderising.myood.srp.goodSrp;

/**
 * Created by thomas_young on 24/6/2017.
 */
public class MailSender {
    private String fromAddress ;
    private String smtpHost;
    private String altSmtpHost;

    public MailSender(Configuration config){
        this.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        this.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
        this.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
    }

    public void sendMail(Mail mail){
        try{
            sendEmail(mail, this.smtpHost);
        }catch(Exception e){
            try{
                sendEmail(mail, this.altSmtpHost);
            }catch (Exception ex){
                System.out.println("通过备用 SMTP服务器发送邮件失败: " + ex.getMessage());
            }

        }
    }

    private void sendEmail(Mail mail, String smtpHost){
        //发送邮件
        System.out.println("开始发送邮件");
        MailUtil.sendEmail(mail, smtpHost, fromAddress);
    }
}
