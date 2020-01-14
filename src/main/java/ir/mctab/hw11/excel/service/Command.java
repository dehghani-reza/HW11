package ir.mctab.hw11.excel.service;

import static ir.mctab.hw11.excel.service.Color.*;
import static ir.mctab.hw11.excel.service.Color.Green;

public class Command {

    String value;

    public Command(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static void showCommand() {
        System.out.println(Yellow + "your option as command: " + reset);
        System.out.println(Green + "loadAll | loadByNumber | Save | ChangePath | exit" + reset);
    }

    public static boolean commandValidation(String value) {
        if (value.equalsIgnoreCase("loadAll") ||
                value.equalsIgnoreCase("loadByNumber") ||
                value.equalsIgnoreCase("Save") ||
                value.equalsIgnoreCase("ChangePath") ||
                value.equalsIgnoreCase("exit")) {
            return true;
        }else {
            System.out.println(Red + "Wrong value" + reset);
            return false;
        }
    }


}
