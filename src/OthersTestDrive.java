import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by m80028770 on 8/18/2017.
 */
public class OthersTestDrive {

    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();

        for(int i=0 ;i<10;i++) {
            myList.add(i);
        }

//        for(Integer i:myList) {
//            System.out.println("I: " + i);
//            if(i==4) {
//                System.out.println("removing ...");
//               // myList.remove(i);
//            }
//        }

        Iterator<Integer> i = myList.iterator();
        while(i.hasNext()) {
            Integer s = i.next();
            System.out.println("I: " + s);
            if(s==4) {
                i.remove();
            }
        }


        for(Integer s:myList) {
            System.out.println("s: " + s);
        }



    }

}
