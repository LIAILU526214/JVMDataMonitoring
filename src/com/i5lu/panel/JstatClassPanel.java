package com.i5lu.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.i5lu.properties.JstatCommondProperties;
import com.i5lu.properties.JstatResultTableProperties;
import com.i5lu.table.JstatTableModel;
import com.i5lu.utils.JavaProcessIDUtils;
import com.i5lu.utils.RegexUtil;

import sun.jvmstat.monitor.MonitorException;

public class JstatClassPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public Object[][] allJavaProcessId;
	public Object[] os;
	public JComboBox<Object> combox;
	public JTextField time;
	public Thread jstatThread;
	//切换页面关闭当前界面线程
	public static boolean jstaFlag = false;
	public JstatClassPanel() {
		super(new BorderLayout());
		setName("JSTAT Class Panel");
		try {
			init(JstatCommondProperties.JSTAT_CLASS);
		} catch (MonitorException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JstatClassPanel(String type) {
		super(new BorderLayout());
		try {
			init(type);
		} catch (MonitorException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void init(String type) throws MonitorException, URISyntaxException {
		jstaFlag = true;
		setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		JPanel contentPanel = new JPanel(new BorderLayout());
		TitledBorder titledBorder = new TitledBorder(type+" Panel");
		titledBorder.setTitleFont(new Font("微软雅黑", 1, 14));
		contentPanel.setBorder(titledBorder);
		JLabel tipLabel = new JLabel("选择java程序: ");
		allJavaProcessId = JavaProcessIDUtils.getAllJavaProcessId();
		os = new Object[allJavaProcessId.length];
		for (int i = 0; i < allJavaProcessId.length; i++) {
			os[i] = allJavaProcessId[i][0];
		}
		combox = new JComboBox<>(os);
		combox.setEditable(true);
		combox.setPreferredSize(new Dimension(180, 20));
		combox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 5172784866813976184L;

			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (index == -1 || index >= os.length) {
					return this;
				}
				this.setToolTipText(os[index].toString());
				return this;
			}
		});
		time = new JTextField("1000  ");
		JLabel timeLabel = new JLabel("毫秒/次");
		JButton ok = new JButton("确定");
		
		JButton cancel = new JButton("停止");
		JButton clear = new JButton("清空");
		JButton refresh = new JButton("刷新Pid");
		refresh.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				try {
					allJavaProcessId = JavaProcessIDUtils.getAllJavaProcessId();
					if (allJavaProcessId == null) {
						return;
					}
					combox.removeAllItems();
					os = new Object[allJavaProcessId.length - 1];
					for (int i = 0; i < allJavaProcessId.length - 1; i++) {
						os[i] = allJavaProcessId[i][0];
						combox.addItem(os[i]);
					}
				} catch (MonitorException e1) {
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}

			}

		});
		JstatTableModel dm;
//		Object[][] data = { /* {"Loaded1","Bytes1","Unloaded1","Bytes1","Time1"} */ };
		Vector<String[]> data = new Vector<>();
		switch(type) {
    	case JstatCommondProperties.JSTAT_CLASS:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_CLASS_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCUTIL:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCUTIL_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GC:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GC_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCCAPACITY:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCCAPACITY_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCNEW:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCNEW_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCNEWCAPACITY:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCNEWCAPACITY_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCOLD:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCOLD_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCPERMCAPACITY:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCPERMCAPACITY_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_GCOLDCAPACITY:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_GCOLDCAPACITY_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_COMPILER:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_COMPILER_TAB, data);
    		break;
    	case JstatCommondProperties.JSTAT_PRINTCOMPILATION:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_PRINTCOMPILATION_TAB, data);
    		break;
    	default:
    		dm = new JstatTableModel(JstatResultTableProperties.JSTAT_CLASS_TAB, data);
    		break;
    	}
		JTable table = new JTable(dm);
//		table.setUpdateSelectionOnSort(true);
//		table.setRowSelectionInterval(index0, index1);
		JPanel northPanel = new JPanel();
		northPanel.add(tipLabel);
		northPanel.add(combox);
		northPanel.add(timeLabel);
		northPanel.add(time);
		northPanel.add(ok);
		northPanel.add(cancel);
		northPanel.add(clear);
		northPanel.add(refresh);
		contentPanel.add(new JScrollPane(northPanel), BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(table), BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane);
		
		cancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				jstaFlag = true;
			}
			
		});
		clear.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dm.removeAll();
			}
			
		});
		
		ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				jstaFlag = false;
				int selectedIndex = combox.getSelectedIndex();
				if (selectedIndex == -1 && RegexUtil.isNumeric(combox.getSelectedItem().toString())) {
//					System.out.println("getSelectedItem-1 ===> "+combox.getSelectedItem().toString());
					return ;
				}else if (selectedIndex == -1 && !RegexUtil.isNumeric(combox.getSelectedItem().toString())){
					JOptionPane.showMessageDialog(null, "请输入数字", "输入有误", JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("selectedIndex ===> "+selectedIndex);
//				System.out.println("allJavaProcessId ===> "+allJavaProcessId[selectedIndex][1]);
//				System.out.println("getSelectedItem ===> "+combox.getSelectedItem().toString());
				jstatThread = new Thread(new Runnable() {
					
					

					@Override
					public void run() {
						String s;
						Process process;
						try {
							process = Runtime.getRuntime().exec("cmd /c " + type + allJavaProcessId[combox.getSelectedIndex()][1] + " "+ time.getText());
							// 截获被调用程序的DOS运行窗口的标准输出
							BufferedReader br = new BufferedReader(
									new InputStreamReader(process.getInputStream()));
							while ((s = br.readLine()) != null) {
								String[] split = s.trim().replaceAll(" +", "-").split("-");
//								data.add(split);
								dm.insert(split);
								table.setRowHeight(20);
								table.setRowSelectionInterval(data.size()-1, data.size()-1);
								table.scrollRectToVisible(table.getCellRect(data.size()-1, 0, true));
								if(jstaFlag) {
									return ;
								}
//								table.setModel(dm);
							}
//							process.waitFor();
							
						} catch (IOException e1) {
							e1.printStackTrace();
						} 
					}
				});
				jstatThread.start();
			}
			
		});
	}
}
