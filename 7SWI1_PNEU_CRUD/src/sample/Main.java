package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public void start(final Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/pneu.fxml"));
        primaryStage.setTitle("Pneuservis ALL-STAR");
        primaryStage.getIcons().add(new Image("sample/images/icon.png"));

        Scene scene = new Scene(root,1280,719);

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
