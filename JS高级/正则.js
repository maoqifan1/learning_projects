/*jslint esversion:6 */
let pattern = /[bc]at/i;
const str = "she is a cat or bat";
let matches = pattern.exec(str);
console.log(matches[0]);
console.log(RegExp.leftContext);
console.log(RegExp.rightContext);