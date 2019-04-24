import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class that manages the operations between the user interface and the logic of the program
 * manages the JavaFx stages and operates in the BinarySearchTree instance to feed the user
 * @author Gustavo Méndez
 * @author Luis Urbina
 * @version 1.0
 * @since  21/03/2019
 */
public class TreeTranslatorView {

    /**
     * TextAreas for show the results
     */
    private TextArea inputTextArea;
    private TextArea outputTextArea;
    private FileChooser fileChooser;
    private ComboBox<String> treeTypesComboBox;
    /**
     * Splay and RB Trees
     */
    private BSTRedBlackTree<String, String> redBlackDictionary;
    private BSTSplayTree<String, String> splayDictionary;
    /**
     * Control variables
     */
    private boolean isDictionaryLoaded, isTextToTranslateLoaded, isSplayTreeSelected = false;

    /**
     * Show the stage
     * @param stage the stage to show in the current context
     */
    public void show(Stage stage) {


        inputTextArea = new TextArea("");
        outputTextArea = new TextArea("...");
        fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt, *.dic")
        );

        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));

        //Init list of trees
        treeTypesComboBox = new ComboBox<>();
        treeTypesComboBox.getItems().add(TreeFactory.SPLAY_TREE);
        treeTypesComboBox.getItems().add(TreeFactory.RED_BLACK_TREE);

        //Initialized as true, cause Splay Tree will be the default tree
        isSplayTreeSelected = true;
        treeTypesComboBox.getSelectionModel().selectFirst();


        BorderPane border = new BorderPane();
        HBox hbox = addHBox(stage);
        border.setTop(hbox);
        border.setLeft(addVBox());

        Scene scene = new Scene(border, 800, 600);
        stage.setTitle("Translator");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * To return a HBox with two buttons for read file and clear TextArea
     *
     * @param stage Stage of JavaFX where we're gonna render the UI
     * @return a filled HBox to add to the UI
     */
    public HBox addHBox(Stage stage) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(6);
        hbox.setStyle("-fx-background-color: #455a64;");

        Button buttonLoadTextToTranslate = new Button("Load text to translate...");
        buttonLoadTextToTranslate.setPrefSize(200, 20);
        buttonLoadTextToTranslate.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(selectedFile));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        inputTextArea.appendText("\n" + text);
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TreeTranslatorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TreeTranslatorView.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TreeTranslatorView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //TODO: Show a dialog with successful message
                isTextToTranslateLoaded = true;

                //Dictionary and/or text haven't been loaded
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Text file loaded!");
                alert.setHeaderText("Text file loaded successfully");
                alert.setContentText("Try to translate your text...");
                alert.showAndWait();

            }
        });

        //Button for load the text file
        Button buttonLoadDictionary = new Button("Load dictionary");
        buttonLoadDictionary.setPrefSize(180, 20);
        buttonLoadDictionary.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {

                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(selectedFile));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                       /* if(text.charAt(0) == '(')
                        {
                            text = text.substring(1);
                        }
                        if(text.charAt(text.length() - 1) == ')')
                        {
                            text = text.substring(0, text.length() - 1);
                        }
                        String[] temp = text.split(",");
                        if(temp.length > 1)
                        {
                            //Create a new association with the value of the temp[0] = word in english
                            //temp[1] = word in spanish
                            Association<String, String> a = new Association(temp[0], temp[1]);
                            myBinarySearchTree.add(a);
                        }*/
                    }

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TreeTranslatorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TreeTranslatorView.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(TreeTranslatorView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                isDictionaryLoaded = true;
                //TODO: Show a dialog with successful message, and content

                /*List<Association<String, String>> list = myBinarySearchTree.inOrder();
                String dictionaryContent = "";

                for(Association association: list) dictionaryContent += association.getKey() + "," + association.getValue() + "\n";
                */
                //Dictionary and/or text haven't been loaded
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dictionary loaded!");
                alert.setHeaderText("Your dictionary was loaded...");
                alert.setContentText("Contains a lot of words!\n");
                alert.showAndWait();
            }
        });


        treeTypesComboBox.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem != null) {
                switch(newItem){
                    case TreeFactory.SPLAY_TREE:
                        isSplayTreeSelected = true;
                        break;
                    case TreeFactory.RED_BLACK_TREE:
                        isSplayTreeSelected = false;
                        break;
                }
            }
        });

        //This is the debug button
        Button buttonRun = new Button("Translate");
        buttonRun.setPrefSize(100, 20);
        buttonRun.setStyle("-fx-background-color: #388e3c;");
        buttonRun.setOnAction(e -> {

            //Initialize trees
            redBlackDictionary = (BSTRedBlackTree<String, String>) TreeFactory.generateTree(TreeFactory.RED_BLACK_TREE);
            splayDictionary = (BSTSplayTree<String, String>) TreeFactory.generateTree(TreeFactory.SPLAY_TREE);

            if(isTextToTranslateLoaded && isDictionaryLoaded){
                //Split the inputTextArea by lines and store in an Array
                List<String> initLines = Arrays.asList(inputTextArea.getText().split("\n"));

                for (String line : initLines) {
                    //TODO: Translate each line in this ArrayList, append to outputTextArea TextArea
                    String[] textToTranslate = line.split(" ");
                    for(String word: textToTranslate)
                    {
                        //for each word get the association that matches the key
                        /*
                        Association<String, String> a = myBinarySearchTree.get(new Association<>(word, null));
                        if(a != null)
                        {
                            //append the association value if exists to the outputTextArea variable
                            outputTextArea.appendText(a.getValue().toString());
                        }
                        else
                        {
                            outputTextArea.appendText(" "+ "*" + word + "*" + " ");
                        }*/
                    }
                    outputTextArea.appendText("\n");

                }

            } else {
                //Dictionary and/or text haven't been loaded
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Load files");
                alert.setContentText("Text to translate or Dictionary haven't been loaded...");
                alert.showAndWait();

            }

        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        outputTextArea.appendText("Output" + "\n");

        //Button for clear the inputTextArea data
        Button buttonClear = new Button("Clear");
        buttonClear.setPrefSize(100, 20);
        buttonClear.setOnAction(e -> {
            inputTextArea.clear();
            outputTextArea.clear();
            outputTextArea.appendText("Output" + "\n");
            isDictionaryLoaded = isTextToTranslateLoaded = false;
        });

        hbox.getChildren().addAll(buttonLoadDictionary, buttonLoadTextToTranslate, buttonClear, buttonRun);

        return hbox;
    }


    /**
     * For add a TextArea to the screen, and show the outputTextArea
     *
     * @return a filled HBox to add to the UI
     */
    public VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        //Adding the TextArea to the VBox
        inputTextArea.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(inputTextArea);

        outputTextArea.setFont(Font.font("Arial", FontWeight.MEDIUM, 14));
        vbox.getChildren().add(outputTextArea);


        return vbox;
    }



}