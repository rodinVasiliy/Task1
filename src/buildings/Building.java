package buildings;

public interface Building {

    int getCountFloors();

    // общее число помещений(квартир или офисов)
    int getCountSpaces();

    // общая площадь.
    int getSumArea();

    int getSumCountRooms();

    Floor[] getFloors();

    Floor getFloor(int num);

    void changeFloor(int num, Floor newDwellingFloor);

    // получить помещение по номеру
    Space getSpace(int num);

    void changeSpace(int num, Space newSpace);

    void addSpace(int num, Space newSpace);

    void eraseSpace(int num);

    // получение лучшего помещения
    int getBestSpace();

    Space[] getSortedSpaces();

}
