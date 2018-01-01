/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.exception.CountryException;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.SimulationStatistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<Country> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    private Model model;
    
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	String annoToString = this.txtAnno.getText();
    	System.out.println("<doCalcolaConfini> annoToString: " + annoToString);
    	
    	if(annoToString==null || "".equals(annoToString.trim())){
       		this.txtResult.setText("Inserire un valore nel campo anno");
       		return;
       	}
    	
    	
    	int anno; 
    	try{
    		anno = Integer.parseInt(annoToString);
    		if(anno<1816 || anno>2006){
        		this.txtResult.setText("L'anno deve essere compreso tra il 1816 e il 2006");
    		}
    		else{
    			this.txtResult.setText(String.format("Paesi confinanti nell'anno %d \n",anno));
            	List<Country> countriesList = model.retrieveCountriesInfo(anno);
        		for(Country c : countriesList){
        			this.txtResult.appendText(c.toString() + "\n");
        		}
        		this.boxNazione.getItems().addAll(countriesList);
    		}
    	} catch( NumberFormatException nfe){
    		this.txtResult.setText("Inserire una stringa numerica");
    	} catch (CountryException ce) {
			ce.printStackTrace();
			this.txtResult.setText("Errore nel recupero delle informazioni: " + ce.getMessage());
		}
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	
    	Country country = this.boxNazione.getValue();
    	if(country == null){
    		this.txtResult.setText("Selezionare una nazione");
    		return;
    	}
    	
    	SimulationStatistics ss = model.doSimulation(country);
    	this.txtResult.setText("<main> steps: " + ss.getSteps() + "\n");
		for(Country c : ss.getCountries()){
			this.txtResult.appendText(c.toString4Statistics() + "\n");
		}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
	}
}
