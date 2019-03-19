package app;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Model {
    private Map <String, Car> cars = new HashMap();
    private ObservableMap <String, Car> carsObservable = FXCollections.observableMap(cars);
    private List<String> licensenumbers  = new ArrayList<>(cars.keySet());
    private ObservableList<String> licensenumbersObservableList = FXCollections.observableList(licensenumbers);
    private DBHelper db = new DBHelper();
    private StringProperty text;
    private BooleanProperty disabled;

    public Model() {
        text = new SimpleStringProperty();
        disabled = new SimpleBooleanProperty();
    }


    public void populateCars(){
        for(Car car : db.selectAll()){
            cars.put(car.getLicensenumber(), car);
        }
        licensenumbers.clear();
        licensenumbers  = new ArrayList<String>(cars.keySet());
        licensenumbersObservableList = FXCollections.observableList(licensenumbers);
    }

    public String getCarByLicencenumber(String licensenumber) {
        if(cars.containsKey(licensenumber)){
            return cars.get(licensenumber).toString();
        }
        return null;
    }

    public void deleteCar(String licensenumber) {
        if(cars.containsKey(licensenumber)){
            cars.remove(licensenumber);
            licensenumbers.remove(licensenumber);
            setText(licensenumber + " was deleted successfully");
        } else {
            setText(licensenumber + " not found");
        }
    }

    // Property
    public boolean isDisabled() {
        return disabled.get();
    }
    public BooleanProperty disabledProperty() {
        return disabled;
    }
    public void setDisabled(boolean disabled) {
        this.disabled.set(disabled);
    }
    public final String getText() {
        return text.get();
    }
    public void setText(String s) {
        text.setValue(s);
        Task<Label> task = new Task<Label>() {
            @Override
            protected Label call(){
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        task.setOnSucceeded(event -> text.setValue(""));
    }
    public StringProperty textProperty() {
        return text;
    }

    // Getters
    public Map<String, Car> getCars() {
        return cars;
    }
    public ObservableMap<String, Car> getCarsObservable() {
        return carsObservable;
    }
    public List<String> getLicensenumbers(){
        return licensenumbers;
    }
    public ObservableList getLicensenumbersObservableList(){
        return licensenumbersObservableList;
    }
}
