package main;

import javafx.scene.Parent;

public class FXMLResource {

    private Parent parent;
    private Object controller;

    public FXMLResource(Parent parent, Object controller) {
        this.parent = parent;
        this.controller = controller;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
