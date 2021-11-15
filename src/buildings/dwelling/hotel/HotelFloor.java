package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {
    private final int COUNT_STARS = 1;
    private int countStars = COUNT_STARS;

    HotelFloor(int countSpaces) {
        super(countSpaces);
    }

    HotelFloor(Space... space) {
        super(space);
    }

    public int getCountStars() {
        return countStars;
    }

    public void setCountStars(int countStars) {
        this.countStars = countStars;
    }
}
