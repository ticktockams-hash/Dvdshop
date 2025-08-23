package controller.employ;

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
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import model.Employ;
import service.impl.EmployServiceImpl;
import javax.swing.JComboBox;

public class EmployManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField phoneField;
	private JComboBox<String> roleComboBox;
	private JTable employTable;
	private DefaultTableModel tableModel;

	private EmployServiceImpl employService = new EmployServiceImpl();
	private Employ loggedInEmploy;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employ testEmploy = new Employ();
					testEmploy.setId(1);
					testEmploy.setName("測試管理者");
					testEmploy.setUsername("admin");
					testEmploy.setPassword("1234");
					testEmploy.setRole("admin");
					
					EmployManager frame = new EmployManager(testEmploy);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EmployManager(Employ loggedInEmploy) {
		setTitle("員工管理介面");
		this.loggedInEmploy = loggedInEmploy;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblName = new JLabel("姓名：");
		lblName.setBounds(30, 30, 80, 20);
		contentPane.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(110, 30, 150, 20);
		contentPane.add(nameField);
		
		JLabel lblUsername = new JLabel("帳號：");
		lblUsername.setBounds(30, 60, 80, 20);
		contentPane.add(lblUsername);
		
		usernameField = new JTextField();
		usernameField.setBounds(110, 60, 150, 20);
		contentPane.add(usernameField);
		
		JLabel lblPassword = new JLabel("密碼：");
		lblPassword.setBounds(30, 90, 80, 20);
		contentPane.add(lblPassword);
		
		passwordField = new JTextField();
		passwordField.setBounds(110, 90, 150, 20);
		contentPane.add(passwordField);
		
		JLabel lblPhone = new JLabel("電話：");
		lblPhone.setBounds(30, 120, 80, 20);
		contentPane.add(lblPhone);
		
		phoneField = new JTextField();
		phoneField.setBounds(110, 120, 150, 20);
		contentPane.add(phoneField);
		
		JLabel lblRole = new JLabel("角色：");
		lblRole.setBounds(30, 150, 80, 20);
		contentPane.add(lblRole);
		
		String[] roles = {"admin", "employee"};
		roleComboBox = new JComboBox<>(roles);
		roleComboBox.setBounds(110, 150, 150, 20);
		contentPane.add(roleComboBox);
		
		// Button
		JButton btnAdd = new JButton("新增");
		btnAdd.setBounds(300, 30, 80, 25);
		contentPane.add(btnAdd);

		JButton btnUpdate = new JButton("更新");
		btnUpdate.setBounds(300, 70, 80, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("刪除");
		btnDelete.setBounds(300, 110, 80, 25);
		contentPane.add(btnDelete);
		
		JButton btnReturn = new JButton("返回");
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminManager am = new AdminManager(loggedInEmploy);
				am.setVisible(true);
				dispose();
			}
		});
		btnReturn.setBounds(300, 150, 80, 25);
		contentPane.add(btnReturn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 190, 640, 250);
		contentPane.add(scrollPane);

		String[] columnNames = {"ID", "姓名", "帳號", "密碼", "電話", "角色"};
		tableModel = new DefaultTableModel(columnNames, 0);
		employTable = new JTable(tableModel);
		scrollPane.setViewportView(employTable);
		
		loadEmploys();

		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText();
				String username = usernameField.getText();
				String password = passwordField.getText();
				String phone = phoneField.getText();
				String role = (String) roleComboBox.getSelectedItem();
				
				if (name.isEmpty() || username.isEmpty() || password.isEmpty() || phone.isEmpty() || role == null) {
					JOptionPane.showMessageDialog(null, "所有欄位都不能為空！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if(employService.isUsernameTaken(username)) {
					JOptionPane.showMessageDialog(null, "帳號已存在，請使用其他帳號！", "錯誤", JOptionPane.ERROR_MESSAGE);
				} else {
					Employ employ = new Employ();
					employ.setName(name);
					employ.setUsername(username);
					employ.setPassword(password);
					employ.setPhone(phone);
					employ.setRole(role);
					
					employService.addEmploy(employ);
					JOptionPane.showMessageDialog(null, "員工新增成功！");
					loadEmploys();
					clearFields();
				}
			}
		});

		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = employTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "請先選擇要更新的員工！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
					String newName = nameField.getText();
					String newUsername = usernameField.getText();
					String newPassword = passwordField.getText();
					String newPhone = phoneField.getText();
					String newRole = (String) roleComboBox.getSelectedItem();
					
					if (newName.isEmpty() || newUsername.isEmpty() || newPassword.isEmpty() || newPhone.isEmpty() || newRole == null) {
						JOptionPane.showMessageDialog(null, "所有欄位都不能為空！", "錯誤", JOptionPane.ERROR_MESSAGE);
						return;
					}
	
					Employ updatedEmploy = new Employ();
					updatedEmploy.setId(id);
					updatedEmploy.setName(newName);
					updatedEmploy.setUsername(newUsername);
					updatedEmploy.setPassword(newPassword);
					updatedEmploy.setPhone(newPhone);
					updatedEmploy.setRole(newRole);
					
					employService.updateEmploy(updatedEmploy);
					
					JOptionPane.showMessageDialog(null, "員工資料更新成功！");
					loadEmploys();
					clearFields();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "無法取得有效的員工ID。");
					ex.printStackTrace();
				}
			}
		});

		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = employTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "請先選擇要刪除的員工！", "錯誤", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int option = JOptionPane.showConfirmDialog(null, "確定要刪除選定的員工嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					try {
						int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
						employService.deleteEmploy(id);
						JOptionPane.showMessageDialog(null, "刪除成功！");
						loadEmploys();
						clearFields();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "無法取得有效的員工ID。");
						ex.printStackTrace();
					}
				}
			}
		});
		
		employTable.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = employTable.getSelectedRow();
		        if (selectedRow != -1) {
		            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
		            Employ selectedEmploy = employService.findEmploy(id);
		            if (selectedEmploy != null) {
		                nameField.setText(selectedEmploy.getName());
		                usernameField.setText(selectedEmploy.getUsername());
		                passwordField.setText(selectedEmploy.getPassword());
		                phoneField.setText(selectedEmploy.getPhone());
		                roleComboBox.setSelectedItem(selectedEmploy.getRole());
		            }
		        }
		    }
		});
	}

	private void loadEmploys() {
		List<Employ> employs = employService.selectAllEmploys();
		tableModel.setRowCount(0);
		for (Employ employ : employs) {
			Object[] rowData = {employ.getId(), employ.getName(), employ.getUsername(), employ.getPassword(), employ.getPhone(), employ.getRole()};
			tableModel.addRow(rowData);
		}
		
		TableColumn idColumn = employTable.getColumnModel().getColumn(0);
		idColumn.setMinWidth(0);
		idColumn.setMaxWidth(0);
		idColumn.setWidth(0);
		idColumn.setPreferredWidth(0);
		idColumn.setResizable(false);
	}
	
	private void clearFields() {
		nameField.setText("");
		usernameField.setText("");
		passwordField.setText("");
		phoneField.setText("");
		roleComboBox.setSelectedIndex(0);
	}
}