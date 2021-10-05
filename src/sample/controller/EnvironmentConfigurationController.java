package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.model.entity.Location;
import sample.model.entity.Sensor;

import java.util.ArrayList;

public class EnvironmentConfigurationController {
    @FXML
    private GridPane addingLocationPane;
    @FXML
    private TextField locationCode,sensorMinRange,sensorMaxRange;
    @FXML
    private ComboBox locationSensorType;
    private Location location;
    private ArrayList<Sensor> locationSensors;
    public void handleAddingLocation(ActionEvent actionEvent) {
        addingLocationPane.setVisible(true);
    }

    public void handelOkButton(ActionEvent actionEvent) {
        locationSensors = new ArrayList<>();
        location = new Location(Integer.parseInt(locationCode.getText()));
        locationSensors.add(new Sensor(locationSensorType.getTypeSelector().toString()
                                       ,Float.parseFloat(sensorMinRange.getText())
                                       ,Float.parseFloat(sensorMaxRange.getText())));

        clearTextFields();

    }

    public void handelCloseButton(ActionEvent actionEvent) {
        addingLocationPane.setVisible(false);
        location.setSensorsByLocationId(locationSensors);
        locationCode.setText("");
        clearTextFields();
        saveLocation(location);
    }
    public void clearTextFields(){
        sensorMaxRange.setText("");
        sensorMinRange.setText("");

    }
    public void saveLocation(Location l){
        //save to database
    }
}
