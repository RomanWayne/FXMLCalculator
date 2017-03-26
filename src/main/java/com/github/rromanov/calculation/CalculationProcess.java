package com.github.rromanov.calculation;

public class CalculationProcess {
    private StringBuilder upperTextValue;
    private StringBuilder lowerTextValue;
    private boolean isResultInTextFile;
    private int intermediateResult;
    private String previousOperationSign;

    public CalculationProcess(){
        upperTextValue = new StringBuilder();
        lowerTextValue = new StringBuilder();
        isResultInTextFile = false;
    }
    /**
     * simple arithmetic calculation when operation button is clicked
     * @param upperText
     * @param lowerText
     * @param operationSign
     */
    public void arithmeticCalculation(String upperText, String lowerText, String operationSign) {
        if (upperText.isEmpty()) {
            upperTextValue.append(lowerText).append(operationSign);
            lowerTextValue.delete(0, lowerTextValue.length());//clearing
            intermediateResult = Integer.parseInt(lowerText);
        }
        if (lowerText.isEmpty()) {
            upperTextValue.deleteCharAt(upperTextValue.length() - 1);
            upperTextValue.append(operationSign);
        }
        if (!upperText.isEmpty() && !lowerText.isEmpty()){
            upperTextValue.append(lowerText).append(operationSign);
            intermediateResult = calculateIntermediateResult(lowerText);
            lowerTextValue.delete(0, lowerTextValue.length());//clearing
            lowerTextValue.append(String.valueOf(intermediateResult));
            isResultInTextFile = true;
        }
        previousOperationSign = operationSign;
    }

    public void calculateTotal(String lowerText){
        intermediateResult = calculateIntermediateResult(lowerText);
        upperTextValue.delete(0, upperTextValue.length());
        lowerTextValue.delete(0, lowerTextValue.length());
        lowerTextValue.append(String.valueOf(intermediateResult));
    }

    private int calculateIntermediateResult(String nextTerm) {
        switch (previousOperationSign) {
            case "+" : return intermediateResult + Integer.parseInt(nextTerm);
            case "-" : return intermediateResult - Integer.parseInt(nextTerm);
            case "*" : return intermediateResult * Integer.parseInt(nextTerm);
            case "/" :
                if (nextTerm.equals("0")) {
                    lowerTextValue.delete(0, lowerTextValue.length());
                    lowerTextValue.append("Деление на ноль невозможно");
                    return 0;
                }
                return intermediateResult / Integer.parseInt(nextTerm);
        }
        return 0;
    }

    public void reset() {
        lowerTextValue.delete(0, lowerTextValue.length()).append("0");
        upperTextValue.delete(0, upperTextValue.length());
        previousOperationSign = "";
        isResultInTextFile = false;
        intermediateResult = 0;
    }

    private boolean isEndsWithOperationChar(String str) {
        if (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/")) return true;
        return false;
    }

    public String getUpperTextValue() {
        return upperTextValue.toString();
    }

    public String getLowerTextValue() {
        return lowerTextValue.toString();
    }

    public boolean isResultInTextFile() {
        return isResultInTextFile;
    }

    public void setResultInTextFile(boolean resultInTextFile) {
        isResultInTextFile = resultInTextFile;
    }
}
