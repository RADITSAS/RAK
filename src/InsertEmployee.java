import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;

public class InsertEmployee extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertEmployee frame = new InsertEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;

	/**
	 * Create the frame.
	 */
	public InsertEmployee() {
		conn = SqlConect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(119, 69, 83, 28);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(119, 108, 83, 28);
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(212, 75, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		textField_1.setBounds(212, 114, 86, 20);
		contentPane.add(textField_1);

		final JCheckBox checkBox = new JCheckBox("\u0394\u0399\u0391\u03A7\u0399\u03A1\u0399\u03A3\u03A4\u0397\u03A3");
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox.setBounds(151, 143, 125, 23);
		contentPane.add(checkBox);

		JButton btnNewButton = new JButton("SAVE");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String query = "select * from xristes where  Name=? "; // an
																			// uparxei
																			// o
																			// kodikos
																			// h
																			// to
																			// onoma
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());

					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next()) {
						count++;
					}

					if (count == 1) {
						JOptionPane.showMessageDialog(null, "то омола упаявеи гдг");

					} else {

						try {
							if (textField.getText().equals("") || textField_1.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "сулпкгяысте та йема педиа");
							} else {
								if (checkBox.isSelected() == true) // box is
																	// checked
								{
									String query1 = "insert into xristes (Name,Password,Manager) values (?,?,'1')";
									PreparedStatement pst1 = conn.prepareStatement(query1);
									pst1.setString(1, textField.getText());
									pst1.setString(2, textField_1.getText());

									pst1.execute();

									JOptionPane.showMessageDialog(null, "Saved");

									pst1.close();
									dispose();
								} else { // if check box unchecked
									String query2 = "insert into xristes (Name,Password,Manager) values (?,?,'0')";
									PreparedStatement pst2 = conn.prepareStatement(query2);
									pst2.setString(1, textField.getText());
									pst2.setString(2, textField_1.getText());

									pst2.execute();

									JOptionPane.showMessageDialog(null, "Saved");

									pst2.close();
									dispose();
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

					rs.close();
					pst.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(165, 188, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(335, 227, 89, 23);
		contentPane.add(btnCancel);
	}
}
