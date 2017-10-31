package io.volkan;

public class Test3 extends FontPropertiesPanel {
    public Test3() {
        FontPropertiesPanel fp = new FontPropertiesPanel();

        fp.buildLayout(); // this will work; in the same package; buildLayout is a protected method.

        this.buildLayout(); // works because we can shadow it because same package.
    }

// this is also doable:
//    @Override
//    protected void buildLayout() {
//        super.buildLayout();
//    }
}
