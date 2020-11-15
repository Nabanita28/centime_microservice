package com.ct.centime.dto;

import lombok.Data;

@Data
public class Response {

    private Object data;
    private Error error;

    private Response(Object data){
        this.data = data;
    }
    private Response(Error error){
        this.error = error;
    }

    public static  Response of(Object data){
        return new Response(data);
    }
    public static  Response of(Error error){
        return new Response(error);
    }


}
