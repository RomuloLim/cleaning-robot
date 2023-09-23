package Robot;

import Scenario.Place;

public class Sensors {
    private boolean obstacle;
    private boolean dirty;
    private boolean wall;

    private Place place;

    public Sensors(Place place){
        this.place = place;
    }

    public boolean nextScenario(Place currentPlace){
        if(currentPlace.isWall()){
            return false;
        }

        this.place.vacate();
        currentPlace.occupe();

        this.place = currentPlace;
        return true;
    }

    public boolean isWall(Place place) {
        return place.isWall();
    }

    public boolean isDirty() {
        return place.isDirty();
    }

    public boolean isClear(Place place) {
        return place.isClear();
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}