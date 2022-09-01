package ddwucom.mobile.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText etTitle;
    DiaryDBManager diaryDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        diaryDBManager = new DiaryDBManager(this);
        etTitle = findViewById(R.id.etTitle);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnSearch:
                String title = etTitle.getText().toString();
                ArrayList<Diary> diaryArrayList = diaryDBManager.getDiaryByTitle(title);

                String str = "";
                if(diaryArrayList.size() > 0) {
                    for(int i = 0; i < diaryArrayList.size(); i++) {
                        str += diaryArrayList.get(i).getDate()+"의 일기장입니다.\n";
                        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "그런 title은 없습니다!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        finish();
    }
}
