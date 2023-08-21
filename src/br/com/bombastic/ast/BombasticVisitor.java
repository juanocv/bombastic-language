package br.com.bombastic.ast;

import br.com.bombastic.antlr.BombasticBaseVisitor;
import br.com.bombastic.antlr.BombasticParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BombasticVisitor extends BombasticBaseVisitor<String> {

    private final Map<String, Variable> varMap = new HashMap<>();

    @Override
    public String visitProg(BombasticParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public String visitDecl(BombasticParser.DeclContext ctx) {
        return ctx.declaravar().stream().map(this::visitDeclaravar).collect(Collectors.toList()).toString();
    }

    @Override
    public String visitDeclaravar(BombasticParser.DeclaravarContext ctx) {
        String type = visitTipo(ctx.tipo());
        List<Variable> vars = new ArrayList<>();
        ctx.ID().forEach(id -> {
            Variable var = new Variable(id.getText(), type);
            vars.add(var);
            varMap.put(id.getText(), var);
            System.out.println("Variavel " + id.getText() + " do tipo " + type + " declarada!");
        });

        return vars.toString();
    }

    @Override
    public String visitTipo(BombasticParser.TipoContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitCmdleitura(BombasticParser.CmdleituraContext ctx) {
        System.out.println("---------------------------------------------------");
        System.out.println("Executando comando de leitura!");
        String varLeitura = ctx.ID().getText();
        Variable var = varMap.get(varLeitura);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Digite o valor da variavel: %s", varLeitura);
            String str = br.readLine();

            String type = var.type;
            if (Objects.equals(type, "num")) {
                var.value = Double.parseDouble(str);
            } else if (Objects.equals(type, "txt")) {
                var.value = str;
            } else if (Objects.equals(type, "char")) {
                var.value = str.toCharArray()[0];
            } else {
                String errorMsg = "O formato do valor digitado é inválido!";
                System.out.println(errorMsg);
                throw new RuntimeException(errorMsg);
            }

            System.out.println("Valor da variavel " + varLeitura + " = " + str);
            varMap.put(varLeitura, var);
            System.out.println("---------------------------------------------------");
            return str;
        } catch (Exception ex) {
            System.out.printf("Erro ao ler o valor da variavel: %s", varLeitura);
            throw new RuntimeException(String.format("Erro ao ler o valor da variavel: %s", varLeitura), ex);
        }
    }

    @Override
    public String visitCmdescrita(BombasticParser.CmdescritaContext ctx) {
        System.out.println("---------------------------------------------------");
        System.out.println("Executando comando de escrita!");
        String varEscrita = ctx.ID().getText();
        Variable var = varMap.get(varEscrita);

        System.out.println("Valor da variavel " + varEscrita + " = " + var.value);
        System.out.println("---------------------------------------------------");
        return var.value.toString();
    }

    @Override
    public String visitCmdattrib(BombasticParser.CmdattribContext ctx) {
        System.out.println("---------------------------------------------------");
        System.out.println("Executando comando de atribuição!");
        String varName = ctx.ID().getText();
        System.out.println("Variavel a ser atribuida: " + varName);
        Variable var = varMap.get(varName);
        System.out.println("Valor atual da variavel: " + var.value);

        var.value = visitExpr(ctx.expr());
        System.out.println("Valor atribuído a variavel " + varName + " = " + var.value);
        System.out.println("---------------------------------------------------");
        return var.value.toString();
    }

    @Override
    public String visitExpr(BombasticParser.ExprContext ctx) {
        List<String> vars = ctx.termo().stream().map(this::visitTermo).collect(Collectors.toList());
        List<String> ops = ctx.OP().stream().map(ParseTree::getText).collect(Collectors.toList());
        System.out.println("Lista de termos: " + vars);
        System.out.println("Lista de operadores: " + ops);

        for (String var : vars) {
            if (!isNumber(var)) {
                return evaluateTextExpression(vars, ops);
            }
        }

        return evaluateArithmeticExpression(vars, ops);
    }

    @Override
    public String visitTermo(BombasticParser.TermoContext ctx) {
        return ctx.getChild(0).getText();
    }

    private boolean isNumber(String str){
        Variable var = varMap.get(str);
        if (var != null) {
            return Objects.equals(var.type, "num");
        }

        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public String visitCmdselecao(BombasticParser.CmdselecaoContext ctx) {
        System.out.println("---------------------------------------------------");
        System.out.println("Executando comando de selecao");
        boolean condition = evaluateCondition(ctx.getChild(2).getText(), ctx.getChild(3).getText(), ctx.getChild(4).getText());
        System.out.printf("Condicao avaliada: %s %s %s, valor da condicao %s", ctx.getChild(2).getText(), ctx.getChild(3).getText(), ctx.getChild(4).getText(), condition);
        System.out.println();

        Map<Boolean, List<BombasticParser.CmdContext>> commandsMap = getCommands(ctx);
        List<BombasticParser.CmdContext> commands = commandsMap.get(condition);

        System.out.println("Comandos executados para a condicao: " + commands);
        commands.forEach(this::visitCmd);
        System.out.println("---------------------------------------------------");
        return commands.toString();
    }

    @Override
    public String visitCmdrepeticao(BombasticParser.CmdrepeticaoContext ctx) {
        System.out.println("---------------------------------------------------");
        System.out.println("Executando comando de repeticao");
        List<BombasticParser.CmdContext> commands = ctx.cmd();

        boolean doWhileFlag = ctx.children.stream()
                .map(ParseTree::getText)
                .anyMatch(txt -> txt.equals("faca"));

        if (doWhileFlag) {
            commands.forEach(this::visitCmd);
        }

        int[] conditionIndex = findConditionIndex(ctx);
        while (evaluateCondition(ctx.getChild(conditionIndex[0]).getText(), ctx.getChild(conditionIndex[1]).getText(), ctx.getChild(conditionIndex[2]).getText())) {
            System.out.printf("Condicao avaliada: %s %s %s, valor da condicao %s", ctx.getChild(conditionIndex[0]).getText(), ctx.getChild(conditionIndex[1]).getText(), ctx.getChild(conditionIndex[2]).getText(), true);
            System.out.println();
            System.out.println("Repetindo comandos");
            commands.forEach(this::visitCmd);
        }

        System.out.println("---------------------------------------------------");
        return commands.toString();
    }

    private String evaluateTextExpression(List<String> vars, List<String> ops) {
        System.out.println("Avaliando expressao de texto!");
        ops.stream().filter(op -> !op.equals("+")).collect(Collectors.toList()).forEach(op -> {
            String errorMsg = "Operador " + op + " não é válido para o tipo texto!";
            System.out.println(errorMsg);
            throw new RuntimeException(errorMsg);
        });

        StringBuilder sb = new StringBuilder();
        vars.forEach(var -> {
            Variable variable = varMap.get(var);
            if (variable != null) {
                sb.append(variable.value);
            } else {
                sb.append(var);
            }
        });
        return sb.toString();
    }

    private String evaluateArithmeticExpression(List<String> vars, List<String> ops) {
        System.out.println("Avaliando expressao aritmetica!");
        for (int i = 0; i < ops.size(); i++) {
            if (ops.get(i).equals("*") || ops.get(i).equals("/")) {
                i = solve(i, vars, ops);
            }
        }

        for (int i = 0; i < ops.size(); i++) {
            i = solve(i, vars, ops);
        }

        return vars.get(0);
    }

    private int solve(int index, List<String> vars, List<String> ops) {
        System.out.println("Valor do indice: " + index);
        double a = Double.parseDouble(varMap.get(vars.get(index)) == null ? vars.get(index) : varMap.get(vars.get(index)).value.toString());
        double b = Double.parseDouble(varMap.get(vars.get(index + 1)) == null ? vars.get(index + 1) : varMap.get(vars.get(index + 1)).value.toString());
        double result = makeOperation(ops.get(index), a, b);
        System.out.println("Resolvendo " + a + " " + ops.get(index) + " " + b + " = " + result);
        vars.set(index, String.valueOf(result));
        vars.remove(index + 1);
        ops.remove(index--);
        return index;
    }

    private double makeOperation(String op, double a, double b) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new RuntimeException("Operador " + op + " não é válido!");
        };
    }

    private boolean evaluateCondition(String left, String op, String right) {
        Variable variable1 = varMap.get(right);
        right = variable1 != null ? variable1.value.toString() : right;

        Variable variable2 = varMap.get(left);

        if (!sameType(variable2.type, right)) {
            String errorMsg = String.format("O tipo da variavel %s (%s) é diferente da variavel %s!", variable2.name, variable2.type, right);
            System.out.println(errorMsg);
            throw new RuntimeException(errorMsg);
        }

        return switch (variable2.type) {
            case "num" -> evaluate(Double.parseDouble(variable2.value.toString()), op, Double.parseDouble(right));
            case "txt" -> evaluate(variable2.value.toString(), op, right);
            case "char" -> evaluate(variable2.value.toString().charAt(0), op, right.charAt(0));
            default -> throw new RuntimeException("Tipo " + variable2.type + " não é válido!");
        };
    }

    private boolean evaluate(Double var, String op, Double compare) {
        return switch (op) {
            case "==" -> var.equals(compare);
            case "!=" -> !var.equals(compare);
            case ">" -> var > compare;
            case "<" -> var < compare;
            case ">=" -> var >= compare;
            case "<=" -> var <= compare;
            default -> throw new RuntimeException("Operador " + op + " não é válido!");
        };
    }

    private boolean evaluate(String var, String op, String compare) {
        return switch (op) {
            case "==" -> var.equals(compare);
            case "!=" -> !var.equals(compare);
            case ">" -> var.compareTo(compare) > 0;
            case "<" -> var.compareTo(compare) < 0;
            case ">=" -> var.compareTo(compare) > 0 || var.equals(compare);
            case "<=" -> var.compareTo(compare) < 0 || var.equals(compare);
            default -> throw new RuntimeException("Operador " + op + " não é válido!");
        };
    }

    private boolean evaluate(Character var, String op, Character compare) {
        return switch (op) {
            case "==" -> var.equals(compare);
            case "!=" -> !var.equals(compare);
            case ">" -> var.compareTo(compare) > 0;
            case "<" -> var.compareTo(compare) < 0;
            case ">=" -> var.compareTo(compare) > 0 || var.equals(compare);
            case "<=" -> var.compareTo(compare) < 0 || var.equals(compare);
            default -> throw new RuntimeException("Operador " + op + " não é válido!");
        };
    }

    private boolean sameType(String type, String value) {
        if (type.equals("num") && isNumber(value)) {
            return true;
        } else if (type.equals("txt") && !isNumber(value)) {
            return true;
        } else if (type.equals("char") && !isNumber(value) && value.length() == 1) {
            return true;
        }

        return false;
    }

    private Map<Boolean, List<BombasticParser.CmdContext>> getCommands(BombasticParser.CmdselecaoContext ctx) {
        List<List<BombasticParser.CmdContext>> commands = new ArrayList<>();
        int cmdCount = 0;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().equals("{")) {
                i++;
                List<BombasticParser.CmdContext> cmds = new ArrayList<>();
                while (!ctx.getChild(i).getText().equals("}")) {
                    cmds.add(ctx.cmd(cmdCount++));
                    i++;
                }
                commands.add(cmds);
            }
        }

        return Map.of(true, commands.get(0), false, commands.get(1));
    }

    private int[] findConditionIndex(BombasticParser.CmdrepeticaoContext ctx) {
        int[] indexes = new int[3];
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (ctx.getChild(i).getText().equals("(")) {
                indexes[0] = i + 1;
                indexes[1] = i + 2;
                indexes[2] = i + 3;
                return indexes;
            }
        }

        throw new RuntimeException("Erro ao encontrar condição!");
    }

    private class Variable {
        private final String name;
        private final String type;
        private Object value;

        public Variable(String name, String type) {
            this.name = name;
            this.type = type;
        }
    }
}
