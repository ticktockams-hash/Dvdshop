package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Member;
import service.impl.MemberServiceImpl;
import util.Tool;

import java.awt.Font;

public class UserManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField passwordField; 
	private JTextField phoneField;
	
	
	private JLabel timeLabel;

	private MemberServiceImpl memberService = new MemberServiceImpl();
	private Member member;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member testMember = new Member();
					testMember.setId(3);
					testMember.setName("測試使用者");
					testMember.setUsername("testuser");
					testMember.setPassword("123456");
					testMember.setPhone("0912345678");
					
					UserManager frame = new UserManager(testMember);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserManager(Member member) {
		setTitle("會員管理");
		this.member = member;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblName = new JLabel("姓名：");
		lblName.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblName.setBounds(80, 45, 80, 20);
		contentPane.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(170, 46, 150, 20);
		nameField.setText(member.getName());
		nameField.setEditable(false);
		contentPane.add(nameField);

		JLabel lblUsername = new JLabel("帳號：");
		lblUsername.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblUsername.setBounds(80, 75, 80, 20);
		contentPane.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(170, 76, 150, 20);
		usernameField.setText(member.getUsername());
		usernameField.setEditable(false);
		contentPane.add(usernameField);

		JLabel lblPassword = new JLabel("密碼：");
		lblPassword.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblPassword.setBounds(80, 105, 80, 20);
		contentPane.add(lblPassword);

		passwordField = new JTextField(); 
		passwordField.setBounds(170, 106, 150, 20);
		passwordField.setText(member.getPassword());
		contentPane.add(passwordField);

		JLabel lblPhone = new JLabel("電話：");
		lblPhone.setFont(new Font("新細明體", Font.PLAIN, 18));
		lblPhone.setBounds(80, 135, 80, 20);
		contentPane.add(lblPhone);

		phoneField = new JTextField();
		phoneField.setBounds(170, 136, 150, 20);
		phoneField.setText(member.getPhone());
		contentPane.add(phoneField);
		
		// 新增顯示時間的 JLabel
        timeLabel = new JLabel("時間載入中...");
        timeLabel.setForeground(new Color(0, 0, 0));
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
        timeLabel.setBounds(256, 238, 229, 33);
        contentPane.add(timeLabel); // 將時間標籤添加到 contentPane

        // 創建 Timer 來每秒更新時間
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 呼叫 Tool 類別的方法來取得當前時間並更新 Label
                timeLabel.setText(Tool.getCurrentTime());
            }
        });
        timer.start();
		
		
		JButton btnUpdate = new JButton("更新");
		btnUpdate.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnUpdate.setBounds(110, 186, 100, 25);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("返回");
		btnBack.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnBack.setBounds(220, 186, 100, 25);
		contentPane.add(btnBack);

		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String newPassword = passwordField.getText();
				String newPhone = phoneField.getText();

				if (newPassword.isEmpty() || newPhone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "密碼或電話不能為空！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				member.setPassword(newPassword);
				member.setPhone(newPhone);
				
				memberService.updateMember(member);
				JOptionPane.showMessageDialog(null, "會員資料更新成功！");
			}
		});
		
		btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UserMain userMain = new UserMain(member);
                userMain.setVisible(true);
                dispose();
            }
        });
	}
}