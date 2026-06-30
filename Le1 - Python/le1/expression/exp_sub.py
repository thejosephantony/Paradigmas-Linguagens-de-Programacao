from __future__ import annotations

from le1.expression.exp_binaria import ExpBinaria
from le1.expression.expressao import Expressao
from le1.expression.valor_inteiro import ValorInteiro
from le1.util.tipo import Tipo


class ExpSub(ExpBinaria):
    def __init__(self, esq: Expressao, dir: Expressao) -> None:
        super().__init__(esq, dir, "-")

    def avaliar(self) -> ValorInteiro:
        valor_esq = self.getEsq().avaliar()
        valor_dir = self.getDir().avaliar()

        if not isinstance(valor_esq, ValorInteiro):
            raise TypeError("ExpSub espera ValorInteiro à esquerda")

        if not isinstance(valor_dir, ValorInteiro):
            raise TypeError("ExpSub espera ValorInteiro à direita")

        return ValorInteiro(valor_esq.valor() - valor_dir.valor())

    def checaTipoElementoTerminal(self) -> bool:
        return self.getEsq().getTipo().eInteiro() and self.getDir().getTipo().eInteiro()

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_INTEIRO