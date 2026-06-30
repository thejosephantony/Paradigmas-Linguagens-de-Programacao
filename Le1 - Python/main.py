from le1.programa import Programa

from le1.expression.valor_inteiro import ValorInteiro
from le1.expression.valor_booleano import ValorBooleano
from le1.expression.valor_string import ValorString

from le1.expression.exp_menos import ExpMenos
from le1.expression.exp_not import ExpNot
from le1.expression.exp_length import ExpLength

from le1.expression.exp_soma import ExpSoma
from le1.expression.exp_sub import ExpSub
from le1.expression.exp_and import ExpAnd
from le1.expression.exp_or import ExpOr
from le1.expression.exp_equals import ExpEquals
from le1.expression.exp_concat import ExpConcat


def executar_exemplo(nome: str, exp) -> None:
    print("Expressao:", nome)

    programa = Programa(exp)

    if programa.checaTipo():
        print("Resultado:", end=" ")
        programa.executar()
    else:
        print("Erro de tipo.")

    print()


def main() -> None:
    executar_exemplo(
        "10",
        ValorInteiro(10)
    )

    executar_exemplo(
        "-5",
        ExpMenos(ValorInteiro(5))
    )

    executar_exemplo(
        "not true",
        ExpNot(ValorBooleano(True))
    )

    executar_exemplo(
        'length("abc")',
        ExpLength(ValorString("abc"))
    )

    executar_exemplo(
        "10 + 5",
        ExpSoma(ValorInteiro(10), ValorInteiro(5))
    )

    executar_exemplo(
        "10 - 5",
        ExpSub(ValorInteiro(10), ValorInteiro(5))
    )

    executar_exemplo(
        "true and false",
        ExpAnd(ValorBooleano(True), ValorBooleano(False))
    )

    executar_exemplo(
        "true or false",
        ExpOr(ValorBooleano(True), ValorBooleano(False))
    )

    executar_exemplo(
        "10 == 10",
        ExpEquals(ValorInteiro(10), ValorInteiro(10))
    )

    executar_exemplo(
        '"curso" ++ " PLP"',
        ExpConcat(ValorString("curso"), ValorString(" PLP"))
    )

    executar_exemplo(
        'length("abc") + 3',
        ExpSoma(
            ExpLength(ValorString("abc")),
            ValorInteiro(3)
        )
    )

    executar_exemplo(
        "1 + true",
        ExpSoma(
            ValorInteiro(1),
            ValorBooleano(True)
        )
    )


if __name__ == "__main__":
    main()