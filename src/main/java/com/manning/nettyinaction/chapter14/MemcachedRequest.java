package com.manning.nettyinaction.chapter14;

import java.util.Random;

public class MemcachedRequest {
    private static final Random rand = new Random();
    private final int magic = 0x80;//fixed so hard coded
    private final byte opCode; //the operation e.g. set or get
    private final String key; //the key to delete, get or set
    private final int flags = 0xdeadbeef; //random
    private final int expires; //0 = item never expires
    private final String body; //if opCode is set, the value
    private final int id = rand.nextInt(); //Opaque
    private final long cas = 0; //data version check...not used
    private final boolean hasExtras; //not all ops have extras

    public MemcachedRequest(byte opcode, String key, String value) {
        this.opCode = opcode;
        this.key = key;
        this.body = value == null ? "" : value;
        this.expires = 0;
        //only set command has extras in our example
        hasExtras = opcode == Opcode.SET;
    }

    public MemcachedRequest(byte opCode, String key) {
        this(opCode, key, null);
    }

    public int magic() {
        return magic;
    }

    public int opCode() {
        return opCode;
    }

    public String key() {
        return key;
    }

    public int flags() {
        return flags;
    }

    public int expires() {
        return expires;
    }

    public String body() {
        return body;
    }

    public int id() {
        return id;
    }

    public long cas() {
        return cas;
    }

    public boolean hasExtras() {
        return hasExtras;
    }
}
