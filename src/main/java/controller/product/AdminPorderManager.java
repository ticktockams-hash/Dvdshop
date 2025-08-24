package controller.product;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel; 

import controller.employ.AdminManager;
import javax.swing.JOptionPane;
import model.Porder;
import model.Member;
import model.Employ; 
import service.impl.PorderServiceImpl;
import service.impl.MemberServiceImpl;
import util.ReportGenerator;
import javax.swing.JComboBox;

public class AdminPorderManager extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable porderTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> memberComboBox;

    private PorderServiceImpl porderService = new PorderServiceImpl();
    private MemberServiceImpl memberService = new MemberServiceImpl();
    private List<Porder> allPorders;
    
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
                    
                    AdminPorderManager frame = new AdminPorderManager(testEmploy);
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
    public AdminPorderManager(Employ employ) {
    	setTitle("會員訂單查詢介面");
        this.employ = employ;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblSearchMember = new JLabel("查詢訂單 (會員帳號)：");
        lblSearchMember.setBounds(30, 30, 150, 20);
        contentPane.add(lblSearchMember);

        memberComboBox = new JComboBox<>();
        memberComboBox.setBounds(180, 30, 200, 20);
        contentPane.add(memberComboBox);
        loadMembersToComboBox();

        JButton btnSearch = new JButton("搜尋");
        btnSearch.setBounds(390, 30, 80, 25);
        contentPane.add(btnSearch);
        
        JButton btnBack = new JButton("回上頁");
        btnBack.setBounds(480, 30, 80, 25);
        contentPane.add(btnBack);
        
        JButton btnExport = new JButton("匯出報表");
        btnExport.setBounds(570, 30, 100, 25);
        contentPane.add(btnExport);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 70, 680, 350);
        contentPane.add(scrollPane);

        String[] columnNames = {"ID", "會員ID", "會員帳號", "產品名稱", "數量", "金額", "訂單日期"};
        tableModel = new DefaultTableModel(columnNames, 0);
        porderTable = new JTable(tableModel);
        scrollPane.setViewportView(porderTable);
        
        // 調整表格欄寬
        porderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = porderTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // ID
        columnModel.getColumn(1).setPreferredWidth(70); // 會員ID
        columnModel.getColumn(2).setPreferredWidth(100); // 會員帳號
        columnModel.getColumn(3).setPreferredWidth(200); // 產品名稱
        columnModel.getColumn(4).setPreferredWidth(60); // 數量
        columnModel.getColumn(5).setPreferredWidth(80); // 金額
        columnModel.getColumn(6).setPreferredWidth(130); // 訂單日期

        JButton btnUpdate = new JButton("更新數量");
        btnUpdate.setBounds(200, 440, 100, 25);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("刪除訂單");
        btnDelete.setBounds(350, 440, 100, 25);
        contentPane.add(btnDelete);

        loadAllPorders();

        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchPordersByMember();
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
        
        btnExport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String selectedUsername = (String) memberComboBox.getSelectedItem();
                
                List<Porder> pordersToExport;
                String fileName;

                if ("全部".equals(selectedUsername)) {
                    pordersToExport = allPorders;
                    fileName = "all_porders_report.xlsx";
                } else {
                    pordersToExport = allPorders.stream()
                        .filter(p -> p.getUsername() != null && p.getUsername().equals(selectedUsername))
                        .collect(Collectors.toList());
                    fileName = selectedUsername + "_porders_report.xlsx";
                }
                
                if (pordersToExport.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "沒有訂單資料可供匯出！", "訊息", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                ReportGenerator reportGenerator = new ReportGenerator();
                reportGenerator.exportPorderReport(pordersToExport, fileName);
            }
        });

        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = porderTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "請先選擇要更新的訂單！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int orderId = (int) tableModel.getValueAt(selectedRow, 0);
                int oldQuantity = (int) tableModel.getValueAt(selectedRow, 4);
                String newQuantityStr = JOptionPane.showInputDialog(null, "請輸入新的數量：", oldQuantity);
                
                if (newQuantityStr != null && !newQuantityStr.isEmpty()) {
                    try {
                        int newQuantity = Integer.parseInt(newQuantityStr);
                        if (newQuantity <= 0) {
                            JOptionPane.showMessageDialog(null, "數量必須大於 0！", "錯誤", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        
                        Porder porderToUpdate = findPorderById(orderId);
                        if (porderToUpdate != null) {
                            int newAmount = (porderToUpdate.getAmount() / porderToUpdate.getQuantity()) * newQuantity;
                            porderToUpdate.setQuantity(newQuantity);
                            porderToUpdate.setAmount(newAmount);
                            
                            porderService.updatePorder(porderToUpdate);
                            JOptionPane.showMessageDialog(null, "訂單數量更新成功！");
                            loadAllPorders();
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "請輸入有效的數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = porderTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "請先選擇要刪除的訂單！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int option = JOptionPane.showConfirmDialog(null, "確定要刪除這筆訂單嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    int orderId = (int) tableModel.getValueAt(selectedRow, 0);
                    porderService.deletePorder(orderId);
                    JOptionPane.showMessageDialog(null, "訂單已成功刪除！");
                    loadAllPorders();
                }
            }
        });
    }

    private void loadMembersToComboBox() {
        memberComboBox.removeAllItems();
        memberComboBox.addItem("全部");
        List<Member> members = memberService.selectAllMembers();
        for (Member member : members) {
            memberComboBox.addItem(member.getUsername());
        }
    }
    
    private void loadAllPorders() {
        allPorders = porderService.selectAllPorders();
        updateTable(allPorders);
    }
    
    private void searchPordersByMember() {
        String selectedUsername = (String) memberComboBox.getSelectedItem();
        
        if ("全部".equals(selectedUsername)) {
            updateTable(allPorders);
        } else {
            List<Porder> filteredPorders = allPorders.stream()
                    .filter(p -> p.getUsername() != null && p.getUsername().equals(selectedUsername))
                    .collect(Collectors.toList());
            updateTable(filteredPorders);
        }
    }
    
    private void updateTable(List<Porder> porders) {
        tableModel.setRowCount(0);
        for (Porder porder : porders) {
            Object[] rowData = {
                porder.getId(),
                porder.getMemberid(),
                porder.getUsername(),
                porder.getProductname(),
                porder.getQuantity(),
                porder.getAmount(),
                porder.getPorderdate()
            };
            tableModel.addRow(rowData);
        }
    }
    
    private Porder findPorderById(int id) {
        return allPorders.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}