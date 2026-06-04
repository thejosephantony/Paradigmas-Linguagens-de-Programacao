package plp.functional4.expression;

import java.util.ArrayList;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional4.util.ValorTupla;

public class ExpTupla implements Expressao{
	private List<Expressao> elements;
	
	public ExpTupla(List<Expressao> elems)
	{
		elements = elems;
	}
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		List<Valor> valores = new ArrayList<Valor>();
		for(Expressao exp : elements)
		{
			Valor v = exp.avaliar(amb);
			valores.add(v);
		}
		return new ValorTupla(valores);
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean result = elements.size() > 0;
		int i = 0;
		while(i < elements.size() && result)
		{
			Expressao exp = elements.get(i);
			result = result && exp.checaTipo(amb);
			i++;
		}
		return result;
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return Tipo.TIPO_TUPLA;
	}

}
