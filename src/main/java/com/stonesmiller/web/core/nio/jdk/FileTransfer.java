package com.stonesmiller.web.core.nio.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Author: JackyShieh
 * Corporation: MistyStone LTD
 * WE LINK
 * Created: 2018/7/13 9:11
 * Description:
 */
public class FileTransfer {

    private static Logger logger = LoggerFactory.getLogger(FileTransfer.class);

    public static void transferFile(File source, File dest) throws IOException {
        if (!dest.exists()) {
            dest.createNewFile();
        }
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] bytes = new byte[1024 * 1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        }
    }

    public static void transferFileWithNIO(File source, File dest) throws IOException {
        if (!dest.exists()) {
            dest.createNewFile();
        }
        try (RandomAccessFile read = new RandomAccessFile(source, "rw");
             RandomAccessFile write = new RandomAccessFile(dest, "rw");
             FileChannel readChannel = read.getChannel();
             FileChannel writeChannel = write.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            while (readChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                writeChannel.write(byteBuffer);
                byteBuffer.clear();
            }
        }
    }

    //BUFFER
    public static void buffer() {
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
        //INITIAL 写模式
        logger.info("INITIAL");
        logger.info("CAPACITY:" + byteBuffer.capacity());
        logger.info("LIMIT:" + byteBuffer.limit());
        logger.info("POSITION:" + byteBuffer.position());
        logger.info("MARK:" + byteBuffer.mark());

        // PUT 写
        logger.info("PUT");
        byteBuffer.put("xieyuejun".getBytes());

        //AFTER PUT
        logger.info("AFTER PUT");
        logger.info("CAPACITY:" + byteBuffer.capacity());
        logger.info("LIMIT:" + byteBuffer.limit());
        logger.info("POSITION:" + byteBuffer.position());
        logger.info("MARK:" + byteBuffer.mark());

        // FLIP 切换读模式
        logger.info("FLIPR");
        byteBuffer.flip();

        //AFTER FLIP
        logger.info("AFTER FLIP");
        logger.info("CAPACITY:" + byteBuffer.capacity());
        logger.info("LIMIT:" + byteBuffer.limit());
        logger.info("POSITION:" + byteBuffer.position());
        logger.info("MARK:" + byteBuffer.mark());

        //READ 读
        logger.info("READ");
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        logger.info(new String(bytes));

        //AFTER READ
        logger.info("AFTER READ");
        logger.info("CAPACITY:" + byteBuffer.capacity());
        logger.info("LIMIT:" + byteBuffer.limit());
        logger.info("POSITION:" + byteBuffer.position());
        logger.info("MARK:" + byteBuffer.mark());

        // PUT AGAIN 再写
        logger.info("PUT AGAIN");
        byteBuffer.flip();
        byteBuffer.put("ORG".getBytes());

        //AFTER PUT AGAIN
        logger.info("AFTER PUT");
        logger.info("CAPACITY:" + byteBuffer.capacity());
        logger.info("LIMIT:" + byteBuffer.limit());
        logger.info("POSITION:" + byteBuffer.position());
        logger.info("MARK:" + byteBuffer.mark());


        //CLEAR 清空
        logger.info("CLEAR");
        byteBuffer.clear();

        //AFTER CLEAR
        logger.info("AFTER CLEAR");
        logger.info("CAPACITY:" + byteBuffer.capacity());
        logger.info("LIMIT:" + byteBuffer.limit());
        logger.info("POSITION:" + byteBuffer.position());
        logger.info("MARK:" + byteBuffer.mark());

    }

    public static void channel() {

//        1--使用FileChannel配合缓冲区实现文件复制的功能：
//        try (FileInputStream inputStream = new FileInputStream("");
//             FileOutputStream outputStream = new FileOutputStream("");
//             FileChannel inChannel = inputStream.getChannel();
//             FileChannel outChannel = outputStream.getChannel()) {
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
//            while (inChannel.read(byteBuffer) > 0) {
//                byteBuffer.flip();
//                outChannel.write(byteBuffer);
//                byteBuffer.clear();
//            }
//        } catch (IOException e) {
//            logger.error("", e);
//        }


        try (FileChannel inChannel = FileChannel.open(Paths.get("xxx"), StandardOpenOption.READ);
             FileChannel outChannel = FileChannel.open(Paths.get("xxx"), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ)) {
            //2--使用内存映射文件的方式实现文件复制的功能(直接操作缓冲区)：
            //内存映射文件
//            MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
//            MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
//
//            byte[] dst = new byte[inMappedBuffer.limit()];
//            inMappedBuffer.get(dst);
//            outMappedBuffer.put(dst);
            //3--通道之间通过transfer()实现数据的传输(直接操作缓冲区)
            //transfer
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            logger.error("", e);
        }
//        非直接缓冲区是需要经过一个：copy的阶段的(从内核空间copy到用户空间)
//        直接缓冲区不需要经过copy阶段，也可以理解成--->内存映射文件
//
//        使用直接缓冲区有两种方式：
//
//        缓冲区创建的时候分配的是直接缓冲区
//        在FileChannel上调用map()方法，将文件直接映射到内存中创建
    }

    public static void main(String[] args) {
        buffer();
    }

}
