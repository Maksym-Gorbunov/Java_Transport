package app;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller {
    Model model = new Model();
    @FXML
    Stage stage;
    @FXML
    ListView licensenumbersListView;
    @FXML
    TextArea carInfoTextArea;
    @FXML
    TableView carInfoTableView;
    @FXML
    TableColumn carInfoTableColumn1, carInfoTableColumn2;
    @FXML
    Button deleteBtn;
    @FXML
    Label totalCarsLabel, messageLabel;


    public void initialise() {
        model.populateCars();
        showAllCars();
        showSelectedCarInfo();
        showTotalCars();
        deleteBtn.setDisable(true);
        model.setText("");
        messageLabel.textProperty().bind(model.textProperty());
        carInfoTextArea.setEditable(false);
//        carInfoTextArea.setStyle("-fx-background-color: red;");
//        carInfoTextArea.setStyle("-fx-opacity: 1;");
        tableTest();
    }

    private void tableTest() {
        TableColumn licensenumberColumn = new TableColumn("Licensenumber");
        TableColumn makeColumn = new TableColumn("Make");
        TableColumn colorColumn = new TableColumn("Color");
        carInfoTableView.getColumns().addAll(licensenumberColumn, makeColumn, colorColumn);

//        carInfoTableView = new TableView();
//        licensenumberColumn.setCellValueFactory(new PropertyValueFactory<>("licensenumber"));

//        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));

//        colorColumn.setCellFactory(new PropertyValueFactory<>("color"));


        Car car = new Car("TTT032", "Ford", "Blue");
        carInfoTableView.getItems().add(car);

    }

    private void showTotalCars() {
        totalCarsLabel.setText("Total: " + model.getLicensenumbers().size());
    }

    public void refresh(){
        licensenumbersListView.setItems(null);
        showAllCars();
        showTotalCars();
    }

    //dont in use, can be as dimmer
    public void test(){
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
        task.setOnSucceeded(event -> carInfoTextArea.setOpacity(0.5));
    }

    //Listen after changes in ViewList
    private void showSelectedCarInfo() {
        licensenumbersListView.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
            if((v == null) || ((v != null) && (newValue != null))){
                carInfoTextArea.setText(model.getCarByLicencenumber(String.valueOf(newValue)));
                deleteBtn.setDisable(false);
            } else {
                deleteBtn.setDisable(true);
                carInfoTextArea.setText("");
            }
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showAllCars(){
        if(model.getLicensenumbers().size() > 0){
            licensenumbersListView.setItems(model.getLicensenumbersObservableList());
        }
    }

    public void deleteCar(){
        if(licensenumbersListView.getSelectionModel().getSelectedItem() != null){
            model.deleteCar(licensenumbersListView.getSelectionModel().getSelectedItem().toString());
            refresh();
        }
    }
}
