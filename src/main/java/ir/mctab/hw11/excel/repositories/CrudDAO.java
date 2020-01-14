package ir.mctab.hw11.excel.repositories;

import ir.mctab.hw11.excel.entity.Person;

import java.io.FileNotFoundException;
import java.util.List;

public interface CrudDAO<T> {

     void save(T t);

     public List<Person> LoadByNumber(Long i);

     public List<Person> LoadAll();
}
