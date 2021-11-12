package buildingStreams;

import buildings.*;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

public class Buildings {

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
        } finally {
            try {
                if (out1 != null) {
                    out1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // чтения данных о здании из байтового потока
    public static Building inputBuilding(InputStream in) {
        DataInputStream in1 = null;

        Building building = null;
        try {
            in1 = new DataInputStream(in);
            int numberFloor = in1.readInt();
            // System.out.println(numberFloor);
            building = new OfficeBuilding(new OfficeFloor[numberFloor]);
            for (int i = 0; i < numberFloor; i++) {
                Floor currentFloor = new OfficeFloor();
                building.setFloor(i, currentFloor);

                int numberSpaces = in1.readInt();
                // System.out.println(numberSpaces);

                for (int j = 0; j < numberSpaces; j++) {
                    Space currentSpace = new Office();
                    int numberRooms = in1.readInt();
                    currentSpace.setCountRooms(numberRooms);
                    // System.out.println(numberRooms);
                    float area = in1.readFloat();
                    currentSpace.setSpace(area);
                    currentFloor.addSpace(j, currentSpace);
                    // System.out.println(area);
                }
            }
            in1.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in1 != null) in1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            printWriter.close();

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

            // System.out.println(numberFloor);
            building = new OfficeBuilding(new OfficeFloor[numberFloor]);
            for (int i = 0; i < numberFloor; i++) {
                Floor currentFloor = new OfficeFloor();
                building.setFloor(i, currentFloor);

                int numberSpaces = (int) getNextIntFromTokenaizer(in1);

                // System.out.println(numberSpaces);

                for (int j = 0; j < numberSpaces; j++) {
                    Space currentSpace = new Office();
                    currentFloor.addSpace(j, currentSpace);

                    int numberRooms = (int) getNextIntFromTokenaizer(in1);

                    currentSpace.setCountRooms(numberRooms);
                    // System.out.println(numberRooms);

                    float area = (float) getNextIntFromTokenaizer(in1);

                    currentSpace.setSpace(area);
                    // System.out.println(area);
                }
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
            out1.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out1 != null) out1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        } finally {
            try {
                if (in1 != null) in1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            f.close();
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
                // System.out.println(tmp);
            }
            numberSpaces = scanner.nextInt();

            floorsArray[i] = new OfficeFloor(numberSpaces);
            for (int j = 0; j < numberSpaces; j++) {
                while (!scanner.hasNextInt()) {
                    String tmp = scanner.next();
                    // System.out.println(tmp);
                }
                numberRooms = scanner.nextInt();
                while (!scanner.hasNextFloat()) {
                    String tmp = scanner.next();
                   //  System.out.println(tmp);
                }
                area = scanner.nextFloat();
                floorsArray[i].addSpace(j, new Office(area, numberRooms));
            }
        }
        Building redBuilding = new OfficeBuilding(floorsArray);
        return redBuilding;
    }

}
