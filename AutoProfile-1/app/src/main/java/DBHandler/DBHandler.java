package DBHandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    SQLiteDatabase mysqldata;
    public static String DB_NAME = "Mobbisys_Auto_profile";
    String TABLE_NAME = "PROFILES";
    public static int version = 1;
    ArrayList<String> a = new ArrayList<String>();

    public DBHandler(Context context) {
        super(context, DB_NAME, null, version);
        mysqldata = getWritableDatabase();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("CREATE TABLE IF NOT EXISTS PROFILES(P_ID INTEGER PRIMARY KEY,PROFILENAME TEXT,FTIME TEXT,TTIME TEXT,SFTIME TEXT,STTIME TEXT,RINGTYPE TEXT,MSG TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public ArrayList<String> infoTimings(String pro, String as1, String as2) {

        Cursor c = mysqldata.rawQuery("select PROFILENAME from PROFILES where PROFILENAME<>'" + pro + "' and SFTIME between '" + as1 + "' and '" + as2 + "'", null);
        //Cursor c= mysqldata.rawQuery("select PROFILENAME from PROFILES where FTIME between '"+t1+"' and '"+t2+"'",null);
        ArrayList<String> aList = new ArrayList<String>();
        if (c != null) {
            while (c.moveToNext()) {
                String s11 = c.getString(c.getColumnIndex("PROFILENAME"));
                aList.add(s11);
            }
        }

        c.close();

        return aList;
    }

    public ArrayList<String> dateget(String t) {

        ArrayList<String> statas = new ArrayList<String>();
        Cursor c112 = mysqldata.rawQuery("select FTIME,TTIME from PROFILES where PROFILENAME='" + t + "'", null);


        int cdd = c112.getColumnIndex("FTIME");
        int cdd1 = c112.getColumnIndex("TTIME");


        if (c112.moveToFirst()) {


            do {

                String ss = c112.getString(cdd);
                String ss1 = c112.getString(cdd1);


                statas.add(ss);
                statas.add(ss1);


            } while (c112.moveToNext());
        }
        c112.close();

        System.out.println("values :" + statas);
        return statas;
    }

    public List<String> msgsend(String nw) {
        // TODO Auto-generated method stub
        List<String> alist = new ArrayList<String>();


        Cursor c = mysqldata.rawQuery("SELECT PROFILENAME,MSG FROM PROFILES where SFTIME<='" + nw + "' and STTIME>='" + nw + "'", null);
        int ipro = c.getColumnIndex("PROFILENAME");
        int ipro1 = c.getColumnIndex("MSG");
        if (c != null) {

            while (c.moveToNext()) {

                String g1 = c.getString(ipro);

                String g2 = c.getString(ipro1);

                alist.add(g1);
                alist.add(g2);


            }
        }
        c.close();


        return alist;
    }

    public void deleteone(String pname) {
        // TODO Auto-generated method stub
        mysqldata.execSQL("delete from PROFILES where PROFILENAME='" + pname + "'");


    }

    public void updateOnly(String t3, String statustone, String sm) {
        // TODO Auto-generated method stub
        mysqldata.execSQL("update PROFILES set RINGTYPE='" + statustone + "',MSG='" + sm + "' where PROFILENAME='" + t3 + "' ");

    }

    public List<String> selectdata() {
        List<String> res = new ArrayList<String>();

        Cursor c = mysqldata.rawQuery("select PROFILENAME,FTIME,TTIME from PROFILES", null);
        int cd = c.getColumnIndex("PROFILENAME");
        int cd1 = c.getColumnIndex("FTIME");
        int cd2 = c.getColumnIndex("TTIME");
        if (c.moveToFirst()) {


            do {

                String ss = c.getString(cd);
                String ft = c.getString(cd1);
                String tt = c.getString(cd2);

                String timedata = ft + "to" + tt;

                res.add(ss);
                res.add(timedata);

            } while (c.moveToNext());
        }

        c.close();

        return res;
    }

    public void insertdata1(String PROFILENAME, String FTIME, String TTIME, String sftime, String sttime, String status, String sm) {
        mysqldata.execSQL("INSERT INTO PROFILES(PROFILENAME,FTIME,TTIME,SFTIME,STTIME,RINGTYPE,MSG) VALUES('" + PROFILENAME + "','" + FTIME + "','" + TTIME + "','" + sftime + "','" + sttime + "','" + status + "','" + sm + "')");


    }

    public void update(String t1, String t2, String s3, String ar1, String ar2, String Statuss, String sm) {
        // TODO Auto-generated method stub
        mysqldata.execSQL("update PROFILES set FTIME='" + t1 + "',TTIME='" + t2 + "',SFTIME='" + ar1 + "',STTIME='" + ar2 + "',RINGTYPE='" + Statuss + "',MSG='" + sm + "' where PROFILENAME='" + s3 + "' ");
        //mysqldata.execSQL("update PROFILES set TTIME='"+t2+"' where " )
        //update sample set status =" + status + " where id = " + id);


    }

    public void delete() {
        // TODO Auto-generated method stub
        mysqldata.execSQL("delete from PROFILES");


    }

    public ArrayList<String> info(String pname) {

        Cursor c = mysqldata.rawQuery("select * from PROFILES where PROFILENAME='" + pname + "'", null);
        ArrayList<String> aList = new ArrayList<String>();
        if (c != null) {
            while (c.moveToNext()) {
                aList.add(c.getString(c.getColumnIndex("PROFILENAME")));
                aList.add(c.getString(c.getColumnIndex("FTIME")));
                aList.add(c.getString(c.getColumnIndex("TTIME")));
                //aList.add(c.getString(c.getColumnIndex("STTIME")));
                //aList.add(c.getString(c.getColumnIndex("SFTIME")));
                aList.add(c.getString(c.getColumnIndex("MSG")));
            }
        }

        c.close();

        return aList;
    }

    public List<String> activatepro(String nval) {
        // TODO Auto-generated method stub
        ArrayList<String> alist = new ArrayList<String>();


        System.out.println("savlues " + nval);

        Cursor c = mysqldata.rawQuery("SELECT PROFILENAME,RINGTYPE FROM PROFILES where SFTIME<='" + nval + "' and STTIME>='" + nval + "'", null);
        int ipro = c.getColumnIndex("PROFILENAME");
        int ipro1 = c.getColumnIndex("RINGTYPE");
        if (c != null) {
            System.out.println("cursor");


            while (c.moveToNext()) {

                System.out.println("ipro" + ipro);
                System.out.println("sdfs :" + ipro1);
                //String g1=c.getString(0);
                System.out.println("t111");
                //String g2=c.getString(1);

                alist.add(c.getString(0));
                alist.add(c.getString(1));
                System.out.println("t222");

            }
        }

        c.close();

        System.out.println("alist size" + alist.size());
        return alist;
    }

    public ArrayList<String> dategetSttime(String t) {
        ArrayList<String> statas = new ArrayList<String>();
        Cursor c112 = mysqldata.rawQuery("select STTIME from PROFILES where PROFILENAME='" + t + "'", null);


        int cdd = c112.getColumnIndex("STTIME");


        if (c112.moveToFirst()) {


            do {

                String ss = c112.getString(cdd);


                statas.add(ss);


            } while (c112.moveToNext());
        }

        System.out.println("values :" + statas);
        return statas;
    }


}
