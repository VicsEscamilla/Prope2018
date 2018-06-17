interface CharStack {
    void push(char c);
    char pop();
    boolean isEmpty();
}

interface CharList {
    void add(char c);
    void insert(int i, char c);
    char remove(int i);
    char get(int i);
    int size();
}

class CharSL implements CharStack, CharList{
    class Node {
        public char value;
        public Node prior;

        public Node(char c) {
            value = c;
            prior = null;
        }
    }

    private Node last = null;
    private int size = 0;

    // CharStack methods

    public boolean isEmpty() {
        return last == null;
    }

    public void push(char c) {
        if (isEmpty()) {
            last = new Node(c);
            size += 1;
            return;
        }

        Node newnode = new Node(c);
        newnode.prior = last;
        last = newnode;
    }

    public char pop() {
        if (isEmpty()) {
            return 0;
        }

        char c = last.value;
        last = last.prior;
        size -= 1;

        return c;
    }

    // CharList methods

    public void add(char c) {
        if (isEmpty()) {
            push(c);
            return;
        }

        Node n = last;
        while (n.prior != null) {
            n = n.prior;
        }

        Node newnode = new Node(c);
        newnode.prior = null;
        n.prior = newnode;
        size += 1;
    }

    public void insert(int i, char c) {
        if (i < 0 || i > size) {
            return;
        }

        if (i == size) {
            add(c);
            return;
        }

        if (i == 0) {
            push(c);
            return;
        }

        Node n = last;
        for (int index=0; index<i-1; index++) {
            n = n.prior;
        }

        Node backup = n.prior;
        Node newnode = new Node(c);
        n.prior = newnode;
        newnode.prior = backup;
        size += 1;
    }

    public char remove(int i) {
        if (i < 0 || i >= size) {
            return 0;
        }

        if (i == 0) {
            char c = last.value;
            last = last.prior;
            size -= 1;
            return c;
        }

        Node n = last;
        for (int index=0; index<i-1; index++) {
            n = n.prior;
        }

        char c = n.prior.value;
        n.prior = n.prior.prior;
        size -= 1;

        return c;
    }

    public char get(int i) {
        if (i < 0 || i >= size) {
            return 0;
        }

        Node n = last;
        for (int index=0; index<i; index++) {
            n = n.prior;
        }

        return n.value;
    }

    public int size() {
        return size;
    }

    // CharSL methods

    public String toString() {
        String s = "";

        Node n = last;
        while (n != null) {
            s += n.value;
            n = n.prior;
        }

        return s;
    }
}

public class CharSLDemo {
    public static void main(String[] args) {

        TareaParte1();
        TareaParte2();

        TestStack();
        TestList();
    }

    public static void TareaParte1() {
        System.out.println("\nTAREA PARTE 1");

        // 1
        CharList list = new CharSL();

        // 2
        list.add('b');
        list.add('c');
        list.add('d');
        list.add('f');
        list.add('g');

        // 3
        list.insert(3, 'e');

        // 4
        list.insert(0, 'a');

        // 5
        list.insert(list.size(), 'h');

        // 6
        System.out.println("Char with index 2: " + list.get(2));

        // 7
        list.remove(2);

        // 8
        list.remove(0);

        // 9
        list.remove(list.size()-1);

        // 10
        System.out.println("List: " + list.toString());
        System.out.println("Size: " + Integer.toString(list.size()));

        System.out.println("SUCCESS");
    }

    public static void TareaParte2() {
        System.out.println("\nTAREA PARTE 2");

        assertBalanced("(a + b) * c");
        assertBalanced("d – (x / (y – z))");
        assertBalanced("a * b");
        assertBalanced("()");
        assertBalanced("(a+b+(d))");

        assertNotBalanced("a + (b – c");
        assertNotBalanced("a + )b – c(");
        assertNotBalanced("(a * (b + c)) / d)");
        assertNotBalanced("(()");
        assertNotBalanced(")");
        assertNotBalanced("a+b+(d))");

        System.out.println("SUCCESS");
    }

    public static void assertBalanced(String expression) {
        assert isBalanced(expression) : "\"" + expression + "\" NOT balanced";
    }

    public static void assertNotBalanced(String expression) {
        assert !isBalanced(expression) : "\"" + expression + "\" IS balanced";
    }

