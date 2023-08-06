grammar Bombastic;

@header{
    import br.com.bombastic.datastructures.*;
    import br.com.bombastic.exceptions.*;
    import br.com.bombastic.ast.*;
    import java.util.ArrayList;
    import java.util.Stack;
}

@members{
    private int _tipo;
    private String _varName;
    private String _varValue;
    private BombasticSymbolTable symbolTable = new BombasticSymbolTable();
    private BombasticSymbol symbol;
    private BombasticProgram program = new BombasticProgram();
    private ArrayList<AbstractCommand> curThread;
    private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();

    private String _readId;
    private String _writeId;
    private String _exprId;
    private String _exprContent;
    private String _exprDecision;
    private ArrayList<AbstractCommand> listaTrue;
    private ArrayList<AbstractCommand> listaFalse;

    public void defineId(BombasticSymbolTable symbolTable, int _tipo, String _varName){
        _varValue = null;
        symbol = new BombasticVariable(_varName, _tipo, _varValue);
        if (!symbolTable.exists(_varName)){
            symbolTable.add(symbol);
            System.out.println("Simbolo adicionado " +symbol);
        } 
        else {
            throw new BombasticSemanticException("Symbol "+_varName+" already declared");
        }
    }

    public void verificaId(String id){
        if (symbolTable.get(id) == null){
            throw new BombasticSemanticException("Symbol "+id+" not declared");
        }
    }
    public void verificaAtr(String id){
				BombasticVariable var = (BombasticVariable)symbolTable.get(id);
				if (var == null){
					return;
				}
		       if (var.getValue() == null){
		            throw new BombasticSemanticException("Symbol "+id+" declared but not attributed");
		        }
		    }

    public void exibeComandos(){
        for(AbstractCommand c: program.getComandos()){
            System.out.println(c);
        }
    }

    public void generateCode(){
        program.generateTarget();
    }         
}


prog    : 'begin'    decl    bloco    'end'
            { 
                program.setVarTable(symbolTable);
                program.setComandos(stack.pop());
            }
        ;

decl    :   (declaravar)+
        ;

declaravar  :   tipo ID {defineId(symbolTable, _tipo, _input.LT(-1).getText());}
                    (VIR 
                    ID {defineId(symbolTable, _tipo, _input.LT(-1).getText());}
                    )* SC
            ;

tipo        :   'num' {_tipo = BombasticVariable.NUMBER;}
            |   'txt'  {_tipo = BombasticVariable.TEXT;}
            |   'char' {_tipo = BombasticVariable.CHAR;}
            ;

bloco   : { curThread = new ArrayList<AbstractCommand>();
            stack.push(curThread);
        }(cmd)+
        ;

cmd     : cmdleitura //{System.out.println("Reconheci um comando de leitura");}
        | cmdescrita //{System.out.println("Reconheci um comando de escrita");}
        | cmdattrib  //{System.out.println("Reconheci um comando de atribuição");}
        | cmdselecao //{System.out.println("Reconheci um comando de seleção");}
        | cmdrepeticao //{System.out.println("Reconheci um comando de repeticao");}
        ;

cmdleitura  : 'read' AP 
                     ID {verificaId(_input.LT(-1).getText());
                         _readId = _input.LT(-1).getText();
                         }
                     FP 
                     SC
                { 
                    BombasticVariable var = (BombasticVariable)symbolTable.get(_readId);
                    CommandLeitura cmd = new CommandLeitura(_readId, var);
                    stack.peek().add(cmd);
                }
            ;

cmdescrita  : 'write' AP 
                        ID {verificaId(_input.LT(-1).getText());
                            verificaAtr(_input.LT(-1).getText());
                            _writeId = _input.LT(-1).getText();
                            }
                        FP 
                        SC
                { 
                    CommandEscrita cmd = new CommandEscrita(_writeId);
                    stack.peek().add(cmd);
                }
            ;

cmdattrib   : ID {verificaId(_input.LT(-1).getText());
                _exprId = _input.LT(-1).getText();
              }
              ATTR {_exprContent = "";} 
              expr 
              SC
              {
                CommandAtribuicao cmd = new CommandAtribuicao(_exprId, _exprContent);
                BombasticVariable var = (BombasticVariable)symbolTable.get(_exprId);
				var.setValue(_exprContent);
                stack.peek().add(cmd);
              }
            ;

cmdselecao  : 'when' AP 
                   ID { verificaAtr(_input.LT(-1).getText());
                        _exprDecision = _input.LT(-1).getText();}
                   OPREL {_exprDecision += _input.LT(-1).getText();}
                   (ID | NUMBER | TEXT | CHAR) { verificaAtr(_input.LT(-1).getText());
                                                _exprDecision += _input.LT(-1).getText();}
                   FP 
                   AC
                   { 
                        curThread = new ArrayList<AbstractCommand>();
                        stack.push(curThread);
                   }
                   (cmd)+ 
                   FC
                   {
                        listaTrue = stack.pop();
                   }
                ('otherwise' 
                    AC
                    { 
                        curThread = new ArrayList<AbstractCommand>();
                        stack.push(curThread);
                    } 
                    (cmd+) 
                    FC
                    {
                        listaFalse = stack.pop();
                        CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                        stack.peek().add(cmd);
                    }
                )?
            ;

cmdrepeticao : 'enquanto' AP 
                   ID {_exprDecision = _input.LT(-1).getText();}
                   OPREL {_exprDecision += _input.LT(-1).getText();}
                   (ID | NUMBER) {_exprDecision += _input.LT(-1).getText();}
                   FP 
                   AC
                   { 
                        curThread = new ArrayList<AbstractCommand>();
                        stack.push(curThread);
                   }
                   (cmd)+ 
                   FC
                   {
                        
                   }
            ;

expr        : termo (OP {_exprContent += _input.LT(-1).getText();} 
                termo 
                )*
            ;

termo       : ID {verificaId(_input.LT(-1).getText());
                _exprContent += _input.LT(-1).getText();
              } 
            | NUMBER
              {
                _exprContent += _input.LT(-1).getText();
              }
            | TEXT
              {
                _exprContent += _input.LT(-1).getText();
              }
            | CHAR
              {
                _exprContent += _input.LT(-1).getText();
              }
            ;

AP  :   '('
    ;

FP  :   ')'
    ;

SC  :   ';'
    ;

OP  :   '+' | '-' | '*' | '/'
    ;

ATTR    :   '='
        ;

VIR :   ','
    ;

AC  :   '{'
    ;

FC  :   '}'
    ;

OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;

ID  :   [a-z] ([a-z] | [A-Z] | [0-9])*
    ;

NUMBER  :   [0-9]+ ('.' [0-9]+)?
        ;

TEXT  :  '"' ([a-z] | [A-Z] | [0-9])* '"'
      ;

CHAR  :  '\'' ([a-z] | [A-Z] | [0-9]) '\''
      ;

WS  :   (' ' | '\t' | '\n' | '\r') -> skip;