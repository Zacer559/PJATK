function bSearch(arr, searchingFor, startIndex, endIndex) {
    if (startIndex > endIndex) return false;
    let mid = Math.floor((startIndex + endIndex) / 2);
    if (arr[mid] === searchingFor) return mid;
    if (arr[mid] > searchingFor) return bSearch(arr, searchingFor, startIndex, mid - 1);
    else return bSearch(arr, searchingFor, mid + 1, endIndex);
}

const arr = [1, 2, 3, 8, 5, 9];
console.log(bSearch(arr, 5, 0, arr.length - 1));
