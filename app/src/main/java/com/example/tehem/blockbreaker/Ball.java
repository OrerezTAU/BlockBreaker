package com.example.tehem.blockbreaker;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by tehem on 31/01/2016.
 */
public class Ball {

    private int points=360;
    private float vertices[]={0.0f,0.0f,0.0f};
    private FloatBuffer vertBuff;


//centre of circle

    public Ball(){

        vertices=new float[(points+1)*3];
        for(int i=3;i<(points+1)*3;i+=3){
            double rad=(i*360/points*3)*(3.14/180);
            vertices[i]=(float)Math.cos(rad);
            vertices[i+1]=(float) Math.sin(rad);
            vertices[i+2]=0;
        }
        ByteBuffer bBuff=ByteBuffer.allocateDirect(vertices.length*4);
        bBuff.order(ByteOrder.nativeOrder());
        vertBuff=bBuff.asFloatBuffer();
        vertBuff.put(vertices);
        vertBuff.position(0);


    }

    public void draw(GL10 gl, float R, float G, float B, float A)
    {
        gl.glColor4f(R,G,B,A);
        gl.glPushMatrix();
        gl.glTranslatef(0, 0, 0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, points);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glPopMatrix();
    }

}

