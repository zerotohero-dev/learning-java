package io.volkan.entities;

import org.springframework.stereotype.Component;

@Component // by default the name of the bean will be the
           // name of the class with a lowercase first letter.
           // (you can override that)
           // This bean will be scanned during a component scan.
public class Cubs implements Team {

    @Override
    public String getName() {
        return "Chicago Cubs";
    }
}
