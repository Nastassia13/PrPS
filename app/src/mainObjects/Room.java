package mainObjects;

public class Room {
    private int number;
    private String type;
    private double area;
    private String responsible;

    public Room(int number, String type, double area, String responsible){
        setNumber(number);
        setType(type);
        setArea(area);
        setResponsible(responsible);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}
