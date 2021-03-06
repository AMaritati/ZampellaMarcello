package it.polito.tdp.camminoAutobus;

import java.util.List;

import it.polito.tdp.camminoAutobus.model.Collegamento;
import it.polito.tdp.camminoAutobus.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CodiciLocaliController {
	

    @FXML
    private TextField txtPartenza;

    @FXML
    private TextField txtArrivo;
    
    @FXML
    private ComboBox<Collegamento> cmbPartenza;
	
    @FXML
    private ComboBox<Collegamento> cmbArrivo;
	
	@FXML
	private TextArea txtResult;

	private Model model;

	private Stage stage;

	private Scene oldScene;

	private ComboBox<Collegamento> modificaPartenza;

	private ComboBox<Collegamento> modificaArrivo;

    @FXML
    void doCercaArrivo(ActionEvent event) {
    	if(this.txtArrivo.getText().equals("")) {
    		this.txtResult.setStyle("-fx-text-inner-color: red;");
    		this.txtResult.setText("INSERIRE UN VALORE DI ARRIVO");
    		return;
    	}
    	this.cmbArrivo.getItems().clear();
    	List<Collegamento> collegamenti=model.cercaCodice(this.txtArrivo.getText());
    	if(collegamenti.size()==0) {
    		this.txtResult.setStyle("-fx-text-inner-color: red;");
    		this.txtResult.setText("Nessun risultato trovato per la ricerca effettuata");
    		return;
    	}
    	this.cmbArrivo.setDisable(false);
    	this.cmbArrivo.getItems().addAll(collegamenti);
    	this.txtResult.clear();
    }

    @FXML
    void doCercaPartenza(ActionEvent event) {
    	if(this.txtPartenza.getText().equals("")) {
    		this.txtResult.setStyle("-fx-text-inner-color: red;");
    		this.txtResult.setText("INSERIRE UN VALORE DI PARTENZA");
    		return;
    	}
    	this.cmbPartenza.getItems().clear();
    	List<Collegamento> collegamenti=model.cercaCodice(this.txtPartenza.getText());
    	if(collegamenti.size()==0) {
    		this.txtResult.setStyle("-fx-text-inner-color: red;");
    		this.txtResult.setText("Nessun risultato trovato per la ricerca effettuata");
    		return;
    	}
		this.cmbPartenza.setDisable(false);
    	this.cmbPartenza.getItems().addAll(collegamenti);
    	this.txtResult.clear();
    }
    
    @FXML
    void doInserisciDati(ActionEvent event) {
    	if(this.cmbPartenza.getValue()!=null) {
    		this.modificaPartenza.setValue(this.cmbPartenza.getValue());
    	}
    	if(this.cmbArrivo.getValue()!=null) {
    		this.modificaArrivo.setValue(this.cmbArrivo.getValue());
    	}
    	stage.setScene(oldScene);
		stage.show();
    }

	public void setModel(Model model, Stage stage, Scene oldScene, ComboBox<Collegamento> modificaPartenza, ComboBox<Collegamento> modificaArrivo) {
		this.model=model;
		this.stage=stage;
		this.oldScene=oldScene;
		this.modificaPartenza=modificaPartenza;
		this.modificaArrivo=modificaArrivo;
		this.cmbArrivo.setDisable(true);
		this.cmbPartenza.setDisable(true);	
	}

}
