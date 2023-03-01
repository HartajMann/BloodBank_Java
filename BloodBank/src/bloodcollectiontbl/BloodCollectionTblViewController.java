package bloodcollectiontbl;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BloodCollectionTblViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmob;

    @FXML
    private TableView<CollectionBean> txttbl;
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    @FXML
    void doallrecords(ActionEvent event) {
    	TableColumn<CollectionBean, String> mob=new TableColumn<CollectionBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//same as bean property
    	mob.setMinWidth(100);
    	
    	TableColumn<CollectionBean, String> bgroup=new TableColumn<CollectionBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<CollectionBean, String> dod=new TableColumn<CollectionBean, String>("Date Of Donation");
    	dod.setCellValueFactory(new PropertyValueFactory<>("dod"));//same as bean property
    	dod.setMinWidth(100);
    	
    	txttbl.getColumns().clear();;
    	txttbl.getColumns().addAll(mob,bgroup,dod);
    	ObservableList<CollectionBean>allRecords=getAllObjects();	
    	txttbl.setItems(allRecords);
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeExcel( ObservableList<CollectionBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BloodCollectionTable.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,blood group,date of donation\n";
            writer.write(text);
            for (CollectionBean p : list)
            {
				text = p.getMob()+ ","+ p.getBgroup()+ "," + p.getDod()+"\n";
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
    
    ObservableList<CollectionBean> getAllObjects()
    {
    	ObservableList<CollectionBean> ary=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from donations");
			table=pst.executeQuery();
			while(table.next())
			{
				String mob=table.getString("mobile");
				String bgroup=table.getString("bgroup");
				String dod=table.getString("dateofdonation");
				CollectionBean obj=new CollectionBean(mob,bgroup,dod);
				ary.add(obj);
				System.out.println(mob+"  "+bgroup+" "+dod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(ary.size());
    	return ary;
    }
    ObservableList<CollectionBean> getAllObjects1()
    {
    	ObservableList<CollectionBean> ary=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from donations where mobile=?");
			pst.setString(1, txtmob.getText());
			table=pst.executeQuery();
			while(table.next())
			{
				String mob=table.getString("mobile");
				String bgroup=table.getString("bgroup");
				String dod=table.getString("dateofdonation");
				CollectionBean obj=new CollectionBean(mob,bgroup,dod);
				ary.add(obj);
				System.out.println(mob+"  "+bgroup+" "+dod);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(ary.size());
    	return ary;
    }
    @FXML
    void dorecords(ActionEvent event) {
    	TableColumn<CollectionBean, String> mob=new TableColumn<CollectionBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//same as bean property
    	mob.setMinWidth(100);
    	
    	TableColumn<CollectionBean, String> bgroup=new TableColumn<CollectionBean, String>("Blood Group");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<CollectionBean, String> dod=new TableColumn<CollectionBean, String>("Date Of Donation");
    	dod.setCellValueFactory(new PropertyValueFactory<>("dod"));//same as bean property
    	dod.setMinWidth(100);
    	
    	txttbl.getColumns().clear();;
    	txttbl.getColumns().addAll(mob,bgroup,dod);
    	ObservableList<CollectionBean>allRecords=getAllObjects1();	
    	txttbl.setItems(allRecords);
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        con=dataconnection.doconnect();
    }

}
