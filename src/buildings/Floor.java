package buildings;

public interface Floor {

    // получения количества помещений на этаже
    int getCountSpaces();

    // получения общей площади помещений на этаже
    int getSumArea();

    // получения общего количества комнат в помещениях этажа
    int getSumCountRooms();

    // получения массива всех помещений этажа
    Space[] getSpaces();

    // получения помещения по его номеру
    Space getSpace(int num);

    // изменения помещения по его номеру и ссылке на новое помещение
    void changeSpace(int num, Space newSpace);

    // вставки помещения по его номеру и ссылке на новое помещение
    void addSpace(int num, Space newSpace) ;

    // удаления помещения по его номеру
    void eraseSpace(int num);

    // получения лучшего помещения на этаже
    int getBestSpace();

}
