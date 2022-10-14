package com.weather.pogodynka.controller;

import com.weather.pogodynka.view.ViewFactory;

public abstract class BaseController {
    protected ViewFactory viewFactory;
    private final String fxmlName;

    public BaseController(ViewFactory viewFactory, String fxmlName) {
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFxmlName() {
        return fxmlName;
    }
}
