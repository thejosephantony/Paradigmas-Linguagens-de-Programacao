package plp.functional4.util;

import java.util.List;

import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;

public class PadraoTupla implements PadraoAbstrato{
	private List<PadraoAbstrato> padroes;

	public PadraoTupla(List<PadraoAbstrato> padroes)
	{
		this.padroes = padroes;
	}

	public boolean casamento(AmbienteExecucao amb, ValorConcreto val) {
		boolean result = false;
		if(val instanceof ValorTupla)
		{
			ValorTupla v = (ValorTupla) val;
			result = v.valor().size() == padroes.size();
			
			int i = 0;
			while(i < padroes.size() && result)
			{
				result = padroes.get(i).casamento(amb, (ValorConcreto) v.valor().get(i));
				i++;
			}
		}
		return result;
	}

	public void includeBindings(AmbienteExecucao amb, ValorConcreto val) throws VariavelJaDeclaradaException {
		ValorTupla v = (ValorTupla) val;
		int i = 0;
		for(PadraoAbstrato padrao : padroes)
		{
			padrao.includeBindings(amb, (ValorConcreto) v.valor().get(i));
			i++;
		}

	}

	public void includeTypeBindings(AmbienteCompilacao amb) throws VariavelJaDeclaradaException {
		for(PadraoAbstrato padrao : padroes)
		{
			padrao.includeTypeBindings(amb);
		}		
	}
}
