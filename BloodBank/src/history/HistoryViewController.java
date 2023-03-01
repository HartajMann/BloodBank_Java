package history;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

public class HistoryViewController {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private ImageView imghist;
    
    @FXML
    private URL location;

    @FXML
    void docollection(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/bloodcollectiontbl/BloodCollectionTblView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void dohome(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/controlpanel/ControlPanelView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)imghist.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doinfo(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/infotable/DonorsInfoView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doissue(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/bloodissuedtbl/BloodIssuedTblView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
		
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
