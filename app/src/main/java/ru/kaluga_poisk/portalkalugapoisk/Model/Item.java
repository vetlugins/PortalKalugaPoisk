package ru.kaluga_poisk.portalkalugapoisk.Model;

import android.widget.TextView;

public class Item {

    private TextView text, subText;
    private Boolean isExpandable;

    public Item(TextView text, TextView subText, Boolean isExpandable) {
        this.text = text;
        this.subText = subText;
        this.isExpandable = isExpandable;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }

    public TextView getSubText() {
        return subText;
    }

    public void setSubText(TextView subText) {
        this.subText = subText;
    }

    public Boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(Boolean expandable) {
        isExpandable = expandable;
    }
}
