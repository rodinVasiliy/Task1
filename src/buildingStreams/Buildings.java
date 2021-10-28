package buildingStreams;

import buildings.Building;
import buildings.Floor;
import buildings.Space;

import java.io.*;

public class Buildings {
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        out.write(building.getCountFloors());
        out.write(' ');
        for (int i = 0; i < building.getCountFloors(); ++i) {
            Floor floor = building.getFloor(i);
            out.write(floor.getCountSpaces());
            out.write(' ');
            for (int j = 0; j < floor.getCountSpaces(); ++j) {
                out.write(floor.getSpace(j).getCountRooms());
                out.write(' ');
                out.write(floor.getSpace(j).getArea());
            }
            out.write(' ');
        }
    }

/*    public static Building inputBuilding (InputStream in){

    }*/

    public static void writeBuilding(Building building, Writer out) {

    }

/*    public static Building readBuilding (Reader in){

    }*/

}
