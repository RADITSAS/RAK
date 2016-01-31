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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Manager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	String BarCode_N = null;
	int count = 0;

	public void RefeshTable() {
		try {
			String query = "select BarCode,Name,Creator,Quantity from proionta";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			count = 1;
			rs.close();
			pst.close();
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
					Manager frame = new Manager();
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
	public Manager() {
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
			public void mouseClicked(MouseEvent e) {

				int row = table.getSelectedRow();
				BarCode_N = (table.getModel().getValueAt(row, 0).toString()); // vazoume
																				// sto
																				// BarCode_N
																				// oti
																				// exei
																				// h
																				// stilh
																				// 0
				try {
					String query = "select * from proionta where BarCode='" + BarCode_N + "'";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					if (count == 1)
						textField_1.setText(rs.getString("Quantity"));
					rs.close();
					pst.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		// proionta
		JButton fortosh = new JButton("\u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03B1");
		fortosh.setBounds(10, 56, 98, 23);
		fortosh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select BarCode,Name,Creator,Quantity from proionta";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					count = 1;
					rs.close();
					pst.close();
					BarCode_N = null;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		contentPane.add(fortosh);
		// insert products
		JButton btnInsertt = new JButton(
				"\u0395\u03B9\u03C3\u03B1\u03B3\u03C9\u03B3\u03AE \u039D\u03AD\u03BF\u03C5 \u03A0\u03C1\u03BF\u03CA\u03CC\u03BD\u03C4\u03C9\u03BD");
		btnInsertt.setBounds(10, 344, 206, 23);
		btnInsertt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InsertProducts inpro = new InsertProducts();
				inpro.setVisible(true);

			}
		});
		contentPane.add(btnInsertt);
		// upaliloi
		JButton btnNewButton = new JButton("\u03A5\u03C0\u03AC\u03BB\u03BB\u03B7\u03BB\u03BF\u03B9");
		btnNewButton.setBounds(118, 56, 98, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from xristes";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					count = 2;
					rs.close();
					pst.close();
					BarCode_N = null;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);

		JButton button = new JButton(
				"\u0395\u03B9\u03C3\u03B1\u03B3\u03C9\u03B3\u03AE \u039D\u03AD\u03BF\u03C5 \u03A5\u03C0\u03B1\u03BB\u03BB\u03AE\u03BB\u03BF\u03C5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				InsertEmployee inemplo = new InsertEmployee();
				inemplo.setVisible(true);
			}
		});
		button.setBounds(10, 378, 206, 23);
		contentPane.add(button);
		// delete
		JButton btnDelete = new JButton("\u0394\u03B9\u03B1\u03B3\u03C1\u03B1\u03C6\u03AE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (BarCode_N != null) {
					if (count == 1) {
						try {

							String query = "delete from proionta where BarCode='" + BarCode_N + "'";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.execute();

							JOptionPane.showMessageDialog(null, "Deleted");

							pst.close();
							BarCode_N = null;

						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (count == 2) {
						try {
							String query = "delete from xristes where Name='" + BarCode_N + "'";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.execute();

							JOptionPane.showMessageDialog(null, "Deleted");

							pst.close();
							BarCode_N = null;

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					RefeshTable();
				} else
					JOptionPane.showMessageDialog(null, "епекене педио");

			}
		});
		btnDelete.setBounds(520, 56, 104, 23);
		contentPane.add(btnDelete);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

			}
		});
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
						count = 1;
						rs.close();
						pst.close();
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
						count = 1;
						rs.close();
						pst.close();
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
				int num1, num2, num3, num4, numchart;
				try {
					if (count == 1) {
						if (textField_1.getText().equals("") || BarCode_N == null) {
							JOptionPane.showMessageDialog(null, "епекене пяоиом");
						} else {
							num1 = Integer.parseInt(textField_1.getText());
							String query1 = "select Quantity,Chart from proionta where BarCode='" + BarCode_N + "'";
							PreparedStatement pst1 = conn.prepareStatement(query1);
							ResultSet rs = pst1.executeQuery();
							num2 = rs.getInt("Quantity");
							numchart = rs.getInt("Chart");
							pst1.execute();
							pst1.close();
							num4 = num1 + numchart;
							num3 = num2 - num1;
							if (num1 < num2) {
								String query = "Update proionta set Quantity='" + num3 + "',Chart='" + num4
										+ "' where BarCode='" + BarCode_N + "'";
								PreparedStatement pst = conn.prepareStatement(query);

								pst.execute();

								JOptionPane.showMessageDialog(null, "OK");

								pst.close();
								BarCode_N = null;
								RefeshTable();
							} else
								JOptionPane.showMessageDialog(null, "дем упаявоум тоса пяоиомта!!!");
						}
					} else
						RefeshTable();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		button_1.setBounds(375, 344, 104, 23);
		contentPane.add(button_1);
		// prostesh proion
		JButton button_2 = new JButton("\u0395\u03B9\u03C3\u03B1\u03B3\u03C9\u03B3\u03AE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num1, num2, num3;
				try {
					if (count == 1) {
						if (textField_1.getText().equals("") || BarCode_N == null) {
							JOptionPane.showMessageDialog(null, "епекене пяоиом");
						} else {
							num1 = Integer.parseInt(textField_1.getText());
							String query1 = "select Quantity from proionta where BarCode='" + BarCode_N + "'";
							PreparedStatement pst1 = conn.prepareStatement(query1);
							ResultSet rs = pst1.executeQuery();
							num2 = rs.getInt("Quantity");
							pst1.execute();
							pst1.close();
							num3 = num2 + num1;

							String query = "Update proionta set Quantity='" + num3 + "' where BarCode='" + BarCode_N
									+ "'";
							PreparedStatement pst = conn.prepareStatement(query);

							pst.execute();

							JOptionPane.showMessageDialog(null, "OK");

							pst.close();
							BarCode_N = null;
							RefeshTable();
						}

					} else
						RefeshTable();
				}

				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button_2.setBounds(489, 344, 104, 23);
		contentPane.add(button_2);

		RefeshTable();
	}
}
