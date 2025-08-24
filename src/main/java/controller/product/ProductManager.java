package controller.product;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.employ.AdminManager;
import model.Employ; 

import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import service.impl.ProductServiceImpl;
import model.Product;
import java.util.List;

public class ProductManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField productNameField;
	private JTextField productPriceField;
	private JTable productTable;
	private DefaultTableModel tableModel;

	private ProductServiceImpl productService = new ProductServiceImpl();
	private Employ employ; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employ testEmploy = new Employ();
					testEmploy.setId(1);
					testEmploy.setName("測試管理員");
					
					ProductManager frame = new ProductManager(testEmploy); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ProductManager(Employ employ) {
		setTitle("產品管理介面");
		this.employ = employ;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblProductName = new JLabel("產品名稱：");
		lblProductName.setBounds(30, 30, 80, 20);
		contentPane.add(lblProductName);
		
		productNameField = new JTextField();
		productNameField.setBounds(110, 30, 150, 20);
		contentPane.add(productNameField);
		
		JLabel lblProductPrice = new JLabel("產品價格：");
		lblProductPrice.setBounds(30, 60, 80, 20);
		contentPane.add(lblProductPrice);
		
		productPriceField = new JTextField();
		productPriceField.setBounds(110, 60, 150, 20);
		contentPane.add(productPriceField);
		
		// Button
		JButton btnAdd = new JButton("新增");
		btnAdd.setBounds(30, 100, 80, 25);
		contentPane.add(btnAdd);

		JButton btnUpdate = new JButton("更新");
		btnUpdate.setBounds(120, 100, 80, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("刪除");
		btnDelete.setBounds(210, 100, 80, 25);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("返回");
		btnBack.setBounds(300, 100, 80, 25);
		contentPane.add(btnBack);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 150, 540, 200);
		contentPane.add(scrollPane);

		String[] columnNames = {"產品名稱", "產品價格"};
		tableModel = new DefaultTableModel(columnNames, 0);
		productTable = new JTable(tableModel);
		scrollPane.setViewportView(productTable);
		
		loadProducts();

		
		btnAdd.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String name = productNameField.getText();
		        String priceText = productPriceField.getText();
		        
		        if (name.isEmpty() || priceText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "產品名稱和價格不能為空。");
		            return;
		        }

		        try {
		            int price = Integer.parseInt(priceText);
		            Product existingProduct = productService.selectByName(name);
		            if (existingProduct != null) {
		                JOptionPane.showMessageDialog(null, "此產品名稱已存在，請使用不同的名稱。");
		                return;
		            }

		            Product newProduct = new Product();
		            newProduct.setProductname(name);
		            newProduct.setProductprice(price);

		            productService.addProduct(newProduct);
		            JOptionPane.showMessageDialog(null, "新增成功！");
		            
		            loadProducts();
		            clearFields();
		            
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "價格必須是有效的數字。");
		        }
		    }
		});

		
		btnUpdate.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = productTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "請先在表格中選擇要更新的產品。");
		            return;
		        }

		        String oldName = (String) tableModel.getValueAt(selectedRow, 0);
		        Product productToUpdate = productService.selectByName(oldName);
		        if (productToUpdate == null) {
		            JOptionPane.showMessageDialog(null, "找不到要更新的產品。");
		            return;
		        }

		        String newName = productNameField.getText();
		        String newPriceText = productPriceField.getText();

		        if (newName.isEmpty() || newPriceText.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "產品名稱和價格不能為空。");
		            return;
		        }
		        
		        
		        Product conflictProduct = productService.selectByName(newName);
		        if (conflictProduct != null && conflictProduct.getId() != productToUpdate.getId()) {
		            JOptionPane.showMessageDialog(null, "新產品名稱已存在，請使用其他名稱。");
		            return;
		        }

		        try {
		            int newPrice = Integer.parseInt(newPriceText);

		            productToUpdate.setProductname(newName);
		            productToUpdate.setProductprice(newPrice);
		            
		            productService.updateProduct(productToUpdate);
		            JOptionPane.showMessageDialog(null, "更新成功！");

		            loadProducts();
		            clearFields();
		            
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "價格必須是有效的數字。");
		        }
		    }
		});

		
		btnDelete.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = productTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "請先在表格中選擇要刪除的產品。");
		            return;
		        }
		        
		        String productName = (String) tableModel.getValueAt(selectedRow, 0);
		        
		        int dialogResult = JOptionPane.showConfirmDialog(null, "您確定要刪除產品：" + productName + " 嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
		        
		        if(dialogResult == JOptionPane.YES_OPTION) {
		            Product productToDelete = productService.selectByName(productName);
		            if (productToDelete != null) {
		                productService.deleteProduct(productToDelete.getId());
		                JOptionPane.showMessageDialog(null, "刪除成功！");
		                
		                loadProducts();
		                clearFields();
		            } else {
		                JOptionPane.showMessageDialog(null, "找不到要刪除的產品。");
		            }
		        }
		    }
		});
		
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminManager adminManager = new AdminManager(employ);
				adminManager.setVisible(true);
				dispose();
			}
		});
		
		productTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = productTable.getSelectedRow();
		        if (selectedRow != -1) {
		            String name = (String) tableModel.getValueAt(selectedRow, 0); 
		            int price = (int) tableModel.getValueAt(selectedRow, 1);     
		            productNameField.setText(name);
		            productPriceField.setText(String.valueOf(price));
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
	
	private void clearFields() {
		productNameField.setText("");
		productPriceField.setText("");
	}
}