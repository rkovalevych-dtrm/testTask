package utils;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@UtilityClass
public class XLSUtils {

    @Step("Getting headers from file template: {file}")
    public Map<String, Integer> getTemplateHeaders(File file) throws IOException {
        return getTemplateHeadersFromRow(file, 1, 1);
    }

    @Step("Getting headers on {headersRow} row of {sheetNumber} sheet from file template: {file}")
    public Map<String, Integer> getTemplateHeadersFromRow(File file, int sheetNumber, int headersRow) throws IOException {
        Map<String, Integer> headers = new LinkedHashMap<>();
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet dataSheet = workbook.getSheetAt(sheetNumber - 1);
        Iterator<Cell> cellIterator = dataSheet.getRow(headersRow - 1).cellIterator();

        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();
            if (headers.containsKey(currentCell.getStringCellValue())) {
                currentCell.setCellValue(currentCell.getStringCellValue()
                        + new Random().nextDouble());
            }
            headers.put(currentCell.getStringCellValue(), currentCell.getColumnIndex());
        }

        return headers;
    }

    @Step("Insert test data, into file: {file}")
    public void insertDataIntoXSLFile(Set<Map<String, Object>> listOfRows, Map<String, Integer> template, int startRow, File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet dataSheet = workbook.getSheetAt(0);
        int i = startRow - 1;
        for (Map<String, Object> listOfRow : listOfRows) {
            i++;
            Row row = dataSheet.createRow(i);
            for (Map.Entry<String, Object> value : listOfRow.entrySet()) {
                int cellIndex;
                for (Map.Entry<String, Integer> header : template.entrySet()) {
                    if (header.getKey().equals(value.getKey())) {
                        cellIndex = header.getValue();
                        Cell cell = row.createCell(cellIndex);
                        if (value.getValue() instanceof Number || value.getValue() instanceof String) {
                            cell.setCellValue(String.valueOf(value.getValue()));
                        } else if (value.getValue() instanceof Boolean) {
                            cell.setCellValue((Boolean) value.getValue() ? 1 : 0);
                        } else if (value.getValue() instanceof List) {
                            cell.setCellValue(String.valueOf((
                                    (List) value.getValue()).stream()
                                    .map(Object::toString).collect(Collectors.joining(","))));
                        }
                    }
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
