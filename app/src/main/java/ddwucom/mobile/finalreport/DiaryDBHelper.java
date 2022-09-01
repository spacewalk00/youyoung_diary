package ddwucom.mobile.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DiaryDBHelper extends SQLiteOpenHelper {
    final static String TAG = "DiaryDBHelper";
    final static String DB_NAME = "diary.db";

    public final static String TABLE_NAME = "diary_table";
    public final static String COL_ID = "_id";
    public final static String COL_DATE = "date";
    public final static String COL_WEATHER = "weather";
    public final static String COL_TITLE = "title";
    public final static String COL_PLACE = "place";
    public final static String COL_FEELING = "feeling";
    public final static String COL_STORY = "story";

    public DiaryDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME
                + " ( " + COL_ID + " integer primary key autoincrement, "
                + COL_DATE + " text, " + COL_WEATHER + " text, "
                + COL_TITLE + " text, " + COL_PLACE + " text, "
                + COL_FEELING + " text, " + COL_STORY + " text)";

        Log.d(TAG, sql);
        db.execSQL(sql);
        insertSample(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertSample(SQLiteDatabase db) {
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2022/06/17', '맑음', '1학기 종강', '집', 'good', '네트워크 시험을 끝으로 종강했다. 사실 레포트 과제가 남아있다ㅎ');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2022/06/18', '비 조금', '여행', '파주', 'good', '고등학교 친구들과 파주에서 바베큐 파티를 했다.');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2022/06/19', '바람많이붐', '낮잠', '집', 'surprised', '집에 도착하자마자 자기만 했다.');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2022/06/20', '맑음', 'shopping', '집근처', 'good', '엄마랑 데이트를 했다. 쇼핑은 역시 엄마랑,,');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2022/06/21', '맑음', '과제하기', '카페', 'good', '과제를 하기 시작했다.. 이번 학기 배운 내용을 총정리하는 기분이라 좋다');");
        db.execSQL("insert into " + TABLE_NAME + " values (null, '2022/06/22', '햇빛 강렬', '계절학기 개강', '학교', 'sad', '계절학기 개강했다...아임 베리 스트롱..');");
    }
}
