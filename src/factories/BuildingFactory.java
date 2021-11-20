package factories;

import buildings.Building;
import buildings.Floor;
import buildings.Space;

public interface BuildingFactory {

    public Space createSpace(double area);

    public Space createSpace(int roomsCount, double area);

    public Floor createFloor(int spacesCount);

    public Floor createFloor(Space[] spaces);

    public Building createBuilding(int floorsCount, int[] spacesCounts);

    public Building createBuilding(Floor[] floors);

}
