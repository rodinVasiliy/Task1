package buildings;


import Exceptions.FloorIndexOutOfBoundsException;
import Exceptions.SpaceIndexOutOfBoundsException;

public class OfficeBuilding implements Building {

    private static class Node {
        Floor floor;
        Node next;
        Node prev;

        Node(Floor newFloor) {
            floor = newFloor;
            next = null;
            prev = null;
        }

    }

    private Node head;

    // - приватный метод получения узла по его номеру;
    private Node getNode(int num) {
        Node currentNode = head;
        for (int i = 0; i < num; ++i) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    // - приватный метод добавления узла в список по номеру;
    private void addNode(int num, Node newNode) {
        if (num == 0) {
            newNode.prev = head.prev;
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        Node currentNode = head;
        for (int i = 0; i < num - 1; ++i) {
            currentNode = currentNode.next;
        }
        currentNode.next.prev = newNode;
        newNode.prev = currentNode;
        newNode.next = currentNode.next;
    }

    // - приватный метод удаления узла из списка по его номеру.
    private void eraseNode(int num) {
        if (num == 0) {
            head.prev.next = head.next;
            head.next.prev = head.prev;
            head = head.next;
        } else {
            Node currentNode = head;
            for (int i = 0; i < num - 1; ++i) {
                currentNode = currentNode.next;
            }
            currentNode.next.next.prev = currentNode;
            currentNode.next = currentNode.next.next;
        }
    }

    // Конструктор может принимать количество этажей и массив количества офисов по этажам.
/*   public OfficeBuilding(int num, int[] arr) {
        if (num == 0) {
            head = null;
            return;
        }
        if (num == 1) {
            head = new Node(new F);
            head.prev = head;
            head.next = head;
            return;
        }

        head = new Node(new OfficeFloor(arr[0]));
        Node currentNode = head;
        for (int i = 0; i < num - 1; ++i) {
            head.next = new Node(new OfficeFloor(arr[i + 1]));
            head.next.prev = head;
            head = head.next;
        }
        head.next = currentNode;
        currentNode.prev = head;
        head = currentNode;

    }*/

    // Конструктор может принимать массив этажей офисного здания.
    OfficeBuilding(Floor ... officeFloors) {
        if (officeFloors.length == 0) {
            head = null;
        }
        if (officeFloors.length == 1) {
            head = new Node(officeFloors[0]);
        }
        head = new Node(officeFloors[0]);
        Node currentNode = head;
        for (int i = 0; i < officeFloors.length - 1; ++i) {
            head.next = new Node(officeFloors[i + 1]);
            head.next.prev = head;
            head = head.next;
        }
        head.next = currentNode;
        currentNode.prev = head;
        head = currentNode;

    }

    //Создайте метод получения общего количества этажей здания.
    public int getCountFloors() {
        if (head == null) return 0;
        if (head.next == head) return 1;
        Node currentNode = head;
        currentNode = currentNode.next;
        int count = 1;
        while (currentNode != head) {
            count++;
            currentNode = currentNode.next;
        }
        return count;
    }

    @Override
    public int getCountSpaces() {
        int count = getCountFloors();
        int sumOffices = 0;
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            sumOffices += currentNode.floor.getCountSpaces();
            currentNode = currentNode.next;
        }
        return sumOffices;
    }


    //Создайте метод получения общей площади помещений здания.
    public int getSumArea() {
        int sumArea = 0;
        int count = getCountFloors();
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            sumArea += currentNode.floor.getSumArea();
            currentNode = currentNode.next;
        }
        return sumArea;
    }

    @Override
    public int getSumCountRooms() {
        int sumRooms = 0;
        int count = getCountFloors();
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            sumRooms += currentNode.floor.getSumCountRooms();
            currentNode = currentNode.next;
        }
        return sumRooms;
    }

    @Override
    public Floor[] getFloors() {
        int count = getCountFloors();
        Floor[] officeFloors = new Floor[count];
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            officeFloors[i] = currentNode.floor;
            currentNode = currentNode.next;
        }
        return officeFloors;
    }


    @Override
    public Floor getFloor(int num) {

        if (num >= this.getCountFloors())
            throw new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        Node node = getNode(num);
        return node.floor;
    }


    @Override
    public Space getSpace(int num) {
        int tmpNum = 0;
        Node currentNode = head;
        int count = getCountFloors();
        for (int i = 0; i < count; ++i) {
            if (num < tmpNum + currentNode.floor.getCountSpaces())
                return currentNode.floor.getSpace(num - tmpNum);
            tmpNum += currentNode.floor.getCountSpaces();
            currentNode = currentNode.next;
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");

    }

    @Override
    public void changeSpace(int num, Space newSpace) {
        int tmpNum = 0;
        Node currentNode = head;
        int count = getCountFloors();
        for (int i = 0; i < count; ++i) {
            if (num < tmpNum + currentNode.floor.getCountSpaces()) {
                currentNode.floor.changeSpace(num - tmpNum, newSpace);
                return;
            }
            tmpNum += currentNode.floor.getCountSpaces();
            currentNode = currentNode.next;
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }

    @Override
    public void addSpace(int num, Space newSpace) {
        int tmpNum = 0;
        Node currentNode = head;
        int count = getCountFloors();
        for (int i = 0; i < count; ++i) {
            if (num < tmpNum + currentNode.floor.getCountSpaces()) {
                currentNode.floor.addSpace(num - tmpNum, newSpace);
                return;
            }
            tmpNum += currentNode.floor.getCountSpaces();
            currentNode = currentNode.next;
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }

    @Override
    public void eraseSpace(int num) {
        int tmpNum = 0;
        Node currentNode = head;
        int count = getCountFloors();
        for (int i = 0; i < count; ++i) {
            if (num < tmpNum + currentNode.floor.getCountSpaces()) {
                currentNode.floor.eraseSpace(num - tmpNum);
                return;
            }
            tmpNum += currentNode.floor.getCountSpaces();
            currentNode = currentNode.next;
        }
        throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
    }


    public void changeFloor(int num, Floor newFloor) {
        if (num >= this.getCountFloors())
            throw new FloorIndexOutOfBoundsException("FloorIndexOutOfBoundsException");
        Node node = getNode(num);
        node.floor = newFloor;
    }

    //Создайте метод getBestSpace() получения самого большого по площади офиса здания.
    public int getBestSpace() {
        int bestSpace = 0;
        Node currentNode = head;
        int count = getCountFloors();
        for (int i = 0; i < count; ++i) {
            if (bestSpace < currentNode.floor.getBestSpace()) bestSpace = currentNode.floor.getBestSpace();
            currentNode = currentNode.next;
        }
        return bestSpace;
    }


    //Создайте метод получения отсортированного по убыванию площадей массива офисов.
    public Space[] getSortedSpaces() {
        int countOffices = getCountSpaces();
        Space[] sorted = new Space[countOffices];

        for (int i = 0; i < countOffices; ++i) {
            sorted[i] = this.getSpace(i);
            if (sorted[i] == null) return null;
        }

        int size = sorted.length;
        for (int i = 0; i < size - 1; ++i) {
            int i_min = i;
            for (int j = i + 1; j < size; ++j) {
                if (sorted[j].getArea() < sorted[i_min].getArea()) i_min = j;
            }
            Space tmp = sorted[i];
            sorted[i] = sorted[i_min];
            sorted[i_min] = tmp;
        }

        return sorted;

    }

}
