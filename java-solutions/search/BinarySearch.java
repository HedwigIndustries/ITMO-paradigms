package search;
// VAR.COND: I && valid array of int values, which sorted by descending &&
//           count = count of odd values && i = args.length();

public class BinarySearch {

    // P: main got valid args, args[0] - search value in array,
    // next {args.length()-1} elements of array, which sorter by descending elements values
    // Q: print value = min (value | {x <= a[value]})
    static public void main(String[] args) {
        // P: true
        int x = Integer.parseInt(args[0]);
        int[] arr = new int[args.length - 1];
        int i = 1;
        int count = 0;
        // Q: x = (int) args[0] && arr initialized, arr.length = args.length - 1 && variables count, i initialized
        // P: valid args && every element from args[1..args.length()] can parse to int && args[i] >= args[i+1]
        // I: (i >= 1) && (i <= args.length())
        while (i < args.length) {
            // P: I && (i <= args.length())
            // P: I && valid args[i]
            int value = Integer.parseInt(args[i]);
            // Q: I && variable "value" initialized with value args[i]
            arr[i - 1] = value;
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
        // Q: COND
        int res;
        // Q: res initialized
        // P: COND && res initialized
        if (count % 2 == 0) {
            // P: (count % 2 == 0) && COND ->
            //    -> (x = args[0]) && (arr initialized) && (arr.length = args.length - 1) &&
            //      next elements of array, which sorter by descending elements values &&
            //      every element was parsed to int
            res = recursiveBinarySearch(x, arr, -1, arr.length);
            // Q: res get returned value: value = min (value | {x <= a[value]})
        } else {
            // P: (count % 2 == 0) && COND ->
            //    -> (x = args[0]) && (arr initialized) && (arr.length = args.length - 1) &&
            //      next elements of array, which sorter by descending elements values &&
            //      every element was parsed to int
            res = iterBinarySearch(x, arr);
            // Q: res get returned value: value = min (value | {x <= a[value]})
        }
        // P: res get returned value: value = min (value | {x <= a[value]})
        System.out.println(res);
    }

    // P: function got valid args &&
    //    x = args[0] - searching value &&
    //    massive sorted descending &&
    //    (left >= -1) && (right <= arr.length()) && (left <= right)
    // Q: return value = min value | {x <= a[value]}
    static int recursiveBinarySearch(int x, int[] arr, int left, int right) {
        // P: true
        // I: (left' < right') && (left' >= -1) && (right' <= arr.length()) &&
        //     (arr[left'] > x) && (x >= arr[right'])
        if (right - left <= 1) {
            // P: I && (right' - left' <= 1) ->
            //      -> (arr[left'] > x) && (arr[right'] <= x) && (right' - left' = 1) ->
            //          -> (arr[right'-1] > x) && (arr[right'] <= x)
            return right;
            // Q: (arr[right'-1] > x) && (arr[right'] <= x) ->
            //      -> return value = min value | {x <= a[value]}
        }
        // Q: I && (right' - left' > 1)
        // P: (right' - left') >= 2
        // left' + (right' - left')/2 -> (left' + right')/2
        int mid = (left + right) / 2;
        // Q: I && (mid' > left') && (mid' < right')
        if (x >= arr[mid]) {
            // P: I && (x >= arr[mid]) && (mid' > left') && (mid' < right') ->
            //      -> (arr[left'] > x) && (x >= arr[right']) && (x >= arr[mid]) &&
            //      (right' - left' > mid' - left')
            return recursiveBinarySearch(x, arr, left, mid);
            // P: right' = mid'
            // Q: I && (right' decrease -> (right'-left') decrease)
        } else {
            // P: I && (x < arr[mid]) && (mid' > left') && (mid' < right') ->
            //      -> (arr[left'] > x) && (x >= arr[right']) && (x < arr[mid]) &&
            //      (right' - left' > right' - mid')
            return recursiveBinarySearch(x, arr, mid, right);
            // P: right' = mid'
            // Q: I && (left' increase -> (right'-left') decrease)
        }
    }
    // Q: (arr[right'-1] > x) && (arr[right'] <= x) ->
    //      -> return value = min(value | {x <= a[value]})

    // P: function got valid args &&
    //       x = args[0] - searching value &&
    //       massive sorted descending
    // Q: return value = min value | {x <= a[value]}
    static int iterBinarySearch(int x, int[] arr) {
        // P: true
        int left = -1;
        int right = arr.length;
        int mid;
        // I: (left' < right') && (left' >= -1) && (right' <= arr.length())
        //     (arr[left'] > x) && (x >= arr[right'])
        while (right > left + 1) {
            // P: I && (right' > left' + 1)
            // P: (right' - left') >= 2
            // left' + (right' - left')/2 -> (left' + right')/2
            mid = (left + right) / 2;
            // Q: I && (mid' > left') && (mid' < right')
            if (x >= arr[mid]) {
                // P: I && (x >= arr[mid]) && (mid' > left') && (mid' < right') ->
                //      -> (arr[right'] <= x) && (arr[mid] <= x) && (arr[left'] > x) &&
                //      (right' - left' > mid' - left')
                right = mid;
                // P: right' = mid'
                // Q: I && right' decrease -> (right'-left') decrease
            } else {
                // P: I && (x < arr[mid]) && (mid' > left') && (mid' < right') ->
                //      -> (arr[left'] > x) && (arr[mid] > x) && (arr[right'] <= x) &&
                //      (right' - left' > right' - mid')
                left = mid;
                // P: left' = mid'
                // Q: I && left' increase -> (right'-left') decrease
            }
            // Q: I && right' - left' decrease
        }
        // Q: I && right' - left' <= 1 ->
        //      -> (arr[left'] > x) && (arr[right'] <= x) && (right' - left' = 1) ->
        //          -> (arr[right'-1] > x) && (arr[right'] <= x)
        return right;
    }
    // Q: (arr[right'-1] > x) && (arr[right'] <= x) ->
    //      -> return value = min(value | {x <= a[value]})

}
