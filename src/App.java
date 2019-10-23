import java.math.BigInteger;

public class App {

	/*
	 * RSA
	 * 
	 * Esse programa realiza a geracao de uma chave publica e uma chave privada para
	 * usar com RSA, sendo que as chaves devem ter um numero de bits superior a
	 * 1000. Dada uma mensagem com texto claro M, o programa cifra esta mensagem
	 * usando RSA com a chave publica gerada. Dada uma mensagem cifrada C, o
	 * programa decifra esta mensagem usando RSA com a chave privada gerada. Os
	 * numeros primos que compoem o modulo utilizado no RSA são gerados utilizando o
	 * pequeno teorema de Fermat.
	 * 
	 * 
	 * Execucao: Para testar o programa é preciso executar a classe principal
	 * 'App.java'. Nessa classe ocorre a chamada dos metodos de geracao das chaves,
	 * de cifragem e decifragem, e também a definicao da mensagem de texto claro
	 * inicial.
	 * 
	 * Autora: Larissa Fiorini Martins 
	 * Disciplina: Seguranca de Sistemas 
	 * Professor: Avelino Zorzo
	 * 
	 */

	public static void main(String[] args) {

		String mensagem = "Implementando RSA em Java";
		System.out.println("**RSA** \nMensagem original: " + mensagem+"\n");

		try {
			RSA rsa = new RSA();

			// Gera chaves publica e privada
			rsa.geraChaves();

			// Recebe mensagem com texto claro para cifrar, chave publica e modulo N
			BigInteger mensagem_cifrada = rsa.cifrarMensagem(mensagem, rsa.getChave_publica(), rsa.getN());

			// Recebe mensagem com texto cifrado para decifrar, chave privada e modulo N
			rsa.decifrarMensagem(mensagem_cifrada, rsa.getChave_privada(), rsa.getN());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Fontes: 
	 * https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html
	 * https://searchsecurity.techtarget.com/definition/RSA
	 * https://www.geeksforgeeks.org/java-math-biginteger-modinverse-method-in-java/
	 * https://br.ccm.net/contents/136-a-codificacao-com-rsa
	 * https://www.geeksforgeeks.org/fermats-little-theorem/
	 */

}
