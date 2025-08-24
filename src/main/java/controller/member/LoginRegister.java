package controller.member;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Login;

import model.Member;
import service.impl.MemberServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class LoginRegister extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField username;
	private JTextField password;
	private JTextField phone;
	private MemberServiceImpl memberServiceImpl = new MemberServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginRegister frame = new LoginRegister();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public LoginRegister() {
		setTitle("註冊介面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("姓名:");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel.setBounds(48, 44, 46, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("帳號:");
		lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(48, 103, 46, 28);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(104, 52, 96, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		username = new JTextField();
		username.setBounds(104, 108, 96, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密碼:");
		lblNewLabel_2.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(48, 148, 46, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("手機:");
		lblNewLabel_3.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(48, 205, 46, 21);
		contentPane.add(lblNewLabel_3);
		
		password = new JTextField();
		password.setBounds(104, 153, 96, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(104, 206, 96, 21);
		contentPane.add(phone);
		phone.setColumns(10);
		
		//Button
		
		JButton btnNewButton = new JButton("確認");
		btnNewButton.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Name = name.getText();
				String UserName = username.getText();
				String Password = password.getText();
				String Phone = phone.getText();
				
				
				boolean isTaken = memberServiceImpl.isUsernameTaken(UserName);
				if(isTaken)
				{
					
					JOptionPane.showMessageDialog(null, "帳號已重複註冊！", "註冊失敗", JOptionPane.ERROR_MESSAGE);
					
				}
				else
				{
					
					Member member = new Member();
					member.setName(Name);
					member.setUsername(UserName);
					member.setPassword(Password);
					member.setPhone(Phone);
					member.setRole("nomember"); 
					
					memberServiceImpl.addMember(member);
					
					
					JOptionPane.showMessageDialog(null, "註冊成功！");
					
					
					Login login = new Login();
					login.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(263, 148, 87, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回");
		btnNewButton_1.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login=new Login();
				login.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(263, 201, 87, 28);
		contentPane.add(btnNewButton_1);
		


	}
}
