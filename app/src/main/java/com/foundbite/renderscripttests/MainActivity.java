package com.foundbite.renderscripttests;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foundbite.imagerenderscripts.ImageProcessor;

public class MainActivity extends Activity {

    private ImageView _image;
    private TextView _timeText;
    private Button _rotate90cwButton;
    private Button _rotate180cwButton;
    private Button _rotate270cwButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        _image = (ImageView)findViewById(R.id.image);
        _timeText = (TextView)findViewById(R.id.timeText);
    }

    public void onRotate90cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        Bitmap rotatedBitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .rotate90()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(rotatedBitmap);
    }

    public void onScaleRotate90cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        Bitmap rotatedBitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .scale(0.2f)
                .rotate90()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(rotatedBitmap);
    }

    public void onRotate180cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        Bitmap rotatedBitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .rotate180()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(rotatedBitmap);
    }

    public void onScaleRotate180cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        Bitmap rotatedBitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .scale(0.2f)
                .rotate180()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(rotatedBitmap);
    }

    public void onRotate270cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        Bitmap rotatedBitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .rotate270()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(rotatedBitmap);
    }

    public void onScaleRotate270cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        Bitmap rotatedBitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .scale(0.2f)
                .rotate270()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(rotatedBitmap);
    }
}
