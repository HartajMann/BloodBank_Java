package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtpwd;

    @FXML
    private TextField txtuser;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void dologin(ActionEvent event) {
    String id="admin";
    String pwd="123456";
    if(txtuser.getText().equals("") ||txtpwd.getText().equals(""))
    {
    	showMsg("Please Fill All Fields");
    }
    else if(txtuser.getText().equals(id) && txtpwd.getText().equals(pwd))
    {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/controlpanel/ControlPanelView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txtuser.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    else if(txtuser.getText()!=id || txtpwd.getText()!=pwd)
    {
    	showMsg("Worng Password Or ID Entered");
    }
    }

    @FXML
    void initialize() {

    }

}
/* @FXML
    void doBilling(ActionEvent event) {
    	playSound();
    	playSong();
    }
    void playSong()
    {
    	
    	url=getClass().getResource("bg.mp3");
		media=new Media(url.toString());
		mediaplayer=new MediaPlayer(media);
		mediaplayer.play();
    }*/
