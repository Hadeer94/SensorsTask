package sample.controller;

import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sample.model.entity.Location;
import sample.model.entity.Sensor;
import javafx.fxml.Initializable;
import sample.utils.HibernateUtil;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class LaunchingEnvironment extends Thread implements Initializable {

    @FXML
    private TextField humiditySensorReading,lightSensorReading,tempSensorReading,locationState;
    @FXML
    private Button humiditySensorBtn,lightSensorBtn,tempSensorBtn;
    int minRange = 50;
    int maxRange = 200;
    Sensor humSensor,tempSensor,lightSensor;
    Location location;
    @FXML
    private ComboBox environmentLocations;
    @FXML
    private GridPane locationDetailsPane;
    @FXML
    private VBox sensorsTypes,sensorsValues;
    List<Location> locations;
    List<Sensor> locationSensors;

    @Override
    public void initialize(URL location1, ResourceBundle resources) {

        //get locations from database
        Session session = HibernateUtil.getSessionFactory().openSession();

        try{
            locations = session.createQuery("from Location ").list();
            for(Location location:locations){
                environmentLocations.getItems().add(location.getLocationId());

            }


        }catch (Exception e){
            System.out.print(e.toString());
        }finally{
            session.close();
        }





        humSensor = new Sensor(1,"Humidity",49,89);
        tempSensor = new Sensor(5,"Temperature",49,89);
        lightSensor = new Sensor(5,"Light",49,89);
        location  = new Location();
        ArrayList<Sensor> arrayList = new ArrayList<>();
        arrayList.add(humSensor);
        arrayList.add(tempSensor);
        arrayList.add(lightSensor);
        location.setSensorsByLocationId(arrayList);
        this.start();
    }
    @Override
    public void run() {

        while(true){
            try{Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println(e);
            }
            getSensorsReadings(minRange,maxRange);
            checkAndUpdateLocationState();

        }
    }

    public void checkAndUpdateLocationState(){
        for(Sensor sensor : location.getSensorsByLocationId()){
            if(sensor.getIsActivated()){
                if(sensor.getReading()>maxRange||sensor.getReading()<minRange){
                    locationState.setText("Abnormal");
                    location.setState("Abnormal");
                }else{
                    locationState.setText("Normal");
                    location.setState("Normal");
                }
            }
        }


    }
    public void getSensorsReadings(float minRange,float maxRange){
        if(humSensor.getIsActivated()){
            humSensor.setReading((float) Math.floor(Math.random()*((maxRange+10)-(minRange-10)+1)+(minRange-10)));
            humiditySensorReading.setText(String.valueOf(humSensor.getReading()));
        }
        if(lightSensor.getIsActivated()){
            lightSensor.setReading((float) Math.floor(Math.random()*((maxRange+10)-(minRange-10)+1)+(minRange-10)));
            lightSensorReading.setText(String.valueOf(Math.floor(Math.random()*((maxRange+10)-(minRange-10)+1)+(minRange-10))));
        }
        if(tempSensor.getIsActivated()){
            tempSensor.setReading((float) Math.floor(Math.random()*((maxRange+10)-(minRange-10)+1)+(minRange-10)));
            tempSensorReading.setText(String.valueOf(Math.floor(Math.random()*((maxRange+10)-(minRange-10)+1)+(minRange-10))));
        }



    }

    public void handleHumiditySensor(ActionEvent actionEvent) {
        if(humiditySensorBtn.getText().equals("Activate")){
            humSensor.setIsActivated(true);
            humiditySensorBtn.setText("Deactivate");

        }
        else{
            humiditySensorBtn.setText("Activate");
            humSensor.setIsActivated(false);
            humiditySensorReading.setText("");

        }

    }

    public void handleLightSensor(ActionEvent actionEvent) {
        if(lightSensorBtn.getText().equals("Activate")){
            lightSensor.setIsActivated(true);
            lightSensorBtn.setText("Deactivate");

        }
        else{
            lightSensorBtn.setText("Activate");
            lightSensor.setIsActivated(false);
            lightSensorBtn.setText("");

        }
    }

    public void handleTempSensor(ActionEvent actionEvent) {
        if(tempSensorBtn.getText().equals("Activate")){
            tempSensor.setIsActivated(true);
            tempSensorBtn.setText("Deactivate");

        }
        else{
            tempSensorBtn.setText("Activate");
            tempSensor.setIsActivated(false);
            tempSensorBtn.setText("");

        }
    }


    public void viewLocationDetails(ActionEvent actionEvent) {
        locationDetailsPane.setVisible(true);
        int locationId = Integer.parseInt(environmentLocations.getValue().toString());
        //get location's sensors from database
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            locationSensors = session.createQuery("from Sensor s where s.locationByLocationId.locationId="+locationId).list();

            for(Sensor sensor:locationSensors){
            }

        }catch (Exception e){
            System.out.print(e.toString());
        }finally{
            session.close();
        }

    }
}
