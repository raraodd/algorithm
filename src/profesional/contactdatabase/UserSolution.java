package profesional.contactdatabase;

import java.util.ArrayList;
import java.util.HashMap;
//import util.HashMap;

/**
 * Created by Wendy P on 5/17/17.
 */

public class UserSolution {

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

    public static void printArr(char[] data) {
        int i = 0;
        while (i < data.length && data[i]!='\0'){
            System.out.print(data[i]);
            i++;
        }
        System.out.print(" ");
    }


    public static String subArray(char[] chr) {
        String result = "";
        int len = chr.length;
        for (int i = 0; i < len; i++) {
            if(chr[i] == '\0') break;
            result += chr[i];

        }
//        System.out.println("Subarray " + result + " " + result.length());
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
        System.out.println("0 Init");

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

        System.out.println("1 Add " + record.toString());

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
        System.out.println("2 Delete "+ field + " " + new String(str).toString());

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
        System.out.println("3 Change "+ field + " " + new String(str).toString() + " " + changefield + " " + new String(changestr).toString() + " ");

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

    public static Solution.RESULT Search(int field, char[] str, int returnfield) {
        System.out.println("4 Search "+ field + " " + new String(str).toString() + " " + returnfield);
        String key = new String(str);
        ArrayList<Integer> listDatabaseIndex = getListDatabaseIndex(field, key);

        int length = listDatabaseIndex == null ? 0 : listDatabaseIndex.size();
        String value = length == 1 ? database.get(listDatabaseIndex.get(0)).getValue(returnfield) : "null";

        Solution.RESULT result = new Solution.RESULT();
        result.count = length;
        result.str = value.toCharArray();

        System.out.println(result.count + " " + value);

        return result;
    }

}
