package com.example.calculatorvx.numbers.simplify;

import java.util.Map;

/**
 * This class features a cosinus, using type expression.
 *
 * @author Raz Shenkman
 * @version 1.0
 * @since 2017-05- 16
 */
public class Triangle extends UnaryExpression implements Expression {
    public static boolean useRadian = true;

    private int mode = 1;

    public static final int SIN = 1, COS = 2, TAN = 3, ARC_SIN = 4, ARC_COS = 5, ARC_TAN = 6;
    /**
     * A cosinus holds an expression.
     */
    private Expression expression1;
    /**
     * isCommutative is used for the swap of expressions, cos only holds 1 expression so it's false.
     */
    private boolean isCommutative = false;

    /**
     * This constructor of cos gets an expression.
     *
     * @param exp an expression.
     */
    public Triangle(Expression exp, int mode) {
        this.expression1 = exp;
        this.mode = mode;
    }

    /**
     * This constructor of cos gets an variable.
     *
     * @param var an inputted string.
     */
    public Triangle(String var, int mode) {
        this.expression1 = new Var(var);
        this.mode = mode;
    }

    /**
     * This constructor of cos gets an string.
     *
     * @param val an inputted number.
     */
    public Triangle(double val, int mode) {
        this.expression1 = new Num(val);
        this.mode = mode;
    }

    /**
     * This method evaluates the cos using a dictionary.
     *
     * @param assignment a dictionary.
     * @return a double value of the calculation.
     * @throws Exception if failed- not all of the variables appear in the dictionary.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double angle;
        if (useRadian) {
            angle = this.expression1.evaluate(assignment);
        } else {
            angle = Math.toRadians(this.expression1.evaluate(assignment));
        }
        switch (this.mode) {
            default:
            case SIN:
                return Math.sin(angle);
            case COS:
                return Math.cos(angle);
            case TAN:
                return Math.tan(angle);
            case ARC_SIN:
                return Math.asin(angle);
            case ARC_COS:
                return Math.acos(angle);
            case ARC_TAN:
                return Math.atan(angle);
        }
    }

    /**
     * Getter of the expression.
     *
     * @return the expression cosinus holding.
     */
    @Override
    public Expression getExpression1() {
        return this.expression1;
    }

    /**
     * Returns a string of the expression.
     *
     * @return a string of this expression.
     */
    public String toString() {
        switch (this.mode) {
            default:
            case SIN:
                return "sin" + "(" + expression1.toString() + ")";
            case COS:
                return "cos" + "(" + expression1.toString() + ")";
            case TAN:
                return "tan" + "(" + expression1.toString() + ")";
            case ARC_SIN:
                return "arcsin" + "(" + expression1.toString() + ")";
            case ARC_COS:
                return "arccos" + "(" + expression1.toString() + ")";
            case ARC_TAN:

                return "arctan" + "(" + expression1.toString() + ")";
        }
    }

    /**
     * This method assigns the specific var in this expression with a new expression.
     *
     * @param var        the user inputted variable to replace.
     * @param expression the new expression to replace with the var.
     * @return new expression with the new expression replace.
     */
    public Expression assign(String var, Expression expression) {
        return new Triangle(this.expression1.assign(var, expression), this.mode);
    }

    /**
     * This method differentiate this expression by a specific variable.
     *
     * @param var inputted var we differentiate by.
     * @return the differentiated expression.
     */
    public Expression differentiate(String var) {
        return null;
        //not implemented yet TODO

        //return new Neg(new Mult(this.expression1.differentiate(var), new Sin((this.expression1))));
    }

    /**
     * This method simplifies the expression.
     *
     * @return this expression simplified.
     */
    @Override
    public Expression specificSimplify() {
        Double temp = null;
        try {
            temp = this.expression1.evaluate();
        } catch (Exception ex) {
            // Simple declaring x (so catch block won't be empty).
            int x = 1;
        }
        // If temp has a value of number- return new Num with the calculation value.
        if (temp != null) {
            if (!useRadian) {
                temp = Math.toRadians(temp);
            }
            switch (this.mode) {
                default:
                case SIN:
                    return new Num(Math.sin(temp));
                case COS:
                    return new Num(Math.cos(temp));
                case TAN:
                    return new Num(Math.tan(temp));
                case ARC_SIN:
                    return new Num(Math.asin(temp));
                case ARC_COS:
                    return new Num(Math.acos(temp));
                case ARC_TAN:
                    return new Num(Math.atan(temp));
            }
        } else {
            return new Triangle(this.expression1.simplify(), this.mode);
        }

    }

    /**
     * Getter of isCommutative.
     *
     * @return true/false if isCommutative.
     */
    public boolean getIsCommutative() {
        return this.isCommutative;
    }

    /**
     * This method returns this expression.
     *
     * @return this expression.
     */
    @Override
    public Expression getExp() {
        return this;
    }
}
