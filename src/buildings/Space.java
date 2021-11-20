package buildings;

public interface Space extends Comparable<Space>{

    int getCountRooms();

    void setCountRooms(int count);

    float getArea();

    void setSpace(float newSpace);

    String toString();

    Object clone();

}
