package search;

// VAR.COND: array sorted by descending from 0 to k &&
//           then a[i+1]>a[i](only one jump) &&
//           then array sorted by descending from i+1 to array.length-1 &&
//           array does not contain duplicate elements
// VAR.COND.2:
//           for i in [0..k-1] a[i]>=a[i+1] &&
//           for j in [k..array.length-1) a[j]>=a[j+1] &&
//             array does not contain duplicate elements

// VAR.COND_MAIN: COND && valid array of int values &&
//           count = count of odd values && i = args.length();
public class BinarySearchShift {

    // P: main got valid args
    // next {args.length()-1} elements of array && COND
    // Q: print k = cyclic shifted array by k;
    static public void main(String[] args) {
        // P: true
        int[] arr = new int[args.length];
        int i = 0;
        int count = 0;
        // Q: arr initialized, arr.length = args.length - 1 && variables count, i initialized
        // P: valid args && every element from args[1..args.length()] can parse to int
        // I: (i >= 0) && (i < args.length())
        while (i < args.length) {
            // P: I && (i <= args.length())
            // P: I && valid args[i]
            int value = Integer.parseInt(args[i]);
            // Q: I && variable "value" initialized with value args[i]
            arr[i] = value;
            // Q: I && initialize array of values
            // P: Q
            if (value % 2 == 1) {
                // P: I && (value % 2 == 1)
                count++;
                // Q: I && "count" incremented by 1
            }
            // P: I && true
            i++;
            // Q: I && "i" incremented by 1
        }
        int res;
        // Q: res initialized
        // P: COND && res initialized
        if (count % 2 == 0) {
            // P: (count % 2 == 0) && COND_MAIN ->
            //    -> (arr initialized) && (arr.length = args.length - 1) &&
            //      next elements of array, which correspond COND &&
            //      every element was parsed to int
            res = recursiveBinarySearch(arr, 0, arr.length - 1);
            // Q: res get returned value which equals {cyclic shifted array by k};
        } else {
            // P: (count % 2 == 0) && COND_MAIN ->
            //    -> (arr initialized) && (arr.length = args.length - 1) &&
            //      next elements of array, which correspond COND &&
            //      every element was parsed to int
            res = iterBinarySearch(arr);
            // Q: res get returned value which equals {cyclic shifted array by k};
        }
        // P=Q
        System.out.println(res);
    }

    // P: COND
    // Q: return k = cyclic shifted array by k;
    static int recursiveBinarySearch(int[] arr, int left, int right) {
        // P: true
        // I: (left' < right') && (left' >= -1) && (right' <= arr.length())
        if (arr[left] > arr[right]) {
            // P:(arr[left] > arr[right]) && COND ->
            // array sorted descending -> k'=0;
            return 0;
            // Q: return (0) which equals {cyclic shifted array by k};
        }
        if (right <= left + 1) {
            // P: I && (right <= left + 1) ->
            //      -> the array is sorted in descending order including the left,
            //      only the right could contain a jump
            return right;
            // Q: return (right' = k) which equals {cyclic shifted array by k};
        }
        // Q: I && (right' - left' > 1)
        // P: (right' - left') >= 2
        // left' + (right' - left')/2 -> (left' + right')/2
        int mid = (left + right) / 2;
        // Q: I && (mid' > left') && (mid' < right')
        if (arr[left] >= arr[mid]) {
            // P: I && (arr[left'] > arr[mid']) ->
            //      -> arr sorted descending from left' to mid'
            return recursiveBinarySearch(arr, mid, right);
            // P: left' = mid'
            // Q: I && left' increase -> (right'-left') decrease
        } else {
            // P: I && (arr[left'] <= arr[mid']) ->
            //      -> the value jump is in the interval [left'..mid']
            //      && there is only one jump in array ->
            //          -> arr sorted descending from mid' to right'
            return recursiveBinarySearch(arr, left, mid);
            // P: right' = mid'
            // Q: I && right' decrease -> (right'-left') decrease
        }
    }
    // Q: return k = cyclic shifted array by k;

    // P: COND
    // Q: return k = cyclic shifted array by k;
    static int iterBinarySearch(int[] arr) {
        // P: true
        int left = 0;
        int right = arr.length - 1;
        if (arr[left] > arr[right]) {
            // P:(arr[left] > arr[right]) && COND ->
            // array sorted descending -> k'=0;
            return 0;
            // Q: return (right' = k) which equals {cyclic shifted array by k};
        }

        // I: (left' < right') && (left' >= -1) && (right' <= arr.length())
        while (right > left + 1) {
            // P: I && (right' > left' + 1)
            // P: (right' - left') >= 2
            // left' + (right' - left')/2 -> (left' + right')/2
            int mid = (left + right) / 2;
            // Q: I && (mid' > left') && (mid' < right')
            if (arr[left] > arr[mid]) {
                // P: I && (arr[left'] > arr[mid']) ->
                //      -> arr sorted descending from left' to mid'
                left = mid;
                // P: left' = mid'
                // Q: I && left' increase -> (right'-left') decrease
            } else {
                // P: I && (arr[left'] <= arr[mid']) ->
                //      -> the value jump is in the interval [left'..mid']
                //      && there is only one jump in array ->
                //          -> arr sorted descending from mid' to right'
                right = mid;
                // P: right' = mid'
                // Q: I && right' decrease -> (right'-left') decrease
            }
            // Q: I && right' - left' decrease
        }
        // Q: I && right' - left' <= 1 ->
        //      -> the array is sorted in descending order including the left,
        //      only the right could contain a jump
        return right;
        // Q: return (right' = k) which equals {cyclic shifted array by k};
    }
    // Q: return k = cyclic shifted array by k;
}