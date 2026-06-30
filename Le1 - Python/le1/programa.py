from __future__ import annotations

from le1.expression.expressao import Expressao
from le1.expression.valor import Valor


class Programa:
    def __init__(self, exp: Expressao) -> None:
        self._exp = exp

    def executar(self) -> Valor:
        result = self._exp.avaliar()
        print(result)
        return result

    def checaTipo(self) -> bool:
        return self._exp.checaTipo()

    def getExpressao(self) -> Expressao:
        return self._exp