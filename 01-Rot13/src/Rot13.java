public class Rot13 {

    // Alfabet català en majúscules
    private static final char[] majuscules = {
            'A', 'Á', 'À', 'Ä', 'B', 'C', 'Ç', 'D', 'E', 'É',
            'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M',
            'N', 'Ñ', 'O', 'Ó', 'Ö', 'P', 'Q', 'R', 'S', 'T',
            'U', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    // Alfabet català en minúscules
    private static final char[] minuscules = {
            'a', 'á', 'à', 'ä', 'b', 'c', 'ç', 'd', 'e', 'é',
            'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm',
            'n', 'ñ', 'o', 'ó', 'ö', 'p', 'q', 'r', 's', 't',
            'u', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr.calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------");

        for(int i = 0; i<msgs.length; i++){
            msgsXifrats[i] = xifraRor13(msgs[i]);   
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("\nDesxifrat\n--------");

        for(String msg: msgsXifrats){
            System.out.printf("%-23s => %s%n", msg, desxifraRot13(msg));
        }
    }

    public static String xifraRot13(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);

            // Comprovem si és una majúscula
            if (Character.isUpperCase(lletra)) {

                for (int j = 0; j < majuscules.length; j++) {

                    if (majuscules[j] == lletra) {
                        int novaPosicio = (j + 13) % majuscules.length;

                        resultat += majuscules[novaPosicio];
                        break;
                    }
                }

            } else if (Character.isLowerCase(lletra)) {

                for (int j = 0; j < minuscules.length; j++) {

                    if (minuscules[j] == lletra) {
                        int novaPosicio = (j + 13) % minuscules.length;

                        resultat += minuscules[novaPosicio];
                        break;
                    }
                }
}