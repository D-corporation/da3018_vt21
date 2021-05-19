import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//outputs the largest shared prefix with java -jar CourseArray.jar < data/all_courses.txt for example

//import jdk.tools.jlink.resources.plugins;

public class CourseArray {
    public ArrayList<String> courses = new ArrayList<String>(); // Store course names in this attribute


    CourseArray() {
        // Empty, but still needed.
    }

    /*
     * Copy constructor — new object with the same data as 'other'
     */
    CourseArray(CourseArray other) {
        courses.addAll(other.courses);     // Copy items from 'other' to this object
    }

    /*
     * selectionSort – implements the Selection Sort algorithm.
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     * Time complexity: quadratic in the number of elements in 'courses' array.
     */
    public void selectionSort() {
        // To implement
        for(int i = courses.size()-1;i >= 0;i--)
        {
            int LargestElement = GetLargestElementIndex(courses, 0, i);
            Swap(courses, LargestElement, i);
        }   
    }
    private <T extends Comparable> int GetLargestElementIndex(ArrayList<T> ArrayToSearch,int StartIndex,int EndIndex)
    {
        T MaxValue = ArrayToSearch.get(StartIndex);
        int ReturnValue = 0;
        for(int i = StartIndex+1; i <= EndIndex;i++)
        {
            if(ArrayToSearch.get(i).compareTo(MaxValue) > 0)
            {
                MaxValue = ArrayToSearch.get(i);
                ReturnValue = i;
            }
        }
        return(ReturnValue);
    }
    private <T> void Swap(ArrayList<T> ArrayToModify,int FirstIndex,int SecondIndex)
    {
        T FirstValue = ArrayToModify.get(FirstIndex);
        ArrayToModify.set(FirstIndex, ArrayToModify.get(SecondIndex));
        ArrayToModify.set(SecondIndex,FirstValue);
    }

    /*
     * mergeSort - implements the Merge Sort algorithm
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     * Time complexity: O(n lg n) comparisons, where n is the number of elements in the course array.
     */
    public void mergeSort() {
        // To implement
        courses = MergeSort(courses);
    }
    private <T extends Comparable> ArrayList<T> MergeSort(ArrayList<T> ArrayToSort)
    {
        if(ArrayToSort.size() < 2)
        {
            return(ArrayToSort);
        }
        else
        {
            ArrayList<T> FirstHalf = MergeSort(SplitArray(ArrayToSort, 0, (ArrayToSort.size()/2)-1));
            ArrayList<T> SecondHalf = MergeSort(SplitArray(ArrayToSort, (ArrayToSort.size()/2), ArrayToSort.size()-1));
            return(MergeSortedLists(FirstHalf, SecondHalf)); 
        }
    }
    private <T> ArrayList<T> SplitArray(ArrayList<T> ArrayToSplit,int StartIndex,int EndIndex)
    {
        ArrayList<T> ReturnValue = new ArrayList<T>((EndIndex-StartIndex)+1);
        for(int i = StartIndex; i<= EndIndex;i++)
        {
            ReturnValue.add(ArrayToSplit.get(i));
        }
        return(ReturnValue);
    }
    private <T extends Comparable> ArrayList<T> MergeSortedLists(ArrayList<T> FirstArray,ArrayList<T> SecondArray)
    {
        int FirstArrayIterator = 0;
        int SecondArrayIterator = 0;
        int FirstArraySize = FirstArray.size();
        int SecondArraySize = SecondArray.size();
        ArrayList<T> ReturnValue = new ArrayList<T>();
        while(FirstArrayIterator < FirstArraySize && SecondArrayIterator < SecondArraySize)
        {
            if(FirstArray.get(FirstArrayIterator).compareTo(SecondArray.get(SecondArrayIterator)) < 0)
            {
                ReturnValue.add(FirstArray.get(FirstArrayIterator));
                FirstArrayIterator++;
            }
            else
            {
                ReturnValue.add(SecondArray.get(SecondArrayIterator));
                SecondArrayIterator++;
            }
        }
        while(FirstArrayIterator < FirstArraySize)
        {
            ReturnValue.add(FirstArray.get(FirstArrayIterator));
            FirstArrayIterator+=1;
        }
        while(SecondArrayIterator < SecondArraySize)
        {
            ReturnValue.add(SecondArray.get(SecondArrayIterator));
            SecondArrayIterator+=1;
        }
        return(ReturnValue);
    }
    /*
     * javaSort - use Java's library support for sorting.
     *
     * Input: none
     * Output: none
     * Side effect: sorts the 'courses' attribute
     */
    public void javaSort() {
        // To implement
        Collections.sort(courses);
    }


