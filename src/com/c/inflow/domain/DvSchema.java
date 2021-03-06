/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.c.inflow.domain;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-03-16")
public class DvSchema implements org.apache.thrift.TBase<DvSchema, DvSchema._Fields>, java.io.Serializable, Cloneable, Comparable<DvSchema> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DvSchema");

  private static final org.apache.thrift.protocol.TField DB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("dbName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("tbName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TB_SCHEMA_FIELD_DESC = new org.apache.thrift.protocol.TField("tbSchema", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DvSchemaStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DvSchemaTupleSchemeFactory());
  }

  public String dbName; // required
  public String tbName; // required
  public String tbSchema; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DB_NAME((short)1, "dbName"),
    TB_NAME((short)2, "tbName"),
    TB_SCHEMA((short)3, "tbSchema");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DB_NAME
          return DB_NAME;
        case 2: // TB_NAME
          return TB_NAME;
        case 3: // TB_SCHEMA
          return TB_SCHEMA;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DB_NAME, new org.apache.thrift.meta_data.FieldMetaData("dbName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TB_NAME, new org.apache.thrift.meta_data.FieldMetaData("tbName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TB_SCHEMA, new org.apache.thrift.meta_data.FieldMetaData("tbSchema", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DvSchema.class, metaDataMap);
  }

  public DvSchema() {
  }

  public DvSchema(
    String dbName,
    String tbName,
    String tbSchema)
  {
    this();
    this.dbName = dbName;
    this.tbName = tbName;
    this.tbSchema = tbSchema;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DvSchema(DvSchema other) {
    if (other.isSetDbName()) {
      this.dbName = other.dbName;
    }
    if (other.isSetTbName()) {
      this.tbName = other.tbName;
    }
    if (other.isSetTbSchema()) {
      this.tbSchema = other.tbSchema;
    }
  }

  public DvSchema deepCopy() {
    return new DvSchema(this);
  }

  @Override
  public void clear() {
    this.dbName = null;
    this.tbName = null;
    this.tbSchema = null;
  }

  public String getDbName() {
    return this.dbName;
  }

  public DvSchema setDbName(String dbName) {
    this.dbName = dbName;
    return this;
  }

  public void unsetDbName() {
    this.dbName = null;
  }

  /** Returns true if field dbName is set (has been assigned a value) and false otherwise */
  public boolean isSetDbName() {
    return this.dbName != null;
  }

  public void setDbNameIsSet(boolean value) {
    if (!value) {
      this.dbName = null;
    }
  }

  public String getTbName() {
    return this.tbName;
  }

  public DvSchema setTbName(String tbName) {
    this.tbName = tbName;
    return this;
  }

  public void unsetTbName() {
    this.tbName = null;
  }

  /** Returns true if field tbName is set (has been assigned a value) and false otherwise */
  public boolean isSetTbName() {
    return this.tbName != null;
  }

  public void setTbNameIsSet(boolean value) {
    if (!value) {
      this.tbName = null;
    }
  }

  public String getTbSchema() {
    return this.tbSchema;
  }

  public DvSchema setTbSchema(String tbSchema) {
    this.tbSchema = tbSchema;
    return this;
  }

  public void unsetTbSchema() {
    this.tbSchema = null;
  }

  /** Returns true if field tbSchema is set (has been assigned a value) and false otherwise */
  public boolean isSetTbSchema() {
    return this.tbSchema != null;
  }

  public void setTbSchemaIsSet(boolean value) {
    if (!value) {
      this.tbSchema = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DB_NAME:
      if (value == null) {
        unsetDbName();
      } else {
        setDbName((String)value);
      }
      break;

    case TB_NAME:
      if (value == null) {
        unsetTbName();
      } else {
        setTbName((String)value);
      }
      break;

    case TB_SCHEMA:
      if (value == null) {
        unsetTbSchema();
      } else {
        setTbSchema((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DB_NAME:
      return getDbName();

    case TB_NAME:
      return getTbName();

    case TB_SCHEMA:
      return getTbSchema();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DB_NAME:
      return isSetDbName();
    case TB_NAME:
      return isSetTbName();
    case TB_SCHEMA:
      return isSetTbSchema();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DvSchema)
      return this.equals((DvSchema)that);
    return false;
  }

  public boolean equals(DvSchema that) {
    if (that == null)
      return false;

    boolean this_present_dbName = true && this.isSetDbName();
    boolean that_present_dbName = true && that.isSetDbName();
    if (this_present_dbName || that_present_dbName) {
      if (!(this_present_dbName && that_present_dbName))
        return false;
      if (!this.dbName.equals(that.dbName))
        return false;
    }

    boolean this_present_tbName = true && this.isSetTbName();
    boolean that_present_tbName = true && that.isSetTbName();
    if (this_present_tbName || that_present_tbName) {
      if (!(this_present_tbName && that_present_tbName))
        return false;
      if (!this.tbName.equals(that.tbName))
        return false;
    }

    boolean this_present_tbSchema = true && this.isSetTbSchema();
    boolean that_present_tbSchema = true && that.isSetTbSchema();
    if (this_present_tbSchema || that_present_tbSchema) {
      if (!(this_present_tbSchema && that_present_tbSchema))
        return false;
      if (!this.tbSchema.equals(that.tbSchema))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_dbName = true && (isSetDbName());
    list.add(present_dbName);
    if (present_dbName)
      list.add(dbName);

    boolean present_tbName = true && (isSetTbName());
    list.add(present_tbName);
    if (present_tbName)
      list.add(tbName);

    boolean present_tbSchema = true && (isSetTbSchema());
    list.add(present_tbSchema);
    if (present_tbSchema)
      list.add(tbSchema);

    return list.hashCode();
  }

  @Override
  public int compareTo(DvSchema other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDbName()).compareTo(other.isSetDbName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDbName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dbName, other.dbName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTbName()).compareTo(other.isSetTbName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTbName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tbName, other.tbName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTbSchema()).compareTo(other.isSetTbSchema());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTbSchema()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tbSchema, other.tbSchema);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DvSchema(");
    boolean first = true;

    sb.append("dbName:");
    if (this.dbName == null) {
      sb.append("null");
    } else {
      sb.append(this.dbName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tbName:");
    if (this.tbName == null) {
      sb.append("null");
    } else {
      sb.append(this.tbName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tbSchema:");
    if (this.tbSchema == null) {
      sb.append("null");
    } else {
      sb.append(this.tbSchema);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DvSchemaStandardSchemeFactory implements SchemeFactory {
    public DvSchemaStandardScheme getScheme() {
      return new DvSchemaStandardScheme();
    }
  }

  private static class DvSchemaStandardScheme extends StandardScheme<DvSchema> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DvSchema struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dbName = iprot.readString();
              struct.setDbNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tbName = iprot.readString();
              struct.setTbNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TB_SCHEMA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tbSchema = iprot.readString();
              struct.setTbSchemaIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DvSchema struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dbName != null) {
        oprot.writeFieldBegin(DB_NAME_FIELD_DESC);
        oprot.writeString(struct.dbName);
        oprot.writeFieldEnd();
      }
      if (struct.tbName != null) {
        oprot.writeFieldBegin(TB_NAME_FIELD_DESC);
        oprot.writeString(struct.tbName);
        oprot.writeFieldEnd();
      }
      if (struct.tbSchema != null) {
        oprot.writeFieldBegin(TB_SCHEMA_FIELD_DESC);
        oprot.writeString(struct.tbSchema);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DvSchemaTupleSchemeFactory implements SchemeFactory {
    public DvSchemaTupleScheme getScheme() {
      return new DvSchemaTupleScheme();
    }
  }

  private static class DvSchemaTupleScheme extends TupleScheme<DvSchema> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DvSchema struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetDbName()) {
        optionals.set(0);
      }
      if (struct.isSetTbName()) {
        optionals.set(1);
      }
      if (struct.isSetTbSchema()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetDbName()) {
        oprot.writeString(struct.dbName);
      }
      if (struct.isSetTbName()) {
        oprot.writeString(struct.tbName);
      }
      if (struct.isSetTbSchema()) {
        oprot.writeString(struct.tbSchema);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DvSchema struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.dbName = iprot.readString();
        struct.setDbNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.tbName = iprot.readString();
        struct.setTbNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tbSchema = iprot.readString();
        struct.setTbSchemaIsSet(true);
      }
    }
  }

}

