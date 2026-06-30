from __future__ import annotations

from abc import ABC
from typing import Generic, TypeVar

from le1.expression.expressao import Expressao
from le1.util.tipo import Tipo

T = TypeVar("T")


class Valor(Expressao, ABC):
    pass

