package ir.mctab.hw11.excel;

import ir.mctab.hw11.excel.entities.Person;
import ir.mctab.hw11.excel.repositories.CrudDAO;
import ir.mctab.hw11.excel.repositories.FileIO;

import java.nio.file.Path;
import java.util.List;

public class Operator {

    void printToFileWithList(List list, CrudDAO crudDAO, FileIO fileIO, Path path) throws Exception {
        for (int i = 0; i <list.size() ; i++) {
            crudDAO.save(list.get(i));
        }
        fileIO.outPut(fileIO.getFile(),path);
    }

    public List<Person> loadFromFileByNumber(CrudDAO crudDAO1, Long number) {
        List<Person> personList = crudDAO1.LoadByNumber(number);
        return personList;
    }

    public List<Person> loadAllFromFile(CrudDAO crudDAO1) {
        List<Person> personList = crudDAO1.LoadAll();
        return personList;
    }
}
