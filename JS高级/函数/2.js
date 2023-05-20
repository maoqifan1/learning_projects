/* jslint esversion:6 */
function createComparisonFunction(propertyName) {
    return function (obj1, obj2) {
        let val1 = obj1[propertyName];
        let val2 = obj2[propertyName];

        if (val1 < val2) {
            return -1;
        } else if (val1 > val2) {
            return 1;
        } else {
            return 0;
        }
    };
}

const data = [{ name: "maoqifan", age: 28 }, { name: "Nicholas", age: 29 }];
data.sort(createComparisonFunction("name"));
console.log(data[0].name);
data.sort(createComparisonFunction("age"));
console.log(data[0].name);