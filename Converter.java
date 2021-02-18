/**
 * Klasse zum Konvertieren der html in eine .txt Datei
 */
public class Converter {

    /**
     * Startet die Konvertierung
     */
    public void start() {

        /** html Datei als String in Klasse FileReaderWriter mit Methode dateiEinlesen() einlesen **/
        String htmlDatei = FileReaderWriter.dateiEinlesen();

        /** Neue Instanz der Klasse Deleter anlegen und Methode deleteTags() starten **/
        Deleter deleter = new Deleter(htmlDatei);
        htmlDatei = deleter.deleteTags();

        /** Neue Instanz der Klasse Replacer anlegen und Methode replaceSonderzeichen() starten **/
        Replacer replacer = new Replacer();
        htmlDatei = replacer.replaceSonderzeichen(htmlDatei);

        /** Bereinigte html Datei auf Konsole ausgeben und in angegebener Datei speichern **/
        System.out.println(htmlDatei);
        FileReaderWriter.dateiSchreiben(htmlDatei);
    }
}
