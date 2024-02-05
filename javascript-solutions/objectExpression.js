"use strict"
let OPERATIONS = new Map();
const VARIABLES = {
    "x": "x", "y": "y", "z": "z"
}

function ExpressionConstructor(createdExpression, evaluate, toString, prefix) {
    createdExpression.prototype.evaluate = evaluate;
    createdExpression.prototype.toString = toString;
    createdExpression.prototype.prefix = prefix;
    return createdExpression;
}

const Const = ExpressionConstructor(
    function (value) {
        this.value = value;
    },
    function (x, y, z) {
        return this.value;
    },
    function () {
        return String(this.value);
    }, function () {
        return String(this.value);
    }
)

const Variable = ExpressionConstructor(
    function (name) {
        this.name = name;
    },
    function (x, y, z) {
        if (this.name === "x") {
            return x;
        } else if (this.name === "y") {
            return y;
        } else if (this.name === "z") {
            return z;
        }
    },
    function () {
        return this.name;
    },
    function () {
        return this.name;
    }
)

const SomeOperation = ExpressionConstructor(
    function (...args) {
        this.args = args;
    },
    function (...args) {
        return this.func(...this.args.map(value => value.evaluate(...args)));
    },
    function () {
        return this.args.map(value => (value.toString())).join(" ") + " " + this.op;
    },
    function () {
        return "(" + this.op + " " + this.args.map(value => (value.prefix())).join(" ") + ")"
    }
)

function OperationConstructor(op, func) {
    function Operation(...args) {
        SomeOperation.call(this, ...args)
    }

    Operation.prototype = Object.create(SomeOperation.prototype);
    Operation.prototype.constructor = OperationConstructor;
    Operation.prototype.op = op;
    Operation.prototype.func = func;
    Operation.operands = func.length;
    OPERATIONS.set(op, Operation);
    return Operation;
}

const Add = OperationConstructor("+", (first, second) => first + second);
const Subtract = OperationConstructor("-", (first, second) => first - second);
const Multiply = OperationConstructor("*", (first, second) => first * second);
const Divide = OperationConstructor("/", (first, second) => first / second);
const Negate = OperationConstructor("negate", value => -value);
const Ln = OperationConstructor("ln", Math.log);
const Exp = OperationConstructor("exp", Math.exp);

const ArcTan = OperationConstructor("atan", Math.atan);
const ArcTan2 = OperationConstructor("atan2", Math.atan2);
const Sum = OperationConstructor("sum", (...args) => args.length === 0 ? 0 : (args).reduce((sum, value) => (sum + value)));
const Avg = OperationConstructor("avg", (...args) => args.length === 0 ? 0 : ((args).reduce((sum, value) => (sum + value)) / args.length));

Sum.operands = "unchecked"
Avg.operands = "unchecked"

const parse = expression => {
    let elements = expression.split(" ");
    let stack = [];
    for (let i = 0; i < elements.length; i++) {
        let elem = elements[i]
        if (elem.length === 0) {
            continue
        }
        if (OPERATIONS.has(elem)) {
            let operation = OPERATIONS.get(elem);
            let size = operation.operands;
            stack.push(new operation(...stack.splice(stack.length - size, size)))
        } else if (elem in VARIABLES) {
            stack.push(new Variable(elem))
        } else
            stack.push(new Const(parseInt(elem)));
    }
    return stack[0]
}


class BaseParser {
    constructor(source) {
        this.sourse = source;
        this.pos = 0;
    }

    take() {
        let result;
        if (this.pos < this.sourse.length) {
            result = this.sourse[this.pos];
        } else
            result = undefined;
        this.pos = this.pos + 1;
        return result;
    }

    takeIfExpected(expected) {
        if (this.currentCheck(expected)) {
            this.take();
            return true;
        }
        return false;
    }

    previousNotWhitespace() {
        let result = "";
        let i = 0;
        while (this.sourse[this.pos - i] !== " " && i < this.pos - 1) {
            i++;
        }
        i++;
        while (this.sourse[this.pos - i] !== " " && i <= this.pos) {
            result += this.sourse[this.pos - i]
            i++;
        }
        return result;
    }

    currentCheck(expected) {
        return this.sourse[this.pos] === expected;
    }

