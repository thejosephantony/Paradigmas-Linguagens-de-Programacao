package plp.functional4.expression;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ExpressaoComplexa;
import plp.expressions2.expression.Valor;
import plp.expressions2.expression.ValorBooleano;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

public class IfThenElse implements Expressao {
	Expressao condicao;
	ExpressaoComplexa then;
	ExpressaoComplexa elseExpressao;

	public IfThenElse(
		Expressao teste,
		ExpressaoComplexa thenExpressao,
		ExpressaoComplexa elseExpressao) {
		this.condicao = teste;
		this.then = thenExpressao;
		this.elseExpressao = elseExpressao;
	}

	public Valor avaliar(AmbienteExecucao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		if (((ValorBooleano) condicao.avaliar(ambiente)).valor())
			return then.avaliar(ambiente);
		else
			return elseExpressao.avaliar(ambiente);
	}

	/**
	 * Retorna texto representando um objeto desta classe.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("if (");
		sb.append(condicao.toString());
		sb.append(") then (");
		sb.append(then.toString());
		sb.append(") else (");
		sb.append(elseExpressao.toString());
		sb.append(")");
		return sb.toString();
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
	public boolean checaTipo(AmbienteCompilacao amb)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		boolean result = true;
		if (!condicao.checaTipo(amb) || !condicao.getTipo(amb).eBooleano()) {
			result = false;
		}else if(!then.checaTipo(amb) || !elseExpressao.checaTipo(amb)){
			result = false;
		} 		
		else if (then.getTipo(amb).intersecao(elseExpressao.getTipo(amb)).eVoid()) {
			result = false;
		}
		return result;
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
	 */
	public Tipo getTipo(AmbienteCompilacao amb)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return then.getTipo(amb).intersecao(elseExpressao.getTipo(amb));
	}

	/**
	 * Returns the condicao.
	 * @return Expressao
	 */
	public Expressao getCondicao() {
		return condicao;
	}

	/**
	 * Returns the then.
	 * @return Expressao
	 */
	public ExpressaoComplexa getThen() {
		return then;
	}

	/**
	 * Returns the elseExpressao.
	 * @return Expressao
	 */
	public ExpressaoComplexa getElseExpressao() {
		return elseExpressao;
	}

}

