package com.demo.ai.entity;

public class ReturnDAO {

/*    "code": 200,
            "message": "",
            "data": [],
            "powered by": "TNanko"*/
    private Integer Code ;
    private String message;
    private  String[] data;

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
