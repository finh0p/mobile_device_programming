package com.example.myapplicationhtsraesry54xzdtdxchdt6excfh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import kotlin.text.Regex;

public class MainActivity extends AppCompatActivity {
    private HashMap<Integer, Boolean> validation = new HashMap<Integer, Boolean>();

    EditText date;

    Calendar dateAndTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = (EditText) findViewById(R.id.dateEdit);

        setFormatString();
        setValidation();

        setSpinner();

        initHashMap();

        ((EditText) findViewById(R.id.dateEdit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    }
                };
                DatePickerDialog dlg = new DatePickerDialog(MainActivity.this, d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                dlg.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateAndTime.set(Calendar.YEAR, year);
                        dateAndTime.set(Calendar.MONTH, monthOfYear);
                        dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        changeDate();
                    }
                });
                dlg.show();
            }
        });

    }

    protected void setValidation(){
        ((EditText) findViewById(R.id.loginEdit)).addTextChangedListener(new MyTextWatcher(R.id.loginEdit));
        ((EditText) findViewById(R.id.passwordEdit)).addTextChangedListener(new MyTextWatcher(R.id.passwordEdit));
        ((EditText) findViewById(R.id.passwordRepetition)).addTextChangedListener(new MyTextWatcher(R.id.passwordRepetition));
        ((EditText) findViewById(R.id.nameEdit)).addTextChangedListener(new MyTextWatcher(R.id.nameEdit));
        ((EditText) findViewById(R.id.surnameEdit)).addTextChangedListener(new MyTextWatcher(R.id.surnameEdit));
        ((EditText) findViewById(R.id.patronymicEdit)).addTextChangedListener(new MyTextWatcher(R.id.patronymicEdit));
        ((EditText) findViewById(R.id.emailEdit)).addTextChangedListener(new MyTextWatcher(R.id.emailEdit));
        ((EditText) findViewById(R.id.phoneEdit)).addTextChangedListener(new MyTextWatcher(R.id.phoneEdit));
        ((EditText) findViewById(R.id.dateEdit)).addTextChangedListener(new MyTextWatcher(R.id.dateEdit));
    }
    private void initHashMap(){
        validation.put(R.id.loginEdit, false);
        validation.put(R.id.passwordEdit, false);
        validation.put(R.id.passwordRepetition, false);
        validation.put(R.id.nameEdit, false);
        validation.put(R.id.surnameEdit, false);
        validation.put(R.id.patronymicEdit, false);
        validation.put(R.id.emailEdit, false);
        validation.put(R.id.dateEdit, false);
        validation.put(R.id.phoneEdit,false);
    }
    public void changeDate(){
        date.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    protected void setSpinner(){
        String[] array = getResources().getStringArray(R.array.teachersList);
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, array);
        ((Spinner) findViewById(R.id.curatorSpinner)).setAdapter(adapter);
    }
    protected void setFormatString(){
        EditText phone = (EditText) findViewById(R.id.phoneEdit);
        EditText date = (EditText) findViewById(R.id.dateEdit);

        phone.addTextChangedListener(new TextWatcher() {
            private boolean mSelfChange= false;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || mSelfChange) {
                    return;
                }

                String preparedStr = charSequence.toString();
                preparedStr = preparedStr.replaceAll("(\\D*)", "");
                String resultString = "";
                for (int j = 0; j < preparedStr.length() && j <= 10; j++){
                    switch (j){
                        case 0: resultString += "+7 ("; break;
                        case 4: resultString += ") " + preparedStr.charAt(j); break;
                        case 7:
                        case 9:
                            resultString += "-" + preparedStr.charAt(j); break;
                        default: resultString += preparedStr.charAt(j); break;
                    }
                }
                mSelfChange = true;
                phone.setText(resultString);
                phone.setSelection(resultString.length());
                mSelfChange = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        date.addTextChangedListener(new TextWatcher() {
            private boolean mSelfChange= false;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || mSelfChange) {
                    return;
                }

                String preparedStr = charSequence.toString();
                preparedStr = preparedStr.replaceAll("(\\D*)", "");
                String resultString = "";
                for (int j = 0; j < preparedStr.length() && j < 8; j++){
                    switch (j){
                        case 1:
                        case 3:
                            resultString += preparedStr.charAt(j) + "."; break;
                        default: resultString += preparedStr.charAt(j); break;
                    }
                }
                mSelfChange = true;
                date.setText(resultString);
                date.setSelection(resultString.length());
                mSelfChange = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void btnRegister(View v){
        boolean isValid = true;
        boolean personalData = ((CheckBox) findViewById(R.id.personalDataCheck)).isChecked();
        if (validation.values().contains(false)){
            isValid = false;
        }
        if (isValid && personalData){
            ((Button) findViewById(R.id.registerButton)).setText(getResources().getString(R.string.registerIsSuccess));
            ((Button) findViewById(R.id.registerButton)).setClickable(false);
            ((EditText) findViewById(R.id.loginEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.passwordEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.passwordRepetition)).setFocusable(false);
            ((EditText) findViewById(R.id.nameEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.surnameEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.patronymicEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.emailEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.phoneEdit)).setFocusable(false);
            ((EditText) findViewById(R.id.dateEdit)).setFocusable(false);
            ((Spinner) findViewById(R.id.curatorSpinner)).setClickable(false);
            ((EditText) findViewById(R.id.dateEdit)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    public class MyTextWatcher implements TextWatcher {

        int id;

        public MyTextWatcher(int id){
            this.id = id;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = String.valueOf(((EditText) findViewById(id)).getText());
            String editName = String.valueOf(((EditText) findViewById(id)).getTag());
            boolean success = false;
            try {
                switch(id){
                    case R.id.loginEdit:
                        if (text.matches(String.valueOf(new Regex("^[a-zA-Z]+$"))) && text != ""){
                            success = true;
                        }; break;
                    case R.id.passwordEdit:
                        if (text.matches(String.valueOf(new Regex("^[a-zA-Z0-9]+$"))) && text != ""){
                            success = true;
                        }; break;
                    case R.id.nameEdit:
                    case R.id.surnameEdit:
                        if (text.matches(String.valueOf(new Regex("^[А-Яа-яЁё\\s]+$"))) && text != ""){
                            success = true;
                        } break;
                    case R.id.patronymicEdit:
                        if (text.matches(String.valueOf(new Regex("^[А-Яа-яЁё\\s]+$"))) || text == null || text == ""){
                            success = true;
                        }
                    case R.id.emailEdit:
                        if (text.matches(String.valueOf(new Regex("^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$"))) && text != ""){
                            success = true;
                        } break;
                    case R.id.dateEdit:
                        if (text.matches(String.valueOf(new Regex("^[0-9]{2}.[0-9]{2}.[0-9]{4}"))) && text != ""){
                            success = true;
                        } break;
                    case R.id.passwordRepetition:
                        if (text.matches(String.valueOf(new Regex("^[a-zA-Z0-9]+$"))) &&
                                text != "" &&
                                text.equals((String.valueOf(((EditText) findViewById(R.id.passwordEdit)).getText())))
                                )
                        {
                            success = true;
                        }; break;
                    case R.id.phoneEdit:
                        if (text.matches(String.valueOf(new Regex("\\+7 \\([0-9]{3}\\) [0-9]{3}-[0-9]{2}-[0-9]{2}"))) && text != ""){
                            success = true;
                        } break;
                }

                if (success){
                    Log.i(editName + " Validation", "Success");
                    ((EditText) findViewById(id)).getBackground().mutate().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
                    validation.replace(id, true);
                } else {
                    throw new Exception(editName + " is invalid");
                }
            } catch (Exception err){
                validation.replace(id, false);
                Log.e(editName + " Validation", "Invalid");
                ((EditText) findViewById(id)).getBackground().mutate().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
}