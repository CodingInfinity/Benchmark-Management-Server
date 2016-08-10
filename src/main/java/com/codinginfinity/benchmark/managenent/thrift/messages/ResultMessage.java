/**
 * Autogenerated by Thrift Compiler (1.0.0-dev)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.codinginfinity.benchmark.management.thrift.messages;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
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

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@Generated(value = "Autogenerated by Thrift Compiler (1.0.0-dev)", date = "2016-08-10")
public class ResultMessage implements org.apache.thrift.TBase<ResultMessage, ResultMessage._Fields>, java.io.Serializable, Cloneable, Comparable<ResultMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ResultMessage");

  private static final org.apache.thrift.protocol.TField EXPERIMENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("experimentId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField JOB_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("jobId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField MEASUREMENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("measurements", org.apache.thrift.protocol.TType.LIST, (short)3);

  private static final SchemeFactory STANDARD_SCHEME_FACTORY = new ResultMessageStandardSchemeFactory();
  private static final SchemeFactory TUPLE_SCHEME_FACTORY = new ResultMessageTupleSchemeFactory();

  public int experimentId; // required
  public int jobId; // required
  public List<Measurement> measurements; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    EXPERIMENT_ID((short)1, "experimentId"),
    JOB_ID((short)2, "jobId"),
    MEASUREMENTS((short)3, "measurements");

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
        case 1: // EXPERIMENT_ID
          return EXPERIMENT_ID;
        case 2: // JOB_ID
          return JOB_ID;
        case 3: // MEASUREMENTS
          return MEASUREMENTS;
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
  private static final int __EXPERIMENTID_ISSET_ID = 0;
  private static final int __JOBID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.EXPERIMENT_ID, new org.apache.thrift.meta_data.FieldMetaData("experimentId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.JOB_ID, new org.apache.thrift.meta_data.FieldMetaData("jobId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MEASUREMENTS, new org.apache.thrift.meta_data.FieldMetaData("measurements", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "Measurement"))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ResultMessage.class, metaDataMap);
  }

  public ResultMessage() {
  }

  public ResultMessage(
    int experimentId,
    int jobId,
    List<Measurement> measurements)
  {
    this();
    this.experimentId = experimentId;
    setExperimentIdIsSet(true);
    this.jobId = jobId;
    setJobIdIsSet(true);
    this.measurements = measurements;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ResultMessage(ResultMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    this.experimentId = other.experimentId;
    this.jobId = other.jobId;
    if (other.isSetMeasurements()) {
      List<Measurement> __this__measurements = new ArrayList<Measurement>(other.measurements.size());
      for (Measurement other_element : other.measurements) {
        __this__measurements.add(other_element);
      }
      this.measurements = __this__measurements;
    }
  }

  public ResultMessage deepCopy() {
    return new ResultMessage(this);
  }

  @Override
  public void clear() {
    setExperimentIdIsSet(false);
    this.experimentId = 0;
    setJobIdIsSet(false);
    this.jobId = 0;
    this.measurements = null;
  }

  public int getExperimentId() {
    return this.experimentId;
  }

  public ResultMessage setExperimentId(int experimentId) {
    this.experimentId = experimentId;
    setExperimentIdIsSet(true);
    return this;
  }

  public void unsetExperimentId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EXPERIMENTID_ISSET_ID);
  }

  /** Returns true if field experimentId is set (has been assigned a value) and false otherwise */
  public boolean isSetExperimentId() {
    return EncodingUtils.testBit(__isset_bitfield, __EXPERIMENTID_ISSET_ID);
  }

  public void setExperimentIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EXPERIMENTID_ISSET_ID, value);
  }

  public int getJobId() {
    return this.jobId;
  }

  public ResultMessage setJobId(int jobId) {
    this.jobId = jobId;
    setJobIdIsSet(true);
    return this;
  }

  public void unsetJobId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __JOBID_ISSET_ID);
  }

  /** Returns true if field jobId is set (has been assigned a value) and false otherwise */
  public boolean isSetJobId() {
    return EncodingUtils.testBit(__isset_bitfield, __JOBID_ISSET_ID);
  }

  public void setJobIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __JOBID_ISSET_ID, value);
  }

  public int getMeasurementsSize() {
    return (this.measurements == null) ? 0 : this.measurements.size();
  }

  public java.util.Iterator<Measurement> getMeasurementsIterator() {
    return (this.measurements == null) ? null : this.measurements.iterator();
  }

  public void addToMeasurements(Measurement elem) {
    if (this.measurements == null) {
      this.measurements = new ArrayList<Measurement>();
    }
    this.measurements.add(elem);
  }

  public List<Measurement> getMeasurements() {
    return this.measurements;
  }

  public ResultMessage setMeasurements(List<Measurement> measurements) {
    this.measurements = measurements;
    return this;
  }

  public void unsetMeasurements() {
    this.measurements = null;
  }

  /** Returns true if field measurements is set (has been assigned a value) and false otherwise */
  public boolean isSetMeasurements() {
    return this.measurements != null;
  }

  public void setMeasurementsIsSet(boolean value) {
    if (!value) {
      this.measurements = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case EXPERIMENT_ID:
      if (value == null) {
        unsetExperimentId();
      } else {
        setExperimentId((Integer)value);
      }
      break;

    case JOB_ID:
      if (value == null) {
        unsetJobId();
      } else {
        setJobId((Integer)value);
      }
      break;

    case MEASUREMENTS:
      if (value == null) {
        unsetMeasurements();
      } else {
        setMeasurements((List<Measurement>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case EXPERIMENT_ID:
      return getExperimentId();

    case JOB_ID:
      return getJobId();

    case MEASUREMENTS:
      return getMeasurements();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case EXPERIMENT_ID:
      return isSetExperimentId();
    case JOB_ID:
      return isSetJobId();
    case MEASUREMENTS:
      return isSetMeasurements();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ResultMessage)
      return this.equals((ResultMessage)that);
    return false;
  }

  public boolean equals(ResultMessage that) {
    if (that == null)
      return false;

    boolean this_present_experimentId = true;
    boolean that_present_experimentId = true;
    if (this_present_experimentId || that_present_experimentId) {
      if (!(this_present_experimentId && that_present_experimentId))
        return false;
      if (this.experimentId != that.experimentId)
        return false;
    }

    boolean this_present_jobId = true;
    boolean that_present_jobId = true;
    if (this_present_jobId || that_present_jobId) {
      if (!(this_present_jobId && that_present_jobId))
        return false;
      if (this.jobId != that.jobId)
        return false;
    }

    boolean this_present_measurements = true && this.isSetMeasurements();
    boolean that_present_measurements = true && that.isSetMeasurements();
    if (this_present_measurements || that_present_measurements) {
      if (!(this_present_measurements && that_present_measurements))
        return false;
      if (!this.measurements.equals(that.measurements))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + experimentId;

    hashCode = hashCode * 8191 + jobId;

    hashCode = hashCode * 8191 + ((isSetMeasurements()) ? 131071 : 524287);
    if (isSetMeasurements())
      hashCode = hashCode * 8191 + measurements.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ResultMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetExperimentId()).compareTo(other.isSetExperimentId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExperimentId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.experimentId, other.experimentId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetJobId()).compareTo(other.isSetJobId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobId, other.jobId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMeasurements()).compareTo(other.isSetMeasurements());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMeasurements()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.measurements, other.measurements);
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
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ResultMessage(");
    boolean first = true;

    sb.append("experimentId:");
    sb.append(this.experimentId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("jobId:");
    sb.append(this.jobId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("measurements:");
    if (this.measurements == null) {
      sb.append("null");
    } else {
      sb.append(this.measurements);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ResultMessageStandardSchemeFactory implements SchemeFactory {
    public ResultMessageStandardScheme getScheme() {
      return new ResultMessageStandardScheme();
    }
  }

  private static class ResultMessageStandardScheme extends StandardScheme<ResultMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ResultMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // EXPERIMENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.experimentId = iprot.readI32();
              struct.setExperimentIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // JOB_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.jobId = iprot.readI32();
              struct.setJobIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MEASUREMENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.measurements = new ArrayList<Measurement>(_list0.size);
                Measurement _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new Measurement();
                  _elem1.read(iprot);
                  struct.measurements.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setMeasurementsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ResultMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(EXPERIMENT_ID_FIELD_DESC);
      oprot.writeI32(struct.experimentId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(JOB_ID_FIELD_DESC);
      oprot.writeI32(struct.jobId);
      oprot.writeFieldEnd();
      if (struct.measurements != null) {
        oprot.writeFieldBegin(MEASUREMENTS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.measurements.size()));
          for (Measurement _iter3 : struct.measurements)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ResultMessageTupleSchemeFactory implements SchemeFactory {
    public ResultMessageTupleScheme getScheme() {
      return new ResultMessageTupleScheme();
    }
  }

  private static class ResultMessageTupleScheme extends TupleScheme<ResultMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ResultMessage struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetExperimentId()) {
        optionals.set(0);
      }
      if (struct.isSetJobId()) {
        optionals.set(1);
      }
      if (struct.isSetMeasurements()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetExperimentId()) {
        oprot.writeI32(struct.experimentId);
      }
      if (struct.isSetJobId()) {
        oprot.writeI32(struct.jobId);
      }
      if (struct.isSetMeasurements()) {
        {
          oprot.writeI32(struct.measurements.size());
          for (Measurement _iter4 : struct.measurements)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ResultMessage struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.experimentId = iprot.readI32();
        struct.setExperimentIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.jobId = iprot.readI32();
        struct.setJobIdIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.measurements = new ArrayList<Measurement>(_list5.size);
          Measurement _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new Measurement();
            _elem6.read(iprot);
            struct.measurements.add(_elem6);
          }
        }
        struct.setMeasurementsIsSet(true);
      }
    }
  }

  private static <S extends IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

