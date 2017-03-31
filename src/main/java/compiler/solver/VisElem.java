package compiler.solver;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.expression.discrete.arithmetic.ArExpression;
import org.chocosolver.solver.variables.IntVar;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code VisElem} class respresents a visualization element. A visualization element consists of name-value pairs,
 * with the values being {@link String} constants, and name-variable pairs, with the variables being {@link IntVar}
 * variables. The values of instantiated variables can also be retreiverd as {@link String} constants.
 */
// TODO: Improve exception handling (or return bool?)
public class VisElem {

    private static final int MIN_INT_BOUND = 0;
    private static final int MAX_INT_BOUND = 1000; // TODO: Find a suitable maximum

    /** The model associated with this element. */
    private final Model model;

    /** The values of this element. */
    private final Map<String, String> values;

    /** The variables of this element. */
    private final Map<String, IntVar> vars;

    /**
     * Constructs a new {@code VisElem} with the given model.
     *
     * @param model The given model.
     */
    public VisElem(Model model) {
        this.model = model;
        this.values = new HashMap<>();
        this.vars = new HashMap<>();
    }

    /**
     * If the value is parsable by {@link Integer#parseInt(String)} it will be treated as the value of an {@link IntVar}
     * variable and set the name-variable pair. Otherwise the value will be treated as a {@link String} constant and set
     * the name-value pair.
     *
     * @param name  The given name.
     * @param value The given value.
     * @throws ElementException If the name can't be assigned to the given value.
     */
    public void set(String name, String value) {
        try {
            int constant = Integer.parseInt(value);
            setVar(name, constant);
        } catch (NumberFormatException e) {
            setValue(name, value);
        }
    }

    /**
     * Set the given name-value pair.
     *
     * @param name  The given name.
     * @param value The given {@link String} constant.
     * @return The value.
     * @throws ElementException If the name is already assigned to a variable or to a different value.
     */
    public String setValue(String name, String value) {
        if (vars.containsKey(name)) {
            throw new ElementException("%s is already defined as a variable", name);
        } else if (values.containsKey(name)) {
            if (!Objects.equals(values.get(name), value)) {
                throw new ElementException("%s already has a different value", name);
            }
            return value;
        } else {
            values.put(name, value);
            return value;
        }
    }

    /**
     * Sets the given name-variable pair. The value is treated as the constant of an {@link IntVar} variable.
     *
     * @param name     The given name.
     * @param constant The given value.
     * @return The variable.
     * @throws ElementException If the name is already assigned to a value.
     */
    public IntVar setVar(String name, int constant) {
        if (values.containsKey(name)) {
            throw new ElementException("%s is already defined as a value", name);
        } else if (vars.containsKey(name)) {
            IntVar var = vars.get(name);
            var.eq(constant).post();
            return var;
        } else {
            IntVar var = model.intVar(constant);
            vars.put(name, var);
            return var;
        }
    }

    /**
     * Set the given name-variable pair. The values are treated as the lower and upper bound of an {@link IntVar}
     * varaible.
     *
     * @param name The given name.
     * @param lb   The given lower bound value.
     * @param ub   The given upper bound value.
     * @return The variable.
     * @throws ElementException If the name is already assigned to a value.
     */
    public IntVar setVar(String name, int lb, int ub) {
        if (values.containsKey(name)) {
            throw new ElementException("%s is already defined as a value", name);
        } else if (vars.containsKey(name)) {
            IntVar var = vars.get(name);
            var.ge(lb).post();
            var.le(ub).post();
            return var;
        } else {
            IntVar var = model.intVar(lb, ub);
            vars.put(name, var);
            return var;
        }
    }

    /**
     * Sets the given name-variable pair. The variable being an {@link IntVar}.
     *
     * @param name   The given name.
     * @param newVar The given {@link IntVar} variable.
     * @return The variable.
     * @throws ElementException If the name is already assigned to a value.
     */
    public IntVar setVar(String name, IntVar newVar) {
        if (values.containsKey(name)) {
            throw new ElementException("%s is already defined as a value", name);
        } else if (vars.containsKey(name)) {
            IntVar var = vars.get(name);
            if (!Objects.equals(var, newVar)) {
                var.eq(newVar).post();
            }
            return var;
        } else {
            vars.put(name, newVar);
            return newVar;
        }
    }

    /**
     * Converts the value of an {@link IntVar} variable to a {@link String} value.
     *
     * @param var The variable to convert.
     * @return The value, or {@code null} if the {@link IntVar} isn't instantiated.
     */
    private static String varToValue(IntVar var) {
        if (var.isInstantiated()) {
            return Integer.toString(var.getValue());
        }
        return null;
    }

    /**
     * Returns whether there is a value belonging to the given name. Instatiated variables also count as values.
     *
     * @param name The given name.
     * @return {@code true} if there is a value, {@code false} otherwise.
     */
    public boolean hasValue(String name) {
        if (values.containsKey(name)) {
            return true;
        } else if (vars.containsKey(name)) {
            return vars.get(name).isInstantiated();
        } else {
            return false;
        }
    }

