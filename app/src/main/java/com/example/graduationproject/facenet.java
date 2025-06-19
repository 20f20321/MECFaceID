package com.example.graduationproject;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class facenet {

    private Interpreter interpreter;
    private int inputSize = 160;
    private final int embeddingSize = 128;

    public facenet(AssetManager assetManager, String modelPath) throws IOException {
        interpreter = new Interpreter(loadModelFile(assetManager, modelPath));
    }

    public float cosineSimilarity(float[] vector1, float[] vector2) {
        float dot = 0.0f;
        float norm1 = 0.0f;
        float norm2 = 0.0f;

        for (int i = 0; i < vector1.length; i++) {
            dot += vector1[i] * vector2[i];
            norm1 += Math.pow(vector1[i], 2);
            norm2 += Math.pow(vector2[i], 2);
        }

        return dot / ((float) (Math.sqrt(norm1) * Math.sqrt(norm2)));
    }

    private MappedByteBuffer loadModelFile(AssetManager assetManager, String modelPath) throws IOException {
        AssetFileDescriptor fileDescriptor = assetManager.openFd(modelPath);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public float[] getFaceEmbedding(Bitmap bitmap) {
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, inputSize, inputSize, true);
        resized = rotateBitmap(resized, 0); // زاوية التدوير 0 مبدئيًا

        ByteBuffer inputBuffer = convertBitmapToBuffer(resized);
        float[][] embeddings = new float[1][embeddingSize];
        interpreter.run(inputBuffer, embeddings);

        return embeddings[0];
    }

    private ByteBuffer convertBitmapToBuffer(Bitmap bitmap) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(inputSize * inputSize * 3 * 4);
        buffer.order(ByteOrder.nativeOrder());

        int[] pixels = new int[inputSize * inputSize];
        bitmap.getPixels(pixels, 0, inputSize, 0, 0, inputSize, inputSize);

        for (int pixel : pixels) {
            int r = (pixel >> 16) & 0xFF;
            int g = (pixel >> 8) & 0xFF;
            int b = pixel & 0xFF;

            float normalizedR = (r - 127.5f) / 128.0f;
            float normalizedG = (g - 127.5f) / 128.0f;
            float normalizedB = (b - 127.5f) / 128.0f;

            buffer.putFloat(normalizedR);
            buffer.putFloat(normalizedG);
            buffer.putFloat(normalizedB);
        }

        buffer.rewind();
        return buffer;
    }

    private Bitmap rotateBitmap(Bitmap bitmap, int rotationDegrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotationDegrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
