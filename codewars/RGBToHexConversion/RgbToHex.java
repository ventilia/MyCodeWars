package RGBToHexConversion;

public class RgbToHex {


    public static String rgb(int r, int g, int b) {
        return toHex(clamp(r)) +
                toHex(clamp(g)) +
                toHex(clamp(b));
    }


    private static int clamp(int value) {
        if (value < 0)   return 0;
        if (value > 255) return 255;
        return value;
    }


    private static String toHex(int value) {
        return String.format("%02X", value);
    }
}
