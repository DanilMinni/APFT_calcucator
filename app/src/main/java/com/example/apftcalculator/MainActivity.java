package com.example.apftcalculator;



import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    public TextView mTextView;
    public TextView mSitTextView;
    public TextView mRunMinTextView;
    public TextView mRunSecTextView;
    public TextView pushScoreTV;
    public TextView FinalScoreTV;
    public TextView sitScoreTV;
    public TextView runScoreTV;
    public  SeekBar seekBar_push_ups;
    public  SeekBar seekBar_sit_ups;
    public  SeekBar seekBar_run_min;
    public  SeekBar seekBar_run_sec;
    public RadioButton radioButtonMale;
    public RadioButton radioButtonFemale;
    public RadioGroup radioGroup;
    public Spinner spinner;
    public int push_score;
    public int sit_score;
    public int age;
    public int pushupNum;
    public int situpNum;
    public int runMinutes;
    public int runSeconds;
    public int gender;
    public int run, runScore;
    public FileReader fileReader;
    String[] data = {"17-21", "22-26", "27-31", "32-36", "37-41", "42-46", "47-51", "52-56", "57-61", "62+"};
    public int[] pushs = {0, 3, 5, 6, 6, 8, 9, 10, 12, 13, 14, 16, 17, 19, 20, 21, 23, 24, 26, 27, 28, 30, 31, 32, 34, 35, 37, 38, 39, 41, 42, 43, 45, 46, 48, 49, 50, 52, 53, 54, 56, 57, 59, 60, 61, 63, 64, 66, 67, 68, 70, 71, 72, 74, 75, 77, 78, 79, 81, 82, 83, 85, 86, 88, 89, 90, 92, 93, 94, 96, 97, 99, 100, 100, 100, 100, 100, 100, 100 };
    public int[] sits = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 10, 12, 14, 15, 17, 18, 20, 22, 23, 25, 26, 28, 30, 31, 33, 34, 36, 38, 39, 41, 42, 44, 46, 47, 49, 50, 52, 54, 55, 57, 58, 60, 62, 63, 65, 66, 68, 70, 71, 73, 74, 76, 78, 79, 81, 82, 84, 86, 87, 89, 90, 92, 94, 95, 97, 98, 100};
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)findViewById(R.id.push_up_text_view);
        mSitTextView = (TextView)findViewById(R.id.sit);
        mRunMinTextView = (TextView)findViewById(R.id.run_min);
        mRunSecTextView = (TextView)findViewById(R.id.run_sec);
        pushScoreTV = (TextView)findViewById(R.id.push_up_score);
        sitScoreTV = (TextView)findViewById(R.id.sit_up_TV);
        runScoreTV = (TextView)findViewById(R.id.run_TV);
        FinalScoreTV = (TextView)findViewById(R.id.final_score);

        seekBar_push_ups = (SeekBar)findViewById(R.id.push_ups_seek_bar);
        seekBar_sit_ups =(SeekBar)findViewById(R.id.sit_ups_seek_bar) ;
        seekBar_run_min =(SeekBar)findViewById(R.id.run_min_seek_bar) ;
        seekBar_run_sec =(SeekBar)findViewById(R.id.run_sec_seek_bar) ;

        seekBar_push_ups.setOnSeekBarChangeListener(this);
        seekBar_push_ups.setMax(77);


        seekBar_sit_ups.setOnSeekBarChangeListener(this);
        seekBar_sit_ups.setMax(78);



        seekBar_run_min.setOnSeekBarChangeListener(this);
        seekBar_run_min.setMax(26);
