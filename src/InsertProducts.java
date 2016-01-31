import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Image;

public class InsertProducts extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertProducts frame = new InsertProducts();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;
	private JTextField textField_3;

	/**
	 * Create the frame.
	 */
	public InsertProducts() {
		conn = SqlConect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("\u039F\u039D\u039F\u039C\u0391");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(75, 70, 123, 17);
		contentPane.add(lblName);

		JLabel lblPassword = new JLabel("BarCode");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(75, 42, 123, 17);
		contentPane.add(lblPassword);

		JLabel lblNewLabel = new JLabel("\u03A0\u039F\u03A3\u039F\u03A4H\u03A4\u0391");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(75, 126, 123, 17);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(208, 42, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(208, 70, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(208, 126, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JButton btnSave = new JButton("SAVE");
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnSave.setIcon(new ImageIcon(img));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textField_2.getText().equals("") || textField_3.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "сулпкгяысте та йема педиа");
				} else {
					try {
						String query = "insert into proionta (BarCode,Name,Creator,Quantity) values (?,?,?,?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, textField.getText());
						pst.setString(2, textField_1.getText());
						pst.setString(3, textField_3.getText());
						pst.setString(4, textField_2.getText());
						pst.execute();

						JOptionPane.showMessageDialog(null, "Saved");

						pst.close();

						dispose();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "сулпкгяысте та йема педиа");
					}
				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(151, 180, 107, 23);
		contentPane.add(btnSave);

		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(
				"\u039A\u0391\u03A4\u0391\u03A3\u039A\u0395\u03A5\u0391\u03A3\u03A4\u0397\u03A3");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(65, 98, 133, 17);
		contentPane.add(lblNewLabel_1);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(208, 95, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
	}
}
