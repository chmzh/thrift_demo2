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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-01-27")
public class RoleLoginInfo implements org.apache.thrift.TBase<RoleLoginInfo, RoleLoginInfo._Fields>, java.io.Serializable, Cloneable, Comparable<RoleLoginInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RoleLoginInfo");

  private static final org.apache.thrift.protocol.TField ROLE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("roleId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ROLE_LEVEL_FIELD_DESC = new org.apache.thrift.protocol.TField("roleLevel", org.apache.thrift.protocol.TType.I16, (short)2);
  private static final org.apache.thrift.protocol.TField CREATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("createTime", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new RoleLoginInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new RoleLoginInfoTupleSchemeFactory());
  }

  public String roleId; // required
  public short roleLevel; // required
  public long createTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROLE_ID((short)1, "roleId"),
    ROLE_LEVEL((short)2, "roleLevel"),
    CREATE_TIME((short)3, "createTime");

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
        case 1: // ROLE_ID
          return ROLE_ID;
        case 2: // ROLE_LEVEL
          return ROLE_LEVEL;
        case 3: // CREATE_TIME
          return CREATE_TIME;
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
  private static final int __ROLELEVEL_ISSET_ID = 0;
  private static final int __CREATETIME_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROLE_ID, new org.apache.thrift.meta_data.FieldMetaData("roleId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ROLE_LEVEL, new org.apache.thrift.meta_data.FieldMetaData("roleLevel", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16        , "int16")));
    tmpMap.put(_Fields.CREATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("createTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "int64")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RoleLoginInfo.class, metaDataMap);
  }

  public RoleLoginInfo() {
  }

  public RoleLoginInfo(
    String roleId,
    short roleLevel,
    long createTime)
  {
    this();
    this.roleId = roleId;
    this.roleLevel = roleLevel;
    setRoleLevelIsSet(true);
    this.createTime = createTime;
    setCreateTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RoleLoginInfo(RoleLoginInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetRoleId()) {
      this.roleId = other.roleId;
    }
    this.roleLevel = other.roleLevel;
    this.createTime = other.createTime;
  }

  public RoleLoginInfo deepCopy() {
    return new RoleLoginInfo(this);
  }

  @Override
  public void clear() {
    this.roleId = null;
    setRoleLevelIsSet(false);
    this.roleLevel = 0;
    setCreateTimeIsSet(false);
    this.createTime = 0;
  }

  public String getRoleId() {
    return this.roleId;
  }

  public RoleLoginInfo setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public void unsetRoleId() {
    this.roleId = null;
  }

  /** Returns true if field roleId is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleId() {
    return this.roleId != null;
  }

  public void setRoleIdIsSet(boolean value) {
    if (!value) {
      this.roleId = null;
    }
  }

  public short getRoleLevel() {
    return this.roleLevel;
  }

  public RoleLoginInfo setRoleLevel(short roleLevel) {
    this.roleLevel = roleLevel;
    setRoleLevelIsSet(true);
    return this;
  }

  public void unsetRoleLevel() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ROLELEVEL_ISSET_ID);
  }

  /** Returns true if field roleLevel is set (has been assigned a value) and false otherwise */
  public boolean isSetRoleLevel() {
    return EncodingUtils.testBit(__isset_bitfield, __ROLELEVEL_ISSET_ID);
  }

  public void setRoleLevelIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ROLELEVEL_ISSET_ID, value);
  }

  public long getCreateTime() {
    return this.createTime;
  }

  public RoleLoginInfo setCreateTime(long createTime) {
    this.createTime = createTime;
    setCreateTimeIsSet(true);
    return this;
  }

  public void unsetCreateTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CREATETIME_ISSET_ID);
  }

  /** Returns true if field createTime is set (has been assigned a value) and false otherwise */
  public boolean isSetCreateTime() {
    return EncodingUtils.testBit(__isset_bitfield, __CREATETIME_ISSET_ID);
  }

  public void setCreateTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CREATETIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ROLE_ID:
      if (value == null) {
        unsetRoleId();
      } else {
        setRoleId((String)value);
      }
      break;

    case ROLE_LEVEL:
      if (value == null) {
        unsetRoleLevel();
      } else {
        setRoleLevel((Short)value);
      }
      break;

    case CREATE_TIME:
      if (value == null) {
        unsetCreateTime();
      } else {
        setCreateTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ROLE_ID:
      return getRoleId();

    case ROLE_LEVEL:
      return getRoleLevel();

    case CREATE_TIME:
      return getCreateTime();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ROLE_ID:
      return isSetRoleId();
    case ROLE_LEVEL:
      return isSetRoleLevel();
    case CREATE_TIME:
      return isSetCreateTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RoleLoginInfo)
      return this.equals((RoleLoginInfo)that);
    return false;
  }

  public boolean equals(RoleLoginInfo that) {
    if (that == null)
      return false;

    boolean this_present_roleId = true && this.isSetRoleId();
    boolean that_present_roleId = true && that.isSetRoleId();
    if (this_present_roleId || that_present_roleId) {
      if (!(this_present_roleId && that_present_roleId))
        return false;
      if (!this.roleId.equals(that.roleId))
        return false;
    }

    boolean this_present_roleLevel = true;
    boolean that_present_roleLevel = true;
    if (this_present_roleLevel || that_present_roleLevel) {
      if (!(this_present_roleLevel && that_present_roleLevel))
        return false;
      if (this.roleLevel != that.roleLevel)
        return false;
    }

    boolean this_present_createTime = true;
    boolean that_present_createTime = true;
    if (this_present_createTime || that_present_createTime) {
      if (!(this_present_createTime && that_present_createTime))
        return false;
      if (this.createTime != that.createTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_roleId = true && (isSetRoleId());
    list.add(present_roleId);
    if (present_roleId)
      list.add(roleId);

    boolean present_roleLevel = true;
    list.add(present_roleLevel);
    if (present_roleLevel)
      list.add(roleLevel);

    boolean present_createTime = true;
    list.add(present_createTime);
    if (present_createTime)
      list.add(createTime);

    return list.hashCode();
  }

  @Override
  public int compareTo(RoleLoginInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRoleId()).compareTo(other.isSetRoleId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleId, other.roleId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRoleLevel()).compareTo(other.isSetRoleLevel());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoleLevel()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roleLevel, other.roleLevel);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreateTime()).compareTo(other.isSetCreateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createTime, other.createTime);
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
    StringBuilder sb = new StringBuilder("RoleLoginInfo(");
    boolean first = true;

    sb.append("roleId:");
    if (this.roleId == null) {
      sb.append("null");
    } else {
      sb.append(this.roleId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("roleLevel:");
    sb.append(this.roleLevel);
    first = false;
    if (!first) sb.append(", ");
    sb.append("createTime:");
    sb.append(this.createTime);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RoleLoginInfoStandardSchemeFactory implements SchemeFactory {
    public RoleLoginInfoStandardScheme getScheme() {
      return new RoleLoginInfoStandardScheme();
    }
  }

  private static class RoleLoginInfoStandardScheme extends StandardScheme<RoleLoginInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RoleLoginInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROLE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.roleId = iprot.readString();
              struct.setRoleIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ROLE_LEVEL
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.roleLevel = iprot.readI16();
              struct.setRoleLevelIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CREATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTime = iprot.readI64();
              struct.setCreateTimeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RoleLoginInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.roleId != null) {
        oprot.writeFieldBegin(ROLE_ID_FIELD_DESC);
        oprot.writeString(struct.roleId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(ROLE_LEVEL_FIELD_DESC);
      oprot.writeI16(struct.roleLevel);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CREATE_TIME_FIELD_DESC);
      oprot.writeI64(struct.createTime);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RoleLoginInfoTupleSchemeFactory implements SchemeFactory {
    public RoleLoginInfoTupleScheme getScheme() {
      return new RoleLoginInfoTupleScheme();
    }
  }

  private static class RoleLoginInfoTupleScheme extends TupleScheme<RoleLoginInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RoleLoginInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRoleId()) {
        optionals.set(0);
      }
      if (struct.isSetRoleLevel()) {
        optionals.set(1);
      }
      if (struct.isSetCreateTime()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRoleId()) {
        oprot.writeString(struct.roleId);
      }
      if (struct.isSetRoleLevel()) {
        oprot.writeI16(struct.roleLevel);
      }
      if (struct.isSetCreateTime()) {
        oprot.writeI64(struct.createTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RoleLoginInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.roleId = iprot.readString();
        struct.setRoleIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.roleLevel = iprot.readI16();
        struct.setRoleLevelIsSet(true);
      }
      if (incoming.get(2)) {
        struct.createTime = iprot.readI64();
        struct.setCreateTimeIsSet(true);
      }
    }
  }

}