//        seekBar_run_min.setMin(13);


        seekBar_run_sec.setOnSeekBarChangeListener(this);
        seekBar_run_sec.setMax(59);



        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioButton radioButtonMale = (RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonMale.setOnClickListener(selectGender);
        RadioButton radioButtonFemale = (RadioButton) findViewById(R.id.radioButtonFemale);
        radioButtonFemale.setOnClickListener(selectGender);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Age");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                switch (position){
                    case 0:
                        age = 17;
                        break;
                    case 1:
                        age = 22;
                        break;
                    case 2:
                        age = 27;
                        break;
                    case 3:
                        age = 32;
                        break;
                    case 4:
                        age = 37;
                        break;
                    case 5:
                        age = 42;
                        break;
                    case 6:
                        age = 47;
                        break;
                    case 7:
                        age = 52;
                        break;
                    case 8:
                        age = 57;
                        break;
                    case 9:
                        age = 62;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                age = 17;
            }
        });

    }

    public void pushScore(){
        String fileName = null;

        if (gender == 1) {
            if (age >= 62) {
                fileName = "MalePU62+.txt";
            }
            else if (age >= 57) {
                fileName = "MalePU57-61.txt";
            }
            else if (age >= 52) {
                fileName = "MalePU52-56.txt";
            }
            else if (age >= 47) {
                fileName = "MalePU47-51.txt";
            }
            else if (age >= 42) {
                fileName = "MalePU42-46.txt";
            }
            else if (age >= 37) {
                fileName = "MalePU37-41.txt";
            }
            else if (age >= 32) {
                fileName = "MalePU32-36.txt";
            }
            else if (age >= 27) {
                fileName = "MalePU27-31.txt";
            }
            else if (age >= 22) {
                fileName = "MalePU22-26.txt";
            }
            else {
                fileName = "MalePU17-21.txt";
            }
        }
        else {
            if (age >= 62) {
                fileName = "FemalePU62+.txt";
            }
            else if (age >= 57) {
                fileName = "FemalePU57-61.txt";
            }
            else if (age >= 52) {
                fileName = "FemalePU52-56.txt";
            }
            else if (age >= 47) {
                fileName = "FemalePU47-51.txt";
            }
            else if (age >= 42) {
                fileName = "FemalePU42-46.txt";
            }
            else if (age >= 37) {
                fileName = "FemalePU37-41.txt";
            }
            else if (age >= 32) {
                fileName = "FemalePU32-36.txt";
            }
            else if (age >= 27) {
                fileName = "FemalePU27-31.txt";
            }
            else if (age >= 22) {
                fileName = "FemalePU22-26.txt";
            }
            else {
                fileName = "FemalePU17-21.txt";
            }
        }
        try{
            AssetManager assetManager = this.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open(fileName));
            BufferedReader reader = new BufferedReader(istream);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                if(data.length == 2){
                    int pushUp = Integer.parseInt(data[0]);
                    int pushUpScore = Integer.parseInt(data[1]);
                    if(pushupNum < 5){
                        push_score = 0;
                    } else if(pushupNum > 77){
                        push_score = 100;
                    } else if (pushUp == pushupNum){
                        push_score = pushUpScore;
                        pushScoreTV.setText(String.valueOf(push_score));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void situpScore(){
        String fileName = null;


        if (age >= 62) {
            fileName = "SU62+.txt";
        }
        else if (age >= 57) {
            fileName = "SU57-61.txt";
        }
        else if (age >= 52) {
            fileName = "SU52-56.txt";
        }
        else if (age >= 47) {
            fileName = "SU47-51.txt";
        }
        else if (age >= 42) {
            fileName = "SU42-46.txt";
        }
        else if (age >= 37) {
            fileName = "SU37-41.txt";
        }
        else if (age >= 32) {
            fileName = "SU32-36.txt";
        }
        else if (age >= 27) {
            fileName = "SU27-31.txt";
        }
        else if (age >= 22) {
            fileName = "SU22-26.txt";
        }
        else {
            fileName = "SU17-21.txt";
        }
        try{
            AssetManager assetManager = this.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open(fileName));
            BufferedReader reader = new BufferedReader(istream);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                if(data.length == 2){
                    int sitUp = Integer.parseInt(data[0]);
                    int sitUpScore = Integer.parseInt(data[1]);
                    if(situpNum < 5){
                        sit_score = 0;
                    } else if(situpNum > 77){
                        sit_score = 100;
                    } else if (sitUp == situpNum){
                        sit_score = sitUpScore;
                        sitScoreTV.setText(String.valueOf(sit_score));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runScore(){
        String fileName = null;

        if (gender == 1) {
            if (age >= 62) {
                fileName = "MaleRUN62+.txt";
            }
            else if (age >= 57) {
                fileName = "MaleRUN57-61.txt";
            }
            else if (age >= 52) {
                fileName = "MaleRUN52-56.txt";
            }
            else if (age >= 47) {
                fileName = "MaleRUN47-51.txt";
            }
            else if (age >= 42) {
                fileName = "MaleRUN42-46.txt";
            }
            else if (age >= 37) {
                fileName = "MaleRUN37-41.txt";
            }
            else if (age >= 32) {
                fileName = "MaleRUN32-36.txt";
            }
            else if (age >= 27) {
                fileName = "MaleRUN27-31.txt";
            }
            else if (age >= 22) {
                fileName = "MaleRUN22-26.txt";
            }
            else {
                fileName = "MaleRUN17-21.txt";
            }
        }
        else {
            if (age >= 62) {
                fileName = "FemaleRUN62+.txt";
            }
            else if (age >= 57) {
                fileName = "FemaleRUN57-61.txt";
            }
            else if (age >= 52) {
                fileName = "FemaleRUN52-56.txt";
            }
            else if (age >= 47) {
                fileName = "FemaleRUN47-51.txt";
            }
            else if (age >= 42) {
                fileName = "FemaleRUN42-46.txt";
            }
            else if (age >= 37) {
                fileName = "FemaleRUN37-41.txt";
            }
            else if (age >= 32) {
                fileName = "FemaleRUN32-36.txt";
            }
            else if (age >= 27) {
                fileName = "FemaleRUN27-31.txt";
            }
            else if (age >= 22) {
                fileName = "FemaleRUN22-26.txt";
            }
            else {
                fileName = "FemaleRUN17-21.txt";
            }
        }
        try{
            AssetManager assetManager = this.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open(fileName));
            BufferedReader reader = new BufferedReader(istream);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\t");

                if(data.length == 2){
                    int run = Integer.parseInt(data[0]);
                    int runScore = Integer.parseInt(data[1]);
                    if(this.run > 2630){
                        this.runScore = 0;
                    } else if(this.run < 1300){
                        this.runScore = 100;
                    } else if (run == this.run){
                        this.runScore = runScore;
                        runScoreTV.setText(String.valueOf(this.runScore));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    View.OnClickListener selectGender = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            switch (rb.getId()){
                case R.id.radioButtonMale:
                    gender = 1;
                    break;
                case R.id.radioButtonFemale:
                    gender = 2;
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Choose gender", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        int push = seekBar_push_ups.getProgress();
        pushupNum = push;
        mTextView.setText(String.valueOf(push));
        if(push == 0) pushScoreTV.setText(String.valueOf(0));
        pushScore();

        int sit = seekBar_sit_ups.getProgress();
        situpNum = sit;
        mSitTextView.setText(String.valueOf(sit));
        if(sit == 0) sitScoreTV.setText(String.valueOf(0));
        situpScore();

        int run_min = seekBar_run_min.getProgress();
        mRunMinTextView.setText(String.valueOf(run_min));
        runMinutes = run_min;

        int run_sec = seekBar_run_sec.getProgress();
        mRunSecTextView.setText(String.valueOf(run_sec));
        runSeconds = run_sec;

        int remainder = runSeconds % 6;
        int formattedSecs;
        int formattedMinutes;
        formattedMinutes = runMinutes * 100;
        if (remainder == 0)
        {
            formattedSecs = runSeconds;
        }
        else
        {
            formattedSecs = (runSeconds - remainder) + 6;
        }
        run = formattedSecs + formattedMinutes;

        runScore();
        System.out.println(run);
        FinalScoreTV.setText(String.valueOf(push_score + runScore + sit_score));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {



    }

    private  int sit_up_score(int sit){
       sitScoreTV.setText(String.valueOf(sits[sit]));
       return sits[sit];
    }

    }

