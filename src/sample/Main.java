package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sample.model.entity.Location;
import sample.model.entity.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;
        primaryStage.setTitle("Environment");


       /* SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Sensor.class).buildSessionFactory();
        Session session = factory.getCurrentSession();
        Sensor sensor = new Sensor("Humidity",50,80);
        try{
            session.beginTransaction();
            session.save(sensor);
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.print(e.toString());
        }finally{
            session.close();
        }*/
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration().configure();
        sessionFactory=configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
       // Sensor sensor = new Sensor("Humidity",50,80);
        //sensor.setSensorId("1");
        try{
           List<Sensor> sensors = session.createQuery("from Sensor s where s.locationByLocationId.locationId=1").list();
           for(int i=0;i<sensors.size();i++){
               System.out.println(sensors.get(i).getType());
           }
           /**/
           // session.getTransaction().commit();
        }catch (Exception e){
            System.out.print(e.toString());
        }finally{
            session.close();
        }
        ArrayList<Location> locations = new ArrayList<Location>();
       // locations.add(new Location(new Sensor("Humidity",45,67),222));
        /*if(Controller.locations.size()!=0){

            System.out.print("main size "+Controller.locations.size());
            root = FXMLLoader.load(getClass().getResource("launchingEnvironmentPage.fxml"));
        }else{
             root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        }*/
        root = FXMLLoader.load(getClass().getResource("layouts/environmentConfigurationPage.fxml"));
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
