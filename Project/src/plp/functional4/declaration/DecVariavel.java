package plp.functional4.declaration;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.ExpressaoComplexa;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional4.expression.ExpressaoComposta;

public class DecVariavel implements DeclaracaoFuncional {
	private Id id;
	private ExpressaoComplexa expressao;

	public DecVariavel(Id idArg, ExpressaoComplexa expressaoArg) {
		id = idArg;
		expressao = expressaoArg;
	}
	public int getAridade() {
		return 0;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 *
	 * @return uma representacao String desta expressao.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("var ");
		sb.append(id.toString());
		sb.append(" = ");
		sb.append(expressao.toString());
		return sb.toString();
	}

	public ExpressaoComplexa getExpressao() {
		return expressao;
	}

	public Id getID() {
		return id;
	}

	/**
	 * Retorna os tipos possiveis desta declara��o.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *          e tipos.
	 * @return os tipos possiveis desta declara��o.
	 * @exception VariavelNaoDeclaradaException se houver uma vari&aacute;vel
	 *          n&atilde;o declarada no ambiente.
	 * @exception VariavelJaDeclaradaException se houver uma mesma
	 *           vari&aacute;vel declarada duas vezes no mesmo bloco do
	 *           ambiente.
	 * @precondition this.checaTipo(amb);
	 */
	public Tipo getTipo(AmbienteCompilacao amb)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		return expressao.getTipo(amb);
	}

	/**
	 * Realiza a verificacao de tipos desta declara��o.
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
		return expressao.checaTipo(ambiente);
	}

}
