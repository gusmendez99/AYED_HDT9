import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class that shows the JavaFX stage
 * @author Gustavo Méndez
 * @version 1.0
 * @since 21/3/2019
 */
public class Main extends Application {

    /**
     * For init the app
     * @param args the default instance args of the Main
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * For show the JavaFX Stage
     * @param primaryStage unique JavaFX Stage displayed on screen
     */
    @Override
    public void start(Stage primaryStage) {
        TreeTranslatorView treeTranslatorView = new TreeTranslatorView();
        treeTranslatorView.show(primaryStage);
    }


}
