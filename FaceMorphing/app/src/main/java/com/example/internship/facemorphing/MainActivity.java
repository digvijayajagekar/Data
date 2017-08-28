package com.example.internship.facemorphing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {

    private ImageView collageImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collageImage = (ImageView)findViewById(R.id.imageView3);

        Button btnSport = (Button)findViewById(R.id.btnSport);
        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.face2);
                Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.filteroldface);
                Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage);

                collageImage.setImageBitmap(mergedImages);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        //canvas.drawBitmap(firstImage, 0f, 0f, null);
        //canvas.drawBitmap(secondImage, 0f, 0f, null);
        //return result;

        int widthBack = firstImage.getWidth();
        int widthFront = secondImage.getWidth();
        int centreX1 = (canvas.getWidth()  - firstImage.getWidth()) /2;
        int centreY1 = (canvas.getHeight() - firstImage.getHeight()) /2;
        int centreX2 = (canvas.getWidth()  - secondImage.getWidth()) /2;
        int centreY2 = (canvas.getHeight() - secondImage.getHeight()) /2;

        //float move = (widthBack - widthFront) / 2;
        canvas.drawBitmap(firstImage, centreX1, centreY1, null);
        canvas.drawBitmap(secondImage,centreX2, centreY2, null);
        return result;
    }
}