    public static boolean isBalanced(String expression) {
        CharStack s = new CharSL();

        for (int i=0; i<expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(':
                    s.push(expression.charAt(i));
                    break;
                case ')':
                    if (s.pop() != '(')
                        return false;
                    break;
            }
        }

        if (s.isEmpty())
            return true;
        return false;
    }

    public static void TestStack() {
        System.out.println("\nRUNNING stack TESTS");
        {
            System.out.printf("PushPop... ");
            CharSL c = new CharSL();

            assert c.isEmpty();

            c.push('a');
            c.push('b');
            c.push('c');
            assert !c.isEmpty() : "CharSL is empty!";

            assert c.toString().equals("cba") : "expected \"cba\", got \"" + c.toString() + "\"";

            assertEq(c.pop(), 'c');
            assertNotEmpty(c);

            assertEq(c.pop(), 'b');
            assertNotEmpty(c);

            assertEq(c.pop(), 'a');
            assertEmpty(c);

            System.out.println("SUCCESS");
        }
    }

    public static void TestList() {
        System.out.println("\nRUNNING list TESTS");
        {
            System.out.printf("AddGet... ");
            CharSL c = new CharSL();
            c.add('a');
            c.add('b');
            c.add('c');

            assert c.toString().equals("abc") : "expected \"abc\", got \"" + c.toString() + "\"";
            assert c.size() == 3;
            assertEq(c.get(0), 'a');
            assertEq(c.get(1), 'b');
            assertEq(c.get(2), 'c');
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("AddRemove... ");
            CharSL c = new CharSL();
            c.add('a');
            c.add('b');
            c.add('c');

            assert c.toString().equals("abc") : "expected \"abc\", got \"" + c.toString() + "\"";
            assert c.size() == 3;
            assertEq(c.remove(0), 'a');
            assertEq(c.remove(0), 'b');
            assertEq(c.remove(0), 'c');

            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Insert... ");
            CharSL c = new CharSL();
            c.insert(0, 'a');
            c.insert(1, 'b');
            c.insert(2, 'd');
            assert c.toString().equals("abd") : "expected \"abd\", got \"" + c.toString() + "\"";
            assert c.size() == 3;

            c.insert(2, 'c');
            assert c.toString().equals("abcd") : "expected \"abcd\", got \"" + c.toString() + "\"";
            assert c.size() == 4;

            c.insert(26, 'z');
            assert c.toString().equals("abcd") : "expected \"abcd\", got \"" + c.toString() + "\"";
            assert c.size() == 4;

            c.insert(-1, '8');
            assert c.toString().equals("abcd") : "expected \"abcd\", got \"" + c.toString() + "\"";
            assert c.size() == 4;

            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Remove... ");
            CharSL c = new CharSL();

            // remove on empty list
            assert c.remove(0) == '\0';
            assert c.toString().equals("") : "expected \"\", got \"" + c.toString() + "\"";
            assert c.size() == 0;

            c.add('a');
            c.add('b');
            c.add('c');

            // remove negative number
            assert c.remove(-1) == '\0';
            assert c.toString().equals("abc") : "expected \"abc\", got \"" + c.toString() + "\"";
            assert c.size() == 3;

            // remove outside range number
            assert c.remove(100) == '\0';
            assert c.toString().equals("abc") : "expected \"abc\", got \"" + c.toString() + "\"";
            assert c.size() == 3;

            // remove success
            assert c.remove(1) == 'b';
            assert c.toString().equals("ac") : "expected \"ac\", got \"" + c.toString() + "\"";
            assert c.size() == 2;

            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Get... ");
            CharSL c = new CharSL();

            // get on empty list
            assert c.get(0) == '\0';

            c.add('a');
            c.add('b');
            c.add('c');

            // get negative number
            assert c.get(-1) == '\0';

            // get outside range number
            assert c.get(100) == '\0';

            // get success
            assert c.get(1) == 'b';

            System.out.println("SUCCESS");
        }
    }

    public static void assertEq(char a, char b) {
        assert a == b: "'" + a + "' != '" + b + "'";
    }

    public static void assertEmpty(CharSL c) {
        assert c.isEmpty() : "CharSL is NOT empty!";
    }

    public static void assertNotEmpty(CharSL c) {
        assert !c.isEmpty() : "CharSL is empty!";
    }
}
