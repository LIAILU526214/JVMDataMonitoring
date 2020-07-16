package com.i5lu.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.i5lu.img.ImageHelper;
import com.i5lu.utils.StaticVar;

public class HelpDialog extends JDialog implements HyperlinkListener {
	private static final long serialVersionUID = -7033085753809394808L;
	private final String HELP_PATH = "/resources/help/";
    private final String INDEX_PROPERTIES = HELP_PATH + "index.properties";
    private final String HOME_PAGE = StaticVar.PROGRAME_PATH+"/jre/lib/"+HELP_PATH + "home.html";

    private ImageIcon prevIcon = null;
    private ImageIcon nextIcon = null;
    private ImageIcon homeIcon = null;
    private ImageIcon exitIcon = null;

    private JButton prevButton = null;
    private JButton nextButton = null;
    private JButton homeButton = null;
    private JButton exitButton = null;

    private JToolBar toolBar = null;
    private JTree indexTree = null;
    private JEditorPane helpPane = null;
    private Vector<TreePath> helpStack = null;
    private int helpStackIndex = 0;
    private boolean blockHelpStack = false;

    public HelpDialog(Component parent) {
        super(JOptionPane.getFrameForComponent(parent), "Help", true);
        init();
    }

    private void init() {
        helpStack = new Vector<TreePath>();
        helpStackIndex = 0;

        JPanel contentPanel = new JPanel(new BorderLayout());
        initToolBar();
        initIndexTree();
        initHelpPane();
        JPanel indexPanel = new JPanel(new BorderLayout());
        indexPanel.add(toolBar, BorderLayout.NORTH);
        indexPanel.add(new JScrollPane(indexTree), BorderLayout.CENTER);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, indexPanel, new JScrollPane(helpPane));
        //splitPane.setDividerLocation(240);
        contentPanel.add(splitPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
        showDlg();
    }

    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            JEditorPane pane = (JEditorPane)e.getSource();
            try {
                pane.setPage(e.getURL());
            	
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    private void showDlg() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dlgSize = new Dimension(840, 640);
        int dlgPosX = (screenSize.width / 2) - (dlgSize.width / 2);
        int dlgPosY = (screenSize.height / 2) - (dlgSize.height / 2);
        setLocation(dlgPosX, dlgPosY);
        setSize(dlgSize);
        setVisible(true);
    }

