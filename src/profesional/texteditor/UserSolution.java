package profesional.texteditor;

public class UserSolution {

    class TextEditor {
        char text[];
        int textLength = 0;
        int cursorPosition;

        char selectedText[];
        int startSelectedText;
        int selectedTextLength;
        boolean isTextSelected;

        char clipboard[];
        int clipboardLength;


        public TextEditor(int N, char str[]) {
            text = new char[1000000];
            for (int i = 0; i < str.length; i++)
                text[i] = str[i];
            textLength = N;
            cursorPosition = N-1;
            System.out.println("Create text editor " + getString(text) + " " + textLength);
        }

        public void setSelectedText(int start, int end) {
            selectedText = new char[1000000];
            startSelectedText = start;
            int x = 0;
            for (int i = start; i <= end; i++) {
                if(text[i] == '\0') break;
                selectedText[x] = text[i];
                x++;
            }
            selectedTextLength = x;
        }


        public void copyClipboard() {
            clipboard = new char[1000000];
            int i = 0;
            while (selectedText[i] != '\0') {
                clipboard[i] = selectedText[i];
                i++;
            }
            isTextSelected = false;
            clipboardLength = selectedTextLength;
        }

        public void insert(int N, char[] str) {
            char tmp[] = new char[1000000];
            int i = cursorPosition;

        }

        public void deleteSelectedText()
    }

    TextEditor te;
    int count;

    public void init(int N, char[] str) {
        count = 1;
        System.out.println("\n" + count + " init " + getString(str) + " " + N);
        count++;
        te = new TextEditor(N, str);
    }
    
    public void select_string(int mLeft, int mRight) {
        System.out.println("\n" + count + " select_string " + mLeft + " " + mRight);
        count++;
        if(mLeft > te.textLength - 1) {
            te.isTextSelected = false;
            te.cursorPosition = te.textLength - 1;
        }
        if (mRight > te.textLength - 1) {
            te.isTextSelected = true;
            te.setSelectedText(mLeft, te.textLength - 1);
            te.cursorPosition = mLeft;
        } else {
            te.isTextSelected = true;
            te.setSelectedText(mLeft, mRight);
            te.cursorPosition = mLeft;
        }
        System.out.println("Selected text " + getString(te.selectedText) + " " + te.selectedTextLength + " " + te.cursorPosition);
    }
    
    public void copy_string() {
        System.out.println("\n" + count + " copy_string");
        count++;
        if(!te.isTextSelected) return;
        te.copyClipboard();
        System.out.println("Copy text " + getString(te.clipboard) + " " + te.clipboardLength + " " + te.cursorPosition);
    }   

    public int paste_string() {
        return 0;
    }
    
    public int insert_string(int M, char[] str) {
        te.insert(M, str);
        return te.textLength;
    }

    public void get_substring(int left, int right, char[] res_str) {

    }

    public String getString(char[] c) {
        String s = "";
        int i = 0;
        while (c[i] != '\0') {
            s += c[i];
            i++;
        }
        return s;
    }
}
