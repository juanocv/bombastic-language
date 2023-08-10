package br.com.bombastic.ast;

import br.com.bombastic.datastructures.BombasticVariable;

public class CommandLeitura extends AbstractCommand {
    
    private String id;
    private BombasticVariable var;

    public CommandLeitura(String id, BombasticVariable var) {
        this.id = id;
        this.var = var;
    }

    @Override
    public String generateJavaCode() {
        // TODO
        return id + " = key." + (var.getType()==BombasticVariable.NUMBER? "nextDouble();": "nextLine();");
    }
    
	@Override
	public String generateJSCode() {				
		return  id + " = prompt(" +"'Digite valor de "+id+"');";		
	}

    
    @Override
    public String toString() {
        return "CommandLeitura [id=" + id + ", var=" + var + "]";
    }

}
