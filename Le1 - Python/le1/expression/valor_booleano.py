from __future__ import annotations
from le1.expression.valor import Valor
from le1.expression.valor_concreto import ValorConcreto
from abc import ABC
from typing import Generic, TypeVar

from le1.expression.expressao import Expressao
from le1.util.tipo import Tipo

T = TypeVar("T")

class ValorBooleano(ValorConcreto[bool]):
    def __init__(self, valor: bool) -> None:
        super().__init__(valor)

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_BOOLEANO

    def __str__(self) -> str:
        return str(self._valor).lower()

