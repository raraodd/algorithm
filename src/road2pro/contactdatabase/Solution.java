package road2pro.contactdatabase;

import java.util.*;

/**
 * Created by Wendy P on 5/17/17.
 */

public class Solution {

    private static final int CMD_INIT = 0;
    private static final int CMD_ADD = 1;
    private static final int CMD_DELETE = 2;
    private static final int CMD_CHANGE = 3;
    private static final int CMD_SEARCH = 4;

    static Scanner sc = new Scanner(System.in);

    static int [] dummy = new int[100];
    static int Score, ScoreIdx;
    static char[] name = new char[20], number= new char[20], birthday = new char[20], email = new char[20], memo = new char[20];

    static char[][] lastname = { "kim".toCharArray(), "lee".toCharArray(), "park".toCharArray(), "choi".toCharArray(), "jung".toCharArray(), "kang".toCharArray(), "cho".toCharArray(), "oh".toCharArray(), "jang".toCharArray(), "lim".toCharArray() };
    static int[] lastname_length = { 3, 3, 4, 4, 4, 4, 3, 2, 4, 3 };

    public static class RESULT
    {
        public int count;
        public char[] str = new char[20];
    };

    static int mSeed;
    static int mrand(int num)
    {
        mSeed = mSeed * 1103515245 + 37209;
        if (mSeed < 0) mSeed *= -1;
        return ((mSeed >> 8) % num);
    }

    static void make_field(int seed)
    {
        name = new char[20];
        number= new char[20];
        birthday = new char[20];
        email = new char[20];
        memo = new char[20];

        int name_length, email_length, memo_length;
        int idx, num;

        mSeed = (int)seed;

        name_length = 6 + mrand(10);
        email_length = 10 + mrand(10);
        memo_length = 5 + mrand(10);

        int a = 97;
        int zero = 48;
        num = mrand(10);
        for (idx = 0; idx < lastname_length[num]; idx++) name[idx] = lastname[num][idx];
        for (; idx < name_length; idx++) name[idx] = (char)(a + mrand(26));
        name[idx] = 0;

        for (idx = 0; idx < memo_length; idx++) memo[idx] = (char)(a + mrand(26));
        memo[idx] = 0;

        for (idx = 0; idx < email_length - 6; idx++) email[idx] = (char)(a + mrand(26));
        email[idx++] = '@';
        email[idx++] = (char)(a + mrand(26));
        email[idx++] = '.';
        email[idx++] = 'c';
        email[idx++] = 'o';
        email[idx++] = 'm';
        email[idx] = 0;

        idx = 0;
        number[idx++] = '0';
        number[idx++] = '1';
        number[idx++] = '0';
        for (; idx < 11; idx++) number[idx] = (char)(zero + mrand(10));
        number[idx] = 0;

        idx = 0;
        birthday[idx++] = '1';
        birthday[idx++] = '9';
        num = mrand(100);
        birthday[idx++] = (char) ('0' + num / 10);
        birthday[idx++] = (char) ('0' + num % 10);
        num = 1 + mrand(12);
        birthday[idx++] = (char) ('0' + num / 10);
        birthday[idx++] = (char) ('0' + num % 10);
        num = 1 + mrand(30);
        birthday[idx++] = (char) ('0' + num / 10);
        birthday[idx++] = (char) ('0' + num % 10);
        birthday[idx] = 0;

        // debug!
        //System.out.format("%s %s %s %s %s\n", new String(name), new String(number), new String(birthday), new String(email), new String(memo));
    }

    static void cmd_init()
    {
        ScoreIdx = sc.nextInt();

        InitDB();
    }

    static void cmd_add()
    {
        int seed;
        seed = sc.nextInt();

        make_field(seed);

        Add(name, number, birthday, email, memo);
    }

    static void cmd_delete()
    {
        int field, check, result;
        char[] str = new char[20];
        field  = sc.nextInt();
        str  = sc.next().toCharArray();
        check  = sc.nextInt();

        result = Delete(field, str);
        if (result != check) {
            Score -= ScoreIdx;
        }
    }

    static void cmd_change()
    {
        int field, changefield, check, result;
        char[] str = new char[20], changestr = new char[20];
        field  = sc.nextInt();
        str  = sc.next().toCharArray();
        changefield  = sc.nextInt();
        changestr  = sc.next().toCharArray();
        check  = sc.nextInt();

        result = Change(field, str, changefield, changestr);
        if (result != check) {
            Score -= ScoreIdx;
        }
    }

    static void cmd_search() {
        int field, returnfield, check;
        char[] str = new char[20], checkstr = new char[20];
        field = sc.nextInt();
        str = sc.next().toCharArray();
        returnfield = sc.nextInt();
        checkstr = sc.next().toCharArray();
        check = sc.nextInt();
        RESULT result = Search(field, str, returnfield);

        if (result.count != check || (result.count == 1 && (new String(checkstr).compareTo(new String(result.str)) != 0))) {
            Score -= ScoreIdx;
        }
    }


