package lf1.plp.functional1;

import java.util.Arrays;

import lf1.plp.expressions2.expression.ExpSoma;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.expressions2.expression.ValorString;
import lf1.plp.functional1.declaration.DecFuncao;
import lf1.plp.functional1.declaration.DecVariavel;
import lf1.plp.functional1.declaration.DeclaracaoFuncional;
import lf1.plp.functional1.expression.Aplicacao;
import lf1.plp.functional1.expression.ExpDeclaracao;
import lf1.plp.functional1.util.ValorFuncao;

public class Exemplo3 {

    public static void main(String[] args) {

        try {
            Id y = new Id("y");
            Id x = new Id("x");
            Id z = new Id("z");
            Id f = new Id("f");

            DecVariavel decY3 = new DecVariavel(
                    y,
                    new ValorInteiro(3)
            );

            Expressao corpoF = new ExpSoma(
                    x,
                    y
            );

            ValorFuncao valorFuncaoF = new ValorFuncao(
                    Arrays.asList(x),
                    corpoF
            );

            DecFuncao decF = new DecFuncao(
                    f,
                    valorFuncaoF
            );

            DecVariavel decZ = new DecVariavel(
                    z,
                    new ValorString("abc")
            );

            Expressao chamadaF = new Aplicacao(
                    f,
                    Arrays.asList(new ValorInteiro(3))
            );

            Expressao letZ = new ExpDeclaracao(
                    Arrays.<DeclaracaoFuncional>asList(decZ),
                    chamadaF
            );

            Expressao letF = new ExpDeclaracao(
                    Arrays.<DeclaracaoFuncional>asList(decF),
                    letZ
            );

            Expressao p = new ExpDeclaracao(
                    Arrays.<DeclaracaoFuncional>asList(decY3),
                    letF
            );

            Programa programa = new Programa(p);

            if (programa.checaTipo()) {
                Valor resultado = programa.executar();
                System.out.println(resultado);
            } else {
                System.out.println("Erro de tipo.");
            }

        } catch (Exception e) {
            System.out.println("Erro durante a execucao do programa:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}