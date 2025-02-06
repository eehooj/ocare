package com.example.ocare.global.common.excel;

import com.example.ocare.global.exception.CustomException;
import com.example.ocare.global.exception.enumerate.FileExceptionType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
@Service
public class ExcelService {

    public <T> void exportExcel(String fileName, Map<String, List<T>> sheetData,
                                Map<String, String[]> headersMap, Map<String, String[]> fieldsMap) {
        // 시트 생성
        try (SXSSFWorkbook workbook = new SXSSFWorkbook();) {
            for (Map.Entry<String, List<T>> entry : sheetData.entrySet()) {
                String sheetName = entry.getKey();
                List<T> dataList = entry.getValue();
                String[] headers = headersMap.get(sheetName);
                String[] fieldNames = fieldsMap.get(sheetName);

                getSheet(workbook, sheetName, dataList, headers, fieldNames);
            }

            // 출력
            File file = new File("src/main/resources/static/export/", fileName);

            try (FileOutputStream output = new FileOutputStream(file)) {
                workbook.write(output);
            }
        } catch (IOException e) {
            e.printStackTrace();

            throw new CustomException(FileExceptionType.EXCEL_EXPORT_FAILED);
        }
    }

    private static <T> void getSheet(SXSSFWorkbook workbook, String sheetName,
                                     List<T> dataList, String[] headers, String[] fieldNames) {
        Sheet sheet = workbook.createSheet(sheetName);

        // 스타일
        CellStyle headerStyle = workbook.createCellStyle();

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        // 헤더 생성
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);

            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // 데이터 생성
        int rowNum = 1;
        for (T data : dataList) {
            Row row = sheet.createRow(rowNum++);
            for (int i = 0; i < fieldNames.length; i++) {
                try {
                    Field field = data.getClass().getDeclaredField(fieldNames[i]);
                    field.setAccessible(true);

                    Object value = field.get(data);

                    Cell cell = row.createCell(i);
                    cell.setCellValue(value != null ? value.toString() : "");
                    cell.setCellStyle(dataStyle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
