package plp.expressions1.expression;

import plp.expressions1.util.Tipo;

/**
 * Este valor primitivo encapsula um String.
 */
public class ValorString extends ValorConcreto<String>{

 	public ValorString(String valor) {
		super(valor);
	}

	/**
	 * Retorna os tipos possiveis desta expressao.
	 *
	 * @return os tipos possiveis desta expressao.
	 */
	public Tipo getTipo() {
		return Tipo.TIPO_STRING;
	}

}
