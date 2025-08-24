// AdminManager.java
package controller.employ;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.member.MemberManager;
import controller.product.AdminPorderManager;
import controller.product.ProductManager;
import controller.report.ProductChart;
import controller.Login;
import model.Employ;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class AdminManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Employ employ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employ testEmploy = new Employ();
					testEmploy.setId(1);
					testEmploy.setName("測試管理者");
					testEmploy.setUsername("admin");
					
					AdminManager frame = new AdminManager(testEmploy);
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
	public AdminManager(Employ employ) {
		setTitle("管理介面主選單");
		this.employ = employ;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		

		JLabel welcomeLabel = new JLabel("歡迎回來，" + this.employ.getName() + "！");
		welcomeLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		welcomeLabel.setBounds(30, 30, 233, 20);
		contentPane.add(welcomeLabel);
		

		//Button
		JButton btnNewButton = new JButton("商品管理");
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        ProductManager productManager = new ProductManager(employ); 
		        productManager.setVisible(true);
		        dispose();
			}
		});
		btnNewButton.setBounds(35, 90, 111, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("會員管理");
		btnNewButton_1.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        MemberManager memberManager = new MemberManager(employ);
		        memberManager.setVisible(true);
		        dispose();
		    }
		});
		btnNewButton_1.setBounds(156, 91, 112, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("會員訂單管理");
		btnNewButton_2.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminPorderManager adminPorderManager = new AdminPorderManager(employ);
				adminPorderManager.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(35, 164, 161, 46);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("產品銷售圖表");
		btnNewButton_3.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductChart productChart = new ProductChart(employ);
				productChart.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(224, 165, 165, 44);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("員工管理");
		btnNewButton_4.setFont(new Font("新細明體", Font.BOLD, 18));
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        EmployManager employManager = new EmployManager(employ); 
		        employManager.setVisible(true);
		        dispose(); 
			}
		});
		btnNewButton_4.setBounds(278, 92, 111, 49);
		contentPane.add(btnNewButton_4);
		
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
		btnLogout.setBounds(317, 22, 87, 31);
		contentPane.add(btnLogout);
	}
}