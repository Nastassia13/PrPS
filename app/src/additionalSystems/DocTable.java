package additionalSystems;

import java.util.ArrayList;

public class DocTable {
    private String roomNumber = "";
    private String departmentNumber;
    private int num = 0;

    public DocTable(String roomNumber, String departmentNumber) {
        setRoomNumber(roomNumber);
        setDepartmentNumber(departmentNumber);
        setNum();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (!this.roomNumber.equals(""))
            this.roomNumber += ", ";
        this.roomNumber += roomNumber;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public int getNum() {
        return num;
    }

    public void setNum() {
        this.num++;
    }
}
