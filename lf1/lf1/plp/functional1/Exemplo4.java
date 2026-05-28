package lf1.plp.functional1;

import java.util.Arrays;

import lf1.plp.expressions2.expression.ExpEquals;
import lf1.plp.expressions2.expression.ExpSoma;
import lf1.plp.expressions2.expression.ExpSub;
import lf1.plp.expressions2.expression.Expressao;
import lf1.plp.expressions2.expression.Id;
import lf1.plp.expressions2.expression.Valor;
import lf1.plp.expressions2.expression.ValorInteiro;
import lf1.plp.functional1.declaration.DecFuncao;
import lf1.plp.functional1.declaration.DeclaracaoFuncional;
import lf1.plp.functional1.expression.Aplicacao;
import lf1.plp.functional1.expression.ExpDeclaracao;
import lf1.plp.functional1.expression.IfThenElse;
import lf1.plp.functional1.util.ValorFuncao;

public class Exemplo4 {

    public static void main(String[] args) {

        try {
            Id mult = new Id("mult");
            Id x = new Id("x");
            Id y = new Id("y");

            Expressao condicao = new ExpEquals(
                    x,
                    new ValorInteiro(0)
            );

            Expressao casoBase = new ValorInteiro(0);

            Expressao chamadaRecursiva = new Aplicacao(
                    mult,
                    Arrays.asList(
                            new ExpSub(x, new ValorInteiro(1)),
                            y
                    )
            );

            Expressao casoRecursivo = new ExpSoma(
                    y,
                    chamadaRecursiva
            );

            Expressao corpoMult = new IfThenElse(
                    condicao,
                    casoBase,
                    casoRecursivo
            );

            ValorFuncao valorFuncaoMult = new ValorFuncao(
                    Arrays.asList(x, y),
                    corpoMult
            );

            DecFuncao decMult = new DecFuncao(
                    mult,
                    valorFuncaoMult
            );

            Expressao chamadaMult = new Aplicacao(
                    mult,
                    Arrays.asList(
                            new ValorInteiro(3),
                            new ValorInteiro(4)
                    )
            );

            Expressao p = new ExpDeclaracao(
                    Arrays.<DeclaracaoFuncional>asList(decMult),
                    chamadaMult
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