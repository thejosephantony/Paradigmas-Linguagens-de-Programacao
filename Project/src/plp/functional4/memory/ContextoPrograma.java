package plp.functional4.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ContextoPrograma implements AmbientePrograma{
	private Map<String,Processo> processes;
	private Processo mainProc;

	public ContextoPrograma() {
		processes = new HashMap<String,Processo>();
		mainProc = new Processo(this);
		mainProc.setAmb(new ContextoExecucaoProcesso(mainProc));
		processes.put(mainProc.toString(), mainProc);
	}

	public synchronized void sendMsg(String id, Valor val){
		Processo proc = processes.get(id);
		if(proc != null){
			synchronized(proc){
				proc.includeMessage(val);
				proc.notify();
			}
		}
	}

	public synchronized String createProc(Expressao func, List<Expressao> params, AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException
	{
		Processo proc = new Processo(this);
		processes.put(proc.toString(), proc);
		AmbienteExecucao novoAmb = (AmbienteExecucaoProcesso) ((AmbienteExecucaoProcesso) amb).clone(proc);
		proc.setAmb(novoAmb);
		proc.criarAplicacao(func, params);
		proc.start();
		return proc.toString();
	}

	
	public synchronized void killProc(String id)
	{
		processes.remove(id);
	}

	public Processo getMainProc()
	{
		return mainProc;
	}
}
