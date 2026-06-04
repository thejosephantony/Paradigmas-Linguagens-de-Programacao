package plp.functional4.memory;

import java.util.List;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.memory.AmbienteExecucaoFuncional;

public interface AmbienteExecucaoProcesso extends AmbienteExecucaoFuncional {
	
	public void sendMessage(String id, Valor msg);
	
	public String createProcess(Expressao func, List<Expressao> args) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException;
				
	public Processo getProcesso();
	
	public AmbienteExecucao clone(Processo proc);
}
