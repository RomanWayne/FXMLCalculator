package com.github.rromanov.ui;

import com.github.rromanov.calculation.CalculationProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private CalculationProcess calculationProcess;

    @FXML
    private TextField lowerTextField;
    @FXML
    private TextField upperTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lowerTextField.setText("0");
        calculationProcess = new CalculationProcess();
    }

    @FXML
    private void clickNumberButton(ActionEvent event){
        if(!calculationProcess.isError()) {
            if (event.getSource() instanceof Button) {
                Button clickButton = (Button) event.getSource();
                if (!lowerTextField.getText().equals("0") && !calculationProcess.isResultInTextFile()) {
                    lowerTextField.setText(lowerTextField.getText() + clickButton.getText());
                    calculationProcess.setResultInTextFile(false);
                } else {
                    lowerTextField.setText(clickButton.getText());
                }
            }
        }
    }

    @FXML
    private void clickOperationButton(ActionEvent event) {
        if(!calculationProcess.isError()) {
            if (event.getSource() instanceof Button) {
                Button clickButton = (Button) event.getSource();
                calculationProcess.arithmeticCalculation(upperTextField.getText(), lowerTextField.getText(), clickButton.getText());
                refreshTextFields();
            }
        }

    }

    @FXML
    private void clickEqualButton() {
        if(!calculationProcess.isError()) {
            if (!upperTextField.getText().isEmpty()) {
                calculationProcess.calculateTotal(lowerTextField.getText());
                refreshTextFields();
            }
        }
    }

    @FXML
    private void clickResetButton() {
        calculationProcess.reset();
        refreshTextFields();
    }

    private void refreshTextFields(){
        upperTextField.setText(calculationProcess.getUpperTextValue());
        lowerTextField.setText(calculationProcess.getLowerTextValue());
    }


}
