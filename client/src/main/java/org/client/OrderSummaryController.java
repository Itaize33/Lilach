package org.client;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.entities.CustomMadeProduct;
import org.entities.Order;
import org.entities.PreMadeProduct;

public class OrderSummaryController extends Controller {
    private Order order;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Text delivery_date;

    @FXML
    private Text order_time;

    @FXML
    private Text store;

    @FXML
    private URL location;

    @FXML
    private Button cancelOrder;

    @FXML
    private Text price;

    @FXML
    private VBox vbox;

    @FXML
    void cancel(ActionEvent event) {
        if (this.alertMsg("Cancel", "cancel your order", false)) {
            LinkedList<Object> msg = new LinkedList<Object>();
            msg.add("#DELETEORDER"); //get stores from db
            msg.add(order.getId());
            App.client.setController(this);//TODO maybe remove
            try {
                App.client.sendToServer(msg); //Sends a msg contains the command and the current controller
            } catch (IOException e) {
                e.printStackTrace();
            }
            App.client.storeSkeleton.changeCenter("SummaryOrders");
        }
    }

    @FXML
    void initialize() {
        assert cancelOrder != null : "fx:id=\"cancelOrder\" was not injected: check your FXML file 'OrderSummary.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'OrderSummary.fxml'.";


    }


    public void setOrder(Order order) {
        this.order=order;
        price.setText("Price: " + Integer.toString(order.getPrice()));
        order_time.setText("Order Time: " + order.getOrderTime());
        delivery_date.setText("Delivery Time: " + order.getDeliveryDate().toString());

        if(order.getDelivery() == Order.Delivery.SELF_SHIPPING && order.getStore()!= null)
            store.setText("Store:" + order.getStore().getName());
        else
            store.setText("");
        if(order.isDelivered()== Order.Status.CANCELED){
            cancelOrder.setDisable(true);
            cancelOrder.setText("Canceled");
        }

        OrderSummaryController orderSummaryController = this;
        for (PreMadeProduct product : order.getPreMadeProducts()) {
            try {
                displayPreProduct(product, orderSummaryController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (CustomMadeProduct product : order.getCustomMadeProducts()) {
            try {
                displayCustomProduct(product, orderSummaryController);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void displayCustomProduct(CustomMadeProduct product, OrderSummaryController orderSummaryController) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("SummeryCustomProduct.fxml"));
        vbox.getChildren().add(fxmlLoader.load());  //Adds new product pane to the screen.
        SummeryCustomProductController controller = fxmlLoader.getController();
        controller.setSkeleton(this.getSkeleton());
        controller.setProduct(product);
    }

    private void displayPreProduct(PreMadeProduct product, OrderSummaryController orderSummaryController) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("SummeryPreProduct.fxml"));
        vbox.getChildren().add(fxmlLoader.load());  //Adds new product pane to the screen.
        SummeryPreProductController controller = fxmlLoader.getController();
        controller.setSkeleton(this.getSkeleton());
        controller.setProduct(product);
    }
}