package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.model.entity.Location;
import sample.model.entity.Sensor;



import java.util.ArrayList;

public class Controller {
    public static ArrayList<Location> locations = new ArrayList<Location>();
    @FXML
    private TextField locationCode,sensorMinRange,sensorMaxRange;
    @FXML
    private ComboBox locationSensorType;
    public void handelOkButton(javafx.event.ActionEvent actionEvent) {

        Location location = new Location();
       // location.setCode(Integer.parseInt(locationCode.getText()));
        Sensor sensor = new Sensor();
        sensor.setType(locationSensorType.getTypeSelector());
        sensor.setMinRange(Float.parseFloat(sensorMinRange.getText()));
        sensor.setMaxRange(Float.parseFloat(sensorMaxRange.getText()));
      //  location.setSensor(sensor);
        locations.add(location);
        locationCode.setText("");
        sensorMaxRange.setText("");
        sensorMinRange.setText("");

        System.out.print(locations);
    }
}
