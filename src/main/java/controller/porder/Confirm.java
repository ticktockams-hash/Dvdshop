package controller.porder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Member;
import model.Porder;
import model.Product;
import service.impl.PorderServiceImpl;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane; 
import controller.member.UserMain;

public class Confirm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AddPorder previousFrame;
	private Member member;
	private Map<Product, Integer> cart;
	private UserMain userMain; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Confirm frame = new Confirm(null, null, null, null); 
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
	public Confirm(UserMain userMain, AddPorder previousFrame, Member member, Map<Product, Integer> cart) {
		setTitle("購物車確認介面");
		this.userMain = userMain;
		this.previousFrame = previousFrame;
		this.member = member;
		this.cart = cart;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel showMember = new JLabel("");
		showMember.setBounds(57, 10, 121, 23);
		contentPane.add(showMember);
		
		
		JTextPane showPoder = new JTextPane();
		
		
		JScrollPane scrollPane = new JScrollPane(showPoder);
		scrollPane.setBounds(57, 44, 319, 152);
		contentPane.add(scrollPane);
		
		
		if (member != null) {
			showMember.setText("會員: " + member.getName());
		}

		
		String show = "您的購物細目:\n";
		int totalAmount = 0;
		if (cart != null && !cart.isEmpty()) {
			for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
				Product p = entry.getKey();
				int quantity = entry.getValue();
				int subtotal = p.getProductprice() * quantity;
				show += "\n" + p.getProductname() + ": " + quantity + " 份";
				totalAmount += subtotal;
			}
		}
		show += "\n\n共: " + totalAmount + "元";
		showPoder.setText(show);
		
		// Button
		JButton btnBack = new JButton("回上一頁");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (previousFrame != null) {
					previousFrame.setVisible(true);
					dispose();
				}
			}
		});
		btnBack.setBounds(73, 217, 87, 23);
		contentPane.add(btnBack);
		
		JButton btnConfirm = new JButton("確認");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (member == null) {
					JOptionPane.showMessageDialog(null, "會員資訊遺失，無法建立訂單！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				PorderServiceImpl porderService = new PorderServiceImpl();
				for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
					Product p = entry.getKey();
					int quantity = entry.getValue();
					int amount = p.getProductprice() * quantity;
					
					Porder porder = new Porder();
					porder.setMemberid(member.getId());
					porder.setUsername(member.getUsername());
					porder.setProductname(p.getProductname());
					porder.setQuantity(quantity);
					porder.setAmount(amount);
					
					porderService.addPorder(porder);
				}
				
				JOptionPane.showMessageDialog(null, "訂單已成功建立！");
				
				cart.clear();
				
				dispose(); 
				if (previousFrame != null) {
					previousFrame.dispose();
				}
				
				if (userMain != null) {
					userMain.setVisible(true);
				}
			}
		});
		btnConfirm.setBounds(260, 217, 87, 23);
		contentPane.add(btnConfirm);
	}
}