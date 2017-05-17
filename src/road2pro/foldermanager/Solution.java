package road2pro.foldermanager;

/**
 * Created by Wendy P on 5/17/17.
 */
// -------------------------------------------------------------------------------------------
// do not edit these code --------------------------------------------------------------------
// -------------------------------------------------------------------------------------------

import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

class Solution {
    private static final int CMD_ADD = 1;
    private static final int CMD_MOVE = 2;
    private static final int CMD_INFECT = 3;
    private static final int CMD_RECOVER = 4;
    private static final int CMD_REMOVE = 5;

    private static Scanner sc;
    private static UserSolution userSolution = new UserSolution();

    private static int run() {
        int score = 0;
        int N = Integer.parseInt(sc.next());

        for(int i=0; i<N; i++) {
            int cmd = Integer.parseInt(sc.next());
            int ret = 0;

            switch(cmd) {
                case CMD_ADD: {
                    int id = Integer.parseInt(sc.next());
                    int pid = Integer.parseInt(sc.next());
                    int fileSize = Integer.parseInt(sc.next());
                    ret = userSolution.add(id, pid, fileSize);
                    break;
                }
                case CMD_MOVE: {
                    int id = Integer.parseInt(sc.next());
                    int pid = Integer.parseInt(sc.next());
                    ret = userSolution.move(id, pid);
                    break;
                }
                case CMD_INFECT: {
                    int id = Integer.parseInt(sc.next());
                    ret = userSolution.infect(id);
                    break;
                }
                case CMD_RECOVER: {
                    int id = Integer.parseInt(sc.next());
                    ret = userSolution.recover(id);
                    break;
                }
                case CMD_REMOVE: {
                    int id = Integer.parseInt(sc.next());
                    ret = userSolution.remove(id);
                    break;
                }
            }

            int checkSum = Integer.parseInt(sc.next());
            if(ret == checkSum) score++;
        }
        return score;
    }

    public static void main(String arg[]) throws Exception {
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
        sc = new Scanner(System.in);

        int totalScore = 0;

        int T = sc.nextInt();
        for(int t=1; t<=T; t++) {
            userSolution.init();
            int score = run();
            System.out.println("#" + t + " " + score);
            totalScore += score;
        }
        sc.close();
        System.out.println("Total Score : " + totalScore);
    }
}





// -------------------------------------------------------------------------------------------
// write your code here ----------------------------------------------------------------------
// -------------------------------------------------------------------------------------------


class UserSolution {

    private Record root;
    private HashMap<Integer, Vector<Integer>> map;
    private HashMap<Integer, Record> mapRecord;

    public void init() {
        map = new HashMap<>();
        mapRecord = new HashMap<>();

        // set root folder
        // id, pid, isFile, fileSize, totalSize, totalFile
        root = new Record(10000, 0, false, 0, 0, 0);

        // save to map record
        mapRecord.put(root.id, root);


        // save to map id mapping
        Vector<Integer> v = new Vector<>();
        map.put(root.id, v);
    }

    private void recalculateFileAndSize(int id, int value, int pid, int isAddFile){
        if(id == root.id) return;

        Record parentRecord = mapRecord.get(pid);
        parentRecord.totalSize += value;
        parentRecord.totalFile = parentRecord.totalFile + isAddFile;
//        System.out.println("calculate - " + id + " " + pid + " " + parentRecord.totalSize + " " + parentRecord.totalFile);
        recalculateFileAndSize(pid, value, parentRecord.pid, isAddFile);
    }

    private void infectedFileAndFolder(Record record, int infectedSize) {
        if(record.isFile) {
            record.fileSize += infectedSize;
            recalculateFileAndSize(record.id, infectedSize, record.pid, 0);
//            System.out.println("infectedFileAndFolder - " + record.id + " " + record.fileSize);
            return;
        }
        if(record.totalFile == 0) return;
//        System.out.println("infectedFileAndFolder - " + record.id);
        Vector<Integer> v = map.get(record.id);
        for (int i=0; i < v.size(); i++) {
            infectedFileAndFolder(mapRecord.get(v.elementAt(i)), infectedSize);
        }
    }

