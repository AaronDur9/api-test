package com.test.api.Model;

public class CarFeatures {

    private String model;
    private String engine;
    private String infoSystem;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getInfoSystem() {
        return infoSystem;
    }

    public void setInfoSystem(String infoSystem) {
        this.infoSystem = infoSystem;
    }

    protected boolean checkFeatures(String model, String engine, String infoSystem) {
        return (this.model.equals(model) && this.engine.equals(engine)  && this.infoSystem.equals(infoSystem));
    }



}
