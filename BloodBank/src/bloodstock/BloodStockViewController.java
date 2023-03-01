package bloodstock;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BloodStockViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtabn;

    @FXML
    private TextField txtabp;

    @FXML
    private TextField txtan;

    @FXML
    private TextField txtap;

    @FXML
    private TextField txtbn;

    @FXML
    private TextField txtbp;

    @FXML
    private TextField txtong;

    @FXML
    private TextField txtop;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    void update()
    {
    	try {
			pst=con.prepareStatement("select * from total_blood_record");
			table=pst.executeQuery();
			while(table.next())
			{
				txtong.setText(table.getString("oneg"));
				txtop.setText(table.getString("opos"));
				txtan.setText(table.getString("an"));
				txtap.setText(table.getString("ap"));
				txtbn.setText(table.getString("bn"));
				txtbp.setText(table.getString("bp"));
				txtabn.setText(table.getString("abn"));
				txtabp.setText(table.getString("abp"));
			}
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
			Scene scene1=(Scene)txtbn.getScene();
			   scene1.getWindow().hide();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
    	con=dataconnection.doconnect();
    	update();
    }

}
