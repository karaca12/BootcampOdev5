package com.example.bootcampodev5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.bootcampodev5.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button0.setOnClickListener(v -> {//Adding the character to the string
            clickButtonWrite(binding.textViewOperation.getText().toString(), "0");
        });

        binding.button1.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "1");
        });

        binding.button2.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "2");
        });

        binding.button3.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "3");
        });

        binding.button4.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "4");
        });

        binding.button5.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "5");
        });

        binding.button6.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "6");
        });

        binding.button7.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "7");
        });

        binding.button8.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "8");
        });

        binding.button9.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "9");
        });

        binding.buttonSubtraction.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "-");
        });

        binding.buttonAddition.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), "+");
        });

        binding.buttonDot.setOnClickListener(v -> {
            clickButtonWrite(binding.textViewOperation.getText().toString(), ".");
        });

        binding.buttonReset.setOnClickListener(v -> {//Reseting the string
            binding.textViewOperation.setText("");
        });

        binding.buttonDelete.setOnClickListener(v -> {//Deleting the last character of the string
            String currentText = binding.textViewOperation.getText().toString();
            if (!currentText.isEmpty()) {
                String newText = currentText.substring(0, currentText.length() - 1);
                binding.textViewOperation.setText(newText);
            }
        });

        binding.buttonEquals.setOnClickListener(v -> {//Finalizes the equation
            String currentText = binding.textViewOperation.getText().toString();
            try {
                double result = evaluateExpression(currentText);
                if (result % 1 ==0){
                    binding.textViewOperation.setText(String.valueOf((int)result));
                }else {
                    binding.textViewOperation.setText(String.valueOf(result));
                }
            } catch (ArithmeticException | IllegalArgumentException e) {
                Snackbar.make(binding.textViewOperation, "Invalid expression", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void clickButtonWrite(String currentText, String clickedButton) {//Adds the desired character to the string
        if ((currentText.length() + 1) < 85) {
            String newText = currentText + clickedButton;
            binding.textViewOperation.setText(newText);
        } else {
            Snackbar.make(binding.textViewOperation, "Maximum expression length reached", Snackbar.LENGTH_SHORT).show();
        }
    }

    private double evaluateExpression(String expression) {//the expression can't start with - or + and there is a bug when you do a subtraction 3 times in a row with decimal numbers
        String[] terms = expression.split("[+-]");
        double result=Double.parseDouble(terms[0]);

        for (int i = 1; i < terms.length; i++) {
            char operator = expression.charAt(terms[i - 1].length());
            double nextNumber = Double.parseDouble(terms[i]);

            if (operator == '+') {
                result += nextNumber;
            } else if (operator == '-') {
                result -= nextNumber;
            }
        }

        return result;
    }


}