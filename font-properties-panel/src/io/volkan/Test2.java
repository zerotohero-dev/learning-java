package io.volkan;

public class Test2 {
    public Test2() {
        FontPropertiesPanel fp = new FontPropertiesPanel();

        fp.buildLayout(); // this will work; in the same package; buildLayout is a protected method.
    }
}
