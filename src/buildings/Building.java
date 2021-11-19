package buildings;

public interface Building  {

    int getCountFloors();

    // общее число помещений(квартир или офисов)
    int getCountSpaces();

    // общая площадь.
    float getSumArea();

    int getSumCountRooms();

    Floor[] getFloors();

    Floor getFloor(int num);

    void setFloor(int num, Floor newDwellingFloor);

    // получить помещение по номеру
    Space getSpace(int num);

    void setSpace(int num, Space newSpace);

    void addSpace(int num, Space newSpace);

    void eraseSpace(int num);

    // получение лучшего помещения
    Space getBestSpace();

    Space[] getSortedSpaces();

    String toString();

    Object clone();

}