    private void initToolBar() {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        prevIcon = ImageHelper.loadImage("prev.gif");
        prevButton = new NoFocusButton(prevIcon);
        prevButton.setToolTipText("prev page");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (helpStackIndex > 0) {
                    blockHelpStack = true;
                    helpStackIndex--;
                    TreePath path = (TreePath)helpStack.get(helpStackIndex);
                    indexTree.setSelectionPath(path);
                    blockHelpStack = false;
                }
            }
        });

        nextIcon = ImageHelper.loadImage("next.gif");
        nextButton = new NoFocusButton(nextIcon);
        nextButton.setToolTipText("next page");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (helpStackIndex < (helpStack.size() - 1)) {
                    blockHelpStack = true;
                    helpStackIndex++;
                    TreePath path = (TreePath)helpStack.get(helpStackIndex);
                    indexTree.setSelectionPath(path);
                    blockHelpStack = false;
                }
            }
        });

        homeIcon = ImageHelper.loadImage("home.gif");
        homeButton = new NoFocusButton(homeIcon);
        homeButton.setToolTipText("home page");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                indexTree.setSelectionRow(0);
            }
        });

        exitIcon = ImageHelper.loadImage("exit.gif");
        exitButton = new NoFocusButton(exitIcon);
        exitButton.setToolTipText("quit help");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        toolBar.add(prevButton);
        toolBar.add(nextButton);
        toolBar.add(homeButton);
        toolBar.addSeparator(new Dimension(110, 8));
        toolBar.add(exitButton);
    }

    private void initIndexTree() {
        DefaultMutableTreeNode home = new DefaultMutableTreeNode(new TreeNodeData("Home", "home.html"));
        DefaultMutableTreeNode nodeI = null;
        try {
            URL url = getClass().getResource(INDEX_PROPERTIES);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = null;
            while ((s = reader.readLine()) != null) {
                s = s.trim();
                if ((s.length() > 0) && (s.charAt(0) != '#')) {
                    int posCategory = s.indexOf(';');
                    int posFileName = s.indexOf('=');
                    String category = s.substring(0, posCategory);
                    String displayString = s.substring(posCategory + 1, posFileName);
                    String fileName = s.substring(posFileName + 1);
                    if (category.equals("I")) {
                        nodeI = new DefaultMutableTreeNode(new TreeNodeData(displayString, fileName));
                        home.add(nodeI);
                    }
                    else if (category.equals("P")) {
                        home.add(new DefaultMutableTreeNode(new TreeNodeData(displayString, fileName)));
                    }
                    else if (category.equals("IP")) {
                        nodeI.add(new DefaultMutableTreeNode(new TreeNodeData(displayString, fileName)));
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        indexTree = new JTree(home);
        indexTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        indexTree.requestFocus();
        indexTree.setSelectionRow(0);
        helpStack.add(indexTree.getSelectionPath());
        updateHelpStack();
        indexTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                TreePath selPath = indexTree.getSelectionPath();
                if (selPath != null) {
                    DefaultMutableTreeNode selPage = (DefaultMutableTreeNode)selPath.getLastPathComponent();
                    TreeNodeData nodeData = (TreeNodeData)selPage.getUserObject();
                    try {
                    	if (nodeData.getURL().contains("www.isourceinv.com")) {
                    		Runtime.getRuntime().exec("cmd /c start http://www.isourceinv.com");
                    		updateHelpStack();
						}else {
						URL resource = new URL("file:/"+nodeData.getURL());
                        helpPane.setPage(resource);
                        if (!blockHelpStack) {
                            helpStack.add(selPath);
                            helpStackIndex = helpStack.size() - 1;
                        }
                        updateHelpStack();
						}
                    }
                    catch (Exception ex) {
                    	ex.printStackTrace();
                        helpPane.setText("<html><h2>ERROR: " + nodeData.getURL() + "</h2></html>");
                    }
                    helpPane.validate();
                }
            }
        });
    }

    private void initHelpPane() {
        try {
            URL url = new URL("file:/"+HOME_PAGE);
            helpPane = new JEditorPane(url) {
                /**
				 * 
				 */
				private static final long serialVersionUID = -4983034981062374018L;

				public void paint(Graphics g) {
                    Graphics2D g2D = (Graphics2D)g;
                    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    super.paint(g);
                }
            };
            helpPane.setEditable(false);
            helpPane.addHyperlinkListener(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateHelpStack() {
        prevButton.setEnabled((helpStack.size() > 1) && (helpStackIndex > 0));
        nextButton.setEnabled((helpStack.size() > 1) && (helpStackIndex < (helpStack.size() - 1)));
    }

    private class NoFocusButton extends JButton {
        /**
		 * 
		 */
		private static final long serialVersionUID = 6458869013453669648L;

		public NoFocusButton(ImageIcon icon) {
            super(icon);
            setFocusPainted(false);
            setRolloverEnabled(true);
        }

        public boolean isFocusTraversable()
        { return false; }

        public void requestFocus()
        {}
    }

    private class TreeNodeData {
        private String displayString = null;
        private String url = null;

        public TreeNodeData(String aDisplayString, String fileName) {
            displayString = aDisplayString;
            if ("www.isourceinv.com".equals(fileName)) {
				url = fileName;
			}else {
				url = StaticVar.PROGRAME_PATH+"/jre/lib/"+HELP_PATH + fileName;
			}
        }

        public String getURL()
        { return url; }

        public String toString()
        { return displayString; }
    }
}
