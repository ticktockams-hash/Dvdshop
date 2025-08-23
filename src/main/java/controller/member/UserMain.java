package controller.member;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import controller.porder.AddPorder;
import controller.porder.FindPorderManager;
import model.Member;
import util.Tool;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.Login;
import java.awt.Font; 

public class UserMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Member member;
	
	// 宣告 timeLabel
	private JLabel timeLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member testMember = new Member();
					testMember.setId(3);
					testMember.setName("測試使用者");
					
					UserMain frame = new UserMain(testMember);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserMain(Member member) {
		setTitle("使用者介面");
		this.member = member;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		JLabel welcomeLabel = new JLabel("歡迎回來，" + member.getName() + "！");
		welcomeLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		welcomeLabel.setBounds(30, 30, 233, 20);
		contentPane.add(welcomeLabel);
		
		
        timeLabel = new JLabel("時間載入中...");
        timeLabel.setForeground(new Color(0, 0, 0));
        timeLabel.setFont(new Font("Consolas", Font.PLAIN, 17));
        timeLabel.setBounds(256, 238, 229, 33);
        contentPane.add(timeLabel); 

       
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                timeLabel.setText(Tool.getCurrentTime());
            }
        });
        timer.start();
		
		
		
		JButton btnToShop = new JButton("進入商城");
		btnToShop.setFont(new Font("新細明體", Font.BOLD, 20));
		btnToShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AddPorder addPorder = new AddPorder(UserMain.this, member);
				addPorder.setVisible(true);
				dispose();
			}
		});
		btnToShop.setBounds(22, 103, 125, 82);
		contentPane.add(btnToShop);
		
		
		
		JButton btnMemberManager = new JButton("會員管理");
		btnMemberManager.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnMemberManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserManager userManager = new UserManager(member);
				userManager.setVisible(true);
				dispose();
			}
		});
		btnMemberManager.setBounds(157, 103, 126, 82);
		contentPane.add(btnMemberManager);
		
		JButton btnPorderManager = new JButton("訂單管理");
		btnPorderManager.setFont(new Font("新細明體", Font.PLAIN, 20));
		btnPorderManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FindPorderManager findPorderManager = new FindPorderManager(member);
				findPorderManager.setVisible(true);
				dispose();
			}
		});
		btnPorderManager.setBounds(293, 103, 121, 82);
		contentPane.add(btnPorderManager);
		
		
		JButton btnLogout = new JButton("登出");
		btnLogout.setFont(new Font("新細明體", Font.PLAIN, 18));
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Login login = new Login();
				login.setVisible(true);
				
				dispose();
			}
		});
		btnLogout.setBounds(318, 29, 87, 23);
		contentPane.add(btnLogout);
	}
}