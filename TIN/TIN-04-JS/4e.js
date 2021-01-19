function longest(str) {
    let splitted = str.split(" ");
    let longestIndex = 0;
    let word = "";
    for (let i = 0; i < splitted.length; i++) {
        if (splitted[longestIndex].length < splitted[i].length) {
            longestIndex = i;
            word = splitted[i];
        }
    }
    return word;
}

console.log(longest("long longlong verylonglong long "))