import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import br.com.bombastic.antlr.BombasticLexer;
import br.com.bombastic.antlr.BombasticParser;
import br.com.bombastic.exceptions.BombasticSemanticException;

public class Main{
    public static void main(String[] args) {
        try {
            BombasticLexer lexer;
            BombasticParser parser;

            lexer = new BombasticLexer(CharStreams.fromFileName("src/testes/testevarnaousada.bomb"));

            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            parser = new BombasticParser(tokenStream);

            parser.prog();

            System.out.println("Sucesso!");

            parser.exibeComandos();

            parser.generateCode();

        } catch (BombasticSemanticException ex) {
            System.err.println("Semantic error - " +ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error - " +ex.getMessage());
        }
    }
}