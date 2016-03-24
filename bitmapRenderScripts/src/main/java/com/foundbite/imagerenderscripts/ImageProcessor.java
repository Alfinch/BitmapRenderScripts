package com.foundbite.imagerenderscripts;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicResize;
import com.foundbite.rs.ScriptC_rotate;
import com.foundbite.rs.ScriptC_crop;

/**
 * Created by Alfie on 22/03/2016.
 */
public class ImageProcessor
{
    private Bitmap _bitmap;
    private RenderScript _renderScript;

    private ImageProcessor(Context c, Bitmap b)
    {
        _bitmap = b;
        _renderScript = RenderScript.create(c);
    }

    public static ImageProcessor with(Context c, Bitmap b)
    {
        return new ImageProcessor(c, b);
    }

    public static ImageProcessor with(Context context, Integer resource)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap b = BitmapFactory.decodeResource(context.getResources(), resource, options);

        return new ImageProcessor(context, b);
    }

    public ImageProcessor rotate90()
    {
        int oldW = _bitmap.getWidth();
        int oldH = _bitmap.getHeight();

        int newW = oldH;
        int newH = oldW;

        final Allocation input = Allocation.createFromBitmap(_renderScript, _bitmap);

        _bitmap.recycle();
        _bitmap = Bitmap.createBitmap(newW, newH, Bitmap.Config.ARGB_8888);

        final Allocation output = Allocation.createFromBitmap(_renderScript, _bitmap);

        final ScriptC_rotate script = new ScriptC_rotate(_renderScript);
        script.set_input(input);
        script.set_inWidth(oldW);
        script.set_inHeight(oldH);
        script.forEach_rotate_90_cw(output, output);

        output.copyTo(_bitmap);

        return this;
    }

    public ImageProcessor rotate180()
    {
        int w = _bitmap.getWidth();
        int h = _bitmap.getHeight();

        final Allocation input = Allocation.createFromBitmap(_renderScript, _bitmap);

        _bitmap.recycle();
        _bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        final Allocation output = Allocation.createFromBitmap(_renderScript, _bitmap);

        final ScriptC_rotate script = new ScriptC_rotate(_renderScript);
        script.set_input(input);
        script.set_inWidth(w);
        script.set_inHeight(h);
        script.forEach_rotate_180_cw(output, output);

        output.copyTo(_bitmap);

        return this;
    }

    public ImageProcessor rotate270()
    {
        int oldW = _bitmap.getWidth();
        int oldH = _bitmap.getHeight();

        int newW = oldH;
        int newH = oldW;

        final Allocation input = Allocation.createFromBitmap(_renderScript, _bitmap);

        _bitmap.recycle();
        _bitmap = Bitmap.createBitmap(newW, newH, Bitmap.Config.ARGB_8888);

        final Allocation output = Allocation.createFromBitmap(_renderScript, _bitmap);

        final ScriptC_rotate script = new ScriptC_rotate(_renderScript);
        script.set_input(input);
        script.set_inWidth(oldW);
        script.set_inHeight(oldH);
        script.forEach_rotate_270_cw(output, output);

        output.copyTo(_bitmap);

        return this;
    }

    public ImageProcessor scale(float multiplier)
    {
        int newW = (int)(_bitmap.getWidth() * multiplier);
        int newH = (int)(_bitmap.getHeight() * multiplier);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            final Allocation input = Allocation.createFromBitmap(_renderScript, _bitmap);

            _bitmap.recycle();
            _bitmap = Bitmap.createBitmap(newW, newH, Bitmap.Config.ARGB_8888);

            final Allocation output = Allocation.createFromBitmap(_renderScript, _bitmap);

            final ScriptIntrinsicResize script = ScriptIntrinsicResize.create(_renderScript);
            script.setInput(input);
            script.forEach_bicubic(output);

            output.copyTo(_bitmap);
        }
        else
        {
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(_bitmap, newW, newH, true);
            _bitmap.recycle();
            _bitmap = scaledBitmap;
        }

        return this;
    }

    public ImageProcessor crop(Rect bounds)
    {
        int oldW = _bitmap.getWidth();
        int oldH = _bitmap.getHeight();

        if (bounds.left > oldW || bounds.top > oldH) return this;
        if (bounds.right > oldW) bounds.right = oldW;
        if (bounds.bottom > oldH) bounds.bottom = oldH;

        final Allocation input = Allocation.createFromBitmap(_renderScript, _bitmap);

        _bitmap.recycle();
        _bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);

        final Allocation output = Allocation.createFromBitmap(_renderScript, _bitmap);

        final ScriptC_crop script = new ScriptC_crop(_renderScript);
        script.set_input(input);
        script.set_left(bounds.left);
        script.set_top(bounds.top);
        script.forEach_crop(output, output);

        output.copyTo(_bitmap);

        return this;
    }

    public Bitmap get()
    {
        return _bitmap;
    }
}
