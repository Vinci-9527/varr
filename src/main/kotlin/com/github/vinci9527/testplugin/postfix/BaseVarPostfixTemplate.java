package com.github.vinci9527.testplugin.postfix;

import com.intellij.codeInsight.template.postfix.templates.PostfixTemplateWithExpressionSelector;
import com.intellij.codeInsight.template.postfix.util.JavaPostfixTemplatesUtils;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.refactoring.introduceVariable.IntroduceVariableHandler;
import org.jetbrains.annotations.NotNull;

public class BaseVarPostfixTemplate extends PostfixTemplateWithExpressionSelector {

  private final String selectedTypeFQN;

  BaseVarPostfixTemplate(String name, String example, String selectedTypeFQN) {
    super(null, name, example,
      JavaPostfixTemplatesUtils.selectorAllExpressionsWithCurrentOffset(JavaPostfixTemplatesUtils.IS_NON_VOID),
      null);
    this.selectedTypeFQN = selectedTypeFQN;
  }

  @Override
  protected void expandForChooseExpression(@NotNull PsiElement expression, @NotNull Editor editor) {
    IntroduceVariableHandler handler = new IntroduceLombokVariableHandler(selectedTypeFQN);
    handler.invoke(expression.getProject(), editor, (PsiExpression) expression);
  }

}
