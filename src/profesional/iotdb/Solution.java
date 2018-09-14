import java.util.Scanner;

abstract class SRIN {
	public static String _inFile = "src/sample_input.txt";
	public static boolean _isDebug = (123 < 45); 		// false
	public static int _repeat = 88;						// 0

	public static int _capacity = 9;
	public static long _timer[] = new long[_capacity];
	public static long _count[] = new long[_capacity];
	public static long _weight[] = new long[_capacity];
	public static boolean debugMaxLoad(boolean m) {	return m;}
	public static void debugPrint(Object... os) {if(!_isDebug)return;	for (Object o : os)	System.out.print(o +"\t");;	System.out.println();}
	public static void timerPrint(Object... os) {if(!_isDebug)return;
		for (long l : _timer) System.out.printf(" \t%9.4f", (l/1e6));	System.out.println();
		for (long l : _weight) System.out.print("\t\t"+ l);				System.out.println();
		for (long l : _count) System.out.print("\t\t"+ l);				System.out.println();
//		debugPrint(os);
	}
	public static void timerStart(int i) {			if(!_isDebug)return;	_timer[i] -= System.nanoTime();	}
	public static void timerStop(int i, int w) {	if(!_isDebug)return;	_timer[i] += System.nanoTime(); 	_weight[i] += w; 					_count[i]++;}
	public static void timerReset() {				if(!_isDebug)return;	_timer = new long[_capacity];		_weight = new long[_capacity];		_count = new long[_capacity];
	}
	public static void assertTrue(boolean test, String msg, Object... os) {
		if(!_isDebug)return;	if (test) return; debugPrint(os);
		throw new NullPointerException(msg);
	}
	// public static void main(String args[]) {		mainRepeater(args);	}
	public static void mainRepeater(String args[]) {
		try {
		long duration,nn = -System.nanoTime();
		for(int i = _repeat; i-->=0;) {
			if (null != _inFile) System.setIn(new java.io.FileInputStream(_inFile));
			Solution.mainOri(args);

			System.out.printf("--> repeat_0%d: %7.2f ms (%s)\n\n",(_repeat-i)
					,(duration = nn +System.nanoTime())/1e6, Solution.usersolution.getClass().getName());
			// timerPrint(null);

			boolean alreadyWarm = (4e9 < duration);
			if (alreadyWarm) break;
		}
		}catch (Exception e) {	e.printStackTrace(System.out);
		}finally {}
	}
}



class Solution
extends SRIN
{	
	private final static int MEMORYSIZE 		= 65536;
	
	private final static char[] mem = new char[MEMORYSIZE];
	
	private final static int CMD_PUT			= 100;
	private final static int CMD_DEL			= 200;
	private final static int CMD_GET			= 300;
	private final static int CMD_GETKEY			= 400;
	
	private final static int MAXLEN				= 12;

//	final static UserSolution usersolution = new UserSolution();
	final static UserSolutionWendy usersolution = new UserSolutionWendy();


	public static boolean memread(char[] dest, int pos, int len) {
		final int timerSlot = 2;
		timerStart(timerSlot);

		if (pos < 0 || len <= 0 || pos + len > MEMORYSIZE)
			return false;
		
		for (int i = 0; i < len; ++i)
			dest[i] = mem[i + pos];
		timerStop(timerSlot, len);

		return true;
	}


	public static boolean memwrite(char[] src, int pos, int len) {
		final int timerSlot = 1;
		timerStart(timerSlot);

		if (pos < 0 || len <= 0 || pos + len > MEMORYSIZE)
			return false;

		for (int i = 0; i < len; ++i)
			mem[i + pos] = (char)(src[i] & 0xff);
		timerStop(timerSlot, len);

		return true;
	}
	
	private static boolean ztrcmp(char[] a, char[] b) {
		int p = 0;
		
		while(a[p] != '\0' && a[p] == b[p])
			++p;
		
		return a[p] == b[p];
	}
	
	private static void String2Char(String s, char[] b) {
		int n = s.length();
		for (int i = 0; i < n; ++i)
			b[i] = s.charAt(i);
		b[n] = '\0';
	}
	
	private static boolean run(Scanner sc) {
		int Q = sc.nextInt();
		int N = sc.nextInt();
		
		boolean okay = usersolution.init(N);
		
		char[] key = new char[MAXLEN + 1];
		char[] value = new char[MAXLEN + 1];
		char[] key_u = new char[MAXLEN + 1];
		char[] value_u = new char[MAXLEN + 1];
		
		for (int d = 0; d < Q; ++d) {
			int cmd = sc.nextInt();
			switch(cmd) {
			case CMD_PUT:
				String2Char(sc.next(), key);
				String2Char(sc.next(), value);
				if (okay) usersolution.put(key, value);
				break;
			case CMD_DEL:
				String2Char(sc.next(), key);
				if (okay) usersolution.del(key);
				break;
			case CMD_GET:
				String2Char(sc.next(), key);
				String2Char(sc.next(), value);
				if (okay) {
					usersolution.get(key, value_u);
					if (ztrcmp(value, value_u) == false)
						okay = false;
				}
				break;
			case CMD_GETKEY:
				String2Char(sc.next(), value);
				String2Char(sc.next(), key);
				if (okay) {
					usersolution.getkey(value,  key_u);
					if (ztrcmp(key, key_u) == false)
						okay = false;
				}
				break;
			default:
				break;
			}
		}
		
		return okay;
	}

	public static void main(String args[]) {		mainRepeater(args);	}
	public static void mainOri(String[] args) throws Exception {
		int TC;
		Scanner sc = new Scanner(System.in);
		TC = sc.nextInt();
        
        int totalscore = 0;
		for (int testcase = 1; testcase <= TC; ++testcase) {
			timerStart(0);

			int score = run(sc) ? 100 : 0;
			totalscore += score;
			System.out.println("#" + testcase + " " + score);

			timerStop(0, 1);
			timerPrint(null);
			timerReset();
		}

        System.out.println("total score = " + totalscore / TC);
		sc.close();
	}
}