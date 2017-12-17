package com.example.administrator.study_database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.study_database.DB.DBFacade;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBFacade dbFacade;

    private EditText editText_name;
    private EditText editText_mobile;
    private TextView textView_result;
    private ListView listView;

    private ListViewCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbFacade = new DBFacade(getApplicationContext());

        editText_name = findViewById(R.id.editText_name);
        editText_mobile = findViewById(R.id.editText_mobile);
        textView_result = findViewById(R.id.textView_result);
        listView = findViewById(R.id.listView);

        cursorAdapter = new ListViewCursorAdapter(getApplicationContext(), dbFacade.getCursor(), false);

        listView.setAdapter(cursorAdapter);

        Button btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText_name.getText().toString();
                String mobile = editText_mobile.getText().toString();

                dbFacade.insertData(name, mobile);

                // 입력창 & 결과창 초기화
                editText_name.setText(null);
                editText_mobile.setText(null);
                textView_result.setText(null);

                Toast.makeText(getApplicationContext(),"Data를 입력했습니다.", Toast.LENGTH_LONG).show();


                // 이름이 null 일때 코드 구현할 것.


            }
        });

        Button btn_select = findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> selectData = dbFacade.selectData();

                cursorAdapter.changeCursor(dbFacade.getCursor());

                // 입력창 & 결과창 초기화
                // editText_name.setText(null);
                // editText_mobile.setText(null);
                // textView_result.setText(null);

                for (String string : selectData) {
                    textView_result.append(string + "\n");
                }
            }
        });

        Button btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText_name.getText().toString();
                String mobile = editText_mobile.getText().toString();

                dbFacade.updateData(name, mobile);

                textView_result.setText(null);

                Toast.makeText(getApplicationContext(),"Data를 수정했습니다.", Toast.LENGTH_LONG).show();
            }
        });

        Button btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText_name.getText().toString();

                dbFacade.deleteData(name);

                textView_result.setText(null);

                Toast.makeText(getApplicationContext(),"Data를 삭제했습니다.", Toast.LENGTH_LONG).show();
            }
        });

    }
}