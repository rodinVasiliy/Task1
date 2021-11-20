package buildingStreams;

import buildings.*;
import buildings.dwelling.Dwelling;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import factories.BuildingFactory;
import factories.DwellingFactory;

import java.io.*;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;


public class Buildings {

    static BuildingFactory buildingFactory = new DwellingFactory();

    void setBuildingFactory(BuildingFactory buildingFactory1) {
        buildingFactory = buildingFactory1;
    }

    public static Space createSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    public Space createSpace(int roomsCount, double area) {
        return buildingFactory.createSpace(roomsCount, area);
    }

    public Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }

    // записи данных о здании в байтовый поток
    public static void outputBuilding(Building building, OutputStream out) {
        DataOutputStream out1 = null;
        try {
            out1 = new DataOutputStream(out);

            out1.writeInt(building.getCountFloors());
            for (int i = 0; i < building.getCountFloors(); i++) {
                out1.writeInt(building.getFloor(i).getCountSpaces());
                for (int j = 0; j < building.getFloor(i).getCountSpaces(); j++) {
                    out1.writeInt(building.getFloor(i).getSpace(j).getCountRooms());
                    out1.writeFloat(building.getFloor(i).getSpace(j).getArea());
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Some error occurred!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // чтения данных о здании из байтового потока
    public static Building inputBuilding(InputStream in) {
        DataInputStream in1 = null;

        Building building = null;
        try {
            in1 = new DataInputStream(in);
            int numberFloor = in1.readInt();
            building = buildingFactory.createBuilding(new Floor[numberFloor]);
            // building = new OfficeBuilding(new Floor[numberFloor]);
            for (int i = 0; i < numberFloor; i++) {

                int numberSpaces = in1.readInt();
                Floor currentFloor = buildingFactory.createFloor(numberSpaces);

                for (int j = 0; j < numberSpaces; j++) {
                    int numberRooms = in1.readInt();
                    float area = in1.readFloat();
                    currentFloor.addSpace(j, buildingFactory.createSpace(numberRooms, area));
                }
                building.setFloor(i, currentFloor);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    // записи здания в символьный поток
    public static void writeBuilding(Building building, Writer out) throws IOException {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(out);

            printWriter.print(building.getCountFloors());
            printWriter.print(" ");
            for (int i = 0; i < building.getCountFloors(); i++) {
                printWriter.print(building.getFloor(i).getCountSpaces());
                printWriter.print(" ");
                for (int j = 0; j < building.getFloor(i).getCountSpaces(); j++) {
                    printWriter.print(building.getFloor(i).getSpace(j).getCountRooms());
                    printWriter.print(" ");
                    printWriter.print(building.getFloor(i).getSpace(j).getArea());
                    printWriter.print(" ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double getNextIntFromTokenaizer(StreamTokenizer in1) throws Exception {
        if (in1.nextToken() != StreamTokenizer.TT_NUMBER)
            throw new Exception("NaN");
        return in1.nval;
    }

    //чтения здания из символьного потока
    public static Building readBuilding(Reader in) {
        StreamTokenizer in1 = new StreamTokenizer(in);
        Building building = null;
        try {
            int numberFloor = (int) getNextIntFromTokenaizer(in1);
            building = buildingFactory.createBuilding(new Floor[numberFloor]);
            for (int i = 0; i < numberFloor; i++) {
                int numberSpaces = (int) getNextIntFromTokenaizer(in1);
                Floor currentFloor = buildingFactory.createFloor(numberSpaces);
                for (int j = 0; j < numberSpaces; j++) {
                    int numberRooms = (int) getNextIntFromTokenaizer(in1);
                    float area = (float) getNextIntFromTokenaizer(in1);
                    currentFloor.addSpace(j, buildingFactory.createSpace(numberRooms, area));
                }
                building.setFloor(i, currentFloor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return building;
    }

    // сериализации здания в байтовый поток
    public static void serializeBuilding(Building building, OutputStream out) {
        ObjectOutputStream out1 = null;
        try {
            out1 = new ObjectOutputStream(out);
            out1.writeObject(building);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // десериализации здания из байтового потока
    public static Building deserializeBuilding(InputStream in) {
        Building building = null;
        ObjectInputStream in1 = null;
        try {
            in1 = new ObjectInputStream(in);
            building = (Building) in1.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return building;

    }

    public static void writeBuildingFormat(Building building, Writer out) {
        Formatter f = new Formatter(out);
        int numberFloors = building.getCountFloors();
        StringBuilder str = new StringBuilder("");
        str.append("NumberFloors: ").append(numberFloors);
        try {
            for (int i = 0; i < building.getCountFloors(); i++) {
                str.append(" NumberFlats: ");
                str.append(building.getFloor(i).getCountSpaces());
                for (int j = 0; j < building.getFloor(i).getCountSpaces(); j++) {
                    str.append(" NumberRooms: ");
                    str.append(building.getFloor(i).getSpace(j).getCountRooms());
                    str.append(" Area: ");
                    str.append(building.getFloor(i).getSpace(j).getArea());
                }
            }
            f.format("Building: %s\n", str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Building readBuilding(Scanner scanner) {
        int numberFloors = 0;
        while (!scanner.hasNextInt()) {
            String tmp = scanner.next();
        }
        numberFloors = scanner.nextInt();
        Floor[] floorsArray = new Floor[numberFloors];
        int numberSpaces = 0;
        int numberRooms = 0;
        float area = 0;
        for (int i = 0; i < numberFloors; i++) {
            while (!scanner.hasNextInt()) {
                String tmp = scanner.next();
            }
            numberSpaces = scanner.nextInt();
            floorsArray[i] = buildingFactory.createFloor(numberSpaces);
            for (int j = 0; j < numberSpaces; j++) {
                while (!scanner.hasNextInt()) {
                    String tmp = scanner.next();
                }
                numberRooms = scanner.nextInt();
                while (!scanner.hasNextFloat()) {
                    String tmp = scanner.next();
                }
                area = scanner.nextFloat();
                floorsArray[i].addSpace(j, buildingFactory.createSpace(numberRooms, area));
            }
        }

        return buildingFactory.createBuilding(floorsArray);
    }

    public static <T extends Comparable<T>> void sort(T[] objects) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (objects[j].compareTo(objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
    }

    public static <T> void sort(T[] objects, Comparator<T> comparator) {
        for (int i = 0; i < objects.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < objects.length; j++) {
                if (comparator.compare(objects[j], objects[minIndex]) < 0)
                    minIndex = j;
            }
            T swapBuf = objects[i];
            objects[i] = objects[minIndex];
            objects[minIndex] = swapBuf;
        }
    }


}
