package com.study_open_gl.rocky.rockyopengles180310_point_line_triangle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;


/**
 * 本类 提供了缓存数据获得的方法
 */
public abstract class OpenGLUtils {

    public FloatBuffer getFloatBuffer(float[] ver) {
        ByteBuffer vbb = ByteBuffer.allocateDirect(ver.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = vbb.asFloatBuffer();
        buffer.put(ver);
        buffer.position(0);
        return buffer;
    }

    public ByteBuffer getByteBuffer(byte[] indices) {
        ByteBuffer indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
        return indexBuffer;
    }

    public IntBuffer getIntBuffer(int[] ver) {
        //创建数据缓冲
        //一个整数是四个字节所以：ver.length * 4
        ByteBuffer vbb = ByteBuffer.allocateDirect(ver.length * 4);
        //设置字节顺序（不同的平台字节顺序不同）
        vbb.order(ByteOrder.nativeOrder());
        //转化为int型缓冲
        IntBuffer intBuffer = vbb.asIntBuffer();
        //向缓冲区内加入数据
        intBuffer.put(ver);
        //设置缓冲区其实位置
        intBuffer.position(0);
        return intBuffer;
    }

}
