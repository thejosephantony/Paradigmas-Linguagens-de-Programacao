package li1.plp.imperative1;


import li1.plp.expressions2.expression.ExpSoma;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.imperative1.Programa;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.SequenciaComando;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoComposta;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

public class Exemplo2 {

    public static void main(String[] args) throws Exception {

        Id a = new Id("a");
        Id b = new Id("b");

        Comando blocoInterno =
            new ComandoDeclaracao(
                new DeclaracaoComposta(
                    new DeclaracaoVariavel(a, new ValorInteiro(2)),
                    new DeclaracaoVariavel(b, new ValorInteiro(5))
                ),
                new SequenciaComando(
                    new Write(a),
                    new Write(new ExpSoma(b, a))
                )
            );

        Comando comando =
            new ComandoDeclaracao(
                new DeclaracaoVariavel(a, new ValorInteiro(3)),
                new SequenciaComando(
                    new Write(a),
                    new SequenciaComando(
                        blocoInterno,
                        new Write(a)
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