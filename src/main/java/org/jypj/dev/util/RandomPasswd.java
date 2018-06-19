package org.jypj.dev.util;
import java.util.Random;  

public class RandomPasswd {
      
        //生成随机数字和字母,  
        public static String getStringRandom(int length) {  
            String val = "";  
            Random random = new Random();  
              
            //参数length，表示生成几位随机数  
            for(int i = 0; i < length; i++) {  
                  
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
                //输出字母还是数字  
                if( "char".equalsIgnoreCase(charOrNum) ) {  
                    //输出是大写字母还是小写字母  
                    int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                    val += (char)(random.nextInt(26) + temp);  
                } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                    val += String.valueOf(random.nextInt(10));  
                }  
            }
            String chars = "abcdefghijklmnopqrstuvwxyz";
            int num = new Random().nextInt(9);
            return chars.charAt((int)(Math.random() * 26))+Integer.toString(num)+val;
        }  
          
        public static void  main(String[] args) {  
            //测试  
            System.out.println(getStringRandom(6));
            System.out.println(MD5Utils.md5Encrypt("18855uN0"));
        }  
	
	
}
