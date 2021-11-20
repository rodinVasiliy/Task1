package buildings.compatators;

import buildings.Space;

import java.util.Comparator;

public class SpaceCountRoomsComparator implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        return o2.getCountRooms() - o1.getCountRooms();
    }

}
