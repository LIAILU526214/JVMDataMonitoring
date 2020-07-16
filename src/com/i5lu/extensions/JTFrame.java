/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.i5lu.extensions;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import com.jtattoo.plaf.AbstractLookAndFeel;
import com.jtattoo.plaf.BaseRootPaneUI;
import com.jtattoo.plaf.BaseTitlePane;
import com.jtattoo.plaf.ColorHelper;
import com.jtattoo.plaf.JTattooUtilities;

public class JTFrame extends JFrame {
	private static final long serialVersionUID = 5219794846898234394L;
	private static final int sleepTime = 200;
    private static final int borderWidth = 8;
    private static final Insets borderInsets = new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
    private static Robot robot = null;
    private boolean opaqueBorder = false;
    private boolean lockUpdate = false;
    private boolean evenUICall = true;
    private BufferedImage imgTopBorder = null;
    private BufferedImage imgLeftBorder = null;
    private BufferedImage imgBottomBorder = null;
    private BufferedImage imgRightBorder = null;
    private Point capturePoint = null;
    private Dimension captureSize = null;

    public JTFrame() {
        super();
        init();
    }

    public JTFrame(GraphicsConfiguration gc) {
        super(gc);
        init();
    }

    public JTFrame(boolean opaqueBorder) {
        super();
        this.opaqueBorder = opaqueBorder;
        init();
    }

    public JTFrame(String title) {
        super(title);
        init();
    }

