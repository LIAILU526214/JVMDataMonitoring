package com.i5lu.app;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.i5lu.img.ImageHelper;
import com.i5lu.panel.RightPanel;

public class SelfIconOfTreeCellRender extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 2344671202446510668L;
	/** 
     * 重写父类DefaultTreeCellRenderer的方法 
     */  
    @Override  
    public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus)  
    {  
  
        //执行父类原型操作  
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,  
                row, hasFocus);  
  
        setText(value.toString());  
          
        if (sel)  
        {  
            setForeground(getTextSelectionColor());  
        }  
        else  
        {  
            setForeground(getTextNonSelectionColor());  
        }  
          
        //得到每个节点的TreeNode  
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;  
          
        //得到每个节点的text  
        String str = node.toString();         
          
        //判断是哪个文本的节点设置对应的值（这里如果节点传入的是一个实体,则可以根据实体里面的一个类型属性来显示对应的图标）  
        if (RightPanel.JSTAT_COMMAND.equals(str) ) 
        {  
            this.setIcon(ImageHelper.loadImage("port.png"));  
        }  
  
        return this;  
    }  
}
