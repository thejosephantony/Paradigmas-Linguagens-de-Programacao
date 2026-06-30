\# LE1 em Python



Implementação da \*\*Linguagem de Expressão 1 (LE1)\*\* em Python.



A LE1 é uma linguagem simples baseada em expressões. Nela, um programa é uma expressão que pode ser avaliada para produzir um valor.



\## Funcionalidades



A implementação possui:



\- Valores inteiros

\- Valores booleanos

\- Valores string

\- Expressões unárias

&#x20; - Menos unário

&#x20; - Not

&#x20; - Length

\- Expressões binárias

&#x20; - Soma

&#x20; - Subtração

&#x20; - And

&#x20; - Or

&#x20; - Igualdade

&#x20; - Concatenação

\- Checagem de tipo

\- Execução de programa



\## Estrutura do projeto



```text

Le1 - Python/

├── main.py

├── README.md

├── .gitignore

└── le1/

&#x20;   ├── \_\_init\_\_.py

&#x20;   ├── programa.py

&#x20;   ├── expression/

&#x20;   │   ├── \_\_init\_\_.py

&#x20;   │   ├── expressao.py

&#x20;   │   ├── valor.py

&#x20;   │   ├── valor\_concreto.py

&#x20;   │   ├── valor\_inteiro.py

&#x20;   │   ├── valor\_booleano.py

&#x20;   │   ├── valor\_string.py

&#x20;   │   ├── exp\_unaria.py

&#x20;   │   ├── exp\_menos.py

&#x20;   │   ├── exp\_not.py

&#x20;   │   ├── exp\_length.py

&#x20;   │   ├── exp\_binaria.py

&#x20;   │   ├── exp\_soma.py

&#x20;   │   ├── exp\_sub.py

&#x20;   │   ├── exp\_and.py

&#x20;   │   ├── exp\_or.py

&#x20;   │   ├── exp\_equals.py

&#x20;   │   └── exp\_concat.py

&#x20;   └── util/

&#x20;       ├── \_\_init\_\_.py

&#x20;       └── tipo.py

```



\## Como executar



No terminal, entre na pasta do projeto:



```bash

cd "C:\\Users\\Joseph\\Downloads\\Paradigmas-Linguagens-de-Programacao\\Le1 - Python"

```



Execute:



```bash

python main.py

```



\## Exemplo de saída



```text

Expressao: 10

Resultado: 10



Expressao: -5

Resultado: -5



Expressao: not true

Resultado: false



Expressao: length("abc")

Resultado: 3



Expressao: 10 + 5

Resultado: 15



Expressao: 10 - 5

Resultado: 5



Expressao: true and false

Resultado: false



Expressao: true or false

Resultado: true



Expressao: 10 == 10

Resultado: true



Expressao: "curso" ++ " PLP"

Resultado: curso PLP



Expressao: length("abc") + 3

Resultado: 6



Expressao: 1 + true

Erro de tipo.

```



\## Exemplos de expressões



\### Valor inteiro



```python

ValorInteiro(10)

```



Representa:



```text

10

```



\### Menos unário



```python

ExpMenos(ValorInteiro(5))

```



Representa:



```text

\-5

```



\### Not



```python

ExpNot(ValorBooleano(True))

```



Representa:



```text

not true

```



Resultado:



```text

false

```



\### Length



```python

ExpLength(ValorString("abc"))

```



Representa:



```text

length("abc")

```



Resultado:



```text

3

```



\### Soma



```python

ExpSoma(

&#x20;   ValorInteiro(10),

&#x20;   ValorInteiro(5)

)

```



Representa:



```text

10 + 5

```



Resultado:



```text

15

```



\### Concatenação



```python

ExpConcat(

&#x20;   ValorString("curso"),

&#x20;   ValorString(" PLP")

)

```



Representa:



```text

"curso" ++ " PLP"

```



Resultado:



```text

curso PLP

```



\## Checagem de tipo



Antes de executar uma expressão, o programa verifica se ela está bem tipada.



Exemplo válido:



```python

ExpSoma(

&#x20;   ValorInteiro(1),

&#x20;   ValorInteiro(2)

)

```



Representa:



```text

1 + 2

```



Resultado:



```text

3

```



Exemplo inválido:



```python

ExpSoma(

&#x20;   ValorInteiro(1),

&#x20;   ValorBooleano(True)

)

```



Representa:



```text

1 + true

```



Resultado:



```text

Erro de tipo.

```



\## Classes principais



\### `Programa`



Representa um programa da LE1.



Na LE1, um programa é apenas uma expressão.



\### `Expressao`



Classe abstrata que representa qualquer expressão da linguagem.



Toda expressão deve implementar:



\- `avaliar()`

\- `checaTipo()`

\- `getTipo()`



\### `Valor`



Representa valores da linguagem.



Tipos de valores:



\- `ValorInteiro`

\- `ValorBooleano`

\- `ValorString`



\### `ExpUnaria`



Classe base para expressões unárias.



Subclasses:



\- `ExpMenos`

\- `ExpNot`

\- `ExpLength`



\### `ExpBinaria`



Classe base para expressões binárias.



Subclasses:



\- `ExpSoma`

\- `ExpSub`

\- `ExpAnd`

\- `ExpOr`

\- `ExpEquals`

\- `ExpConcat`



\## Observação



Esta implementação traduz a LE1 para Python mantendo a mesma ideia da versão Java: as expressões são representadas como objetos, verificadas por tipo e depois avaliadas.

