package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiaryDBManager {
    DiaryDBHelper diaryDBHelper = null;
    Cursor cursor = null;

    public DiaryDBManager(Context context) {
        diaryDBHelper = new DiaryDBHelper(context);
    }

    public ArrayList<Diary> getAllDiary() {
        ArrayList diaryList = new ArrayList();
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + diaryDBHelper.TABLE_NAME, null);
        while(cursor.moveToNext()){
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_DATE));
            String weather = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_WEATHER));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_TITLE));
            String place = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_PLACE));
            String feeling = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_FEELING));

            String story = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_STORY));

            diaryList.add( new Diary(id, date, weather, title, place ,feeling, story) );
        }

        cursor.close();
        diaryDBHelper.close();
        return diaryList;
    }

    public boolean addNewDiary(Diary newDiary) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(DiaryDBHelper.COL_DATE, newDiary.getDate());
        row.put(DiaryDBHelper.COL_WEATHER, newDiary.getWeather());
        row.put(DiaryDBHelper.COL_TITLE, newDiary.getTitle());
        row.put(DiaryDBHelper.COL_PLACE, newDiary.getPlace());
        row.put(DiaryDBHelper.COL_FEELING, newDiary.getFeeling());
        row.put(DiaryDBHelper.COL_STORY, newDiary.getStory());

        long count = db.insert(DiaryDBHelper.TABLE_NAME, null, row);

        diaryDBHelper.close();

        if(count > 0) return true;
        return false;
    }

    public boolean modifyDiary(Diary diary) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();

        row.put(DiaryDBHelper.COL_DATE, diary.getDate());
        row.put(DiaryDBHelper.COL_WEATHER, diary.getWeather());
        row.put(DiaryDBHelper.COL_TITLE, diary.getTitle());
        row.put(DiaryDBHelper.COL_PLACE, diary.getPlace());
        row.put(DiaryDBHelper.COL_FEELING, diary.getFeeling());
        row.put(DiaryDBHelper.COL_STORY, diary.getStory());

        //id로 구별해줘야하는 거여써? 조건으로?
        // 조건
        String whereClause = DiaryDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(diary.get_id()) };
        int result = db.update(DiaryDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        diaryDBHelper.close();

        if(result > 0) return true;

        return false;
    }

    public boolean removeDiary(long id) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();
        String whereClause = DiaryDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};

        int result = db.delete(DiaryDBHelper.TABLE_NAME, whereClause, whereArgs);

        diaryDBHelper.close();

        if(result > 0) return true;
        return false;
    }

//    일기장 제목으로 DB 검색
    public ArrayList<Diary> getDiaryByTitle(String title) {
        ArrayList diaryList = new ArrayList();
        SQLiteDatabase db = diaryDBHelper.getReadableDatabase();

        String selection = DiaryDBHelper.COL_TITLE + "=?";
        String[] selectArgs = new String[] {title};

        Cursor cursor = db.query(DiaryDBHelper.TABLE_NAME, null, selection, selectArgs, null, null, null, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_ID));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_DATE));
            String weather = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_WEATHER));
            String title_s = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_TITLE));
            String place = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_PLACE));
            String feeling = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_FEELING));
            String story = cursor.getString(cursor.getColumnIndexOrThrow(DiaryDBHelper.COL_STORY));

            Diary diary = new Diary(id, date, weather, title_s, place, feeling, story);
            diaryList.add(diary);
        }

        cursor.close();
        diaryDBHelper.close();
        return diaryList;
    }

}
