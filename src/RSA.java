import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {
	
	// User parameter
	private static int numero_bits = 2048;

	public RSA() {}

	/*
	 * Gera uma chave pública e uma chave privada para usar com RSA. Estas chaves
	 * devem ter um número de bits superior a 1000. Imprimir a chave pública e chave
	 * privada.
	 */
	private static void geraChaves() {
		
		// Gera numeros primos aleatorios
		Random random = new SecureRandom();
		BigInteger primo_p = BigInteger.probablePrime(numero_bits / 2, random);
		BigInteger primo_q = BigInteger.probablePrime(numero_bits / 2, random);
		
		System.out.println("Numero primo 'p': "+primo_p+ ", numero primo 'q': "+primo_q);
		
		// Calcula produto dos numeros primos para descobrir o modulo N
		BigInteger modulo_N = primo_p.multiply(primo_q);
		
		// Aplica formula de Euler(N) = p-1 * q-1
		BigInteger phi = primo_p.subtract(BigInteger.ONE)
		    .multiply(primo_q.subtract(BigInteger.ONE));
		
		
		
		// Generate public and private exponents
		BigInteger e;
		do e = new BigInteger(phi.bitLength(), random);
		while (e.compareTo(BigInteger.ONE) <= 0
		    || e.compareTo(phi) >= 0
		    || !e.gcd(phi).equals(BigInteger.ONE));
		BigInteger d = e.modInverse(phi);
		
		
		System.out.println("Chave publica: "+e);
		System.out.println("Chave privada: "+d);

		
	}
	
	
	

	/*
	 * Dada uma mensagem M, cifre esta mensagem usando RSA com a chave pública
	 * gerada no item 1. Imprimir a mensagem cifrada C.
	 */

	private static void cifrarMensagem(String mensagem_M) throws Exception {
		String mensagem_cifrada = "";
		BigInteger cifrando;
		
		
		//byte[] bytes = mensagem_M.getBytes("US-ASCII");
		byte[] bytes = mensagem_M.getBytes(StandardCharsets.US_ASCII);
		cifrando = new BigInteger(bytes);
				
		// Para cifrar, pegar a mensagem de texto claro (Ex: "8"), e elevar essa
		// mensagem na chave publica 'exponent' em mod N.
		mensagem_cifrada = cifrando.modPow(exponent, N);
		
		//mensagem_cifrada = new String(bytes);
		
		System.out.println("Mensagem cifrada: " + mensagem_cifrada);
	}

	
	/*
	 * Dada uma mensagem C, decifre esta mensagem usando RSA com a chave privada
	 * gerada no item 1. Imprimir a mensagem decifrada.
	 */

	private static void decifrarMensagem(BigInteger mensagem_cifrada) {
		String mensagem_decifrada = "";
		BigInteger decifrando;

		// Elevar mensagem cifrada na chave privada 'exponent' em mod 'N'
		decifrando  = mensagem_cifrada.modPow(exponent, biginteger);
		
		mensagem_decifrada = new String(decifrando.toByteArray());
		
		System.out.println("Mensagem decifrada: " + mensagem_decifrada);

	}

	/*
	 * Fontes: 
	 * https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html
	 * https://www.geeksforgeeks.org/biginteger-modpow-method-in-java/
	 * https://www.nayuki.io/page/java-biginteger-was-made-for-rsa-cryptography
	 */
}
