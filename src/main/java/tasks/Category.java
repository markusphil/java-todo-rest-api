package tasks;

public class Category {
    public String name;
    public Color color;
    public int id;

    public Category(String name){
        this.name = name;
        this.color = new Color(null);
    }

    public Category(String name, String colorCode){
        this.name = name;
        this.color = new Color(colorCode);
    }

    public void setId(int id){
        this.id = id;
    }

}
