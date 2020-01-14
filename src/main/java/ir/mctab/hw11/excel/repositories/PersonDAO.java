package ir.mctab.hw11.excel.repositories;

import ir.mctab.hw11.excel.service.Color;
import ir.mctab.hw11.excel.entity.Person;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements CrudDAO<Person> {

    XSSFSheet sheet;
    XSSFWorkbook workbook;


    public PersonDAO(XSSFWorkbook workbook) {
        this.workbook = workbook;
        if (workbook.getSheet("Person") == null) {
            sheet = workbook.createSheet("Person");
        }
    }

    @Override
    public void save(Person person) {
        long id;
        if(sheet==null)sheet = workbook.getSheet("Person");

        XSSFRow row = sheet.createRow(Math.toIntExact(person.getId()));
        XSSFCell cell1 = row.createCell(0);
        XSSFCell cell2 = row.createCell(1);
        XSSFCell cell3 = row.createCell(2);
        XSSFCell cell4 = row.createCell(3);
        cell1.setCellValue(person.getId());
        cell2.setCellValue(person.getFirstName());
        cell3.setCellValue(person.getLastName());
        cell4.setCellValue(person.getPhoneNumber().toString());
    }

    @Override
    public List<Person> LoadByNumber(Long i) {
        XSSFSheet sheet1 = workbook.getSheet("Person");
        if(sheet1.getLastRowNum()<i){
            i= (long) sheet1.getLastRowNum();
            System.out.println(Color.Red+"Max number is:"+ (i+1)+Color.reset);
        }
        List<Person> personList = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            XSSFRow row = sheet1.getRow(j);
            Person person = rowToPerson(row);
            if(person==null)continue;
            personList.add(person);
        }
        return personList;
    }

    @Override
    public List<Person> LoadAll() {
        List<Person> personList = new ArrayList<>();
        XSSFSheet sheet1 = workbook.getSheet("Person");
        for (int j = 0; j <= sheet1.getLastRowNum(); j++) {
            XSSFRow row = sheet1.getRow(j);
            Person person = rowToPerson(row);
            if(person==null)continue;
            personList.add(person);
        }
        return personList;
    }

    static Person rowToPerson(XSSFRow row) {
        if(row==null)return null;
        XSSFCell cell1 = row.getCell(0);
        XSSFCell cell2 = row.getCell(1);
        XSSFCell cell3 = row.getCell(2);
        XSSFCell cell4 = row.getCell(3);
        Person p1 = new Person();
        p1.setId((long) cell1.getNumericCellValue());
        p1.setFirstName(cell2.getStringCellValue());
        p1.setLastName(cell3.getStringCellValue());
        p1.setPhoneNumber(Long.valueOf(cell4.getStringCellValue()));
        return p1;
    }
}
