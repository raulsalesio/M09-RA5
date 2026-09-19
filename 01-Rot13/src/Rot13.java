public class Rot13 {

     private static final String lletres = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
     private static final char[] minuscules = lletres.toLowerCase().toCharArray();
     private static final char[] majuscules = lletres.toCharArray();


    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------");

        for(int i = 0; i<msgs.length; i++){
            msgsXifrats[i] = xifraRot13(msgs[i]);
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

            for (int j = 0; j < majuscules.length; j++) {
                if (majuscules[j] == lletra) {
                    int novaPosicio = (j + 13) % majuscules.length;
                    lletra = majuscules[novaPosicio];
                    break;
                }
            }

            for (int j = 0; j < minuscules.length; j++) {
                if (minuscules[j] == lletra) {
                    int novaPosicio = (j + 13) % minuscules.length;
                    lletra = minuscules[novaPosicio];
                    break;
                }
            }

            resultat += lletra;
        }

        return resultat;
    }

    public static String desxifraRot13(String cadena) {
        return xifraRot13(cadena);
    }
}