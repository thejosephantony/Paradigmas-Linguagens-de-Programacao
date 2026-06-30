from __future__ import annotations

from abc import ABC
from typing import Generic, TypeVar

from le1.expression.valor import Valor

T = TypeVar("T")


class ValorConcreto(Valor, Generic[T], ABC):

    def __init__(self, valor: T) -> None:
        self._valor = valor

    def valor(self) -> T:
        return self._valor

    def avaliar(self) -> Valor:
        return self

    def checaTipo(self) -> bool:
        return True

    def isEquals(self, obj: "ValorConcreto[T]") -> bool:
        return self.valor() == obj.valor()

    def __str__(self) -> str:
        return str(self._valor)
