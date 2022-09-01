// 과제명: 다이어리앱
// 분반: 02분반
// 학번: 20201017 성명: 정유영
// 제출일: 2022년 6월 22일

package ddwucom.mobile.finalreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";

    DiaryDBManager diaryDBManager;
    private ArrayList<Diary> diaryList;
    private DiaryAdapter diaryAdapter;
    private ListView listView;
//    ImageView feeling_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity!!");
        listView = findViewById(R.id.listView);
        diaryList = new ArrayList<Diary>();

        diaryAdapter = new DiaryAdapter(this, R.layout.activity_diary, diaryList);
        listView.setAdapter(diaryAdapter);
        diaryDBManager = new DiaryDBManager(this);

        listView.setOnItemClickListener(onItemClickListener);
        listView.setOnItemLongClickListener(onItemLongClickListener);

//        feeling_pic = findViewById(R.id.imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        diaryList.clear();
        diaryList.addAll(diaryDBManager.getAllDiary());

//        feeling_pic.setImageAlpha(R.mipmap.laugh);
//        for(int i=0; i< diaryList.size(); i++) {
//            switch(diaryList.get(i).getFeeling()) {
//                case "good":
//                    feeling_pic.setImageAlpha(R.mipmap.good);
//                    break;
//                case "bad":
//                    feeling_pic.setImageAlpha(R.mipmap.bad);
//                    break;
//                case "angry":
//                    feeling_pic.setImageAlpha(R.mipmap.angry);
//                    break;
//                case "sad":
//                    feeling_pic.setImageAlpha(R.mipmap.sad);
//                    break;
//                case "surprised":
//                    feeling_pic.setImageAlpha(R.mipmap.surprised);
//                    break;
//                default:
//                    feeling_pic.setImageAlpha(R.mipmap.laugh);
//                    break;
//            }
//        }
        diaryAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
//                manifest에 등록안해서 오류났었음.
                Intent addIntent = new Intent(this, AddActivity.class);
                startActivity(addIntent);
                break;
            case R.id.search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.introduction:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("개발자 정보")
                        .setIcon(R.mipmap.laugh)
                        .setMessage("분반: 02분반\n학번: 20201017\n성명: 정유영\n내용: 모바일소프트웨어최종과제\n")
                        .setPositiveButton("확인", null)
                        .show();
                break;
            case R.id.exit:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                break;
        }
        return true;
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            Diary diary = diaryList.get(pos);
            Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
            updateIntent.putExtra("diary", diary);
            startActivity(updateIntent);
        }
    };

    AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
            final int pos = position; //pos 값때문에 헤맸음...?..

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("일기장 삭제")
                    .setMessage(diaryList.get(pos).getDate() + "의 날짜의 일기장을 삭제하시겠습니까?")
                    .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean result = diaryDBManager.removeDiary(diaryList.get(pos).get_id());

                            if(result) {
                                diaryList.clear();
                                diaryList.addAll(diaryDBManager.getAllDiary());
                                diaryAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("취소", null)
                    .setCancelable(false)
                    .show();

            return true;
        }
    };
}
