package tasks;

public class Color {
    public static String DEFAULT_COLOR = "#FFF";
    public String value;
    public Color(String val){
        this.value = isValidHexColor(val) ? val : DEFAULT_COLOR;
    }
    private static boolean isValidHexColor(String cc){
        if(cc == null) return false;
        return cc.matches("#([0-9a-fA-F]{3}){1,2}");
    }
}
