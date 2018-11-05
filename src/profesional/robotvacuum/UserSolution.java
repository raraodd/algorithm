package profesional.robotvacuum;

class UserSolution {

    private final static int GOFORWARD			= 0;
    private final static int TURNRIGHT			= 1;

    private final static int MAXN				= 200;

    private final static int UP					= 0;
    private final static int RIGHT				= 1;
    private final static int DOWN				= 2;
    private final static int LEFT				= 3;

    class Data {
        int y;
        int x;
        int d;
        long k;
        Data next;

        public Data(int y, int x, int d, long k, Data next) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.k = k;
            this.next = next;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return y + " " + x + " " + d + " " + k;
        }
    }

    // Main API:
    //   Solution.scan(int image[4][4])
    //   Solution.control(int command)

    Data posFinder[];
    Data map[][][];

    int posFinderCap = 8191;

    public long[][] scanRoom(int N, int room[][]) {
        long[][] result = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(room[i][j] == 9)
                    result[i][j] = 2;
                else
                    result[i][j] = room[i][j];

            }
        }

        return result;
    }

    public long getKey(int y, int x, int dir, long room[][]) {
        long res = 0;
        switch(dir) {
            case 0:
                res += 1 * room[y][x];
                res += (1 << 2) * room[y][x + 1];
                res += (1 << 4) * room[y][x + 2];
                res += (1 << 6) * room[y][x + 3];

                res += (1 << 8) * room[y+1][x];
                res += (1 << 10) * room[y+1][x + 1];
                res += (1 << 12) * room[y+1][x + 2];
                res += (1 << 14) * room[y+1][x + 3];

                res += (1 << 16) * room[y+2][x];
                res += (1 << 18) * room[y+2][x + 1];
                res += (1 << 20) * room[y+2][x + 2];
                res += (1 << 22) * room[y+2][x + 3];

                res += (1 << 24) * room[y+3][x];
                res += (1 << 26) * room[y+3][x + 1];
                res += (1 << 28) * room[y+3][x + 2];
                res += (1 << 30) * room[y+3][x + 3];
                break;
            case 1:
                res += 1 * room[y][x+3];
                res += (1 << 2) * room[y+1][x + 3];
                res += (1 << 4) * room[y+2][x + 3];
                res += (1 << 6) * room[y+3][x + 3];

                res += (1 << 8) * room[y][x + 2];
                res += (1 << 10) * room[y+1][x + 2];
                res += (1 << 12) * room[y+2][x + 2];
                res += (1 << 14) * room[y+3][x + 2];

                res += (1 << 16) * room[y][x + 1];
                res += (1 << 18) * room[y+1][x + 1];
                res += (1 << 20) * room[y+2][x + 1];
                res += (1 << 22) * room[y+3][x + 1];

                res += (1 << 24) * room[y][x];
                res += (1 << 26) * room[y+1][x];
                res += (1 << 28) * room[y+2][x];
                res += (1 << 30) * room[y+3][x];
                break;
            case 2:
                res += 1 * room[y + 3][x + 3];
                res += (1 << 2) * room[y+3][x + 2];
                res += (1 << 4) * room[y+3][x + 1];
                res += (1 << 6) * room[y+3][x];

                res += (1 << 8) * room[y+2][x + 3];
                res += (1 << 10) * room[y+2][x + 2];
                res += (1 << 12) * room[y+2][x + 1];
                res += (1 << 14) * room[y+2][x];

                res += (1 << 16) * room[y+1][x + 3];
                res += (1 << 18) * room[y+1][x + 2];
                res += (1 << 20) * room[y+1][x + 1];
                res += (1 << 22) * room[y+1][x];

                res += (1 << 24) * room[y][x + 3];
                res += (1 << 26) * room[y][x + 2];
                res += (1 << 28) * room[y][x + 1];
                res += (1 << 30) * room[y][x];
                break;
            case 3:
                res += 1 * room[y + 3][x];
                res += (1 << 2) * room[y+2][x];
                res += (1 << 4) * room[y+1][x];
                res += (1 << 6) * room[y][x];

                res += (1 << 8) * room[y+3][x + 1];
                res += (1 << 10) * room[y+2][x + 1];
                res += (1 << 12) * room[y+1][x + 1];
                res += (1 << 14) * room[y][x + 1];

                res += (1 << 16) * room[y+3][x + 2];
                res += (1 << 18) * room[y+2][x + 2];
                res += (1 << 20) * room[y+1][x + 2];
                res += (1 << 22) * room[y+0][x + 2];

                res += (1 << 24) * room[y + 3][x + 3];
                res += (1 << 26) * room[y+2][x + 3];
                res += (1 << 28) * room[y+1][x + 3];
                res += (1 << 30) * room[y][x + 3];
        }
        if (0>res)
            throw new Error("gak mungkin");
        return res;
    }

    public void init(int N, int room[][]) {
        posFinder = new Data[posFinderCap]; // todo find bigger prime
        map = new Data[N][N][4];
        long[][] roomLong = scanRoom(N, room);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(room[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < N-3; i++) {
            for (int j = 0; j < N-3; j++) {
                for(int d = 0; d < 4; d++) {
                    long key = getKey(i, j, d, roomLong);
                    int hash = (int)(key % posFinderCap);
                    Data existing = posFinder[hash];
                    Data newData = new Data(i, j, d, key, existing);
                    posFinder[hash] = newData;
                    map[i][j][d] = newData;
                }
            }
        }
    }

    long lastKey = -1;

    public boolean isValid (Data data_baru, long key) {
        int hash = (int)(key % posFinderCap);
        Data data_lama = posFinder[hash];
        int i = 0;
        //System.out.println("-----data_lama " + data_lama.toString() );
        for(;data_lama != null;data_lama = data_lama.next) {
            if(key != data_lama.k) continue;
            switch(data_baru.d) {
                case 0:
                    // up
                    if (data_lama.y-1 == data_baru.y && data_lama.x == data_baru.x){
//						//System.out.println("-----xx0 " + data_baru.toString() );
                        return true;
                    }
                    break;
                case 3:
                    if (data_lama.y == data_baru.y && data_lama.x-1 == data_baru.x){
//						//System.out.println("-----xx1 " + data_baru.toString() );
                        return true;
                    }
                    break;

                case 2:
                    // down
                    if ((data_lama.y+1 == data_baru.y) && (data_lama.x == data_baru.x)){
                        //System.out.println("-----xx2 " + data_baru.toString() + "       " + ((data_lama.y+1 == data_baru.y) && (data_lama.x == data_baru.x)));
                        //System.out.println("-----data_lama " + data_lama.toString() );
                        return true;
                    }
                    break;

                case 1:
                    if (data_lama.y == data_baru.y && data_lama.x+1 == data_baru.x) {
                        //System.out.println("-----xx3 " + data_baru.toString() );
                        return true;
                    }
                    break;
            }
        }
        //System.out.println("-----xx false");
        return false;
    }
    public Data eliminate(long key, long lastKey) {
        //System.out.println("masuk eliminate " + key + " " + lastKey );
        Data result;

        int hash = (int)(key % posFinderCap);
        Data first = posFinder[hash];

        int i = 0;
        result = null;

        for(;first != null;first = first.next) {
            if(key != first.k) continue;

            if (isValid(first, lastKey)) {
                //System.out.println("masuk ------" );
                i++;
                if(i > 1)
                    return null;

                result = first;
//                first = first.next;
                //System.out.println(result.toString() );
            }
        }

        return result;
    }

    public Data find_data() {
        Data result = new Data(0, 0, 0, 0, null);

        int image[][] = new int[4][4];

        boolean x = Solution.scan(image);

        //System.out.println(x);

        int N = 4;

        long[][] roomLong = scanRoom(N, image);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }

        long key = getKey(0, 0, 0, roomLong);
        int hash = (int)(key % posFinderCap);
        Data first = posFinder[hash];

        int i = 1;
        for(;first != null;first = first.next) {
            if(key != first.k) continue;

            System.out.println("finding ke-" + i + " " + first.toString());
            result = first;

            i++;
        }




        if(i > 2){

            if(lastKey > -1) {
                result = eliminate(key, lastKey);
                if(result != null)
                    return result;
            }


            lastKey = key;

            // deteksi tembok;
            for(int k = 0; k < 3; k++) {
                if(!isTembok(roomLong)) {
                    //System.out.println("bukan tembok");
                    break;
                }
                else {
                    Solution.control(TURNRIGHT_);
                    System.out.println("temboook " + roomLong[0][0] + " " + roomLong[0][1]);
//                    break;
                }
            }
            result = null;
        }
        return result;
    }

    public boolean isTembok(long[][] room) {
        if(room[0][0] == 2)
            return true;
        return false;
    }

    private final  int GOFORWARD_ = 0;
    private final static int TURNRIGHT_ = 1;

    public Solution.Result findoutwhere() {
        lastKey = -1;

        //System.out.println("\n\n===== findoutwhere");
        Data result = find_data();

        int count = 0;
        while(result == null && count < 30) {
            Solution.control(GOFORWARD_);
            result = find_data();

            //System.out.println("----");
            count++;
        }

        //System.out.println("\n== result");

        Solution.Result ret = new Solution.Result();

        ret.x = result.x + 1;
        ret.y = result.y + 1;
        ret.dir = result.d;
        //System.out.println(result.toString());
//		ret.x = ret.y = ret.dir = 0; // ret.x = 0 ~ N - 1, ret.y = 0 ~ N - 1, ret.dir = UP, RIGHT, DOWN or LEFT 

        return ret;
    }
}
