import java.io.*;
import java.util.Scanner;

/**
 * Hilfsklasse zur Konsolen-, sowie Dateienein-/ausgabe
 */
public class FileReaderWriter {
    /**
     * Wartet darauf, dass auf der Konsole ein Text eingegeben wird und gibt diesen Text zurück
     * @return Eingelesener Text
     */
    public static String getText() {

        Scanner konsolenScanner = new Scanner(System.in);
        return konsolenScanner.nextLine();
    }
    /**
     * Gibt einen Text auf der Konsole aus
     * @param text  Text, welcher auf der Konsole ausgegeben werden soll
     */
    public static void sendText(String text) {
        System.out.println(text);
    }
    /**
     * Fragt nach einem Dateipfad,
     * liest diese Datei als String ein und gibt diesen zurück
     * @return aus der ausgewählten Datei eingelesener String
     */
    public static String dateiEinlesen() {

        boolean validInput = false;
        StringBuilder stringBuilder = new StringBuilder();

        /** Solange keine valide Eingabe gemacht wurde: **/
        do {
            try {
                sendText("Bitte gib einen validen Dateipfad ein!\n");
                String pfad = getText();

                /** File, FileReader und BufferedReader initialisieren. **/
                File sourceFile = new File(pfad);
                FileReader fr = new FileReader(sourceFile);
                BufferedReader br = new BufferedReader(fr);
                String line = null;

                /** Zeilenumbruch, damit nicht alles auf einer Zeile ausgegeben wird. **/
                String ls = System.getProperty("line.separator");

                /** Solange es eine weitere Zeile in der Datei gibt, Zeile und Zeilenumbruch hinzufuegen. **/
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }
                /** Letzten Zeilenumbruch am Ende der Datei entfernen. **/
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                br.close();
                validInput = true;
            } catch (IOException ioe) {
                sendText("Datei konnte nicht gefunden werden.");
            }
        } while(!validInput);
        return stringBuilder.toString();
    }

    /**
     * Fragt nach Dateipfad und schreibt gegebenen String in die angegegebene Datei
     * @param text Text, welcher in eine Datei geschrieben werden soll
     */
    public static void dateiSchreiben(String text) {

        sendText("Bitte gib den gewünschten Dateinamen an!");
        String pfad = getText();
        try {

            /** FileWriter und BufferedWriter initialisieren **/
            FileWriter fw = new FileWriter(pfad);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        } catch (FileNotFoundException fnfe) {
            sendText("Datei konnte nicht gefunden werden.");
        } catch (IOException ioe) {
            sendText("Ungültiger Dateiname!");
        }

    }

}