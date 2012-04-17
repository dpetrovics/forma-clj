/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package forma.schema;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
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

public class DataValue extends org.apache.thrift.TUnion<DataValue, DataValue._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DataValue");
  private static final org.apache.thrift.protocol.TField LONG_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("longVal", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField LONGS_FIELD_DESC = new org.apache.thrift.protocol.TField("longs", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField DOUBLE_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("doubleVal", org.apache.thrift.protocol.TType.DOUBLE, (short)3);
  private static final org.apache.thrift.protocol.TField DOUBLES_FIELD_DESC = new org.apache.thrift.protocol.TField("doubles", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField FIRE_VAL_FIELD_DESC = new org.apache.thrift.protocol.TField("fireVal", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField TIME_SERIES_FIELD_DESC = new org.apache.thrift.protocol.TField("timeSeries", org.apache.thrift.protocol.TType.STRUCT, (short)6);
  private static final org.apache.thrift.protocol.TField FIRE_SERIES_FIELD_DESC = new org.apache.thrift.protocol.TField("fireSeries", org.apache.thrift.protocol.TType.STRUCT, (short)7);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    LONG_VAL((short)1, "longVal"),
    LONGS((short)2, "longs"),
    DOUBLE_VAL((short)3, "doubleVal"),
    DOUBLES((short)4, "doubles"),
    FIRE_VAL((short)5, "fireVal"),
    TIME_SERIES((short)6, "timeSeries"),
    FIRE_SERIES((short)7, "fireSeries");

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
        case 1: // LONG_VAL
          return LONG_VAL;
        case 2: // LONGS
          return LONGS;
        case 3: // DOUBLE_VAL
          return DOUBLE_VAL;
        case 4: // DOUBLES
          return DOUBLES;
        case 5: // FIRE_VAL
          return FIRE_VAL;
        case 6: // TIME_SERIES
          return TIME_SERIES;
        case 7: // FIRE_SERIES
          return FIRE_SERIES;
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

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.LONG_VAL, new org.apache.thrift.meta_data.FieldMetaData("longVal", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LONGS, new org.apache.thrift.meta_data.FieldMetaData("longs", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, LongArray.class)));
    tmpMap.put(_Fields.DOUBLE_VAL, new org.apache.thrift.meta_data.FieldMetaData("doubleVal", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.DOUBLES, new org.apache.thrift.meta_data.FieldMetaData("doubles", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DoubleArray.class)));
    tmpMap.put(_Fields.FIRE_VAL, new org.apache.thrift.meta_data.FieldMetaData("fireVal", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, FireTuple.class)));
    tmpMap.put(_Fields.TIME_SERIES, new org.apache.thrift.meta_data.FieldMetaData("timeSeries", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TimeSeries.class)));
    tmpMap.put(_Fields.FIRE_SERIES, new org.apache.thrift.meta_data.FieldMetaData("fireSeries", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, FireSeries.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DataValue.class, metaDataMap);
  }

  public DataValue() {
    super();
  }

  public DataValue(_Fields setField, Object value) {
    super(setField, value);
  }

  public DataValue(DataValue other) {
    super(other);
  }
  public DataValue deepCopy() {
    return new DataValue(this);
  }

  public static DataValue longVal(long value) {
    DataValue x = new DataValue();
    x.setLongVal(value);
    return x;
  }

  public static DataValue longs(LongArray value) {
    DataValue x = new DataValue();
    x.setLongs(value);
    return x;
  }

  public static DataValue doubleVal(double value) {
    DataValue x = new DataValue();
    x.setDoubleVal(value);
    return x;
  }

  public static DataValue doubles(DoubleArray value) {
    DataValue x = new DataValue();
    x.setDoubles(value);
    return x;
  }

  public static DataValue fireVal(FireTuple value) {
    DataValue x = new DataValue();
    x.setFireVal(value);
    return x;
  }

  public static DataValue timeSeries(TimeSeries value) {
    DataValue x = new DataValue();
    x.setTimeSeries(value);
    return x;
  }

  public static DataValue fireSeries(FireSeries value) {
    DataValue x = new DataValue();
    x.setFireSeries(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case LONG_VAL:
        if (value instanceof Long) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Long for field 'longVal', but got " + value.getClass().getSimpleName());
      case LONGS:
        if (value instanceof LongArray) {
          break;
        }
        throw new ClassCastException("Was expecting value of type LongArray for field 'longs', but got " + value.getClass().getSimpleName());
      case DOUBLE_VAL:
        if (value instanceof Double) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Double for field 'doubleVal', but got " + value.getClass().getSimpleName());
      case DOUBLES:
        if (value instanceof DoubleArray) {
          break;
        }
        throw new ClassCastException("Was expecting value of type DoubleArray for field 'doubles', but got " + value.getClass().getSimpleName());
      case FIRE_VAL:
        if (value instanceof FireTuple) {
          break;
        }
        throw new ClassCastException("Was expecting value of type FireTuple for field 'fireVal', but got " + value.getClass().getSimpleName());
      case TIME_SERIES:
        if (value instanceof TimeSeries) {
          break;
        }
        throw new ClassCastException("Was expecting value of type TimeSeries for field 'timeSeries', but got " + value.getClass().getSimpleName());
      case FIRE_SERIES:
        if (value instanceof FireSeries) {
          break;
        }
        throw new ClassCastException("Was expecting value of type FireSeries for field 'fireSeries', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case LONG_VAL:
          if (field.type == LONG_VAL_FIELD_DESC.type) {
            Long longVal;
            longVal = iprot.readI64();
            return longVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case LONGS:
          if (field.type == LONGS_FIELD_DESC.type) {
            LongArray longs;
            longs = new LongArray();
            longs.read(iprot);
            return longs;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DOUBLE_VAL:
          if (field.type == DOUBLE_VAL_FIELD_DESC.type) {
            Double doubleVal;
            doubleVal = iprot.readDouble();
            return doubleVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case DOUBLES:
          if (field.type == DOUBLES_FIELD_DESC.type) {
            DoubleArray doubles;
            doubles = new DoubleArray();
            doubles.read(iprot);
            return doubles;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case FIRE_VAL:
          if (field.type == FIRE_VAL_FIELD_DESC.type) {
            FireTuple fireVal;
            fireVal = new FireTuple();
            fireVal.read(iprot);
            return fireVal;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case TIME_SERIES:
          if (field.type == TIME_SERIES_FIELD_DESC.type) {
            TimeSeries timeSeries;
            timeSeries = new TimeSeries();
            timeSeries.read(iprot);
            return timeSeries;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case FIRE_SERIES:
          if (field.type == FIRE_SERIES_FIELD_DESC.type) {
            FireSeries fireSeries;
            fireSeries = new FireSeries();
            fireSeries.read(iprot);
            return fireSeries;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case LONG_VAL:
        Long longVal = (Long)value_;
        oprot.writeI64(longVal);
        return;
      case LONGS:
        LongArray longs = (LongArray)value_;
        longs.write(oprot);
        return;
      case DOUBLE_VAL:
        Double doubleVal = (Double)value_;
        oprot.writeDouble(doubleVal);
        return;
      case DOUBLES:
        DoubleArray doubles = (DoubleArray)value_;
        doubles.write(oprot);
        return;
      case FIRE_VAL:
        FireTuple fireVal = (FireTuple)value_;
        fireVal.write(oprot);
        return;
      case TIME_SERIES:
        TimeSeries timeSeries = (TimeSeries)value_;
        timeSeries.write(oprot);
        return;
      case FIRE_SERIES:
        FireSeries fireSeries = (FireSeries)value_;
        fireSeries.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case LONG_VAL:
          Long longVal;
          longVal = iprot.readI64();
          return longVal;
        case LONGS:
          LongArray longs;
          longs = new LongArray();
          longs.read(iprot);
          return longs;
        case DOUBLE_VAL:
          Double doubleVal;
          doubleVal = iprot.readDouble();
          return doubleVal;
        case DOUBLES:
          DoubleArray doubles;
          doubles = new DoubleArray();
          doubles.read(iprot);
          return doubles;
        case FIRE_VAL:
          FireTuple fireVal;
          fireVal = new FireTuple();
          fireVal.read(iprot);
          return fireVal;
        case TIME_SERIES:
          TimeSeries timeSeries;
          timeSeries = new TimeSeries();
          timeSeries.read(iprot);
          return timeSeries;
        case FIRE_SERIES:
          FireSeries fireSeries;
          fireSeries = new FireSeries();
          fireSeries.read(iprot);
          return fireSeries;
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      return null;
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case LONG_VAL:
        Long longVal = (Long)value_;
        oprot.writeI64(longVal);
        return;
      case LONGS:
        LongArray longs = (LongArray)value_;
        longs.write(oprot);
        return;
      case DOUBLE_VAL:
        Double doubleVal = (Double)value_;
        oprot.writeDouble(doubleVal);
        return;
      case DOUBLES:
        DoubleArray doubles = (DoubleArray)value_;
        doubles.write(oprot);
        return;
      case FIRE_VAL:
        FireTuple fireVal = (FireTuple)value_;
        fireVal.write(oprot);
        return;
      case TIME_SERIES:
        TimeSeries timeSeries = (TimeSeries)value_;
        timeSeries.write(oprot);
        return;
      case FIRE_SERIES:
        FireSeries fireSeries = (FireSeries)value_;
        fireSeries.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case LONG_VAL:
        return LONG_VAL_FIELD_DESC;
      case LONGS:
        return LONGS_FIELD_DESC;
      case DOUBLE_VAL:
        return DOUBLE_VAL_FIELD_DESC;
      case DOUBLES:
        return DOUBLES_FIELD_DESC;
      case FIRE_VAL:
        return FIRE_VAL_FIELD_DESC;
      case TIME_SERIES:
        return TIME_SERIES_FIELD_DESC;
      case FIRE_SERIES:
        return FIRE_SERIES_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public long getLongVal() {
    if (getSetField() == _Fields.LONG_VAL) {
      return (Long)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'longVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setLongVal(long value) {
    setField_ = _Fields.LONG_VAL;
    value_ = value;
  }

  public LongArray getLongs() {
    if (getSetField() == _Fields.LONGS) {
      return (LongArray)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'longs' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setLongs(LongArray value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.LONGS;
    value_ = value;
  }

  public double getDoubleVal() {
    if (getSetField() == _Fields.DOUBLE_VAL) {
      return (Double)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'doubleVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDoubleVal(double value) {
    setField_ = _Fields.DOUBLE_VAL;
    value_ = value;
  }

  public DoubleArray getDoubles() {
    if (getSetField() == _Fields.DOUBLES) {
      return (DoubleArray)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'doubles' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setDoubles(DoubleArray value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.DOUBLES;
    value_ = value;
  }

  public FireTuple getFireVal() {
    if (getSetField() == _Fields.FIRE_VAL) {
      return (FireTuple)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'fireVal' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setFireVal(FireTuple value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.FIRE_VAL;
    value_ = value;
  }

  public TimeSeries getTimeSeries() {
    if (getSetField() == _Fields.TIME_SERIES) {
      return (TimeSeries)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'timeSeries' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setTimeSeries(TimeSeries value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.TIME_SERIES;
    value_ = value;
  }

  public FireSeries getFireSeries() {
    if (getSetField() == _Fields.FIRE_SERIES) {
      return (FireSeries)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'fireSeries' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setFireSeries(FireSeries value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.FIRE_SERIES;
    value_ = value;
  }

  public boolean isSetLongVal() {
    return setField_ == _Fields.LONG_VAL;
  }


  public boolean isSetLongs() {
    return setField_ == _Fields.LONGS;
  }


  public boolean isSetDoubleVal() {
    return setField_ == _Fields.DOUBLE_VAL;
  }


  public boolean isSetDoubles() {
    return setField_ == _Fields.DOUBLES;
  }


  public boolean isSetFireVal() {
    return setField_ == _Fields.FIRE_VAL;
  }


  public boolean isSetTimeSeries() {
    return setField_ == _Fields.TIME_SERIES;
  }


  public boolean isSetFireSeries() {
    return setField_ == _Fields.FIRE_SERIES;
  }


  public boolean equals(Object other) {
    if (other instanceof DataValue) {
      return equals((DataValue)other);
    } else {
      return false;
    }
  }

  public boolean equals(DataValue other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(DataValue other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    HashCodeBuilder hcb = new HashCodeBuilder();
    hcb.append(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      hcb.append(setField.getThriftFieldId());
      Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        hcb.append(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        hcb.append(value);
      }
    }
    return hcb.toHashCode();
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


}
