/**
 * Klasse zum Löschen der Tags
 * Attribute: htmlDatei bezeichnet die eingelesene, zu konvertierende html Datei
 *            charArray bezeichnet die momemtan betrachtete Zeile in der zu konvertierenden Datei
 *            position bezeichnet die momentane Position des Deleter im charArray
 */
public class Deleter {

    private String htmlDatei;
    private char[] charArray;
    int position;

    /**
     * Konstruktor der Klasse
     * @param htmlDatei die als String eingelesene html Datei
     */
    public Deleter(String htmlDatei) {

        this.htmlDatei = htmlDatei;
        charArray = htmlDatei.toCharArray();
    }
    /**
     * bereinigt den html String von Tags
     * @return von Tags bereinigten html String
     */
    public String deleteTags() {
        StringBuilder stringBuilder = new StringBuilder();

        while(position < htmlDatei.length()) {

            /** Wenn " oder ' gefunden wird, Methode ignoreDelete() aufrufen, Character " oder ' übergeben **/
            if((charArray[position] == '"') || (charArray[position] == '\'')) {
                ignoreDelete(charArray[position]);
            } else if(charArray[position] == '<') {

                // Wenn <!-- gefunden wird, deleteComment()aufrufen, um Kommentar nicht zu übernehmen
                /** (musste einfaches Kommentar machen, da JavaDoc aufgrund der Zeichenkette nicht funktioniert.) **/
                if((charArray[position+1] == '!') && (charArray[position+2] == '-') && (charArray[position+3] == '-')) {
                    deleteComment();

                    /** Wenn <style> gefunden wird, deleteStyle() aufrufen, um <style> Tags mit Inhalt nicht zu übernehmen **/
                } else if((charArray[position+1] == 's') && (charArray[position+2] == 't') && (charArray[position+3] == 'y') && (charArray[position+4] == 'l') && (charArray[position+5] == 'e')) {
                    deleteStyle();

                    /** Wenn <head> gefunden wird, deleteHead() aufrufen, um <head> Tags mit Inhalt nicht zu übernehmen **/
                } else if((charArray[position+1] == 'h') && (charArray[position+2] == 'e') && (charArray[position+3] == 'a') && (charArray[position+4] == 'd')) {
                    deleteHead();

                    /** Ansonsten deleteTag() aufrufen, um Tag nicht zu übernehmen **/
                } else {
                    deleteTag();
                }

                /** Ansonsten den Character an stringBuilder anhängen **/
            } else {
                stringBuilder.append(charArray[position]);
                position++;
            }
        }
        /** Bereinigten html String zurückgeben **/
        return stringBuilder.toString();
    }
    /**
     * Falls ein " oder ' innerhalb eines Tags gefunden wurde, solange weiterlaufen, bis " oder ' beendet ist
     * @param c char, welcher gefunden wurde (" oder ')
     */
    public void ignoreDelete(Character c) {
        /** position + 1, um hinter " oder ' zu springen **/
        position++;

        /** Solange kein " oder ' gefunden ist, weiterlaufen **/
        while(charArray[position] != c) {
            position++;
        }

        /** position + 1, um hinter " oder ' zu springen **/
        position++;
    }

    /** Methode, um Kommentare, in denen weitergegangen wird, bis der schliessende Tag erreicht ist nicht zu übernehmen **/
    public void deleteComment() {

        /** position + 4, um an den Anfang des Kommentares zu gelangen **/
        position += 4;

        /** Solange weiterlaufen, bis Ende des Tags erreicht ist **/
        while(!((charArray[position] == '-') && (charArray[position + 1] == '-') && (charArray[position + 2] == '>'))) {
            position++;
        }

        /** position +3, um hinter den Tag zu springen **/
        position += 3;
    }

    /** Methode, um <style> Tags nicht zu übernehmen, in denen weitergegangen wird, bis der schliessende Tag erreicht ist **/
    public void deleteStyle() {

        /** position + 7, um hinter den <style> Tag zu springen **/
        position += 7;

        /** Solange der schliessende Tag nicht erreicht ist, weiterlaufen **/
        while(!((charArray[position] == '<') && (charArray[position+1] == '/') && (charArray[position+2] == 's') && (charArray[position+3] == 't') && (charArray[position+4] == 'y') && (charArray[position+5] == 'l') && (charArray[position+6] == 'e'))) {
            position++;
        }

        /** Zu > springen **/
        while(!(charArray[position] == '>')) {
            position++;
        }

        /** position ++, um hinter den schliessenden Tag zu springen **/
        position ++;
    }

    /** Methode, um <head> Tags nicht zu übernehmen, in denen weitergegangen wird, bis der schliessende Tag erreicht ist **/
    public void deleteHead() {

        /** position + 7 um hinter <head> zu springen **/
        position += 6;

        /** Solange der schliessende Tag nicht erreicht ist, weiterlaufen **/
        while(!((charArray[position] == '<') &&(charArray[position+1] == '/') && (charArray[position+2] == 'h') && (charArray[position+3] == 'e') && (charArray[position+4] == 'a') && (charArray[position+5] == 'd'))) {
            position++;
        }

        /** Zu > springen **/
        while(!(charArray[position] == '>')) {
            position++;
        }

        /** position ++, um hinter den schliessenden Tag zu springen **/
        position ++;
    }

    /** Methode, um Tags nicht zu übernehmen, in denen weitergegangen wird, bis der schliessende Tag erreicht ist **/
    public void deleteTag() {

        /** Solange der schliessende Tag nicht erreicht ist, weiterlaufen **/
        while(charArray[position] != '>') {
            position++;
        }

        /** Hinter den schliessenden Tag springen **/
        position++;
    }
}
