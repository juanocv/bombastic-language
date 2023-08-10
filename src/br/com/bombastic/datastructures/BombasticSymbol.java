package br.com.bombastic.datastructures;

public abstract class BombasticSymbol {
    
    protected String name;

    public abstract String generateJavaCode();
    public abstract String generateJSCode();
    
    public BombasticSymbol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BombasticSymbol [name=" + name + "]";
    }
}
