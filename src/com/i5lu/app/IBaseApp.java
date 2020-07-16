package com.i5lu.app;

import javax.swing.JTabbedPane;

public interface IBaseApp {

    public GUIProperties getGuiProps();
    public void setMainTabbedPane(JTabbedPane tabPane);
    public JTabbedPane getMainTabbedPane();
    public void updateLookAndFeel(String lf);
    public void updateTheme(String theme);
    public void setLookAndFeelFlag(String propName, boolean flag);
    public void performExit();

}
