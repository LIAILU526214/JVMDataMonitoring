package com.i5lu.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

import com.i5lu.properties.JstatCommondProperties;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = 6196742125569017952L;
    private JPanel jstat = null;
    public static final String ROOT_SETTING = "Setting";
    public static final String JSTAT_COMMAND = "jstat";
    public static final String REFRESH_FREQUENCY_SETTING = "Refresh Frequency Setting";
    
    public RightPanel(Component aParent) {
        super(new BorderLayout());
        init();
    }

    private void init() {
    	jstat = new JstatClassPanel();
    	add(jstat, BorderLayout.CENTER);
    }
    
    public  void showTypeSetting(String type) {
    	if (type == null) {
			return ;
		}
    	switch (type) {
    	case JSTAT_COMMAND:
    	case JstatCommondProperties.JSTAT_CLASS:
    		removeAll();
    		jstat = new JstatClassPanel();
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCUTIL:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCUTIL);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GC:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GC);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCCAPACITY:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCCAPACITY);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCNEW:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCNEW);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCNEWCAPACITY:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCNEWCAPACITY);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCOLD:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCOLD);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCPERMCAPACITY:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCPERMCAPACITY);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_GCOLDCAPACITY:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_GCOLDCAPACITY);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_COMPILER:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_COMPILER);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	case JstatCommondProperties.JSTAT_PRINTCOMPILATION:
    		removeAll();
    		jstat = new JstatClassPanel(JstatCommondProperties.JSTAT_PRINTCOMPILATION);
        	add(jstat, BorderLayout.CENTER);
    		repaint();    //重新绘制面板
    		validate();  //防止面板中的组件闪烁
    		break;
    	default:
    		System.err.println("Not Found Right Panel!");
    		break;
    	}
    }
    
    
    public void updateLookAndFeel() {
    }

}
