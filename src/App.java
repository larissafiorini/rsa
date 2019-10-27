import java.math.BigInteger;
import java.util.Scanner;


public class App {

	/*
	 * RSA
	 * 
	 * Esse programa realiza a geracao de uma chave publica e uma chave privada para
	 * usar com RSA, sendo que as chaves devem ter um numero de bits superior a
	 * 1000. Dada uma mensagem com texto claro M, o programa cifra esta mensagem
	 * usando RSA com a chave publica gerada. Dada uma mensagem cifrada C, o
	 * programa decifra esta mensagem usando RSA com a chave privada gerada. Os
	 * numeros primos que compoem o modulo utilizado no RSA sao gerados utilizando o
	 * pequeno teorema de Fermat.
	 * 
	 * 
	 * Execucao: Para testar o programa e preciso executar a classe principal
	 * 'App.java'. Nessa classe ocorre a chamada dos metodos de geracao das chaves,
	 * de cifragem e decifragem, e tambem a definicao da mensagem de texto claro
	 * inicial.
	 * 
	 * Autora: Larissa Fiorini Martins 
	 * Disciplina: Seguranca de Sistemas 
	 * Professor: Avelino Zorzo
	 * 
	 */


	public static void main(String[] args) throws Exception {

		String mensagem ="";
		String modN ="";
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite: \n1 - Gerar chaves \n2 - Cifrar \n3 - Decifrar");
		Integer escolha = sc.nextInt();
		System.out.println("Digite o numero de bits:");
		Integer num_bits = sc.nextInt();
		RSA rsa = new RSA(num_bits);

		switch(escolha){
			case 1:
				System.out.println("Gerando chaves...");
				// Gera chaves publica e privada
				rsa.geraChaves();
				break;
			case 2:
				System.out.println("Digite a mensagem de texto claro para cifrar: ");
				mensagem = sc.next();
				System.out.println("Digite a chave publica: \n");
				String chave_publica = sc.next();
				System.out.println("Digite o modulo N: \n");
				modN = sc.next();
				System.out.println("Cifrando mensagem...");
				// Recebe mensagem com texto claro para cifrar, chave publica e modulo N
				rsa.cifrarMensagem(mensagem, chave_publica, modN);
				break;
			case 3:
				System.out.println("Digite a mensagem cifrada para decifrar: ");
				mensagem = sc.next();
				System.out.println("Digite a chave privada: ");
				String chave_privada = sc.next();
				System.out.println("Digite o modulo N: ");
				modN = sc.next();
				System.out.println("Decifrando mensagem...");
				// Recebe mensagem com texto cifrado para decifrar, chave privada e modulo N
				rsa.decifrarMensagem(mensagem, chave_privada, modN);
				break;
			default:
				System.out.println("Digite 1, 2 ou 3.");
		}
		sc.close();
		
	}

	/*
	 * Fontes: 
	 * https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html
	 * https://searchsecurity.techtarget.com/definition/RSA
	 * https://www.geeksforgeeks.org/java-math-biginteger-modinverse-method-in-java/
	 * https://br.ccm.net/contents/136-a-codificacao-com-rsa
	 * https://www.geeksforgeeks.org/fermats-little-theorem/
	 * https://www.cut-the-knot.org/blue/Euler.shtml
	 */

}
