package plp.functional4.expression;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional4.memory.AmbienteExecucaoProcesso;
import plp.functional4.memory.Processo;
import plp.functional4.util.Clausula;

public class ExpReceive implements ExpProcesso{

	private List<Clausula> clausulas;
	
	public ExpReceive(List<Clausula> clausulas){
		this.clausulas = clausulas;
	}
	
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		AmbienteExecucaoProcesso ambProc = (AmbienteExecucaoProcesso) amb;
		Processo proc = ambProc.getProcesso();
		Valor result = null;
		boolean casou = false;
		ValorConcreto val = null;
		Clausula claus = null;
		synchronized(proc){
			try {
				List<Expressao> messages = proc.getMessages();
				int i = 0;
				int j = 0;
				
				while(i < messages.size() && !casou)
				{
					j = 0;
					while(j < clausulas.size() && !casou)
					{
						val = (ValorConcreto) messages.get(i);
						claus = clausulas.get(j);
						casou = claus.casamentoPadrao(ambProc, val);
						j++;
					}
					i++;
				}
				if(!casou){
					proc.wait();
				}else{
					proc.readMessage(i - 1);
				}
				
			} catch (InterruptedException e) {
			}			
		}
		if(!casou)
		{
			result = avaliar(ambProc);
		}else{
			result = claus.executarCorpo(ambProc, val);
		}		
		return result;
	}
	
	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		int i = 0;
		boolean result = true;
		while(i < clausulas.size() && result)
		{
			result = clausulas.get(i).checaTipo(amb);
			i++;
		}
		return result;
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return new Tipo();
	}

}
