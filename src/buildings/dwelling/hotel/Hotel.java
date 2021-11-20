package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

import java.util.Map;


public class Hotel extends Dwelling {
    private final float[] coef = {0.25F, 0.5F, 1F, 1.25F, 1.5F};

    public Hotel(int floorsCount, int[] spacesCounts){
        super(floorsCount, spacesCounts);
    }

    public Hotel(Floor... dwellingFloors1){
        super(dwellingFloors1);
    }

    public int getCountStars() {
        int max = 0;
        for (int i = 0; i < getCountFloors(); ++i) {
            if (getFloor(i) instanceof HotelFloor) {
                if (max < ((HotelFloor) getFloor(i)).getCountStars())
                    max = ((HotelFloor) getFloor(i)).getCountStars();
            }
        }
        return max;
    }

    @Override
    public Space getBestSpace() {
        float max = 0;
        Space bestSpace = null;
        for (int i = 0; i < getCountFloors(); ++i) {
            if (getFloor(i) instanceof HotelFloor) {
                // умножаем на коэффициент
                if (max < getFloor(i).getBestSpace().getArea() * coef[((HotelFloor) getFloor(i)).getCountStars() - 1]) {
                    max = getFloor(i).getBestSpace().getArea() * ((HotelFloor) getFloor(i)).getCountStars();
                    bestSpace = getFloor(i).getBestSpace();
                }
            }
        }

        return bestSpace;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Hotel ").append("(").append(getCountStars()).append(", ").append(getCountFloors()).append(", ");
        for (int i = 0; i < getCountFloors(); ++i) {
            sb.append(getFloor(i).toString());
        }
        sb.append(")");
        return new String(sb);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Hotel)) return false;
        if (this.getCountFloors() != ((Hotel) object).getCountFloors()) return false;
        for (int i = 0; i < getCountFloors(); ++i) {
            if (!this.getFloor(i).equals(((Hotel) object).getFloor(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = getCountFloors();
        for (int i = 0; i < getCountFloors(); ++i) {
            result ^= getFloor(i).hashCode();
        }
        return result;
    }

}
