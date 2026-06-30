from __future__ import annotations

from le1.expression.exp_binaria import ExpBinaria
from le1.expression.expressao import Expressao
from le1.expression.valor_booleano import ValorBooleano
from le1.expression.valor_concreto import ValorConcreto
from le1.util.tipo import Tipo


class ExpEquals(ExpBinaria):
    def __init__(self, esq: Expressao, dir: Expressao) -> None:
        super().__init__(esq, dir, "==")

    def avaliar(self) -> ValorBooleano:
        valor_esq = self.getEsq().avaliar()
        valor_dir = self.getDir().avaliar()

        if not isinstance(valor_esq, ValorConcreto):
            raise TypeError("ExpEquals espera ValorConcreto à esquerda")

        if not isinstance(valor_dir, ValorConcreto):
            raise TypeError("ExpEquals espera ValorConcreto à direita")

        return ValorBooleano(valor_esq.isEquals(valor_dir))

    def checaTipoElementoTerminal(self) -> bool:
        return self.getEsq().getTipo() == self.getDir().getTipo()

    def getTipo(self) -> Tipo:
        return Tipo.TIPO_BOOLEANO