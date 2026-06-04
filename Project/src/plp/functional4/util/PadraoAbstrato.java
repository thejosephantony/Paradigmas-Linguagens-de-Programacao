package plp.functional4.util;

import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;

public interface PadraoAbstrato {
	public boolean casamento(AmbienteExecucao amb, ValorConcreto val);

	public void includeBindings(AmbienteExecucao amb, ValorConcreto val) throws VariavelJaDeclaradaException;
	public void includeTypeBindings(AmbienteCompilacao amb) throws VariavelJaDeclaradaException;
}
