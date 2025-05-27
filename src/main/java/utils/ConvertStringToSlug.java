package utils;

public class ConvertStringToSlug {
    public static String convertToSlug(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        return input
                .toLowerCase()
                .replaceAll("[^a-z0-9 ]", "")
                .replaceAll("\\s+", "-");
    }

}
