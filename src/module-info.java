/**
 * 
 */
/**
 * 
 */
module JavaProject22_N89_2 {
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	
	opens RapChieuPhim to javafx.fxml;
    
    // 3. Mở cửa cho JavaFX Graphics (để chạy file Main)
    // ĐÂY LÀ DÒNG BẠN ĐANG THIẾU
    exports RapChieuPhim;
}