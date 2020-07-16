package com.i5lu.app;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.i5lu.img.ImageHelper;
import com.i5lu.panel.RightPanel;

public class MainToolBar extends JToolBar {
	private static final long serialVersionUID = -5043312895801540268L;
	private ToolButton helpButton = null;
    private ImageIcon helpImage = null;
    private ToolButton portButton = null;
    private ImageIcon portImage = null;


    public MainToolBar() {
        super();
        setFloatable(false);
        setMargin(new Insets(2, 0, 2, 0));
        
        portImage = ImageHelper.loadImage("port.png");
        portButton = new ToolButton(portImage);
        portButton.setToolTipText("Port Setting");
        portButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainApp.rightPanel.showTypeSetting(RightPanel.JSTAT_COMMAND);
				MainApp.leftPanel.tree.setSelectionRow(0);
			}
		});
        
        helpImage = ImageHelper.loadImage("help.gif");
        helpButton = new ToolButton(helpImage);
        helpButton.setToolTipText("How To Use");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpDialog(getParent());
            }
        });
        add(portButton);
        addSeparator();
        add(helpButton);
    }

    private class ToolButton extends JButton {

		private static final long serialVersionUID = 7611175122341144861L;

		public ToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }

}
