package bloodissuedtbl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import bloodstock.dataconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;

public class BloodIssuedtblViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combotype;

    @FXML
    private DatePicker txtdate;

    @FXML
    private TableView<IssuedBean> txttbl;
    Connection con;
    ResultSet table;
    PreparedStatement pst;
    @FXML
    void dolistall(ActionEvent event) {
    	TableColumn<IssuedBean, String> name=new TableColumn<IssuedBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String> mob=new TableColumn<IssuedBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//same as bean property
    	mob.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String> hospital=new TableColumn<IssuedBean, String>("hospital");
    	hospital.setCellValueFactory(new PropertyValueFactory<>("hospital"));//same as bean property
    	hospital.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String> bgroup=new TableColumn<IssuedBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<IssuedBean, Integer> units=new TableColumn<IssuedBean, Integer>("Units");
    	units.setCellValueFactory(new PropertyValueFactory<>("units"));//same as bean property
    	units.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String> reason=new TableColumn<IssuedBean, String>("reason");
    	reason.setCellValueFactory(new PropertyValueFactory<>("reason"));//same as bean property
    	reason.setMinWidth(100);
    	
    	TableColumn<IssuedBean, String> date=new TableColumn<IssuedBean, String>("Date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	date.setMinWidth(100);
    	
    	txttbl.getColumns().clear();
    	txttbl.getColumns().addAll(name,mob,hospital,bgroup,units,reason,date);
    	ObservableList<IssuedBean>allRecords=getAllObjects();	
    	txttbl.setItems(allRecords);
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeExcel( ObservableList<IssuedBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BloodIssuedTable.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,blood group,date of donation\n";
            writer.write(text);
            for (IssuedBean p : list)
            {
				text = p.getName()+ ","+p.getMob()+ ","+ p.getHospital()+ "," + p.getBgroup()+p.getUnits()+ ","+p.getReason()+ ","+p.getDate()+ ","+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    
    ObservableList<IssuedBean> getAllObjects()
    {
    	ObservableList<IssuedBean> ary=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from issued where date=? and bgroup=?");
			pst.setString(1, String.valueOf(txtdate.getValue()));
			pst.setString(2, combotype.getSelectionModel().getSelectedItem());
			table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				String mob=table.getString("mobile");
				String hospital=table.getString("hospital");
				String bgroup=table.getString("bgroup");
				int units=table.getInt("units");
				String reason=table.getString("reason");
				String date=table.getString("date");
				IssuedBean obj=new IssuedBean(name,mob,hospital,bgroup,units,reason,date);
				ary.add(obj);
				System.out.println(mob+"  "+bgroup+" "+date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(ary.size());
    	return ary;
    }
    String aryblood[]= {"O-","O+","A-","A+","B-","B+","AB-","AB+"};
    @FXML
    void initialize() {
    	con=dataconnection.doconnect();
    combotype.getItems().addAll(aryblood);
    }

}
