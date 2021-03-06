package buildings.office;

import Exceptions.InvalidRoomsCountException;
import Exceptions.InvalidSpaceAreaException;
import buildings.Space;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class Office implements Space, Serializable, Cloneable {
    private float area;
    private int numOfRooms;

    public Office() {
        area = 250;
        numOfRooms = 1;
    }

    public Office(float newArea, int newNumOfRooms) {
        if (newArea <= 0) throw new InvalidSpaceAreaException("InvalidSpaceAreaException");
        if (newNumOfRooms <= 0) throw new InvalidRoomsCountException("InvalidRoomsCountException");
        area = newArea;
        numOfRooms = newNumOfRooms;
    }

    @Override
    public int getCountRooms() {
        return numOfRooms;
    }

    @Override
    public void setCountRooms(int count) {
        if (count <= 0) throw new InvalidRoomsCountException("InvalidRoomsCountException");
        this.numOfRooms = count;
    }

    @Override
    public float getArea() {
        return area;
    }

    @Override
    public void setSpace(float newSpace) {
        if (newSpace <= 0) throw new InvalidSpaceAreaException("InvalidSpaceAreaException");
        this.area = newSpace;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Office ").append("(").append(numOfRooms).append(", ").append(area).append(")");
        return new String(sb);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Office)) {
            return false;
        }
        Office office = (Office) object;
        return (this.numOfRooms == office.numOfRooms) && (this.area == office.area);
    }

    @Override
    public Object clone() {
        Object copy = null;
        try {
            copy = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public int hashCode() {
        byte[] arrDoubleBytes;
        double areaDouble = getArea();
        //?????????????????????? ?????????? ?????????? ???????????????? ?? byte[]
        arrDoubleBytes = ByteBuffer.allocate(8).putDouble(areaDouble).array();
        byte[] arrFirstBytes = new byte[4];
        byte[] arrLastBytes = new byte[4];

        // ?? ???????????? ???????????? - ???????????? 4 ??????????, ???? ???????????? - ?????????????????? 4 ??????????
        for (int i = 0; i < 4; i++) {
            arrFirstBytes[i] = arrDoubleBytes[i];
            arrLastBytes[i] = arrDoubleBytes[4 + i];
        }

        int intFirstByte = ByteBuffer.wrap(arrFirstBytes).getInt();
        int intSecondByte = ByteBuffer.wrap(arrLastBytes).getInt();

        return getCountRooms() ^ intFirstByte ^ intSecondByte;
    }

    @Override
    public int compareTo(Space o) {
        return (int) (this.getArea() - o.getArea());
    }
}
