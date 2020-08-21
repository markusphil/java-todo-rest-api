public class Category {
    public String name;
    public Color color;

    public Category(String name, String colorCode){
        this.name = name;
        this.color = new Color(colorCode);
    }

}
