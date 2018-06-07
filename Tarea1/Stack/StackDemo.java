class Stack {
    private int MAX = 10;
    private int[] stack = new int[MAX];
    private int index = 0;

    public boolean empty() {
        return index == 0;
    }

    public boolean full() {
        return index == MAX;
    }

    public void push(int data) {
        if (full()) {
            System.out.println("Stack is full.");
            return;
        }
        stack[index] = data;
        index += 1;
    }

    public int pop() {
        if (empty()) {
            System.out.println("Stack underflow.");
            return 0;
        }
        index -= 1;
        return stack[index];
    }
}

public class StackDemo {
    public static void main(String[] args) {
        Stack stk1 = new Stack();
        Stack stk2 = new Stack();

        for(int i = 50; i < 160; i+=10) stk1.push(i);
        for(int i = 10; i < 160; i+=20) stk2.push(i);

        System.out.println("Stack in stk1: ");
        for(int i = 0; i < 10; i++)
            System.out.println(stk1.pop());

        System.out.println("Stack in stk2: ");
        for(int i = 0; i < 10; i++)
            System.out.println(stk2.pop());

        TestStack();
    }

    public static void TestStack() {
        System.out.println("\nRUNNING Stack TESTS");

        {
            System.out.printf("Empty... ");
            assert !(new Stack()).full();
            assert (new Stack()).empty();
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Push one... ");
            Stack s = new Stack();
            s.push(1);
            assert !(s.empty());
            assert !(s.full());
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Full... ");
            Stack s = new Stack();
            for (int i = 0; i < 10; i++) {
                s.push(i);
            }

            assert !(s.empty());
            assert s.full();
            System.out.println("SUCCESS");
        }

        {
            System.out.printf("Pop all... ");
            Stack s = new Stack();
            for (int i = 0; i < 10; i++) {
                s.push(i);
            }

            for (int i = 0; i < 10; i++) {
                s.pop();
            }

            assert s.empty();
            System.out.println("SUCCESS");
        }

        System.out.println("DONE");
    }
}
