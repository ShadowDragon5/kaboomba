package com.chat;

public class AttributeExpression implements Expression {
    private ValueExpression valueExpression;
    private String attribute;

    public AttributeExpression(String attribute, ValueExpression valueExpression) {
        this.valueExpression = valueExpression;
        this.attribute = attribute;
    }

    @Override
    public String interpret(ChatContext context) {
        context.setAttribute(attribute);
        return valueExpression.interpret(context);
    }
}
