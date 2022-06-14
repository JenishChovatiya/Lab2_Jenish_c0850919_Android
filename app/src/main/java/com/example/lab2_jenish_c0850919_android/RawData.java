package com.example.lab2_jenish_c0850919_android;

public class RawData
{

    //declaring Variables
    String id = "";
    String product_id="";
    String product_name ="";
    String product_description ="";
    String product_price ="";
    String created_at="";

    //creating Constructor
    public RawData(String id,String product_id, String product_name, String product_description, String product_price, String created_at) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.created_at = created_at;
    }

    public RawData()
    {

    }

    public RawData(String string, String string1, String string2, String string3, String string4) {



    }

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public  String getproduct_id()
    {
        return product_id;
    }

    public void setproduct_id(String id)
    {
        this.product_id = id;
    }

    public  String getProduct_name()
    {
        return product_name;
    }

    public void setProduct_name(String product_name)
    {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

}
