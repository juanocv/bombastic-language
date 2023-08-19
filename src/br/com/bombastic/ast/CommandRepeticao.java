package br.com.bombastic.ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
    
    private String condition;
    private ArrayList<AbstractCommand> listaLoop;
    private boolean isDoWhile; 
    
    public CommandRepeticao(String condition, ArrayList<AbstractCommand> listaLoop, boolean isDoWhile) {
        this.condition = condition;
        this.listaLoop = listaLoop;
        this.isDoWhile = isDoWhile;
    }

    @Override
    public String generateJavaCode() {
        StringBuilder str = new StringBuilder();
        
        if (isDoWhile) {
            str.append("do {\n");
            for (AbstractCommand cmd : listaLoop) {
            	str.append("\t\t\t");
                str.append(cmd.generateJavaCode());
            }
            str.append("\n\t\t} while (" + condition + ");\n");
        } else {
            str.append("while (" + condition + ") {\n");
            for (AbstractCommand cmd : listaLoop) {
            	str.append("\t\t\t");
                str.append(cmd.generateJavaCode());
            }
            str.append("\n\t\t}\n");            
        }
        
        return str.toString();
    }
    
	@Override
	public String generateJSCode() {
		StringBuilder str = new StringBuilder();        
        if (isDoWhile) {
            str.append("do {\n");            
            for (AbstractCommand cmd : listaLoop) {
            	str.append("\t\t\t");
                str.append(cmd.generateJSCode());
            }
            str.append("\n\t\t} while (" + condition + ");\n");
        } else {            
        	str.append("while (" + condition + ") {\n");            
            for (AbstractCommand cmd : listaLoop) {    
            	str.append("\t\t\t");
                str.append(cmd.generateJSCode());
            }
            str.append("\n\t\t}\n");
        }
        
        return str.toString();
	}
    
    @Override
    public String toString() {
        return "CommandRepeticao [condition=" + condition + ", listaLoop=" + listaLoop + "]";
    }

}
