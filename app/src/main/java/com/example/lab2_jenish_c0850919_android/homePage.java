package com.example.lab2_jenish_c0850919_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class homePage extends AppCompatActivity
{


    //declaring database helper class
    dataBaseHelper dbHelper;


    //declaring Variables
    EditText productId, productName, productDescription, productPrice;
    Button addBTN,showBtn,deleteBtn,updateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        //binding that variables
        productId = findViewById(R.id.productIdTF);
        productName = findViewById(R.id.productNameTF);
        productDescription = findViewById(R.id.productDescriptionTF);
        productPrice = findViewById(R.id.productPriceTF);
        addBTN = findViewById(R.id.addDataBtn);
        showBtn = findViewById(R.id.displayDataBtn);
        updateBtn = findViewById(R.id.updateDataBtn);
        deleteBtn = findViewById(R.id.deleteDataBtn);
        dbHelper = new dataBaseHelper(homePage.this);



        /*showBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),displayDataPage.class));
                finish();
            }
        });*/



        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homePage.this, "Clicked !", Toast.LENGTH_SHORT).show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });




        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });


        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String tempid = productId.getText().toString().trim();
                String tempname = productName.getText().toString().trim();
                String tempDesc = productDescription.getText().toString().trim();
                String tempPrice = productPrice.getText().toString().trim();
                insertintoDatab();

               if(tempid.isEmpty()) {
                   productId.setError("Id is Missing !");

               }
               else if(tempname.isEmpty())
                {
                    productName.setError("Name is Missing !");

                }

                else if(tempDesc.isEmpty())
                {
                    productDescription.setError("Description is Missing !");

                }

                else if(tempPrice.isEmpty())
                {
                    productPrice.setError("Price is Missing !");

                }
                else
               {
                   insertintoDatab();

               }



            }


        });

    }


    private void insertintoDatab()
    {
        String tempid12= productId.getText().toString().trim();
        String tempname12 = productName.getText().toString().trim();
        String tempDesc12 = productDescription.getText().toString().trim();
        String tempPrice12 = productPrice.getText().toString().trim();

        RawData rawData = new RawData();
        rawData.setproduct_id(tempid12);
        rawData.setProduct_name(tempname12);
        rawData.setProduct_description(tempDesc12);
        rawData.setProduct_price(tempPrice12);
        dbHelper.AddProduct(rawData);
        //loadData();


        Toast.makeText(homePage.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
        emptyfiled();
    }

    private void emptyfiled()
    {
        productId.setText("");
        productName.setText("");
        productDescription.setText("");
        productPrice.setText("");
    }


    //creating function that will refresh the page and load the data

    /*private void loadData()
    {

        List<RawData> productModelList =dbHelper.getAll();
        for(RawData productModel : productModelList){
            //datalist.append(" | product_ID : "+ productModel.getproduct_id()+" | Product_Name : "+ productModel.getProduct_name()+" | Product_Price : "+ productModel.getProduct_price()+ " | Product_description : "+ productModel.getProduct_description()+" \n\n");
        }
    }*/



    private void update()
    {
        //opening new layout file in dialog box
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(homePage.this);
        View view = getLayoutInflater().inflate(R.layout.activity_display_data_page,null);
        alertDialog.setView(view);



        EditText temp_id = view.findViewById(R.id.productIdDisplayPage);
        Button temp_getDataBtn = view.findViewById(R.id.fetchBtndisplayPage);
        AlertDialog alertDialog1 = alertDialog.show();

        temp_getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                displayDilog(temp_id.getText().toString().trim());
                alertDialog1.dismiss();
                //loadData();

            }
        });

    }


    private void delete()
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(homePage.this);
        View view = getLayoutInflater().inflate(R.layout.deletepage,null);
        alertDialog.setView(view);

        //binding ids
        EditText temp_productid_1 = view.findViewById(R.id.productIdDeletePage);
        Button temp_deleteBtn = view.findViewById(R.id.deleteBtnDeletePage);

        AlertDialog alertDialog1 = alertDialog.show();

        temp_deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteProduct(temp_productid_1.getText().toString());
                alertDialog1.dismiss();
                //loadData();

            }
        });
    }



    private void displayDilog(final String proid)
    {
        RawData rawData = dbHelper.getProduct(Integer.parseInt(proid));
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(homePage.this);
        View view=getLayoutInflater().inflate(R.layout.update_page_as_dialog,null);

      final   EditText proId=view.findViewById(R.id.productIdUpdateProductPage1);
        final EditText proName=view.findViewById(R.id.productNameUpdateProductPage);
        final EditText proDescription=view.findViewById(R.id.productDescriptionUpdateProductPage2);
        final EditText proPrice=view.findViewById(R.id.productPriceUpdateProductPage3);
        Button updateBTn2=view.findViewById(R.id.updateBtnUP2);

        alertdialog.setView(view);

        proId.setText(rawData.getproduct_id());
        proName.setText(rawData.getProduct_name());
        proDescription.setText(rawData.getProduct_description());
        proPrice.setText(rawData.getProduct_price());


        final AlertDialog alertDialog1 = alertdialog.show();
        updateBTn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RawData rawdata =new RawData();
                rawData.setproduct_id(proId.getText().toString());
                rawData.setProduct_name(proName.getText().toString());
                rawData.setProduct_description(proDescription.getText().toString());
                rawData.setProduct_price(proPrice.getText().toString());
                dbHelper.updateProduct(rawData);
                alertDialog1.dismiss();
                //loadData();
            }
        });

    }




}