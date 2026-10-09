import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    public static final int MIDA_IV = 16;
    public static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";


    private static SecretKeySpec generaHash(String password) throws Exception {
      byte[] passthroughBytes = password.getBytes(StandardCharsets.UTF_8);
        
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(passthroughBytes);

        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    private static IvParameterSpec generaIv() {
        SecureRandom random = new SecureRandom();
        
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l'String
        
        // Genera IvParameterSpec

        // Genera hash

        // Encrypt

        // Combinar IV i part xifrat

        // return iv+msgxifrat
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extreure l'IV

        // Extreure la part xifrada

        // Fer hash de la clau

        // Desxifrar

        // return String desxifrat
    }
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora illa Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());

            }
        
            System.out.println("---------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
