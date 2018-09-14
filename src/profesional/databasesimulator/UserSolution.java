
//https://swexpertacademy.samsung.com/common/swea/solvingPractice/userProblemDetail.do?contestProbId=AWM-VzF_DT8AAAGv&userProblemProcess=&userIsFavorite=&problemTitle=&rowNum=10&pageIndex=4

class UserSolution{

    void Add(String name, String number, String birthday, String email, String memo){
        Record r = new Record();
        r.init(name, number, birthday, email, memo);
        r.addRecord(all);
    }
    Solution.Result Search(int field, String str, int returnfield){
        Solution.Result result = new Solution.Result();
        result.count = 0;
        int hash = Record.hash(str);
        for (Record next[] = all[hash]; null != next; ){
            Record rec = Record.getNext(field, next);
            if (null == rec) break;
            next = rec._next;
            if (rec.isEquals(field, str)){
                result.count++;
                result.str = rec._value[returnfield];
            }
        }
        return result;
    }
    int Delete(int field, String str){
        int count = 0;
        int hash = Record.hash(str);
        for (Record next[] = all[hash]; null != next; ){
            Record rec = Record.getNext(field, next);
            if (null == rec) break;
            next = rec._next;
            if (rec.isEquals(field, str)){
                count++;
                rec.delete();
            }
        }
        return count;
    }
    int Change(int field, String str, int changefield, String changestr){
        int count = 0;
        int hash = Record.hash(str);
        for (Record next[] = all[hash]; null != next; ){
            Record rec = Record.getNext(field, next);
            if (null == rec) break;
            next = rec._next;
            if (rec.isEquals(field, str)){
                count++;
                rec = rec.replaceIt(changefield, changestr);
                rec.addRecord(all);
            }
        }
        return count;
    }

    void InitDB(){
        all = new Record[Record.hashMod][5];
    }
    static Record[][] all;
}

class Record{
    static int hashMod = 8191;
    static int hash(String str){
        int val = 0;
        for(int i = str.length(); i-->0;){
            val *= 37;
            val += str.charAt(i);
            val %= hashMod;
        }
        return val;
    }
    static Record getNext(int field, Record next[]){
        Record result = next[field];
        if (null == result) return null;
        if (!result.isDeleted()) return result;
        next[field] = result._next[field];			// discard deleted result
        return getNext(field, next);
    }
    
    String _value[] = new String[5];
    Record _next[] = new Record[5];
    void init(String name, String number, String birthday, String email, String memo){
        _value[0] = name;
        _value[1] = number;
        _value[2] = birthday;
        _value[3] = email;
        _value[4] = memo;
    }
    boolean isDeleted(){
        return _value == null;
    }
    void delete(){
        _value = null;
    }
    void addRecord(Record hashMap[][]){
        for (int i = 5; i-->0;){
            int hash = hash(_value[i]);
            Record next[] = hashMap[hash];
            _next[i] = next[i];
            next[i] = this;
        }
    }
    boolean isEquals(int col, String s0){
        String s1 = _value[col];
        int len = s1.length();
        if (len != s0.length()) return false;
        while(len-->0){
            if (s0.charAt(len) != s1.charAt(len)) return false;
        }
        return true;
    }
    Record replaceIt(int col, String str){
        Record r = new Record();
        _value[col] = str;			// replacement
        r._value = _value;
        delete();
        return r;
    }
}
