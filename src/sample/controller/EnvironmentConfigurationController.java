package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sample.model.entity.Location;
import sample.model.entity.Sensor;
import sample.utils.HibernateUtil;

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
        Sensor sensor = new Sensor(Integer.parseInt(locationCode.getText())
                ,locationSensorType.getValue().toString()
                ,Float.parseFloat(sensorMinRange.getText())
                ,Float.parseFloat(sensorMaxRange.getText()));
        location = new Location(Integer.parseInt(locationCode.getText()));
        //locationSensors.add(new Sensor(locationSensorType.getTypeSelector().toString()
                                //       ,Float.parseFloat(sensorMinRange.getText())
                                 //      ,Float.parseFloat(sensorMaxRange.getText())));

        clearTextFields();
        saveSensor(sensor);


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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(l);
            transaction.commit();
        }
        catch (Exception e){
            System.out.print(e.toString());
        }finally{
            session.close();
        }

    }
    public void saveSensor(Sensor sensor){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(sensor);
            transaction.commit();
        }
        catch (Exception e){
            System.out.print(e.toString());
        }finally{
            session.close();
        }
    }
}
