package br.com.bombastic.datastructures;

import java.util.ArrayList;
import java.util.HashMap;

public class BombasticSymbolTable {
    
    private HashMap<String, BombasticSymbol> map;

    public BombasticSymbolTable() {
        map = new HashMap<String, BombasticSymbol>();
    }

    public void add(BombasticSymbol symbol) {
        map.put(symbol.getName(), symbol);
    }

    public boolean exists(String symbolName) {
        return map.get(symbolName) != null;
    }

    public BombasticSymbol get(String symbolName) {
        return map.get(symbolName);
    }

    public ArrayList<BombasticSymbol> getAll(){
        ArrayList<BombasticSymbol> lista = new ArrayList<BombasticSymbol>();
        for (BombasticSymbol symbol: map.values()) {
            lista.add(symbol);
        }
        return lista;
    }
}
