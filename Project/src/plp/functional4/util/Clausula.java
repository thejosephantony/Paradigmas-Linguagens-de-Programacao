package plp.functional4.util;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ExpressaoComplexa;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorConcreto;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class Clausula {
	
	private PadraoAbstrato pattern;
	private ExpressaoComplexa exp;
	
	public Clausula(PadraoAbstrato patt, ExpressaoComplexa exp)
	{
		this.pattern = patt;
		this.exp = exp;
	}
		
	public boolean casamentoPadrao(AmbienteExecucao amb, ValorConcreto val)
	{
		return pattern.casamento(amb, val);
	}
	
	public Valor executarCorpo(AmbienteExecucao amb, ValorConcreto val) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException
	{
		amb.incrementa();
		pattern.includeBindings(amb, val);
		Valor result = exp.avaliar(amb);
		amb.restaura();
		return result;
	}
	
	public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException
	{
		amb.incrementa();
		pattern.includeTypeBindings(amb);
		Tipo result = exp.getTipo(amb);
		amb.restaura();
		return result;
	}
	
	public boolean checaTipo(AmbienteCompilacao amb) throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException
	{
		amb.incrementa();
		pattern.includeTypeBindings(amb);
		boolean result = exp.checaTipo(amb);
		amb.restaura();
		return result;
	}
}
