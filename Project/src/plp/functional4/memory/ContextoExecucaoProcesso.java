package plp.functional4.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.Contexto;
import plp.expressions2.memory.ContextoExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.memory.ContextoExecucaoFuncional;
import plp.functional1.util.ValorFuncao;

public class ContextoExecucaoProcesso extends ContextoExecucaoFuncional implements 
AmbienteExecucaoProcesso{
	private Processo proc;

	public ContextoExecucaoProcesso(Processo proc)
	{
		super();
		this.proc = proc;
	}
	
	public void sendMessage(String id, Valor msg) {
		proc.sendMessage(id, msg);
		
	}

	public String createProcess(Expressao func, List<Expressao> args) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return proc.createProcess(func, args, this);
	}
	
	public Processo getProcesso()
	{
		return proc;
	}
	
	public void setProcesso(Processo proc)
	{
		this.proc = proc;
	}
	
	public ContextoExecucaoProcesso clone(Processo processo)
	{
		ContextoExecucaoProcesso ret = new ContextoExecucaoProcesso(processo);
		ret.setPilhaFuncao((Stack<HashMap<Id, ValorFuncao>>) this.getPilhaFuncao().clone());
		ret.setPilhaIdValor(this.getPilhaIdValor().clone());
		ret.setPilhaIdValorFunc(this.getPilhaIdValorFunc().clone());
		return ret;
			//	((Stack<HashMap<Id, ValorFuncao>>) pilhaFuncao.clone(), (ContextoExecucao) ((Contexto<Valor>) (((Contexto<Valor>)pilhaIdValor).clone())), pilhaIdValorFunc.clone());
	}
}
