package buildings;

import buildingStreams.Buildings;
import buildings.dwelling.Dwelling;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class test {

    static void printBuilding(Building building) {
        for (int i = 0; i < building.getCountFloors(); ++i) {
            OfficeFloor floor = (OfficeFloor) building.getFloor(i);
            for (int j = 0; j < floor.getCountSpaces(); ++j) {
                Office office = (Office) floor.getSpace(j);
                System.out.print(office.getCountRooms() + "," + office.getArea() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
/*        // проверка итератора
        Floor floor1 = new OfficeFloor(new Office(10, 2), new Office(15, 3),
                new Office(20, 4));
        Floor floor2 = new OfficeFloor(new Office(20, 2), new Office(25, 3),
                new Office(30, 4));
        for (Space space : floor1) {
            System.out.println(space);
        }
        Dwelling dwelling = new Dwelling(floor1, floor2);
        for (Floor floor : dwelling) {
            System.out.println(floor);
        }*/

    }
}
