package plp.functional2.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import plp.expressions2.expression.ExpAnd;
import plp.expressions2.expression.ExpConcat;
import plp.expressions2.expression.ExpEquals;
import plp.expressions2.expression.ExpLength;
import plp.expressions2.expression.ExpMenos;
import plp.expressions2.expression.ExpNot;
import plp.expressions2.expression.ExpOr;
import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.ExpSub;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.expression.ValorInteiro;
import plp.expressions2.expression.ValorString;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.declaration.DecVariavel;
import plp.functional1.declaration.DeclaracaoFuncional;
import plp.functional1.expression.IfThenElse;
import plp.functional2.declaration.DecFuncao;
import plp.functional2.expression.Aplicacao;
import plp.functional2.expression.ExpDeclaracao;
import plp.functional2.expression.ValorFuncao;

/**
 * @author Sérgio
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PartialInstantiatorVisitor {
	private Map metodosVisit;
	
	private static PartialInstantiatorVisitor instance = null;

	protected PartialInstantiatorVisitor() {
		metodosVisit = new HashMap();
		Method metodos[] = this.getClass().getMethods();
		for (int i = 0; i < metodos.length; i++) {
			Method method = metodos[i];
			if (method.getName().startsWith("_visit")) {
				metodosVisit.put(method.getName(), method);
			}
		}
	}
	
	public static PartialInstantiatorVisitor getInstance() {
		if (instance == null) {
			instance = new PartialInstantiatorVisitor();			
		}
		return instance;	
	}

	public Expressao visit(
		Expressao exp,
		AmbienteExecucao ambiente,
		Set localVariables) {

		// Class.getName() returns package information as well.
		// This strips off the package information giving us
		// just the class name
		String methodName = exp.getClass().getName();
		methodName =
			"_visit" + methodName.substring(methodName.lastIndexOf('.') + 1);

		Expressao result = null;
		// Now we try to invoke the method visit
		try {
			// Get the method visitFoo(Foo foo)
			Method m = getMethod(methodName);
			// o metodo visit existe
			// Try to invoke visitFoo(Foo foo)
			result =
				(Expressao) m.invoke(
					this,
					new Object[] { exp, ambiente, localVariables });
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			throw new IllegalStateException(
				"Não foi possível executar o método ("
					+ methodName
					+ "). IllegalAccessException");

		} catch (InvocationTargetException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new IllegalStateException(
				"Não foi possível executar o método ("
					+ methodName
					+ "). InvocationTargetException");

		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

			throw new IllegalStateException(
				"O método visit chamado ("
					+ methodName
					+ ") não foi implementado");
		}
		return result;
	}

	private Method getMethod(String name) throws NoSuchMethodException {
		Object method = metodosVisit.get(name);
		if (method == null) {
			throw new NoSuchMethodException(
				"O método '" + name + "' especificado não foi encontrado");
		}
		return (Method) method;
	}
	
	public Expressao _visitAplicacao(Aplicacao expressao, 
			AmbienteExecucao ambiente,
			Set localVariables) {
		List novosValoresReais = new ArrayList(expressao.getArgsExpressao().size());
		Set novasVariaveisLocais = new HashSet(localVariables);
		novasVariaveisLocais.add(expressao.getFunc());
		for (Iterator iter = expressao.getArgsExpressao().iterator();
			iter.hasNext();
			) {
			Expressao argReal = (Expressao) iter.next();
			Expressao novoArg = visit(argReal, ambiente, novasVariaveisLocais);
			novosValoresReais.add(novoArg);
		}
		Aplicacao resultado = new Aplicacao(expressao.getFunc(), novosValoresReais);
		return resultado;
	}

	public Expressao _visitExpAnd(ExpAnd expressao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		
		Expressao esquerda = visit(expressao.getEsq(), ambiente, localVariables);
		Expressao direita = visit(expressao.getDir(), ambiente, localVariables);
		ExpAnd resultado = new ExpAnd(esquerda, direita);
		return resultado;
	}

	public Expressao _visitExpConcat(ExpConcat expressao,			
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao esquerda = visit(expressao.getEsq(), ambiente, localVariables);
		Expressao direita = visit(expressao.getDir(), ambiente, localVariables);
		ExpConcat resultado = new ExpConcat(esquerda, direita);
		return resultado;
	}

	public Expressao _visitExpDeclaracao(ExpDeclaracao expressao,	
			AmbienteExecucao ambiente,
			Set localVariables) {

		// Adicionando as variaveis locais da declaracao funcional
		Set novasVariaveisLocais = new HashSet(localVariables);
		List novaListaDeclaracao = new ArrayList(expressao.getSeqdecFuncional().size());
		for (Iterator iter = expressao.getSeqdecFuncional().iterator();
			iter.hasNext();
			) {
			DeclaracaoFuncional declaracao = (DeclaracaoFuncional) iter.next();
			if (declaracao instanceof DecFuncao) {
				DecFuncao novaDec = visitDecFuncao((DecFuncao)declaracao, ambiente, localVariables);
				novaListaDeclaracao.add(novaDec);
			} else if (declaracao instanceof DecVariavel) {
				DecVariavel novaDec = visitDecVariavel((DecVariavel)declaracao, ambiente, localVariables);
				novaListaDeclaracao.add(novaDec);
			} else {
				throw new IllegalStateException("DeclaracaoFuncional desconhecida em PartialInstantiatorVisitor");
			}
			Id idAtual = declaracao.getID();
			novasVariaveisLocais.add(idAtual);
		}
		
		Expressao novaExpressao = visit(expressao.getExpressao(), ambiente, novasVariaveisLocais);
		ExpDeclaracao resultado = new ExpDeclaracao(novaListaDeclaracao, novaExpressao);
		return resultado;
	}


	private DecFuncao visitDecFuncao(DecFuncao declaracao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		Set novasVariaveisLocais = new HashSet(localVariables);
		novasVariaveisLocais.add(declaracao.getID());
		ValorFuncao novaExpressao = (ValorFuncao) _visitValorFuncao(declaracao.getFuncao(), ambiente, novasVariaveisLocais);
		DecFuncao resultado = new DecFuncao(declaracao.getID(), novaExpressao);
		return resultado;		
	}		
	
	private DecVariavel visitDecVariavel(DecVariavel declaracao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		Set novasVariaveisLocais = new HashSet(localVariables);
		novasVariaveisLocais.add(declaracao.getID());
		Expressao novaExpressao = visit(declaracao.getExpressao(), ambiente, novasVariaveisLocais);
		DecVariavel resultado = new DecVariavel(declaracao.getID(), novaExpressao);
		return resultado;
	}		
	

	public Expressao _visitExpEquals(ExpEquals expressao,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao esquerda = visit(expressao.getEsq(), ambiente, localVariables);
		Expressao direita = visit(expressao.getDir(), ambiente, localVariables);
		ExpEquals resultado = new ExpEquals(esquerda, direita);
		return resultado;
	}
	
	public Expressao _visitExpLength(ExpLength expressao,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		
		Expressao expInterna = visit(expressao.getExp(), ambiente, localVariables);
		ExpLength resultado = new ExpLength(expInterna);
		return resultado;
	}
	
	public Expressao _visitExpMenos(ExpMenos expressao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao expInterna = visit(expressao.getExp(), ambiente, localVariables);
		ExpMenos resultado = new ExpMenos(expInterna);
		return resultado;
	}
		
	public Expressao _visitExpNot(ExpNot expressao,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao expInterna = visit(expressao.getExp(), ambiente, localVariables);
		ExpNot resultado = new ExpNot(expInterna);
		return resultado;
	}

	public Expressao _visitExpOr(ExpOr expressao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao esquerda = visit(expressao.getEsq(), ambiente, localVariables);
		Expressao direita = visit(expressao.getDir(), ambiente, localVariables);
		ExpOr resultado = new ExpOr(esquerda, direita);
		return resultado;
	}

	public Expressao _visitExpSoma(ExpSoma expressao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao esquerda = visit(expressao.getEsq(), ambiente, localVariables);
		Expressao direita = visit(expressao.getDir(), ambiente, localVariables);
		ExpSoma resultado = new ExpSoma(esquerda, direita);
		return resultado;
	}
	
	public Expressao _visitExpSub(ExpSub expressao,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao esquerda = visit(expressao.getEsq(), ambiente, localVariables);
		Expressao direita = visit(expressao.getDir(), ambiente, localVariables);
		ExpSub resultado = new ExpSub(esquerda, direita);
		return resultado;
	}

	public Expressao _visitIfThenElse(IfThenElse expressao,
			AmbienteExecucao ambiente,
			Set localVariables) {
		
		Expressao condicao = visit(expressao.getCondicao(), ambiente, localVariables);
		Expressao then = visit(expressao.getThen(), ambiente, localVariables);
		Expressao elseExpressao = visit(expressao.getElseExpressao(), ambiente, localVariables);
		IfThenElse resultado = new IfThenElse(condicao, then, elseExpressao);
		return resultado;
	}

	public Expressao _visitId(Id thisId,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		Expressao resultado;
		if (localVariables.contains(thisId)) {
			resultado = thisId;	
		} else {
			try {
				resultado = thisId.avaliar(ambiente);
			} catch (VariavelNaoDeclaradaException e) {
				resultado = thisId;
			}
		}
		return resultado;
	}
	
	public Expressao _visitValorInteiro(ValorInteiro valor,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		return valor;				
	}

	public Expressao _visitValorString(ValorString valor,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		return valor;				
	}

	public Expressao _visitValorBooleano(ValorBooleano valor,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		return valor;				
	}

	public Expressao _visitValorFuncao(ValorFuncao valor,	
			AmbienteExecucao ambiente,
			Set localVariables) {
		Set novasVariaveisLocais = new HashSet(localVariables);
		novasVariaveisLocais.addAll(valor.getListaId());
		Expressao novaExpressao = visit(valor.getExp(), ambiente, novasVariaveisLocais);
		ValorFuncao resultado = new ValorFuncao(valor.getListaId(), novaExpressao);
		return resultado;				
	}
	
}
