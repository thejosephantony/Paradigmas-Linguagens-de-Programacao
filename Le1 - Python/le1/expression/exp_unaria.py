from __future__ import annotations

from abc import ABC, abstractmethod

from le1.expression.expressao import Expressao


class ExpUnaria(Expressao, ABC):
    def __init__(self, exp: Expressao, operador: str) -> None:
        self._exp = exp
        self._operador = operador

    def getExp(self) -> Expressao:
        return self._exp

    def getOperador(self) -> str:
        return self._operador

    def __str__(self) -> str:
        return self.getOperador() + "(" + str(self.getExp()) + ")"

    def checaTipo(self) -> bool:
        return self.getExp().checaTipo() and self.checaTipoElementoTerminal()

    @abstractmethod
    def checaTipoElementoTerminal(self) -> bool:
        pass
