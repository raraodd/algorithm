class UserSolutionWendy {
	/*
	  DO NOT USE ANY FIELD VARIABLE OR STATIC VARIABLE IN USER CODE.
	  ONLY LOCAL VARIABLES OF CLASS METHOD ARE ALLOWED.
	*/
	
	// The below commented methods are for your reference. If you want 
    // to use it, uncomment these methods.
    //
    // boolean mstrcmp(char[] a, char[] b) {
	//     int i = 0;
    //     while(a[i] != '\0' && a[i] == b[i])
    //         ++i;
    //     return a[i] == b[i];
    // }
    //
    // int mstrlen(char[] a) {
    //     int len = 0;
    //     while (a[len] != '\0')
    //         ++len;
    //     return len;
    // }

	// Main API :
	//   Solution.memread(char[] dest, int pos, int len)
	//   Solution.memwrite(char[] src, int pos, int len)
	
	private boolean compareStr(char[] a, char[] b) {
        final int timerSlot = 3;
		SRIN.timerStart(timerSlot);

		int p = 0;
		
		while(a[p] != '\0' && a[p] == b[p])
			++p;

		SRIN.timerStop(timerSlot, 1);
		return a[p] == b[p];
	}
	
	int findEmpty() {
        int i = 29000;
		while (i < 29000+2400) {
			char[] dst = new char[1];
	        Solution.memread(dst, i, 1);
			if(dst[0] == '\0') break;
			i++;
		}
		return i;
	}
	
	void saveKey(int pos, char[] key) {
		char[] dest = new char[13];
		pos = pos*12;
		// save key
		Solution.memwrite(key, pos, 12);
		
		// save dict key
		int posDictKey = 29000 + (pos/12);
		Solution.memwrite(key, posDictKey, 1);
	}
	
	void saveValue(int pos, char[] val) {
		char[] dest = new char[13];
		pos = (pos*12) + 32000;
		// save value
		Solution.memwrite(val, pos, 12);
		
		// save dict val
		int posDictVal = 61000 + ((pos - 32000)/12);
		Solution.memwrite(val, posDictVal, 1);
	}
	
	int deleteKey(char[] key) {
		char[] buff= new char[2400];
		Solution.memread(buff, 29000, 2400);
		
		int i = 0;
		while(i < 2400) {
			if(buff[i] == key[0]) {
				char[] dest = new char[13];
				int pos = i*12;
				Solution.memread(dest, pos, 12);
				boolean compare = compareStr(key, dest);
				if(compare) {
					char[] empty = new char[13];
					
					// delete dict key and val
					Solution.memwrite(empty, i+29000, 1);
					Solution.memwrite(empty, i+61000, 1);
					
				}
				break;
			}
			i++;
		}
		return i;
	}
	
	void getKey(char val[], char key_out[]) {
		char[] dest = new char[13];
		char[] buff= new char[2400];
		// read dict value
		Solution.memread(buff, 61000, 2400);
		
		int i = 0;
		while (i < 2400) {
			if(buff[i] == val[0]) {
				int pos = i*12;
				// read full val
				Solution.memread(dest, pos+32000, 12);
				boolean compare = compareStr(val, dest);
				if(compare) {
					// get key
					Solution.memread(key_out, pos, 12);
					key_out[12] = '\0';
					break;
				}	
			}
			i++;
		}
	}
	
	void getVal(char key[], char val_out[]) {
		char[] dest = new char[13];
		char[] buff= new char[2400];
		// read dict value
		Solution.memread(buff, 29000, 2400);
		
		int i = 0;
		while (i < 2400) {
			if(buff[i] == key[0]) {
				int pos = i*12;
				// read full val
				Solution.memread(dest, pos, 12);
				boolean compare = compareStr(key, dest);
				if(compare) {
					// get key
					Solution.memread(val_out, pos+32000, 12);
					val_out[12] = '\0';
					break;
				}	
			}
			i++;
		}
	}
	
	boolean init(int N) {
		
		// memwrite sebanyak N
		char[] empty = new char[N*12];
		// init key and value
		Solution.memwrite(empty, 0, N*12);
		Solution.memwrite(empty, 32000, N*12);
		
		// init dict
		empty = new char[N]; 
		Solution.memwrite(empty, 29000, N);
		Solution.memwrite(empty, 61000, N);
		
        if (N >= 10 && N <= 2400) return true;
		return false; // if the value of 'false' is returned, this testcase would be skipped
	}
	
	void put(char[] key_in, char[] value_in) {
		final int timerSlot = 5;
		SRIN.timerStart(timerSlot);

		int pos = findEmpty() - 29000;
		
		saveKey(pos, key_in);
		saveValue(pos, value_in);

		SRIN.timerStop(timerSlot ,1);
	}
	
	void del(char[] key_in) {
		final int timerSlot = 6;
		SRIN.timerStart(timerSlot);

		deleteKey(key_in);

		SRIN.timerStop(timerSlot ,1);
	}
	
	void get(char[] key_in, char[] value_out) {
		final int timerSlot = 7;
		SRIN.timerStart(timerSlot);

		getVal(key_in, value_out);

		SRIN.timerStop(timerSlot ,1);
	}
	
	void getkey(char[] value_in, char[] key_out) {
		final int timerSlot = 8;
		SRIN.timerStart(timerSlot);

		getKey(value_in, key_out);

		SRIN.timerStop(timerSlot ,1);
	}
}