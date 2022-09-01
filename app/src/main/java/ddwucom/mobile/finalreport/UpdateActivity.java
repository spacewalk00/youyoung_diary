package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {
    Diary diary;

    EditText etDate;
    EditText etWeather;
    EditText etTitle;
    EditText etPlace;
    EditText etFeeling;
    EditText etStory;

    DiaryDBManager diaryDBManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        diary = (Diary) getIntent().getSerializableExtra("diary");

        etDate = findViewById(R.id.etDate);
        etWeather = findViewById(R.id.etWeather);
        etTitle = findViewById(R.id.etTitle);
        etPlace = findViewById(R.id.etPlace);
        etFeeling = findViewById(R.id.etFeeling);
        etStory = findViewById(R.id.etStory);

        etDate.setText(diary.getDate());
        etWeather.setText(diary.getWeather());
        etTitle.setText(diary.getTitle());
        etPlace.setText(diary.getPlace());
        etFeeling.setText(diary.getFeeling());
        etStory.setText(diary.getStory());

        etStory.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                if(view.getId() == R.id.etStory) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch(event.getAction()&MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

        diaryDBManager = new DiaryDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnUpdate:
                diary.setDate(etDate.getText().toString());
                diary.setWeather(etWeather.getText().toString());
                diary.setTitle(etTitle.getText().toString());
                diary.setPlace(etPlace.getText().toString());
                diary.setFeeling(etFeeling.getText().toString());
                diary.setStory(etStory.getText().toString());

                if(diary.getDate().equals("")){
                    Toast.makeText(this, "날짜 입력은 필수입니다!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                } else {
                    if(diaryDBManager.modifyDiary(diary)) {
                        Toast.makeText(this, "수정 성공!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "수정 실패!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                }
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }



}
