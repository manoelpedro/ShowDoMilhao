import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Showdomilhao{
	
	//atributos usados no main
	static Scanner ler = new Scanner(System.in);
	static boolean continuar = true;
	static boolean respostaInvalida = false;
	static String resposta;
	
	//atributos do jogo em si
	static String nomeDoJogador = null;
	static String valorAcumulado = "";//quantia em dinheiro que o jogador acumulou/ganhou
	static int indicePerguntaSorteada = 0;
	
	static List<String> perguntas = new ArrayList<>();
	static List<String> respostas = new ArrayList<>();
	static List<String> alternativasFalsas1 = new ArrayList<>();
	static List<String> alternativasFalsas2 = new ArrayList<>();
	static List<String> alternativasFalsas3 = new ArrayList<>();
	
	static List<Integer> jaEscolhidas = new ArrayList<>();//guarda o indice das perguntas que ja sairam
	static List<String> valoresDasPerguntas = new ArrayList<>();//valor em dinheiro de cada pergunta (o indice do valor corresponde ao indice da pergunta)
	
	static String a = null; 
	static String b = null; 
	static String c = null; 
	static String d = null;
	
	static int indiceDosValoresDasPerguntas = 0; //indice do valor em dinheiro de cada pergunta

	
	public static void main(String[] args) {
		
		preenchePerguntas();
		
		exibeMenu();
		
		do {
			
			sorteiaPergunta();
			
			exibeAlternativas();		
			
			recebeResposta();
			
			continuar = analisaResposta(resposta);
			
			if(continuar == false || valorAcumulado.equals("1.000.000") ) { //se errou a resposta(continuar == false) ou ja ganhou 1 milhão (valorAcumulado = "1.000.000")
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
				}while(respostaInvalida);
			}
			
		} while(continuar);
		
	}

	
	private static void exibeMenu() {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$ S H O W $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ D O $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$ M I L H Ã O $$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
		System.out.print("Digite seu nome para começar: ");
		nomeDoJogador = ler.nextLine();
		System.out.println("\nBem vindo " + nomeDoJogador + "! O jogo vai começar, boa sorte! \n");
	}

	public static void preenchePerguntas (){
		perguntas.add("Quem descobriu o brasil?");
		perguntas.add("Dois mais dois vale quanto?");
		perguntas.add("A lua é um(a) ?");
		perguntas.add("A Tim é uma");
		perguntas.add("Quem é destro usa o(s) braço(s):");
		perguntas.add("H2O é a fórmula da:");
		
		respostas.add("Pedro Alvares Cabral");
		respostas.add("Quatro");
		respostas.add("Satélite Natural");
		respostas.add("Operadora de celular");
		respostas.add("Direito");
		respostas.add("Água");
		
		alternativasFalsas1.add("napoleao");
		alternativasFalsas1.add("cinco");
		alternativasFalsas1.add("estrela");
		alternativasFalsas1.add("estrela");
		alternativasFalsas1.add("estrela");
		alternativasFalsas1.add("estrela");
		
		alternativasFalsas2.add("d. pedro");
		alternativasFalsas2.add("seis");
		alternativasFalsas2.add("planeta");
		alternativasFalsas2.add("planeta");
		alternativasFalsas2.add("planeta");
		alternativasFalsas2.add("planeta");
		
		alternativasFalsas3.add("cristovao comlombo");
		alternativasFalsas3.add("sete");
		alternativasFalsas3.add("cometa");
		alternativasFalsas3.add("cometa");
		alternativasFalsas3.add("cometa");
		alternativasFalsas3.add("cometa");
		
		valoresDasPerguntas.add("R$ 1.000");
		valoresDasPerguntas.add("R$ 50.000");
		valoresDasPerguntas.add("R$ 100.000");
		valoresDasPerguntas.add("R$ 300.000");
		valoresDasPerguntas.add("R$ 500.000");
		valoresDasPerguntas.add("R$ 1.000.000");
	}
	
	
	
	//recebe a resposta da alternativa e verifica se a letra eh valida
	public static void recebeResposta() {
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
	
	
	//sorteia uma pergunta aleatoria
	public static void sorteiaPergunta() {
		
		int qtdPerguntas = perguntas.size();
		
		Random rand = new Random();
        int numeroPergunta = rand.nextInt(qtdPerguntas);
        if(!jaEscolhidas.contains(numeroPergunta)) {//se nao foi escolhida entao adiciona na lista de jaEscolhidas
        	jaEscolhidas.add(numeroPergunta);
        }else {// se ja foi escolhida procura outra aleatoriamente ate achar uma que nao foi e adiciona na lista de jaEscolhidas
        	while(jaEscolhidas.contains(numeroPergunta)) {
        		numeroPergunta = rand.nextInt(qtdPerguntas);
        	}
        	jaEscolhidas.add(numeroPergunta);
        }
        indicePerguntaSorteada = numeroPergunta;
	}
	
	
	//exibe a pergunta com as alternativas
	//a letra da resposta correta é escolhida de forma aleatoria
	public static void exibeAlternativas() {
		System.out.println("----------------------------------------------------------------\n");
		System.out.println("Prepare-se para a pergunta que vale " + valoresDasPerguntas.get(indiceDosValoresDasPerguntas) + "\n");
		System.out.println(perguntas.get(indicePerguntaSorteada));
		Random rand = new Random();
        int posicao = rand.nextInt(4);
        switch (posicao) {
		case 0:
			a = respostas.get(indicePerguntaSorteada);
			b = alternativasFalsas1.get(indicePerguntaSorteada);
			c = alternativasFalsas2.get(indicePerguntaSorteada);
			d = alternativasFalsas3.get(indicePerguntaSorteada);			
			break;
		case 1:
			a = alternativasFalsas1.get(indicePerguntaSorteada);
			b = respostas.get(indicePerguntaSorteada);
			c = alternativasFalsas2.get(indicePerguntaSorteada);
			d = alternativasFalsas3.get(indicePerguntaSorteada);
			break;
		case 2:
			a = alternativasFalsas1.get(indicePerguntaSorteada);
			b = alternativasFalsas2.get(indicePerguntaSorteada);
			c = respostas.get(indicePerguntaSorteada);
			d = alternativasFalsas3.get(indicePerguntaSorteada);
			break;
		case 3:
			a = alternativasFalsas1.get(indicePerguntaSorteada);
			b = alternativasFalsas2.get(indicePerguntaSorteada);
			c = alternativasFalsas3.get(indicePerguntaSorteada);
			d = respostas.get(indicePerguntaSorteada);
			break;
		default:
			break;
		}
        System.out.println("a) "+ a);
        System.out.println("b) "+ b);
        System.out.println("c) "+ c);
        System.out.println("d) "+ d + "\n");
        System.out.print("Resposta: ");
	}
	
	
	//verifica se a resposta esta correta
	//resposta correta o continuar eh true, logo, o jogo continua
	public static boolean analisaResposta(String resposta) {
		boolean continuar = true;
		switch (resposta) {
		case "a":
			if(a == respostas.get(indicePerguntaSorteada))
				continuar = true;
			else
				continuar = false;
			break;
		case "b":
			if(b == respostas.get(indicePerguntaSorteada) )
				continuar = true;
			else
				continuar = false;
			break;
		case "c":
			if(c == respostas.get(indicePerguntaSorteada))
				continuar = true;
			else
				continuar = false;
			break;
		case "d":
			if(d == respostas.get(indicePerguntaSorteada))
				continuar = true;
			else
				continuar = false;
			break;
		default:
			break;
		}
		
		if(continuar == false) { //se errou a pergunta
			System.out.println("Que pena, você errou.");
			System.out.println("A resposta certa era: " + respostas.get(indicePerguntaSorteada));
			
			if(indiceDosValoresDasPerguntas != 0) {//se errou a primeira pergunta então nao ganha nada, logo nao entra aqui
				System.out.println("Você leva para casa " + valoresDasPerguntas.get(indiceDosValoresDasPerguntas-1) + "\n");//se errar ganha o valor da pergunta anterior
			}
			System.out.print("Deseja continuar jogando? s/n ");//a resposta eh lida no main
			indiceDosValoresDasPerguntas = 0;
			jaEscolhidas = new ArrayList<>(); 
			valorAcumulado = "";
			
		}else{ //se acertou a pergunta
			System.out.println("Parabéns! você ganhou " + valoresDasPerguntas.get(indiceDosValoresDasPerguntas) + "\n");
			valorAcumulado = valoresDasPerguntas.get(indiceDosValoresDasPerguntas);
			indiceDosValoresDasPerguntas += 1;
			
			if(valorAcumulado.equals("1.000.000")){
				System.out.println("Agora você é o mais novo milionário do Brasil!\n");
				System.out.print("Deseja continuar jogando? s/n "); //a resposta eh lida no main
				indiceDosValoresDasPerguntas = 0;
				jaEscolhidas = new ArrayList<>(); 
			}else {
				System.out.println("Próxima pergunta...\n");
			}
		}
		return continuar;
	}
	
	

	
}
