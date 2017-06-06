package fengzi.library.mail;

import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;

import com.kysoft.library.R;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import fengzi.library.constants.AppConstant;
import fengzi.library.tool.PixTool;
import fengzi.library.tool.ToastUtil;

import static android.R.attr.width;

/**
 * 作者: {yff} time: 2017/4/18  09:14
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{}
 */

public class SendMail {

    private static final String fromMail = AppConstant.MailInfo.FROMMAIL;
    private static final String name = AppConstant.MailInfo.MAILNAME;
    private static final String password = AppConstant.MailInfo.MAILPASSWORD;
    private static final String toMail = AppConstant.MailInfo.TOMAIL;
    private static final String host = AppConstant.MailInfo.TOMAIL;

    static Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(name, password);
            }
        });
    }

    static Message createMessage(String subject, String messageBody,
                                 Session session) throws MessagingException,
            UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromMail, name));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                toMail, name));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    public static void sendMail(String appName,String tel, String msg,int phoneW,int phoneH) {

        tel = TextUtils.isEmpty(tel) ? "暂无联系方式" : tel;
        String content =  String.format("联系方式:%s,反馈内容(:%s,手机型号:%s,手机的宽高:%s * %s,App版本号:%s,手机当前版本%s)",
                tel,msg,Build.MODEL,phoneW,phoneH,Build.VERSION.SDK_INT,Build.VERSION.INCREMENTAL);
        Session session = createSessionObject();
        try {
            Message message = createMessage(appName + "用户反馈:" , content, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static class SendMailTask extends AsyncTask<Message, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ToastUtil.show("提交中,请稍等....");
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid) {
                ToastUtil.show("发送反馈成功,我们会考虑您的反馈改进我们的工作");
            } else {
                ToastUtil.show("发送失败,请再发送一次吧！^_^");
            }
        }

        @Override
        protected Boolean doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }
}
