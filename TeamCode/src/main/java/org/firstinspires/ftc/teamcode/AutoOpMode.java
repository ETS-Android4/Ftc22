package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.Range;
import java.lang.Math;


//written for blue in position as shown in manual

@Autonomous(name="Auto OpMode", group="Linear Opmode")
public class AutoOpMode extends LinearOpMode {

    private MecanumWheels drive;
    private Arm arm;
    private Carousel carousel;

    private DcMotor leftBackMotor;
    private DcMotor leftFrontMotor;
    private DcMotor rightBackMotor;
    private DcMotor rightFrontMotor;

    private Servo handServo;
    private Servo armServo;

    private Servo launchServo;
    private DcMotor launchMotor;

    private CRServo loaderMotor;

    private boolean loaderOn;
    private boolean launcherOn;

    private ColorSensor sensorColor;

    // Declare OpMode members.
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");

        handServo = hardwareMap.get(Servo.class, "handServo");
        armServo = hardwareMap.get(Servo.class, "armServo");

        launchMotor = hardwareMap.get(DcMotor.class, "launchMotor");

        loaderMotor = hardwareMap.get(CRServo.class, "loaderMotor");

        sensorColor = hardwareMap.colorSensor.get("sensorColor");

        drive = new MecanumWheels(leftBackMotor, leftFrontMotor, rightBackMotor, rightFrontMotor);
        arm = new Arm(launchServo, loaderMotor);
        carousel = new Carousel(launchMotor);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        timer.reset();

        //String ringPos = "A";

        driveUntilLine(false, true);//color - RED = true, direction - Forward = true

        driveUntilLine(false, false);


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + timer.toString());
            telemetry.update();
        }

    }

    // forward is true, back is false
    public void driveUntilLine(boolean isRed, boolean direction) {
        sensorColor.enableLed(true);
        if (isRed) {
            while (sensorColor.red() < 2){//test threshold
                if (direction) {
                    drive.drive(Math.PI/2, 1, 0);
                }
                else {
                    drive.drive(Math.PI/2, -1, 0);
                }
            }
        }
        else {
            while (sensorColor.blue() < 2) {//test threshold
                if (direction) {
                    drive.drive(Math.PI/2, 1, 0);
                }
                else {
                    drive.drive(Math.PI/2, -1, 0);
                }
            }
        }
        sensorColor.enableLed(false);
    }
}
