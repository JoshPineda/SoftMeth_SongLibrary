package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class SonglibController {
	@FXML
	ListView<String> SongView;
	
	private ObservableList<String> obslist;
	
	public void start() {
		obslist = FXCollections.observableArrayList(
			"Song 1",
			"Song 2");
		SongView.setItems(obslist);
	}
}
