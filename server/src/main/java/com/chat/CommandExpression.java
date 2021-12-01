package com.chat;

public class CommandExpression implements Expression {
    private AttributeExpression attributeExpression;

    public CommandExpression(AttributeExpression attributeExpression) {
        this.attributeExpression = attributeExpression;
    }

    @Override
    public String interpret(ChatContext context) {
        context.setCommand(true);
        return attributeExpression.interpret(context);
    }
}
