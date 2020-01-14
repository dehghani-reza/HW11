package ir.mctab.hw11.excel.service;

public enum Color {

    reset("\u001B[0m"),Black("\u001B[30m")
    ,Red("\u001B[31m"),Green("\u001B[32m")
    ,Yellow("\u001B[33m"),Blue("\u001B[34m")
    ,Purple("\u001B[35m"),Cyan("\u001B[36m")
    ,White("\u001B[37m");

    private String var;

    Color(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return  var ;
    }
}
