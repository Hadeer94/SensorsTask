package sample.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Location {
    @Basic
    @Column(name = "state")
    private String state;

   // private ArrayList<Sensor> locationSensors;
    @Id
    @Column(name = "locationId")
    private int locationId;
    @OneToMany(mappedBy = "locationByLocationId")
    private Collection<Sensor> sensorsByLocationId;

    public Location(ArrayList<Sensor> locationSensors,int locationId){
       // this.locationSensors = locationSensors;
        this.locationId = locationId;

    }
    public Location(int locationId){
        this.locationId = locationId;

    }
    public Location(){

    }

   /* @Basic
    @Column(name = "code")
    public int getCode() {
        return code;
    }*/

    public void setLocationId(Short locationId) {
        this.locationId = locationId;
    }

   /* public ArrayList<Sensor> getlocationSensors() {
        return locationSensors;
    }

    public void setLocationSensors(ArrayList<Sensor> locationSensors) {
        this.locationSensors = locationSensors;
    }*/


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId &&
                Objects.equals(state, location.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(locationId, state);
    }


    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }


    public Collection<Sensor> getSensorsByLocationId() {
        return sensorsByLocationId;
    }

    public void setSensorsByLocationId(Collection<Sensor> sensorsByLocationId) {
        this.sensorsByLocationId = sensorsByLocationId;
    }
}
