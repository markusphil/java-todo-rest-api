public class Color {
    private static String DEFAULT_COLOR = "#FFF";
    public String value;
    public Color(String val){
        this.value = isValidHexColor(val) ? val : DEFAULT_COLOR;
    }
    private static boolean isValidHexColor(String cc){
        return cc.matches("#([0-9a-f]{3}){1,2}");
    }
}
