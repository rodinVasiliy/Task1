package buildings;

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
        System.out.println("count floors = " + building.getCountFloors());
        System.out.println("count Offices = " + building.getCountSpaces());
        System.out.println("sum space = " + building.getSumArea());
        System.out.println("best space = " + building.getBestSpace());
        System.out.println("sum rooms = " + building.getSumCountRooms());
        System.out.println();

        printBuilding(building);

        System.out.println("Sorting:");
        Space[] offices = building.getSortedSpaces();
        for (int i = 0; i < building.getCountSpaces(); ++i) {
            System.out.print(offices[i].getArea() + " ");
        }

        System.out.println();

        printBuilding(building);

        System.out.println("Changing Office:");
        Office newOffice = new Office(100, 10);
        int numChangeOffice = (int) (Math.random() * building.getCountSpaces());
        building.changeSpace(numChangeOffice, newOffice);

        printBuilding(building);

        System.out.println("Adding Office:");
        Office newOfficeToAdd = new Office(150, 20);
        int numAddOffice = (int) (Math.random() * building.getCountSpaces());
        building.addSpace(numAddOffice, newOfficeToAdd);

        printBuilding(building);

/*        System.out.println("Deleting Office:");
        int numOfficeToDelete = (int) (Math.random() * building.getCountSpaces());
        building.eraseSpace(numOfficeToDelete);

        printBuilding(building);*/

    }
}
