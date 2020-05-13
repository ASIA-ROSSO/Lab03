package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class FXMLController {

	private Dictionary dictionary;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox btnComboBox;

	@FXML
	private ComboBox<String> boxLingua;

	@FXML
	private TextArea txtInsert;

	@FXML
	private Button btnSpellCheck;

	@FXML
	private TextArea txtRisultato;

	@FXML
	private Label txtErrori;

	@FXML
	private Button btnCleraText;

	@FXML
	private Label txtTempoControllo;

	@FXML
	void doSpellCheck(ActionEvent event) {
		// comunico la lingua scelta:
		String lingua = boxLingua.getValue();
		if (lingua == null) {
			txtRisultato.setText("Scegli la lingua!");
			return;
		}
		dictionary.loadDictionary(lingua);

		// Passo a spellCheckText le parole inserite una a una
		double start = System.nanoTime();
		List<String> parole = new ArrayList<String>();
		String[] testo = txtInsert.getText().toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]\\?", "")
				.replace("\\?", "").split(" ");
		for (String s : testo) {
			parole.add(s);
		}
		double stop = System.nanoTime();

		// restituisco le parole errate sullo schermo
		List<RichWord> listaParole = new ArrayList<RichWord>();
		listaParole = dictionary.spellCheckText(parole);
		String risultato = "";
		for (RichWord r : listaParole) {
			if (r.getIsValid() == false) {
				risultato += r.toString() + "\n";
			}
		}
		txtRisultato.setText(risultato);
		txtErrori.setText("The text contains " + dictionary.getParoleErrate() + " errors");
		txtTempoControllo.setText("Spell check completed in " + (stop - start) / 1000000000 + " seconds");
	}

	@FXML
	void doClearText(ActionEvent event) {
		txtRisultato.clear();
		txtInsert.clear();
		txtErrori.setText("");
		txtTempoControllo.setText("");
		dictionary.remove();
	}

	@FXML
	void initialize() {
		assert boxLingua != null : "fx:id=\"boxLingua\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCleraText != null : "fx:id=\"btnCleraText\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtTempoControllo != null : "fx:id=\"txtTempoControllo\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setDictinary(Dictionary dictionary) {
		boxLingua.getItems().addAll("English", "Italian");
		this.dictionary = dictionary;

	}
}
