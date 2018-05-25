import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Showdomilhao{
	
	static Scanner ler = new Scanner(System.in);

	static List<List<String>> questoesFaceisCC;
	static List<List<String>> questoesMediasCC;
	static List<List<String>> questoesDificeisCC;
	static List<List<String>> questoesFaceisGeral;
	static List<List<String>> questoesMediasGeral;
	static List<List<String>> questoesDificeisGeral;
	static List<Integer> premios = new ArrayList<>();
	static String categoria = "";
	static int numeroDaRodadaAtual = 1; //rodada 1 e 2(perguntas nivel facil), rodada 3 e 4(nivel medio), 5 e 6(nivel dificil)
	static int indiceDaPerguntaSorteada;
	static String letraRespostaCertaAtual = "";
	static String nomeDoJogador = null;
	static String resposta;
	static boolean continuar = true;
	
	/**
	 * Essas tres listas recebem as questoes das listas principais(computacao ou geral);
	 * Serao usadas para manipular as questoes que serao ou ja foram sorteadas;
	 * As questoes que ja foram sorteadas serao removidas destas listas para evitar repeticoes.
	 */
	static List<List<String>> faceisAux;
	static List<List<String>> mediasAux;
	static List<List<String>> dificeisAux;
	
	public static void main(String[] args) {
		iniciarJogo();
		
		do {
			
			sorteiaPergunta();
			
			imprimePergunta();		
			
			recebeRespostaDaQuestao();//analisa se eh um caractere valido
			
			imprimeResultado();
			
			
//			System.out.println("***********************");
//			System.out.println("FACEIS: "  + faceisAux.size());
//			System.out.println("MEDIAS: " + mediasAux.size());
//			System.out.println("DIFICEIS: " + dificeisAux.size());
//			System.out.println("RODADA ATUAL: " + numeroDaRodadaAtual);
//			System.out.println("INDICE ALEATORIO: " + indiceDaPerguntaSorteada);
		
		} while(continuar);
	}

	
	private static void iniciarJogo() {
		System.out.println("___________________________________________________________________");
		System.out.println("_____________________________ S H O W _____________________________");
		System.out.println("_______________________________ D O _______________________________");
		System.out.println("___________________________ M I L H A O ___________________________");
		System.out.println("___________________________________________________________________");
		System.out.println("Teste seus conhecimentos e concorra ao premio maximo de R$ 1 milhão.\n");
		System.out.print("Digite seu nome: ");
		nomeDoJogador = ler.nextLine();
		
		preencherQuestoes(); //tem q vir antes de escolheCategorias!!!
		escolheCategoria();
		preencherPremios();
		
		System.out.println("\nBem vindo " + nomeDoJogador + "! O jogo vai começar, boa sorte! \n");
		
	}
	
	//Computacao ou Conhecimentos Gerais
	private static void escolheCategoria() {
		System.out.println("\nQual categoria de questões você deseja?");
		System.out.println("1) Computação");
		System.out.println("2) Conhecimentos Gerias");
		System.out.print("Digite o numero da categoria: ");
		do {
			resposta = ler.next();
				
			if(!resposta.equals("1") && !resposta.equals("2"))
				System.out.print("Categoria invalida. Digite 1 ou 2: ");
		
		}while(!resposta.equals("1") && !resposta.equals("2"));
				
		if(resposta.equals("1")) {
			faceisAux = questoesFaceisCC;
			mediasAux = questoesMediasCC;
			dificeisAux = questoesDificeisCC;
		
		}else if(resposta.equals("2")){
			faceisAux = questoesFaceisGeral;
			mediasAux = questoesMediasGeral;
			dificeisAux = questoesDificeisGeral;
		}
	}
	
	private static void preencherPremios() {
		premios.add(1000);
		premios.add(10000);
		premios.add(50000);
		premios.add(100000);
		premios.add(500000);
		premios.add(1000000);
	}

	public static void preencherQuestoes (){
		
		questoesFaceisCC = new ArrayList<>();
		questoesMediasCC = new ArrayList<>();
		questoesDificeisCC = new ArrayList<>();
		questoesFaceisGeral = new ArrayList<>();
		questoesMediasGeral = new ArrayList<>();
		questoesDificeisGeral = new ArrayList<>();
		
		// GERAL
		List<String> facilgeral1 = new ArrayList<>();
		facilgeral1.add("Quanto vale 2 + 2? ");
		facilgeral1.add("A) 5");
		facilgeral1.add("B) 7");
		facilgeral1.add("C) 4");
		facilgeral1.add("D) 2");
		facilgeral1.add("C");
		facilgeral1.add("c");
		questoesFaceisGeral.add(facilgeral1);
		
		List<String> facilgeral2 = new ArrayList<>();
		facilgeral2.add("O que esta escrito na bandeira do brasil? ");
		facilgeral2.add("A) Ordem e Regresso");
		facilgeral2.add("B) Ordem e retrocesso");
		facilgeral2.add("C) Ordem e progresso");
		facilgeral2.add("D) Progresso e ordem");
		facilgeral2.add("C");
		facilgeral2.add("c");
		questoesFaceisGeral.add(facilgeral2);
		
		List<String> facilgeral3 = new ArrayList<>();
		facilgeral3.add("Qual a tradução da palavra inglesa 'horse'?");
		facilgeral3.add("A) Casa");
		facilgeral3.add("B) Cavalo");
		facilgeral3.add("C) Elefante");
		facilgeral3.add("D) Hipopotamo");
		facilgeral3.add("B");
		facilgeral3.add("b");
		questoesFaceisGeral.add(facilgeral3);
		//
		List<String> mediageral1 = new ArrayList<>();
		mediageral1.add("A frase 'I have a dream' é associada a: ");
		mediageral1.add("A) George W. Bush");
		mediageral1.add("B) Martin Luther King");
		mediageral1.add("C) Michael Jackson");
		mediageral1.add("D) Snoop Dogg");
		mediageral1.add("B");
		mediageral1.add("b");
		questoesMediasGeral.add(mediageral1);
		
		List<String> mediageral2 = new ArrayList<>();
		mediageral2.add("QUal o maior e o menor país do mundo? ");
		mediageral2.add("A) Russia e Vaticano");
		mediageral2.add("B) China e Nepal");
		mediageral2.add("C) Guiana Francesa e Estados Unidos");
		mediageral2.add("D) Canadá e Bangladesh");
		mediageral2.add("A");
		mediageral2.add("a");
		questoesMediasGeral.add(mediageral2);
		
		List<String> mediageral3 = new ArrayList<>();
		mediageral3.add("Qual foi o primeiro recurso usado para explicar a origem das coias?");
		mediageral3.add("A) Mitologia");
		mediageral3.add("B) Filosofia");
		mediageral3.add("C) Matematica");
		mediageral3.add("D) Astronomia");
		mediageral3.add("A");
		mediageral3.add("a");
		questoesMediasGeral.add(mediageral3);
		//
		List<String> dificilgeral1 = new ArrayList<>();
		dificilgeral1.add("Qual a altura em metros da rede de vôlei masculino e feminino?");
		dificilgeral1.add("A) 2,5 e 2,0");
		dificilgeral1.add("B) 2,45 para ambos");
		dificilgeral1.add("C) 1,8 e 1,5");
		dificilgeral1.add("D) 2,43 e 2,24");
		dificilgeral1.add("D");
		dificilgeral1.add("d");
		questoesDificeisGeral.add(dificilgeral1);
		
		List<String> dificilgeral2 = new ArrayList<>();
		dificilgeral2.add("Em que periodo pre-historico o fogo foi descoberto?");
		dificilgeral2.add("A) Neolitico");
		dificilgeral2.add("B) Paleolitico");
		dificilgeral2.add("C) Neolitico");
		dificilgeral2.add("D) Idade Media");
		dificilgeral2.add("B");
		dificilgeral2.add("b");
		questoesDificeisGeral.add(dificilgeral2);
		
		List<String> dificilgeral3 = new ArrayList<>();
		dificilgeral3.add("Qual desses filmes foi baseado na obra de Shakespeare?");
		dificilgeral3.add("A) Muito Barulho por Nada (2012)");
		dificilgeral3.add("B) Capitães de Areia (2011)");
		dificilgeral3.add("C) A Dama das Camélias (1936)");
		dificilgeral3.add("D) Excalibur");
		dificilgeral3.add("A");
		dificilgeral3.add("a");
		questoesDificeisGeral.add(dificilgeral3);
		// COMPUTACAO
		List<String> facilcc1 = new ArrayList<>();
		facilcc1.add("Qual o valor em decimal do binário 00000111");
		facilcc1.add("A) 7");
		facilcc1.add("B) 8");
		facilcc1.add("C) 6");
		facilcc1.add("D) 14");
		facilcc1.add("A");
		facilcc1.add("a");
		questoesFaceisCC.add(facilcc1);
		
		List<String> facilcc2 = new ArrayList<>();
		facilcc2.add("Qual alternativa não contém um Hardware?");
		facilcc2.add("A) Mouse");
		facilcc2.add("B) Processador");
		facilcc2.add("C) Chipset");
		facilcc2.add("D) Debian");
		facilcc2.add("D");
		facilcc2.add("d");
		questoesFaceisCC.add(facilcc2);
		
		List<String> facilcc3 = new ArrayList<>();
		facilcc3.add("É o principal módulo do computador, onde estão conectados todos os periféricos");
		facilcc3.add("A) CPU");
		facilcc3.add("B) Placa mãe");
		facilcc3.add("C) Gabinete");
		facilcc3.add("D) Entrada usb");
		facilcc3.add("B");
		facilcc3.add("b");
		questoesFaceisCC.add(facilcc3);
		//
		List<String> mediacc1 = new ArrayList<>();
		mediacc1.add("Qual alternativa não contém um Hardware?");
		mediacc1.add("A) Mouse");
		mediacc1.add("B) Processador");
		mediacc1.add("C) Chipset");
		mediacc1.add("D) Debian");
		mediacc1.add("D");
		mediacc1.add("d");
		questoesMediasCC.add(mediacc1);
		
		List<String> mediacc2 = new ArrayList<>();
		mediacc2.add("Na computação, qual tecnologia substituiu a válvula?");
		mediacc2.add("A) Capacitor");
		mediacc2.add("B) Resistor");
		mediacc2.add("C) Transistor");
		mediacc2.add("D) Diodo");
		mediacc2.add("C");
		mediacc2.add("c");
		questoesMediasCC.add(mediacc2);
		
		List<String> mediacc3 = new ArrayList<>();
		mediacc3.add("Dizer que a classe A estende a classe B é o mesmo que dizer que:");
		mediacc3.add("A) As classes são irmas");
		mediacc3.add("B) A é superclasse de B");
		mediacc3.add("C) B é filha de A");
		mediacc3.add("D) A é filha de B");
		mediacc3.add("D");
		mediacc3.add("d");
		questoesMediasCC.add(mediacc3);
		//
		List<String> dificilcc1 = new ArrayList<>();
		dificilcc1.add("Na ordem cronológica, marque a alternativa correta");
		dificilcc1.add("A) Ábaco, Eniac, Chip, Transistor e Microprocessador");
		dificilcc1.add("B) Eniac, Ábaco, Chip, Transistor e Microprocessador");
		dificilcc1.add("C) Ábaco, Eniac, Transistor, Chip e Microprocessador.");
		dificilcc1.add("D) Ábaco, Eniac, Chip, Microprocessador e Transistor");
		dificilcc1.add("C");
		dificilcc1.add("c");
		questoesDificeisCC.add(dificilcc1);
		
		List<String> dificilcc2 = new ArrayList<>();
		dificilcc2.add("Em ordem cronológica, marque a alternativa correta");
		dificilcc2.add("A) Ábaco, Eniac, Chip, Transistor e Microprocessador");
		dificilcc2.add("B) Eniac, Ábaco, Chip, Transistor e Microprocessador");
		dificilcc2.add("C) Ábaco, Eniac, Transistor, Chip e Microprocessador.");
		dificilcc2.add("D) Ábaco, Eniac, Chip, Microprocessador e Transistor");
		dificilcc2.add("C");
		dificilcc2.add("c");
		questoesDificeisCC.add(dificilcc2);
		
		List<String> dificilcc3 = new ArrayList<>();
		dificilcc3.add("Pela ordem cronológica, marque a alternativa correta");
		dificilcc3.add("A) Ábaco, Eniac, Chip, Transistor e Microprocessador");
		dificilcc3.add("B) Eniac, Ábaco, Chip, Transistor e Microprocessador");
		dificilcc3.add("C) Ábaco, Eniac, Transistor, Chip e Microprocessador.");
		dificilcc3.add("D) Ábaco, Eniac, Chip, Microprocessador e Transistor");
		dificilcc3.add("C");
		dificilcc3.add("c");
		questoesDificeisCC.add(dificilcc3);
	}
	
	//recebe a resposta da alternativa e verifica se a letra eh valida
	public static void recebeRespostaDaQuestao() {
		boolean respostaInvalida;
		do {
			resposta = ler.next();	
			System.out.println("");
			if(!resposta.equals("a") && !resposta.equals("b") && !resposta.equals("c") && !resposta.equals("d") ) {					
				respostaInvalida = true;
				System.out.print("Resposta inválida. Digite a, b, c ou d: ");
			}else
				respostaInvalida = false;
		}while(respostaInvalida);
	}
	
	
	/**
	 * sorteia uma pergunta aleatoria
	 * @return indice da pergunta sorteada
	 */
	public static void sorteiaPergunta() {
		//int indiceDaPerguntaSorteada;
		Random rand = new Random();
		if(numeroDaRodadaAtual <= 2) {//nivel facil
			indiceDaPerguntaSorteada = rand.nextInt(faceisAux.size());
		}else if(numeroDaRodadaAtual >= 3 && numeroDaRodadaAtual <= 4) {//nivel medio
			indiceDaPerguntaSorteada = rand.nextInt(mediasAux.size());	
		}else {//nivel dificil	
			indiceDaPerguntaSorteada = rand.nextInt(dificeisAux.size());
		}
		//return indiceDaPerguntaSorteada;
	}
	
	//exibe a pergunta com as alternativas
	//a letra da resposta correta é escolhida de forma aleatoria
	public static void imprimePergunta() {
		System.out.println("----------------------------------------------------------------\n");
		System.out.println("Prepare-se para a pergunta que vale " + premios.get(numeroDaRodadaAtual-1) + "\n");
		
		if(numeroDaRodadaAtual <= 2) {
			for (int i = 0; i < 5; i++) {
				System.out.println(faceisAux.get(indiceDaPerguntaSorteada).get(i));
			}
		}else if(numeroDaRodadaAtual >= 3 && numeroDaRodadaAtual <= 4) {
			for (int i = 0; i < 5; i++) {
				System.out.println(mediasAux.get(indiceDaPerguntaSorteada).get(i));	
			}
		}else {	
			for (int i = 0; i < 5; i++) {
				System.out.println(dificeisAux.get(indiceDaPerguntaSorteada).get(i));
			}
		}
        System.out.print("Resposta: ");
	}
	
	
	//verifica se a resposta esta correta
	public static boolean alternativaCorreta() {
		boolean retorno;
		
		if(numeroDaRodadaAtual <= 2) {
			letraRespostaCertaAtual = faceisAux.get(indiceDaPerguntaSorteada).get(5);
			retorno = faceisAux.get(indiceDaPerguntaSorteada).get(5).compareTo(resposta) == 0 ||
					faceisAux.get(indiceDaPerguntaSorteada).get(6).compareTo(resposta) == 0;
			faceisAux.remove(indiceDaPerguntaSorteada);
		
		}else if(numeroDaRodadaAtual >= 3 && numeroDaRodadaAtual <= 4) {
			letraRespostaCertaAtual = mediasAux.get(indiceDaPerguntaSorteada).get(5);
			retorno = mediasAux.get(indiceDaPerguntaSorteada).get(5).compareTo(resposta) == 0 ||
					mediasAux.get(indiceDaPerguntaSorteada).get(6).compareTo(resposta) == 0;
			mediasAux.remove(indiceDaPerguntaSorteada);
		}else {	
			letraRespostaCertaAtual = dificeisAux.get(indiceDaPerguntaSorteada).get(5);
			retorno = dificeisAux.get(indiceDaPerguntaSorteada).get(5).compareTo(resposta) == 0 ||
					dificeisAux.get(indiceDaPerguntaSorteada).get(6).compareTo(resposta) == 0;
			dificeisAux.remove(indiceDaPerguntaSorteada);
		}
		return retorno;
	}
	
	public static void imprimeResultado() {
		
		if(alternativaCorreta()) {
			System.out.println("Parabéns! você ganhou " + premios.get(numeroDaRodadaAtual-1) + "\n");
			//valorAcumulado
			if(premios.size() == (numeroDaRodadaAtual)) {// eh pq respondeu a ultima rodada
				System.out.println("Agora você é o mais novo milionário do Brasil!\n");
				numeroDaRodadaAtual = 1;
				continuar();
			}else {
				numeroDaRodadaAtual++;
				System.out.println("Próxima pergunta...\n");
			}
		}else {//alternativa incorreta
			System.out.println("Que pena, você errou.");
			System.out.println("A resposta certa era: " + letraRespostaCertaAtual);
			if(numeroDaRodadaAtual != 1) {
				System.out.println("Você leva pra casa " + premios.get(numeroDaRodadaAtual-2));
			}
			numeroDaRodadaAtual = 1;
			continuar();
		}
		
	}
	
	public static void continuar() {
		boolean respostaInvalida;
		System.out.print("Deseja continuar jogando? s/n ");
		do {
			resposta = ler.next();
			
			if(resposta.equals("n")) {
				continuar = false;
				respostaInvalida = false;
			}else if(resposta.equals("s")) {
				continuar = true;
				respostaInvalida = false;
			}else {
				respostaInvalida = true;
				System.out.print("Resposta inválida. Digite s ou n: ");
			}
		} while (respostaInvalida);
		
		if (continuar) {
			preencherQuestoes();
			escolheCategoria();
		}
	}
	
	

	
}