    public JTFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        init();
    }

    public JTFrame(String title, boolean opaqueBorder) {
        super(title);
        this.opaqueBorder = opaqueBorder;
        init();
    }

    private void init() {
        getRootPane().setBorder(new FrameBorder());
        getRootPane().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("UI")) {
                    if (evenUICall) {
                        evenUICall = false;
                        releaseBorder();
                    } else {
                        evenUICall = true;
                        captureBorder();
                    }
                }
            }
        });
        addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("windowMoving") || evt.getPropertyName().equals("windowResizing") || evt.getPropertyName().equals("windowMaximized")) {
                    releaseBorder();
                } else if (evt.getPropertyName().equals("windowMoved") || evt.getPropertyName().equals("windowResized") || evt.getPropertyName().equals("windowRestored")) {
                    captureBorder();
                }
            }
        });
        addWindowListener(new WindowAdapter() {

            public void windowActivated(WindowEvent e) {
                captureBorder();
            }

            public void windowDeactivated(WindowEvent e) {
                releaseBorder();
            }
        });
    }

    private Robot getRobot() {
        if (robot == null) {
            try {
                robot = new Robot();
            } catch (Exception ex) {
            }
        }
        return robot;
    }

    public void captureBorder() {
        if (!isShowing()) {
            return;
        }
        int titleHeight = 0;
        if (!opaqueBorder && getRootPane().getUI() instanceof BaseRootPaneUI) {
            BaseRootPaneUI ui = (BaseRootPaneUI) getRootPane().getUI();
            titleHeight = ui.getTitlePane().getHeight();
        }
        lockUpdate = true;
        capturePoint = getLocationOnScreen();
        captureSize = getSize();
        setBounds(capturePoint.x + borderWidth, capturePoint.y + borderWidth + titleHeight, captureSize.width - (2 * borderWidth), captureSize.height - (2 * borderWidth) - titleHeight);

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(sleepTime);
                } catch (Exception ex) {
                }
                Rectangle bounds = getBounds();
                Rectangle rect = new Rectangle(capturePoint.x, capturePoint.y, captureSize.width, borderWidth);
                imgTopBorder = getRobot().createScreenCapture(rect);
                rect.y += captureSize.height - borderWidth;
                imgBottomBorder = getRobot().createScreenCapture(rect);
                rect.y = capturePoint.y + borderWidth;
                rect.width = borderWidth;
                rect.height = captureSize.height - (2 * borderWidth);
                imgLeftBorder = getRobot().createScreenCapture(rect);
                rect.x += captureSize.width - borderWidth;
                imgRightBorder = getRobot().createScreenCapture(rect);

                if (!opaqueBorder && getRootPane().getUI() instanceof BaseRootPaneUI) {
                    BaseRootPaneUI ui = (BaseRootPaneUI) getRootPane().getUI();
                    BaseTitlePane tp = (BaseTitlePane)ui.getTitlePane();
                    rect.x = capturePoint.x + borderWidth;
                    rect.y = capturePoint.y + borderWidth;
                    rect.width = captureSize.width - (2 * borderWidth);
                    rect.height = tp.getHeight();
                    tp.setBackgroundImage(getRobot().createScreenCapture(rect));
                }
                lockUpdate = false;
                setBounds(capturePoint.x, capturePoint.y, captureSize.width, captureSize.height);
            }
        });
    }

    public void releaseBorder() {
        imgTopBorder = null;
        imgBottomBorder = null;
        imgLeftBorder = null;
        imgRightBorder = null;
        if (!opaqueBorder && getRootPane().getUI() instanceof BaseRootPaneUI) {
            BaseRootPaneUI ui = (BaseRootPaneUI) getRootPane().getUI();
            BaseTitlePane tp = (BaseTitlePane)ui.getTitlePane();
            tp.setBackgroundImage(null);
        }
        repaint();

    }

    public void paint(Graphics g) {
        if (!lockUpdate) {
            super.paint(g);
        }
    }

    private class FrameBorder implements Border {

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            boolean active = JTattooUtilities.isActive((JComponent) c);
            Color frameColor = AbstractLookAndFeel.getWindowBorderColor();
            Color borderColor = AbstractLookAndFeel.getWindowTitleColorLight();
            if (!active) {
                frameColor = AbstractLookAndFeel.getWindowInactiveBorderColor();
                borderColor = AbstractLookAndFeel.getWindowInactiveTitleColorLight();
            }

            if (imgTopBorder != null) {
                g.drawImage(imgTopBorder, x, y, null);
                g.drawImage(imgLeftBorder, x, y + borderWidth, null);
                g.drawImage(imgBottomBorder, x, y + height - borderWidth, null);
                g.drawImage(imgRightBorder, x + width - borderWidth, y + borderWidth, null);

                Graphics2D g2D = (Graphics2D) g;
                Composite composite = g2D.getComposite();
                AlphaComposite alpha = null;

                g.setColor(frameColor);

                // oben
                int x1 = x + 5;
                int y1 = y;
                int x2 = x + width - 5;
                int y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 3;
                x2 = x1 + 1;
                y1++;
                y2++;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + width - 4;
                x2 = x1 + 1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 2;
                x2 = x1;
                y1++;
                y2++;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + width - 2;
                x2 = x1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 1;
                x2 = x1;
                y1++;
                y2 = y1 + 1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + width - 1;
                x2 = x1;
                g2D.drawLine(x1, y1, x2, y2);

                // Unten
                x1 = x + 5;
                x2 = x + width - 5;
                y1 = y + height - 1;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 3;
                x2 = x1 + 1;
                y1 = y + height - 2;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + width - 4;
                x2 = x1 + 1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 2;
                x2 = x1;
                y1--;
                y2--;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + width - 2;
                x2 = x1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 1;
                x2 = x1;
                y1 -= 2;
                y2 = y1 + 1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + width - 1;
                x2 = x1;
                g2D.drawLine(x1, y1, x2, y2);

                // rechts
                x1 = x;
                y1 = y + 5;
                x2 = x1;
                y2 = y + height - 5;
                g2D.drawLine(x1, y1, x2, y2);
                // links
                x1 = x + width - 1;
                x2 = x1;
                g2D.drawLine(x1, y1, x2, y2);

                if (!opaqueBorder) {
                    alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85f);
                    g2D.setComposite(alpha);
                }

                g.setColor(ColorHelper.brighter(borderColor, 20));
                x1 = x + 5;
                y1 = y + 1;
                x2 = x + width - 5;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 1;
                y1 = y + borderWidth - 1;
                x2 = x + 1;
                y2 = y + height - borderWidth + 1;
                g2D.drawLine(x1, y1, x2, y2);

                g.setColor(borderColor);
                //g.setColor(Color.red);
                x1 = x + 3;
                y1 = y + 2;
                x2 = x + width - 3;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 2;
                y1 = y + 3;
                x2 = x + width - 2;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                y1++;
                y2++;
                g2D.drawLine(x1, y1, x2, y2);
                g2D.fillRect(x + 1, y + 5, width - 2, borderWidth - 6);
                x1 = x + 5;
                y1 = y + height - 2;
                x2 = x + width - 5;
                y2 = y1;

                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 3;
                y1 = y + height - 3;
                x2 = x + width - 3;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                x1 = x + 2;
                y1 = y + height - 4;
                x2 = x + width - 2;
                y2 = y1;
                g2D.drawLine(x1, y1, x2, y2);
                g2D.fillRect(x + 1, y + height - borderWidth + 1, width - 2, borderWidth - 5);

                g.fillRect(x + 2, y + borderWidth - 1, borderWidth - 3, height - (2 * borderWidth) + 2);
                g.fillRect(x + width - borderWidth + 1, y + borderWidth - 1, borderWidth - 2, height - (2 * borderWidth) + 2);

                //g.setColor(frameColor);
                g.drawRect(x + borderWidth - 1, y + borderWidth - 1, width - (2 * borderWidth) + 1, height - (2 * borderWidth) + 1);

                if (!opaqueBorder) {
                    g2D.setComposite(composite);
                }

            } else {
                //borderColor = ColorHelper.brighter(borderColor, 20);
                g.setColor(frameColor);
                g.drawRect(x, y, width - 1, height - 1);
                g.setColor(borderColor);
                g.fillRect(x + 1, y + 1, width - 2, borderWidth - 1);
                g.fillRect(x + 1, y + height - borderWidth, width - 2, borderWidth - 1);
                g.fillRect(x + 1, y + borderWidth, borderWidth - 1, y + height - (2 * borderWidth));
                g.fillRect(x + width - borderWidth, y + borderWidth, borderWidth - 1, y + height - (2 * borderWidth));
            }

        }

        public Insets getBorderInsets(Component c) {
            return borderInsets;
        }

        public boolean isBorderOpaque() {
            return false;
        }
    }
}
