# Trabalho Final para diciplina de Estrutura de Dados

# Documentação

O código se trata de uma implementação de um jogo de aventura controlado por entradas de texto e é baseado no problema do caixeiro viajante, ele consiste em duas classes principais: “Cidade” e “Jogo”.

1. Classe Cidade:
	Responsável por representar cada cidade dentro do mapa do jogo. Possui os seguintes atributos:

	- nome: string que representa o nome da cidade.
	- poderDaCidade: um inteiro que indica o poder ganho ao viajar  - para a cidade.
	- distancia: um inteiro que representa a distância entre uma cidade e a cidade atual.
	- vizinhos: uma lista de objetos Cidade que armazena os vizinhos de cada cidade

	A classe também possui os seguintes métodos:

	- Construtor: inicializa uma instância da classe Cidade tendo como parâmetros o nome e o poder da cidade.
	- Getters e setters: garantem acesso aos atributos da cidade.
	- adicionarVizinho: adiciona uma cidade a lista de vizinhos da cidade atual.
	- getVizinho: retorna a lista de vizinhos da cidade.
	- mostrarVizinhos: exibe os vizinhos da cidade atual enumerados.
retorna o nome do vizinho escolhido a partir do método anterior.

2. Classe Jogo:
A classe jogo representa o jogo e a lógica por traz do seu funcionamento. ela possui os seguintes atributos:

	- poderJoia: um inteiro que indica qual o poder acumulado da jóia no momento.
	- cidadeAtual: um objeto Cidade que indica em qual cidade o jogador se encontra.
	- limiarJoia: um inteiro que define o limite de poder que a joia suporta.
	- moedasTransporte: um inteiro que indica a quantidade de moedas de transporte disponíveis.
	- emMissao: um inteiro que indica se o jogador está em missão (0 - não está em missão, 1, 2 ou 3 - indica a missão que está em andamento).
	- missao: uma string que descreve a missão em andamento.
nivel: um inteiro que indica o atual nível do jogador.
	- cidades: um mapa que mapeia as cidades.
	- scan: um objeto Sanner para entrada de dados.

	E a classe possui os seguintes métodos:

	- Construtor: inicializa uma instância da classe Jogo com os valores iniciais de cada atributo.
	- clearTerminal: limpa a tela do terminal para uma melhor experiência durante o jogo.
	- adicionarCidade: adiciona uma nova cidade ao mapa de cidades.
	- adicionarVizinho: adiciona uma cidade existente como vizinha de outra cidade também existente no mapa.
	- definirCidadeAtual: define qual cidade o jogador está.
	- mostrarCidades: exibe a lista de cidades dentro do mapa enumeradas.
	- escolherCidadePorNumero: acessa uma cidade da lista gerada pelo método anterior através do número correspondente.
	- missaoLocal: destaca uma missão local em cidades que as possuem.
	- concluirMissao: exibe a mensagem de conclusão da missão atual quando o jogador chega na cidade destino da missão.
	- theEnd: finaliza o jogo, variando baseado na situação final do jogador.
	- viajar: realiza uma viagem da cidade atual para algum dos vizinhos.
	- mercador: simula uma interação com um mercador, onde o jogador fornece informações sobre a cidade de origem, cidade de destino, moedas de transporte e se deseja ou não negociar, e baseado nessas respostas o jogador pode ganhar ou perder atributos.

No geral, o código fornece uma estrutura básica para um jogo onde o jogador pode navegar entre as cidades, completar missões, acumular moedas e progredir de nível. O jogador também interage com o mercador que pode ser uma ajuda ou um obstáculo dependendo de suas respostas. E o objetivo final é chegar à cidade de Nargumun com o máximo possível de moedas.
