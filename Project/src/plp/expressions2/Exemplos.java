package plp.expressions2;

import java.util.LinkedList;
import java.util.List;

import plp.expressions2.declaration.DecVariavel;
import plp.expressions2.expression.ExpDeclaracao;
import plp.expressions2.expression.ExpSoma;
import plp.expressions2.expression.Id;
import plp.expressions2.expression.ValorInteiro;

public class Exemplos {

	public static void main(String [] args){
	
	/*
	// let var x = 3 in
	//     let var x = x + 1, var y = x in
	//	     x + y
	//	     
	
	
	    Id idX = new Id("x");
	    Id idY = new Id("y");
	
	
	    DecVariavel decVar1 = new DecVariavel( idX ,  new ValorInteiro(3) );
	
	
	    DecVariavel decVar2 = new DecVariavel( idX , new ExpSoma( idX, new ValorInteiro(1) ) );
	
	
	    DecVariavel decVar3 = new DecVariavel( idY ,  idX );
	
	
	    ExpDeclaracao expDeclara2 = new ExpDeclaracao(new SeqDec(decVar2,
																 new SeqDec(decVar3)),
													  new ExpSoma(idX,idY));
	
	
	    ExpDeclaracao expDeclara = new ExpDeclaracao(new SeqDec(decVar1),expDeclara2);
	
	
	    Programa prg = new Programa( expDeclara );
	
	
		try {
			if (!prg.checaTipo()) {
				System.out.println("Erro de tipo");
			} else {
				System.out.println(prg.executar());
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			e.printStackTrace();
		}
	
	*/
	// let var x = 3 in
	//     let var y = x + 1 in
	//         let var x = 5 in
	//             y
		Id idX = new Id("x");
		Id idY = new Id("y");
	
		DecVariavel decX2 = new DecVariavel(idX, new ValorInteiro(5));
		List<DecVariavel> list3 = new LinkedList<DecVariavel>();
		list3.add(decX2);
		ExpDeclaracao exp3 = new ExpDeclaracao(list3, idY);
		
		DecVariavel decY = new DecVariavel(idY, new ExpSoma(idX, new ValorInteiro(1)));
		List<DecVariavel> list2 = new LinkedList<DecVariavel>();
		list2.add(decY);
		ExpDeclaracao exp2 = new ExpDeclaracao(list2, exp3);
		
		DecVariavel decX1 = new DecVariavel(idX, new ValorInteiro(3));
		List<DecVariavel> list1 = new LinkedList<DecVariavel>();
		list1.add(decX1);
		ExpDeclaracao exp1 = new ExpDeclaracao(list1, exp2);
		
		Programa prg = new Programa(exp1);

		try {
			if (!prg.checaTipo()) {
				System.out.println("Erro de tipo");
			} else {
				System.out.println(prg.executar());
			}
		} catch (Exception e) {
			System.out.println("Erro: " + e);
			e.printStackTrace();
		}

	}

}
