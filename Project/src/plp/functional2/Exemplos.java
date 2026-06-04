package plp.functional2;

import java.util.Arrays;

import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.ValorInteiro;
import plp.expressions2.memory.VariavelJaDeclaradaException;
import plp.expressions2.memory.VariavelNaoDeclaradaException;
import plp.functional1.declaration.DecVariavel;
import plp.functional1.declaration.DeclaracaoFuncional;
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
public class Exemplos {

	public static void main(String[] args) {


//let fun add x = fn y . x + y in
//let var id = add(0), var x = 4 in
//  id(1) + x

		Id idAdd = new Id("add");
		Id idX = new Id("x");
		Id idY = new Id("y");
		Id idId = new Id("id");
	
		Aplicacao aplicId =
			new Aplicacao(
				idId,
				Arrays.asList(new Expressao[] { new ValorInteiro(1)}));
		Aplicacao aplicAdd =
			new Aplicacao(
				idAdd,
				Arrays.asList(new Expressao[] { new ValorInteiro(0)}));
		DecVariavel decVarId = new DecVariavel(idId, aplicAdd);
		DecVariavel decVarX = new DecVariavel(idX, new ValorInteiro(4));
	
		ExpSoma expSomaFinal = new ExpSoma(aplicId, idX);
	
		ExpDeclaracao expdecId =
			new ExpDeclaracao(
				Arrays.asList(new DeclaracaoFuncional[] { decVarId, decVarX }),
				expSomaFinal);
	
		ExpSoma expSoma = new ExpSoma(idX, idY);
		ValorFuncao valorFuncaoLambda =
			new ValorFuncao(Arrays.asList(new Id[] { idY }), expSoma);
		ValorFuncao valorFuncaoAdd =
			new ValorFuncao(
				Arrays.asList(new Id[] { idX }),
				valorFuncaoLambda);
		DecFuncao decFunAdd = new DecFuncao(idAdd, valorFuncaoAdd);
	
		ExpDeclaracao expdecAdd =
			new ExpDeclaracao(
				Arrays.asList(new DeclaracaoFuncional[] { decFunAdd }),
				expdecId);
	
		Programa prg = new Programa(expdecAdd);
		try {
			System.out.println(prg.executar());
		} catch (VariavelJaDeclaradaException e) {
			e.printStackTrace();
		} catch (VariavelNaoDeclaradaException e) {
			e.printStackTrace();
		}
	}
}