    /*
     * loadData - Convenience function. Reads lines from stdin and put them in 'courses'.
     */
    private void loadData() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String c = sc.nextLine();
            courses.add(c);
        }
    }

    public boolean sorted() {
        for (int i=1; i<courses.size(); i++) {
            if (courses.get(i).compareTo(courses.get(i-1)) < 0) {
                return false;
            }
        }
        return true;
    }

    //sorting the array with mergeSort is nlog(n), or knlog(n) as it does comparisons of strings at max length k
    // and then we do n-1 comparisons with GetSharedPrefixLength. This function does at most c*k operations, so the total
    //operations of extracting the largest prefix is O((n-1)*(c*k)) = O(n) = O(kn), and the total algoritm then becomes O(knlog(n))+O(kn) = O(knlog(n))
    public String GetLargestSharedPrefix()
    {
        //Assumes that the array is sorted
        mergeSort();
        String ReturnValue = "";
        int MaxPrefixLength = 0;
        for(int i = 0; i < courses.size()-2;i++)
        {
            int NewPrefixLength = GetSharedPrefixLength(courses.get(i), courses.get(i+1)); 
            if( NewPrefixLength > MaxPrefixLength)
            {
                MaxPrefixLength = NewPrefixLength;
                ReturnValue = courses.get(i).substring(0,MaxPrefixLength);
            }
        }
        return(ReturnValue);
    }
    private int GetSharedPrefixLength(String FirstString,String SecondString)
    {
        int ReturnValue = 0;
        //assumes that the string is ascii
        byte[] FirstStringBytes = FirstString.getBytes();
        byte[] SecondStringBytes = SecondString.getBytes();
        int MinSize = Math.min(FirstStringBytes.length, FirstStringBytes.length);
        int i = 0;
        while(i < MinSize)
        {
            if(FirstStringBytes[i] == SecondStringBytes[i])
            {
                ReturnValue+=1;
                i+=1;
            }
            else
            {
                break;
            }
        }
        return(ReturnValue);
    }
    public static void main(String[] args) {
        //part 4
        /*
        CourseArray CourseToAnalyze = new CourseArray();
        CourseToAnalyze.loadData();
        System.out.println(CourseToAnalyze.GetLargestSharedPrefix());
       */
        //test part
       
        // We create 3 CourseArray objects. They contain the same
        // data, but we can apply three different sorting algorithms on them independently.
       
        
        CourseArray courses1 = new CourseArray();
        courses1.loadData();    // Read course names from stdin

        CourseArray courses2 = new CourseArray(courses1); // Copy the data to two more arrays using the copy-constructor
        CourseArray courses3 = new CourseArray(courses1);

        long checkpoints[] = new long[4]; // To store timestamps in
        // Start tests
        checkpoints[0] = System.currentTimeMillis();
        courses1.selectionSort();
        checkpoints[1] = System.currentTimeMillis();
        courses2.mergeSort();
        checkpoints[2] = System.currentTimeMillis();
        courses3.javaSort();
        checkpoints[3] = System.currentTimeMillis();

        // Ensure correct sorting
        if (!courses1.sorted()) {
            System.out.println("courses1 is not sorted");
        }
        if (!courses2.sorted()) {
            System.out.println("courses2 is not sorted");
        }
        if (!courses3.sorted()) {
            System.out.println("courses3 is not sorted");
        }

        String algs[] = {"", "selectionSort", "mergeSort", "javaSort"};
        // Output timing results
        for (int i=1; i<4; i++) {
            System.out.format("Time for task %s: %d ms", algs[i], checkpoints[i] - checkpoints[i-1]);
            System.out.println();
        }
        
    }
}
