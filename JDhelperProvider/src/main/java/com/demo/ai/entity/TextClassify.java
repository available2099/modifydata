package com.demo.ai.entity;

/**
 * @author quanlin
 * @date 2019 2019/3/11 13:34
 **/

public class TextClassify implements Comparable <TextClassify>{
    private String label;
    private Float prob;

    public TextClassify() {
    }

    public TextClassify(String label, Float prob) {
        this.label = label;
        this.prob = prob;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Float getProb() {
        return prob;
    }

    public void setProb(Float prob) {
        this.prob = prob;
    }
    @Override
    public String toString() {
        return "TextClassify{" +
                "label='" + label + '\'' +
                ", prob=" + prob +
                '}';
    }

    @Override
    public int compareTo(TextClassify textClassify) {
        if(this.prob>textClassify.prob){
            return 1 ;
        }else if(this.prob<textClassify.prob){
            return -1 ;
        }else{
            return this.label.compareTo(textClassify.label) ; // 调用String中的compareTo()方法
        }
    }
}
