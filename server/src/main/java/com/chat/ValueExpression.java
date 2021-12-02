package com.chat;

public class ValueExpression implements Expression {
    private String value;

    public ValueExpression(String value) {
        this.value = value;
    }

    @Override
    public String interpret(ChatContext context) {
        context.setValue(value);
        return  context.execute();
    }
}
