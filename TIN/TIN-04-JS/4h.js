function secondMaxMin(arr) {
    arr.sort(function (x, y) {
        return x - y;
    });
    let uniqa = [arr[0]];
    let result = [];

    for (let j = 1; j < arr.length; j++) {
        if (arr[j - 1] !== arr[j]) {
            uniqa.push(arr[j]);
        }
    }
    result.push(uniqa[1], uniqa[uniqa.length - 2]);
    return result.join(",");
}

console.log(secondMaxMin([1, 8, 3, 4, 5, 6, 7]))