    private void recoverFileAndFolder(Record record) {
        if(record.isFile) {
            recalculateFileAndSize(record.id, -(record.fileSize-record.size), record.pid, 0);
            record.fileSize = record.size;
//            System.out.println("recover - " + record.id + " " + record.fileSize);
            return;
        }
        if(record.totalFile == 0) return;
//        System.out.println("recover - " + record.id);
        Vector<Integer> v = map.get(record.id);
        for (int i=0; i < v.size(); i++) {
            recoverFileAndFolder(mapRecord.get(v.elementAt(i)));
        }
    }

    public int add(int id, int pid, int fileSize) {
//        System.out.println("Add - " + id + " " + pid + " " + fileSize);

        // init record, map, and save to map record
        Record record = new Record(id, pid, false, fileSize, 0, 0);
        record.isFile = fileSize != 0 ? true : false;

        if(!record.isFile) {
            // folder -> create map
            Vector<Integer> v = new Vector<>();
            map.put(id, v);
        }

        mapRecord.put(id, record);

        // save to map parent id
        Vector<Integer> vParent = map.get(pid);
        try {
            vParent.addElement(id);
        }
        catch (Exception e) {
            System.out.println();
        }

        // recursive recalculate the size of its parent until root
        if(fileSize != 0) {
            recalculateFileAndSize(id, fileSize, pid, 1);
        }

        int res =  mapRecord.get(pid).totalSize;

//        System.out.println("   result " + res);
        return res;
    }

    public int move(int id, int pid) {
//        System.out.println("Move - " + id + " " + pid);

        // remove the id from the prev parent map
        Record record = mapRecord.get(id);
        Vector<Integer> prevParent = map.get(record.pid);

        for(int i=0; i<prevParent.size(); i++) {
            if(prevParent.elementAt(i) == id){
                prevParent.removeElement(i);
                break;
            }
        }

        // recalculate the value after remove element
        int removeValue = record.isFile ? -record.fileSize : -record.totalSize;
        if(removeValue != 0)
            recalculateFileAndSize(id, removeValue, record.pid, -1);

        record.pid = pid;

        // insert the id into new parent map
        Vector<Integer> nextParent = map.get(pid);
        nextParent.addElement(id);

        // recalculate the value after add element
        int addValue = record.isFile ? record.fileSize : record.totalSize;
        if(addValue != 0)
            recalculateFileAndSize(id, addValue, pid, 1);

        int res = mapRecord.get(pid).totalSize;
//        System.out.println("   result " + res);
        return res;
    }

    public int infect(int id) {
//        System.out.println("Infect - " + id);
        if(root.totalFile == 0) return 0;

        int res;
        Record record = mapRecord.get(id);

        // calculate increases file size
        int infectedFileSize = root.totalSize / root.totalFile;

        infectedFileAndFolder(record, infectedFileSize);

        res = record.isFile ? record.fileSize : record.totalSize;
//        System.out.println("   result " + res);
        return res;
    }

    public int recover(int id) {
//        System.out.println("Recover - " + id);

        int res;
        Record record = mapRecord.get(id);

        recoverFileAndFolder(record);

        res = record.isFile ? record.fileSize : record.totalSize;
//        System.out.println("   result " + res);
        return res;
    }

    public int remove(int id) {
//        System.out.println("Remove - " + id);

        int res;
        Record record = mapRecord.get(id);

        // remove from parent
        Vector<Integer> v = map.get(record.pid);
        for (int i=0; i < v.size(); i++) {
            if(mapRecord.get(v.elementAt(i)).id == id) {
                v.removeElement(i);
                map.remove(i);
                break;
            }
        }

        if(record.isFile) {
            recalculateFileAndSize(id, -record.fileSize, record.pid, -1);
            res = record.fileSize;
        } else {
            recalculateFileAndSize(id, -record.totalSize, record.pid, 0);
            res = record.totalSize;
        }
//        System.out.println("   result " + res);
        return res;
    }

    class Record {
        public int id;
        public int pid;
        public int size; // true file size
        public int fileSize; // file size can infected by virus
        public int totalSize;
        public int totalFile;
        public boolean isFile;

        public Record(int id, int pid, boolean isFile, int fileSize, int totalSize,int totalFile){
            this.id = id;
            this.pid = pid;
            this.size = this.fileSize = fileSize;
            this.totalSize = totalSize;
            this.totalFile = totalFile;
            this.isFile = isFile;
        }

        @Override
        public String toString() {
            return "id: " + id + " pid: " + pid + " isFile: " + isFile + " size: " + fileSize + " totalSize: " + totalSize;
        }
    }
}


