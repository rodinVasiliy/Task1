package buildings;

import Exceptions.SpaceIndexOutOfBoundsException;

import java.io.Serializable;

public class OfficeFloor implements Floor, Serializable, Cloneable {

    private static class Node implements Serializable, Cloneable {
        private Space office;
        private Node next;

        public Node(Space newOffice) {
            // office = newOffice;
            office = new Office(newOffice.getArea(), newOffice.getCountRooms());
            next = null;
        }

        public Node(Node rhs) {
            office = new Office();
            next = null;
            office.setCountRooms(rhs.office.getCountRooms());
            office.setSpace(rhs.office.getArea());
        }

        public Node() {
            office = new Office();
            next = null;
        }


        // TODO: удалить
        @Override
        public Node clone() {
            try {
                Node clone = (Node) super.clone();
                // clone.office = (Space) office.clone();
                clone.next = (Node) next.clone();
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }


    private Node head;

    @Override
    public Object clone() {
        OfficeFloor floor = new OfficeFloor();
        for(int i = 0; i < getCountSpaces(); ++i){
            floor.addNode(i, new Node(getNode(i)));
        }
        try {
            Object clone = super.clone();
            ((OfficeFloor)clone).head = floor.head;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public int getCountSpaces() {
        if (head == null) return 0;
        Node tmpNode = head;
        tmpNode = tmpNode.next;
        if (tmpNode == head) return 1;
        int count = 1;
        while (tmpNode != head) {
            count++;
            tmpNode = tmpNode.next;
        }
        return count;
    }

    public OfficeFloor(int count) {
        if (count == 0) {
            head = null;
            return;
        }
        if (count == 1) {
            head = new Node();
            head.next = head;
            return;
        }

        head = new Node();
        Node tmpHead = head;
        for (int i = 0; i < count - 1; ++i) {
            head.next = new Node();
            head = head.next;
        }
        head.next = tmpHead;
        head = tmpHead;
    }

    public OfficeFloor(Space... offices) {
        if (offices.length == 0) {
            head = null;
            return;
        }
        if (offices.length == 1) {
            head = new Node(offices[0]);
            head.next = head;
        } else {
            head = new Node(offices[0]);
            Node tmpHead = head;
            for (int i = 0; i < offices.length - 1; ++i) {
                head.next = new Node(offices[i + 1]);
                head = head.next;
            }
            head.next = tmpHead;
            head = tmpHead;
        }
    }

    // надо ли кидать исключение?
    private Node getNode(int num) {
        //if (num > count)
        Node currentNode = head;
        for (int i = 0; i < num; ++i) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private void addNode(int num, Node node) {
        int count = getCountSpaces();
        if (count == 0) {
            head = node;
            head.next = node;
            return;
        }
        if (num == 0) {
            Node currentNode = head;
            for (int i = 0; i < count; ++i) {
                currentNode = currentNode.next;
            }
            currentNode.next = node;
            currentNode.next.next = head;
            head = currentNode.next;
            return;
        }
        Node currentNode = head;
        for (int i = 0; i < num - 1; ++i) {
            currentNode = currentNode.next;
        }
        Node tmpNode = currentNode.next;
        currentNode.next = node;
        node.next = tmpNode;
    }

    private void deleteNode(int num) {
        int count = getCountSpaces();
        if (num == 0) {
            if (count == 1) {
                head = null;
                return;
            } else {
                Node currentNode = head;
                for (int i = 0; i < count; ++i) {
                    currentNode = currentNode.next;
                }
                currentNode.next = currentNode.next.next;
                head = head.next;
                return;
            }
        }
        Node currentNode = head;
        for (int i = 0; i < num - 1; ++i) {
            currentNode = currentNode.next;
        }
        currentNode.next = currentNode.next.next;
    }


    public float getSumArea() {
        int count = getCountSpaces();
        Node currentNode = head;
        int sumSpace = 0;
        for (int i = 0; i < count; ++i) {
            sumSpace += currentNode.office.getArea();
            currentNode = currentNode.next;
        }
        return sumSpace;
    }

    @Override
    public int getSumCountRooms() {
        int count = getCountSpaces();
        Node currentNode = head;
        int sumNum = 0;
        for (int i = 0; i < count; ++i) {
            sumNum += currentNode.office.getCountRooms();
            currentNode = currentNode.next;
        }
        return sumNum;
    }

    @Override
    public Space[] getSpaces() {
        int count = getCountSpaces();
        Space[] offices = new Space[count];
        Node currentNode = head;
        for (int i = 0; i < count; ++i) {
            offices[i] = currentNode.office;
            currentNode = currentNode.next;
        }
        return offices;
    }

    @Override
    public Space getSpace(int num) {
        if (num >= this.getCountSpaces())
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        Node node = getNode(num);
        return node.office;
    }

    @Override
    public void setSpace(int num, Space newSpace) {
        if (num >= this.getCountSpaces())
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        Node node = getNode(num);
        node.office = newSpace;
    }


    @Override
    public void addSpace(int num, Space newSpace) {
        if (num > this.getCountSpaces()) throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        addNode(num, new Node(newSpace));
    }

    @Override
    public void eraseSpace(int num) {

        if (num >= this.getCountSpaces())
            throw new SpaceIndexOutOfBoundsException("SpaceIndexOutOfBoundsException");
        deleteNode(num);
    }

    public float getBestSpace() {
        int count = getCountSpaces();
        Node currentNode = head;
        float bestSpace = 0;
        for (int i = 0; i < count; ++i) {
            if (bestSpace < currentNode.office.getArea()) bestSpace = currentNode.office.getArea();
            currentNode = currentNode.next;
        }
        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Office Floor ").append("(").append(getCountSpaces()).append(", ");
        for (int i = 0; i < getCountSpaces() - 1; ++i) {
            sb.append(getSpace(i).toString()).append(",");
        }
        sb.append(getSpace(getCountSpaces() - 1));
        sb.append(")");
        return new String(sb);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof OfficeFloor officeFloor)) {
            return false;
        }
        if (this.getCountSpaces() != officeFloor.getCountSpaces()) return false;

        for (int i = 0; i < getCountSpaces(); ++i) {
            if (!this.getSpace(i).equals(officeFloor.getSpace(i))) return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        int result = getCountSpaces();
        for (int i = 0; i < getCountSpaces(); ++i) {
            result ^= this.getSpace(i).hashCode();
        }
        return result;
    }


}
