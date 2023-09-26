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

    public boolean nextPlace(Place currentPlace){
        if(currentPlace.isWall()){
            return false;
        }

        this.place.vacate();
        currentPlace.occupe();

        this.place = currentPlace;
        return true;
    }

    // Sensores para ver oq esta na posicao indicada
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