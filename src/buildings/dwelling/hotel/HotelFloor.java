package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.DwellingFloor;

import java.util.Objects;

public class HotelFloor extends DwellingFloor {
    private final int COUNT_STARS = 1;

    private int countStars = COUNT_STARS;

    public HotelFloor(int countSpaces) {
        super(countSpaces);
    }

    public HotelFloor(Space... space) {
        super(space);
    }

    public int getCountStars() {
        return countStars;
    }

    public void setCountStars(int countStars) {
        this.countStars = countStars;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Hotel Floor ").append("(").append(getCountStars()).append(", ").append(getCountSpaces()).append(", ");
        for (int i = 0; i < getCountSpaces() - 1; ++i) {
            sb.append(getSpace(i).toString()).append(",");
        }
        sb.append(getSpace(getCountSpaces() - 1));
        sb.append(")");
        return new String(sb);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof HotelFloor)) return false;
        if (this.getCountSpaces() != ((HotelFloor) object).getCountSpaces()) return false;
        for (int i = 0; i < getCountSpaces(); ++i) {
            if (!this.getSpace(i).equals(((HotelFloor) object).getSpace(i))) return false;
        }
        return true;
    }



    @Override
    public int hashCode() {
        int result = getCountSpaces() ^ getCountStars();
        for (int i = 0; i < getCountSpaces(); ++i) {
            result ^= getSpace(i).hashCode();
        }
        return result;
    }

}
