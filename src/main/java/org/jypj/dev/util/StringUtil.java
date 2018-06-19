package org.jypj.dev.util;

public class StringUtil {
	
	/**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
	
	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null) {

			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if (!"".equals(str) && str != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/* JSON字符串特殊字符处理，比如：“\A1;1300”
     * @param s
     * @return String
     */
    public final static String stringToJson(String s) {     
        if(StringUtil.isEmpty(s)){
            return "";
        }
        StringBuffer sb = new StringBuffer();      
        for (int i=0; i<s.length();i++) {
         char c = s.charAt(i);  
          switch (c){
          case '\"':    
                 sb.append("\\\"");        
                 break;      
             case '\\':      
                 sb.append("\\\\");      
                 break;      
             case '/':      
                 sb.append("\\/");      
                 break;      
             case '\b':      
                 sb.append("\\b");      
                 break;      
             case '\f':      
                 sb.append("\\f");      
                 break;      
             case '\n':      
                 sb.append("\\n");      
                 break;      
             case '\r':      
                 sb.append("\\r");      
                 break;      
             case '\t':      
                 sb.append("\\t");      
                 break;      
             default:      
                 sb.append(c);   
          }
         }    
        return sb.toString();   
      }
}
