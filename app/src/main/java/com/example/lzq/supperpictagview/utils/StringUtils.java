package com.example.lzq.supperpictagview.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 */
public class StringUtils {
	static String TAG = "StringUtils";
	
	/**
	 * 设置超链接
	 * @param context
	 * @param textView 设置超链接的textview
	 * @param contentId 超链接的内容
	 * @param linkId  超链接的内容对应的http地址
	 * @param start   开始位置
	 * @param end  结束位置
	 */
	
	public static void setLink(Context context, TextView textView, int contentId, int linkId, int start, int end){
      SpannableString sp = new SpannableString(context.getResources().getString(contentId));
      sp.setSpan(new URLSpan(context.getResources().getString(linkId))
      					, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      textView.setText(sp);    
      textView.setMovementMethod(LinkMovementMethod.getInstance());
      
	}

	/**
	 * 给某段内容加上颜色
	 * 
	 * @param color
	 *            String类型的颜色值
	 * @param content
	 *            变颜色的内容
	 * @return
	 */
	public static String setTextColor(String color, String content) {
		return "<font color='" + color + "'><b>" + content + "</b></font>";
	}

	/**
	 * 隐藏软键盘
	 */
	public static void hideSoftInputMode(Context context, View windowToken) {
		((InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(windowToken.getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
	}


	/**
	 * 判断字符串是否为空
	 * @param str
	 * @param includeBlank 是否去除空格  true 做trim false 不做trim
	 * @return
	 */
	public static boolean isEmpty(String str, boolean includeBlank) {
		return (str==null || includeBlank?(str.trim().length() == 0):(str.length() == 0));
	}
	/**
	 * 
	 * @Description:字符串是否为空
	 * @author: sunny
	 * @date:2012-4-11
	 */
	public static boolean isEmptyString(final String str) {
		return str == null || str.length() < 1 || str.equals("");
	}

	/**
	 * 设置字符串中高亮的字
	 * 
	 * @param wholeText
	 *            原始字符串
	 * @param spanableText
	 *            高亮的字符串
	 * @return 高亮后的字符串
	 */
	public static SpannableString getSpanableText(String wholeText,
                                                  String spanableText) {
		if (TextUtils.isEmpty(wholeText))
			wholeText = "";
		SpannableString spannableString = new SpannableString(wholeText);
		if (spanableText.equals(""))
			return spannableString;
		wholeText = wholeText.toLowerCase();
		spanableText = spanableText.toLowerCase();
		int startPos = wholeText.indexOf(spanableText);
		if (startPos == -1) {
			int tmpLength = spanableText.length();
			String tmpResult = "";
			for (int i = 1; i <= tmpLength; i++) {
				tmpResult = spanableText.substring(0, tmpLength - i);
				int tmpPos = wholeText.indexOf(tmpResult);
				if (tmpPos == -1) {
					tmpResult = spanableText.substring(i, tmpLength);
					tmpPos = wholeText.indexOf(tmpResult);
				}
				if (tmpPos != -1)
					break;
				tmpResult = "";
			}
			if (tmpResult.length() != 0) {
				return getSpanableText(wholeText, tmpResult);
			} else {
				return spannableString;
			}
		}
		int endPos = startPos + spanableText.length();
		do {
			endPos = startPos + spanableText.length();
			spannableString.setSpan(new BackgroundColorSpan(Color.YELLOW),
					startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			startPos = wholeText.indexOf(spanableText, endPos);
		} while (startPos != -1);
		return spannableString;
	}

	/**
	 * 判断手机是否合法
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNum(String mobiles) {
		String rule = "^[1][3-8]+\\d{9}$";
		//String rule ="^1[0-9]{10}$";
		Pattern regex = Pattern.compile(rule);
		Matcher matcher = regex.matcher(mobiles);
		boolean b = matcher.matches();
		return b;
	}
	/**
	 * 判断密码
	 *
	 * @param pwd;
	 * @return
	 */
//    /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/
//    "^[a-zA-Z0-9_\u4e00-\u9fa5]+$"
	public static boolean isPwd(String pwd) {
//		String rule = "^[[a-zA-Z0-9_\u4e00-\u9fa5]+]{4,16}$";
		String rule = "^(?!([a-zA-Z]+|\\d+)$)[a-zA-Z\\d]{6,16}$";
		Pattern regex = Pattern.compile(rule);
		Matcher matcher = regex.matcher(pwd);
		boolean b = matcher.matches();
		return b;
	}
	
	/**
	 * 判断是否为六位数字
	 * @param code
	 * @return
	 */
	public static boolean isClassCode(String code){
		String rule ="^\\d{6}$";
		Pattern regex = Pattern.compile(rule);
		Matcher matcher = regex.matcher(code);
		boolean b = matcher.matches();
		return b;
	}

	/**
	 * @Description:是否为email格式
	 */
	public static boolean isEmail(String strEmail) {
		String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]*@[a-zA-Z0-9]"
				+ "[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

    /**
     * 判断是否是整数
     * @param str
     * @return
     */
    public static boolean isIntegers(String str) {
        String strPattern = "^-?//d+$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

	/**
	 * 如果文字大于屏幕宽度，则根据screenWidth去适配截取字符串
	 * 
	 * @param s
	 * @param screenWidth
	 * @return
	 */
	public static String parseString(String s, int screenWidth) {
		String s1 = "";
		int length = 0;
		int maxCharacters = screenWidth / 13 + 1;

		if (s.getBytes().length >= maxCharacters) {
			while (s1.getBytes().length < maxCharacters) {
				s1 += s.toCharArray()[length++];
			}
			if (s1.getBytes().length >= maxCharacters)
				s1 += "...";
			else
				s1 += "...";
		} else {
			s1 = s;
		}
		return s1;
	}


	/**
	 * 判断一个字符是否是汉字
	 * 
	 * @param a
	 * @return
	 */
	private static boolean isChinese(char a) {
		return ((int) a >= 19968 && (int) a <= 171941);
	}

	/**
	 * 按照字符串中2个字母的长度为1，一个汉字的长度为1的规律,获取一个字符串的长度
	 * 
	 * @param str
	 * @return
	 */
	private static int getStringLength(String str) {
		int cnt = 0, ent = 0;
		for (int i = 0; i < str.length(); i++) {
			if (isChinese(str.charAt(i))) {
				cnt++;
			} else {
				ent++;
			}
		}
		return cnt + ent / 2;
	}

	public static String trim(String resource) {
		return resource.trim().replaceAll(" ", "");
	}

	/**
	 * 对我的事务展示的文字进行截取
	 * 
	 * @param s
	 * @return
	 */
	public static String getSplitString(String s) {
		StringBuffer sb = new StringBuffer();
		if (s.getBytes().length > 16) {
			for (char a : s.toCharArray()) {
				if (sb.length() < 8) {
					sb.append(a);
				}
			}
			sb.append("...");
		}

		return sb.toString();
	}

	/**
	 * 对URL的中文字符进行编码，
	 * 
	 * @param words
	 * @return
	 */
	public static String parseChineseWord(String words) {
		StringBuffer sb = new StringBuffer();
		for (char c : words.toCharArray()) {
			if (c >= 0x0391 && c <= 0xFFE5) {
				try {
					String encode = URLEncoder.encode(c + "", "UTF-8");
					sb.append(encode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 截取图片名
	 * prefix is l_
	 * @Description:
	 * @author: sunny
	 * @date:2012-7-31
	 */
	public static String splitImageName(String url, boolean isRemovePrefix_l) {
		if(isEmptyString(url)){
			return "";
		}
		int lastindex = url.lastIndexOf("/");
		if(lastindex<=0)
			return "";
		String subStr = url.substring(isRemovePrefix_l?lastindex+3:lastindex+1);
		url = url.substring(0, lastindex+1)+subStr;
		return url;
		
	}
	/**
	 * 把大写转成小写 
	 * 
	 * @Description:
	 * @author: sunny
	 * @date:2012-7-31
	 */
	public static String upToLowerCase(String str) {
		char[] ch = str.toCharArray();
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < str.length(); i++) {
			if (ch[i] >= 'A' && ch[i] <= 'Z') {
				buffer.append(String.valueOf(ch[i]).toLowerCase());
			} else {
				buffer.append(String.valueOf(ch[i]));
			}
		}
		return buffer.toString();
	}

    /**
     * double保留小数位
     * 例子：decimal传("#.0000000000")10位
     */
    public static double torReservedDecimal1(double d,String decimal){
        DecimalFormat df=new DecimalFormat(decimal);
        double f = Double.valueOf(df.format(d));
        return f ;
    }

    /**
     * double保留小数位
     * 例子：decimal传("#.0000000000")10位
     */
    public static double torReservedDecimal(double d,int decimal){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(10);
        double f = Double.valueOf(nf.format(d));
        return f ;
    }

}
