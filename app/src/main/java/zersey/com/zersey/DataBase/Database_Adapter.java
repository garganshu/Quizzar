package zersey.com.zersey.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by anshul on 8/10/17.
 */

public class Database_Adapter extends SQLiteOpenHelper {

    static String DATABASE_NAME="Quizzar";


    public static final String TABLE_Quiz="TQ";

    public static final String KEY_Ques="tques";
    public static final String KEY_Opt1="topt1";
    public static final String KEY_Opt2="topt2";
    public static final String KEY_Opt3="topt3";
    public static final String KEY_Opt4="topt4";
    public static final String KEY_Solution="tsolution";
    private static String CREATE_TABLE_QUIZ = "CREATE TABLE "
            + TABLE_Quiz + " (" + KEY_Ques + " TEXT," + KEY_Opt1 + " TEXT," + KEY_Opt2 + " TEXT," + KEY_Opt3 + " TEXT," + KEY_Opt4 + " TEXT," + KEY_Solution + " TEXT)";

    //initial quiz values in SQlite
    private static String INITIAL_QUES1 = "INSERT INTO "+ TABLE_Quiz + " VALUES(' OS computer abbreviation usually means ?','Order of Significance','Open Software','Operating System','Optical Sensor','Operating System')";
    private static String INITIAL_QUES2 = "INSERT INTO "+ TABLE_Quiz + " VALUES(' DB computer abbreviation usually means ?','Database','Double Byte','Data Block','Driver Boot','Database')";
    private static String INITIAL_QUES3 = "INSERT INTO "+ TABLE_Quiz + " VALUES(' (Do no evil) is tag line of ?','Google','Microsoft','Apple','Intel','Google')";
    private static String INITIAL_QUES4 = "INSERT INTO "+ TABLE_Quiz + " VALUES(' World first microprocessor is ?','Intel 4004','Intel 4006','Intel 4020','Intel 4008','Intel 4004')";
    private static String INITIAL_QUES5 = "INSERT INTO "+ TABLE_Quiz + " VALUES(' Longhorn was the code name of ?','Windows Vista','Windows XP','Windows 7','Windows 10','Windows Vista')";

    public Database_Adapter(Context context2) {
        super(context2, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {
        db1.execSQL(CREATE_TABLE_QUIZ);
        db1.execSQL(INITIAL_QUES1);
        db1.execSQL(INITIAL_QUES2);
        db1.execSQL(INITIAL_QUES3);
        db1.execSQL(INITIAL_QUES4);
        db1.execSQL(INITIAL_QUES5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {
        db1.execSQL("DROP TABLE IF EXISTS " + TABLE_Quiz);
        onCreate(db1);

    }
}


