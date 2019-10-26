package FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TPIntegradorIAR2019 extends Application
{
    
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setTitle("Trabajo Practico Integrador - Inteligencia Artificial 2019");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args)
//    {
//        launch(args);
//    }
//    
}
