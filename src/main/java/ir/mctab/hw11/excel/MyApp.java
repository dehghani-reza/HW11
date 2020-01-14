package ir.mctab.hw11.excel;

import ir.mctab.hw11.excel.entities.Person;
import ir.mctab.hw11.excel.repositories.CrudDAO;
import ir.mctab.hw11.excel.repositories.ExcelIO;
import ir.mctab.hw11.excel.repositories.FileIO;
import ir.mctab.hw11.excel.repositories.PersonDAO;
import ir.mctab.hw11.excel.service.Command;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ir.mctab.hw11.excel.service.Color.*;

//"C:\\Users\\Reza\\IdeaProjects\\HW11\\Book1.xlsx"

public class MyApp {

    //**********************save********************************

    static void save(Operator operator, Path path) throws Exception {
        Person p1 = new Person();
        List<Person> personList = new ArrayList<>();
        FileIO fileIO;
        try {
            fileIO = new ExcelIO(path);
        } catch (Exception e) {
            System.out.println(Red + "File not exist new one will create after save progress" + reset);
            fileIO = new ExcelIO(new XSSFWorkbook());
        }
        CrudDAO crudDAO = new PersonDAO((XSSFWorkbook) fileIO.getFile());

        Scanner scanner = new Scanner(System.in);
        System.out.println("id");
        p1.setId(Long.valueOf(scanner.nextLine()));
        System.out.println("FirstName?");
        p1.setFirstName(scanner.nextLine());
        System.out.println("LastName?");
        p1.setLastName(scanner.nextLine());
        System.out.println("PhoneNumber?");
        p1.setPhoneNumber(Long.valueOf(scanner.nextLine()));
        personList.add(p1);

        operator.printToFileWithList(personList, crudDAO, fileIO, path);
    }

    //**********************loadAll********************************

    static void loadAll(Operator operator, Path path) throws IOException {

        List<Person> personList = new ArrayList<>();

        FileIO fileIO1 = new ExcelIO(path);
        CrudDAO crudDAO1 = new PersonDAO((XSSFWorkbook) fileIO1.getFile());

        personList = operator.loadAllFromFile(crudDAO1);
        personList.forEach(System.out::println);
    }

    //**********************loadByNumber********************************

    static void loadByNumber(Path path, Operator operator, Long aLong) throws IOException {
        List<Person> personList;
        FileIO fileIO1 = new ExcelIO(path);
        CrudDAO crudDAO1 = new PersonDAO((XSSFWorkbook) fileIO1.getFile());

        personList = operator.loadFromFileByNumber(crudDAO1, aLong);
        personList.forEach(System.out::println);
    }

    //*******************************main********************************

    public static void main(String[] args) throws Exception {

        Operator operator = new Operator();

        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Command command = new Command("");

        Path path = null;

        while (!command.getValue().equalsIgnoreCase("exit")) {
            if (path == null) {
                System.out.println("first type your path like: C:\\\\Users\\\\Reza\\\\IdeaProjects\\\\HW11\\\\Book1.xlsx ");
                path = Paths.get(scanner.nextLine().replaceAll("\\s+", ""));
                if (!path.toString().contains(":\\")) {
                    System.out.println(Red + "wrong path" + reset);
                    path = null;
                    continue;
                }
            }
            Command.showCommand();
            System.out.println(Yellow + "type your command: " + reset);
            command.setValue(scanner.nextLine().replaceAll("\\s+", ""));
            try {
                if (!Command.commandValidation(command.getValue())) continue;
                if (command.getValue().equalsIgnoreCase("loadAll")) {
                    loadAll(operator, path);
                }
                if (command.getValue().equalsIgnoreCase("loadByNumber")) {
                    System.out.println("How many row?");
                    Long along = scanner1.nextLong();
                    loadByNumber(path, operator, along);
                }
                if (command.getValue().equalsIgnoreCase("Save")) {

                    save(operator, path);

                }
                if (command.getValue().equalsIgnoreCase("ChangePath")) {
                    path = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(Purple+e.getMessage()+reset);
            }
        }
    }

}
