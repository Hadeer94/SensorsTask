package sample.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Sensor {
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "minRange")
    private double minRange;
    @Basic
    @Column(name = "maxRange")
    private double maxRange;
    @Basic
    @Column(name = "reading")
    private double reading;
    @Basic
    @Column(name = "isActivated")
    private boolean isActivated;
    @Id
    @Column(name = "sensorId")
    private int sensorId;
   // @ManyToOne(optional = false)
    //private Location locationSensors;
    //@Basic
    //@Column(name = "locationId")
   // private Short locationId;
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location locationByLocationId;

    public Sensor(int locationId ,String type,float minRange,float maxRange){
        this.locationByLocationId = new Location(locationId);
        this.type = type;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }

    public Sensor(){

    }

    public void setMinRange(Double minRange) {
        this.minRange = minRange;
    }

    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    public void setReading(Double reading) {
        this.reading = reading;
    }



    public void setSensorId(Short sensorId) {
        this.sensorId = sensorId;
    }


    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }


    public double getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double getMinRange() {
        return minRange;
    }

    public void setMinRange(float minRange) {
        this.minRange = minRange;
    }


    public double getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(float maxRange) {
        this.maxRange = maxRange;
    }

    public double read(){
        return Math.floor(Math.random()*((maxRange+10)-(minRange-10)+1)+(minRange-10));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Double.compare(sensor.minRange, minRange) == 0 &&
                Double.compare(sensor.maxRange, maxRange) == 0 &&
                Double.compare(sensor.reading, reading) == 0 &&
                isActivated == sensor.isActivated &&
                Objects.equals(type, sensor.type);
    }
/*
    @OneToOne
    public Location getLocationByRef2() {
        return locationByRef2;
    }

    public void setLocationByRef2(Location locationByRef2) {
        this.locationByRef2 = locationByRef2;
    }*/

    @Override
    public int hashCode() {

        return Objects.hash(type, minRange, maxRange, reading, isActivated);
    }





    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

   /* public Location getLocationSensors() {
        return locationSensors;
    }

    public void setLocationSensors(Location locationSensors) {
        this.locationSensors = locationSensors;
    }*/


    /*public Short getLocationId() {
        return locationId;
    }

    public void setLocationId(Short locationId) {
        this.locationId = locationId;
    }*/


    public Location getLocationByLocationId() {
        return locationByLocationId;
    }

    public void setLocationByLocationId(Location locationByLocationId) {
        this.locationByLocationId = locationByLocationId;
    }
}
