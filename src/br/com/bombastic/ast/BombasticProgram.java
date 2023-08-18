package br.com.bombastic.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import br.com.bombastic.datastructures.BombasticSymbol;
import br.com.bombastic.datastructures.BombasticSymbolTable;

public class BombasticProgram {
    private BombasticSymbolTable varTable;
    private ArrayList<AbstractCommand> comandos;
    private String programName;

    public void generateTargetJava() {
        StringBuilder str = new StringBuilder();
        str.append("import java.util.Scanner;\n\n");
        str.append("public class MainTestClass{ \n");
        str.append("\tpublic static void main(String args[]){ \n");
        str.append("\t\tScanner key = new Scanner(System.in);\n");
        for (BombasticSymbol symbol: varTable.getAll()) {
            str.append("\t\t"+symbol.generateJavaCode()+"\n");
        }
        for (AbstractCommand command: comandos) {
            str.append("\t\t"+command.generateJavaCode()+"\n");
        }
        str.append("\t}\n");
        str.append("}");

        try {
            FileWriter fr = new FileWriter(new File("src/Linguagem-Geradas/MainTestClass.java"));
            fr.write(str.toString());
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void generateTargetJS() {
        StringBuilder str = new StringBuilder();        
        
        for (BombasticSymbol symbol: varTable.getAll()) {
            str.append("\t\t"+symbol.generateJSCode()+"\n");
        }
        
        for (AbstractCommand command: comandos) {
            str.append("\t\t"+command.generateJSCode()+"\n");
        }
        
        try {
            FileWriter fr = new FileWriter(new File("src/Linguagem-Geradas/MainTestClass.js"));
            fr.write(str.toString());
            fr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public BombasticSymbolTable getVarTable() {
        return varTable;
    }

    public void setVarTable(BombasticSymbolTable varTable) {
        this.varTable = varTable;
    }

    public ArrayList<AbstractCommand> getComandos() {
        return comandos;
    }

    public void setComandos(ArrayList<AbstractCommand> comandos) {
        this.comandos = comandos;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }
}