    static void run()
    {
        int N;
        N = sc.nextInt();
        for (int i = 0; i < N; i++)
        {
            int cmd;
            cmd = sc.nextInt();
            switch (cmd)
            {
                case CMD_INIT:   cmd_init();   break;
                case CMD_ADD:    cmd_add();    break;
                case CMD_DELETE: cmd_delete(); break;
                case CMD_CHANGE: cmd_change(); break;
                case CMD_SEARCH: cmd_search(); break;
                default: break;
            }
        }
    }

    static void init()
    {
        Score = 1000;
        ScoreIdx = 1;
    }

    public static void main(String[] args)
    {

        int T;
        T = sc.nextInt();

        int TotalScore = 0;
        for (int tc = 1; tc <= T; tc++)
        {
            init();

            run();

            if (Score < 0)
                Score = 0;

            TotalScore += Score;
            System.out.format("#%d %d%n", tc, Score);
        }
        System.out.format("TotalScore = %d%n", TotalScore);
    }

    // -------------------------------------------------------------------------------------------
    // Write your code here : --------------------------------------------------------------------
    // -------------------------------------------------------------------------------------------
    public static ArrayList<Record> database;
    public static HashMap<String, ArrayList<Integer>> mapName, mapBirthday, mapMemo, mapNumber, mapEmail;

    public static class Field {
        final public static int name = 0;
        final public static int number = 1;
        final public static int birthday = 2;
        final public static int email = 3;
        final public static int memo = 4;
    }

    static class Record {
        public String name, number, birthday, email, memo;

        @Override
        public String toString() {
            return name+" "+number+" "+birthday+" "+email+" "+memo;
        }

        public String getValue(int fieldName){
            switch (fieldName){
                case Field.name : return name;
                case Field.number: return number;
                case Field.birthday: return birthday;
                case Field.email: return email;
                case Field.memo: return memo;
                default: return "";
            }
        }

