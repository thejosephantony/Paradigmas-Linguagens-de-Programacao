package plp.functional4.util;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ValorTupla extends ValorConcreto<List<Valor>>{

	public ValorTupla(List<Valor> valor) {
		super(valor);
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return Tipo.TIPO_TUPLA;
	}
	
	public String toString(){
		String result = "{";
		for(Valor val : valor())
		{
			result += val.toString();
			result += ", ";
		}
		if(result.length() > 2)
		{
			result = result.substring(0, result.length() - 2);
		}
		result += "}";
		return result;
	}

}
