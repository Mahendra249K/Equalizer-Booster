package com.mahendra249k.equalizer_booster.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "spotiq.db";
    public static final String EQ_COLUMN_ALBUMNAME = "albumname";
    public static final String EQ_COLUMN_ARTISTNAME = "artistname";
    public static final String EQ_COLUMN_TRACKID = "trackid";
    public static final String EQ_COLUMN_TRACKNAME = "trackname";
    public static final String PRESETS_COLUMN_PRESETNAME = "presetname";
    public static final String PRESETS_TABLEMS_NAME = "eqpreset";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table eq (id integer primary key, trackid text, trackname text, albumname text,artistname text, na1 text,na2 text, eqenable text, bbsenable text, bbsvalue text, bandsnumber text, eq0 text, eq1 text, eq2 text, eq3 text, eq4 text, eq5 text, eq6 text, eq7 text, eq8 text, eq9 text, device text, data1 text, data2 text)");
        sQLiteDatabase.execSQL("create table eqpreset (id integer primary key, presetname text, na1 text,na2 text, eqenable text, bbsenable text, bbsvalue text, bandsnumber text, eq0 text, eq1 text, eq2 text, eq3 text, eq4 text, eq5 text, eq6 text, eq7 text, eq8 text, eq9 text, device text, data1 text, data2 text)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS eq");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS eqpreset");
        onCreate(sQLiteDatabase);
    }

    public boolean insertEqPreset(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRESETS_COLUMN_PRESETNAME, str);
        contentValues.put("eqenable", str2);
        contentValues.put("bbsenable", str3);
        contentValues.put("bbsvalue", str4);
        contentValues.put("bandsnumber", str5);
        contentValues.put("eq0", str6);
        contentValues.put("eq1", str7);
        contentValues.put("eq2", str8);
        contentValues.put("eq3", str9);
        contentValues.put("eq4", str10);
        contentValues.put("device", str11);
        writableDatabase.insert(PRESETS_TABLEMS_NAME, (String) null, contentValues);
        return true;
    }

    public Cursor getDataEq(int i) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        return readableDatabase.rawQuery("select * from eq where id=" + i + "", (String[]) null);
    }

    public Cursor getDataEqPreset(int i) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        return readableDatabase.rawQuery("select * from eqpreset where id=" + i + "", (String[]) null);
    }

    @SuppressLint("Range")
    public Integer getIdFromTrackid(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from eq where trackid ='" + str + "'", (String[]) null);
        int i = -1;
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
        } else {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex("id"));
        }
        readableDatabase.close();
        return i;
    }

    @SuppressLint("Range")
    public Integer getIdFromPresetName(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from eqpreset where presetname like '%" + str + "%'", (String[]) null);
        int i = -1;
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
        } else {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex("id"));
        }
        readableDatabase.close();
        return i;
    }

    @SuppressLint("Range")
    public Integer getIdFromAlbumName(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from eq where albumname like '%" + str + "%' AND trackid = 'ALBUM_EQ'", (String[]) null);
        int i = -1;
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
        } else {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex("id"));
        }
        readableDatabase.close();
        return i;
    }

    @SuppressLint("Range")
    public Integer getIdFromArtistName(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("select * from eq where albumname ='" + str + "' AND trackid = 'ARTIST_EQ'", (String[]) null);
        int i = -1;
        if (rawQuery.getCount() <= 0) {
            rawQuery.close();
        } else {
            rawQuery.moveToFirst();
            i = rawQuery.getInt(rawQuery.getColumnIndex("id"));
        }
        readableDatabase.close();
        return i;
    }

    public boolean updateEqPreset(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRESETS_COLUMN_PRESETNAME, str);
        contentValues.put("eqenable", str2);
        contentValues.put("bbsenable", str3);
        contentValues.put("bbsvalue", str4);
        contentValues.put("bandsnumber", str5);
        contentValues.put("eq0", str6);
        contentValues.put("eq1", str7);
        contentValues.put("eq2", str8);
        contentValues.put("eq3", str9);
        contentValues.put("eq4", str10);
        contentValues.put("device", str11);
        writableDatabase.update(PRESETS_TABLEMS_NAME, contentValues, "id = ? ", new String[]{Integer.toString(num)});
        return true;
    }

    public Integer deleteEqPreset(Integer num) {
        return getWritableDatabase().delete(PRESETS_TABLEMS_NAME, "id = ? ", new String[]{Integer.toString(num.intValue())});
    }

    @SuppressLint("Range")
    public ArrayList<String> getEqValues(Integer num) {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor dataEq = getDataEq(num);
        dataEq.moveToFirst();
        arrayList.add(dataEq.getString(dataEq.getColumnIndex(EQ_COLUMN_TRACKID)));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex(EQ_COLUMN_TRACKNAME)));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex(EQ_COLUMN_ALBUMNAME)));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex(EQ_COLUMN_ARTISTNAME)));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eqenable")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("bbsenable")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("bbsvalue")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("bandsnumber")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq0")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq1")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq2")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq3")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq4")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq5")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq6")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq7")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq8")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("eq9")));
        arrayList.add(dataEq.getString(dataEq.getColumnIndex("device")));
        readableDatabase.close();
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<String> getEqPresetValue(Integer num) {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor dataEqPreset = getDataEqPreset(num);
        dataEqPreset.moveToFirst();
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex(PRESETS_COLUMN_PRESETNAME)));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eqenable")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("bbsenable")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("bbsvalue")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("bandsnumber")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq0")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq1")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq2")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq3")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq4")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq5")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq6")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq7")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq8")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("eq9")));
        arrayList.add(dataEqPreset.getString(dataEqPreset.getColumnIndex("device")));
        dataEqPreset.moveToNext();
        readableDatabase.close();
        return arrayList;
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllEqPreset() {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor rawQuery = getReadableDatabase().rawQuery("select * from eqpreset", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(PRESETS_COLUMN_PRESETNAME)));
            rawQuery.moveToNext();
        }
        return arrayList;
    }
}
