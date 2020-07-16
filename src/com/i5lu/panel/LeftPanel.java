package com.i5lu.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.i5lu.app.GUIProperties;
import com.i5lu.app.MainApp;
import com.i5lu.app.SelfIconOfTreeCellRender;
import com.i5lu.boarder.JTBorderFactory;
import com.i5lu.img.ImageHelper;
import com.i5lu.properties.JstatCommondProperties;
import com.jtattoo.plaf.AbstractLookAndFeel;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = -5021676184436342040L;
	private JScrollPane treePanel = null;
    private JScrollPane listPanel = null;
    private JScrollPane controlPanel = null;
    private JSplitPane innerSplitPane = null;
    private JSplitPane outerSplitPane = null;
    private boolean initialized = false;
    public JTree tree = null;

    public LeftPanel(Component aParent) {
        super(new BorderLayout());
        init();
        initialized = true;
    }

    public void updateUI() {
        if (initialized) {
            if (UIManager.getLookAndFeel().getName().equals("Texture")) {
                Border innerBorder = JTBorderFactory.createTitleBorder(ImageHelper.loadImage("setting.gif"), "setting", 0, 0);
                Border outerBorder = BorderFactory.createEmptyBorder(4, 8, 4, 0);
                treePanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
                treePanel.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
            } else {
                treePanel.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("setting.gif"), "setting", 0, 0));
            }
            
            //controlPanel.getVerticalScrollBar().putClientProperty("JComponent.sizeVariant", "large");
        }
        super.updateUI();
    }

    private void init() {
        initControls();
    }

    private void initControls() {
        treePanel = createTree();
        treePanel.setMinimumSize(new Dimension(80, 60));

        innerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, treePanel, listPanel);
        innerSplitPane.setDividerLocation(160);
        innerSplitPane.setMinimumSize(new Dimension(100, 100));
        innerSplitPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);

        outerSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, innerSplitPane, controlPanel);
        outerSplitPane.setDividerLocation(360);
        outerSplitPane.setMinimumSize(new Dimension(100, 200));
        outerSplitPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
        add(outerSplitPane, BorderLayout.CENTER);
    }

    private JScrollPane createTree() {
    	

         // 创建根节点
         DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Setting");
         // 创建二级节点
         DefaultMutableTreeNode jstatNode = new DefaultMutableTreeNode(RightPanel.JSTAT_COMMAND);
         //三级节点
         DefaultMutableTreeNode jclass = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_CLASS);
         DefaultMutableTreeNode jcompiler = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_COMPILER);
         DefaultMutableTreeNode jgc = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GC);
         DefaultMutableTreeNode jgccapacity = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCCAPACITY);
         DefaultMutableTreeNode jgcnew = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCNEW);
         DefaultMutableTreeNode jgcnewcapacity = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCNEWCAPACITY);
         DefaultMutableTreeNode jgcold = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCOLD);
         DefaultMutableTreeNode jgcoldcapacity = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCOLDCAPACITY);
         DefaultMutableTreeNode jgcpermcapacity = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCPERMCAPACITY);
         DefaultMutableTreeNode jgcutil = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_GCUTIL);
         DefaultMutableTreeNode jprintcompilation = new DefaultMutableTreeNode(JstatCommondProperties.JSTAT_PRINTCOMPILATION);
         // 创建三级级节点
         jstatNode.add(jclass);
         jstatNode.add(jcompiler);
         jstatNode.add(jgc);
         jstatNode.add(jgccapacity);
         jstatNode.add(jgcnew);
         jstatNode.add(jgcnewcapacity);
         jstatNode.add(jgcold);
         jstatNode.add(jgcoldcapacity);
         jstatNode.add(jgcpermcapacity);
         jstatNode.add(jgcutil);
         jstatNode.add(jprintcompilation);
         rootNode.add(jstatNode);
        tree = new JTree(rootNode) {
			private static final long serialVersionUID = -326414979504479631L;

			public Insets getInsets() {
                return new Insets(5, 5, 5, 5);
            }
            
        };
     // "JTree.lineStyle"客户属性 "None" "Horizontal" "Angled
        tree.putClientProperty("JTree.lineStyle", "Horizontal");// 将树设为水平分隔风格
        tree.setRootVisible(false);
        // 设置树显示根节点句柄
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new SelfIconOfTreeCellRender());
//        tree.setToolTipText("aaaa");
        tree.requestFocus();
        tree.setSelectionRow(0);
     // 设置节点选中监听器
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
            	TreePath path = e.getPath();
            	String string = path.getLastPathComponent().toString();
//                System.out.println("当前被选中的节点: " + string);
            	if (MainApp.rightPanel == null) {
            		System.err.println("右侧面板找不到!");
					return;
				}
            	MainApp.rightPanel.showTypeSetting(string);
            }
        });
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setBorder(JTBorderFactory.createTitleBorder(ImageHelper.loadImage("setting.gif"), "setting", 0, 0));
        return scrollPane;
    }


    public void updateLookAndFeel() {
        //fillThemesList();
    	
        LookAndFeel laf = UIManager.getLookAndFeel();
        if (laf instanceof AbstractLookAndFeel) {
           // taaCheck.setSelected(AbstractLookAndFeel.getTheme().isTextAntiAliasingOn());
           // patternCheck.setSelected(AbstractLookAndFeel.getTheme().isBackgroundPatternOn());
            //centerWindowTitleCheck.setSelected(AbstractLookAndFeel.getTheme().isCenterWindowTitleOn());
        } else {
            //taaCheck.setSelected(false);
            //patternCheck.setSelected(false);
            //centerWindowTitleCheck.setSelected(false);
        }
        if (tree != null) {
			tree.updateUI();
		}else {
			createTree();
		}
    }

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
		 if (tree != null) {
				tree.updateUI();
			}else {
				createTree();
			}
	}
    
    
}