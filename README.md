# Refatoração SOLID 

Este é o meu trabalho de refatoração de POO avançado. O código original ficava todo dentro do `App.java`. O meu objetivo foi separar as responsabilidades aplicando os princípios SOLID, mantendo o sistema funcionando exatamente como antes, sem usar frameworks externos.

## Aplicação dos Princípios

- **S (Responsabilidade Única):** Tirei a lógica do `App.java` e criei classes separadas para cada tarefa específica dentro da pasta `services` (ex: `AdicionarParticipante`, `ImprimirTabuleiro`).

- **O (Aberto/Fechado):** Removi o `switch/case` do menu no `App.java`. Agora eu uso um `Map` com a interface `Comando`. Ficou fácil adicionar novas opções no menu sem precisar alterar o código do loop principal.

- **L (Substituição de Liskov):** Criei uma interface `ICalcularNota`. O meu sistema usa o cálculo padrão (`CalcularNota`), mas implementei uma versão alternativa `CalcularNotaComPenalidade` para mostrar que posso trocar a regra da nota sem quebrar o resto do código.

- **I (Segregação de Interface):** Em vez de fazer uma interface gigante para o sistema todo, eu criei várias interfaces pequenas, com apenas um método cada (como `IAplicarProva` e `IEscolherProva`). Assim, nenhuma classe implementa o que não usa.

- **D (Inversão de Dependência):** As minhas classes não usam mais o `new` para chamar outras classes diretamente. Agora, o `App.java` injeta os serviços (através de suas interfaces) direto pelos parâmetros do construtor, deixando o meu código menos acoplado.
