package com.example.tehem.blockbreaker;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Orerez on 25/12/2015.
 */
public class Block {

    private float vertices[] = {-0.3f, 0.15f, 0.0f,
            -0.3f, -0.15f, 0.0f,
            0.3f, -0.15f, 0.0f,
            0.3f, 0.15f, 0.0f
    };

    private short[] indices = {0, 1, 2, 0, 2, 3};

    private FloatBuffer vertexBuffer;

    private ShortBuffer indexBuffer;

    public Block() {

        // a float is 4 bytes, therefore we multiply the number if
        // vertices with 4.

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

    }


    public void draw(GL10 gl) {

        gl.glColor4f(0.5f, 0.0f, 1.0f,1.0f); // green
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);

        // Disable vertex array drawing to avoid
        // conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


    }
}