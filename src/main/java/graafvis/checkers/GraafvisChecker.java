package graafvis.checkers;

import graafvis.grammar.GraafvisParser;

/**
 *
 */
public class GraafvisChecker {

    public CheckerResult check(GraafvisParser.ProgramContext program) {

        CheckerResult result = new CheckerResult();

        final LabelGenerationCheck labelGenerationCheck = new LabelGenerationCheck();
        final ConsequenceBlacklist consequenceBlacklist = new ConsequenceBlacklist();
        final VariableUsageCheck variableUsageCheck = new VariableUsageCheck();
        final WildcardUsageCheck wildcardUsageCheck = new WildcardUsageCheck();

        labelGenerationCheck.visitProgram(program);
        consequenceBlacklist.visitProgram(program);
        variableUsageCheck.visitProgram(program);
        wildcardUsageCheck.visitProgram(program);

        result.addErrors(labelGenerationCheck.getErrors());
        result.addErrors(consequenceBlacklist.getErrors());
        result.addErrors(variableUsageCheck.getErrors());
        result.addErrors(wildcardUsageCheck.getErrors());

        return result;
    }


}