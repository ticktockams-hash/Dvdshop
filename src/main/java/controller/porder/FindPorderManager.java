package controller.porder;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.Member;
import model.Porder;
import service.impl.PorderServiceImpl;
import controller.member.UserMain;

public class FindPorderManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable porderTable;
	private DefaultTableModel tableModel;
	private JTextField searchField;
	
	private PorderServiceImpl porderService = new PorderServiceImpl();
	private Member member;
	private List<Porder> allPorders;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Member testMember = new Member();
					testMember.setId(3);
					testMember.setUsername("testuser");
					
					FindPorderManager frame = new FindPorderManager(testMember);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public FindPorderManager(Member member) {
		setTitle("我的訂單");
		this.member = member;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblSearch = new JLabel("查詢訂單 (產品名稱)：");
		lblSearch.setBounds(30, 30, 150, 20);
		contentPane.add(lblSearch);
		
		searchField = new JTextField();
		searchField.setBounds(180, 30, 200, 20);
		contentPane.add(searchField);
		
		JButton btnSearch = new JButton("搜尋");
		btnSearch.setBounds(390, 30, 80, 25);
		contentPane.add(btnSearch);
		
		
		
		JButton btnBack = new JButton("回上頁");
		btnBack.setBounds(480, 30, 80, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 70, 640, 350);
		contentPane.add(scrollPane);
		
		String[] columnNames = {"會員帳號", "產品名稱", "數量", "金額", "訂單日期"};
		tableModel = new DefaultTableModel(columnNames, 0);
		porderTable = new JTable(tableModel);
		scrollPane.setViewportView(porderTable);
		
		
		loadPorders();
		
		
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchPorders();
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
		
		// 在視窗創建後調整欄寬
		porderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = porderTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(120); // 會員帳號
		columnModel.getColumn(1).setPreferredWidth(250); // 產品名稱
		columnModel.getColumn(2).setPreferredWidth(60); // 數量
		columnModel.getColumn(3).setPreferredWidth(80); // 金額
		columnModel.getColumn(4).setPreferredWidth(130); // 訂單日期
	}
	
	private void loadPorders() {
		allPorders = porderService.selectPordersByMemberId(member.getId());
		updateTable(allPorders);
	}
	
	private void searchPorders() {
		String searchText = searchField.getText().toLowerCase();
		if (searchText.isEmpty()) {
			updateTable(allPorders);
		} else {
			List<Porder> filteredPorders = allPorders.stream()
					.filter(p -> p.getProductname().toLowerCase().contains(searchText))
					.collect(Collectors.toList());
			updateTable(filteredPorders);
		}
	}
	
	private void updateTable(List<Porder> porders) {
		tableModel.setRowCount(0);
		for (Porder porder : porders) {
			Object[] rowData = {
				porder.getUsername(),
				porder.getProductname(),
				porder.getQuantity(),
				porder.getAmount(),
				porder.getPorderdate()
			};
			tableModel.addRow(rowData);
		}
	}
}