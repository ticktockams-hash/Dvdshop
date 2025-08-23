package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import model.Porder;
import model.Member;
import service.impl.MemberServiceImpl;


public class ReportGenerator {

    private MemberServiceImpl memberService = new MemberServiceImpl();

    public void exportMemberReport(String filePath) {
        // 取得所有會員資料
        List<Member> members = memberService.selectAllMembers();

        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("會員報表");

            // 建立標題列
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "姓名", "帳號", "密碼", "電話", "角色"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 填入會員資料
            int rowNum = 1;
            for (Member member : members) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(member.getId());
                row.createCell(1).setCellValue(member.getName());
                row.createCell(2).setCellValue(member.getUsername());
                row.createCell(3).setCellValue(member.getPassword());
                row.createCell(4).setCellValue(member.getPhone());
                row.createCell(5).setCellValue(member.getRole());
            }

            // 調整欄寬以適應內容
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(fos);
            JOptionPane.showMessageDialog(null, "報表已成功匯出至：\n" + filePath, "匯出成功", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "匯出報表失敗！", "匯出失敗", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void exportPorderReport(List<Porder> porders, String filePath) {
        try (Workbook workbook = new XSSFWorkbook(); FileOutputStream fos = new FileOutputStream(filePath)) {
            Sheet sheet = workbook.createSheet("訂單報表");

            // 創建樣式來格式化日期
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

            // 建立標題列
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "會員ID", "會員帳號", "產品名稱", "數量", "金額", "訂單日期"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 填入訂單資料
            int rowNum = 1;
            for (Porder porder : porders) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(porder.getId());
                row.createCell(1).setCellValue(porder.getMemberid());
                row.createCell(2).setCellValue(porder.getUsername());
                row.createCell(3).setCellValue(porder.getProductname());
                row.createCell(4).setCellValue(porder.getQuantity());
                row.createCell(5).setCellValue(porder.getAmount());
                
                // 直接寫入日期物件
                Cell dateCell = row.createCell(6);
                dateCell.setCellValue(porder.getPorderdate());
                dateCell.setCellStyle(dateCellStyle);
            }

            // 手動設定欄寬
            sheet.setColumnWidth(0, 5 * 256);   // ID
            sheet.setColumnWidth(1, 10 * 256);  // 會員ID
            sheet.setColumnWidth(2, 15 * 256);  // 會員帳號
            sheet.setColumnWidth(3, 30 * 356);  // 產品名稱
            sheet.setColumnWidth(4, 8 * 256);   // 數量
            sheet.setColumnWidth(5, 10 * 256);  // 金額
            sheet.setColumnWidth(6, 25 * 256);  // 訂單日期

            workbook.write(fos);
            JOptionPane.showMessageDialog(null, "訂單報表已成功匯出至：\n" + filePath, "匯出成功", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "匯出訂單報表失敗！", "匯出失敗", JOptionPane.ERROR_MESSAGE);
        }
    }
}