package plp.functional1.memory;

import plp.expressions2.expression.Id;
import plp.expressions2.memory.AmbienteExecucao;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.util.ValorFuncao;

public interface AmbienteExecucaoFuncional extends AmbienteExecucao {

	/**
	 * Mapeia um identificador em uma funcao.
	 *
	 * @param idArg o identificador
	 * @param funcao a funcao.
	 * @exception VariavelJaDeclaradaException se o id ja' estiver declarado.
	 */
	public void mapFuncao(Id idArg, ValorFuncao funcao)
		throws VariavelJaDeclaradaException;

	/**
	 * Retorna uma funcao.
	 *
	 * @param idArg o identificador que mapeia a funcao
	 * @param funcao a funcao.
	 * @exception VariavelNaoDeclaradaException se o id nao estiver declarado.
	 */
	public ValorFuncao getFuncao(Id idArg)
		throws VariavelNaoDeclaradaException;
	
	

}
