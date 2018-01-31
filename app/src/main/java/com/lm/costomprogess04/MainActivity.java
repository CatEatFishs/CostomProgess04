package com.lm.costomprogess04;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ShapView shapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapView = (ShapView) findViewById(R.id.shapview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setMax(4000);

        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3500);
        valueAnimator.setDuration(2*1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                progressBar.setProgress((int) animatedValue);
            }
        });
        valueAnimator.start();

    }

    public void exchange(View view) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shapView.exchage();
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 4000);
//        valueAnimator.setDuration(4000*1000);
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float animatedValue = (float) animation.getAnimatedValue();
//                //int value= (int) (animatedValue%3);
//                shapView.exchage();
//            }
//        });

    }
}
