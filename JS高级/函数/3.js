/* jslint esversion:6 */

function factorial(num) {
    if (num === 1) {
        return num;
    } else {
        return num * arguments.callee(num - 1);
    }
}
console.log(factorial(100));