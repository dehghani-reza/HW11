package ir.mctab.hw11.excel.repositories;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Path;

public class ExcelIO implements FileIO<XSSFWorkbook> {

    XSSFWorkbook workbook;


    public ExcelIO(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public ExcelIO(Path path) throws IOException {
        this.workbook = new XSSFWorkbook(new FileInputStream(String.valueOf(path)));
    }


    @Override
    public XSSFWorkbook getFile() {
        return workbook;
    }


    @Override
    public void outPut(XSSFWorkbook workbook , Path path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(String.valueOf(path));
        workbook.write(fileOutputStream);
        workbook.close();
    }
}
