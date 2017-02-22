package com.jack;

/**
 * Created by jack on 17-2-22.
 */
public class NumberRule implements Rule {
    String type;
    Integer value;

    public NumberRule(Integer value) {
        this.type="number";
        this.value=value;
    }
    public String getType() {
        return type;
    }

    public boolean legal() {
        if (value==null)
            return false;
        return true;
    }

    public boolean match(String data) {
        Integer i;
        try {
            i=Integer.parseInt(data);
        } catch (Exception e) {
            return false;
        }
        if (i==value)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "NumberRule: value="+value;
    }

}
