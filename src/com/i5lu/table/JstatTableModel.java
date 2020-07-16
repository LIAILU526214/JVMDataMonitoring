package com.i5lu.table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class JstatTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 8998508470639122306L;
	private String[] columnNames;
	private Vector<String[]> data;

	public JstatTableModel(String[] columnNames, Vector<String[]> data) {
		super();
		this.columnNames = columnNames;
		this.data = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
//		System.out.println(row+"===="+col+"[[[["+data.size());
		if (data.size() <= row) {
			return "";
		}
//		System.out.println(data.get(row)[0]);
		return data.get(row)[col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(String value, int row, int col) {
		data.get(row)[col] = value;
		fireTableCellUpdated(row, col);
	}

	public void insert(String[] split) {
		data.add(split);
		fireTableDataChanged();
		
	}

	public void removeAll() {
		data.clear();
		fireTableDataChanged();
	}

}
