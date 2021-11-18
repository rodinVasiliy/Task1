package buildings;

import buildingStreams.Buildings;
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

        int rangeCountOffices = 20;
        int rangeFloors = 10;
        int rangeCountRoomsInOffice = 5;
        int rangeSpace = 100;
        int countFloors = (int) (Math.random() * rangeFloors) + 10;
        System.out.println("count floors = " + countFloors);
        Floor[] officeFloors = new Floor[countFloors];
        for (int i = 0; i < countFloors; ++i) {
            int countOffices = (int) (Math.random() * rangeCountOffices) + 1;
            Space[] offices = new Office[countOffices];
            for (int j = 0; j < countOffices; ++j) {
                int countRoomsInOffice = (int) (Math.random() * rangeCountRoomsInOffice) + 1;
                int space = (int) (Math.random() * rangeSpace) + 1;
                offices[j] = new Office(space, countRoomsInOffice);
            }
            officeFloors[i] = new OfficeFloor(offices);
        }
        Building building = new OfficeBuilding(officeFloors);

        System.out.println(building.toString());
        printBuilding(building);

      try (FileOutputStream fout = new FileOutputStream("fout")) {
            Buildings.outputBuilding(building, fout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fin = new FileInputStream("fout")){
            Building tmp = Buildings.inputBuilding(fin);
            printBuilding(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }



      try (FileWriter fwr = new FileWriter("fwr");) {
            Buildings.writeBuilding(building, fwr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fr = new FileReader("fwr");) {
            Building tmp = Buildings.readBuilding(fr);
            printBuilding(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fout2 = new FileOutputStream("fout2");) {
            Buildings.serializeBuilding(building, fout2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fin2 = new FileInputStream("fout2");) {
            Building tmp = Buildings.deserializeBuilding(fin2);
            printBuilding(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fwr = new FileWriter("fwr1");) {
            Buildings.writeBuildingFormat(building, fwr);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fr = new FileReader("fwr1");) {
            Scanner scanner = new Scanner(fr);
            scanner.useLocale(Locale.US);
            Building tmp = Buildings.readBuilding(scanner);
            printBuilding(tmp);
        } catch (IOException e) {
            e.printStackTrace();
        }


/*
        // проверка глубокого клонирования DWELLING
        Space flat1 = new Flat(10.0F, 5);
        Space flat2 = (Space) flat1.clone();

        flat2.setCountRooms(10);


        Floor dwellingFloor1 = new DwellingFloor(flat1, flat2);
        Floor dwellingFloor2 = (Floor) dwellingFloor1.clone();

        dwellingFloor2.getSpace(1).setCountRooms(30);

        Building dwelling1 = new Dwelling(dwellingFloor1, dwellingFloor2);
        Building dwelling2 = (Building) dwelling1.clone();

        dwelling2.addSpace(dwelling2.getCountSpaces(),new Flat(44.0F,4));

        System.out.println(dwelling1);
        System.out.println(dwelling2);*/

/*        // проверка глубокого клонирования OfficeBuilding
        Space office1 = new Office(10.0F, 5);
        Space office2 = (Office) office1.clone();

        office2.setSpace(20.0F);

        Floor floor1 = new OfficeFloor(office1, office2);
        Floor floor2 = (Floor) floor1.clone();
        floor2.getSpace(1).setCountRooms(30);

        Building dwelling1 = new OfficeBuilding(floor1, floor2);
        Building dwelling2 = (Building) dwelling1.clone();

        dwelling2.eraseSpace(0);

        System.out.println(dwelling1);
        System.out.println(dwelling2);*/

    }
}
