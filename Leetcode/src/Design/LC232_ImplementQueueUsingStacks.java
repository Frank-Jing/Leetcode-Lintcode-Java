package Leetcode.src.Design;

import java.util.Stack;

public class LC232_ImplementQueueUsingStacks {
    class MyQueue {

        Stack<Integer> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        public MyQueue() {
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            int res = stack2.pop();
            while(!stack2.empty()){
                stack1.push(stack2.pop());
            }
            return res;
        }

        public int peek() {
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
            int res = stack2.peek();
            while(!stack2.empty()){
                stack1.push(stack2.pop());
            }
            return res;
        }

        public boolean empty() {
            return stack1.empty();
        }
    }

    // O(1) as every element is moved only once
    class MyQueue_II {
        Stack<Integer> queue;
        Stack<Integer> stack;
        public MyQueue_II() {
            queue = new Stack<>();
            stack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
        }

        public int pop() {
            peek();
            return queue.pop();
        }

        public int peek() {
            if(queue.isEmpty()){
                while(!stack.isEmpty()){
                    queue.push(stack.pop());
                }
            }
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty() && stack.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
