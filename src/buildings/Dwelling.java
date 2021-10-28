package buildings;


import Exceptions.FloorIndexOutOfBoundsException;
import Exceptions.SpaceIndexOutOfBoundsException;

public class Dwelling implements Building {
    private Floor[] dwellingFloors;

    public Dwelling(int countOfFloors, int ... countFlatsArray) {
        dwellingFloors = new Floor[countOfFloors];
        for (int i = 0; i < countOfFloors; ++i) {
            dwellingFloors[i] = new OfficeFloor(countFlatsArray[i]);
        }
    }

    public Dwelling(Floor ... dwellingFloors1) {
        dwellingFloors = new Floor[dwellingFloors1.length];
        System.arraycopy(dwellingFloors1, 0, dwellingFloors, 0, dwellingFloors1.length);
    }

    @Override
    public int getCountFloors() {
        return dwellingFloors.length;
    }

    @Override
    public int getCountSpaces() {
        int flatsCount = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            flatsCount += dwellingFloor.getCountSpaces();
        }
        return flatsCount;
    }

    public int getSumArea() {
        int sumArea = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            sumArea += dwellingFloor.getSumArea();
        }
        return sumArea;
    }

    @Override
    public int getSumCountRooms() {
        int sumRooms = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            sumRooms += dwellingFloor.getSumCountRooms();
        }
        return sumRooms;
    }

    @Override
    public Floor[] getFloors() {
        return dwellingFloors;
    }

    @Override
    public Floor getFloor(int num) {
        if (num >= this.getCountFloors())
            throw  new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        return dwellingFloors[num];
    }

    @Override
    public Space getSpace(int num) {
        int count = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            if (num < count + dwellingFloor.getCountSpaces()) {
                return dwellingFloor.getSpace(num - count);
            }
            count += dwellingFloor.getCountSpaces();
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }

    @Override
    public void changeSpace(int num, Space newSpace)  {
        int count = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            if (num < count + dwellingFloor.getCountSpaces()) {
                dwellingFloor.changeSpace(num - count, (Flat) newSpace);
                return;
            }
            count += dwellingFloor.getCountSpaces();
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }

    // возможно убрать FloorIndexOutOfBoundsException
    @Override
    public void addSpace(int num, Space newSpace) {

        int count = 0;
        // добавляем квартиру между уже созданными квартирами
        for (Floor dwellingFloor : dwellingFloors) {
            if (num < count + dwellingFloor.getCountSpaces()) {
                // добавляем квартиру к этажу, вызываем метод у объекта ЭТАЖ
                dwellingFloor.addSpace(num - count, newSpace);
                return;
            }
            count += dwellingFloor.getCountSpaces();
        }
        // если добавляем новую квартиру в конец "списка"
        // например если всего 20 квартир, то можно добавить 21ю, но нельзя 22, 23 и тд...
        if (num == count) {
            // добавляем квартиру на последний этаж
            int numOfFloor = dwellingFloors.length - 1;
            int newNum = dwellingFloors[numOfFloor].getCountSpaces();
            dwellingFloors[numOfFloor].addSpace(newNum, newSpace);
            return;
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }

    @Override
    public void eraseSpace(int num) {
        int count = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            if (num < count + dwellingFloor.getCountSpaces()) {
                dwellingFloor.eraseSpace(num - count);
                return;
            }
            count += dwellingFloor.getCountSpaces();
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }


    public void changeFloor(int num, Floor newDwellingFloor) {
        if (num >= this.getCountFloors()) throw new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        Floor[] newArray = new Floor[dwellingFloors.length];
        System.arraycopy(dwellingFloors, 0, newArray, 0, num);
        newArray[num] = newDwellingFloor;
        System.arraycopy(dwellingFloors, num + 1, newArray, num + 1,
                dwellingFloors.length - num - 1);
        dwellingFloors = newArray;
    }


    public int getBestSpace() {
        int max = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            if (max < dwellingFloor.getBestSpace()) max = dwellingFloor.getBestSpace();
        }
        return max;
    }

    @Override
    public Space[] getSortedSpaces() {

        // собираем новый массив.
        Space[] sorted = new Space[this.getCountSpaces()];
        for (int i = 0; i < this.getCountSpaces(); ++i) {
            if (this.getSpace(i) == null) return null;
            sorted[i] = this.getSpace(i);
        }

        int size = sorted.length;
        for (int i = 0; i < size - 1; ++i) {
            int i_min = i;
            for (int j = i + 1; j < size; ++j) {
                if (sorted[j].getArea() < sorted[i_min].getArea()) i_min = j;
            }
            Space tmp = sorted[i];
            sorted[i] = sorted[i_min];
            sorted[i_min] = tmp;
        }

        return sorted;
    }

}
