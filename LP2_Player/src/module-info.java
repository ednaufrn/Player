module LP2_Player {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	opens br.imd.controle to javafx.graphics, javafx.fxml;
}
