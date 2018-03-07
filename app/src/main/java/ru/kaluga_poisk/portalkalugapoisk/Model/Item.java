package ru.kaluga_poisk.portalkalugapoisk.Model;

import android.widget.TextView;

public class Item {

    private String text, subText;
    private Boolean isExpandable;

    public Item(String text, String subText, Boolean isExpandable) {
        this.text = text;
        this.subText = subText;
        this.isExpandable = isExpandable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public Boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(Boolean expandable) {
        isExpandable = expandable;
    }
}
