package com.example.servicesapp.barcode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.servicesapp.R;

public class QRCodePreviewOverlay extends FrameLayout {

    private Paint paint;
    private static final Xfermode CLEAR_MODE = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private static final Xfermode OVERLAY_MODE = new PorterDuffXfermode(PorterDuff.Mode.OVERLAY);

    private float virtualPadding;
    private float radius;

    public QRCodePreviewOverlay(@NonNull Context context) {
        super(context);
        init(null, 0);
    }

    public QRCodePreviewOverlay(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public QRCodePreviewOverlay(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(@Nullable AttributeSet attrs, int defStyleAttr) {

        if(attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.QRCodePreviewOverlay, defStyleAttr, 0);

            virtualPadding = typedArray.getDimension(R.styleable.QRCodePreviewOverlay_overlayPadding, 0);
            radius = typedArray.getDimension(R.styleable.QRCodePreviewOverlay_cornerRadius, 0);

            typedArray.recycle();
        }

        setWillNotDraw(false);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getContext().getResources().getColor(R.color.qr_code_overlay_color));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        paint.setXfermode(OVERLAY_MODE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setXfermode(CLEAR_MODE);
        float size = getWidth() - virtualPadding * 2;
        float top = getHeight() / 2 - size / 2;
        canvas.drawRoundRect(virtualPadding, top, getWidth() - virtualPadding, top + size, radius, radius, paint);
    }

    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
        invalidate();
    }
}
