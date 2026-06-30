from __future__ import annotations

from abc import ABC, abstractmethod

from le1.expression.expressao import Expressao


class ExpBinaria(Expressao, ABC):
    def __init__(self, esq: Expressao, dir: Expressao, operador: str) -> None:
        self._esq = esq
        self._dir = dir
        self._operador = operador

    def getEsq(self) -> Expressao:
        return self._esq

    def getDir(self) -> Expressao:
        return self._dir

    def getOperador(self) -> str:
        return self._operador

    def __str__(self) -> str:
        return "(" + str(self.getEsq()) + " " + self.getOperador() + " " + str(self.getDir()) + ")"

    def checaTipo(self) -> bool:
        return (
            self.getEsq().checaTipo()
            and self.getDir().checaTipo()
            and self.checaTipoElementoTerminal()
        )

    @abstractmethod
    def checaTipoElementoTerminal(self) -> bool:
        pass