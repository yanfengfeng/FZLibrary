//package fengzi.library.exception;
//
//import android.os.AsyncTask;
//import java.io.UnsupportedEncodingException;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//
//import fengzi.library.tool.ToastUtil;
//
///***
// * 这里发送给邮箱
// * @author Administrator
// *
// */
//public class PostExceptionToEmailImpl implements CrashHandler.PostExceptionInter {
//
//	@Override
//	public void postException(Throwable ex, String deviceInfo) {
//		//在这里去处理
//
//		StringBuilder sb=new StringBuilder(deviceInfo);
//		//发送邮箱
//		sendMail("移动监管 异常信息",sb.append("Exception:"+ex.getMessage()).toString());
//	}
//
//
//
//	private void sendMail(String subject, String messageBody) {
//		Session session = FeedBackMailUtil.createSessionObject();
//		try {
//			Message message = FeedBackMailUtil.createMessage(subject, messageBody, session);
//			new SendMailTask().execute(message);
//		} catch (AddressException e) {
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//
//
//	private class SendMailTask extends AsyncTask<Message, Void, Boolean>{
//
//		@Override
//		protected void onPreExecute() {
//			super.onPreExecute();
//		}
//
//
//		@Override
//		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
//			if (result) {
//				ToastUtil.show("发送成功");
//			}
//		}
//
//		@Override
//		protected Boolean doInBackground(Message... params) {
//			try {
//				Transport.send(params[0]);
//			} catch (MessagingException e) {
//				e.printStackTrace();
//				return false;
//			}
//			return true;
//		}
//
//	}
//
//
//}
