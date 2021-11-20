package buildings.compatators;

import buildings.Floor;

import java.util.Comparator;

public class FloorSumAreaComparator implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return (int) (o2.getSumArea() - o1.getSumArea());
    }
}
