package cheng.hollis.dicepredictor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView LVDiceResult, LVDicePredictor;
    private ArrayList<DiceObject> dicePredictAL = new ArrayList<>();
    private ArrayList<DiceResult> dicResultAL = new ArrayList<>();
    private DiceReslutListAdapter diceReslutListAdapter;
    private DicePredictorListAdapter dicePredictorListAdapter;
    private Boolean IsPredictable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        LVDiceResult = (ListView) findViewById(R.id.LVDiceResult);
        LVDicePredictor = (ListView) findViewById(R.id.LVDicePredictor);
        final EditText ETDice1 = (EditText) findViewById(R.id.ETDice1);
        final EditText ETDice2 = (EditText) findViewById(R.id.ETDice2);
        final EditText ETDice3 = (EditText) findViewById(R.id.ETDice3);
        Button BtnDiceResultSubmit = (Button) findViewById(R.id.BtnDiceResultSubmit);
        Button BtnResultClear = (Button) findViewById(R.id.BtnResultClear);
        Button BtnCal = (Button) findViewById(R.id.BtnCal);
        Button BtnPredictClear = (Button) findViewById(R.id.BtnPredictClear);

        initPredictorAL();

        BtnDiceResultSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ResultDice1 = Integer.parseInt(ETDice1.getText().toString());
                int ResultDice2 = Integer.parseInt(ETDice2.getText().toString());
                int ResultDice3 = Integer.parseInt(ETDice3.getText().toString());
                if (ResultDice1 > 6 || ResultDice1 < 1 ||
                        ResultDice2 > 6 || ResultDice2 < 1 ||
                        ResultDice3 > 6 || ResultDice3 < 1) {
                    Toast.makeText(MainActivity.this, "Please Input 1-6!", Toast.LENGTH_LONG).show();
                } else {
                    dicResultAL.add(new DiceResult(ResultDice1, ResultDice2, ResultDice3));
                    Log.v("TEST", "ResultDice1=" + ResultDice1 + ",ResultDice2=" + ResultDice2 + ",ResultDice3=" + ResultDice3);
                    diceReslutListAdapter = new DiceReslutListAdapter(MainActivity.this, dicResultAL);
                    LVDiceResult.setAdapter(diceReslutListAdapter);
                    IsPredictable = true;
                }
            }
        });

        BtnResultClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dicResultAL = new ArrayList<>();
                diceReslutListAdapter = new DiceReslutListAdapter(MainActivity.this, dicResultAL);
                LVDiceResult.setAdapter(diceReslutListAdapter);
            }
        });

        BtnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsPredictable) {
                    dicePredictAL = new ArrayList<>();
                    initPredictorAL();
                    CalPredictor();
                    dicePredictorListAdapter = new DicePredictorListAdapter(MainActivity.this, dicePredictAL);
                    LVDicePredictor.setAdapter(dicePredictorListAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Please Input more than one result", Toast.LENGTH_LONG).show();
                }
            }
        });

        BtnPredictClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dicePredictAL = new ArrayList<>();
                IsPredictable = false;
                initPredictorAL();
                dicePredictorListAdapter = new DicePredictorListAdapter(MainActivity.this, dicePredictAL);
                LVDicePredictor.setAdapter(dicePredictorListAdapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
        confirmLeaveDialog();
    }

    private void initPredictorAL() {
        //create 1-6 dice objects
        for (int i = 1; i <= 6; i++) {
            dicePredictAL.add(new DiceObject(i, 0, 0, 0, 0, 0, 0));
        }
    }

    private void CalPredictor() {
        for (int i = 1; i < dicResultAL.size(); i++) {
            int oldResultPosition = i - 1;

            //old 1 -> new 1
            switch (dicResultAL.get(i).getThisRd1()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeSix() + 1);
                    break;
            }

            //old 1 -> new2
            switch (dicResultAL.get(i).getThisRd2()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeSix() + 1);
                    break;
            }

            //old 1 -> new3
            switch (dicResultAL.get(i).getThisRd3()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd1() - 1).getBecomeSix() + 1);
                    break;
            }

            //old 2 -> new1
            switch (dicResultAL.get(i).getThisRd1()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeSix() + 1);
                    break;
            }

            //old 2 -> new 2
            switch (dicResultAL.get(i).getThisRd2()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeSix() + 1);
                    break;
            }

            //old 2 -> new3
            switch (dicResultAL.get(i).getThisRd3()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd2() - 1).getBecomeSix() + 1);
                    break;
            }

            //old3 -> new1
            switch (dicResultAL.get(i).getThisRd1()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeSix() + 1);
                    break;
            }

            //old3 -> new2
            switch (dicResultAL.get(i).getThisRd2()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeSix() + 1);
                    break;
            }

            //old3 -> new3
            switch (dicResultAL.get(i).getThisRd3()) {
                case 1:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeOne(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeOne() + 1);
                    break;
                case 2:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeTwo(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeTwo() + 1);
                    break;
                case 3:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeThree(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeThree() + 1);
                    break;
                case 4:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeFour(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeFour() + 1);
                    break;
                case 5:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeFive(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeFive() + 1);
                    break;
                case 6:
                    dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).setBecomeSix(
                            dicePredictAL.get(dicResultAL.get(oldResultPosition).getThisRd3() - 1).getBecomeSix() + 1);
                    break;
            }
        }
        Log.v("this", "dicePredictAL=" + dicePredictAL.toString());
    }

    private void confirmLeaveDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Quit?");
        alertDlg.setCancelable(true);

        alertDlg.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDlg.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//do wt u want after click No
            }
        });

        alertDlg.create().show();

    }
}
