package io.volkan.dummy;

import io.volkan.FontPropertiesPanel;

public class Test {
    public Test() {
        FontPropertiesPanel fp = new FontPropertiesPanel();

        // protected access; will error out:
        // fp.buildLayout();
    }
}
