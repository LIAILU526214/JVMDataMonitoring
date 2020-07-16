package com.i5lu.app;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MainMenuBar extends JMenuBar {
	private static final long serialVersionUID = -7115835933119664365L;
	private Component parent = null;
    private IBaseApp baseApp = null;
    private ButtonGroup plafGroup = null;
    
    public MainMenuBar(Component aParent) {
        parent = aParent;
        baseApp = (IBaseApp)parent;
        plafGroup = new ButtonGroup();
        

        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        JMenuItem menuItem = null;
        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic('x');
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.performExit();
            }
        });
        menu.add(menuItem);
        add(menu);

        menu = new JMenu("Look & Feel");
        menu.setMnemonic('L');
        
        JRadioButtonMenuItem radioMenuItem = null;
        
        radioMenuItem = new JRadioButtonMenuItem("Metal");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_METAL);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isMetalLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Nimbus");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_NIMBUS);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isNimbusLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Motif");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_MOTIF);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isMotifLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("System");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_SYSTEM);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isSystemLook());
        radioMenuItem.setEnabled(true);
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        menu.addSeparator();

        radioMenuItem = new JRadioButtonMenuItem("Acryl");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_ACRYL);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isAcrylLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Aero");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_AERO);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isAeroLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Aluminium");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_ALUMINIUM);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isAluminiumLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Bernstein");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_BERNSTEIN);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isBernsteinLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Fast");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_FAST);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isFastLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Graphite");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_GRAPHITE);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isGraphiteLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("HiFi");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_HIFI);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isHiFiLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Luna");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_LUNA);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isLunaLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("McWin");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_MCWIN);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isMcWinLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Mint");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_MINT);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isMintLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Noire");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_NOIRE);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isNoireLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Smart");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_SMART);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isSmartLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        radioMenuItem = new JRadioButtonMenuItem("Texture");
        radioMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                baseApp.updateLookAndFeel(GUIProperties.PLAF_TEXTURE);
            }
        });
        radioMenuItem.setSelected(baseApp.getGuiProps().isTextureLook());
        plafGroup.add(radioMenuItem);
        menu.add(radioMenuItem);

        if (GUIProperties.isCustomEnabled()) {
            menu.addSeparator();
            radioMenuItem = new JRadioButtonMenuItem("Custom");
            radioMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    baseApp.updateLookAndFeel(GUIProperties.PLAF_CUSTOM);
                }
            });
            radioMenuItem.setSelected(baseApp.getGuiProps().isCustomLook());
            plafGroup.add(radioMenuItem);
            menu.add(radioMenuItem);
        }

        add(menu);

        menu = new JMenu("Help");
        menu.setMnemonic('H');
        menuItem = new JMenuItem("Content...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                 new HelpDialog(parent);
            }
        });
        menuItem.setMnemonic('C');
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("About...");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutDialog(parent);
            }
        });
        menuItem.setMnemonic('A');
        menu.add(menuItem);
        add(menu);
        
    }


    public void updateLookAndFeel() {
    }

}
