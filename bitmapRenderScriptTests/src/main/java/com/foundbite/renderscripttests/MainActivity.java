package com.foundbite.renderscripttests;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foundbite.imagerenderscripts.ImageProcessor;

public class MainActivity extends Activity {

    private ImageView _image;
    private Bitmap _bitmap;
    private TextView _timeText;
    private TextView _resolutionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        _image = (ImageView)findViewById(R.id.image);
        _timeText = (TextView)findViewById(R.id.timeText);
        _resolutionText = (TextView)findViewById(R.id.resolutionText);

        _bitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .get();
        _image.setImageBitmap(_bitmap);
    }

    public void onRotate90cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        _bitmap = ImageProcessor
                .with(this, _bitmap)
                .rotate90()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _resolutionText.setText(Integer.toString(_bitmap.getWidth()) + " x " + Integer.toString(_bitmap.getHeight()));
        _image.setImageBitmap(_bitmap);
    }

    public void onRotate180cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        _bitmap = ImageProcessor
                .with(this, _bitmap)
                .rotate180()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _image.setImageBitmap(_bitmap);
    }

    public void onRotate270cwButtonClick(View v)
    {
        long startTime = System.nanoTime();

        _bitmap = ImageProcessor
                .with(this, _bitmap)
                .rotate270()
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _resolutionText.setText(Integer.toString(_bitmap.getWidth()) + " x " + Integer.toString(_bitmap.getHeight()));
        _image.setImageBitmap(_bitmap);
    }

    public void onScaleButtonClick(View v)
    {
        long startTime = System.nanoTime();

        _bitmap = ImageProcessor
                .with(this, _bitmap)
                .scale(0.5f)
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _resolutionText.setText(Integer.toString(_bitmap.getWidth()) + " x " + Integer.toString(_bitmap.getHeight()));
        _image.setImageBitmap(_bitmap);
    }

    public void onCropButtonClick(View v)
    {
        int w = _bitmap.getWidth();
        int h = _bitmap.getHeight();

        Rect bounds = new Rect(w / 4, h / 4, w * 3 / 4, h * 3 / 4);

        long startTime = System.nanoTime();

        _bitmap = ImageProcessor
                .with(this, _bitmap)
                .crop(bounds)
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _resolutionText.setText(Integer.toString(_bitmap.getWidth()) + " x " + Integer.toString(_bitmap.getHeight()));
        _image.setImageBitmap(_bitmap);
    }

    public void onBlurButtonClick(View v)
    {
        long startTime = System.nanoTime();

        _bitmap = ImageProcessor
                .with(this, _bitmap)
                .blur(50, 2)
                .get();

        long stopTime = System.nanoTime();
        long elapsedTime = (stopTime - startTime) / 1000000;

        _timeText.setText(Long.toString(elapsedTime) + "ms");
        _resolutionText.setText(Integer.toString(_bitmap.getWidth()) + " x " + Integer.toString(_bitmap.getHeight()));
        _image.setImageBitmap(_bitmap);
    }

    public void onResetButtonClick(View v)
    {
        _bitmap.recycle();
        _bitmap = ImageProcessor
                .with(this, R.drawable.plant)
                .get();

        _timeText.setText(null);
        _resolutionText.setText(Integer.toString(_bitmap.getWidth()) + " x " + Integer.toString(_bitmap.getHeight()));
        _image.setImageBitmap(_bitmap);
    }
}
