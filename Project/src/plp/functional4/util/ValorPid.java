package plp.functional4.util;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ValorPid extends ValorConcreto<String>{

	public ValorPid(String valor) {
		super(valor);
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return Tipo.TIPO_PID;
	}
}
