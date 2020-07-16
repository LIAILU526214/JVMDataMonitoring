package com.i5lu.img;

import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class ImageHelper {

    private ImageHelper() {
    }

    public static ImageIcon loadImage(String name) {
        ImageIcon image = null;
        try {
            URL url = ImageHelper.class.getResource(name);
            if (url != null) {
                java.awt.Image img = Toolkit.getDefaultToolkit().createImage(url);
                if (img != null) {
                    image = new ImageIcon(img);
                }
            }
        } catch (Throwable ex) {
            System.out.println("ERROR: loading image " + name + " failed. Exception: " + ex.getMessage());
        }
        return image;
    }
    public static File loadImageFile(String name) {
    	File imageFile = null;
    	try {
    		URL url = ImageHelper.class.getResource(name);
    		if (url != null) {
    			imageFile = new File(url.getFile());
    		}
    	} catch (Throwable ex) {
    		System.out.println("ERROR: loading image " + name + " failed. Exception: " + ex.getMessage());
    	}
    	return imageFile;
    }
}
