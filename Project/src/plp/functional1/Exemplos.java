package plp.functional1;

import java.util.ArrayList;
import java.util.List;

import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.Expressao;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.ValorInteiro;
import plp.expressions2.expression.ValorString;
import plp.functional1.declaration.DecFuncao;
import plp.functional1.declaration.DecVariavel;
import plp.functional1.expression.Aplicacao;
import plp.functional1.expression.ExpDeclaracao;
import plp.functional1.util.ValorFuncao;

public class Exemplos {

	public static void main(String [] args){
		Id idX = new Id("x");
		Id idY = new Id("y");
		Id idZ = new Id("z");		
		Id idMult = new Id("mult");
		Id idFat = new Id("fat");
		Id idF = new Id("f");
		Id idN = new Id("n");

// mult x y = if (x == 0) then (0) else (y + mult (x - 1, y))
/*
		Expressao expMult2 = new ExpSoma( idY,
							  new Aplicacao( idMult,
							  				 new ListaExpressao( new Expressao[] {
							  				 	new ExpSub(idX, ValorInteiro.UM), idY } )
							  				)
							  			);
		Expressao expMult1 = new IfThenElse(
								new ExpEquals(idX, ValorInteiro.ZERO),
								ValorInteiro.ZERO,
								expMult2
							);
		DecFuncao funMult = new DecFuncao(idMult,
								new ValorFuncao(new ListaId(new Id[] {idX, idY}), expMult1)
							);


		Expressao expFat2 = new Aplicacao( idMult,
								new ListaExpressao( new Expressao[] {
									idN,
									new Aplicacao( idFat,
										new ListaExpressao(
											new ExpSub(idN,
													   ValorInteiro.UM
											)
										)
									)}
								)
							);
		Expressao expFat1 = new IfThenElse(
								new ExpEquals(idN, ValorInteiro.ZERO),
								ValorInteiro.UM,
								expFat2
							);
		DecFuncao funFat = new DecFuncao(idFat,
								new ValorFuncao(new ListaId(idN), expFat1)
							);
		ListaExpressao lexp = new ListaExpressao(new ValorInteiro(5));
		ExpDeclaracao exp1 = new ExpDeclaracao(new SeqDec(funFat), new Aplicacao(idFat, lexp));
		ExpDeclaracao exp = new ExpDeclaracao(new SeqDec(funMult), exp1);
*/


//		let fun fat n =
//		    let fun mult x y = if (x == 0) then (0) else (y + (mult (x - 1) y))
//		    in if (n == 0) then (1) else (mult n (fat (n - 1)))
//		in fat 5

/*
		Expressao expMult2 = new ExpSoma( idY,
							  new Aplicacao( idMult,
							  				 new ListaExpressao( new Expressao[] {
							  				 	new ExpSub(idX, ValorInteiro.UM), idY } )
							  				)
							  			);
		Expressao expMult1 = new IfThenElse(
								new ExpEquals(idX, ValorInteiro.ZERO),
								ValorInteiro.ZERO,
								expMult2
							);
		DecFuncao funMult = new DecFuncao(idMult,
								new ValorFuncao(new ListaId(new Id[] {idX, idY}), expMult1)
							);


		Expressao expFat2 = new Aplicacao( idMult,
								new ListaExpressao( new Expressao[] {
									idN,
									new Aplicacao( idFat,
										new ListaExpressao(
											new ExpSub(idN,
													   ValorInteiro.UM
											)
										)
									)}
								)
							);
		Expressao expFat1 = new IfThenElse(
								new ExpEquals(idN, ValorInteiro.ZERO),
								ValorInteiro.UM,
								expFat2
							);
		Expressao expFat0 = new ExpDeclaracao(new SeqDec(funMult), expFat1);
		DecFuncao funFat = new DecFuncao(idFat,
								new ValorFuncao(new ListaId(idN), expFat0)
							);

		ListaExpressao lexp = new ListaExpressao(new ValorInteiro(5));
		ExpDeclaracao exp = new ExpDeclaracao(new SeqDec(funFat), new Aplicacao(idFat, lexp));
		System.out.println("Expressao original:");
		System.out.println(exp);
		Programa prog = new Programa(exp);

*/

/*
// let var x = 3 in
//     let fun n y = y + x in
//         let var x = 5 in
//             n 1
		DecVariavel decX1 = new DecVariavel(idX, new ValorInteiro(3));
		DecVariavel decX2 = new DecVariavel(idX, new ValorInteiro(5));
		Expressao exp = new Aplicacao(idN, new ListaExpressao(ValorInteiro.UM));
		Expressao exp2 = new ExpDeclaracao(new SeqDec(decX2), exp);
		DecFuncao decN = new DecFuncao(idN, new ValorFuncao(new ListaId(idY), new ExpSoma(idX, idY)));
		Expressao exp3 = new ExpDeclaracao(new SeqDec(decN), exp2);
		Expressao exp4 = new ExpDeclaracao(new SeqDec(decX1), exp3);
		Programa prog = new Programa(exp4);
*/

/*
	// let fun f x = x + 1 in f 2
		DecFuncao decF = new DecFuncao(idF, new ValorFuncao(new ListaId(idX), new ExpSoma(idX, ValorInteiro.UM)));
		Expressao exp = new ExpDeclaracao(new SeqDec(decF), new Aplicacao(idF, new ListaExpressao(new ValorInteiro(2))));
		Programa prog = new Programa(exp);
*/

  // let x=3,f(x,y)=x+y in f(2,x)
/*   Id idX = new Id("x");
   Id idY = new Id("y");
   Id idF = new Id("f");
   DecVariavel decVar = new DecVariavel( idX ,  new ValorBooleano(true) );
   DecFuncao decFun = new DecFuncao(idF, new ValorFuncao( new ListaId(idX,new ListaId(idY)),new ExpSoma(idX ,idY) ) );
   ExpDeclaracao expDeclara = new ExpDeclaracao(new SeqDec(decVar,new SeqDec(decFun)),new Aplicacao(idF,new ListaExpressao(new ValorInteiro(2),new ListaExpressao(idX)) )   );
   Programa prog = new Programa( expDeclara );
*/
// let var y = 3 in
//   let fun f x = x + y in
//     let var z = "abc" in
//       f 3
   List valor3 = new ArrayList();
   valor3.add(new ValorInteiro(3));
   Expressao exp = new Aplicacao(idF, valor3);

	Expressao exp2;   
   {
	   List declaracoes = new ArrayList();
	   declaracoes.add(new DecVariavel(idZ, new ValorString("abc")));
	   exp2 = new ExpDeclaracao(declaracoes,
	   										exp);
   }

   List idXList = new ArrayList();
   idXList.add(idX);
   DecFuncao decF = new DecFuncao(idF, new ValorFuncao(idXList,
   													new ExpSoma(idX, idY)));

	Expressao exp3;
   {
		List declaracoes = new ArrayList();
		declaracoes.add(decF);
   		exp3 = new ExpDeclaracao(declaracoes, exp2);
   }

	Expressao exp4;
	{
		List declaracoes = new ArrayList()	;
		declaracoes.add(new DecVariavel(idY, new ValorInteiro(3)));
		exp4 = new ExpDeclaracao(declaracoes,
   										exp3);
	}
   Programa prog = new Programa( exp4 );
      
		System.out.println("Expressao:");
		System.out.println(prog.getExpressao().toString());
		try {
			if (!prog.checaTipo()) {
				System.out.println("Erro de tipo");
			} else {
				System.out.println(prog.executar());
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			e.printStackTrace();
		}

 }

}

