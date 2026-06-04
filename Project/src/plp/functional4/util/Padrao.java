package plp.functional4.util;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;

public class Padrao implements PadraoAbstrato {
	private Expressao exp;
	
	public Padrao(Expressao exp)
	{
		this.exp = exp;		
	}
	
	public boolean casamento(AmbienteExecucao amb, ValorConcreto val){
		boolean retorno = false;
		if(exp instanceof Id)
		{
			retorno = true;
		}else if(exp instanceof ValorConcreto)
		{
			ValorConcreto v = (ValorConcreto) exp;
			retorno = v.valor().equals(val.valor());
		}
		return retorno;
	}

	public void includeBindings(AmbienteExecucao amb, ValorConcreto val) throws VariavelJaDeclaradaException {
		if(exp instanceof Id)
		{
			Id id = (Id) exp;
			amb.map(id, val);
		}
	}

	public void includeTypeBindings(AmbienteCompilacao amb) throws VariavelJaDeclaradaException {
		if(exp instanceof Id)
		{
			Id id = (Id) exp;
			amb.map(id, new Tipo());
		}
	}
}