from __future__ import annotations

from abc import ABC
from typing import Generic, TypeVar
from le1.expression.valor import Valor
from le1.expression.valor_concreto import ValorConcreto
from le1.expression.expressao import Expressao
from le1.util.tipo import Tipo

T = TypeVar("T")

class ValorInteiro(ValorConcreto[int]):
    def __init__(self, valor: int) -> None:
        super().__init__(valor)

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_INTEIRO

