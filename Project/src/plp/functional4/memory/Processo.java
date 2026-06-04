package plp.functional4.memory;

import java.util.ArrayList;
import java.util.List;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.ContextoCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional4.expression.Aplicacao;

public class Processo extends Thread{
	private AmbientePrograma manager;
	private List<Expressao> messages;
	private Aplicacao aplic;
	private AmbienteExecucao amb;
	
	public Processo(AmbientePrograma manager) {
		this.manager = manager;
		messages = new ArrayList<Expressao>();
	}
		
	public void run()
	{
		try {
			avaliar(aplic);
		} catch (VariavelNaoDeclaradaException e) {
			e.printStackTrace();
		} catch (VariavelJaDeclaradaException e) {
			e.printStackTrace();
		}
		manager.killProc(this.toString());
	}
	
	public Valor avaliar(Expressao exp) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException
	{		
		return exp.avaliar(amb);
	}
		
	public boolean checaTipo(Expressao exp) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException
	{
		AmbienteCompilacao ambComp = new ContextoCompilacao();
		return exp.checaTipo(ambComp);
	}

	public void readMessage(int pos){
		messages.remove(pos);
	}
	
	//quem chamar ele tem que ta no bloco synchronized
	public List<Expressao> getMessages(){
		return this.messages;
	}
	
	//quem chamar ele tem que ta num bloco synchronized
	public void includeMessage(Valor msg)
	{
		messages.add(msg);
	}
	
	public void sendMessage(String id, Valor msg)
	{
		manager.sendMsg(id, msg);
	}
	
	public String createProcess(Expressao func, List<Expressao> args, AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException{
		return manager.createProc(func, args, amb);
	}
	
	public void setAmb(AmbienteExecucao novoAmb)
	{
		this.amb = novoAmb;
	}
	
	public void criarAplicacao(Expressao func, List<Expressao> args) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		aplic = new Aplicacao(func, args);
	}
}
