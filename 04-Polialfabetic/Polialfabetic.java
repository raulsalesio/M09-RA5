import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {

    private static final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static char[] alfabetPermutat;
    private static Random random;
    private static final int clauSecreta = 1234; 

    public static void initRandom(int clau) {
        random = new Random(clau);
    }

    public static void permutaAlfabet() {
        ArrayList<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }

        Collections.shuffle(llista, random);

        alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
    }

    public static String xifraMonoAlfa(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletraOriginal = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraOriginal);
            
            char lletraMajuscula = Character.toUpperCase(lletraOriginal);
            char lletraXifrada = lletraOriginal;

            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == lletraMajuscula) {
                    char lletraPermutada = alfabetPermutat.charAt(j);
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraPermutada);
                    } else {
                        lletraXifrada = lletraPermutada;
                    }
                    break;
                }
            }
            resultat += resultat + lletraXifrada;
        }
        return resultat;
    }
    

    public static void main(String[] args) {
    String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila"};
    String msgsXifrats[] = new String[msgs.length];

    System.out.println("Xifratge:\n---------");
    for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
    }

    System.out.println("Desxifratge:\n-----------");
    for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        String msg = desxifraPoliAlfa(msgsXifrats[i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
    }
}
}