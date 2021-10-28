package buildings;

import Exceptions.InvalidRoomsCountException;
import Exceptions.InvalidSpaceAreaException;

public class Office implements Space {
    private int area;
    private int numOfRooms;

    public Office() {
        area = 250;
        numOfRooms = 1;
    }

    public Office(int newArea, int newNumOfRooms) {
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
    public void changeCountRooms(int count) {
        if (count <= 0) throw new InvalidRoomsCountException("InvalidRoomsCountException");
        this.numOfRooms = count;
    }

    @Override
    public int getArea() {
        return area;
    }

    @Override
    public void changeSpace(int newSpace) {
        if (newSpace <= 0) throw new InvalidSpaceAreaException("InvalidSpaceAreaException");
        this.area = newSpace;
    }
}
