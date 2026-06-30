from __future__ import annotations

from le1.expression.exp_binaria import ExpBinaria
from le1.expression.expressao import Expressao
from le1.expression.valor_booleano import ValorBooleano
from le1.util.tipo import Tipo


class ExpOr(ExpBinaria):
    def __init__(self, esq: Expressao, dir: Expressao) -> None:
        super().__init__(esq, dir, "or")

    def avaliar(self) -> ValorBooleano:
        valor_esq = self.getEsq().avaliar()
        valor_dir = self.getDir().avaliar()

        if not isinstance(valor_esq, ValorBooleano):
            raise TypeError("ExpOr espera ValorBooleano à esquerda")

        if not isinstance(valor_dir, ValorBooleano):
            raise TypeError("ExpOr espera ValorBooleano à direita")

        return ValorBooleano(valor_esq.valor() or valor_dir.valor())

    def checaTipoElementoTerminal(self) -> bool:
        return self.getEsq().getTipo().eBooleano() and self.getDir().getTipo().eBooleano()

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_BOOLEANO