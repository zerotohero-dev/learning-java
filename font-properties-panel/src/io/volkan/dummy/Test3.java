package io.volkan.dummy;

import io.volkan.FontPropertiesPanel;

public class Test3 extends FontPropertiesPanel {
    public Test3() {
        this.buildLayout(); // will work. which means you can override a protected method in a subclass
                            // only subclasses can override protected methods.

        FontPropertiesPanel fp = new FontPropertiesPanel();

        // won’t work; has protected access.
        // fp.buildLayout();
    }
}
