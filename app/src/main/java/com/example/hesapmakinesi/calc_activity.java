package com.example.hesapmakinesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class calc_activity extends AppCompatActivity {

    Button button1, button2, button3, button4, button5, button6, button7,
            button8, button9, button0, butonkaresi, btnfaktoriyel,
            buttonpercent, buttonplus, buttonsub, buttondot, buttonmultiply,
            buttondivide, buttonequality, buttonclear, buttonbracket;

    TextView inputs, outputs;
    String pro, pro2;
    Double num1;
    int num2 = 2;
    boolean chcbrackets = false;
    Vibrator vibe;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.yeni_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.credits:
                opencredits();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void opencredits() {
        Intent intent = new Intent(this, credits.class);
        startActivity(intent);
    }

    @Override
    //calculator main stuff etc
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculato);
        vibe = (Vibrator) getSystemService(calc_activity.VIBRATOR_SERVICE);

        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);

        btnfaktoriyel = findViewById(R.id.factorialbtn);
        ImageButton butonrootsq = findViewById(R.id.squareroot);
        ImageButton butondelete = findViewById(R.id.silmetusu);
        butonkaresi = findViewById(R.id.butonkaresi);
        buttonpercent = findViewById(R.id.btnPercent);
        buttonplus = findViewById(R.id.btnPlus);
        buttonsub = findViewById(R.id.btnMinus);

        buttondot = findViewById(R.id.btnDot);
        buttonmultiply = findViewById(R.id.btnMultiply);
        buttondivide = findViewById(R.id.btnDivision);

        buttonequality = findViewById(R.id.btnEqual);
        buttonclear = findViewById(R.id.btnClear);
        buttonbracket = findViewById(R.id.btnBracket);


        inputs = findViewById(R.id.input);
        outputs = findViewById(R.id.output);


        //------------------------------------------------------//
        butondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputs.getText().equals("")) {
                    inputs.setText(null);
                } else {
                    int abc = inputs.getText().length();
                    String s = inputs.getText().toString();
                    if (s.charAt(abc - 1) == '.') {
                        inputs.setText(inputs.getText().subSequence(0, inputs.getText().length() - 1));
                    } else {
                        inputs.setText(inputs.getText().subSequence(0, inputs.getText().length() - 1));
                    }
                }
            }
        });


        btnfaktoriyel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

                if (pro == null) {
                    showsnackbar();
                    return;
                } else {
                    dofaktoriyel();
                    return;
                }


            }


            public void dofaktoriyel() {
                pro = inputs.getText().toString();
                inputs.setText(null);

                num1 = Double.parseDouble(pro);
                int i = Integer.parseInt(pro) - 1;

                while (i > 0) {
                    num1 = num1 * i;
                    i--;
                }
                outputs.setText(num1 + "");
                inputs.setText(pro + "!");
            }


        });

        butonrootsq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

                if (pro == null) {
                    showsnackbar();
                    return;
                } else
                    dosquareroot();
                return;
            }


            public void dosquareroot() {
                pro = inputs.getText().toString();
                num1 = Double.parseDouble(pro);
                inputs.setText("√" + pro);
                outputs.setText(Math.sqrt(num1) + "");
            }
        });


        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + ".");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);


            }
        });


        butonkaresi.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override

            public void onClick(View v) {
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

                if (pro == null) {
                    showsnackbar();
                    return;
                } else {
                    karesiniall();
                    return;
                }


            }

            public void karesiniall() {
                pro = inputs.getText().toString();
                inputs.setText("");
                num1 = Double.parseDouble(pro);
                pro2 = inputs.getText().toString();
                num2 = 2;
                inputs.setText(pro + "²");
                outputs.setText(Math.pow(num1, 2) + "");

            }
        });


        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputs.setText(null);
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);
                outputs.setText(null);
                pro = null;
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "0");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "1");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "2");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "3");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "4");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "5");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "6");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "7");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "8");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "9");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        buttonplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "+");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "-");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        buttonmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "×");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        buttondivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "÷");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        buttonpercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pro = inputs.getText().toString();
                inputs.setText(pro + "%");
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

            }
        });
        buttonbracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

                if (chcbrackets) {
                    pro = inputs.getText().toString();
                    inputs.setText(pro + ")");
                    chcbrackets = false;


                } else {
                    pro = inputs.getText().toString();
                    inputs.setText(pro + "(");
                    chcbrackets = true;
                }

            }
        });


        buttonequality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibe.vibrate(15);

                if (pro == null) {
                    showsnackbar();
                } else {
                    pro = inputs.getText().toString();
                    pro = pro.replaceAll("×", "*");
                    pro = pro.replaceAll("%", "/100");
                    pro = pro.replaceAll("÷", "/");


                    Context rhinos = Context.enter();

                    rhinos.setOptimizationLevel(-1);
                    String finalResult = "";

                    try {

                        Scriptable scriptable = rhinos.initStandardObjects();
                        finalResult =
                                rhinos.evaluateString(scriptable, pro,
                                        "javascript", 1,
                                        null).toString();


                    } catch (Exception a) {
                        finalResult = "0";
                    }
                    outputs.setText(finalResult);

                }
                 //**

            }
        });


    }

    public void showsnackbar() {
        Snackbar snack = Snackbar.make(btnfaktoriyel, "Please enter value", 2000);
        snack.show();
    }


}


