package plp.functional4.util;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.ExpressaoComplexa;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional2.expression.ValorAbstrato;

/**
 * @author S�rgio
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ValorFuncao implements ValorAbstrato {

	private List<Id> argsId;

	private ExpressaoComplexa exp;

	public ValorFuncao(List<Id> argsId, ExpressaoComplexa exp) {
		this.argsId = argsId;
		this.exp = exp;
	}

	public List<Id> getListaId() {
		return argsId;
	}

	public ExpressaoComplexa getExp() {
		return exp;
	}

	/**
	 * Retorna a aridade desta funcao.
	 *
	 * @return a aridade desta funcao.
	 */
	public int getAridade() {
		return argsId.size();
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
		/*
		ambiente.incrementa();
		Tipo t = getTipo(ambiente);
		boolean result = true;
		int i = 0;
		while(i < argsId.size() && result){
			Id id = argsId.get(i);
			ambiente.map(id, new Tipo(t.get()));//problema é aqui?
			result = id.checaTipo(ambiente);
			t = t.getProx();
			i++;
		}
		result = result && exp.checaTipo(ambiente);
		ambiente.restaura();
		return result;
		*/
		return true;
	}

	/**
	 * Retorna os tipos possiveis desta fun��o.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *          e tipos.
	 * @return os tipos possiveis desta declara��o.
	 * @exception VariavelNaoDeclaradaException se houver uma vari&aacute;vel
	 *          n&atilde;o declarada no ambiente.
	 * @exception VariavelJaDeclaradaException se houver uma mesma
	 *           vari&aacute;vel declarada duas vezes no mesmo bloco do
	 *           ambiente.
	 * @precondition exp.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
			throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
		/*
		ambiente.incrementa();
		for(Id argId : argsId)
		{
			ambiente.map(argId, new Tipo()); //e se for funcao aqui?
		}
		Tipo t = exp.getTipo(ambiente);
		for(int i = argsId.size() - 1; i >= 0; i--)
		{
			t = new Tipo(argsId.get(i).getTipo(ambiente).get(), t);	
		}
		ambiente.restaura();
		*/
		Tipo t = new Tipo();
		return t;
	}

	public Valor avaliar(AmbienteExecucao ambiente) {
		return this;	
	}	

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("fn ");

		List<Id> listaId = getListaId();

		if (listaId != null) {
			for (Id id : listaId) {
				sb.append(id.toString() + " ");
			}
		}
		sb.append(". ");
		sb.append(exp.toString());
		return sb.toString();
	}

}
