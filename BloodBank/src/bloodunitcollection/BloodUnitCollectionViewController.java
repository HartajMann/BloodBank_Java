package bloodunitcollection;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class BloodUnitCollectionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgview;

    @FXML
    private DatePicker seldate;

    @FXML
    private TextField txtbgroup;

   

    @FXML
    private ComboBox<String> combono;
    
    Connection con;
    PreparedStatement pst;
    String showpath;
    @FXML
    void doreset(ActionEvent event) {
    	combono.getEditor().setText("");
		imgview.setImage(null);
		txtbgroup.setText("");
		seldate.getEditor().clear();
		showpath=null;
    }
    ResultSet table;
    boolean findno(String mobile)
    {
    	boolean flag=false;
    	try {
			pst=con.prepareStatement("select * from donormaster where mobile=?");
			pst.setString(1, combono.getEditor().getText());
			table=pst.executeQuery();
			while(table.next())
			{
				flag=true;
				txtbgroup.setText(table.getString("bgroup"));
				showpath=table.getString("picpath");
				Image img=new Image(showpath);
				imgview.setImage(img);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flag;
    }
    @FXML
    void dosearch(ActionEvent event) {
    	String mob=combono.getEditor().getText();
    	if(findno(mob)==false)
    	{
    		showMsg("Record Not Avaliable");
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
			Scene scene1=(Scene)txtbgroup.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    @FXML
    void doupload(ActionEvent event) {
    	String bloodtype;
    	try {
			pst=con.prepareStatement("insert into donations values(?,?,?)");
			pst.setString(1, combono.getEditor().getText());
			pst.setString(2, txtbgroup.getText());
			bloodtype=txtbgroup.getText();
			LocalDate local=seldate.getValue();
		    Date date=Date.valueOf(local);
			pst.setDate(3, date);
			pst.executeUpdate();
			add(bloodtype);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
       		pst=con.prepareStatement("update total_blood_record	 set "+s+"="+s+"+1");
       		pst.executeUpdate();
       		showMsg("One Unit Added To "+s+" Group");
       	 }catch(Exception ex)
       	 {
       		 ex.printStackTrace();
       	 }
    }
    String s;
    void add(String bloodtype)
    {
    	if(bloodtype.equals("A+"))
    		s="ap";
    	 else if(bloodtype.equals("A-"))
    		 s="an";
    	 else if(bloodtype.equals("B+"))
    		 s="bp";
    	 else if(bloodtype.equals("B-"))
    		 s="bn";
    	 else if(bloodtype.equals("O+"))
    		 s="opos";
    	 else if(bloodtype.equals("O-"))
    		 s="oneg";
    	 else if(bloodtype.equals("AB+"))
    		 s="abp";
    	 else if(bloodtype.equals("AB+"))
    	     s="abn";
    }
    void getno()
    {
    	ArrayList<String> mob=new ArrayList<String>();
    	try {
			pst=con.prepareStatement("select distinct mobile from donormaster");
		
    	table=pst.executeQuery();
    	while(table.next())
    	{
    		String mobile=table.getString("mobile");
    		mob.add(mobile);
    	}
    	combono.getItems().addAll(mob);
    	
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
       con=dataconnection.doconnect();
       getno();
    }
}