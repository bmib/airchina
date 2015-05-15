/**  
 * @Title: ImageServlet.java
 * @Package com.ibase.login
 * @date 2012-11-19 下午04:00:35
 */
package com.ass.framework.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ass.framework.common.config.Constants;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
  * @ClassName: ImageServlet
  * @Description: 验证码
  * @Author:liuyong
 */
public class ImageServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	private Random generator = new Random();

	private static char[] captchars = new char[] { 'a', 'b', 'c', 'd', 'e',
			'2', '3', '4', '5', '6', '7', '8', 'g', 'f', 'y', 'n', 'm', 'n',
			'p', 'w', 'x' };

	/**
	 * 随机产生定义的颜色
	 * 
	 * @return
	 */
	private Color getRandColor()
	{
		Random random = new Random();
		Color color[] = new Color[10];
		color[0] = new Color(32, 158, 25);
		color[1] = new Color(218, 42, 19);
		color[2] = new Color(31, 75, 208);
		return color[random.nextInt(3)];
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{

		int ImageWidth = 70;
		int ImageHeight = 26;

		int car = captchars.length - 1;
		/**
		 * 产生随机字符串
		 */
		String randomNumber = "";
		for (int i = 0; i < 4; i++){
			randomNumber += captchars[generator.nextInt(car) + 1];
		}
		/**
		 * 放入Session
		 */
		HttpSession session=req.getSession(true);
		session.setAttribute(Constants.VERIFY_CODE_FLAG, randomNumber.toUpperCase());

		/**
		 * 得到输出流
		 */
		OutputStream output = resp.getOutputStream();// 得到输出流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
		BufferedImage bi = new BufferedImage(ImageWidth+10, ImageHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bi.createGraphics();
		/**
		 * 设置背景色
		 */
		graphics.setColor(Color.white);
		graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
		graphics.setColor(Color.black);
		Font font = new Font("Fixedsys", Font.BOLD, 24);
		graphics.setFont(font);
		graphics.setColor(this.getRandColor());
		graphics.drawString(randomNumber, 10, 19);
		for (int i = 0; i < 60; i++){   
            int x = (int) (Math.random() * 78);   
            int y = (int) (Math.random() * 35);   
            int red = (int) (Math.random() * 255);   
            int green = (int) (Math.random() * 255);   
            int blue = (int) (Math.random() * 255);   
            graphics.setColor(new Color(red, green, blue));   
            graphics.drawOval(x, y, 1, 0);   
        }   
		int w = bi.getWidth();
		int h = bi.getHeight();
		shear(graphics, w, h, Color.white);
		resp.setContentType("image/jpg");
		encoder.encode(bi);
		output.close();
	}

	private void shear(Graphics g, int w1, int h1, Color color){
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	public void shearX(Graphics g, int w1, int h1, Color color){
		int period = generator.nextInt(16);
		boolean borderGap = true;
		int frames = 1;
		int phase = generator.nextInt(2);
		for (int i = 0; i < h1; i++){
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap){
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}

	public void shearY(Graphics g, int w1, int h1, Color color){
		int period = generator.nextInt(15); // 50;
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++){
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
							+ (6.2831853071795862D * (double) phase)
							/ (double) frames);

			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap){
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}
}
