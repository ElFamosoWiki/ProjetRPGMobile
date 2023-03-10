package com.example.projetcoktail;

public class Response {

    private String prereponse;
    private String deuxreponse;
    public Response(String result, String resultat){
        prereponse=result;
        deuxreponse=resultat;
    }

    public String getPrereponse(){
        return this.prereponse;
    }
    public String getDeuxreponse(){
        return this.deuxreponse;
    }
}
