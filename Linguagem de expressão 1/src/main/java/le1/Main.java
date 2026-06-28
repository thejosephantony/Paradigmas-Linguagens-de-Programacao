package le1;

import le1.plp.expressions1.Programa;
import le1.plp.expressions1.expression.ExpConcat;
import le1.plp.expressions1.expression.ExpLength;
import le1.plp.expressions1.expression.ExpSoma;
import le1.plp.expressions1.expression.Expressao;
import le1.plp.expressions1.expression.ValorInteiro;
import le1.plp.expressions1.expression.ValorString;

public class Main {
    public static void main(String[] args) {
        // Exemplo 1: length("abc") + 3 => 6
        Expressao exp1 = new ExpSoma(
                new ExpLength(new ValorString("abc")),
                new ValorInteiro(3)
        );
        executar("length(\"abc\") + 3", exp1);

        // Exemplo 2: "curso" ++ " de " ++ "paradigmas" => "curso de paradigmas"
        Expressao exp2 = new ExpConcat(
                new ExpConcat(new ValorString("curso"), new ValorString(" de ")),
                new ValorString("paradigmas")
        );
        executar("\"curso\" ++ \" de \" ++ \"paradigmas\"", exp2);
    }

    private static void executar(String descricao, Expressao expressao) {
        Programa programa = new Programa(expressao);
        System.out.println("Expressao: " + descricao);

        if (programa.checaTipo()) {
            System.out.print("Resultado: ");
            programa.executar();
        } else {
            System.out.println("Erro de tipo.");
        }

        System.out.println();
    }
}
