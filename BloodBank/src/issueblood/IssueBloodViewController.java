package issueblood;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class IssueBloodViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboblood;

    @FXML
    private DatePicker dateissue;

    @FXML
    private TextField txthos;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtno;

    @FXML
    private TextField txtpos;

    @FXML
    private TextField txtunits;
    Connection con;
    PreparedStatement pst;
    @FXML
    void douplode(ActionEvent event) {
    	String blood = null;
    	try {
			pst=con.prepareStatement("insert into issued values(?,?,?,?,?,?,?)");
			pst.setString(1, txtname.getText());
			pst.setString(2, txtno.getText());
			pst.setString(3, txthos.getText());
			pst.setString(4, comboblood.getSelectionModel().getSelectedItem());
			blood=comboblood.getSelectionModel().getSelectedItem();
			pst.setString(5, txtunits.getText());
			pst.setString(6, txtpos.getText());
			LocalDate local=dateissue.getValue();
		    Date date=Date.valueOf(local);
		    pst.setDate(7, date);
		    pst.executeUpdate();
		    showMsg("Update Complete");
		    sub(blood);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
    String s;
    void sub(String b)
    {
    	if(b.equals("O-"))
    		s="oneg";
    	else if(b.equals("O+"))
    		s="opos";
    	else if(b.equals("A-"))
    		s="an";
    	else if(b.equals("A+"))
    		s="ap";
    	else if(b.equals("B+"))
    		s="bp";
    	else if(b.equals("B-"))
    		s="bn";
    	else if(b.equals("AB+"))
    		s="abp";
    	else if(b.equals("AB-"))
    		s="abn";
    	try {
			pst=con.prepareStatement("update total_blood_record	 set "+s+"="+s+"-?");
			pst.setString(1, txtunits.getText());
			pst.executeUpdate();
			showMsg("Unit Removed From "+s+" Group");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void gohome(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/controlpanel/ControlPanelView.fxml")); 
							//OR
			//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide parent window
			Scene scene1=(Scene)txthos.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    @FXML
    void doclear(ActionEvent event) {
    	txtname.clear();
    	txtno.clear();
    	txthos.clear();
    	txtunits.clear();
    	txtpos.clear();
    	dateissue.getEditor().clear();
    	comboblood.valueProperty().set("");
    }
	void showMsg(String msg)
	{
		Alert alert=new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Title");
		alert.setContentText(msg);
		alert.show();
	}
    String aryblood[]= {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
    @FXML
    void initialize() {
    	con=dataconnection.doconnect();
    	comboblood.getItems().addAll(aryblood);
    }

}
