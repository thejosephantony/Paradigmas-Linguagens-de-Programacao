package plp.functional4.memory;

import java.util.List;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public interface AmbientePrograma {
	public void sendMsg(String id, Valor val);
	public String createProc(Expressao func, List<Expressao> params, AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;
	public void killProc(String id);
	public Processo getMainProc();
}
