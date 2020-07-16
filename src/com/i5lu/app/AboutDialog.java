package com.i5lu.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.i5lu.img.ImageHelper;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 3860496745127026206L;
	private Component parent = null;

    public AboutDialog(Component aParent) {
        super(JOptionPane.getFrameForComponent(aParent), "About", true);
        parent = aParent;
        init();
    }

    private void init() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        SplashPanel splashPanel = new SplashPanel();
        
        if (!(parent instanceof JApplet)) {
        }
        contentPanel.add(splashPanel, BorderLayout.CENTER);
        setContentPane(contentPanel);
        setIconImage(ImageHelper.loadImage("isource32.png").getImage());
        showDlg();
    }

    private void showDlg() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dlgSize = new Dimension(605, 425);
        int dlgPosX = (screenSize.width / 2) - (dlgSize.width / 2);
        int dlgPosY = (screenSize.height / 2) - (dlgSize.height / 2);
        setLocation(dlgPosX, dlgPosY);
        setSize(dlgSize);
        //设置窗体不可变化
        setResizable(false);
        setVisible(true);
    }
}
