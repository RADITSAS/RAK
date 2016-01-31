import java.awt.EventQueue;
import java.awt.Image;

import java.sql.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

	private JFrame frame;
	int dia; // an einai diaxirisths
	String num1=null;
	Connection conn = null;
	private JTextField textField;
	private JPasswordField passwordField;

	@SuppressWarnings("deprecation")
	public void login() {
		if (textField.getText().equals("") || passwordField.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "сулпкгяысте та йема педиа");
		} else {
			try {
				String query = "select * from xristes where Name=? and Password=?";
				PreparedStatement pst = conn.prepareStatement(query);
				pst.setString(1, textField.getText());
				pst.setString(2, passwordField.getText());
				ResultSet rs = pst.executeQuery();
				dia = rs.getInt("Manager");
				int count = 0;
				num1=passwordField.getText();
				while (rs.next()) {
					count++;
				}

				if (count == 1) {
					if (dia == 1) {
						// frame.dispose();
						Manager man = new Manager();
						man.setVisible(true);
					} else {
						// frame.dispose();
						employee empl = new employee();
						empl.setVisible(true);
					}

				}else {
					JOptionPane.showMessageDialog(null, "то омола г йодийос дем упаявоум.пяоспахгсе нама...");
				}
				rs.close();
				pst.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"то омола г йодийос дем упаявоум.пяоспахгсе нама...");

			}

		}

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		conn = SqlConect.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(93, 61, 98, 22);
		frame.getContentPane().add(lblUsername);

		final JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(93, 94, 98, 22);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(225, 62, 98, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnNewButton.setBounds(163, 182, 98, 27);
		frame.getContentPane().add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}

			}
		});
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(225, 95, 98, 20);
		frame.getContentPane().add(passwordField);
	}
}
