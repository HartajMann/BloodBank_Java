package infotable;

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

import issueblood.dataconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import infotable.DonorBean;



public class DonorsInfoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DonorBean> tblgrid;

    @FXML
    private TextField txtbgroup;
    
    ResultSet table;
    Connection con;
    PreparedStatement pst;
    
    ObservableList<DonorBean> getAllObjects()
    {
    	ObservableList<DonorBean> ary=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from donormaster");
			table=pst.executeQuery();
			while(table.next())
			{
				String mob=table.getString("mobile");
				String picpath=table.getString("picpath");
				String name=table.getString("name");
				String gender=table.getString("gender");
				String address=table.getString("address");
				String city=table.getString("city");
				String bgroup=table.getString("bgroup");
				int age=table.getInt("age");
				String disease=table.getString("disease");
				String date=table.getString("dor");
				DonorBean obj=new DonorBean(mob,picpath,name,gender,address,city,bgroup,age,disease,date);
				ary.add(obj);
				System.out.println(mob+"  "+name+"  "+address+" "+city+" "+picpath+" "+gender+" "+disease+" "+age+" "+bgroup+" "+date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(ary.size());
    	return ary;
    }
    
    ObservableList<DonorBean> getAllObjects1()
    {
    	ObservableList<DonorBean> ary=FXCollections.observableArrayList();
    	try {
			pst=con.prepareStatement("select * from donormaster where bgroup=?");
			pst.setString(1, txtbgroup.getText());
			table=pst.executeQuery();
			while(table.next())
			{
				String mob=table.getString("mobile");
				String picpath=table.getString("picpath");
				String name=table.getString("name");
				String gender=table.getString("gender");
				String address=table.getString("address");
				String city=table.getString("city");
				String bgroup=table.getString("bgroup");
				int age=table.getInt("age");
				String disease=table.getString("disease");
				String date=table.getString("dor");
				DonorBean obj=new DonorBean(mob,picpath,name,gender,address,city,bgroup,age,disease,date);
				ary.add(obj);
				System.out.println(mob+"  "+name+"  "+address+" "+city+" "+picpath+" "+gender+" "+disease+" "+age+" "+bgroup+" "+date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(ary.size());
    	return ary;
    }
    @FXML
    void dofetch(ActionEvent event) {
    	TableColumn<DonorBean, String> mob=new TableColumn<DonorBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//same as bean property
    	mob.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> picpath=new TableColumn<DonorBean, String>("PicPath");
    	picpath.setCellValueFactory(new PropertyValueFactory<>("picpath"));//same as bean property
    	picpath.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> name=new TableColumn<DonorBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> gender=new TableColumn<DonorBean, String>("Gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));//same as bean property
    	gender.setMinWidth(100);
    	TableColumn<DonorBean, String> address=new TableColumn<DonorBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	address.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> city=new TableColumn<DonorBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//same as bean property
    	city.setMinWidth(100);

    	TableColumn<DonorBean, String> bgroup=new TableColumn<DonorBean, String>("Bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
       	TableColumn<DonorBean, Integer> age=new TableColumn<DonorBean,Integer>("Age");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));//same as bean property
    	age.setMinWidth(100);
    
    	TableColumn<DonorBean, String> disease=new TableColumn<DonorBean, String>("Disease");
    	disease.setCellValueFactory(new PropertyValueFactory<>("disease"));//same as bean property
    	disease.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> date=new TableColumn<DonorBean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	date.setMinWidth(100);
    	tblgrid.getColumns().clear();
    	tblgrid.getColumns().addAll(mob,picpath,name,gender,address,city,bgroup,age,disease,date);
    	
    	ObservableList<DonorBean>allRecords=getAllObjects1();	
    	tblgrid.setItems(allRecords);
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void doshowall(ActionEvent event) {
    	TableColumn<DonorBean, String> mob=new TableColumn<DonorBean, String>("Mobile");
    	mob.setCellValueFactory(new PropertyValueFactory<>("mob"));//same as bean property
    	mob.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> picpath=new TableColumn<DonorBean, String>("PicPath");
    	picpath.setCellValueFactory(new PropertyValueFactory<>("picpath"));//same as bean property
    	picpath.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> name=new TableColumn<DonorBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> gender=new TableColumn<DonorBean, String>("Gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));//same as bean property
    	gender.setMinWidth(100);
    	TableColumn<DonorBean, String> address=new TableColumn<DonorBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	address.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> city=new TableColumn<DonorBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//same as bean property
    	city.setMinWidth(100);

    	TableColumn<DonorBean, String> bgroup=new TableColumn<DonorBean, String>("Bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
       	TableColumn<DonorBean, Integer> age=new TableColumn<DonorBean,Integer>("Age");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));//same as bean property
    	age.setMinWidth(100);
    
    	TableColumn<DonorBean, String> disease=new TableColumn<DonorBean, String>("Disease");
    	disease.setCellValueFactory(new PropertyValueFactory<>("disease"));//same as bean property
    	disease.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> date=new TableColumn<DonorBean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	date.setMinWidth(100);
    	tblgrid.getColumns().clear();
    	tblgrid.getColumns().addAll(mob,picpath,name,gender,address,city,bgroup,age,disease,date);
    	
    	ObservableList<DonorBean>allRecords=getAllObjects();	
    	tblgrid.setItems(allRecords);
    	

    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeExcel( ObservableList<DonorBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BloodRegistrationTable.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Mobile,PicPath,name,gender,address,city,broup,age,disease,date\n";
            writer.write(text);
            for (DonorBean p : list)
            {
				text = p.getMob()+ "," + p.getPicpath()+ "," + p.getName()+ "," + p.getGender()+ "," + p.getAddress()+ "," + p.getCity()+ "," + p.getBgroup()+ "," + p.getAge()+ "," + p.getDisease()+ "," + p.getDate()+"\n";
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
    @FXML
    void initialize() {
        con=dataconnection.doconnect();

    }

}
