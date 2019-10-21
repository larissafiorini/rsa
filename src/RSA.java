import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {

	private static int numero_bits = 1024;
	private static BigInteger chave_privada;
	private static BigInteger chave_publica;
	private static BigInteger N;

	public RSA() {
	}

	public static BigInteger getChave_privada() {
		return chave_privada;
	}

	public static BigInteger getChave_publica() {
		return chave_publica;
	}

	public static BigInteger getN() {
		return N;
	}

	/*
	 * Gera uma chave pública e uma chave privada para usar com RSA. Estas chaves
	 * possuem um número de bits superior a 1000. Imprime a chave pública e a chave
	 * privada.
	 */
	public static void geraChaves() {

		// Gera numeros primos aleatorios utilizando o método 'probablePrime()' da
		// biblioteca 'BigInteger' que baseia-se no pequeno teorema de Fermat
		Random random = new SecureRandom();
		BigInteger primo_p = BigInteger.probablePrime(numero_bits, random);
		BigInteger primo_q = BigInteger.probablePrime(numero_bits, random);

		System.out.println("Numero primo 'p': " + primo_p);
		System.out.println("Numero primo 'q': " + primo_q);

		// Calcula o produto dos numeros primos para descobrir o modulo N
		N = primo_p.multiply(primo_q);

		// Aplica formula de Euler(N) = (p-1) * (q-1)
		BigInteger euler = primo_p.subtract(BigInteger.ONE).multiply(primo_q.subtract(BigInteger.ONE));

		// Gera a chave publica utilizando o indicador de Euler encontrado e calculando
		// o maximo divisor comum (gcd) entre Euler e o valor de chave gerado ate que resulte 1
		do
			chave_publica = new BigInteger(euler.bitLength(), random);
		while (chave_publica.compareTo(BigInteger.ONE) <= 0 || chave_publica.compareTo(euler) >= 0
				|| !chave_publica.gcd(euler).equals(BigInteger.ONE));

		// Gera a chave privada utilizando o algoritmo extendido de Euclides disponivel
		// na biblioteca 'BigInteger'
		chave_privada = chave_publica.modInverse(euler);

		System.out.println("Chave publica: " + chave_publica);
		System.out.println("Chave privada: " + chave_privada);

	}

	/*
	 * Dada uma mensagem M, cifra esta mensagem usando RSA com a chave pública
	 * gerada. Imprime a mensagem cifrada C.
	 */

	public BigInteger cifrarMensagem(String mensagem_M, BigInteger chave_publica, BigInteger N) throws Exception {
		BigInteger mensagem_cifrada;

		byte[] bytes = mensagem_M.getBytes(StandardCharsets.US_ASCII);
		mensagem_cifrada = new BigInteger(bytes);

		// Para cifrar, pega a mensagem de texto claro e eleva essa
		// mensagem na chave publica em mod N
		mensagem_cifrada = mensagem_cifrada.modPow(chave_publica, N);

		System.out.println("\nMensagem cifrada: " + mensagem_cifrada.toString());

		return mensagem_cifrada;
	}

	/*
	 * Dada uma mensagem C, decifra esta mensagem usando RSA com a chave privada
	 * gerada. Imprime a mensagem decifrada.
	 */

	public void decifrarMensagem(BigInteger mensagem_cifrada, BigInteger chave_privada, BigInteger N) {
		String mensagem_decifrada = "";

		// Para decifrar, eleva mensagem cifrada na chave privada em mod N
		BigInteger decifrando = mensagem_cifrada.modPow(chave_privada, N);

		mensagem_decifrada = new String(decifrando.toByteArray());

		System.out.println("\nMensagem decifrada: " + mensagem_decifrada);

	}

}
