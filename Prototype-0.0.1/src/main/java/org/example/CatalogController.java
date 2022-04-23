package org.example;


/**
 * Sample Skeleton for 'Catalog.fxml' Controller Class
 */

    import java.io.IOException;
    import java.net.URL;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.ResourceBundle;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.image.Image;
    import javafx.scene.layout.FlowPane;


public class CatalogController extends Controller{

    //All products in the catalog.
    private List<Product> products = new ArrayList<Product>();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mainPane"
    private FlowPane mainPane; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'Catalog.fxml'.";

        createProducts();
    }

    public void setCatalog(StoreSkeleton skeleton){

        this.setSkeleton(skeleton);
        try{
            //mainPane.getChildren().clear();
            for (Product product : products) {
                displayPreMadeProduct(product);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void createProducts(){
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        products.get(0).setImage(new Image("C:\\Users\\tahel\\Lilach\\Prototype-0.0.1\\src\\main\\resources\\Images\\pexels-jonas-kakaroto-736230.jpg", 140, 140, false, false ));
        products.get(0).setName("Rakefet");
        products.get(0).setPrice(55);
        products.get(1).setImage(new Image("C:\\Users\\tahel\\Lilach\\Prototype-0.0.1\\src\\main\\resources\\Images\\pexels-pixabay-60597.jpg", 140, 140, false, false ));
        products.get(1).setName("Chrysanthemum");
        products.get(1).setPrice(45);
        products.get(2).setImage(new Image("C:\\Users\\tahel\\Lilach\\Prototype-0.0.1\\src\\main\\resources\\Images\\photo-1604085572504-a392ddf0d86a.jpg", 140, 140, false, false ));
        products.get(2).setName("Sunflower");
        products.get(2).setPrice(555);

        for (int i = 1 ; i < 5 ; i++){
            products.get(2+i).setImage(new Image("C:\\Users\\tahel\\Lilach\\Prototype-0.0.1\\src\\main\\resources\\Images\\tahelStupid" + i + ".jpg", 140, 140, false, false ));
            products.get(2 + i).setName("tahel Stupid" + i);
            products.get(2 + i).setPrice(i * 27);
        }
    }

    /**
     *
     * @param product
     * @throws IOException
     * Function adding instance of pre-made product to the screen.
     * Note to self: VERY IMPORTANT to load before using the "getController" method (else you'll get null).
     */
    protected void displayPreMadeProduct(Product product) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product.fxml"));
        mainPane.getChildren().add(fxmlLoader.load());  //Adds new product pane to the screen.
        ProductController controller = fxmlLoader.getController();
        controller.setSkeleton(this.getSkeleton());
        controller.setProduct(product);
    }

}
