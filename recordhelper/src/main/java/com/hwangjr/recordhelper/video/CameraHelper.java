package com.hwangjr.recordhelper.video;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.os.Build;
import android.view.Surface;

import java.util.List;

/*
 * Represents camera management helper class.
 * Holds method for setting camera display orientation. 
 */
public class CameraHelper {

    public static int getAvailableCamerasCount() {
        return Camera.getNumberOfCameras();
    }

    public static int getDefaultCameraID() {
        int camerasCnt = getAvailableCamerasCount();
        int defaultCameraID = 0;
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < camerasCnt; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK) {
                defaultCameraID = i;
            }
        }
        return defaultCameraID;
    }

    public static boolean isCameraFacingBack(int cameraID) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(cameraID, cameraInfo);
        return (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK);
    }

    @SuppressLint("NewApi")
    public static List<Size> getCameraSupportedVideoSizes(Camera camera) {
        if ((Build.VERSION.SDK_INT >= 11) && (camera != null)) {
            return camera.getParameters().getSupportedVideoSizes();
        } else {
            return null;
        }
    }

    public static int setCameraDisplayOrientation(int cameraId, Camera camera, int displayRotation) {
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int degrees = 0;
        switch (displayRotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int camRotationDegree = 0;
        if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
            camRotationDegree = (info.orientation + degrees) % 360;
            camRotationDegree = (360 - camRotationDegree) % 360; // compensate the mirror
        } else {
            camRotationDegree = (info.orientation - degrees + 360) % 360;
        }

        if (camera != null) {
            camera.setDisplayOrientation(camRotationDegree);
        }
        return camRotationDegree;
    }
}
