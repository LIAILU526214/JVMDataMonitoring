package com.i5lu.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.i5lu.gif.GifFrame;
import com.i5lu.img.ImageHelper;
import com.sun.imageio.plugins.gif.GIFImageMetadata;

public class SplashPanel extends JPanel {
	private static final long serialVersionUID = 7450672435098582570L;
	private ImageIcon splashImage = ImageHelper.loadImage("splash.jpg");
	private Dimension size = new Dimension(splashImage.getIconWidth(), splashImage.getIconHeight());
	private ImageReader reader;
	private int count = 0;
	private GifFrame[] frames;
	private Map<Integer, Integer[]> frameMap = new HashMap<Integer, Integer[]>();
	private int index = 0;
	private int delayFactor = 22;
	private int pointP = 0;
	private volatile static int START_Percentage_MAX = 10;
	public int START_Percentage = 0;

	public SplashPanel() {
		super();
		setForeground(new Color(233, 115, 103));
		setFont(new Font("Serif", Font.PLAIN, 28));

		try {
			ImageInputStream imageIn = ImageIO.createImageInputStream(ImageHelper.loadImageFile("loading.gif"));
			Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("gif");
			if (iter.hasNext()) {
				reader = iter.next();
			}
			reader.setInput(imageIn, false);
			count = reader.getNumImages(true);
			frames = new GifFrame[count];
			for (int i = 0; i < count; i++) {
				frames[i] = new GifFrame();
				frames[i].image = reader.read(i);
				frames[i].x = ((GIFImageMetadata) reader.getImageMetadata(i)).imageLeftPosition;
				frames[i].y = ((GIFImageMetadata) reader.getImageMetadata(i)).imageTopPosition;
				frames[i].width = ((GIFImageMetadata) reader.getImageMetadata(i)).imageWidth;
				frames[i].height = ((GIFImageMetadata) reader.getImageMetadata(i)).imageHeight;
				frames[i].disposalMethod = ((GIFImageMetadata) reader.getImageMetadata(i)).disposalMethod;
				frames[i].delayTime = ((GIFImageMetadata) reader.getImageMetadata(i)).delayTime;
				if (frames[i].delayTime == 0) {
					frames[i].delayTime = 1;
				}
			}
			for (int i = 1; i < count; i++) {
				if (frames[i].disposalMethod == 2) {
					// restoreToBackgroundColor
					frameMap.put(new Integer(i), new Integer[] { i });
					continue;
				}
				// doNotDispose
				int firstIndex = getFirstIndex(i);
				List<Integer> l = new ArrayList<Integer>();
				for (int j = firstIndex; j <= i; j++) {
					l.add(j);
				}
				frameMap.put(new Integer(i), l.toArray(new Integer[] {}));
			}
			Thread t = new Thread(new Delay());
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Dimension getPreferredSize() {
		return size;
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		splashImage.paintIcon(this, g, 0, 0);
		int x = 316;
		int y = 172;
		Object rh = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(getFont());
		g.setColor(Color.WHITE);
		g.drawString("航空总线数据实时监视记录软件", x - 220, y - 55);
		g.setFont(new Font("", 10, 20));
		g.drawString("Aviation Bus Data Real-time Monitoring Software", x - 220, y - 28);
		g.setColor(Color.WHITE);
		// g.setColor(getForeground());
		g.setFont(new Font("", 10, 12));
		g.drawString("V1.01", x + 175, y - 65);
		g.setColor(Color.black);
		g.setFont(new Font("Serif", 10, 12));
		g.drawString("因索思(北京)信息技术有限公司", x - 160, y + 212);
		g.drawString("http:\\\\www.isourceinv.com", x + 10, y + 212);
		if (index > 0 && frameMap.get(index) != null) {
			Integer[] array = frameMap.get(index);
			for (Integer i : array) {
				g.drawImage(frames[i].image.getScaledInstance(127, 98, Image.SCALE_SMOOTH), x - 68, y + 23, 127, 98,
						this);
			}
		}
//		Color color = new Color(1, 55,119);
		// 10, 210,254
		Color color = new Color(2, 183, 254);
		int mod = pointP % 12;
		if (START_Percentage >= START_Percentage_MAX) {
			START_Percentage = START_Percentage_MAX;
		} else {
			START_Percentage++;
		}
		if (mod >= 0 && mod < 4) {
			g.setColor(color);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			g.drawString("Starting .   " + START_Percentage + "%", x - 70, y + 138);
		}
		if (mod >= 4 && mod < 8) {
			g.setColor(color);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			g.drawString("Starting ..  " + START_Percentage + "%", x - 70, y + 138);
		}
		if (mod >= 8 && mod < 12) {
			g.setColor(color);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			g.drawString("Starting ... " + START_Percentage + "%", x - 70, y + 138);
		}
		pointP++;

//		g.setColor(color);
//		g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
//		g.drawString("Starting", x - 83 , y + 192);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, rh);
	}

	private int getFirstIndex(int index) {
		int tempIndex = index;
		while (tempIndex > 1) {
			if (tempIndex - 1 > 0 && frames[tempIndex - 1].disposalMethod == 2) {
				return index;
			}
			tempIndex--;
		}
		return tempIndex;
	}

	private class Delay implements Runnable {

		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(frames[index].delayTime * delayFactor);
				} catch (InterruptedException e) {
				}

				index++;
				if (index >= count) {
					index = 0;
				}
			}
		}
	}

	/**
	 * 设置当前进度增加值
	 * 
	 * @param max
	 * @param type 1表示原有值加max ,其他表示原有值赋值
	 */
	public static void setStartPercentageMax(int max, int type) {
		if (type == 1) {
			START_Percentage_MAX += max;
			return;
		}
		START_Percentage_MAX = max;
	}

}
