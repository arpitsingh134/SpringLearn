package com.java.learn;

public class Test {
    public static void main(String[] args) {

        /*
          3-->5-->7-->9-->

          inputinput linked list: 3->5->7->9
        output linked list: 3->5->8->0

        [0,9]
        9->9->9->9
        1->0->0->0->0

         3->6->0->0


          3579+1-->3580




         */
    }


    public static LL addOneInLL(LL head) {

        if (head == null) {
            return null;
        }
        LL temp = reverseLL(head);
        int carry = 1;


        //1-->2-->3  --> 4-->2-->1  ( 3+1) %10 --->4   //
        LL previous = null;
        while (temp != null) {   //0->1
            if(carry!=0)
            {
                int sum = (carry + temp.val) % 10;   // 9-->9-->9-->1-->4 sum=0 carr  // 0-->9-->-->9
                carry = (temp.val + carry) / 10;    //carry= 1 // // 0-->9-->9-->1-->4 sum=0 carry=1
                temp.val = sum;
            }
            previous = temp; //previous pointing at 0
            temp = temp.next;  //temp=9    //1-->
        }

        if (previous != null && carry != 0) {
            LL newNode = new LL(carry);
            previous.next = newNode;
            carry = 0;
            previous=previous.next;
        }
        return reverseLL(previous);
    }


    public static LL reverseLL(LL head) {
        if (head == null) return null;
        LL prev = null;

        while (head != null) {
            LL next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }


    static class LL {
        int val;
        LL next;

        public LL(int val) {
            this.val = val;
        }

    }
}
