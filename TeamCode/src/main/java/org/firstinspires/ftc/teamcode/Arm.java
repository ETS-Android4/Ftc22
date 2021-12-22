package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Arm {
    private Servo servo;
    private ElapsedTime timer;
    private CRServo motor;

    public Arm(Servo servo, CRServo motor ) {
        this.servo = servo;
        this.motor = motor;
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void up(boolean isPressed) {
        if (isPressed) {
            motor.setPower(1.0);
        } else {
            motor.setPower(0.0);
        }
    }
    public void pause() { motor.setPower(0.0); }
    public void down(boolean isPressed) {
        if (isPressed) {
            motor.setPower(-1.0);
        } else {
            motor.setPower(0.0);
        }
    }

    public void open(boolean isPressed) {
        if (isPressed) {
            servo.setPosition(1.0);
        } else {
            servo.setPosition(0.0);
        }
    }
    //public void open() { servo.setPosition(1.0); }
    //public void close() { servo.setPosition(0.0); }

}
