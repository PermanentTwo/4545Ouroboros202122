package org.firstinspires.ftc.teamcode.HardwareClasses;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class VisionCamera {
    LinearOpMode opMode;
    private VuforiaLocalizer vuforia;

    public static final String vuKey =
            "AdzMYbL/////AAABmflzIV+frU0RltL/ML+2uAZXgJiI" +
                    "Werfe92N/AeH7QsWCOQqyKa2G+tUDcgvg8uE8QjHeBZPcpf5hAwlC5qCfvg76eBoaa2b" +
                    "MMZ73hmTiHmr9fj3XmF4LWWZtDC6pWTFrzRAUguhlvgnck6Y4jjM16Px5TqgWYuWnpcxNM" +
                    "HMyOXdnHLlyysyE64PVzoN7hgMXgbi2K8+pmTXvpV2OeLCag8fAj1Tgdm/kKGr0TX86aQsC2" +
                    "RVjToZXr9QyAeyODi4l1KEFmGwxEoteNU8yqNbBGkPXGh/+IIm6/s/KxCJegg8qhxZDgO8110F" +
                    "RzwA5a6EltfxAMmtO0G8BB9SSkApxkcSzpyI0k2LxWof2YZG6x4H";

    public void initVision(LinearOpMode opMode) {
        this.opMode = opMode;

        // variable allows image to show up on robot controller phone
        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id",
                        opMode.hardwareMap.appContext.getPackageName());

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // original vuforia license key
        parameters.vuforiaLicenseKey = vuKey;
        // hardware mapping of webcam device
        parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

        // start vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // set RGB format to 565
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);

        // allowing the frame to only be 3 images at a time
        vuforia.setFrameQueueCapacity(3);
        opMode.telemetry.addLine("Vision init");
    }

    public VisionCamera(LinearOpMode opMode) {
        this.opMode = opMode;

        // variable allows image to show up on robot controller phone
        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id",
                        opMode.hardwareMap.appContext.getPackageName());

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // original vuforia license key
        parameters.vuforiaLicenseKey = vuKey;
        // hardware mapping of webcam device
        parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

        // start vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // set RGB format to 565
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);

        // allowing the frame to only be 4 images at a time
        vuforia.setFrameQueueCapacity(1);
        opMode.telemetry.addLine("Vision init");
        opMode.telemetry.update();
    }

    public Bitmap getBitmap() throws InterruptedException {
        // method to actually capture frame
        VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
        Image rgb = frame.getImage(1);

        long numImages = frame.getNumImages();

        // go through all images taken in frame and find ones that match correct format
        for (int i = 0; i < numImages; i++) {
            int fmt = frame.getImage(i).getFormat();

            if (fmt == PIXEL_FORMAT.RGB565) {
                rgb = frame.getImage(i);
                break;

            } else {
                opMode.telemetry.addLine("Didn't find correct rgb format");
                opMode.telemetry.update();

            }

        }

        // create bitmap
        Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(rgb.getPixels());

        frame.close();

        opMode.telemetry.addLine("Got Bitmap");
        opMode.telemetry.addData("width", rgb.getWidth());
        opMode.telemetry.addData("height", rgb.getHeight());
        opMode.telemetry.update();

        opMode.sleep(500);

        return bm;
    }

    public int senseRedCarousel(LinearOpMode opMode) throws InterruptedException {

        int location = 3;
        Bitmap bitmap = getBitmap();

        // top left = (0,0)

            // receive R, G, and B values for each pixel
            int redPixel1 = red(bitmap.getPixel(100, 50));
            int greenPixel1 = green(bitmap.getPixel(100, 50));
            int bluePixel1 = blue(bitmap.getPixel(100, 50));

            int redPixel2 = red(bitmap.getPixel(350, 50));
            int greenPixel2 = green(bitmap.getPixel(350, 50));
            int bluePixel2 = blue(bitmap.getPixel(350, 50));

            int redPixel3 = red(bitmap.getPixel(600, 50));
            int greenPixel3 = green(bitmap.getPixel(600, 50));
            int bluePixel3 = blue(bitmap.getPixel(600, 50));
            // if (bitmap.getColor(100,250) == bitmap.)

            if (redPixel1 < 30 && greenPixel1 < 30 && bluePixel1 < 30) {
                location = 1;
            } else if (redPixel2 < 30 && greenPixel2 < 30 && bluePixel2 < 30) {
                location = 2;
            } else if (redPixel3 < 30 && greenPixel3 < 30 && bluePixel3 < 30) {
                location = 3;
            }
                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

        opMode.telemetry.addData("Position", location);
        opMode.telemetry.update();
        return location;
    }
    public int senseRedWarehouse(LinearOpMode opMode) throws InterruptedException {

        int location = 0;
        Bitmap bitmap = getBitmap();

        // top left = (0,0)

        // receive R, G, and B values for each pixel
        int redPixel1 = red(bitmap.getPixel(125, 50));
        int greenPixel1 = green(bitmap.getPixel(125, 50));
        int bluePixel1 = blue(bitmap.getPixel(125, 50));

        int redPixel2 = red(bitmap.getPixel(350, 50));
        int greenPixel2 = green(bitmap.getPixel(350, 50));
        int bluePixel2 = blue(bitmap.getPixel(350, 50));

        int redPixel3 = red(bitmap.getPixel(580, 50));
        int greenPixel3 = green(bitmap.getPixel(580, 50));
        int bluePixel3 = blue(bitmap.getPixel(580, 50));
        // if (bitmap.getColor(100,250) == bitmap.)

        if (redPixel1 < 30 && greenPixel1 < 30 && bluePixel1 < 30) {
            location = 1;
        } else if (redPixel2 < 30 && greenPixel2 < 30 && bluePixel2 < 30) {
            location = 2;
        } else if (redPixel3 < 30 && greenPixel3 < 30 && bluePixel3 < 30) {
            location = 3;
        }
                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

        opMode.telemetry.addData("Position", location);
        opMode.telemetry.update();
        return location;
    }
    public int senseBlueCarousel(LinearOpMode opMode) throws InterruptedException {

        int location = 1;
        Bitmap bitmap = getBitmap();

        // top left = (0,0)

        // receive R, G, and B values for each pixel
        int redPixel1 = red(bitmap.getPixel(150, 50));
        int greenPixel1 = green(bitmap.getPixel(150, 50));
        int bluePixel1 = blue(bitmap.getPixel(150, 50));

        int redPixel2 = red(bitmap.getPixel(400, 50));
        int greenPixel2 = green(bitmap.getPixel(400, 50));
        int bluePixel2 = blue(bitmap.getPixel(400, 50));

        int redPixel3 = red(bitmap.getPixel(639, 50));
        int greenPixel3 = green(bitmap.getPixel(639, 50));
        int bluePixel3 = blue(bitmap.getPixel(639, 50));
        // if (bitmap.getColor(100,250) == bitmap.)

        if (redPixel1 < 30 && greenPixel1 < 30 && bluePixel1 < 30) {
            location = 1;
        } else if (redPixel2 < 30 && greenPixel2 < 30 && bluePixel2 < 30) {
            location = 2;
        } else if (redPixel3 < 30 && greenPixel3 < 30 && bluePixel3 < 30) {
            location = 3;
        }
                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

        opMode.telemetry.addData("Position", location);
        opMode.telemetry.update();
        return location;
    }
    public int senseBlueWarehouse(LinearOpMode opMode) throws InterruptedException {

        int location = 0;
        Bitmap bitmap = getBitmap();

        // top left = (0,0)

        // receive R, G, and B values for each pixel
        int redPixel1 = red(bitmap.getPixel(1, 50));
        int greenPixel1 = green(bitmap.getPixel(1, 50));
        int bluePixel1 = blue(bitmap.getPixel(1, 50));

        int redPixel2 = red(bitmap.getPixel(200, 50));
        int greenPixel2 = green(bitmap.getPixel(200, 50));
        int bluePixel2 = blue(bitmap.getPixel(200, 50));

        int redPixel3 = red(bitmap.getPixel(450, 50));
        int greenPixel3 = green(bitmap.getPixel(450, 50));
        int bluePixel3 = blue(bitmap.getPixel(450, 50));
        // if (bitmap.getColor(100,250) == bitmap.)

        if (redPixel3 < 30 && greenPixel3 < 30 && bluePixel3 < 30) {
            location = 3;
        } else if (redPixel2 < 30 && greenPixel2 < 30 && bluePixel2 < 30) {
            location = 2;
        } else if (redPixel1 < 30 && greenPixel1 < 30 && bluePixel1 < 30) {
            location = 1;
        }
                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

                    /*opMode.telemetry.addData("Red", redPixel);
                    opMode.telemetry.addData("Green", greenPixel);
                    opMode.telemetry.addData("Blue", bluePixel);
                    opMode.telemetry.update();*/

        opMode.telemetry.addData("Position", location);
        opMode.telemetry.update();
        return location;
    }
}
