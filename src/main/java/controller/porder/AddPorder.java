package controller.porder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import model.Product;
import service.impl.ProductServiceImpl;
import service.impl.PorderServiceImpl;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import model.Member;
import util.Tool;

import controller.member.UserMain;

public class AddPorder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable productTable;
	private DefaultTableModel tableModel;
	private JTable cartTable;
	private DefaultTableModel cartTableModel;
	private JSpinner quantitySpinner;
	private JLabel totalLabel;

	private ProductServiceImpl productService = new ProductServiceImpl();
	private PorderServiceImpl porderService = new PorderServiceImpl(); 
	private Map<Product, Integer> cart = new HashMap<>();
	private Member member;
	private UserMain userMain;

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
					testMember.setRole("member"); 
					
					
					AddPorder frame = new AddPorder(null, testMember);
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
	public AddPorder(UserMain userMain, Member member) {
		setTitle("商城介面");
		this.userMain = userMain;
		this.member = member;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JLabel lblProductList = new JLabel("產品清單：");
		lblProductList.setBounds(30, 20, 100, 20);
		contentPane.add(lblProductList);

		JScrollPane productScrollPane = new JScrollPane();
		productScrollPane.setBounds(30, 50, 450, 200);
		contentPane.add(productScrollPane);

		
		String[] productColumnNames = {"產品名稱", "價格"};
		tableModel = new DefaultTableModel(productColumnNames, 0);
		productTable = new JTable(tableModel);
		productScrollPane.setViewportView(productTable);
		
		
		TableColumnModel productColumnModel = productTable.getColumnModel();
		productColumnModel.getColumn(0).setPreferredWidth(450); 
		productColumnModel.getColumn(1).setPreferredWidth(100);
		
		loadProducts();

		JLabel lblQuantity = new JLabel("數量：");
		lblQuantity.setBounds(500, 50, 50, 20);
		contentPane.add(lblQuantity);
		
		quantitySpinner = new JSpinner();
		quantitySpinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		quantitySpinner.setBounds(550, 50, 60, 20);
		contentPane.add(quantitySpinner);

		JButton btnAddToCart = new JButton("加入購物車");
		btnAddToCart.setBounds(620, 50, 120, 25);
		contentPane.add(btnAddToCart);
		
		JButton btnUpdateQuantity = new JButton("更新數量");
		btnUpdateQuantity.setBounds(620, 90, 120, 25);
		contentPane.add(btnUpdateQuantity);
		
		JButton btnDeleteItem = new JButton("刪除商品");
		btnDeleteItem.setBounds(620, 130, 120, 25);
		contentPane.add(btnDeleteItem);

		JLabel lblCart = new JLabel("購物車：");
		lblCart.setBounds(30, 280, 100, 20);
		contentPane.add(lblCart);

		JScrollPane cartScrollPane = new JScrollPane();
		cartScrollPane.setBounds(30, 310, 600, 150);
		contentPane.add(cartScrollPane);

		String[] cartColumnNames = {"產品名稱", "價格", "數量", "小計"};
		cartTableModel = new DefaultTableModel(cartColumnNames, 0);
		cartTable = new JTable(cartTableModel);
		cartScrollPane.setViewportView(cartTable);

		
		cartTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel cartColumnModel = cartTable.getColumnModel();

		
		cartColumnModel.getColumn(0).setPreferredWidth(250); // 產品名稱，給予較大的寬度
		cartColumnModel.getColumn(1).setPreferredWidth(100);  // 價格
		cartColumnModel.getColumn(2).setPreferredWidth(100);  // 數量
		cartColumnModel.getColumn(3).setPreferredWidth(147); // 小計，給予適當寬度
	
		JButton btnCheckout = new JButton("結帳");
		btnCheckout.setBounds(418, 480, 100, 25);
		contentPane.add(btnCheckout);
		
		
		JButton btnBack = new JButton("返回");
		btnBack.setBounds(528, 480, 100, 25);
		contentPane.add(btnBack);

		
		totalLabel = new JLabel("總金額：0 元");
		totalLabel.setBounds(222, 480, 200, 25);
		contentPane.add(totalLabel);

		
		btnAddToCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = productTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "請先選擇要加入購物車的產品！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				String productName = (String) tableModel.getValueAt(selectedRow, 0);
				int productPrice = (int) tableModel.getValueAt(selectedRow, 1);
				int quantity = (int) quantitySpinner.getValue();
				
				
				Product selectedProduct = productService.selectByName(productName);
				
				if (selectedProduct == null) {
					JOptionPane.showMessageDialog(null, "找不到選定的產品！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				boolean found = false;
				for (Product p : cart.keySet()) {
					if (p.getId() == selectedProduct.getId()) {
						cart.put(p, cart.get(p) + quantity);
						found = true;
						break;
					}
				}
				if (!found) {
					cart.put(selectedProduct, quantity);
				}

				updateCartTable();
				updateTotal();
			}
		});
		
		btnUpdateQuantity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = cartTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "請先在購物車中選擇要更新的商品！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String productName = (String) cartTableModel.getValueAt(selectedRow, 0);
				int newQuantity = (int) quantitySpinner.getValue();

				Product productToUpdate = findProductInCart(productName);
				if (productToUpdate != null) {
					cart.put(productToUpdate, newQuantity);
					JOptionPane.showMessageDialog(null, "商品數量已更新！");
					updateCartTable();
					updateTotal();
				}
			}
		});
		
		btnDeleteItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = cartTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "請先在購物車中選擇要刪除的商品！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int option = JOptionPane.showConfirmDialog(null, "確定要刪除選定的商品嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					String productName = (String) cartTableModel.getValueAt(selectedRow, 0);
					Product productToDelete = findProductInCart(productName);
					if (productToDelete != null) {
						cart.remove(productToDelete);
						JOptionPane.showMessageDialog(null, "商品已從購物車中刪除！");
						updateCartTable();
						updateTotal();
					}
				}
			}
		});
		
		btnCheckout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cart.isEmpty()) {
					JOptionPane.showMessageDialog(null, "購物車是空的，請先加入產品！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Confirm confirm = new Confirm(userMain, AddPorder.this, member, cart);
				confirm.setVisible(true);
				setVisible(false);
			}
		});
		
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (userMain != null) {
					userMain.setVisible(true);
					dispose();
				} else {
					
					JOptionPane.showMessageDialog(null, "無法返回主頁面，請從主頁面重新進入。", "錯誤", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
			}
		});

		productTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = productTable.getSelectedRow();
		        if (selectedRow != -1) {
		           
		        }
		    }
		});

		cartTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = cartTable.getSelectedRow();
		        if (selectedRow != -1) {
		            int quantity = (int) cartTableModel.getValueAt(selectedRow, 2);
		            quantitySpinner.setValue(quantity);
		        }
		    }
		});
	}
	
	private void loadProducts() {
		List<Product> products = productService.selectAllProducts();
		tableModel.setRowCount(0); 
		for (Product product : products) {
			
			Object[] rowData = {product.getProductname(), product.getProductprice()};
			tableModel.addRow(rowData);
		}
	}
	
	private void updateCartTable() {
		cartTableModel.setRowCount(0);
		for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
			Product p = entry.getKey();
			int quantity = entry.getValue();
			int subtotal = p.getProductprice() * quantity;
			Object[] rowData = {p.getProductname(), p.getProductprice(), quantity, subtotal};
			cartTableModel.addRow(rowData);
		}
	}
	
	private void updateTotal() {
		
		int total = Tool.calculateTotal(member, cart);
		String discountText = "";
		if (member != null && "member".equals(member.getRole())) {
			discountText = "(會員優惠)";
		} else {
			discountText = "(非會員無優惠)";
		}
		totalLabel.setText("總金額：" + total + " 元 " + discountText);
	}
	
	private Product findProductInCart(String productName) {
		for (Product p : cart.keySet()) {
			if (p.getProductname().equals(productName)) {
				return p;
			}
		}
		return null;
	}
}