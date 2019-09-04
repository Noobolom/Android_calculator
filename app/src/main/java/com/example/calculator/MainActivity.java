package com.example.calculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnButtonNumberClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        Button btn = (Button)view;
        String text = expression.getText().toString();
        text = text + btn.getText().toString();
        expression.setText(text);
    }

    public void OnButtonEraseAllClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        TextView result = (TextView)findViewById(R.id.Result);
        expression.setText("");
        result.setText("");
    }

    public void OnButtonPlusClick(View view) {
        TextView expression = (TextView) findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if(temp.charAt(temp.length() - 1) != '.') {
            if (temp.charAt(temp.length() - 1) == '-' || temp.charAt(temp.length() - 1) == '*' || temp.charAt(temp.length() - 1) == '+' || temp.charAt(temp.length() - 1) == '/') {
                temp = BasicCalculator.substituteString(temp, temp.length() - 1, temp.length() - 1, "+");
                expression.setText(temp);
            } else {
                temp = temp + "+";
                expression.setText(temp);
            }
        }
    }

    public void OnButtonMinusClick(View view) {
        TextView expression = (TextView) findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if(temp.charAt(temp.length() - 1) != '.') {
            if (temp.length() == 0) {
                temp = temp + "-";
                expression.setText(temp);
            } else if (temp.charAt(temp.length() - 1) == '-' || temp.charAt(temp.length() - 1) == '*' || temp.charAt(temp.length() - 1) == '+' || temp.charAt(temp.length() - 1) == '/') {
                temp = BasicCalculator.substituteString(temp, temp.length() - 1, temp.length() - 1, "-");
                expression.setText(temp);
            } else {
                temp = temp + "-";
                expression.setText(temp);
            }
        }
    }

    public void OnButtonMultiplicationClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if(temp.charAt(temp.length() - 1) != '.') {
            if (temp.charAt(temp.length() - 1) == '-' || temp.charAt(temp.length() - 1) == '*' || temp.charAt(temp.length() - 1) == '+' || temp.charAt(temp.length() - 1) == '/') {
                temp = BasicCalculator.substituteString(temp, temp.length() - 1, temp.length() - 1, "*");
                expression.setText(temp);
            } else {
                temp = temp + "*";
                expression.setText(temp);
            }
        }
    }

    public void OnButtonDivisionClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if(temp.charAt(temp.length() - 1) != '.') {
            if (temp.charAt(temp.length() - 1) == '-' || temp.charAt(temp.length() - 1) == '*' || temp.charAt(temp.length() - 1) == '+' || temp.charAt(temp.length() - 1) == '/') {
                temp = BasicCalculator.substituteString(temp, temp.length() - 1, temp.length() - 1, "/");
                expression.setText(temp);
            } else {
                temp = temp + "/";
                expression.setText(temp);
            }
        }
    }

    public void OnButtonLeftBoundClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if(!Character.isDigit(temp.charAt(temp.length() - 1)) && temp.charAt(temp.length() - 1) != '.' && temp.charAt(temp.length() - 1) != '!'){
            temp = temp + "(";
            expression.setText(temp);
        }
    }

    public void OnButtonRightBoundClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if ((QuoteAnalyser.getNumberOfLeftQuotes(temp) - QuoteAnalyser.getNumberOfRightQuotes(temp) > 0) && (Character.isDigit(temp.charAt(temp.length() - 1)) || temp.charAt(temp.length() - 1) == ')')) {
            temp = temp + ")";
            expression.setText(temp);
        }
    }

    public void OnButtonPowerClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if(Character.isDigit(temp.charAt(temp.length() - 1)) || temp.charAt(temp.length() - 1) == ')') {
            temp = temp + "^";
            expression.setText(temp);
        }
    }

    public void OnButtonSinClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if (temp.length() == 0 || !Character.isDigit(temp.charAt(temp.length() - 1)) && temp.charAt(temp.length() - 1) != '.') {
            temp = temp + "sin(";
            expression.setText(temp);
        }
    }

    public void OnButtonCosClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if (temp.length() == 0 || !Character.isDigit(temp.charAt(temp.length() - 1)) && temp.charAt(temp.length() - 1) != '.') {
            temp = temp + "cos(";
            expression.setText(temp);
        }
    }

    public void OnButtonTanClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if (temp.length() == 0 || !Character.isDigit(temp.charAt(temp.length() - 1)) && temp.charAt(temp.length() - 1) != '.') {
            temp = temp + "tan(";
            expression.setText(temp);
        }
    }

    public void OnButtonPointClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        String temp = expression.getText().toString();
        if (Character.isDigit(temp.charAt(temp.length() - 1))) {
            boolean isNumberWithoutPoint = true;
            for (int i = temp.length() - 1; i >= 0; i--){
                if (temp.charAt(i) == '+' || temp.charAt(i) == '-' || temp.charAt(i) == '*' || temp.charAt(i) == '/' || temp.charAt(i) == '(' || temp.charAt(i) == '^'){
                    break;
                } else if (temp.charAt(i) == '.'){
                    isNumberWithoutPoint = false;
                    break;
                }
            }
            if (isNumberWithoutPoint) {
                temp = temp + ".";
                expression.setText(temp);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void OnButtonEraseClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        TextView result = (TextView)findViewById(R.id.Result);
        String temp = expression.getText().toString();
        String temp2 = "";
        if (temp.length() == 0 || temp.length() == 1){
            expression.setText("");
            result.setText("");
        } else if (Character.isDigit(temp.charAt(temp.length() - 1)) && (temp.charAt(temp.length() - 2)) == '.'){
            for (int i = 0; i < temp.length() - 2; i++){
                temp2 = temp2 + temp.charAt(i);
            }
            expression.setText(temp2);
        } else if (Character.isAlphabetic(temp.charAt(temp.length() - 2)) && (temp.charAt(temp.length() - 1)) == '('){
            int StepsToErase = 1;
            for (int i = temp.length() - 2; i >= 0; i--){
                if (Character.isAlphabetic(temp.charAt(i))){
                    ++StepsToErase;
                } else break;
            }
            for (int i = 0; i < temp.length() - StepsToErase; i++){
                temp2 = temp2 + temp.charAt(i);
            }
            expression.setText(temp2);
        } else {
            for (int i = 0; i < temp.length() - 1; i++) {
                temp2 = temp2 + temp.charAt(i);
            }
            expression.setText(temp2);
        }
    }

    public void OnButtonEqualClick(View view){
        TextView expression = (TextView)findViewById(R.id.Expression);
        TextView result = (TextView)findViewById(R.id.Result);
        String Example = expression.getText().toString();
        if (QuoteAnalyser.CheckQuotes(Example) && !Example.equals("")) {
            expression.setText(Example);
            String Result = "";
            try {
                Result = BasicCalculator.Calculate(Example);
                Result = "= " + Result;
                result.setText(Result);
            } catch (Exception e){
                Toast.makeText(MainActivity.this, "Неверный ввод", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Неверный ввод", Toast.LENGTH_LONG).show();
        }
    }
}
