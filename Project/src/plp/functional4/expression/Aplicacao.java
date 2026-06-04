package plp.functional4.expression;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ExpressaoComplexa;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional2.util.PartialInstantiatorVisitor;
import plp.functional4.util.ValorFuncao;

public class Aplicacao extends plp.functional2.expression.Aplicacao implements Expressao {
	public Aplicacao(Expressao f, List<Expressao> expressoes) {
		super(f, expressoes);
	}

	
	@Override
	public Valor avaliar(AmbienteExecucao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		
		ValorFuncao funcao = (ValorFuncao)getFuncao().avaliar(ambiente); //(ValorFuncao2) ambiente.getValor(func);
		
		Map<Id,Valor> mapIdValor = resolveParametersBindings(ambiente, funcao);

		ambiente.incrementa();

		includeValueBindings(ambiente, mapIdValor);
		
		ExpressaoComplexa expInterna = funcao.getExp();
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
	
	private Map<Id,Valor> resolveParametersBindings(AmbienteExecucao ambiente, ValorFuncao funcao)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		List<Id> parametrosId = funcao.getListaId();
		List<Expressao> expressoesValorReal = getArgsExpressao();
		
		Map<Id,Valor> mapIdValor = new HashMap<Id,Valor>();
		
		Iterator<Expressao> iterExpressoesValor = expressoesValorReal.iterator();
		for(Id id:parametrosId){
			Expressao exp = iterExpressoesValor.next();
			Valor valorReal = exp.avaliar(ambiente);
			mapIdValor.put(id, valorReal);			
		}
	
		return mapIdValor;
	}

}
