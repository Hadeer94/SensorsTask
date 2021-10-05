package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.model.entity.Location;
import sample.model.entity.Sensor;

public class LocationDetailsController {
    @FXML
    private TextField humiditySensorReading,lightSensorReading,tempSensorReading,locationState;
    @FXML
    private Button humiditySensorBtn,lightSensorBtn,tempSensorBtn;
    int minRange = 50;
    int maxRange = 200;
    Sensor humSensor,tempSensor,lightSensor;
    Location location;
    public void handleHumiditySensor(ActionEvent actionEvent) {
        if(humiditySensorBtn.getText().equals("Activate")){
            humSensor.setIsActivated(true);
            humiditySensorBtn.setText("Deactivate");
            // System.out.print("hum is pressed");

        }
        else{
            humiditySensorBtn.setText("Activate");
            humSensor.setIsActivated(false);
            humiditySensorReading.setText("");
            // humSensor.setReading(0);

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
            // humSensor.setReading(0);

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
            // humSensor.setReading(0);

        }
    }

}
