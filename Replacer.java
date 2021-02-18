import java.util.HashMap;
/**
 * Klasse zum Ersetzen der Sonderzeichen in der html Datei
 */
public class Replacer {

    /** Hashmap mit html Zeichenketten als Key und entsprechende Sonderzeichen  als Value **/
    HashMap<String, String> replacerMap = new HashMap<String, String>() {{

        put("&auml;", "ä");
        put("&Auml;", "Ä");
        put("&ouml;", "ö");
        put("&Ouml;", "Ö");
        put("&uuml;", "ü");
        put("&Uuml;", "Ü");
        put("&szlig;", "ß");
        put("&lt;", "<");
        put("&gt;", ">");
        put("&amp;", "&");
        put("&quot;", "\"");
        put("&nbsp;"," ");

    }};

    /**
     *
     * @param htmlDatei die eingelesene html Datei als String
     * @return von Sonderzeichen bereinigte html Datei als String
     */
    public String replaceSonderzeichen(String htmlDatei) {

        /** Für jeden Eintrag der replacerMap die jeweiligen Sonderzeichen ersetzen **/
        for(HashMap.Entry<String, String> entry : replacerMap.entrySet()) {
            htmlDatei = htmlDatei.replaceAll(entry.getKey(), entry.getValue());
        }
        return htmlDatei;
    }
}