        public void changeValue(int fieldName, String newValue){
            switch (fieldName){
                case Field.name :
                    this.name = newValue;
                    break;
                case Field.number:
                    this.number = newValue;
                    break;
                case Field.birthday:
                    this.birthday = newValue;
                    break;
                case Field.email:
                    this.email = newValue;
                    break;
                case Field.memo:
                    this.memo = newValue;
                    break;
            }
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String subArray(char[] chr) {
        String result = "";
        int len = chr.length;
        for (int i = 0; i < len; i++) {
            if(chr[i] == '\0') break;
            result += chr[i];

        }
        return result;
    }

    public static void addValueToMap(int field, Record record, int index) {
        ArrayList<Integer> listDatabaseIndex;
        switch (field){
            case Field.name:
                if(mapName.get(record.name) == null) {
                    listDatabaseIndex = new ArrayList<>();
                    listDatabaseIndex.add(index);
                    mapName.put(record.name, listDatabaseIndex);
                } else {
                    mapName.get(record.name).add(index);
                }
                return;
            case Field.number:
                if(mapNumber.get(record.number) == null) {
                    listDatabaseIndex = new ArrayList<>();
                    listDatabaseIndex.add(index);
                    mapNumber.put(record.number, listDatabaseIndex);
                } else {
                    mapNumber.get(record.number).add(index);
                }
                return;
            case Field.birthday:
                if(mapBirthday.get(record.birthday) == null) {
                    listDatabaseIndex = new ArrayList<>();
                    listDatabaseIndex.add(index);
                    mapBirthday.put(record.birthday, listDatabaseIndex);
                } else {
                    mapBirthday.get(record.birthday).add(index);
                }
                return;
            case Field.email:
                if(mapEmail.get(record.email) == null) {
                    listDatabaseIndex = new ArrayList<>();
                    listDatabaseIndex.add(index);
                    mapEmail.put(record.email, listDatabaseIndex);
                } else {
                    mapEmail.get(record.email).add(index);
                }
                return;
            case Field.memo:
                if(mapMemo.get(record.memo) == null) {
                    listDatabaseIndex = new ArrayList<>();
                    listDatabaseIndex.add(index);
                    mapMemo.put(record.memo, listDatabaseIndex);
                } else {
                    mapMemo.get(record.memo).add(index);
                }
                return;
        }
    }

    public static ArrayList<Integer> getListDatabaseIndex(int field, String key) {
        switch (field) {
            case Field.name:
                return mapName.get(key);
            case Field.birthday:
                return mapBirthday.get(key);
            case Field.memo:
                return mapMemo.get(key);
            case Field.number:
                return mapNumber.get(key);
            case Field.email:
                return mapEmail.get(key);
        }
        return null;
    }

    public static void removeIndexFromMap(int field, Record record, int index) {
        ArrayList<Integer> listDatabaseIndex;
        int length;
        switch (field) {
            case Field.name :
                listDatabaseIndex = mapName.get(record.name);
                length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
                for(int i=0; i < length; i++) {
                    if(listDatabaseIndex.get(i) == index) {
                        mapName.get(record.name).remove(i);
                        break;
                    }
                }
                break;
            case Field.birthday:
                listDatabaseIndex = mapBirthday.get(record.birthday);
                length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
                for(int i=0; i < length; i++) {
                    if(listDatabaseIndex.get(i) == index) {
                        mapBirthday.get(record.birthday).remove(i);
                        break;
                    }
                }
                break;
            case Field.memo:
                listDatabaseIndex = mapMemo.get(record.memo);
                length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
                for(int i=0; i < length; i++) {
                    if(listDatabaseIndex.get(i) == index) {
                        mapMemo.get(record.memo).remove(i);
                        break;
                    }
                }
                break;
            case Field.email:
                listDatabaseIndex = mapEmail.get(record.email);
                length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
                for(int i=0; i < length; i++) {
                    if(listDatabaseIndex.get(i) == index) {
                        mapEmail.get(record.email).remove(i);
                        break;
                    }
                }
                break;
            case Field.number:
                listDatabaseIndex = mapNumber.get(record.number);
                length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
                for(int i=0; i < length; i++) {
                    if(listDatabaseIndex.get(i) == index) {
                        mapNumber.get(record.number).remove(i);
                        break;
                    }
                }
                break;
            default:
                listDatabaseIndex = null;
        }
    }

    public static void removeMapByField(int field, String key) {
        switch (field) {
            case Field.name:
                mapName.remove(key);
            case Field.birthday:
                mapBirthday.remove(key);
            case Field.memo:
                mapMemo.remove(key);
            case Field.number:
                mapNumber.remove(key);
            case Field.email:
                mapEmail.remove(key);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void InitDB() {
        // init database arrayList
        database = new ArrayList<>();

        // init map name, birthday, memo, number, email
        mapName = new HashMap<>();
        mapBirthday = new HashMap<>();
        mapMemo = new HashMap<>();
        mapNumber = new HashMap<>();
        mapEmail = new HashMap<>();

        return;
    }

    public static void Add(char[] name2, char[] number2, char[] birthday2, char[] email2, char[] memo2) {
        // create database record
        Record record = new Record();
        record.name = subArray(name2);
        record.number = subArray(number2);
        record.birthday = subArray(birthday2);
        record.email = subArray(email2);
        record.memo = subArray(memo2);

        // save record to database
        database.add(record);

        // save key to all map
        int index = database.indexOf(record);
        addValueToMap(Field.name, record, index);
        addValueToMap(Field.birthday, record, index);
        addValueToMap(Field.memo, record, index);
        addValueToMap(Field.number, record, index);
        addValueToMap(Field.email, record, index);
    }

    public static int Delete(int field, char[] str) {
        ArrayList<Integer> listDatabaseIndex = getListDatabaseIndex(field, new String(str));

        int length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
        for(int i=0; i < length; i++) {
            int index = listDatabaseIndex.get(i); // index in database
            Record record = database.get(index);

            // remove all index in map record
            for (int j=0; j<5; j++) {
                if(j == field) continue;
                removeIndexFromMap(j, record, index);
            }
        }
        removeMapByField(field, new String(str));
        return length;
    }

    public static int[] copyIndex(ArrayList<Integer> listDatabaseIndex) {
        if(listDatabaseIndex == null || listDatabaseIndex.size() == 0) return null;
        int[] result = new int[listDatabaseIndex.size()];
        for(int i=0; i<listDatabaseIndex.size(); i++) {
            result[i] = listDatabaseIndex.get(i);
        }
        return result;
    }

    public static int Change(int field, char[] str, int changefield, char[] changestr) {
        String key = new String(str);
        String newValue = new String(changestr);

        ArrayList<Integer> listIdx = getListDatabaseIndex(field, key);
        // di copy dulu indexnya, somehow kalo pake arraylist kalo di remove ikut ke remove juga
        int[] idxList = copyIndex(listIdx);

        int length = idxList == null ? 0 : idxList.length;
        for(int i=0; i < length; i++) {
            int index = idxList[i];
            Record record = database.get(index);

            // remove old key from map
            removeIndexFromMap(changefield, record, index);

            // change record with new value
            database.get(index).changeValue(changefield, newValue);
            record.changeValue(changefield, newValue);

            // add new str to key map
            addValueToMap(changefield, record, index);
        }

        return length;
    }

    public static RESULT Search(int field, char[] str, int returnfield) {
        String key = new String(str);
        ArrayList<Integer> listDatabaseIndex = getListDatabaseIndex(field, key);

        int length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
        String value = length == 1 ? database.get(listDatabaseIndex.get(0)).getValue(returnfield) : "null";

        RESULT result = new RESULT();
        result.count = length;
        result.str = value.toCharArray();

        return result;
    }

}
