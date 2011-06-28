/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package forma.schema;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

public class FormaValue implements TBase<FormaValue, FormaValue._Fields>, java.io.Serializable, Cloneable {
  private static final TStruct STRUCT_DESC = new TStruct("FormaValue");

  private static final TField FIRE_VALUE_FIELD_DESC = new TField("fireValue", TType.STRUCT, (short)1);
  private static final TField SHORT_DROP_FIELD_DESC = new TField("shortDrop", TType.DOUBLE, (short)2);
  private static final TField LONG_DROP_FIELD_DESC = new TField("longDrop", TType.DOUBLE, (short)3);
  private static final TField T_STAT_FIELD_DESC = new TField("tStat", TType.DOUBLE, (short)4);

  public FireTuple fireValue;
  public double shortDrop;
  public double longDrop;
  public double tStat;

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements TFieldIdEnum {
    FIRE_VALUE((short)1, "fireValue"),
    SHORT_DROP((short)2, "shortDrop"),
    LONG_DROP((short)3, "longDrop"),
    T_STAT((short)4, "tStat");

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
        case 1: // FIRE_VALUE
          return FIRE_VALUE;
        case 2: // SHORT_DROP
          return SHORT_DROP;
        case 3: // LONG_DROP
          return LONG_DROP;
        case 4: // T_STAT
          return T_STAT;
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
  private static final int __SHORTDROP_ISSET_ID = 0;
  private static final int __LONGDROP_ISSET_ID = 1;
  private static final int __TSTAT_ISSET_ID = 2;
  private BitSet __isset_bit_vector = new BitSet(3);

  public static final Map<_Fields, FieldMetaData> metaDataMap;
  static {
    Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FIRE_VALUE, new FieldMetaData("fireValue", TFieldRequirementType.OPTIONAL, 
        new StructMetaData(TType.STRUCT, FireTuple.class)));
    tmpMap.put(_Fields.SHORT_DROP, new FieldMetaData("shortDrop", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    tmpMap.put(_Fields.LONG_DROP, new FieldMetaData("longDrop", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    tmpMap.put(_Fields.T_STAT, new FieldMetaData("tStat", TFieldRequirementType.DEFAULT, 
        new FieldValueMetaData(TType.DOUBLE)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    FieldMetaData.addStructMetaDataMap(FormaValue.class, metaDataMap);
  }

  public FormaValue() {
  }

  public FormaValue(
    double shortDrop,
    double longDrop,
    double tStat)
  {
    this();
    this.shortDrop = shortDrop;
    setShortDropIsSet(true);
    this.longDrop = longDrop;
    setLongDropIsSet(true);
    this.tStat = tStat;
    setTStatIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public FormaValue(FormaValue other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetFireValue()) {
      this.fireValue = new FireTuple(other.fireValue);
    }
    this.shortDrop = other.shortDrop;
    this.longDrop = other.longDrop;
    this.tStat = other.tStat;
  }

  public FormaValue deepCopy() {
    return new FormaValue(this);
  }

  @Override
  public void clear() {
    this.fireValue = null;
    setShortDropIsSet(false);
    this.shortDrop = 0.0;
    setLongDropIsSet(false);
    this.longDrop = 0.0;
    setTStatIsSet(false);
    this.tStat = 0.0;
  }

  public FireTuple getFireValue() {
    return this.fireValue;
  }

  public FormaValue setFireValue(FireTuple fireValue) {
    this.fireValue = fireValue;
    return this;
  }

  public void unsetFireValue() {
    this.fireValue = null;
  }

  /** Returns true if field fireValue is set (has been asigned a value) and false otherwise */
  public boolean isSetFireValue() {
    return this.fireValue != null;
  }

  public void setFireValueIsSet(boolean value) {
    if (!value) {
      this.fireValue = null;
    }
  }

  public double getShortDrop() {
    return this.shortDrop;
  }

  public FormaValue setShortDrop(double shortDrop) {
    this.shortDrop = shortDrop;
    setShortDropIsSet(true);
    return this;
  }

  public void unsetShortDrop() {
    __isset_bit_vector.clear(__SHORTDROP_ISSET_ID);
  }

  /** Returns true if field shortDrop is set (has been asigned a value) and false otherwise */
  public boolean isSetShortDrop() {
    return __isset_bit_vector.get(__SHORTDROP_ISSET_ID);
  }

  public void setShortDropIsSet(boolean value) {
    __isset_bit_vector.set(__SHORTDROP_ISSET_ID, value);
  }

  public double getLongDrop() {
    return this.longDrop;
  }

  public FormaValue setLongDrop(double longDrop) {
    this.longDrop = longDrop;
    setLongDropIsSet(true);
    return this;
  }

  public void unsetLongDrop() {
    __isset_bit_vector.clear(__LONGDROP_ISSET_ID);
  }

  /** Returns true if field longDrop is set (has been asigned a value) and false otherwise */
  public boolean isSetLongDrop() {
    return __isset_bit_vector.get(__LONGDROP_ISSET_ID);
  }

  public void setLongDropIsSet(boolean value) {
    __isset_bit_vector.set(__LONGDROP_ISSET_ID, value);
  }

  public double getTStat() {
    return this.tStat;
  }

  public FormaValue setTStat(double tStat) {
    this.tStat = tStat;
    setTStatIsSet(true);
    return this;
  }

  public void unsetTStat() {
    __isset_bit_vector.clear(__TSTAT_ISSET_ID);
  }

  /** Returns true if field tStat is set (has been asigned a value) and false otherwise */
  public boolean isSetTStat() {
    return __isset_bit_vector.get(__TSTAT_ISSET_ID);
  }

  public void setTStatIsSet(boolean value) {
    __isset_bit_vector.set(__TSTAT_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FIRE_VALUE:
      if (value == null) {
        unsetFireValue();
      } else {
        setFireValue((FireTuple)value);
      }
      break;

    case SHORT_DROP:
      if (value == null) {
        unsetShortDrop();
      } else {
        setShortDrop((Double)value);
      }
      break;

    case LONG_DROP:
      if (value == null) {
        unsetLongDrop();
      } else {
        setLongDrop((Double)value);
      }
      break;

    case T_STAT:
      if (value == null) {
        unsetTStat();
      } else {
        setTStat((Double)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FIRE_VALUE:
      return getFireValue();

    case SHORT_DROP:
      return new Double(getShortDrop());

    case LONG_DROP:
      return new Double(getLongDrop());

    case T_STAT:
      return new Double(getTStat());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FIRE_VALUE:
      return isSetFireValue();
    case SHORT_DROP:
      return isSetShortDrop();
    case LONG_DROP:
      return isSetLongDrop();
    case T_STAT:
      return isSetTStat();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof FormaValue)
      return this.equals((FormaValue)that);
    return false;
  }

  public boolean equals(FormaValue that) {
    if (that == null)
      return false;

    boolean this_present_fireValue = true && this.isSetFireValue();
    boolean that_present_fireValue = true && that.isSetFireValue();
    if (this_present_fireValue || that_present_fireValue) {
      if (!(this_present_fireValue && that_present_fireValue))
        return false;
      if (!this.fireValue.equals(that.fireValue))
        return false;
    }

    boolean this_present_shortDrop = true;
    boolean that_present_shortDrop = true;
    if (this_present_shortDrop || that_present_shortDrop) {
      if (!(this_present_shortDrop && that_present_shortDrop))
        return false;
      if (this.shortDrop != that.shortDrop)
        return false;
    }

    boolean this_present_longDrop = true;
    boolean that_present_longDrop = true;
    if (this_present_longDrop || that_present_longDrop) {
      if (!(this_present_longDrop && that_present_longDrop))
        return false;
      if (this.longDrop != that.longDrop)
        return false;
    }

    boolean this_present_tStat = true;
    boolean that_present_tStat = true;
    if (this_present_tStat || that_present_tStat) {
      if (!(this_present_tStat && that_present_tStat))
        return false;
      if (this.tStat != that.tStat)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  public int compareTo(FormaValue other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    FormaValue typedOther = (FormaValue)other;

    lastComparison = Boolean.valueOf(isSetFireValue()).compareTo(typedOther.isSetFireValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFireValue()) {
      lastComparison = TBaseHelper.compareTo(this.fireValue, typedOther.fireValue);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetShortDrop()).compareTo(typedOther.isSetShortDrop());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetShortDrop()) {
      lastComparison = TBaseHelper.compareTo(this.shortDrop, typedOther.shortDrop);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLongDrop()).compareTo(typedOther.isSetLongDrop());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLongDrop()) {
      lastComparison = TBaseHelper.compareTo(this.longDrop, typedOther.longDrop);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTStat()).compareTo(typedOther.isSetTStat());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTStat()) {
      lastComparison = TBaseHelper.compareTo(this.tStat, typedOther.tStat);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(TProtocol iprot) throws TException {
    TField field;
    iprot.readStructBegin();
    while (true)
    {
      field = iprot.readFieldBegin();
      if (field.type == TType.STOP) { 
        break;
      }
      switch (field.id) {
        case 1: // FIRE_VALUE
          if (field.type == TType.STRUCT) {
            this.fireValue = new FireTuple();
            this.fireValue.read(iprot);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 2: // SHORT_DROP
          if (field.type == TType.DOUBLE) {
            this.shortDrop = iprot.readDouble();
            setShortDropIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 3: // LONG_DROP
          if (field.type == TType.DOUBLE) {
            this.longDrop = iprot.readDouble();
            setLongDropIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        case 4: // T_STAT
          if (field.type == TType.DOUBLE) {
            this.tStat = iprot.readDouble();
            setTStatIsSet(true);
          } else { 
            TProtocolUtil.skip(iprot, field.type);
          }
          break;
        default:
          TProtocolUtil.skip(iprot, field.type);
      }
      iprot.readFieldEnd();
    }
    iprot.readStructEnd();

    // check for required fields of primitive type, which can't be checked in the validate method
    validate();
  }

  public void write(TProtocol oprot) throws TException {
    validate();

    oprot.writeStructBegin(STRUCT_DESC);
    if (this.fireValue != null) {
      if (isSetFireValue()) {
        oprot.writeFieldBegin(FIRE_VALUE_FIELD_DESC);
        this.fireValue.write(oprot);
        oprot.writeFieldEnd();
      }
    }
    oprot.writeFieldBegin(SHORT_DROP_FIELD_DESC);
    oprot.writeDouble(this.shortDrop);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(LONG_DROP_FIELD_DESC);
    oprot.writeDouble(this.longDrop);
    oprot.writeFieldEnd();
    oprot.writeFieldBegin(T_STAT_FIELD_DESC);
    oprot.writeDouble(this.tStat);
    oprot.writeFieldEnd();
    oprot.writeFieldStop();
    oprot.writeStructEnd();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("FormaValue(");
    boolean first = true;

    if (isSetFireValue()) {
      sb.append("fireValue:");
      if (this.fireValue == null) {
        sb.append("null");
      } else {
        sb.append(this.fireValue);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("shortDrop:");
    sb.append(this.shortDrop);
    first = false;
    if (!first) sb.append(", ");
    sb.append("longDrop:");
    sb.append(this.longDrop);
    first = false;
    if (!first) sb.append(", ");
    sb.append("tStat:");
    sb.append(this.tStat);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
  }

}

