package lf1.plp.functional1;

import java.util.Arrays;

import lf1.plp.expressions2.expression.ExpSoma;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.functional1.declaration.DecFuncao;
import lf1.plp.functional1.declaration.DeclaracaoFuncional;
import lf1.plp.functional1.expression.Aplicacao;
import lf1.plp.functional1.expression.ExpDeclaracao;
import lf1.plp.functional1.util.ValorFuncao;

public class Exemplo1 {

    public static void main(String[] args) {

        try {
            Id f = new Id("f");
            Id x = new Id("x");

            Expressao corpoFuncao = new ExpSoma(
                    x,
                    new ValorInteiro(1)
            );

            ValorFuncao valorFuncao = new ValorFuncao(
                    Arrays.asList(x),
                    corpoFuncao
            );

            DecFuncao declaracaoF = new DecFuncao(
                    f,
                    valorFuncao
            );

            Expressao chamadaF = new Aplicacao(
                    f,
                    Arrays.asList(new ValorInteiro(2))
            );

            Expressao p = new ExpDeclaracao(
                    Arrays.<DeclaracaoFuncional>asList(declaracaoF),
                    chamadaF
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