package donormaster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobgroup;

    @FXML
    private ComboBox<String> combogender;

    @FXML
    private ImageView imageshow;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtdisease;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtno;
    Connection con;
    PreparedStatement pst;
    String showpath=null;
    @FXML
    void dobrowse(ActionEvent event) {
    	FileChooser file=new FileChooser();
    	file.getExtensionFilters().add(new ExtensionFilter("JPG FILES","*.jpg"));
    	File f=file.showOpenDialog(null);
    	if(f!=null)
    	{
    		try {
				Image img=new Image(new FileInputStream(f.getAbsoluteFile()));
				imageshow.setImage(img);
				showpath=(f.getAbsolutePath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
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
			Scene scene1=(Scene)txtadd.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    @FXML
    void doclear(ActionEvent event) {
    	txtno.setText("");
		txtname.setText("");
		txtadd.setText("");
		txtcity.setText("");
		txtage.setText("");
		txtdisease.setText("");
		combobgroup.valueProperty().set("");
		combogender.valueProperty().set("");
		imageshow.setImage(null);
		showpath=null;
    }

    @FXML
    void dodelete(ActionEvent event) {
    	try {
			pst=con.prepareStatement("delete from donormaster where mobile=?");
			pst.setString(1, txtno.getText());
			int count=pst.executeUpdate();
			if(count==0)
			{
				showMsg("Something Went Wrong Try Again");
			}
			showMsg("Delete Complete");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    ResultSet table;
    boolean findno(String mobile)
    {
    	boolean flag=false;
    	try {
			pst=con.prepareStatement("select * from donormaster where mobile=?");
			pst.setString(1, txtno.getText());
			table=pst.executeQuery();
			while(table.next())
			{
				flag=true;
				txtname.setText(table.getString("name"));
				txtadd.setText(table.getString("address"));
				txtcity.setText(table.getString("city"));
				showpath=table.getString("picpath");
				txtage.setText(String.valueOf(table.getString("age")));
				txtdisease.setText(table.getString("disease"));
				combogender.setValue(table.getString("gender"));
				combobgroup.setValue(table.getString("bgroup"));
				Image img=new Image(showpath);
				imageshow.setImage(img);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }
    @FXML
    void dofetch(ActionEvent event) {
    	String mob=txtno.getText();
    	if(findno(mob)==false)
    	{
    		showMsg("No Record Avaliable For Entered Number");
    	}
    }
    boolean exsist(String mobile)
    {
    	boolean flag=false;
    	try {
			pst=con.prepareStatement("select * from donormaster where mobile=?");
			pst.setString(1, txtno.getText());
			table=pst.executeQuery();
			while(table.next())
			{
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }
    @FXML
    void doregister(ActionEvent event) {
    	String mob=txtno.getText();
    	if(exsist(mob)==true)
    	{
    		showMsg("Number Already Registered");
    		return;
    	}
    	try {
			pst=con.prepareStatement("insert into donormaster values(?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, txtno.getText());
			pst.setString(2, showpath);
			pst.setString(3, txtname.getText());
			pst.setString(4, combogender.getSelectionModel().getSelectedItem());
			pst.setString(5, txtadd.getText());
			pst.setString(6, txtcity.getText());
			pst.setString(7, combobgroup.getSelectionModel().getSelectedItem());
			pst.setInt(8, Integer.parseInt(txtage.getText()));
			pst.setString(9, txtdisease.getText());
			pst.executeUpdate();
			showMsg("Registration Complete");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    @FXML
    void doupdate(ActionEvent event) {
    	try {
			pst=con.prepareStatement("update donormaster set picpath=?,name=?,gender=?,address=?,city=?,bgroup=?,age=?,disease=? where mobile=?");
			pst.setString(9, txtno.getText());
			pst.setString(1, showpath);
			pst.setString(2, txtname.getText());
			pst.setString(3, combogender.getSelectionModel().getSelectedItem());
			pst.setString(4, txtadd.getText());
			pst.setString(5, txtcity.getText());
			pst.setString(6, combobgroup.getSelectionModel().getSelectedItem());
			pst.setInt(7, Integer.parseInt(txtage.getText()));
			pst.setString(8, txtdisease.getText());
			int count=pst.executeUpdate();
			if(count==0)
			{
				showMsg("Something Went Wrong Try Again");
			}
			else
			showMsg("Update Complete");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    String aryblood[]= {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
    String arygender[]= {"Male","Female","Non-Binary"};
    
    @FXML
    void initialize() {
    	con=dataconnection.doconnect();
    	combobgroup.getItems().addAll(aryblood);
    	combogender.getItems().addAll(arygender);
    }

}
