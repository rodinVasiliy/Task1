package buildings;

import Exceptions.SpaceIndexOutOfBoundsException;

public class DwellingFloor implements Floor {
    private Space[] flats;

    public DwellingFloor(int count) {
        flats = new Space[count];
        for (int i = 0; i < count; ++i) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space ... array) {
        flats = new Space[array.length];
        System.arraycopy(array, 0, flats, 0, flats.length);
    }

    @Override
    public int getCountSpaces() {
        return flats.length;
    }

    @Override
    public int getSumArea() {
        int sum = 0;
        for (Space flat : flats) {
            sum += flat.getArea();
        }
        return sum;
    }

    @Override
    public int getSumCountRooms() {
        int sum = 0;
        for (Space flat : flats) {
            sum += flat.getCountRooms();
        }
        return sum;
    }

    @Override
    public Space[] getSpaces() {
        return flats;
    }

    @Override
    public Space getSpace(int num) {
        if (num >= flats.length)
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        return flats[num];
    }

    @Override
    public void changeSpace(int num, Space newSpace)  {
        if (num >= flats.length)
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        flats[num] = newSpace;
    }

    @Override
    public void addSpace(int num, Space newSpace) {
        if(num > flats.length) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        Space[] newArr = new Space[flats.length + 1];
        System.arraycopy(flats, 0, newArr, 0, num);
        newArr[num] = newSpace;
        System.arraycopy(flats, num, newArr, num + 1, flats.length - num);
        flats = newArr;
    }

    @Override
    public void eraseSpace(int num) {
        if (num >= flats.length)
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        Space[] newArr = new Space[flats.length - 1];
        System.arraycopy(flats, 0, newArr, 0, num);
        System.arraycopy(flats, num + 1, newArr, num, flats.length - num - 1);
        flats = newArr;
    }

    @Override
    public int getBestSpace() {
        int max = 0;
        for (Space flat : flats) {
            if (flat.getArea() > max) max = flat.getArea();
        }
        return max;
    }

}
