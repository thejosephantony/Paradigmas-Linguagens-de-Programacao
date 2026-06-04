package plp.functional2.expression;

import java.util.List;

import plp.expressions1.util.Tipo;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.Valor;
import plp.expressions2.memory.AmbienteCompilacao;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;

/**
 * @author Sérgio
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ValorFuncao implements ValorAbstrato {

	private List<Id> argsId;

	private Expressao exp;

	public ValorFuncao(List<Id> argsId, Expressao exp) {
		this.argsId = argsId;
		this.exp = exp;
	}

	public List<Id> getListaId() {
		return argsId;
	}

	public Expressao getExp() {
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
		Tipo t = getTipo(ambiente);
		
		for(Id id:this.argsId){
			ambiente.map(id, new Tipo(t.get()));
			t = t.getProx();			
		}
		ambiente.restaura();
		return true;
	}

	/**
	 * Retorna os tipos possiveis desta função.
	 *
	 * @param amb o ambiente que contem o mapeamento entre identificadores
	 *          e tipos.
	 * @return os tipos possiveis desta declaração.
	 * @exception VariavelNaoDeclaradaException se houver uma vari&aacute;vel
	 *          n&atilde;o declarada no ambiente.
	 * @exception VariavelJaDeclaradaException se houver uma mesma
	 *           vari&aacute;vel declarada duas vezes no mesmo bloco do
	 *           ambiente.
	 * @precondition exp.checaTipo();
	 */
	public Tipo getTipo(AmbienteCompilacao ambiente)
		throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
			
			throw new UnsupportedOperationException("As expressoes da Linguagem " +
					"funcional 2 nao possuem checagem de tipo implementada");
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
