package com.example.forthmobileapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText textURI;
    EditText textPort;
    ToggleButton buttonConnect;
    Button buttonLeft, buttonRight, buttonForward, buttonBackward, buttonStop;
    MultiAutoCompleteTextView forthView;
    ArrayList<String> words = new ArrayList<>();
    SeekBar seekBarSpeed;
    ForthmobileModel model = new ForthmobileModel();
    String changedText = "";
    int start = 0;
    int count = 0;
    int length = 0;
    int oldLength = 0;
    int newlength = 0;

    void logTextChange(String tag, String message) {
        Log.i(tag, "\"" + message + "\" start:" + start + " count:" + count + " length:" + length + " oldlength:" + oldLength + " newlength:" + newlength + " changedText:\"" + changedText + "\"");
    }

    void addAll() {
        words.add("IMMEDIATE");
        words.add("COMPILE-ONLY");
        words.add("(");
        words.add("\"");
        words.add(".(");
        words.add("CONSTANT");
        words.add("VARIABLE");
        words.add("CREATE");
        words.add("CODE");
        words.add("ELSE");
        words.add("AFT");
        words.add("REPEAT");
        words.add("AHEAD");
        words.add("IF");
        words.add("AGAIN");
        words.add("UNTIL");
        words.add("NEXT");
        words.add("BEGIN");
        words.add("FOR");
        words.add("THEN");
        words.add("KKK");
        words.add("PPPP");
        words.add("TYPEE");
        words.add("EMITT");
        words.add("PPP");
        words.add("P1IN");
        words.add("P0IN");
        words.add("P1ENC");
        words.add("P1ENS");
        words.add("P1EN");
        words.add("P0ENC");
        words.add("P0ENS");
        words.add("\"");
        words.add("P0EN");
        words.add("P1C");
        words.add("P1S");
        words.add("P1");
        words.add("P0C");
        words.add("P0S");
        words.add("P0");
        words.add("PP");
        words.add("LINE");
        words.add("COLD");
        words.add("BOOT");
        words.add("FORGET");
        words.add("WORDS");
        words.add(".ID");
        words.add(">NAME");
        words.add("DUMP");
        words.add("dm+");
        words.add(";");
        words.add("\"");
        words.add(":");
        words.add("]");
        words.add("OVERT");
        words.add("$COMPILE");
        words.add("COMPILE");
        words.add("[COMPILE]");
        words.add("'");
        words.add("$,n");
        words.add("?UNIQUE");
        words.add("$,");
        words.add("ALLOT");
        words.add("LITERAL");
        words.add(");");
        words.add("LOAD");
        words.add("QUIT");
        words.add("EVAL");
        words.add(".OK");
        words.add("[");
        words.add("$INTERPRET");
        words.add("ERROR");
        words.add("abort");
        words.add("ABORT");
        words.add("QUERY");
        words.add("EXPECT");
        words.add("NAME?");
        words.add("find");
        words.add("SAME?");

        words.add("NAME>");
        words.add("WORD");
        words.add("TOKEN");
        words.add("PARSE");
        words.add("PACK$");
        words.add("(parse)");
        words.add("?");
        words.add(".");
        words.add("U.");
        words.add("U.R");
        words.add(".R");
        words.add(".\"|");
        words.add("@$\"|");
        words.add("do$");
        words.add("CR");
        words.add("TYPE");
        words.add("SPACES");
        words.add("CHARS");
        words.add("SPACE");
        words.add("NUMBER?");
        words.add("DIGIT?");
        words.add(">upper");
        words.add("wupper");
        words.add("UMASK");
        words.add("DECIMAL");
        words.add("HEX");
        words.add("str");
        words.add("#>");
        words.add("SIGN");
        words.add("#S");
        words.add("#");
        words.add("HOLD");
        words.add("<#");
        words.add("EXTRACT");
        words.add("DIGIT");
        words.add("FILL");
        words.add("MOVE");
        words.add("CMOVE");
        words.add("@EXECUTE");
        words.add("TIB");
        words.add("PAD");
        words.add("HERE");
        words.add("ALIGNED");
        words.add(">CHAR");
        words.add("WITHIN");
        words.add("KEY");
        words.add("BYE");
        words.add("2/");
        words.add("2*");
        words.add("2-");
        words.add("2+");
        words.add("1-");
        words.add("1+");
        words.add("CELL/");
        words.add("CELLS");
        words.add("CELL-");
        words.add("CELL+");
        words.add("CELL");
        words.add("BL");
        words.add("file-size");
        words.add("resize-file");
        words.add("reposition-file");
        words.add("file-position");
        words.add("read-file");
        words.add("write-file");
        words.add("delete-file");
        words.add("create-file");
        words.add("open-file");
        words.add("close-file");
        words.add("bin");
        words.add("w/o");
        words.add("r/w");
        words.add("r/o");
        words.add("TERMINATE");
        words.add("MS");
        words.add("FREQ");
        words.add("DUTY");
        words.add("PIN");
        words.add("ADC");
        words.add("PEEK");
        words.add("POKE");
        words.add("sendPacket");
        words.add("TONE");
        words.add("MIN");
        words.add("MAX");
        words.add("DOVAR");
        words.add("COUNT");
        words.add("2@");
        words.add("2!");
        words.add("+!");
        words.add("PICK");
        words.add("*/");
        words.add("*/MOD");
        words.add("M*");
        words.add("*");
        words.add("UM*");
        words.add("/");
        words.add("MOD");
        words.add("/MOD");
        words.add("M/MOD");
        words.add("UM/MOD");
        words.add("<");
        words.add("U<");
        words.add("=");
        words.add("ABS");
        words.add("-");
        words.add("DNEGATE");
        words.add("NEGATE");
        words.add("INVERSE");
        words.add("+");
        words.add("2DUP");
        words.add("2DROP");
        words.add("ROT");
        words.add("?DUP");
        words.add("NEXT");
        words.add("U+");
        words.add("XOR");
        words.add("OR");
        words.add("AND");
        words.add("0<");
        words.add("OVER");
        words.add("SWAP");
        words.add("DUP");
        words.add("DROP");
        words.add("SPSTO");
        words.add("SPAT");
        words.add(">R");
        words.add("R@");
        words.add("R>");
        words.add("RPSTO");
        words.add("RPAT");
        words.add("C@");
        words.add("C!");
        words.add("@");
        words.add("!");
        words.add("BRANCH");
        words.add("QBRANCH");
        words.add("DONEXT");
        words.add("EXECUTE");
        words.add("EXIT");
        words.add("DOLIST");
        words.add("DOLIT");
        words.add("DOCON");
        words.add("EMIT");
        words.add("?KEY");
        words.add("ACCEPT");
        words.add("NOP");
        words.add("BASE");
        words.add("tmp");
        words.add("#TIB");
        words.add(">IN");
        words.add("SPAN");
        words.add("HLD");
        words.add("'ABORT");
        words.add("'EVAL");
        words.add("LAST");
        words.add("CP");
        words.add("CONTEXT");
        words.add("FIB");
        words.add("#FIB");
        words.add("'TIB");
    }

    boolean blockOnTextChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textURI = findViewById(R.id.editTextURI);
        textPort = findViewById(R.id.editTextPortNumber);
        buttonConnect = findViewById(R.id.toggleButton);
        buttonLeft = findViewById(R.id.buttonLeft);
        buttonRight = findViewById(R.id.buttonRight);
        buttonForward = findViewById(R.id.buttonForward);
        buttonBackward = findViewById(R.id.buttonBackward);
        buttonStop = findViewById(R.id.buttonStop);
        forthView = findViewById(R.id.multiAutoCompleteTextView);
        seekBarSpeed = findViewById(R.id.seekBarSpeed);

        buttonConnect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (model.isConnected()) {
                    model.disconnect();
                } else {
                    model.connect(textURI.getText().toString(), Integer.parseInt(textPort.getText().toString()));
                }
            }
        });

        ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, words);
        forthView.setAdapter(ad);
        addAll();

        forthView.setTokenizer(new MultiAutoCompleteTextView.Tokenizer() {
            @Override
            public int findTokenStart(CharSequence text, int cursor) {

                for (int i = cursor - 2; i > 0; i--) { // words are at least one character long. Not fool proof.
                    if (Character.isWhitespace(text.charAt(i))) {
                        // Log.i("TokenStart", text.toString() + " " + i);
                        return i + 1;
                    }
                }
                return 0;
            }

            @Override
            public int findTokenEnd(CharSequence text, int cursor) {
                // Log.i("TokenEnd", text.toString() + " " + cursor);
                for (int i = cursor - 1; i > 0; i--) {
                    if (!Character.isWhitespace(text.charAt(i))) {
                        return i;
                    }
                }
                return cursor;
            }

            @Override
            public CharSequence terminateToken(CharSequence text) {
                // Log.i("TerminateToken", text.toString());
                return text;
            }
        });


        forthView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int pstart, int pcount, int pafter) {
                changedText = s.toString().substring(pstart);
                start = pstart;
                count = pcount;
                newlength = pafter;
                logTextChange("beforeTextChanged", s.toString());
            }

            @Override
            public void onTextChanged(CharSequence es, int pstart, int pbefore, int pcount) {
                changedText = es.toString().substring(pstart);
                start = pstart;
                oldLength = pbefore;
                count = pcount;
                logTextChange("onTextChanged", es.toString());
            }

            @Override
            public void afterTextChanged(Editable es) {
                String s = es.toString();

                logTextChange("afterTextChanged", s);
                if (changedText.endsWith("\n")) {
                    for (int i = start; i > 0; i--) {
                        if (s.charAt(i) == '\n') {
                            Log.i("SENDING", s.substring(i));
                            break;
                        }
                    }
                }
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forthView.append("" +
                        seekBarSpeed.getProgress() + " " +
                        seekBarSpeed.getProgress() + " " +
                        "forward drive\n");
            }
        });

        buttonBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forthView.append("" +
                        seekBarSpeed.getProgress() + " " +
                        seekBarSpeed.getProgress() + " " +
                        "backward drive\n");
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forthView.append("motor_stop\n");
            }
        });
    }
}