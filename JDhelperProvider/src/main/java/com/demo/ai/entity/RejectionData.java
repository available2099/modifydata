package com.demo.ai.entity;

public class RejectionData {
    private String inputText;
    private String limitValue;
    private String label;
    private Float prod;
    private Boolean reject;

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(String limitValue) {
        this.limitValue = limitValue;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getProd() {
        return prod;
    }

    public void setProd(Float prod) {
        this.prod = prod;
    }

    public Boolean getReject() {
        return reject;
    }

    public void setReject(Boolean reject) {
        this.reject = reject;
    }
}
