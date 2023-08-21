import br.com.bombastic.antlr.BombasticLexer;
import br.com.bombastic.antlr.BombasticParser;
import br.com.bombastic.ast.BombasticVisitor;
import br.com.bombastic.exceptions.BombasticSemanticException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class MainInterpretor {

    public static void main(String[] args) {
        try {
            BombasticVisitor interpreter = new BombasticVisitor();
            BombasticLexer lexer;
            BombasticParser parser;

            lexer = new BombasticLexer(CharStreams.fromFileName("src/teste.bomb"));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            parser = new BombasticParser(tokenStream);

            BombasticParser.ProgContext tree = parser.prog();
            interpreter.visit(tree);
        } catch (BombasticSemanticException ex) {
            System.err.println("Semantic error - " +ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error - " +ex.getMessage());
        }
    }
}
