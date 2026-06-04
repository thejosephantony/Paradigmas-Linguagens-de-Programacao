package plp.functional4.expression;

import java.util.ArrayList;
import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorString;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional4.memory.AmbienteExecucaoProcesso;
import plp.functional4.util.ValorPid;

public class ExpSpawn implements ExpProcesso{
	private Expressao func;
	private List<Expressao> args;
	
	public ExpSpawn(Expressao func, List<Expressao> args)
	{
		this.func = func;
		this.args = args;
	}
	
	public Valor avaliar(AmbienteExecucao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		AmbienteExecucaoProcesso ambProc = (AmbienteExecucaoProcesso) amb;
		List<Expressao> args_spawn = new ArrayList<Expressao>();
		for(Expressao arg : args)
		{
			args_spawn.add(arg.avaliar(amb));
		}
		String id = ambProc.createProcess(func, args_spawn);
		return new ValorPid(id);
	}

	public boolean checaTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean tipoOk = func.checaTipo(amb);

		Aplicacao a = new Aplicacao(func,args);
		return tipoOk && a.checaTipo(amb);
	}

	public Tipo getTipo(AmbienteCompilacao amb)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return Tipo.TIPO_PID;
	}


}
