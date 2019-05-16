package edu.uiowa.besttunes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    Button btnHit;
    TextView searchArtist;
    public static List<String> trackArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lv = (ListView) findViewById(R.id.list1);
        btnHit = (Button) findViewById(R.id.searchButton);
        searchArtist = (TextView) findViewById(R.id.artistText);
        searchArtist.setImeOptions(EditorInfo.IME_ACTION_DONE);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                trackArray );
        lv.setAdapter(arrayAdapter);

        btnHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchString = searchArtist.getText().toString();
                fetchData process = new fetchData(searchString);
                process.execute();
                arrayAdapter.notifyDataSetChanged();
                Log.d("SONGLIST", trackArray.toString());
            }
        });

        searchArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchArtist.setText("");

            }
        });

        searchArtist.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager imm = (InputMethodManager)textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

    }
}
