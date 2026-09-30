import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {

    private static final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static String alfabetPermutat = "";


    public static char[] permutaAlfabet(char[] alfabet) {

        ArrayList<Character> lista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            lista.add(alfabet[i]);
        }

        Collections.shuffle(lista);

        char[] resultat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            resultat[i] = lista.get(i);
        }

        return resultat;
    }

    public static String xifraMonoAlfa(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletraOriginal = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(cadena);
            
            char lletraMajuscula = Character.toUpperCase(cadena);
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

            resultat += lletraXifrada;
        }

        return resultat;
    }
}