    /**
     * Get the {@link String} value belonging to the given name. The value of a variable is determined by {@link
     * VisElem#varToValue(IntVar)}.
     *
     * @param name The given name.
     * @return The value, or {@code null} if it doesn't exists.
     */
    public String getValue(String name) {
        if (values.containsKey(name)) {
            return values.get(name);
        } else if (vars.containsKey(name)) {
            return varToValue(vars.get(name));
        } else {
            return null;
        }
    }

    /**
     * Returns whether there is a variable belonging to the given name.
     *
     * @param name The given name.
     * @return {@code true} if there is a variable, {@code false} otherwise.
     */
    public boolean hasVar(String name) {
        return vars.containsKey(name);
    }

    /**
     * Get the {@link IntVar} variable belonging to the given name.
     *
     * @param name The given name.
     * @return The {@link IntVar} variable.
     */
    public IntVar getVar(String name) {
        if (vars.containsKey(name)) {
            return vars.get(name);
        } else {
            return setVar(name, MIN_INT_BOUND, MAX_INT_BOUND);
        }
    }

    /**
     * Get a copy of the name-value pairs of this element. The value of a variable is determined by {@link
     * VisElem#varToValue(IntVar)}.
     *
     * @return A map containting the name-value pairs.
     */
    public Map<String, String> getValues() {
        Map<String, String> result = new HashMap<>(values);
        vars.forEach((name, var) -> {
            String value = varToValue(var);
            if (value != null) {
                result.put(name, value);
            }
        });
        return result;
    }

    /**
     * Get a copy of the name-variable pairs of this element.
     *
     * @return A map containing the name-variable pairs.
     */
    public Map<String, IntVar> getVars() {
        return new HashMap<>(vars);
    }

    /**
     * Initializes the default variables. The defaults are:
     * <table summary="Defaults" border="1">
     * <tr><td><b>Name</b></td> <td><b>Description</b></td> <td><b>Value</b></td></tr>
     * <tr><td>z</td>           <td>z position</td>         <td>[MIN, MAX]</td></tr>
     * <tr><td>x1</td>          <td>x start position</td>   <td>[0, MAX]</td></tr>
     * <tr><td>y1</td>          <td>y start position</td>   <td>[0, MAX]</td></tr>
     * <tr><td>width</td>       <td>width</td>              <td>[0, MAX]</td></tr>
     * <tr><td>height</td>      <td>height</td>             <td>[0, MAX]</td></tr>
     * <tr><td>x2</td>          <td>x end position</td>     <td>x1 + width</td></tr>
     * <tr><td>y2</td>          <td>y end position</td>     <td>y1 + height</td></tr>
     * <tr><td>radiusX</td>     <td>x radius</td>           <td>width / 2</td></tr>
     * <tr><td>radiusY</td>     <td>y radius</td>           <td>height / 2</td></tr>
     * <tr><td>centerX</td>     <td>x center position</td>  <td>x1 + radiusX</td></tr>
     * <tr><td>centerY</td>     <td>y center position</td>  <td>y1 + radiusY</td></tr>
     * </table>
     */
    public void setDefaults() {
        getVar("z");

        setDefaultDimensions("width", "radiusX");
        setDefaultDimensions("height", "radiusY");

        setDefaultPositions("x1", "centerX", "x2", "width", "radiusX");
        setDefaultPositions("y1", "centerY", "y2", "height", "radiusY");
    }

    @SuppressWarnings("ConstantConditions")
    private void setDefaultDimensions(String diameter, String radius) {
        boolean hasDiameter = hasVar(diameter);
        boolean hasRadius = hasVar(radius);
        if (!hasDiameter && !hasRadius) {
            setVar(radius, getVar(diameter).div(2).intVar());
        } else if (hasDiameter) {
            setConstraint(radius, hasRadius, getVar(diameter).div(2));
        } else if (hasRadius) {
            setConstraint(diameter, hasDiameter, getVar(radius).mul(2));
        }
    }

    // Assumes dimensions are set
    @SuppressWarnings("ConstantConditions")
    private void setDefaultPositions(String start, String center, String end, String diameter, String radius) {
        boolean hasStart = hasVar(start);
        boolean hasCenter = hasVar(center);
        boolean hasEnd = hasVar(end);
        if (!hasStart && !hasCenter && !hasEnd) {
            setVar(center, getVar(start).add(getVar(radius)).intVar());
            setVar(end, getVar(start).add(getVar(diameter)).intVar());
        } else if (hasStart) {
            setConstraint(center, hasCenter, getVar(start).add(getVar(radius)));
            setConstraint(end, hasEnd, getVar(start).add(getVar(diameter)));
        } else if (hasCenter) {
            setConstraint(start, hasStart, getVar(center).sub(getVar(radius)));
            setConstraint(end, hasEnd, getVar(center).add(getVar(radius)));
        } else if (hasEnd) {
            setConstraint(start, hasStart, getVar(end).sub(getVar(diameter)));
            setConstraint(center, hasCenter, getVar(end).sub(getVar(radius)));
        }
//        getVar(start).gt(getVar(end)).post(); TODO: Not guaranteed for lines
    }

    private void setConstraint(String name, boolean exists, ArExpression constraint) {
        if (exists) {
            constraint.eq(getVar(name)).post();
        } else {
            setVar(name, constraint.intVar());
        }
    }
}