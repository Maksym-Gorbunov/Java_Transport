package app;

import java.util.ArrayList;
import java.util.List;

public class aaa {
    public static void main(String[] args) {
        Model model = new Model();
        model.populateCars();
        List<String> licensenumbers = new ArrayList<>(model.getCars().keySet());

        System.out.println(licensenumbers);
//        List<String> licensenumbers = (List<String>) model.getCarsObservable().keySet();
    }
}



//    private void showTotalCars() {
//        totalCarsLabel.setText(String.valueOf(test.size()));
//        model.textProperty().bindBidirectional(new SimpleIntegerProperty(testObservable.size()),
//                new NumberStringConverter());
//         model.textProperty().bind(totalCarsLabel.textProperty());
////         model.textProperty().bindBidirectional(test.size());
//        totalCarsLabel.setText("Total: "+String.valueOf(test.size()));

//         model.textProperty().bindBidirectional(new SimpleIntegerProperty(test.size()).asString());
//        totalCarsLabel.textProperty().bind(model.textProperty(testObservable.size()));
//    }




//    public void addObservableListListener(){
//        testObservable.addListener(new ListChangeListener<String>() {
//            @Override
//            public void onChanged(Change<? extends String> change) {
//                System.out.println("LISTENER");
////                showAllCars();
//            }
//        });
//    }

//    public void addObservableListListener(){
//        model.getCarsObservable().addListener(new MapChangeListener<String, Car>() {
//            @Override
//            public void onChanged(Change<? extends String, ? extends Car> change) {
//                showAllCars();
//            }
//        });
//    }
