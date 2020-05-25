package mainObjects;

import java.util.Date;

public class Document {
    private static int idDocument = 0;
    private static Date date;
    private static int enterpriseNumber;
    private static int positionNumber;
    private static String act;
    private static int roomNumber;
    private static int departmentNumber;

    public Document(Date date, int enterpriseNumber, int positionNumber,
                    String act, int roomNumber, int departmentNumber){
        setIdDocument();
        setDate(date);
        setEnterpriseNumber(enterpriseNumber);
        setPositionNumber(positionNumber);
        setAct(act);
        setRoomNumber(roomNumber);
        setDepartmentNumber(departmentNumber);
    }

    public Document(int roomNumber, int departmentNumber){
        setRoomNumber(roomNumber);
        setDepartmentNumber(departmentNumber);
    }

    public static int getIdDocument() {
        return idDocument;
    }

    public static void setIdDocument() {
        Document.idDocument++;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        Document.date = date;
    }

    public static int getEnterpriseNumber() {
        return enterpriseNumber;
    }

    public static void setEnterpriseNumber(int enterpriseNumber) {
        Document.enterpriseNumber = enterpriseNumber;
    }

    public static int getPositionNumber() {
        return positionNumber;
    }

    public static void setPositionNumber(int positionNumber) {
        Document.positionNumber = positionNumber;
    }

    public static String getAct() {
        return act;
    }

    public static void setAct(String act) {
        Document.act = act;
    }

    public static int getRoomNumber() {
        return roomNumber;
    }

    public static void setRoomNumber(int roomNumber) {
        Document.roomNumber = roomNumber;
    }

    public static int getDepartmentNumber() {
        return departmentNumber;
    }

    public static void setDepartmentNumber(int departmentNumber) {
        Document.departmentNumber = departmentNumber;
    }
}
