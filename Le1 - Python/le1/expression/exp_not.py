from __future__ import annotations

from le1.expression.exp_unaria import ExpUnaria
from le1.expression.expressao import Expressao
from le1.expression.valor_booleano import ValorBooleano
from le1.util.tipo import Tipo


class ExpNot(ExpUnaria):
    def __init__(self, exp: Expressao) -> None:
        super().__init__(exp, "~")

    def avaliar(self) -> ValorBooleano:
        valor = self.getExp().avaliar()

        if not isinstance(valor, ValorBooleano):
            raise TypeError("ExpNot espera ValorBooleano")

        return ValorBooleano(not valor.valor())

    def checaTipoElementoTerminal(self) -> bool:
        return self.getExp().getTipo().eBooleano()

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_BOOLEANO
