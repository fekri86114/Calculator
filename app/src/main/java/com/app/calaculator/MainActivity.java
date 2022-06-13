package com.app.calaculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    private EditText display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display_edittext);

        display.setOnClickListener(view -> {
            if (getString(R.string.display).equals(display.getText().toString())){
                display.setText("");
                display.setShowSoftInputOnFocus(false);
            }
        });

    }

    private void updateText(String strToAdd){

        String oldStr = display.getText().toString();
        int cursorPost = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPost);
        String rightStr = oldStr.substring(cursorPost);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPost + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPost + 1);
        }
    }

    public void zeroButton(View view) {
        updateText("0");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void clearButton(View view) {
        display.setText("");
    }

    public void parenthesesButton(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i+1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")) {
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void exponentButton(View view) {
        updateText("^");
    }

    public void divideButton(View view) {
        updateText("÷");
    }

    public void backspaceButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void multiplyButton(View view) {
        updateText("×");
    }

    public void minusButton(View view) {
        updateText("-");
    }

    public void addButton(View view) {
        updateText("+");
    }

    public void pointButton(View view) {
        updateText(".");
    }

    public void equalsButton(View view) {
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf((exp.calculate()));

        display.setText(result);
        display.setSelection(result.length());
    }
    public void plusMinus(View view) {
        updateText("-");
    }

}