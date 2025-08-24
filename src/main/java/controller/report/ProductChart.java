package controller.report;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import controller.employ.AdminManager;
import model.Porder;
import model.Employ;
import service.impl.PorderServiceImpl;
import java.awt.Font;


import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

public class ProductChart extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private PorderServiceImpl porderService = new PorderServiceImpl();
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
                    
                    ProductChart frame = new ProductChart(testEmploy);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public ProductChart(Employ employ) {
    	setTitle("銷售圖表介面");
        this.employ = employ;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        
        setChartTheme();

        // 取得產品銷售資料
        Map<String, Integer> productSalesData = getProductSalesData();

        // 創建圖表
        JFreeChart barChart = createLineChart(productSalesData);
        JFreeChart pieChart = createPieChart(productSalesData);
        
        // 設置中文字體
        setChartChineseFont(barChart);
        setChartChineseFont(pieChart);

        // 將圖表添加到面板
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setPreferredSize(new Dimension(400, 400));
        
        ChartPanel pieChartPanel = new ChartPanel(pieChart);
        pieChartPanel.setPreferredSize(new Dimension(400, 400));
        
        JPanel chartsPanel = new JPanel();
        chartsPanel.add(barChartPanel);
        chartsPanel.add(pieChartPanel);
        
        contentPane.add(chartsPanel, BorderLayout.CENTER);

        // 返回按鈕
        JButton btnBack = new JButton("回上一頁");
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                AdminManager adminManager = new AdminManager(employ);
                adminManager.setVisible(true);
                dispose();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnBack);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    // 設置中文字體並應用主題
    private void setChartTheme() {
        StandardChartTheme theme = new StandardChartTheme("JFreeChart");
        Font font = new Font("標楷體", Font.PLAIN, 12);
        theme.setExtraLargeFont(new Font("標楷體", Font.BOLD, 20));
        theme.setLargeFont(new Font("標楷體", Font.BOLD, 14));
        theme.setRegularFont(font);
        theme.setSmallFont(font);
        ChartFactory.setChartTheme(theme);
    }

    // 新增的方法：設置圖表的中文字體
    private void setChartChineseFont(JFreeChart chart) {
        // 設置標題字體
        chart.getTitle().setFont(new Font("標楷體", Font.BOLD, 20));
        // 設置圖例字體
        chart.getLegend().setItemFont(new Font("標楷體", Font.PLAIN, 12));
        
        if (chart.getPlot() instanceof CategoryPlot) {
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            // 設置 X 軸標籤字體
            plot.getDomainAxis().setLabelFont(new Font("標楷體", Font.BOLD, 14));
            // 設置 X 軸刻度字體
            plot.getDomainAxis().setTickLabelFont(new Font("標楷體", Font.PLAIN, 12));
            // 設置 Y 軸標籤字體
            plot.getRangeAxis().setLabelFont(new Font("標楷體", Font.BOLD, 14));
            // 設置 Y 軸刻度字體
            plot.getRangeAxis().setTickLabelFont(new Font("標楷體", Font.PLAIN, 12));
        } else if (chart.getPlot() instanceof PiePlot) {
            PiePlot piePlot = (PiePlot) chart.getPlot();
            // 設置圓餅圖標籤字體
            piePlot.setLabelFont(new Font("標楷體", Font.PLAIN, 12));
        }
    }

    private Map<String, Integer> getProductSalesData() {
        List<Porder> porders = porderService.selectAllPorders();
        Map<String, Integer> salesData = new HashMap<>();
        for (Porder porder : porders) {
            String productName = porder.getProductname();
            int quantity = porder.getQuantity();
            salesData.put(productName, salesData.getOrDefault(productName, 0) + quantity);
        }
        return salesData;
    }

    private JFreeChart createLineChart(Map<String, Integer> salesData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            // 直接使用原始的產品名稱
            dataset.addValue(entry.getValue(), "銷售量", entry.getKey());
        }
        
       
        JFreeChart chart = ChartFactory.createLineChart(
            "產品銷售量折線圖", // 圖表標題
            "產品名稱", // X 軸標籤
            "銷售數量", // Y 軸標籤
            dataset,
            PlotOrientation.VERTICAL, // 折線圖通常是垂直的
            true, true, false);
            
        // 取得圖表的繪圖區域
        CategoryPlot plot = chart.getCategoryPlot();
        
        // 取得X軸
        CategoryAxis domainAxis = plot.getDomainAxis();
        
        // 旋轉X軸標籤，使其不會互相重疊
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
        
        return chart;
    }

    private JFreeChart createPieChart(Map<String, Integer> salesData) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        for (Map.Entry<String, Integer> entry : salesData.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        
        JFreeChart chart = ChartFactory.createPieChart(
            "產品銷售量圓餅圖",
            dataset,
            true, true, false);

      
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
        plot.setCircular(false);
        
        return chart;
    }
}