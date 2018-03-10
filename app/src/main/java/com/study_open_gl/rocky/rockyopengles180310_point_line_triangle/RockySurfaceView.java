package com.study_open_gl.rocky.rockyopengles180310_point_line_triangle;

import android.content.Context;
import android.graphics.Point;
import android.opengl.GLES10;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by rocky on 2018/3/10.
 * 自定义SurfaceView 实现构造方法
 */

public class RockySurfaceView extends GLSurfaceView {

    private RockyPoints points;

    public RockySurfaceView(Context context) {
        super(context);
        //初始化渲染器
        initRender();
    }

    private void initRender() {
        //本Demo 用的版本OpenGL ES 1.0
        //这里相对 2.0版本 使用了很多OpenGL 提供的方法
        //在2.0中 我们将使用2.0的封装
        //这里使用1.0 也是为了 认识并熟悉OpenGL 的方法

        //设置渲染器
        setRenderer(new RockyRenderer());

        //设置渲染模式  主动 和 被动 这里是主动
        setRenderMode(RENDERMODE_CONTINUOUSLY);
    }


    /**
     * 自定义Renderer 并实现Renderer接口
     */
    private class RockyRenderer implements Renderer {
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //SurfaceView 创建

            //关闭抗抖动
            gl.glDisable(GLES10.GL_DITHER);
            //设置hint修正模式  设置 快速模式
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);

            //设置背景颜色 r g b a
            gl.glClearColor(0,0,0,0);

            //开启深度测试  本次绘制的是 2d图形  可以不开启  这里就是认识一下
            gl.glEnable(GLES10.GL_DEPTH_TEST);
            points = new RockyPoints();


        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //SurfaceView的改变

            //在这里做的事情 直接关系到我们目标效果
            //我们绘制的图形都要通过视口显示  在这里 我们设置一下视口
            //有关是口的解释 请看 http://blog.csdn.net/rocky123shine/article/details/79478881
            gl.glViewport(0,0,width,height);
            //设置投影矩阵
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //单位化矩阵
            gl.glLoadIdentity();
            //投影 OpenGL 提供了两个  正交（gl.glOrthof） 和 透视投影（gl.glFrustumf）
            //这里使用透视矩阵
            //设置透视投影

            /**
             * glFrustumf 参数解释
             *  float left,近平面中心点到左边距的坐标 负值
             float right,近平面中心点到右边距的坐标 正值
             float bottom,近平面中心点到下边距的坐标 负值
             float top,近平面中心点到上边距的坐标 正值
             float zNear,相机距离近平面的距离
             float zFar 相机距离远平面的距离
             */

            //设置近平面上的值 我们一般用 屏幕宽高比

            //屏幕宽高比
            float r = (float)width/height;
            gl.glFrustumf(-r,r,-1,1,1,10);

        }

        @Override
        public void onDrawFrame(GL10 gl) {
            //SurfaceView 绘制新界面

            //在这里 我们做具体的绘制工作

            //清除颜色和深度缓存
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GLES10.GL_DEPTH_BUFFER_BIT);
            //设置模型矩阵
            gl.glMatrixMode(GLES10.GL_MODELVIEW);
            //单位化
            gl.glLoadIdentity();

            //如果程序写完 而且没有报错 那么就要考录你设置的进远平面的比例了
            //也可以使用平移的方法  把图像“移动”到屏幕内
            gl.glTranslatef(0, 0, -3.0f);

            //创建类 抽取 数据设置 提供绘制方法
            points.drawPhoto(gl);
        }
    }
}
