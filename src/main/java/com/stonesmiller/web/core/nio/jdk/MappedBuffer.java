package com.stonesmiller.web.core.nio.jdk;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/16 21:43
 * Description:
 */
public class MappedBuffer {

    public void normal() throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("", "rw");
             FileChannel fileChannel = randomAccessFile.getChannel();) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) randomAccessFile.length());
            byteBuffer.clear();
            fileChannel.read(byteBuffer);
        }
    }

    public void mapped() throws IOException {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("", "rw");
             FileChannel fileChannel = randomAccessFile.getChannel()) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
            
        }
    }


}
