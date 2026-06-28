from __future__ import annotations

from abc import ABC, abstractmethod
from typing import TYPE_CHECKING

from le1.util.tipo import Tipo

if TYPE_CHECKING:
    from le1.expression.valor import Valor


class Expressao(ABC):
	
	@abstractmethod
	def avaliar(self) -> Valor:
		pass
	
	@abstractmethod
	def checaTipo(self) -> bool:
		pass 
	
	@abstractmethod
	def getTipo(self) -> Tipo:
		pass
		
