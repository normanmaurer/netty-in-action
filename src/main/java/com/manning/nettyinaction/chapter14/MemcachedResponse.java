package com.manning.nettyinaction.chapter14;


public class MemcachedResponse {
    private final byte magic;
    private final byte opCode;
    private final short status;
    private final int id;
    private final long cas;
    private final int flags;
    private final int expires;
    private final String key;
    private final String data;

    public MemcachedResponse(byte magic, byte opCode,
                             short status, int id, long cas,
                             int flags, int expires, String key, String data) {
        this.magic = magic;
        this.opCode = opCode;
        this.status = status;
        this.id = id;
        this.cas = cas;
        this.flags = flags;
        this.expires = expires;
        this.key = key;
        this.data = data;
    }

    public byte magic() {
        return magic;
    }

    public byte opCode() {
        return opCode;
    }

    public short status() {
        return status;
    }

    public int id() {
        return id;
    }

    public long cas() {
        return cas;
    }

    public int flags() {
        return flags;
    }

    public int expires() {
        return expires;
    }

    public String key() {
        return key;
    }

    public String data() {
        return data;
    }
}
