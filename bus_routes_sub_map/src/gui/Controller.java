package gui;

import domain.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import repository.Repository;
import service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Controller {
    private Service service;

    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listView2;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBox2;
    @FXML
    private Button button;
    @FXML
    private Button button2;
    @FXML
    private TextField textField;

    @FXML
    private Text text;

    public Controller(Service service) {
        this.service = service;
    }

    @FXML
    protected void populateList() {
        try {
            ArrayList<Route> Routes = service.getAll();
            var sortedRoutes = Routes.stream().sorted(Comparator.comparing(report-> report.getSourceCity())).collect(Collectors.toList());
            var sortedRoutes2 = sortedRoutes.stream().sorted(Comparator.comparing(report-> report.getDepartureTime())).collect(Collectors.toList());
            for (Route Route : sortedRoutes2) {
                listView.getItems().add(Route.toString());
            }
        }catch (Exception exception) { exception.printStackTrace();}
    }

    @FXML
    protected void populateComboBox(){
        ArrayList<String> descriptions = new ArrayList<>();
        try {
            ArrayList<Route> Routes = service.getAll();
            for(Route route:Routes)
            {
                String desc = route.getSourceCity();
                if(!descriptions.contains(desc)) descriptions.add(desc);
            }
            comboBox.getItems().addAll(descriptions);
        }
        catch (Exception exception) {exception.printStackTrace();}
    }

    @FXML
    protected void populateComboBox2(){
        ArrayList<String> descriptions = new ArrayList<>();
        String chosenCity = comboBox.getValue();
        try {
            ArrayList<Route> Routes = service.getAll();
            for(Route route:Routes)
            {
                if(route.getSourceCity().equals(chosenCity))
                {
                    String desc = route.getDestCity();
                    if(!descriptions.contains(desc)) descriptions.add(desc);
                }
            }
            comboBox2.getItems().addAll(descriptions);
        }
        catch (Exception exception) {exception.printStackTrace();}
    }


    @FXML
    void sortComboBox(ActionEvent event) {
        listView.getItems().clear();
        String filter_term = comboBox.getValue();
        try {
            ArrayList<Route> Routes = service.getAll();
            var filteredRoutes = Routes.stream().filter(w -> w.getSourceCity().startsWith(filter_term)).collect(Collectors.toList());
            for (Route route : filteredRoutes) {
                listView.getItems().add(route.toString());
            }
            comboBox2.getItems().clear();
            populateComboBox2();
        }catch (Exception exception) {exception.printStackTrace();}
    }

    @FXML
    void sortComboBox2(ActionEvent event) {
        listView2.getItems().clear();
        String filter_term1 = comboBox.getValue();
        String filter_term2 = comboBox2.getValue();
        try {
            ArrayList<Route> routes = service.getAll();
            var filteredRoutes = routes.stream().filter(w -> w.getSourceCity().startsWith(filter_term1)).collect(Collectors.toList());
            var filteredRoutes2 = filteredRoutes.stream().filter(w -> w.getDestCity().startsWith(filter_term2)).collect(Collectors.toList());
            for (Route route : filteredRoutes2) {
                listView2.getItems().add(route.toString());
            }
        }catch (Exception exception) {exception.printStackTrace();}

    }

    @FXML
    void updateSeats(ActionEvent event) throws IOException {
        String[] descr = listView2.getSelectionModel().getSelectedItem().split("'");
        String sourceCity = descr[1];
        String destCity = descr[3];
        int departure = Integer.parseInt(descr[5]);
        int seats = Integer.parseInt(descr[9]);
        int price = Integer.parseInt(descr[11]);
        listView2.getItems().clear();

        String bs = textField.getText();
        int boughtseats = Integer.parseInt(bs);
        int updatedSeats = seats - boughtseats;

        service.UpdateSchema(updatedSeats, sourceCity,destCity,departure);
        sortComboBox2(event);

        int finalprice = price * boughtseats;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gui/ui2.fxml"));

        Controller controller = new Controller(service);
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 679, 338);
        stage.setTitle("Client Window");
        stage.setScene(scene);
        stage.show();
        text.setText(String.valueOf(finalprice));
    }

    @FXML
    void openWindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/gui/ui2.fxml"));

        Controller controller = new Controller(service);
        fxmlLoader.setController(controller);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 679, 338);
        stage.setTitle("Client Window");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize(){
        try {
//            service.createSchema();
//            service.AddinSchema();
            populateList();
            populateComboBox();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
