package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;

import java.util.Map;


public class Hotel extends Dwelling {
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
    public float getBestSpace(){
        float best = 0;

        return 0;
    }
}
