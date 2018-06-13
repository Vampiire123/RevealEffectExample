package com.example.syl.revealeffect

import android.animation.Animator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.os.Build
import android.view.View.VISIBLE


class MainActivity : AppCompatActivity() {

    lateinit var btCircularRevealShow: Button
    lateinit var fabCircularRevealShow: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btCircularRevealShow = findViewById(R.id.bt_circularreveal)
        fabCircularRevealShow = findViewById(R.id.fab_circlereveal_start)

        btCircularRevealShow.setOnClickListener {
            var cx = fabCircularRevealShow.width / 2
            var cy = fabCircularRevealShow.height / 2
            if (fabCircularRevealShow.visibility == VISIBLE) {
                hideFab(cx, cy)
            } else {
                showFab(cx, cy)
            }
        }
    }

    fun showFab(cx: Int, cy: Int) {
        var finalRadius = Math.max(cx, cy)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            var animator = ViewAnimationUtils.createCircularReveal(fabCircularRevealShow, cx, cy, 0f, finalRadius.toFloat())
            animator.start()
            fabCircularRevealShow.visibility = View.VISIBLE
        }
    }

    fun hideFab(cx: Int, cy: Int) {
        fabCircularRevealShow.visibility = View.VISIBLE
        val initialRadious = Math.max(cx, cy).toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val animator = ViewAnimationUtils.createCircularReveal(fabCircularRevealShow, cx, cy, initialRadious, 0f)
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {}
                override fun onAnimationEnd(animator: Animator) {
                    fabCircularRevealShow.setVisibility(View.INVISIBLE)
                }

                override fun onAnimationCancel(animator: Animator) {}
                override fun onAnimationRepeat(animator: Animator) {}
            })
            animator.start()
        }
    }
}
