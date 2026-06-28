from __future__ import annotations

from enum import Enum
from typing import Optional, FrozenSet, Iterable


class Tipos(Enum):
    INTEIRO = "INTEIRO"
    BOOLEANO = "BOOLEANO"
    STRING = "STRING"
    PID = "PID"
    TUPLA = "TUPLA"


class Tipo:
    TIPO_INTEIRO: Tipo
    TIPO_BOOLEANO: Tipo
    TIPO_STRING: Tipo
    TIPO_PID: Tipo
    TIPO_TUPLA: Tipo
    TIPO_INDEFINIDO: Tipo

    def __init__(
        self,
        tipo: Optional[Iterable[Tipos]] = None,
        prox: Optional[Tipo] = None
    ) -> None:
        if tipo is None:
            self._tipo = frozenset(Tipos)
        else:
            self._tipo = frozenset(tipo)
        self._prox = prox

    def get(self) -> FrozenSet[Tipos]:
        return self._tipo

    def eInteiro(self) -> bool:
        return Tipos.INTEIRO in self._tipo

    def eBooleano(self) -> bool:
        return Tipos.BOOLEANO in self._tipo

    def eString(self) -> bool:
        return Tipos.STRING in self._tipo

    def ePid(self) -> bool:
        return Tipos.PID in self._tipo

    def eTupla(self) -> bool:
        return Tipos.TUPLA in self._tipo

    def eVoid(self) -> bool:
        return len(self._tipo) == 0

    def eValido(self) -> bool:
        return len(self._tipo) == 1

    def intersecao(self, outroTipo: Tipo) -> Tipo:
        if self._tipo == outroTipo._tipo:
            return self
        return Tipo(self._tipo & outroTipo._tipo)

    def getProx(self) -> Optional[Tipo]:
        return self._prox

    def setProx(self, novoProx: Optional[Tipo]) -> None:
        self._prox = novoProx

    def __eq__(self, obj: object) -> bool:
        if isinstance(obj, Tipo):
            return self._tipo == obj._tipo
        return False

    def __hash__(self) -> int:
        return hash(self._tipo)

    def __repr__(self) -> str:
        tipos_str = "{" + ",".join(t.name for t in self._tipo) + "}"
        if self._prox is not None:
            return f"Tipo({tipos_str}, prox={self._prox!r})"
        return f"Tipo({tipos_str})"


Tipo.TIPO_INTEIRO = Tipo({Tipos.INTEIRO})
Tipo.TIPO_BOOLEANO = Tipo({Tipos.BOOLEANO})
Tipo.TIPO_STRING = Tipo({Tipos.STRING})
Tipo.TIPO_PID = Tipo({Tipos.PID})
Tipo.TIPO_TUPLA = Tipo({Tipos.TUPLA})
Tipo.TIPO_INDEFINIDO = Tipo(set())
