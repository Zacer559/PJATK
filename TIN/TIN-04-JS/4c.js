function isPalindrome(str) {
    str = str.toLowerCase().replace(/[^a-z]+/g,"");
    return str === str.split("").reverse().join("")
 }

console.log(isPalindrome("kajak"));
console.log(isPalindrome("kajaak"));