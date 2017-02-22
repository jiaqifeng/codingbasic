package com.jack;

/**
 * Created by jack on 17-2-22.
 */
public class RegexRule implements Rule {
    String type;
    String regex;

    public RegexRule(String regex) {
        this.regex=regex;
    }
    public String getType() {
        return type;
    }

    public boolean legal() {
        if (regex==null)
            return false;
        return true;
    }

    public boolean match(String data) {
        if (data!=null && data.contains(regex))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "RegexRule: regex="+regex;
    }
}
