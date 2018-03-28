package cn.ltaoj.mythguard.media;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.media.Image;
import android.os.Debug;
import android.util.Log;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by ltaoj on 2018/3/26 23:50.
 */

public class YUV420Convert {
    private static final String TAG = "YUV420Convert";

    private static final boolean VERBOSE = false;

    public static final int COLOR_FormatI420 = 1;
    public static final int COLOR_FormatNV21 = 2;

    private static boolean isImageFormatSupported(Image image) {
        int format = image.getFormat();
        switch (format) {
            case ImageFormat.YUV_420_888:
            case ImageFormat.NV21:
            case ImageFormat.YV12:
                return true;
        }
        return false;
    }

    /**
     * NV21格式为YYYY VU
     * NV12格式为YYYY UV
     * @param image
     * @param colorFormat
     * @return
     */
    public static byte[] getDataFromImage(Image image, int colorFormat) {
        if (colorFormat != COLOR_FormatI420 && colorFormat != COLOR_FormatNV21) {
            throw new IllegalArgumentException("only support COLOR_FormatI420 " + "and COLOR_FormatNV21");
        }
        if (!isImageFormatSupported(image)) {
            throw new RuntimeException("can't convert Image to byte array, format " + image.getFormat());
        }
        Rect crop = image.getCropRect();
        int format = image.getFormat();
        int width = crop.width();
        int height = crop.height();
        Image.Plane[] planes = image.getPlanes();
        byte[] data = new byte[width * height * ImageFormat.getBitsPerPixel(format) / 8];
        byte[] rowData = new byte[planes[0].getRowStride()];
        if (VERBOSE) Log.v(TAG, "get data from " + planes.length + " planes");
        int channelOffset = 0;
        int outputStride = 1;
        for (int i = 0; i < planes.length; i++) {
            switch (i) {
                case 0:
                    channelOffset = 0;
                    outputStride = 1;
                    break;
                case 1:
                    if (colorFormat == COLOR_FormatI420) {
                        channelOffset = width * height;
                        outputStride = 1;
                    } else if (colorFormat == COLOR_FormatNV21) {
                        channelOffset = width * height + 1;
                        outputStride = 2;
                    }
                    break;
                case 2:
                    if (colorFormat == COLOR_FormatI420) {
                        channelOffset = (int) (width * height * 1.25);
                        outputStride = 1;
                    } else if (colorFormat == COLOR_FormatNV21) {
                        channelOffset = width * height;
                        outputStride = 2;
                    }
                    break;
            }
            ByteBuffer buffer = planes[i].getBuffer();
            int rowStride = planes[i].getRowStride();
            int pixelStride = planes[i].getPixelStride();
            if (VERBOSE) {
                Log.v(TAG, "pixelStride " + pixelStride);
                Log.v(TAG, "rowStride " + rowStride);
                Log.v(TAG, "width " + width);
                Log.v(TAG, "height " + height);
                Log.v(TAG, "buffer size " + buffer.remaining());
            }
            int shift = (i == 0) ? 0 : 1;
            int w = width >> shift;
            int h = height >> shift;
            buffer.position(rowStride * (crop.top >> shift) + pixelStride * (crop.left >> shift));

            for (int row = 0; row < h; row++) {
                int length;
                if (pixelStride == 1 && outputStride == 1) {
                    length = w;
                    buffer.get(data, channelOffset, length);
                    channelOffset += length;
                } else {
                    length = (w - 1) * pixelStride + 1;
                    buffer.get(rowData, 0, length);
                    for (int col = 0; col < w; col++) {
                        data[channelOffset] = rowData[col * pixelStride];
                        channelOffset += outputStride;
                    }
                }

                if (row < h - 1) {
                    buffer.position(buffer.position() + rowStride - length);
                }
            }
            if (VERBOSE) Log.v(TAG, "Finished reading data from plane " + i);
        }
        return data;
    }

    public static byte[] cutYuv(int format, byte[] srcData, int srcWidth, int srcHeight, int startX, int startY, int cutWidth, int cutHeight) {
        if (format != COLOR_FormatNV21) {
            throw new IllegalArgumentException("only support NV21");
        }

        if (startX >= srcWidth || startY >= srcHeight) {
            throw new IllegalArgumentException("startX must no more than srcWidth and startY must no more than srcHeight");
        }

        if (cutWidth > srcWidth || cutHeight > srcHeight) {
            throw new IllegalArgumentException("cutWidth must no more than srcWidth and cutHeight must nno more than srcHeight");
        }

        byte[] targetData = new byte[cutWidth * cutHeight * 3 / 2];

        switch (format) {
            case COLOR_FormatNV21:
                int count = 0;
                // copy Y
                for (int i = startY;i < startY+cutHeight;i++) {
                    for (int j = startX;j < startX+cutWidth;j++) {
                        targetData[count++] = srcData[i*srcWidth+j];
                    }
                }
                // Y copy right!!!

                // copy VU
                for (int i = srcHeight+startY/2;i < srcHeight+startY/2+cutHeight/2;i++) {
                    for (int j = startX;j < startX+cutWidth;j++) {
                        targetData[count++] = srcData[i*srcWidth+j];
                    }
                }
                // VU copy right!!
                // 2018年3月29日01:27:55
                // 原因，没有矫正cutHeight的值为偶数，导致识别错误
                break;
            default:
                break;
        }
        return targetData;
    }

    public static byte[] cutYuvImage(Image image, int colorFormat, Rect cropRect) {
        adjustRect(cropRect);
        byte[] yuv = getDataFromImage(image, colorFormat);
        return cutYuv(colorFormat, yuv, image.getWidth(), image.getHeight(), cropRect.left, cropRect.top, cropRect.width(), cropRect.height());
    }

    private static void adjustRect(Rect rect) {
        rect.right = rect.right+4-rect.width()%4;
        rect.bottom = rect.bottom-rect.height()%2;
        rect.offset(rect.left%4,0);
    }

    public byte[] cropYUV420(byte[] data,int imageW,int imageH,int newImageH){
        int cropH;int i,j,count,tmp;
        byte[] yuv =new byte[imageW*newImageH*3/2];

        cropH =(imageH - newImageH)/2;

        count = 0;
        for(j=cropH;j<cropH+newImageH;j++){
            for(i=0;i<imageW;i++){
                yuv[count++]= data[j*imageW+i];
            }
        }

        //Cr Cb
        tmp = imageH+cropH/2;
        for(j=tmp;j<tmp + newImageH/2;j++)
        {
            for(i=0;i<imageW;i++){
                yuv[count++]= data[j*imageW+i];
            }
        }
        return yuv;
    }


}
