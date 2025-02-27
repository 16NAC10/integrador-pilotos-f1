package utils;

public class NombreParser {

    public static String parse(String fullName){
        String[] palabras = fullName.split("\\s+");
        StringBuilder parsedFullName = new StringBuilder();
        for (String p : palabras) {
            if (!p.isEmpty()) {
                parsedFullName.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1).toLowerCase()).append(" ");
            }
        }
        return parsedFullName.toString().trim();
    }
}

