from __future__ import annotations

from le1.expression.exp_binaria import ExpBinaria
from le1.expression.expressao import Expressao
from le1.expression.valor_string import ValorString
from le1.util.tipo import Tipo


class ExpConcat(ExpBinaria):
    def __init__(self, esq: Expressao, dir: Expressao) -> None:
        super().__init__(esq, dir, "++")

    def avaliar(self) -> ValorString:
        valor_esq = self.getEsq().avaliar()
        valor_dir = self.getDir().avaliar()

        if not isinstance(valor_esq, ValorString):
            raise TypeError("ExpConcat espera ValorString à esquerda")

        if not isinstance(valor_dir, ValorString):
            raise TypeError("ExpConcat espera ValorString à direita")

        return ValorString(valor_esq.valor() + valor_dir.valor())

    def checaTipoElementoTerminal(self) -> bool:
        return self.getEsq().getTipo().eString() and self.getDir().getTipo().eString()

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_STRING