    expectChar(expected) {
        if (!this.takeIfExpected(expected)) {
            if (expected === ")") {
                throw new ParseException("Missing close bracket.");
            }
            throw new ParseException("Expected: " + "\"" + expected + "\"" + " but was: " + "\"" + this.take() + "\".");
        }
    }

    eof() {
        return this.takeIfExpected(undefined);
    }

    skipWhitespace() {
        while (" " === this.sourse[this.pos]) {
            this.pos++;
        }
    }

    between(from, to) {
        return from <= this.sourse[this.pos] && this.sourse[this.pos] <= to;
    }
}

class ExpressionParser {
    constructor(expression) {
        this.expression = new BaseParser(expression);
    }

    parseExpression() {
        let result;
        this.expression.skipWhitespace();
        result = this.parseArgument();
        this.expression.skipWhitespace();
        if (!this.expression.eof()) {
            throw new ParseException("Invalid symbol: " + "\"" + this.expression.take() +
                "\"" + " after " + "\"" + this.expression.previousNotWhitespace() + "\".");
        }
        return result;
    }

    parseOperation() {
        let operation = "";
        this.expression.skipWhitespace();
        while (!this.expression.currentCheck(" ") && !this.expression.eof() && !this.expression.currentCheck("(") && !this.expression.currentCheck(")")) {
            operation += this.expression.take();
        }
        if (OPERATIONS.has(operation)) {
            let currentOperation = OPERATIONS.get(operation)
            let operationArgs = this.parseOperationArguments();
            if (currentOperation.operands !== "unchecked") {
                this.checkOperationArgs(operationArgs, currentOperation.operands);
            }
            return new currentOperation(...operationArgs);
        } else {
            if (this.expression.takeIfExpected(')')) {
                throw new ParseException("Missing operation.");
            } else
                throw new ParseException("Expected correct operation after open bracket, but was:" + " \"" + operation + "\".");
        }
    }

    parseOperationArguments() {
        let operationArgs = []
        this.expression.skipWhitespace();
        while (!this.expression.eof() && !this.expression.currentCheck(')')) {
            operationArgs.push(this.parseArgument());
            this.expression.skipWhitespace();
        }
        return operationArgs;
    }

    checkOperationArgs(operationArgs, correctArgs) {
        if (operationArgs.length !== correctArgs) {
            throw new ParseException("Incorrect operation arguments, operation should be with " + String(correctArgs) +
                " operands, but have " + String(operationArgs.length) + ".")
        }
    }

    parseArgument() {
        this.expression.skipWhitespace();
        if (this.expression.takeIfExpected('(')) {
            let result = this.parseOperation();
            this.expression.skipWhitespace();
            this.expression.expectChar(')');
            return result;
        }
        if (this.expression.takeIfExpected('-')) {
            if (this.expression.between('0', '9')) {
                return this.parseConst('-');
            } else {
                throw new ParseException("Invalid number, expected the negative number, but was: \"-" + this.expression.take() + "\".");
            }
        } else if (this.expression.between('0', '9')) {
            return this.parseConst('+');
        } else if (this.expression.currentCheck('x') || this.expression.currentCheck('y') || this.expression.currentCheck('z')) {
            let current = this.expression.take()
            if (this.expression.between("a", "z")) {
                throw new ParseException("Expected correct variable, but was: " + "\"" + current + this.expression.take() + "\".")
            }
            return new Variable(current)
        } else {
            if (this.expression.eof()) {
                throw new ParseException("Empty input.")
            }
            if (this.expression.between("a", "z")) {
                throw new ParseException("Unknown variable: " + "\"" + this.expression.take() + "\".")
            }
            throw new ParseException("Illegal argument.")
        }
    }

    parseConst(op) {
        if (this.expression.takeIfExpected('0')) {
            return new Const(0);
        } else {
            let result = "";
            if (op === '-') {
                result += op;
            }
            result = this.takeDigits(result);
            return new Const(Number(result));
        }
    }

    takeDigits(result) {
        while (this.expression.between('0', '9')) {
            result += this.expression.take();
        }
        return result;
    }
}

function ParseException(message) {
    Error.call(this, message);
    this.message = message;
}

function parsePrefix(string) {
    let parser = new ExpressionParser(string);
    return parser.parseExpression();
}
