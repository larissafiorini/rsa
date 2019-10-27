import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {

	private static int numero_bits = 1024;
	private static BigInteger chave_privada;
	private static BigInteger chave_publica;
	private static BigInteger N;

	public RSA(int nb) {
		numero_bits = nb;
		System.out.println("Numero de bits: "+numero_bits);
	}

	/*
	 * Pequeno teorema de Fermat que verifica se um dado numero e primo, conforme a
	 * formula 2^(p-1) mod p = 1
	 */
	public static boolean Fermat(BigInteger p) {
		for (int i = 0; i < numero_bits; i++) {

			BigInteger a = new BigInteger("2");
			if (!a.modPow(p.subtract(BigInteger.ONE), p).equals(BigInteger.ONE))
				return false;
		}
		return true;
	}

	/*
	 * Gera um numero primo, que ira compor o modulo utilizado no RSA, utilizando o
	 * pequeno teorema de Fermat.
	 */
	public static BigInteger geraNumeroPrimo() {
		BigInteger numero_primo;
		Random random;

		do {
			random = new SecureRandom();
			numero_primo = new BigInteger(numero_bits, random);
		} while (!Fermat(numero_primo));

		return numero_primo;
	}

	/*
	 * Gera uma chave publica e uma chave privada para usar com RSA. Estas chaves
	 * possuem um numero de bits superior a 1000. Por fim, imprime as chaves publica e 
	 * privada geradas.
	 */
	public void geraChaves() {
		BigInteger primo_p = geraNumeroPrimo();
		BigInteger primo_q = geraNumeroPrimo();

		System.out.println("Numero primo 'p': " + primo_p);
		System.out.println("\nNumero primo 'q': " + primo_q);

		// Calcula o produto dos numeros primos para descobrir o modulo N
		N = primo_p.multiply(primo_q);
		System.out.println("\nModulo 'N': " + N);

		// Aplica formula de Euler(N) = (p-1) * (q-1)
		BigInteger euler = primo_p.subtract(BigInteger.ONE).multiply(primo_q.subtract(BigInteger.ONE));
		System.out.println("\nEuler(N): " + euler);

		// Gera a chave publica utilizando o valor de Euler encontrado e calculando
		// o maximo divisor comum (gcd) entre Euler e o valor de chave gerado ate que
		// resulte 1
		do
			chave_publica = new BigInteger(euler.bitLength(), new SecureRandom());
		while (chave_publica.compareTo(BigInteger.ONE) <= 0 || chave_publica.compareTo(euler) >= 0
				|| !chave_publica.gcd(euler).equals(BigInteger.ONE));

		// Gera a chave privada utilizando o algoritmo extendido de Euclides disponivel
		// na biblioteca 'BigInteger'
		chave_privada = chave_publica.modInverse(euler);

		System.out.println("\nChave publica: " + chave_publica);
		System.out.println("\nChave privada: " + chave_privada);

	}

	/*
	 * Dada uma mensagem M, cifra esta mensagem usando RSA com a chave publica
	 * gerada. Imprime a mensagem cifrada C.
	 */
	public BigInteger cifrarMensagem(String mensagem_M, String chave, String modN) throws Exception {
		BigInteger mensagem_cifrada;
		chave_publica = new BigInteger(chave);
		N = new BigInteger(modN);

		byte[] bytes = mensagem_M.getBytes(StandardCharsets.US_ASCII);
		mensagem_cifrada = new BigInteger(bytes);

		// Para cifrar, eleva mensagem de texto claro na chave publica em mod N
		mensagem_cifrada = mensagem_cifrada.modPow(chave_publica, N);

		System.out.println("\nMensagem cifrada: " + mensagem_cifrada.toString());

		return mensagem_cifrada;
	}

	/*
	 * Dada uma mensagem C, decifra esta mensagem usando RSA com a chave privada
	 * gerada. Imprime a mensagem decifrada.
	 */
	public void decifrarMensagem(String mensagem_C, String chave, String modN) {
		String mensagem_decifrada = "";
		BigInteger mensagem_cifrada = new BigInteger(mensagem_C);
		chave_privada = new BigInteger(chave);
		N = new BigInteger(modN);

		// Para decifrar, eleva mensagem cifrada na chave privada em mod N
		BigInteger decifrando = mensagem_cifrada.modPow(chave_privada, N);

		mensagem_decifrada = new String(decifrando.toByteArray());

		System.out.println("\nMensagem decifrada: " + mensagem_decifrada);

	}

}
