package br.com.bombastic.ast;

import java.util.ArrayList;

public class CommandDecisao extends AbstractCommand {
    
    private String condition;
    private ArrayList<AbstractCommand> listaTrue;
    private ArrayList<AbstractCommand> listaFalse;
    
    public CommandDecisao(String condition, ArrayList<AbstractCommand> listaTrue,
            ArrayList<AbstractCommand> listaFalse) {
        this.condition = condition;
        this.listaTrue = listaTrue;
        this.listaFalse = listaFalse;
    }

    @Override
    public String generateJavaCode() {
        // TODO
        StringBuilder str = new StringBuilder();
        str.append("if ("+condition+") {\n");
        for (AbstractCommand cmd: listaTrue) {
        	str.append("\t\t\t");
        	str.append(cmd.generateJavaCode());
        }
        str.append("\t\t}");
        if (listaFalse.size() > 0) {
            str.append(" else{\n");
            for (AbstractCommand cmd: listaFalse) {
            	str.append("\t\t\t");
                str.append(cmd.generateJavaCode());
            }
            str.append("\t\t}");
        }
        return str.toString();
    }
    
    
	@Override
	public String generateJSCode() {
        StringBuilder str = new StringBuilder();
        str.append("if ("+condition+") {\n");
        for (AbstractCommand cmd: listaTrue) {
        	str.append("\t\t\t");
            str.append(cmd.generateJSCode());
        }
        str.append("\t\t}");
        if (listaFalse.size() > 0) {
            str.append(" else{\n");
            for (AbstractCommand cmd: listaFalse) {
            	str.append("\t\t\t");
                str.append(cmd.generateJSCode());
            }
            str.append("\t\t}");
        }
        return str.toString();
	}
    @Override
    public String toString() {
        return "CommandDecisao [condition=" + condition + ", listaTrue=" + listaTrue + ", listaFalse=" + listaFalse
                + "]";
    }


}
