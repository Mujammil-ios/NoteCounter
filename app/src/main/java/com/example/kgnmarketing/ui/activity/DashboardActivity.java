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
        int twoThousandIntFinal =0;
        int fiveHundradeIntFinal =0;
        int twoHundradeIntFinal =0;
        int oneHundradeIntFinal =0;
        int fiftyIntFinal =0;
        int twentyIntFinal =0;
        int tenIntFinal =0;
        int fiveIntFinal =0;
        int twoIntFinal =0;
        int oneIntFinal =0;
        int twoThousandSum, fiveHundradeSum, twoHundraedeSum, oneHundradeSum , fiftySum, twentySum , tenSum, fiveSum, twoSum,oneSum, finalSum, finalNotes;
        String twoThousandStringFinal ="0";
        String fiveHundradeStringFinal = "0";
        String twoHundradeStringFinal = "0";
        String oneHundradeStringFinal = "0";
        String fiftyStringFinal = "0";
        String twentyStringFinal = "0";
        String tenStringFinal = "0";
        String fiveStringFinal = "0";
        String twoStringFinal = "0";
        String oneStringFinal = "0";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        initToolbar();
        initListener();


    }
//    1.Implemenmt onbackpressed
//    2. add user interaction
//    logout option
//    add share with date and user name
//    [1] 2000*666=1332000₹
//
// Total Amount  1332000 ₹ (666 Notes)
//  Thirteen Lakh Thirty Two Thousand
//    add share application in apk format
//    menu option
//    add readme on github
//

    private void finalResult() {
        String finalInputString = twoThousandStringFinal + fiveHundradeStringFinal + twoHundradeStringFinal +oneHundradeStringFinal +fiftyStringFinal + twentyStringFinal
                +tenStringFinal + fiveStringFinal + twoStringFinal + oneStringFinal;
        try {
            twoThousandIntFinal = Integer.parseInt(twoThousandStringFinal);
            fiveHundradeIntFinal = Integer.parseInt(fiveHundradeStringFinal);
            twoHundradeIntFinal = Integer.parseInt(twoHundradeStringFinal);
            oneHundradeIntFinal = Integer.parseInt(oneHundradeStringFinal);
            fiftyIntFinal = Integer.parseInt(fiftyStringFinal);
            twentyIntFinal = Integer.parseInt(twentyStringFinal);
            tenIntFinal = Integer.parseInt(tenStringFinal);
            fiveIntFinal = Integer.parseInt(fiveStringFinal);
            twoIntFinal = Integer.parseInt(twoStringFinal);
            oneIntFinal = Integer.parseInt(oneStringFinal);
            finalNotes = twoThousandIntFinal + fiveHundradeIntFinal +twoHundradeIntFinal+oneHundradeIntFinal+fiftyIntFinal+twentyIntFinal+tenIntFinal
                    +fiveIntFinal+twoIntFinal+oneIntFinal;
            finalSum = twoThousandSum + fiveHundradeSum + twoHundraedeSum + oneHundradeSum + fiftySum + twentySum +  tenSum + fiveSum + twoSum + oneSum;
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
    }

    private void twoHundrade() {

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
                String twoHundradeString= layoutBinding.twoHundradeEt.getText().toString();

                if(!twoHundradeString.equals("")){
                    num1 = Integer.parseInt(twoHundradeString);
                }
                String s2 = "200";
                num2 = Integer.parseInt(s2);
                twoHundraedeSum = num1 * num2;
                if (twoHundradeString.equals("")){
                    layoutBinding.twoHundradeEt.setText("0");

                    layoutBinding.finalTwoHundradeResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalTwoHundradeResult.setText(Integer.toString(twoHundraedeSum) + " ₹");
                    twoHundradeStringFinal = twoHundradeString;
                    finalResult();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                finalResult();

            }
        };
        layoutBinding.twoHundradeEt.addTextChangedListener(autoAddTextWatcher);
    }


    private void oneHundrade() {

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
                String oneHundradeString= layoutBinding.oneHundradeEt.getText().toString();

                if(!oneHundradeString.equals("")){
                    num1 = Integer.parseInt(oneHundradeString);
                }
                String s2 = "100";
                num2 = Integer.parseInt(s2);
                oneHundradeSum = num1 * num2;
                if (oneHundradeString.equals("")){
                    layoutBinding.oneHundradeEt.setText("0");
                    layoutBinding.finalOneHundradeResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalOneHundradeResult.setText(Integer.toString(oneHundradeSum) + " ₹");
                    oneHundradeStringFinal = oneHundradeString;
                    finalResult();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.oneHundradeEt.addTextChangedListener(autoAddTextWatcher);
    }

    private void fifty() {


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

                String fiftyString= layoutBinding.fiftyEt.getText().toString();

                if(!fiftyString.equals("")){
                    num1 = Integer.parseInt(fiftyString);
                }
                String s2 = "50";
                num2 = Integer.parseInt(s2);
                fiftySum = num1 * num2;
                if (fiftyString.equals("")){
                    layoutBinding.fiftyEt.setText("0");

                    layoutBinding.finalFiftyResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalFiftyResult.setText(Integer.toString(fiftySum) + " ₹");
                    fiftyStringFinal = fiftyString;
                    finalResult();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.fiftyEt.addTextChangedListener(autoAddTextWatcher);

    }

    private void twenty() {



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
                String twentyString= layoutBinding.twentyEt.getText().toString();

                if(!twentyString.equals("")){
                    num1 = Integer.parseInt(twentyString);
                }
                String s2 = "20";
                num2 = Integer.parseInt(s2);
                twentySum= num1 * num2;
                if (twentyString.equals("")){
                    layoutBinding.twentyEt.setText("0");

                    layoutBinding.twentyResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.twentyResult.setText(Integer.toString(twentySum) + " ₹");
                    twentyStringFinal = twentyString;
                    finalResult();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.twentyEt.addTextChangedListener(autoAddTextWatcher);


    }

    private void ten() {

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
                String tenString= layoutBinding.tenEt.getText().toString();

                if(!tenString.equals("")){
                    num1 = Integer.parseInt(tenString);
                }
                String s2 = "10";
                num2 = Integer.parseInt(s2);
                tenSum = num1 * num2;
                if (tenString.equals("")){
                    layoutBinding.tenEt.setText("0");

                    layoutBinding.tenResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.tenResult.setText(Integer.toString(tenSum) + " ₹");
                    tenStringFinal = tenString;
                    finalResult();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.tenEt.addTextChangedListener(autoAddTextWatcher);



    }



    private void two() {
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
                String twoString= layoutBinding.twoEt.getText().toString();

                if(!twoString.equals("")){
                    num1 = Integer.parseInt(twoString);
                }
                String s2 = "2";
                num2 = Integer.parseInt(s2);
                twoSum = num1 * num2;
                if (twoString.equals("")){
                    layoutBinding.twoEt.setText("0");

                    layoutBinding.twoResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.twoResult.setText(Integer.toString(twoSum) + " ₹");
                    twoStringFinal = twoString;
                    finalResult();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.twoEt.addTextChangedListener(autoAddTextWatcher);



    }

    private void one() {
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
                String oneString= layoutBinding.oneEt.getText().toString();

                if(!oneString.equals("")){
                    num1 = Integer.parseInt(oneString);
                }
                String s2 = "1";
                num2 = Integer.parseInt(s2);
                oneSum = num1 * num2;
                if (oneString.equals("")){
                    layoutBinding.oneEt.setText("0");

                    layoutBinding.finalOneResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalOneResult.setText(Integer.toString(oneSum) + " ₹");
                    oneStringFinal = oneString;
                    finalResult();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.oneEt.addTextChangedListener(autoAddTextWatcher);

    }

    private void five() {

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
                String fiveString= layoutBinding.fiveEt.getText().toString();

                if(!fiveString.equals("")){
                    num1 = Integer.parseInt(fiveString);
                }
                String s2 = "5";
                num2 = Integer.parseInt(s2);
                fiveSum = num1 * num2;
                if (fiveString.equals("")){
                    layoutBinding.fiveEt.setText("0");

                    layoutBinding.finalFiveResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalFiveResult.setText(Integer.toString(fiveSum) + " ₹");
                    fiveStringFinal = fiveString;
                    finalResult();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        layoutBinding.fiveEt.addTextChangedListener(autoAddTextWatcher);

    }

    // a public method to perform multiplication
    public void twoThousands() {


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
                //checkAndClear();
                // get the input number
                String twoThousandString= layoutBinding.twoThousandsEt.getText().toString();

                if(!twoThousandString.equals("")){
                    num1 = Integer.parseInt(twoThousandString);
                }
                String s2 = "2000";
                num2 = Integer.parseInt(s2);
                twoThousandSum = num1 * num2;
                if (twoThousandString.equals("")){
                    layoutBinding.twoThousandsEt.setText("0");

                    layoutBinding.finalTwoThousandResultTv.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalTwoThousandResultTv.setText(Integer.toString(twoThousandSum) + " ₹");
                    twoThousandStringFinal = twoThousandString;
                    finalResult();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.twoThousandsEt.addTextChangedListener(autoAddTextWatcher);


    }

    // a public method to perform multiplication
    public void fiveHundrade() {


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
                String fiveHundradeString= layoutBinding.fiveHundradeEt.getText().toString();

                if(!fiveHundradeString.equals("")){
                    num1 = Integer.parseInt(fiveHundradeString);
                }
                String s2 = "500";
                num2 = Integer.parseInt(s2);
                fiveHundradeSum = num1 * num2;
                if (fiveHundradeString.equals("")){
                    layoutBinding.fiveHundradeEt.setText("0");
                    layoutBinding.finalFiveHundradeResult.setText("0" + " ₹");
                    layoutBinding.finalResult.setText(getString(R.string.zero));

                }else {
                    layoutBinding.finalFiveHundradeResult.setText(Integer.toString(fiveHundradeSum) + " ₹");
                    fiveHundradeStringFinal = fiveHundradeString;
                    finalResult();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        layoutBinding.fiveHundradeEt.addTextChangedListener(autoAddTextWatcher);

        //checkAndClear();
        // get the input number
    }
}