package cn.ltaoj.mythguard.mvp.model.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;

import com.arcsoft.facedetection.AFD_FSDKFace;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.ltaoj.mythguard.mvp.model.ScanModel;

/**
 * Created by ltaoj on 2018/3/24 23:02.
 */

public class ScanModelimpl implements ScanModel{
    private static final String TAG = "ScanModelimpl";

    // 保存的人脸图片名称
    private static final String picName = "face.jpg";

    // 保存图片的父目录
    private File pFile;

    private File mFile = null;

    @Override
    public void setParentFile(File pFile) {
        this.pFile = pFile;
        mFile = new File(pFile, picName);
    }

    @Override
    public Bitmap loadImage(ImageType imageType) {
        Bitmap bitmap = null;
        switch (imageType) {
            case FACE:
                bitmap = BitmapFactory.decodeFile(pFile.getPath() + "/" + picName);
                Matrix matrix = new Matrix();
                matrix.setRotate(90f);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                break;
            case CODE:

                break;
            default:
                break;
        }
        return bitmap;
    }

    @Override
    public void saveImage(YuvImage yuvImage, AFD_FSDKFace face) {
        new Thread(new ImageSaver(yuvImage, mFile)).start();
    }

    /**
     * Saves a JPEG {@link Image} into the specified {@link File}.
     */
    private static class ImageSaver implements Runnable {

        /**
         * The JPEG image
         */
        private final YuvImage mYuvImage;
        /**
         * The file we save the image into.
         */
        private final File mFile;

        ImageSaver(YuvImage yuvImage, File file) {
            mYuvImage = yuvImage;
            mFile = file;
        }

        @Override
        public void run() {
//            ByteBuffer buffer = mImage.getPlanes()[0].getBuffer();
//            byte[] bytes = new byte[buffer.remaining()];
//            buffer.get(bytes);

//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

            FileOutputStream output = null;
            try {
                output = new FileOutputStream(mFile);
                Rect rect = new Rect(0, 0, mYuvImage.getWidth(), mYuvImage.getHeight());
                mYuvImage.compressToJpeg(rect, 100, output);

//                output.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != output) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
