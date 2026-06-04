package plp.functional2.declaration;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.declaration.DeclaracaoFuncional;
import plp.functional2.expression.ValorFuncao;

/**
 * @author Sérgio
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DecFuncao implements DeclaracaoFuncional {
	private Id id;
	private ValorFuncao valorFuncao;

	public DecFuncao(Id idFun, ValorFuncao valorFuncao) {
		this.id = idFun;
		this.valorFuncao = valorFuncao;
	}

	/**
	 * Retorna uma representacao String desta expressao. Util para depuracao.
	 *
	 * @return uma representacao String desta expressao.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("fun ");
		sb.append(id.toString());
		sb.append(" (");
		List<Id> listaId = valorFuncao.getListaId();
		if (listaId != null) {
			for(Id id:listaId){
				sb.append(id.toString());
					sb.append(", ");
			}
		}
		sb.append(") = ");
		sb.append(valorFuncao.getExp().toString());
		return sb.toString();
	}

	public Id getID() {
		return id;
	}

	public Expressao getExpressao() {
		return valorFuncao.getExp();
	}

	public ValorFuncao getFuncao() {
		return valorFuncao;
	}

	/**
	 * Retorna a aridade da funcao declarada. Variaveis tem aridade 0.
	 *
	 * @return a aridade da funcao declarada.
	 */
	public int getAridade() {
		return valorFuncao.getAridade();
	}

	/**
	 * Realiza a verificacao de tipos desta declaração.
	 *
	 * @param amb o ambiente de compilação.
	 * @return <code>true</code> se os tipos da expressao sao validos;
	 *          <code>false</code> caso contrario.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          nao declarado no ambiente.
	 * @exception VariavelNaoDeclaradaException se existir um identificador
	 *          declarado mais de uma vez no mesmo bloco do ambiente.
	 */
	public boolean checaTipo(AmbienteCompilacao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		ambiente.incrementa();
		Tipo tipo = new Tipo();
		for (int i = getAridade() - 1; i >= 0; i--) {
			tipo = new Tipo(tipo);
		}
		ambiente.map(id, tipo);
		boolean result = valorFuncao.checaTipo(ambiente);
		ambiente.restaura();
		return result;
	}

	/**
	 * Retorna os tipos possiveis da função declarada.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *          e tipos.
	 * @return os tipos possiveis desta declaração.
	 * @exception VariavelNaoDeclaradaException se houver uma vari&aacute;vel
	 *          n&atilde;o declarada no ambiente.
	 * @exception VariavelJaDeclaradaException se houver uma mesma
	 *           vari&aacute;vel declarada duas vezes no mesmo bloco do
	 *           ambiente.
	 * @precondition this.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao amb)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		amb.incrementa();
		Tipo tipo = new Tipo();
		for (int i = getAridade() - 1; i >= 0; i--) {
			tipo = new Tipo(tipo);
		}
		amb.map(id, tipo);
		Tipo result = valorFuncao.getTipo(amb);
		amb.restaura();
		return result;
	}
}
