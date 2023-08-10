package br.com.bombastic.datastructures;

public class BombasticVariable extends BombasticSymbol{

    public static final int NUMBER=0;
    public static int TEXT=1;
    public static int CHAR=2;
    
    private int type;
    private String value;

    public BombasticVariable(String name, int type, String value){
        super(name);
        this.type = type;
        this.value = value;
    }
    

    @Override
    public String toString() {
        return "BombasticVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
    }

    public String generateJavaCode() {
        String str;
        if (type == NUMBER) {
            str = "double";
        }
        else if (type == TEXT) {
            str = "String";
        }
        else{
            str = "char";
        }
        return str + " " +super.name+ ";";
    }
    
	@Override
	public String generateJSCode() {
		
        return "let " +super.name+ ";";
	}
    
    public static int getNumber() {
        return NUMBER;
    }

    public static int getTEXT() {
        return TEXT;
    }

    public static void setTEXT(int tEXT) {
        TEXT = tEXT;
    }
    public static int getCHAR() {
        return CHAR;
    }

    public static void setCHAR(int cHAR) {
        CHAR = cHAR;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
