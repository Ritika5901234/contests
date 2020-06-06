package leetcode.june2020.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 *Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 *
 */

public class QueueReconstructionByHeight {

    public static void main(String[] args){
        int arr[][] = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        reconstructQueue(arr);
    }

    /**
     * 7 ms runtime
     * @param people
     * @return
     */
    public static int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][];
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                if(a1[0]!=a2[0]){
                    return a2[0]-a1[0];
                }else{
                    return a1[1]-a2[1];
                }
            }
        });

        ArrayList<int[]> list = new ArrayList<int[]>();
//        for(int i=0;i<people.length;i++){
//            System.out.println(people[i][0]+" "+people[i][1]);
//        }
        for(int i=0; i<people.length; i++){
            int[] arr = people[i];
            list.add(arr[1],arr);
//            System.out.println("---------------");
//            for(int j=0; j<list.size(); j++){
//                System.out.println(j+"----->"+list.get(j)[0]+" "+list.get(j)[1]);
//            }
        }
//        System.out.println("---------------");
//        for(int i=0; i<list.size(); i++){
//            System.out.println(list.get(i)[0]+" "+list.get(i)[1]);
//        }
        for(int i=0; i<people.length; i++){
            result[i]=list.get(i);
        }

        return result;
    }


    /**
     * 2 ms run time
     * @param people
     * @return
     */
    public int[][] reconstructQueue_Faster(int[][] people) {
        quickSort(people, 0, people.length-1);
        ArrayList<int[]> res = new ArrayList<>();
        for(int[] person : people) {
            res.add(person[1], person);
        }

        int i = 0;
        for(int[] r : res) {
            people[i++] = r;
        }

        return people;
    }

    private void quickSort(int[][] people, int left, int right) {
        if (left >= right) return;
        int[] pivot = people[left];
        int i = left;
        int j = right;
        while (i < j) {
            while ((people[j][0] < pivot[0] || (people[j][0] == pivot[0] && people[j][1] >= pivot[1])) && i < j) {
                j--;
            }
            while ((people[i][0] > pivot[0] || (people[i][0] == pivot[0] && people[i][1] <= pivot[1])) && i < j) {
                i++;
            }
            if (i < j) {
                int[] tmp = people[i];
                people[i] = people[j];
                people[j] = tmp;
            }
        }
        // Swap pivot with left(start)
        people[left] = people[i];
        people[i] = pivot;
        quickSort(people, left, i - 1);
        quickSort(people, i + 1, right);
    }
}
