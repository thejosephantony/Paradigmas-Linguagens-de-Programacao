package li1.plp.imperative1;

import li1.plp.expressions2.expression.ExpEquals;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.expressions2.expression.ValorString;
import li1.plp.imperative1.Programa;
import li1.plp.imperative1.command.Atribuicao;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.IfThenElse;
import li1.plp.imperative1.command.SequenciaComando;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoComposta;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

public class Exemplo4 {

    public static void main(String[] args) throws Exception {

        Id n = new Id("n");
        Id m = new Id("m");

        Comando inicializaN =
            new Atribuicao(n, new ValorInteiro(2));

        Comando inicializaM =
            new Atribuicao(m, new ValorInteiro(3));

        Comando condicional =
            new IfThenElse(
                new ExpEquals(m, n),
                new Write(new ValorString("valores de entrada iguais")),
                new Write(new ValorString("valores de entrada diferentes"))
            );

        Comando comando =
            new ComandoDeclaracao(
                new DeclaracaoComposta(
                    new DeclaracaoVariavel(n, new ValorInteiro(0)),
                    new DeclaracaoVariavel(m, new ValorInteiro(0))
                ),
                new SequenciaComando(
                    inicializaN,
                    new SequenciaComando(
                        inicializaM,
                        condicional
                    )
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