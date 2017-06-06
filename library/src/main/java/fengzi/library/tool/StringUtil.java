package fengzi.library.tool;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author M.c
 * 
 * @isBlank 判断字符串是否为空
 * @isChinese 判断是否为中文
 * @countStringLength 判断字符串是否超出长度
 * @isNum 判断是否为纯数字
 * @DoubleToAmountString double转为字符串,保留小数位
 * @removeAllChar 去掉字符串中某一字符
 * @getInitialAlphaEn 获取英文首字母 并大写显示,不为英文字母时,返回"#"
 * @getEditText 获取非空edittext
 * @getMd5Value 字符串MD5加密
 */
public class StringUtil {

	/**
	 * 26英文字母字符串
	 */
	public static String[] ENGLIST_LETTER = { "A", "B", "C", "D", "E", "F",
			"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };
 
	
	/**
	 * 集合装维数组 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] list2Array(List<T> list) {
		if(list == null){
			return null;
		}
		return (T[]) list.toArray();
	}
	
	/**
	 * 数组转化为集合
	 * @param array
	 * @return
	 */
	public static <T> List<T> array2List(T[] array) {
		if(array == null){
			return null;
		}
		List<T> userList = new ArrayList<T>(Arrays.asList(array));
		return userList;
	}

	/**
	 * 判断是否是中文
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否超过指定字符数(待测试)
	 * 
	 * @param content
	 * @param stringNum
	 *            指定字符数 如：140
	 * @return
	 */
	public static boolean countStringLength(String content, int stringNum) {
		int result = 0;
		if (content != null && !"".equals(content)) {
			char[] contentArr = content.toCharArray();
			if (contentArr != null) {
				for (int i = 0; i < contentArr.length; i++) {
					char c = contentArr[i];
					if (isChinese(c)) {
						result += 3;
					} else {
						result += 1;
					}
				}
			}
		}
		if (result > stringNum * 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否为无符号数字
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isNum(String num) {
		String check = "^[0-9]*$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(num);
		boolean isMatched = matcher.matches();
		return isMatched;
	}
 

	/**
	 * 将double转换为字符串，保留小数点位数
	 * 
	 * @param doubleNum
	 *            需要解析的double
	 * @param digitNum
	 *            小数点位数，小于0则默认0
	 * @return
	 */
	public static String doubleToAmountString(Double doubleNum, int digitNum) {
		if (digitNum < 0)
			digitNum = 0;
		StringBuilder strBuilder = new StringBuilder("#");
		for (int i = 0; i < digitNum; i++) {
			if (i == 0)
				strBuilder.append(".#");
			else
				strBuilder.append("#");
		}
		DecimalFormat df = new DecimalFormat(strBuilder.toString());
		return df.format(doubleNum);
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替
	 * 
	 * @param str
	 * @return
	 */
	public static String getInitialAlphaEn(String str) {
		if (str == null) {
			return "#";
		}

		if (str.trim().length() == 0) {
			return "#";
		}

		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是26字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase(Locale.getDefault()); // 大写输出
		} else {
			return "#";
		}
	}

	/**
	 * 去除String中的某一个字符
	 * 
	 * @param orgStr
	 * @param splitStr
	 *            要去除的字符串
	 * @return
	 */
	public static String removeStringIntChar(String orgStr, String splitStr) {
		String[] strArray = orgStr.split(splitStr);
		String res = "";
		for (String tmp : strArray) {
			res += tmp;
		}
		return res;
	}

	/**
	 * 判断是否为金额
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @name ismobile
	 * @Todo 是否手机号码
	 * @return_type boolean
	 * @param num
	 * @return
	 */
	public static boolean isMobile(String num) {
		if (TextUtils.isEmpty(num)) {
			return false;
		}
		String check = "^1[3|4|5|7|8][0-9]\\d{8}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(num);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 是否是电话
	 * @param num
	 * @return
	 */
	public static boolean isTel(String num) {
		if (TextUtils.isEmpty(num)) {
			return false;
		}
	   Pattern regex = Pattern.compile("^(0\\d{2}-\\d{1,10})|(0\\d{3}-\\d{1,10})|\\d{1,10}$");
       Matcher matcher = regex.matcher(num);
       boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 
	 * @name isEmail
	 * @Todo 是否为邮箱
	 * @return_type boolean
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern pattern = Pattern
				.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 改变字符串中某些紫的颜色
	 * @param content
	 *            整体内容
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static SpannableStringBuilder changStringColor(String content,
			int start, int end, int color) {

		SpannableStringBuilder builder = new SpannableStringBuilder(content);
		ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
		builder.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return builder;
	}


	/**
	 * 改变字符串中某些紫的颜色
	 * @param content
	 *            整体内容
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static SpannableStringBuilder changStringColor(String content,
			int[] start, int[] end, int color) {
		SpannableStringBuilder builder = new SpannableStringBuilder(content);
		for (int i = 0; i < end.length; i++) {
			ForegroundColorSpan redSpan = new ForegroundColorSpan(color);
			builder.setSpan(redSpan, start[i], end[i],
					Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		return builder;
	}

	/**
	 * 格式化数字
	 * 
	 * @name test_formatFileSize
	 * @Todo TODO
	 * @return_type void
	 */
	public static String test_formatFileSize(float size) {
		NumberFormat df1 = new DecimalFormat("##.##");
		String result = df1.format(size);
		return result;
	}
 

 

	public static String lengthformat(float number) {

		if (number > 1000) {
			return test_formatFileSize(number / 1000) + "千米";
		} else {
			return test_formatFileSize(number) + "米";
		}

	}

	public static String GetEdittextValue(EditText editText) {
		if (editText == null) {
			return null;
		}
		return editText.getText().toString().trim();
	}

	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public static String getValue(String str) {
		return TextUtils.isEmpty(str) ? "" : str;

	}

	/**
	 * 保留小数
	 * 
	 * @param num
	 *            保留几位小数
	 * @param value
	 *            需要改变的值
	 * @author String 返回的结果值
	 */
	public static String getDecimal(int num, float value) {
		StringBuffer buffer = new StringBuffer("0.");
		for (int i = 0; i < num; i++) {
			buffer.append("0");
		}
		DecimalFormat format = new DecimalFormat(buffer.toString());
		return format.format(value);
	}

	/**
	 * 保留小数
	 * 
	 * @param num
	 *            保留几位小数
	 * @param value
	 *            需要改变的值
	 * @author String 返回的结果值
	 */
	public static String getDecimal(int num, double value) {
		StringBuffer buffer = new StringBuffer("0.");
		for (int i = 0; i < num; i++) {
			buffer.append("0");
		}
		DecimalFormat format = new DecimalFormat(buffer.toString());
		return format.format(value);
	}

	/**
	 * 身份号判断(简单的判断)
	 * @return
	 */
	public static boolean isidCardNo(String card){
		if(TextUtils.isEmpty(card) || card.length() != 18){
			return  false;
		}
		String x = card.substring(card.length() - 1,card.length());
		if(x.toLowerCase().equals("x")){
			return  isNum(card.substring(0,card.length() - 1));
		}else{
			return  isNum(card);
		}
	}

 
}
