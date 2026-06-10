package li1.plp.imperative1;
import li1.plp.expressions2.expression.Id;
import li1.plp.expressions2.expression.ValorInteiro;
import li1.plp.imperative1.Programa;
import li1.plp.imperative1.command.Comando;
import li1.plp.imperative1.command.ComandoDeclaracao;
import li1.plp.imperative1.command.Write;
import li1.plp.imperative1.declaration.DeclaracaoVariavel;
import li1.plp.imperative1.memory.ContextoCompilacaoImperativa;
import li1.plp.imperative1.memory.ContextoExecucaoImperativa;
import li1.plp.imperative1.memory.ListaValor;

public class Exemplo1 {

    public static void main(String[] args) throws Exception {

        Id a = new Id("a");

        Comando comando =
            new ComandoDeclaracao(
                new DeclaracaoVariavel(a, new ValorInteiro(3)),
                new Write(a)
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