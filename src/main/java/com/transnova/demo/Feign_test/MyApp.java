package com.transnova.demo.Feign_test;

import feign.Feign;
import feign.gson.GsonDecoder;
import java.util.List;


public class MyApp {
    public static void main(String[] args){
        GitHub gitHub= Feign.builder()
                .decoder(new GsonDecoder())
                .target(GitHub.class,"https://api.github.com");
        List<String> conreibutors=gitHub.contributors("OpenFeign","feign");
        for (String conreibutor:conreibutors){
            System.out.println("login"+"("+"contributions"+")");
        }
    }
}
