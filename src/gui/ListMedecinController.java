/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author soumayaab
 */
public class ListMedecinController implements Initializable {
private double x, y;
    @FXML
    private VBox pnitems = null;
    @FXML
    private Button btnAdd;
    @FXML
    private ImageView btnAddplus1;   
    @FXML
    private Label Nom;
    @FXML
    private Label Prenom;
    @FXML
    private Label Email;
    @FXML
    private Label Telephone;
    @FXML
    private Label Titre;
    @FXML
    private Label Specialites;
    @FXML
    private Label Tarif;
    @FXML
    private Label Adresse;
    @FXML
    private Label Action;
    @FXML
    private Label ListeMedecin;
   

    

      public void initialize(URL location, ResourceBundle resources) {
        Node[] nodes = new Node[13];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/gui/item.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #F1F7FD");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #6C9FC1");
                });
                pnitems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            
            }
        }

    }
@FXML
    void handleButtonClick(ActionEvent event) {
      if(event.getSource()== btnAdd){
      showAsDialog("AddMedecin");
      }
      if(event.getSource()== btnAddplus1){
      showAsDialog("AddMedecin");
      }
      //if(event.getSource() == btncross) {
        // Get the Stage that contains the button
      //  Stage stage = (Stage) btncross.getScene().getWindow();
        // Close the stage
       // stage.close();
    //}
    }
    
    
    private void showAsDialog(String fxml)
    {
       try {
           Parent parent = FXMLLoader.load(getClass().getResource("/gui/AddMedecin.fxml"));
           Stage stage = new Stage();
           Scene scene = new Scene(parent);
           stage.setScene(scene);
           stage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        parent.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        parent.setOnMouseDragged(event -> {

            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
           stage.show();
        
        //primaryStage.setTitle("Sign In User");
       
       
           
           
           
           
       } catch (IOException ex) {
           ex.printStackTrace();
       }
    
    }

    

    
    
}
