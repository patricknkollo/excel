package com.excel.export.excel.services;

import com.excel.export.excel.entities.Person;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PersonExcelGenerator {

    private List<Person> personList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public PersonExcelGenerator(List < Person > list) {
        this.personList = list;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Person");
        Row row0 = sheet.createRow(0);
        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        Row row = sheet.createRow(3);
        CellStyle style = workbook.createCellStyle();
        CellStyle style1 = workbook.createCellStyle();
        CellStyle style2 = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        XSSFFont font1 = workbook.createFont();
        XSSFFont font3 = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        font1.setBold(true);
        font1.setFontHeight(14);
        font3.setBold(false);
        font3.setFontHeight(14);
        style.setFont(font);
        style1.setFont(font1);
        style2.setFont(font3);
        createCell(row0, 5, "THE LIST OF ALL THE Person", style1);
        createCell(row1, 5, "Bel air Hightschool", style1);
        createCell(row2, 3, "Bel air Hightschool", style2);
        createCell(row, 3, "ID", style);
        createCell(row, 4, "Prenom", style);
        createCell(row, 5, "Nom", style);
        createCell(row, 6, "Age", style);
        createCell(row, 7, "Nationalite", style);
    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 4;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Person record: personList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 3;
            createCell(row, columnCount++, record.getPersonid(), style);
            createCell(row, columnCount++, record.getPrenom(), style);
            createCell(row, columnCount++, record.getNom(), style);
            createCell(row, columnCount++, record.getAge(), style);
            createCell(row, columnCount++, record.getNationaliteid(), style);
        }

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Row rowFoot1 = sheet.createRow(rowCount + personList.toArray().length -1);
        CellStyle style2 = workbook.createCellStyle();
        XSSFFont font3 = workbook.createFont();
        font3.setBold(false);
        font3.setFontHeight(14);
        font3.setItalic(true);
        style2.setFont(font3);
        createCell(rowFoot1, 1, "List Footer "+currentDateTime, style2);
    }
    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
