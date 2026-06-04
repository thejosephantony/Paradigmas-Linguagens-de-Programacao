package plp.functional2.expression;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional2.util.PartialInstantiatorVisitor;

public class Aplicacao implements Expressao {

	private Expressao func;
	private List<Expressao> argsExpressao;

	public Aplicacao(Expressao f, List<Expressao> expressoes) {
		func = f;
		argsExpressao = expressoes;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 *
	 * @return uma representacao String desta expressao.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(func.toString());
		sb.append(" (");
		sb.append(argsExpressao.toString());
		sb.append(')');
		return sb.toString();
	}
	

	public Valor avaliar(AmbienteExecucao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ValorFuncao funcao = (ValorFuncao)func.avaliar(ambiente); //(ValorFuncao2) ambiente.getValor(func);
		
		Map<Id,Valor> mapIdValor = resolveParametersBindings(ambiente, funcao);

		ambiente.incrementa();

		includeValueBindings(ambiente, mapIdValor);
		
		Expressao expInterna = funcao.getExp();
		Valor vresult;
		
		if (expInterna instanceof ValorFuncao) {
			ValorFuncao temp = (ValorFuncao) expInterna;
			Set variaveisLocais = Collections.unmodifiableSet(new HashSet<Id>(temp.getListaId()));
			vresult = (Valor)
					PartialInstantiatorVisitor.getInstance().visit(temp, ambiente, variaveisLocais);
		} else {
			vresult = expInterna.avaliar(ambiente);			
		}
		ambiente.restaura();
		return vresult;
	}

	protected void includeValueBindings(AmbienteExecucao ambiente, Map<Id,Valor> mapIdValor)
		throws VariavelJaDeclaradaException {
		for(Map.Entry<Id,Valor> mapeamento:mapIdValor.entrySet()){
			Id id = mapeamento.getKey();
			Valor valor = mapeamento.getValue();
			ambiente.map(id, valor);			
		}		
	}

	private Map<Id,Valor> resolveParametersBindings(AmbienteExecucao ambiente, ValorFuncao funcao)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		List<Id> parametrosId = funcao.getListaId();
		List<Expressao> expressoesValorReal = argsExpressao;
		
		Map<Id,Valor> mapIdValor = new HashMap<Id,Valor>();
		
		Iterator<Expressao> iterExpressoesValor = expressoesValorReal.iterator();
		for(Id id:parametrosId){
			Expressao exp = iterExpressoesValor.next();
			Valor valorReal = exp.avaliar(ambiente);
			mapIdValor.put(id, valorReal);			
		}
	
		return mapIdValor;
	}

	/**
	 * Realiza a verificacao de tipos desta expressao.
	 *
	 * @param amb o ambiente de compila��o.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {	
		return true;
		/*
		
		//TODO
		Id t = (Id)func;
		Tipo tipoFuncao = ambiente.get(t);
		
		//return checkArgumentListSize(tipoFuncao) && checkArgumentTypes(ambiente, tipoFuncao) ;
		 * */
	}

	private boolean checkArgumentTypes(
		AmbienteCompilacao ambiente,
		Tipo tipoFuncao)		
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean result = true;
		List<Expressao> expressoesValorReal = argsExpressao;
		for(Expressao valorReal:expressoesValorReal){
			if (!valorReal.checaTipo(ambiente)) {
				result = false;
			}
			Tipo tipoArg = valorReal.getTipo(ambiente);
			if (tipoArg.intersecao(tipoFuncao).eVoid()) {
				result = false;
			}
			tipoFuncao = tipoFuncao.getProx();			
		}
		return result;
	}

	private boolean checkArgumentListSize(Tipo tipoFuncao) {
		boolean res;
		{
				res = true;
				
				List<Expressao> expressoesValorReal = argsExpressao;
		
				Tipo aux = tipoFuncao;
		
				int tamanhoTipo = 0;
				while (aux != null) {
					tamanhoTipo++;
					aux = aux.getProx();
				}
				if ((tamanhoTipo - 1) != expressoesValorReal.size()) {
					res = false;
				}
				
		}	
		return res;
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @param amb o ambiente de compila��o.
	 * @return os tipos possiveis desta expressao.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 * @precondition this.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		//TODO
		Id id = (Id)func;
		Tipo t = ambiente.get(id);
		while (t.getProx() != null) {
			t = t.getProx();
		}
		return t;
	}

	/**
	 * Returns the func.
	 * @return Id
	 */
	public Id getFunc() {
		//TODO
		Id t = (Id)func;
		return t;
//		return (Id)func;
	}
	
	public Expressao getFuncao()
	{
		return func;
	}

	/**
	 * Returns the args.
	 * @return ListaExpressao
	 */
	public List<Expressao> getArgsExpressao() {
		return argsExpressao;
	}

}
