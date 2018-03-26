package com.stylefeng.guns.modular.auth.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public class Util {

	private static Logger logger = Logger.getLogger(Util.class);

	/**接口返回信息设置*/
	public static final String RESULT="error";
	
	public static final String MESSAGE="information";
	
	/** 路径 */
	public static final String SYS_VIEWS="/views";
	
	public static final String SYS_API="api/";
	
	public static final String SYS_ADMIN="/admin/";
		
	/** 菜单级别 */
	public static final String TYPE_LEVEL="type_level";
	
	
	/**正常返回*/
	public static final String RESULT_RC_SUCCESS = "0000";
	
	/**已经结束*/
	public static final String RESULT_SL_SUCCESS = "0001";
	
	/** 正常返回没有查到数据 */
	public static final String RESULT_RC_NO_DATA = "1111";
	
	/** 系统异常 */
	public static final String RESULT_RC_EXCEPTION = "-9999";
	
	/**校验失败*/
	public static final String RESULT_RC_CHECK_FAIL = "0002";
	
	
	
	/** 默认分页大小 */
	public static final int DEFAULT_PAGE_COUNT = 20;

	/** 单次查询返回最大记录条数 */
	public static final int DEFAULT_PAGE_TOTAL_COUNT = 50;
	
	/**
	 * 产生6为随机数方法
	 * NextInt(final int min, final int max)
	 * 
	 * @return 返回随机数
	 */
	public static int sixRandomNum()
	{
		final int min = 100000;
		final int max = 999999;
		Random rand = new Random(); 
	    int tmp = Math.abs(rand.nextInt());
	    return tmp % (max - min + 1) + min;
	}
	
	/**
     * 验证字符中否有空格
     * @param str
     * @return
     */
    public static boolean checkTrim(String str){
		String rea = "^[^\\s]*";
		Pattern p = Pattern.compile(rea);
		Matcher m = p.matcher(str);
		return m.matches();
    }
	
	/**
	 * 自定义随机数方法
	 * 
	 * @return 返回对应位数的随机数
	 */
	public static String customNum(int minVal) {
		final int min = minVal;
		final int max = minVal * 10 - 1;
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return String.valueOf(tmp % (max - min + 1) + min);
	}

	public static int customNumTo(int maxVal) {
		int max = maxVal - 1;
		int min = 0;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	/**
	 * 获取用户登录token值
	 * 
	 * @param customerId
	 *            用户编号
	 * @return token值
	 */
	public static String getToken(String userId) {
		String s = UUID.randomUUID().toString();
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + userId + s.substring(24);
	}
	
	/**
	 * orderId:最大订单号
	 * usercode:用户ID
	 * 生成订单号
	 * @return
	 */
	public static String getDingDnaNo(Integer orderId,String usercode){
		// 生成订单号
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyyMMddhhmmss");
		String date = sdf.format(new Date());
		String prexf = String.format("%05d", orderId);
		String orderNo = String.format("H%s%s%s", usercode,
				date, prexf);
		return orderNo;
	}
	
	/**
	 * 订单号生产规则-----时间+5位数字(每天从0开始)
	 * @return
	 */
	public static String getDingDanTitles(){
		String titles = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String date = sdf.format(new Date());
		
		return titles;
	}
	
	/**
	 * 判断对象是否为null 集合和数组如果大小为0，也返回true 字符串如果是空字符串或者"null",返回true
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNull(Object o) {
		if (o == null) {
			return true;
		}

		if (o instanceof String) {
			String s = (String) o;
			if (s.trim().equals("")) {
				return true;
			}
			if (s.trim().equals("null")) {
				return true;
			}
			return false;
		} else if (o instanceof Object[]) {
			return ((Object[]) o).length == 0;
		} else if (o instanceof Collection<?>) {
			return ((Collection<?>) o).isEmpty();
		} else if (o instanceof Map<?, ?>) {
			return ((Map<?, ?>) o).isEmpty();
		}
		return false;
	}

	/**
	 * 判断两个对象是否相等
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean equal(Object o1, Object o2) {
		if (o1 == o2) {
			return true;
		}
		if (o1 != null && o1.equals(o2)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个字符串是否为数字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s) {
		if (isNull(s)) {
			return false;
		}

		Pattern p = Pattern.compile("\\d+\\.?\\d*|\\d*\\.?\\d+");
		Matcher m = p.matcher(s);
		if (!m.matches()) {
			return false;
		}

		return true;
	}

	/**
	 * 验证一个字符串能否被转换成相应的整数、浮点数
	 * 
	 * @param s
	 * @param type
	 * @return
	 */
	public static boolean isInvalidateNumber(String s, Class<?> type) {
		if (isNull(s)) {
			return false;
		}

		try {
			if (type == byte.class) {
				Byte.parseByte(s);
			} else if (type == short.class) {
				Short.parseShort(s);
			} else if (type == int.class) {
				Integer.parseInt(s);
			} else if (type == long.class) {
				Long.parseLong(s);
			} else if (type == float.class) {
				Float.parseFloat(s);
			} else if (type == double.class) {
				Double.parseDouble(s);
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串分隔为set
	 * 
	 * @param s
	 * @param split
	 * @return
	 */
	public static Set<String> string2Set(String s, String split) {
		Set<String> set = new HashSet<String>();
		String arr[] = s.split(split);
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}
		return set;
	}

	/**
	 * 将数组、集合、map的value连接成字符串 <功能详细描述>
	 * 
	 * @param array
	 * @param prefix
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static String JoinArray(Object array, String prefix) {
		StringBuilder sb = new StringBuilder();
		if (!isNull(array)) {
			String fix = prefix == null ? "" : prefix;
			if (array instanceof Object[]) {
				for (Object o : (Object[]) array) {
					sb.append(String.valueOf(o)).append(fix);
				}
			} else if (array instanceof Collection) {
				for (Object o : (Collection<?>) array) {
					sb.append(String.valueOf(o)).append(fix);
				}
			} else if (array instanceof Map) {
				for (Map.Entry<Object, Object> e : ((Map<Object, Object>) array)
						.entrySet()) {
					sb.append(String.valueOf(e.getValue())).append(prefix);
				}
			}

			// 去掉最后一个分隔符
			if (sb.length() != 0) {
				sb = sb.delete(sb.length() - prefix.length(), sb.length());
			}
		}
		return sb.toString();
	}

	/**
	 * 将null转为空字符串
	 * 
	 * @param source
	 * @return
	 */
	public static String nullToEmpty(String source) {
		if (isNull(source)) {
			source = "";
		}
		return source;
	}

	public static String toString(Object o) {
		if (o == null) {
			logger.warn("the object is null");
			return "";
		}
		if (o.getClass().getName().startsWith("java")
				|| o.getClass().getName().startsWith("javax")) {
			return o.toString();
		}
		StringBuffer result = new StringBuffer();
		try {
			Class<? extends Object> entityClass = o.getClass();
			result.append(entityClass.getName()).append("[");
			PropertyDescriptor[] props = Introspector.getBeanInfo(o.getClass(),
					Object.class).getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				Object value = props[i].getReadMethod().invoke(o);
				if (i > 0) {
					result.append(",");
				}
				result.append(props[i].getName()).append("=").append(value);
			}
			result.append("]");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 字符串一去除字符串二的内容，两字符串都是以逗号分隔
	 * 
	 * @param s1
	 * @param s2
	 * @return 去除后的字符串，并且顺序保持不变，以逗号分隔
	 */
	public static String cutString(String s1, String s2) {
		String s1s[] = s1.split(",");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < s1s.length; i++) {
			list.add(s1s[i]);
		}
		String s2s[] = s2.split(",");
		for (int i = 0; i < s2s.length; i++) {
			list.remove(s2s[i]);
		}
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			if (result.equals("")) {
				result += list.get(i);
			} else {
				result += "," + list.get(i);
			}
		}
		return result;
	}

	/**
	 * 判断一个值处于数组中的位置
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static int getArrayIndex(String s1[], String s2) {
		for (int i = 0; i < s1.length; i++) {
			if (s1[i].equals(s2)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 验证日期是否 “yyyy-MM-dd HH:mm:ss”格式的
	 * 
	 * @param date
	 * @return
	 */
	public static boolean validateDate(String date) {
		Pattern pattern = Pattern
				.compile("\\d{4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}");
		Matcher matcher = pattern.matcher(date);
		return matcher.matches();
	}
	
	/**
	 * 手机号码格式化 
	 * @param mobile
	 * @return
	 */
	public static String  mobileReplace(String mobile) {
		 String str = "";
	        for (int i = 0; i < mobile.length(); i++) {
	            if (i == mobile.length()-11) {
	                str += mobile.charAt(i);
	            } else if(i == mobile.length()-10) {
	             str += mobile.charAt(i);
	            }else if(i == mobile.length()-9) {
	             str += mobile.charAt(i);
	            }else if(i == mobile.length()-3) {
	             str += mobile.charAt(i);
	            }else if(i == mobile.length()-2) {
	             str += mobile.charAt(i);
	            }else if(i == mobile.length()-1) {
	             str += mobile.charAt(i);
	            }else {
	              str += "*";
	            }
	        }	       
	        return str;
	}
	
	/**
	 * 邮箱格式化
	 * @param mobile
	 * @return
	 */
	public static String  emailReplace(String email) {
		email=email.replaceAll(email.substring(4,email.lastIndexOf("@")),"*****"); 
		return email;
	}

	public static void main(String main[]) {
		String s = "475888864@qq.com";
		s=s.replaceAll(s.substring(4,s.lastIndexOf("@")),"*****");    
		System.out.println(s);
	}
}
