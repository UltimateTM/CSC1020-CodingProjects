package week2;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;


public class JavaFXIntro extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        Parent root = new VBox();
        final int width = 400;
        final int height = 400;
        Scene scene = new Scene(root);
        Button b = new Button("Click me");
        TextField tf = new TextField();
        // Panes have a list called children where all the components go 
        root.getChildrenUnmodifiable().add(b);
        root.getChildrenUnmodifiable().add(tf);
        stage.setScene(scene);
        stage.setTitle("JavaFX Intro");
        stage.show();
    }
}
