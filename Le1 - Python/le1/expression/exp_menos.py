from __future__ import annotations

from le1.expression.exp_unaria import ExpUnaria
from le1.expression.expressao import Expressao
from le1.expression.valor_inteiro import ValorInteiro
from le1.util.tipo import Tipo


class ExpMenos(ExpUnaria):
    def __init__(self, exp: Expressao) -> None:
        super().__init__(exp, "-")

    def avaliar(self) -> ValorInteiro:
        valor = self.getExp().avaliar()

        if not isinstance(valor, ValorInteiro):
            raise TypeError("ExpMenos espera ValorInteiro")

        return ValorInteiro(-valor.valor())

    def checaTipoElementoTerminal(self) -> bool:
        return self.getExp().getTipo().eInteiro()

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_INTEIRO