package plp.functional4;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.ContextoCompilacao;
import plp.expressions2.memory.ContextoExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

import plp.functional4.memory.*;
public class Programa {

	private Expressao exp;
	private AmbientePrograma amb;

	public Programa(Expressao exp) {
		this.exp = exp;		
		this.amb = new ContextoPrograma();
	}

	public Valor executar()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		return amb.getMainProc().avaliar(exp);
	}

	public boolean checaTipo()
		throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException {
		return amb.getMainProc().checaTipo(exp);
	}

	public Expressao getExpressao() {
		return exp;
	}

}
