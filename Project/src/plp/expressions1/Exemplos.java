/* 
   Execucao de alguns programas-testes.

   Autor: Vander Alves
   Data da ultima alteracao:  12/04/99
*/

package plp.expressions1;

import plp.expressions1.expression.ExpAnd;
import plp.expressions1.expression.ExpConcat;
import plp.expressions1.expression.ExpEquals;
import plp.expressions1.expression.ExpLength;
import plp.expressions1.expression.ExpMenos;
import plp.expressions1.expression.ExpNot;
import plp.expressions1.expression.ExpOr;
import plp.expressions1.expression.ExpSoma;
import plp.expressions1.expression.ExpSub;
import plp.expressions1.expression.ValorBooleano;
import plp.expressions1.expression.ValorInteiro;
import plp.expressions1.expression.ValorString;

public class Exemplos {

  public static void main(String [] args){


    // Avalia   -4 + 12 - 3
    Programa prg = new Programa(
                         new  ExpSub( new ExpSoma( new ExpMenos(new ValorInteiro(4)), 
                                                   new ValorInteiro(12)), 
                                      new ValorInteiro(3)));
	if (!prg.checaTipo()) {
		System.out.println("Erro de tipo");
	} else {
		System.out.println(prg.executar());
	}

    // Avalia    not ( (T and F) or (T and T) )
    prg = new Programa(
                new ExpNot( new ExpOr(new ExpAnd( new ValorBooleano(true),new ValorBooleano(false)), 
                                      new ExpAnd( new ValorBooleano(true), new  ValorBooleano(true)))));

	if (!prg.checaTipo()) {
		System.out.println("Erro de tipo");
	} else {
		System.out.println(prg.executar());
	}


    // Avalia length("Hello" ++ " world" ++ "!");
    prg = new Programa(
                       new ExpLength(
                             new ExpConcat(new ValorString("Hello"),
                                           new ExpConcat(
                                                  new ValorString(" world"),
                                                  new ValorString("!")))));
	if (!prg.checaTipo()) {
		System.out.println("Erro de tipo");
	} else {
		System.out.println(prg.executar());
	}


    // Avalia  length("forro") == 5
    prg = new Programa(new ExpEquals( new ExpLength(new ValorString("forro")), new ValorInteiro(5)));
	if (!prg.checaTipo()) {
		System.out.println("Erro de tipo");
	} else {
		System.out.println(prg.executar());
	}

   // Avalia  "hello" + " world" == "hello world"
  prg = new Programa(new ExpEquals(new ExpConcat(new ValorString("hello"), new ValorString(" world")), 
                                   new ValorString("hello world")));
	if (!prg.checaTipo()) {
		System.out.println("Erro de tipo");
	} else {
		System.out.println(prg.executar());
	}
 

   // Avalia   not T ==  F and 1
  prg = new Programa(new ExpEquals(new ExpNot(new ValorBooleano(true)), 
                                   new ExpAnd(new ValorBooleano(false),new ValorInteiro(1))));
	if (!prg.checaTipo()) {
		System.out.println("Erro de tipo");
	} else {
		System.out.println(prg.executar());
	}
 }
}
