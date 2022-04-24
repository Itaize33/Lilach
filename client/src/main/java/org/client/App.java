
package org.client;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;
        import java.util.Scanner;
        import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Client client;

    @Override
    public void start(Stage stage) throws IOException {
        //Add here title set (Lilach Store).
        scene = new Scene(loadFXML("StoreSkeleton"), 1000, 600);
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException{
        launch();
        Client client = new Client("localhost", 3000);
        client.openConnection();
        Scanner s = new Scanner(System.in);
        String str;
        while(true){
            str = s.nextLine();
            client.sendToServer(str);

        }
    }

}