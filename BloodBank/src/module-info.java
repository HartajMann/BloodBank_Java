module BloodBank {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	opens donormaster to javafx.graphics, javafx.fxml;
	opens bloodunitcollection to javafx.graphics, javafx.fxml;
	opens bloodstock to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	opens controlpanel to javafx.graphics, javafx.fxml;
	opens issueblood to javafx.graphics, javafx.fxml;
	opens infotable to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodcollectiontbl to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodissuedtbl to javafx.graphics, javafx.fxml,javafx.base;
	opens history to javafx.graphics, javafx.fxml;
}
