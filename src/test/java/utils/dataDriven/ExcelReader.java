package utils.dataDriven;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    private static FileInputStream inputStream;
    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static XSSFRow row;

    // Creating an array of the excel data
    public static FileInputStream GetFileInputStream(String path) {
        String filepath = path;
        File srcFile = new File(filepath);

        try {
            inputStream = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Testdata file can't be found on this path" + filepath);
            System.exit(0);
        }
        return inputStream;
    }

    public static String[][] getExcelData(String sheetName){

        inputStream = GetFileInputStream("src\\main\\resources\\TestData.xlsx");
        try {
            wb = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sheet = wb.getSheet(sheetName);

        int rowNum = sheet.getLastRowNum() + 1;
        int columnNum = sheet.getRow(0).getLastCellNum();

        String[][] arrayExcelData = new String[rowNum][columnNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                row = sheet.getRow(i);
                if (row.getCell(j) == null) {
                    arrayExcelData[i][j] = "";
                }else {
                    arrayExcelData[i][j] = row.getCell(j).toString();
                }
            }
        }

        try {
            wb.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayExcelData;
    }

    //Use this method to get a cell value by identifying the sheet name and the cellName on the left of cellData
    public static String getExcelValueByAttribute(String sheetName, String ExcelAttribute){
        String cellValue = null;
        String[][] array = getExcelData(sheetName);
        for (int i = 0; i < array.length; i++) {
            String attribute = array[i][0];
            if (attribute.equalsIgnoreCase(ExcelAttribute)) {
                cellValue = array[i][1];
                break;
            }
        }
        return cellValue;
    }
}
