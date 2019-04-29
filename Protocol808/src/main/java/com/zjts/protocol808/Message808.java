package com.zjts.protocol808;

import com.zjts.protocol808.MessageBodys.Message808Body;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Message808 implements Analysisable {
    public Message808(){}

    public Message808(Message808Head message808Head,
                      Message808Body message808Body) {
        this.messageHead = message808Head;
        this.messageBody = message808Body;
    }

    /*
     * 808协议标识符，以0x7E开头，以0x7E结尾；
     */
    private byte delimiter = 0x7e;

    /*
     * 原始数据，没有经过转义的
     */
    public byte[] originMessage;

    public String error;


    public Message808Head messageHead;
    public Message808Body messageBody;

    public Message808Head getMessageHead() {
        return messageHead;
    }

    public void setMessageHead(Message808Head messageHead) {
        this.messageHead = messageHead;
    }

    public Message808Body getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(Message808Body messageBody) {
        this.messageBody = messageBody;
    }

    public byte[] getOriginMessage() {
        return originMessage;
    }

    public void setOriginMessage(byte[] originMessage) {
        this.originMessage = originMessage;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean bytes2Entity(byte[] bytes) {
        if (bytes[0] != delimiter || bytes[bytes.length] != delimiter) {
            error = "不是0x7E开头或不是0x7E结尾";
            return false;
        }
        originMessage = bytes;
        byte[] buffer = unescape(bytes);

        return true;
    }

    @Override
    public byte[] entity2Bytes() {
        return new byte[0];
    }


    /**
     * 转义还原
     */
    private byte[] unescape(byte[] bytes) {
        int csc = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; i++) {
            int actual = bytes[i];
            if ((bytes[i] & 0xFF) == 0x7D) {
                if (bytes[i] == 0x01) {
                    actual = 0x7D;
                } else if (bytes[i] == 0x02) {
                    actual = 0x7E;
                }
                i++;
            }
            csc ^= actual;
            baos.write(actual);
        }
        byte[] reBytes = baos.toByteArray();
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reBytes;
    }
}
