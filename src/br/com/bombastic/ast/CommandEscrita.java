package br.com.bombastic.ast;

public class CommandEscrita extends AbstractCommand {
    
    private String id;

    public CommandEscrita(String id) {
        this.id = id;
    }
    
    @Override
    public String generateJavaCode() {
        // TODO
        return "\t\tSystem.out.println("+id+");\n";
    }

    @Override
    public String toString() {
        return "CommandEscrita [id=" + id + "]";
    }
}
