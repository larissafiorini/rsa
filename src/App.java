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
	 * Autora: Larissa Fiorini Martins 
	 * Disciplina: Seguranca de Sistemas 
	 * Professor: Avelino Zorzo
	 * 
	 */

	public static void main(String[] args) throws Exception {
		int op = 1;
		String linha="";
		Scanner sc = new Scanner(System.in);
		while (op == 1) {
			String mensagem = "";
			String modN = "";
			String chave_publica="";
			String chave_privada="";
			System.out.println("Digite: \n1 - Gerar chaves \n2 - Cifrar \n3 - Decifrar");
			linha=sc.nextLine();
			Integer escolha = Integer.parseInt(linha);
			System.out.println("Digite o numero de bits:");
			linha=sc.nextLine();
			Integer num_bits =Integer.parseInt(linha);
			RSA rsa = new RSA(num_bits);

			switch (escolha) {
			case 1:
				System.out.println("Gerando chaves...");
				// Gera chaves publica e privada
				rsa.geraChaves();
				break;
			case 2:
				System.out.println("Digite a mensagem de texto claro para cifrar:");
				mensagem = sc.nextLine();
				System.out.println("Digite a chave publica:");
				chave_publica = sc.nextLine();
				System.out.println("Digite o modulo N:");
				modN = sc.nextLine();
				System.out.println("Cifrando mensagem...");
				// Recebe mensagem com texto claro para cifrar, chave publica e modulo N
				rsa.cifrarMensagem(mensagem, chave_publica, modN);
				break;
			case 3:
				System.out.println("Digite a mensagem cifrada para decifrar: ");
				mensagem = sc.nextLine();
				System.out.println("Digite a chave privada:");
				chave_privada = sc.nextLine();
				System.out.println("Digite o modulo N:");
				modN = sc.nextLine();
				System.out.println("Decifrando mensagem...");
				// Recebe mensagem com texto cifrado para decifrar, chave privada e modulo N
				rsa.decifrarMensagem(mensagem, chave_privada, modN);
				break;
			default:
				System.out.println("Digite 1, 2 ou 3.");
			}
			System.out.println("\nDigite 1 para continuar a execucao, 0 para encerrar.");
			linha=sc.nextLine();
			op = Integer.parseInt(linha);
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
