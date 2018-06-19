package org.jypj.dev.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author QiCai
 *
 */
public class ValidateCode {
	
	private static Logger logger=Logger.getLogger(ValidateCode.class);
	
	private static int width = 105;//定义图片的width
	private static int height = 32;//定义图片的height
	private static int codeCount = 5;//定义图片上显示验证码的个数
	private static int fontHeight = 25;//字体大小
	private static int codeX = 15;
	private static int codeY = 25;
	private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J','K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W','X', 'Y', 'Z', 
		      	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a','b','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z'};
	
	public static BufferedImage getCode(HttpServletRequest request){
		//定义图像buffer
	    BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
	    Graphics gd = buffImg.getGraphics();
	    //创建一个随机数生成器类
	    Random random = new Random();
	    //将图像填充为白色
	    gd.setColor(Color.WHITE);
	    gd.fillRect(0, 0, width, height);

	    //创建字体，字体的大小应该根据图片的高度来定
	    Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
	    //设置字体
	    gd.setFont(font);

	    //画边框
	    gd.setColor(Color.BLACK);
	    gd.drawRect(0, 0, width - 1, height - 1);
	   
	    int red = 0, green = 0, blue = 0;
	    //随机产生N条干扰线，使图象中的认证码不易被其它程序探测到
	    for (int i = 0; i < 20; i++) {
	      red = random.nextInt(255);
		  green = random.nextInt(255);
		  blue = random.nextInt(255);
		  gd.setColor(new Color(red, green, blue));
	    	
	      int x = random.nextInt(width);
	      int y = random.nextInt(height);
	      int xl = random.nextInt(20);
	      int yl = random.nextInt(20);
	      gd.drawLine(x, y, x + xl, y + yl);
	    }

	    //randomCode用于保存随机产生的验证码，以便用户登录后进行验证
	    StringBuffer randomCode = new StringBuffer();

	    //随机产生codeCount数字的验证码
	    for (int i = 0; i < codeCount; i++) {
	      //得到随机产生的验证码数字
	      String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length-1)]);
	      //产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同
	      red = random.nextInt(255);
	      green = random.nextInt(255);
	      blue = random.nextInt(255);

	      //用随机产生的颜色将验证码绘制到图像中
	      gd.setColor(new Color(red, green, blue));
	      gd.drawString(code, (i + 1) * codeX, codeY);

	      //将产生的N个随机数组合在一起
	      randomCode.append(code);
	    }
	    
	    //将验证码保存到Session中
	    HttpSession session=request.getSession();
	    session.setAttribute("code", randomCode.toString());
	    logger.info("validateCode="+session.getAttribute("code"));
		return buffImg;
	}
	
}
