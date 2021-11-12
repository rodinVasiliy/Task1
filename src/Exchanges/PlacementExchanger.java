package Exchanges;

import Exceptions.FloorIndexOutOfBoundsException;
import Exceptions.InexchangeableFloorsException;
import Exceptions.InexchangeableSpacesException;
import Exceptions.SpaceIndexOutOfBoundsException;
import buildings.Building;
import buildings.Floor;
import buildings.Space;

public class PlacementExchanger {

    public static boolean isExchangedSpaces(Space first, Space second) {
        return first.getArea() == second.getArea() && first.getCountRooms() == second.getCountRooms();
    }

    public static boolean isExchangedFloors(Floor first, Floor second) {
        return first.getSumArea() == second.getSumArea() && first.getCountSpaces() == second.getCountSpaces();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2)
            throws InexchangeableSpacesException {
        if (index1 >= floor1.getCountSpaces())
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        if (index2 >= floor2.getCountSpaces())
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        if (!isExchangedSpaces(floor1.getSpace(index1), floor2.getSpace(index2)))
            throw new InexchangeableSpacesException("InexchangeableSpacesException");

        Space tmpSpace = floor1.getSpace(index1);
        floor1.setSpace(index1, floor2.getSpace(index2));
        floor2.setSpace(index2, tmpSpace);
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2)
            throws InexchangeableFloorsException {
        if (index1 >= building1.getCountFloors())
            throw new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        if (index2 >= building2.getCountFloors())
            throw new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        if (!isExchangedFloors(building1.getFloor(index1), building2.getFloor(index2)))
            throw new InexchangeableFloorsException("InexchangeableFloorsException");

        Floor tmpFloor = building1.getFloor(index1);
        building1.setFloor(index1, building2.getFloor(index2));
        building2.setFloor(index2, tmpFloor);
    }


}
