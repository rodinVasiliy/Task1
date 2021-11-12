package buildings;

import Exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class DwellingFloor implements Floor, Serializable, Cloneable {
    private Space[] flats;

    public DwellingFloor(int count) {
        flats = new Space[count];
        for (int i = 0; i < count; ++i) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space... array) {
        flats = new Space[array.length];
        System.arraycopy(array, 0, flats, 0, flats.length);
    }

    @Override
    public int getCountSpaces() {
        return flats.length;
    }

    @Override
    public float getSumArea() {
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
    public void setSpace(int num, Space newSpace) {
        if (num >= flats.length)
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        flats[num] = newSpace;
    }

    @Override
    public void addSpace(int num, Space newSpace) {
        if (num > flats.length) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
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
    public float getBestSpace() {
        float max = 0;
        for (Space flat : flats) {
            if (flat.getArea() > max) max = flat.getArea();
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Dwelling Floor ").append("(").append(flats.length).append(", ");
        for (int i = 0; i < getCountSpaces() - 1; ++i) {
            sb.append(getSpace(i).toString()).append(",");
        }
        sb.append(getSpace(getCountSpaces() - 1));
        sb.append(")");
        return new String(sb);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof DwellingFloor)) {
            return false;
        }

        DwellingFloor dwellingFloor = (DwellingFloor) object;
        if (this.getCountSpaces() != ((DwellingFloor) object).getCountSpaces()) return false;

        for (int i = 0; i < getCountSpaces(); ++i) {
            if (!this.getSpace(i).equals(((DwellingFloor) object).getSpace(i))) return false;
        }

        return true;
    }

    @Override
    public Object clone() {
        Object copy = null;
        try {
            copy = super.clone();
            ((DwellingFloor)copy).flats = new Space[this.getCountSpaces()];
            for (int i = 0; i < getCountSpaces(); ++i) {
                // ((DwellingFloor)copy).flats[i] = new Flat(this.getSpace(i).getArea(), this.getSpace(i).getCountRooms());
                ((DwellingFloor)copy).flats[i] = (Space) this.getSpace(i).clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public int hashCode() {
        int result = getCountSpaces();
        for (int i = 0; i < getCountSpaces(); ++i) {
            result ^= this.getSpace(i).hashCode();
        }
        return result;
    }

}
