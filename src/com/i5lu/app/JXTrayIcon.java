package com.i5lu.app;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class JXTrayIcon extends TrayIcon {

    private JPopupMenu menu;

    private static JXTrayIcon trayIcon = null;
    private static final JDialog dialog = new JDialog((Frame) null, "TrayDialog");
    static {
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);
    }

    private static PopupMenuListener popupListener = new PopupMenuListener() {

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    dialog.setVisible(false);
                }
            });
        }

        public void popupMenuCanceled(PopupMenuEvent e) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    dialog.setVisible(false);
                }
            });
        }
    };


    public JXTrayIcon(Image image) {
        super(image);
        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                showJPopupMenu(e);
            }

            public void mouseReleased(MouseEvent e) {
                showJPopupMenu(e);
            }
        });
    }

    private void showJPopupMenu(MouseEvent e) {
        if (e.isPopupTrigger() && menu != null) {
            Dimension size = menu.getPreferredSize();
            dialog.setBounds(e.getX(), e.getY(), size.width, size.height);
            //dialog.setLocation(e.getX(), e.getY() - size.height);
            dialog.setVisible(true);
            menu.show(dialog.getContentPane(), 0, 0);
            dialog.toFront();
        }
    }

    public JPopupMenu getJPopuMenu() {
        return menu;
    }

    public void setJPopuMenu(JPopupMenu menu) {
        if (this.menu != null) {
            this.menu.removePopupMenuListener(popupListener);
        }
        this.menu = menu;
        menu.addPopupMenuListener(popupListener);
    }

    private static void createGui() {
        trayIcon = new JXTrayIcon(createImage());
        trayIcon.setJPopuMenu(createJPopupMenu());
        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static Image createImage() {
        BufferedImage i = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) i.getGraphics();
        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Float(0, 0, i.getWidth(), i.getHeight()));
        g2.dispose();
        return i;
    }

    static JPopupMenu createJPopupMenu() {
        final JPopupMenu m = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Item 1");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("bin drin");
                trayIcon.displayMessage("JXTrayIcon", "Hello World", MessageType.ERROR);
            }
        });
        m.add(menuItem);


        m.add(new JMenuItem("Item 2"));
        JMenu submenu = new JMenu("Submenu");
        submenu.add(new JMenuItem("item 1"));
        submenu.add(new JMenuItem("item 2"));
        submenu.add(new JMenuItem("item 3"));
        submenu.add(new JMenuItem("item 4"));
        submenu.add(new JMenuItem("item 5"));
        submenu.add(new JMenuItem("item 6"));
        submenu.add(new JMenuItem("item 7"));
        submenu.add(new JMenuItem("item 8"));
        submenu.add(new JMenuItem("item 9"));
        m.add(submenu);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        m.add(exitItem);
        return m;
    }

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                createGui();
            }
        });
    }

}
