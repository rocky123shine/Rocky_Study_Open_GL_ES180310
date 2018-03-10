package com.study_open_gl.rocky.rockyopengles180310_point_line_triangle;

/**
 * Created by rocky on 2018/3/10.
 */

import android.opengl.GLES10;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/**
 * 直接继承可以直接使用父类的方法
 */
public class RockyPoints extends OpenGLUtils {

    private IntBuffer verBuffer;
    private IntBuffer colorBuffer;
    private ByteBuffer indexBuffer;

    public RockyPoints() {
        //初始化数据
        initData();
    }

    private void initData() {
        int UNIT_SIZE = 10000;//缩放比例
        //顶点数据（默认3d z 全是0 目的绘制平面图形）
        int ver[] = new int[]{
                -2 * UNIT_SIZE, 3 * UNIT_SIZE, 0,
                UNIT_SIZE, UNIT_SIZE, 0,
                -1 * UNIT_SIZE, -2 * UNIT_SIZE, 0,
                2 * UNIT_SIZE, -3 * UNIT_SIZE, 0
        };
        //创建顶点缓冲
        verBuffer = getIntBuffer(ver);
        int one = 65536;//支持65536色彩通道
        //顶点个数=颜色个数
        //颜色数据(RGB A)
        int color[] = new int[]{
                one, 0, 0, 0,
                one, 0, 0, 0,
                one, 0, 0, 0,
                one, 0, 0, 0
        };
        colorBuffer = getIntBuffer(color);
        //索引
        byte index[] = new byte[]{
                0, 3, 2, 1
        };
        //创建顶点索引缓冲
        indexBuffer = getByteBuffer(index);

    }


    /**
     * 提供 绘制方法
     */

    public void drawPhoto(GL10 gl10){

        //启用顶点数据坐标
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //把数据设置到画笔上
        /**
         * glVertexPointer 参数
         *  int size,数据维度
         int type,数据类型
         int stride,绘制间隔 默认0
         java.nio.Buffer pointer   缓冲数据
         */
        gl10.glVertexPointer(3,GLES10.GL_FIXED,0,verBuffer);
        gl10.glColorPointer(4,GL10.GL_FIXED,0,colorBuffer);


        //屏幕上绘制一个点 可能看着会很小  那么我们可以让点变大
        gl10.glPointSize(20);


        //绘制  索引法绘制
        /**
         * glDrawElements 参数
         *  int mode, 绘制模型 点、线、三角型
         int count,索引个数
         int type,数据类型
         java.nio.Buffer indices 索引缓冲数据
         */
        gl10.glDrawElements(GL10.GL_POINTS,4,GL10.GL_UNSIGNED_BYTE,indexBuffer);

    }
}
