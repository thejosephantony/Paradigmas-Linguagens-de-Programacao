package plp.functional4.expression;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class SeqExpProc implements ExpProcesso {
	private ExpProcesso exp1, exp2;
	
	public SeqExpProc(ExpProcesso exp1, ExpProcesso exp2){
		this.exp1 = exp1;
		this.exp2 = exp2;
	}
	
	public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		exp1.avaliar(amb);
		return exp2.avaliar(amb);
	}

	public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return exp1.checaTipo(amb) && exp2.checaTipo(amb);
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return exp2.getTipo(amb);
	}

}
