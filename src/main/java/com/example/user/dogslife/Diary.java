package com.example.user.dogslife;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Diary extends AppCompatActivity {

    private ListView                m_ListView;
    private MemberDataAdapter       m_adapter;
    private ArrayList<DiaryList>    m_DiaryList = new ArrayList() ;
    private Button                  mBtPlus ;
    private SQLiteDatabase db;
    private String dbName = "idList.db"; // name of Database;
    private String tableName = "idListTable"; // name of Table;
    private int dbMode = Context.MODE_PRIVATE;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { // 인텐트로 받는다.
        super.onActivityResult(requestCode, resultCode, data);
        DiaryList temp = new DiaryList();
        try {
            temp.SetDiary(data.getStringExtra("NOTE"));
            temp.SetDate(data.getStringExtra("DATE")); // 메인 액티비티로 넘긴다.
            temp.SetCategory(data.getStringExtra("CATEGORY"));
            temp.SetTime(data.getStringExtra("TIME"));

            insertData(temp);
            m_DiaryList.clear() ;
            selectAll();
            m_adapter.notifyDataSetChanged();
        }
        catch (Exception e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setTitle("Diary"); // Title을 바꿈.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 액션바를 보이게 하기
        db = openOrCreateDatabase(dbName, dbMode, null);
        createTable() ; // 테이블 생성.
        selectAll(); // DB에 담겨진 내용들을 ArrayList -> ArrayList<DiaryList>  에 담음

        mBtPlus = (Button)findViewById(R.id.Plus) ;
        mBtPlus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Select one category", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Diary.this , Write.class);
                startActivityForResult(intent, 1) ;
            }
        });
        // 어댑터를 생성하는데 기존에 가지고 있는 안드로이드에서 제공하는 것과 다르게 이미지 생성을 위해 새로 만든다.
        m_adapter= new MemberDataAdapter( getLayoutInflater() , m_DiaryList);
        m_ListView = (ListView) findViewById(R.id.listview);
        m_ListView.setOnItemClickListener(onClickListItem) ; // 리스트뷰를 클릭했을 때에 대한 이벤트
        m_ListView.setAdapter(m_adapter); // 리스트를 처리할 어댑터를 연결시킴
        for (int x = 0; x < m_DiaryList.size(); x++) {
            m_adapter.getView(x, null, null); // 화면에 띄우기.
        }
    }

    @Override // 액션바 뒤로가기.
    public boolean onOptionsItemSelected(MenuItem item) { // 액션바 클릭 리스너. 액션바에 종류가 여러개라면 오버라이딩이후 조절해야함
        finish();
        return true;
    }

    // 어댑터 클릭 리스너
    private AdapterView.OnItemClickListener onClickListItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
            new AlertDialog.Builder(Diary.this).setTitle(m_DiaryList.get(arg2).GetDiary()).setMessage(m_DiaryList.get(arg2).GetDate()).setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dlg, int sumthin) {
                    new AlertDialog.Builder(Diary.this).setTitle("정말로 삭제하시겠습니까?").setNeutralButton("삭제", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int TEMP) {
                                    removeData(m_DiaryList.get(arg2).GetId());
                                    m_DiaryList.clear(); // 삭제후에는 해야됨.
                                    selectAll();
                                    m_adapter.notifyDataSetChanged();
                                }
                            }
                    ).setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) { }
                    }).show();
                }
            })
                    .setNegativeButton("수정", new DialogInterface.OnClickListener() {
                        // 취소 버튼 클릭시 설정
                        public void onClick(DialogInterface dialog, int whichButton) { }
                    }).show() ;

        }
    };

    //DB 생성
    public void createTable() {
        try {
            String sql = "CREATE TABLE " + tableName + "(id integer primary key autoincrement, my_Diary TEXT, my_Category TEXT, my_Date TEXT, my_Time TEXT)";
            Log.d("Lab sqlite", "생성되었습니다. :  ");
            db.execSQL(sql);
        } catch (android.database.sqlite.SQLiteException e) {
            Log.d("Lab sqlite", "error 입니다. :  " + e);
        }
    }
    // Data 추가
    public void insertData(DiaryList Temp) {
        String sql = "INSERT INTO " + tableName + " VALUES (NULL, '" + Temp.GetDiary() + "', '" + Temp.GetCategory() + "', '" + Temp.GetDate() + "', '" + Temp.GetTime() + "');";
        db.execSQL(sql);
    }
    // Data 삭제
    public void removeData(int index) {
        try {
            String sql = "delete from " + tableName + " where id = " + index + ";";
            db.execSQL(sql);
        }
        catch (Exception e) { }
    }
    // 보여주기.
    public void selectAll() {
        String sql = "select * from " + tableName + ";"; // 오도바이 디크 , 디비 셀렉트 정렬 쳐서
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            DiaryList temp = new DiaryList() ;
            temp.SetId(results.getInt(0));
            temp.SetDiary(results.getString(1)); ;
            temp.SetCategory(results.getString(2)); ;
            temp.SetDate(results.getString(3));
            temp.SetTime(results.getString(4)) ;

            Log.d("lab_sqlite", "index= " + results.getInt(0) + " title=" + results.getString(1) + " " + results.getString(2) + " " + results.getString(3)) ;
            m_DiaryList.add(temp);
            results.moveToNext();
        }
        results.close();
    }
}
