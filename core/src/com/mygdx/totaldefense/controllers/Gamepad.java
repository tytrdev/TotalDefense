package com.mygdx.totaldefense.controllers;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dubforce on 10/18/15.
 */
public class Gamepad implements ControllerListener {
    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        if(axisCode == XBoxOneController.AXIS_LEFT_X) {
            Controllers.getLeftAxis().set(
                    controller.getAxis(axisCode),
                    Controllers.getLeftAxis().y
            );
        }
        else if(axisCode == XBoxOneController.AXIS_LEFT_Y) {
            Controllers.getLeftAxis().set(
                    Controllers.getLeftAxis().x,
                    controller.getAxis(axisCode) * -1 // flip incoming y values
            );
        }
        else if(axisCode == XBoxOneController.AXIS_RIGHT_X) {
            Controllers.getRightAxis().set(
                    controller.getAxis(axisCode),
                    Controllers.getRightAxis().y
            );
        }
        else if(axisCode == XBoxOneController.AXIS_RIGHT_Y) {
            Controllers.getRightAxis().set(
                    Controllers.getRightAxis().x,
                    controller.getAxis(axisCode) * -1 // flip incoming y values
            );
        }

        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
