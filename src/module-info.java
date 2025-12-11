module Movie_JavaProject {
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.media;
	requires javafx.graphics;
	requires javafx.controls;
	requires lombok;
	requires jbcrypt;
	requires transitive javafx.web;

	requires java.sql;

	opens RapChieuPhim to javafx.fxml;
	
    exports RapChieuPhim;
}
