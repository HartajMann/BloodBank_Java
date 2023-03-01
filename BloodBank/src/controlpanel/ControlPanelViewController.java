package controlpanel;

import java.net.URL;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlPanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    

    @FXML
    private ImageView txtreg;
    
    @FXML
    void dodonate(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/bloodunitcollection/BloodUnitCollectionView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txtreg.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dohistory(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/history/HistoryView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txtreg.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doissue(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/issueblood/IssueBloodView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txtreg.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doregister(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/donormaster/DonorMasterView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txtreg.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dostock(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/bloodstock/BloodStockView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txtreg.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    

    @FXML
    void initialize() {

    }

}
