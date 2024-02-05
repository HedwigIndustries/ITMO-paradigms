"use strict"
const cnst = value => () => value
const variable = name => (x, y, z) => {
    if (name === "x") {
        return x
    } else if (name === "y") {
        return y
    } else if (name === "z") {
        return z
    }
}
const o = f => (...args) => (...vars) => f(...args.map(x => x(...vars)))
const add = o((first, second) => first + second);
const subtract = o((first, second) => first - second);
const multiply = o((first, second) => first * second);
const divide = o((first, second) => first / second);
const negate = o((first) => -first);
const sin = o((first) => Math.sin(first));
const cos = o((first) => Math.cos(first));
const sinh = o((first) => Math.sinh(first));
const cosh = o((first) => Math.cosh(first));
const one = cnst(1);
const two = cnst(2);
