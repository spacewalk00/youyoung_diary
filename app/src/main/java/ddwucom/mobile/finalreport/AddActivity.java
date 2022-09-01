package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    EditText etDate;
    int year = 2022, month = 6, day = 1;
    EditText etWeather;
    EditText etTitle;
    EditText etPlace;
    EditText etFeeling;
    EditText etStory;

    DiaryDBManager diaryDBManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etDate = findViewById(R.id.etDate);
        DatePicker datePicker = findViewById(R.id.datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String date = i + "/" + (i1 + 1) + "/" + i2;
                etDate.setText(date);
            }
        });

        etWeather = findViewById(R.id.etWeather);
        etTitle = findViewById(R.id.etTitle);
        etPlace = findViewById(R.id.etPlace);
        etFeeling = findViewById(R.id.etFeeling);
        etStory = findViewById(R.id.etStory);
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
            case R.id.btnAdd:
                String diaryDate = etDate.getText().toString();
                String diaryWeather = etWeather.getText().toString();
                String diaryTitle = etTitle.getText().toString();
                String diaryPlace = etPlace.getText().toString();
                String diaryFeeling = etFeeling.getText().toString();
                String diaryStory = etStory.getText().toString();

                boolean result = diaryDBManager.addNewDiary(
                        new Diary(diaryDate, diaryWeather, diaryTitle, diaryPlace, diaryFeeling, diaryStory)
                );
                if(diaryDate.equals("")){
                    Toast.makeText(this, "날짜 입력은 필수입니다!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                } else {
                    if(result) {
                        Toast.makeText(this, "새로운 다이어리 추가 성공!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "새로운 다이어리 추가 실패!", Toast.LENGTH_SHORT).show();
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
