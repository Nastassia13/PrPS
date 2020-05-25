package mainObjects;

public class Department {
    private int code;
    private String fullName;
    private String shortName;
    private String head;

    public Department(int code, String fullName, String shortName, String head){
        setCode(code);
        setFullName(fullName);
        setShortName(shortName);
        setHead(head);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public  String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
