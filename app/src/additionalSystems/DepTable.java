package additionalSystems;


public class DepTable {
    private String roomNumber;
    private String respName;

    public DepTable(String roomNumber, String respName) {
        setRoomNumber(roomNumber);
        setRespName(respName);
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRespName() {
        return respName;
    }

    public void setRespName(String departmentNumber) {
        this.respName = departmentNumber;
    }
}
