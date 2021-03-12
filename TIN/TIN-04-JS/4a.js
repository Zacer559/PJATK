// Factorial Iteration using declaration
function factorial_iteration(x) {
    let res = 1;
    for (let i = 2; i <= x; i++) {
        res = res * i;
    }
    return res;
}
// Factorial recursive using function expression
let factorial_recursive = function (x) {

    if (x === 0) {
        return 1;
    }
    return x * factorial_recursive(x - 1);
};

console.log(factorial_iteration(5))
console.log(factorial_recursive(5))