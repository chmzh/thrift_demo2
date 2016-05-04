/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.c.proto;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface DvDataProxy {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"DvDataProxy\",\"namespace\":\"com.cndw.proto\",\"types\":[{\"type\":\"record\",\"name\":\"DvSchema\",\"fields\":[{\"name\":\"dbName\",\"type\":\"string\"},{\"name\":\"tbName\",\"type\":\"string\"},{\"name\":\"tbSchema\",\"type\":\"string\"}]},{\"type\":\"record\",\"name\":\"DvData\",\"fields\":[{\"name\":\"dbName\",\"type\":\"string\"},{\"name\":\"tbName\",\"type\":\"string\"},{\"name\":\"tbParCol\",\"type\":\"string\"},{\"name\":\"tbParColVal\",\"type\":\"string\"},{\"name\":\"tbData\",\"type\":\"bytes\"},{\"name\":\"progress\",\"type\":\"int\"}]}],\"messages\":{\"sendSchema\":{\"request\":[{\"name\":\"message\",\"type\":\"DvSchema\"}],\"response\":\"string\"},\"sendData\":{\"request\":[{\"name\":\"message\",\"type\":\"DvData\"}],\"response\":\"string\"},\"queryDataCount\":{\"request\":[{\"name\":\"sql\",\"type\":\"string\"}],\"response\":\"int\"},\"queryData\":{\"request\":[{\"name\":\"sql\",\"type\":\"string\"},{\"name\":\"curPage\",\"type\":\"int\"},{\"name\":\"rows\",\"type\":\"int\"}],\"response\":\"string\"}}}");
  java.lang.CharSequence sendSchema(com.c.proto.DvSchema message) throws org.apache.avro.AvroRemoteException;
  java.lang.CharSequence sendData(com.c.proto.DvData message) throws org.apache.avro.AvroRemoteException;
  int queryDataCount(java.lang.CharSequence sql) throws org.apache.avro.AvroRemoteException;
  java.lang.CharSequence queryData(java.lang.CharSequence sql, int curPage, int rows) throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  public interface Callback extends DvDataProxy {
    public static final org.apache.avro.Protocol PROTOCOL = com.c.proto.DvDataProxy.PROTOCOL;
    void sendSchema(com.c.proto.DvSchema message, org.apache.avro.ipc.Callback<java.lang.CharSequence> callback) throws java.io.IOException;
    void sendData(com.c.proto.DvData message, org.apache.avro.ipc.Callback<java.lang.CharSequence> callback) throws java.io.IOException;
    void queryDataCount(java.lang.CharSequence sql, org.apache.avro.ipc.Callback<java.lang.Integer> callback) throws java.io.IOException;
    void queryData(java.lang.CharSequence sql, int curPage, int rows, org.apache.avro.ipc.Callback<java.lang.CharSequence> callback) throws java.io.IOException;
  }
}