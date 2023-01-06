package com.example.kgnmarketing.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.kgnmarketing.R;
import com.example.kgnmarketing.databinding.ActivityDashboardBinding;
import com.example.kgnmarketing.object.core.EnglishNumberToWords;

public class DashboardActivity extends AppCompatActivity {
        private final String TAG = getClass().getSimpleName();
        private Activity mActivity = DashboardActivity.this;
        private ActivityDashboardBinding layoutBinding;
        int num1, num2;
        int num3 =0;
        int num4 =0;
        int num5 =0;
        int sum, sum1, finalSum, finalNotes;
        String twoThousandStringFinal ="";
        String fiveHundradeStringFinal = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        initToolbar();
        initListener();


    }

    private void finalResult() {
        String finalInputString = twoThousandStringFinal + fiveHundradeStringFinal;
        try {
            num3 = Integer.parseInt(twoThousandStringFinal);
            num4 = Integer.parseInt(fiveHundradeStringFinal);
            finalNotes = num3 + num4;
            finalSum = sum + sum1;
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        if (finalInputString.equals("")){
            layoutBinding.finalResult.setText(getString(R.string.zero));

        }else{
            String return_val_in_english =   EnglishNumberToWords.convert(finalSum);
            layoutBinding.finalResult.setText("Total Amount "+ Integer.toString(finalSum) + " ₹"+ "("+ finalNotes + " Notes" + ")" + return_val_in_english);
        }

    }

    /**
     * initialize toolbar
     */
    private void initToolbar() {
        layoutBinding.layoutToolbar.tvTitle.setText(getResources().getString(R.string.app_name));
        layoutBinding.layoutToolbar.home.setVisibility(View.INVISIBLE);
        layoutBinding.layoutToolbar.menu.setVisibility(View.VISIBLE);
        layoutBinding.layoutToolbar.menu.setImageDrawable(getResources().getDrawable(R.drawable.ic_user));
        layoutBinding.layoutToolbar.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("isUserLogin");
                editor.commit();

                finish();

                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });

        /*layoutBinding.layoutToolbar.home.setOnClickListener(this);
        layoutBinding.layoutToolbar.menu.setOnClickListener(this);*/
    }

    /**
     * initialize listener
     */
    private void initListener() {

        TextWatcher autoAddTextWatcher = new TextWatcher() {
            private String oldText = "";
            private String newText = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                this.oldText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newText = s.toString().replace(oldText, "").trim();
                twoThousands();
                fiveHundrade();
                twoHundrade();
                oneHundrade();
                fifty();
                twenty();
                ten();
                five();
                two();
                one();
//                doMul();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        layoutBinding.twoThousandsEt.addTextChangedListener(autoAddTextWatcher);
        layoutBinding.fiveHundradeEt.addTextChangedListener(autoAddTextWatcher);
    }

    private void twoHundrade() {
    }


    private void oneHundrade() {
    }

    private void fifty() {
    }

    private void twenty() {
    }

    private void ten() {

    }



    private void two() {

    }private void one() {

    }private void five() {

    }

    // a public method to perform multiplication
    public void twoThousands() {
        //checkAndClear();
        // get the input number
        String twoThousandString= layoutBinding.twoThousandsEt.getText().toString();

        if(!twoThousandString.equals("")){
            num1 = Integer.parseInt(twoThousandString);
        }
        String s2 = "2000";
        num2 = Integer.parseInt(s2);
        sum = num1 * num2;
        if (twoThousandString.equals("")){

            layoutBinding.finalTwoThousandResultTv.setText("0" + " ₹");
            layoutBinding.finalResult.setText(getString(R.string.zero));

        }else {
            layoutBinding.finalTwoThousandResultTv.setText(Integer.toString(sum) + " ₹");
            twoThousandStringFinal = twoThousandString;
            finalResult();

        }

    }

    // a public method to perform multiplication
    public void fiveHundrade() {
        //checkAndClear();
        // get the input number
        String fiveHundradeString= layoutBinding.fiveHundradeEt.getText().toString();

        if(!fiveHundradeString.equals("")){
            num1 = Integer.parseInt(fiveHundradeString);
        }
        String s2 = "500";
        num2 = Integer.parseInt(s2);
        sum1 = num1 * num2;
        if (fiveHundradeString.equals("")){

            layoutBinding.finalFiveHundradeResult.setText("0" + " ₹");

        }else {
            layoutBinding.finalFiveHundradeResult.setText(Integer.toString(sum1) + " ₹");
            fiveHundradeStringFinal = fiveHundradeString;
            finalResult();
        }

    }
}