package li1.plp.imperative1;

import li1.plp.expressions2.expression.ExpEquals;
import li1.plp.expressions2.expression.ExpNot;
import li1.plp.expressions2.expression.ExpSoma;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.imperative1.Programa;
import li1.plp.imperative1.command.Atribuicao;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.SequenciaComando;
import li1.plp.imperative1.command.While;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

public class Exemplo3 {

    public static void main(String[] args) throws Exception {

        Id i = new Id("i");

        Comando whileIncrementa =
            new While(
                new ExpNot(
                    new ExpEquals(i, new ValorInteiro(3))
                ),
                new Atribuicao(
                    i,
                    new ExpSoma(i, new ValorInteiro(1))
                )
            );

        Comando comando =
            new ComandoDeclaracao(
                new DeclaracaoVariavel(i, new ValorInteiro(0)),
                new SequenciaComando(
                    whileIncrementa,
                    new Write(new ValorString("Hello World"))
                )
            );

        Programa programa = new Programa(comando);

        ContextoCompilacaoImperativa ambienteCompilacao =
            new ContextoCompilacaoImperativa(new ListaValor());

        ContextoExecucaoImperativa ambienteExecucao =
            new ContextoExecucaoImperativa(new ListaValor());

        if (programa.checaTipo(ambienteCompilacao)) {
            ListaValor saida = programa.executar(ambienteExecucao);
            System.out.println(saida);
        }
    }
}