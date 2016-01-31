import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class employee extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	String BarCode_ = null;
	
	
	/**
	 * Launch the application.
	 */
	public void refeshTable() {
		try {
			String query = "select BarCode,Name,Creator,Quantity from proionta";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee frame = new employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public employee() {
		conn = SqlConect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 614, 243);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				BarCode_ = (table.getModel().getValueAt(row, 0).toString()); // vazoume
																				// sto
																				// BarCode_
																				// oti
																				// exei
																				// h
																				// stilh
																				// 0
				try {
					String query = "select * from proionta where BarCode='" + BarCode_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					textField_1.setText(rs.getString("Quantity"));

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton fortosh = new JButton("\u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03B1");
		fortosh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select BarCode,Name,Creator,Quantity from proionta";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		fortosh.setBounds(10, 56, 89, 23);
		contentPane.add(fortosh);

		textField = new JTextField();
		textField.setBounds(10, 11, 303, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		// search proion
		JButton btnSearch = new JButton("Search \u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03BF\u03C2");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "сулпкгяосе то педио");
				} else {
					try {
						String query = "select BarCode,Name,Creator,Quantity from proionta where Name=? ";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, textField.getText());
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(323, 10, 143, 23);
		contentPane.add(btnSearch);
		// search barcode
		JButton btnSearchBarcode = new JButton("Search BarCode");
		btnSearchBarcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "сулпкгяосе то педио");
				} else {
					try {

						String query = "select BarCode,Name,Creator,Quantity from proionta where BarCode=?";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, textField.getText());
						ResultSet rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		});
		btnSearchBarcode.setBounds(476, 10, 148, 23);
		contentPane.add(btnSearchBarcode);

		JButton btnNewButton_1 = new JButton("EXIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(535, 427, 89, 23);
		contentPane.add(btnNewButton_1);

		textField_1 = new JTextField();
		textField_1.setBounds(279, 344, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		// paralabh
		JButton button_1 = new JButton("\u03A0\u03B1\u03C1\u03B1\u03BB\u03B1\u03B2\u03AE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1, num2, num3;
				try {
					if (textField_1.getText().equals("") || BarCode_ == null) {
						JOptionPane.showMessageDialog(null, "епекене пяоиом");
					} else {
						num1 = Integer.parseInt(textField_1.getText());
						String query1 = "select Quantity from proionta where BarCode='" + BarCode_ + "'";
						PreparedStatement pst1 = conn.prepareStatement(query1);
						ResultSet rs = pst1.executeQuery();
						num2 = rs.getInt("Quantity");
						pst1.execute();
						pst1.close();
						num3 = num2 - num1;
						if (num1 < num2) {
							String query = "Update proionta set Quantity='" + num3 + "' where BarCode='" + BarCode_
									+ "'";
							PreparedStatement pst = conn.prepareStatement(query);

							pst.execute();

							JOptionPane.showMessageDialog(null, "OK");

							pst.close();
							refeshTable();
						} else
							JOptionPane.showMessageDialog(null, "дем упаявоум тоса пяоиомта!!!");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		button_1.setBounds(375, 344, 104, 23);
		contentPane.add(button_1);
		refeshTable();
	}
}
