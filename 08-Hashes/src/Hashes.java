import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;
    private final String charset = "abcdefABCDEF1234567890!";

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
        
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 65536;
        int keyLength = 128; 
        
        char[] passwordChars = pw.toCharArray();
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] bytes = skf.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;
        char[] charsetArray = charset.toCharArray();
        int n = charsetArray.length;

        // Longitud 1
        for (int i0 = 0; i0 < n; i0++) {
            char[] attempt = {charsetArray[i0]};
            if (comprovaiIncrementa(attempt, alg, hash, salt)) return new String(attempt);
        }

        // Longitud 2
        for (int i0 = 0; i0 < n; i0++) {
            for (int i1 = 0; i1 < n; i1++) {
                char[] attempt = {charsetArray[i0], charsetArray[i1]};
                if (comprovaiIncrementa(attempt, alg, hash, salt)) return new String(attempt);
            }
        }

        // Longitud 3
        for (int i0 = 0; i0 < n; i0++) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    char[] attempt = {charsetArray[i0], charsetArray[i1], charsetArray[i2]};
                    if (comprovaiIncrementa(attempt, alg, hash, salt)) return new String(attempt);
                }
            }
        }

        // Longitud 4
        for (int i0 = 0; i0 < n; i0++) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    for (int i3 = 0; i3 < n; i3++) {
                        char[] attempt = {charsetArray[i0], charsetArray[i1], charsetArray[i2], charsetArray[i3]};
                        if (comprovaiIncrementa(attempt, alg, hash, salt)) return new String(attempt);
                    }
                }
            }
        }

        // Longitud 5
        for (int i0 = 0; i0 < n; i0++) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    for (int i3 = 0; i3 < n; i3++) {
                        for (int i4 = 0; i4 < n; i4++) {
                            char[] attempt = {charsetArray[i0], charsetArray[i1], charsetArray[i2], charsetArray[i3], charsetArray[i4]};
                            if (comprovaiIncrementa(attempt, alg, hash, salt)) return new String(attempt);
                        }
                    }
                }
            }
        }

        // Longitud 6
        for (int i0 = 0; i0 < n; i0++) {
            for (int i1 = 0; i1 < n; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    for (int i3 = 0; i3 < n; i3++) {
                        for (int i4 = 0; i4 < n; i4++) {
                            for (int i5 = 0; i5 < n; i5++) {
                                char[] attempt = {charsetArray[i0], charsetArray[i1], charsetArray[i2], charsetArray[i3], charsetArray[i4], charsetArray[i5]};
                                if (comprovaiIncrementa(attempt, alg, hash, salt)) return new String(attempt);
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private boolean comprovaiIncrementa(char[] attempt, String alg, String targetHash, String salt) throws Exception {
        npass++;
        String pwCandidate = new String(attempt);
        String currentHash = "";

        if ("SHA-512".equals(alg)) {
            currentHash = getSHA512AmbSalt(pwCandidate, salt);
        } else if ("PBKDF2".equals(alg)) {
            currentHash = getPBKDF2AmbSalt(pwCandidate, salt);
        }

        return currentHash.equals(targetHash);
    }

    public String getInterval(long t1, long t2) {
        long millisTotal = t2 - t1;

        long millis = millisTotal % 1000;
        long segonsTotal = millisTotal / 1000;

        long segons = segonsTotal % 60;
        long minutsTotal = segonsTotal / 60;

        long minuts = minutsTotal % 60;
        long horesTotal = minutsTotal / 60;

        long hores = horesTotal % 24;
        long dies = horesTotal / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                             h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("==============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("------------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass     : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps    : %s\n", h.getInterval(t1, t2));
            System.out.printf("------------------------------\n\n");
        }
    }
}