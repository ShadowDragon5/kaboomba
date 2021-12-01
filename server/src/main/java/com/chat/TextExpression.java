package com.chat;

public class TextExpression implements Expression {
    private String text;

    public TextExpression(String text) {
        this.text = text;
    }

    @Override
    public String interpret(ChatContext context) {
        context.setText(text);
        return context.execute();
    }
}
