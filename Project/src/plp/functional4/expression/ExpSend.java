package plp.functional4.expression;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional4.memory.AmbienteExecucaoProcesso;

public class ExpSend implements ExpProcesso{
	private List<Id> ids;
	private Expressao exp;
	
	public ExpSend(List<Id> ids, Expressao exp)
	{
		this.ids = ids;
		this.exp = exp;
	}
	
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		Valor v = exp.avaliar(amb);
		AmbienteExecucaoProcesso ambProc = (AmbienteExecucaoProcesso) amb;
		for(Id id : ids)
		{
			ambProc.sendMessage(id.avaliar(amb).toString(), v);
		}
		return v;
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean tipo = exp.checaTipo(amb);
		int i = 0;
		while(tipo && i < ids.size())
		{
			Id id = ids.get(i);
			tipo = tipo && id.checaTipo(amb);
			i++;
		}
		return tipo;
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return exp.getTipo(amb);
	}

}
