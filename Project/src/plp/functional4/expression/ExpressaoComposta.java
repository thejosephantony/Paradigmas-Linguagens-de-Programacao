package plp.functional4.expression;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ExpressaoComplexa;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpressaoComposta implements ExpressaoComplexa{
	ExpProcesso before;
	ExpProcesso after;
	Expressao exp;

	public ExpressaoComposta(ExpProcesso before, ExpProcesso after, Expressao exp){
		this.before = before;
		this.after = after;
		this.exp = exp;
	}

	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Valor retorno = null;
		if (before != null){
			retorno = before.avaliar(amb);
		}
		if(exp != null){
			retorno = exp.avaliar(amb);
		}
		if(after != null)
		{
			retorno = after.avaliar(amb);
		}
		return retorno;
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean retorno = before != null || exp != null || after != null;
		if(retorno)
		{
			if (before != null){
				retorno = before.checaTipo(amb);
			}
			if(exp != null){
				retorno = retorno && exp.checaTipo(amb);
			}
			if(after != null)
			{
				retorno = retorno && after.checaTipo(amb);
			}
		}
		return retorno;
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Tipo retorno = null;
		if(after != null)
		{
			retorno = after.getTipo(amb);
		}
		else if(exp != null){
			retorno = exp.getTipo(amb);
		}
		else if (before != null){
			retorno = before.getTipo(amb);
		}
		return retorno;
		
	}
}
