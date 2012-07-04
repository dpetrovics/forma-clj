/**
 * Autogenerated by Thrift Compiler (0.8.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package forma.thrift;

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

public class ModisChunkLocation implements org.apache.thrift.TBase<ModisChunkLocation, ModisChunkLocation._Fields>, java.io.Serializable, Cloneable {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ModisChunkLocation");

  private static final org.apache.thrift.protocol.TField RESOLUTION_FIELD_DESC = new org.apache.thrift.protocol.TField("resolution", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField TILE_H_FIELD_DESC = new org.apache.thrift.protocol.TField("tileH", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField TILE_V_FIELD_DESC = new org.apache.thrift.protocol.TField("tileV", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField CHUNK_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("chunkID", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField CHUNK_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("chunkSize", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ModisChunkLocationStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ModisChunkLocationTupleSchemeFactory());
  }

  public String resolution; // required
  public int tileH; // required
  public int tileV; // required
  public int chunkID; // required
  public int chunkSize; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESOLUTION((short)1, "resolution"),
    TILE_H((short)2, "tileH"),
    TILE_V((short)3, "tileV"),
    CHUNK_ID((short)4, "chunkID"),
    CHUNK_SIZE((short)5, "chunkSize");

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
        case 1: // RESOLUTION
          return RESOLUTION;
        case 2: // TILE_H
          return TILE_H;
        case 3: // TILE_V
          return TILE_V;
        case 4: // CHUNK_ID
          return CHUNK_ID;
        case 5: // CHUNK_SIZE
          return CHUNK_SIZE;
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
  private static final int __TILEH_ISSET_ID = 0;
  private static final int __TILEV_ISSET_ID = 1;
  private static final int __CHUNKID_ISSET_ID = 2;
  private static final int __CHUNKSIZE_ISSET_ID = 3;
  private BitSet __isset_bit_vector = new BitSet(4);
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESOLUTION, new org.apache.thrift.meta_data.FieldMetaData("resolution", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TILE_H, new org.apache.thrift.meta_data.FieldMetaData("tileH", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TILE_V, new org.apache.thrift.meta_data.FieldMetaData("tileV", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CHUNK_ID, new org.apache.thrift.meta_data.FieldMetaData("chunkID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CHUNK_SIZE, new org.apache.thrift.meta_data.FieldMetaData("chunkSize", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ModisChunkLocation.class, metaDataMap);
  }

  public ModisChunkLocation() {
  }

  public ModisChunkLocation(
    String resolution,
    int tileH,
    int tileV,
    int chunkID,
    int chunkSize)
  {
    this();
    this.resolution = resolution;
    this.tileH = tileH;
    setTileHIsSet(true);
    this.tileV = tileV;
    setTileVIsSet(true);
    this.chunkID = chunkID;
    setChunkIDIsSet(true);
    this.chunkSize = chunkSize;
    setChunkSizeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ModisChunkLocation(ModisChunkLocation other) {
    __isset_bit_vector.clear();
    __isset_bit_vector.or(other.__isset_bit_vector);
    if (other.isSetResolution()) {
      this.resolution = other.resolution;
    }
    this.tileH = other.tileH;
    this.tileV = other.tileV;
    this.chunkID = other.chunkID;
    this.chunkSize = other.chunkSize;
  }

  public ModisChunkLocation deepCopy() {
    return new ModisChunkLocation(this);
  }

  @Override
  public void clear() {
    this.resolution = null;
    setTileHIsSet(false);
    this.tileH = 0;
    setTileVIsSet(false);
    this.tileV = 0;
    setChunkIDIsSet(false);
    this.chunkID = 0;
    setChunkSizeIsSet(false);
    this.chunkSize = 0;
  }

  public String getResolution() {
    return this.resolution;
  }

  public ModisChunkLocation setResolution(String resolution) {
    this.resolution = resolution;
    return this;
  }

  public void unsetResolution() {
    this.resolution = null;
  }

  /** Returns true if field resolution is set (has been assigned a value) and false otherwise */
  public boolean isSetResolution() {
    return this.resolution != null;
  }

  public void setResolutionIsSet(boolean value) {
    if (!value) {
      this.resolution = null;
    }
  }

  public int getTileH() {
    return this.tileH;
  }

  public ModisChunkLocation setTileH(int tileH) {
    this.tileH = tileH;
    setTileHIsSet(true);
    return this;
  }

  public void unsetTileH() {
    __isset_bit_vector.clear(__TILEH_ISSET_ID);
  }

  /** Returns true if field tileH is set (has been assigned a value) and false otherwise */
  public boolean isSetTileH() {
    return __isset_bit_vector.get(__TILEH_ISSET_ID);
  }

  public void setTileHIsSet(boolean value) {
    __isset_bit_vector.set(__TILEH_ISSET_ID, value);
  }

  public int getTileV() {
    return this.tileV;
  }

  public ModisChunkLocation setTileV(int tileV) {
    this.tileV = tileV;
    setTileVIsSet(true);
    return this;
  }

  public void unsetTileV() {
    __isset_bit_vector.clear(__TILEV_ISSET_ID);
  }

  /** Returns true if field tileV is set (has been assigned a value) and false otherwise */
  public boolean isSetTileV() {
    return __isset_bit_vector.get(__TILEV_ISSET_ID);
  }

  public void setTileVIsSet(boolean value) {
    __isset_bit_vector.set(__TILEV_ISSET_ID, value);
  }

  public int getChunkID() {
    return this.chunkID;
  }

  public ModisChunkLocation setChunkID(int chunkID) {
    this.chunkID = chunkID;
    setChunkIDIsSet(true);
    return this;
  }

  public void unsetChunkID() {
    __isset_bit_vector.clear(__CHUNKID_ISSET_ID);
  }

  /** Returns true if field chunkID is set (has been assigned a value) and false otherwise */
  public boolean isSetChunkID() {
    return __isset_bit_vector.get(__CHUNKID_ISSET_ID);
  }

  public void setChunkIDIsSet(boolean value) {
    __isset_bit_vector.set(__CHUNKID_ISSET_ID, value);
  }

  public int getChunkSize() {
    return this.chunkSize;
  }

  public ModisChunkLocation setChunkSize(int chunkSize) {
    this.chunkSize = chunkSize;
    setChunkSizeIsSet(true);
    return this;
  }

  public void unsetChunkSize() {
    __isset_bit_vector.clear(__CHUNKSIZE_ISSET_ID);
  }

  /** Returns true if field chunkSize is set (has been assigned a value) and false otherwise */
  public boolean isSetChunkSize() {
    return __isset_bit_vector.get(__CHUNKSIZE_ISSET_ID);
  }

  public void setChunkSizeIsSet(boolean value) {
    __isset_bit_vector.set(__CHUNKSIZE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RESOLUTION:
      if (value == null) {
        unsetResolution();
      } else {
        setResolution((String)value);
      }
      break;

    case TILE_H:
      if (value == null) {
        unsetTileH();
      } else {
        setTileH((Integer)value);
      }
      break;

    case TILE_V:
      if (value == null) {
        unsetTileV();
      } else {
        setTileV((Integer)value);
      }
      break;

    case CHUNK_ID:
      if (value == null) {
        unsetChunkID();
      } else {
        setChunkID((Integer)value);
      }
      break;

    case CHUNK_SIZE:
      if (value == null) {
        unsetChunkSize();
      } else {
        setChunkSize((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RESOLUTION:
      return getResolution();

    case TILE_H:
      return Integer.valueOf(getTileH());

    case TILE_V:
      return Integer.valueOf(getTileV());

    case CHUNK_ID:
      return Integer.valueOf(getChunkID());

    case CHUNK_SIZE:
      return Integer.valueOf(getChunkSize());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RESOLUTION:
      return isSetResolution();
    case TILE_H:
      return isSetTileH();
    case TILE_V:
      return isSetTileV();
    case CHUNK_ID:
      return isSetChunkID();
    case CHUNK_SIZE:
      return isSetChunkSize();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ModisChunkLocation)
      return this.equals((ModisChunkLocation)that);
    return false;
  }

  public boolean equals(ModisChunkLocation that) {
    if (that == null)
      return false;

    boolean this_present_resolution = true && this.isSetResolution();
    boolean that_present_resolution = true && that.isSetResolution();
    if (this_present_resolution || that_present_resolution) {
      if (!(this_present_resolution && that_present_resolution))
        return false;
      if (!this.resolution.equals(that.resolution))
        return false;
    }

    boolean this_present_tileH = true;
    boolean that_present_tileH = true;
    if (this_present_tileH || that_present_tileH) {
      if (!(this_present_tileH && that_present_tileH))
        return false;
      if (this.tileH != that.tileH)
        return false;
    }

    boolean this_present_tileV = true;
    boolean that_present_tileV = true;
    if (this_present_tileV || that_present_tileV) {
      if (!(this_present_tileV && that_present_tileV))
        return false;
      if (this.tileV != that.tileV)
        return false;
    }

    boolean this_present_chunkID = true;
    boolean that_present_chunkID = true;
    if (this_present_chunkID || that_present_chunkID) {
      if (!(this_present_chunkID && that_present_chunkID))
        return false;
      if (this.chunkID != that.chunkID)
        return false;
    }

    boolean this_present_chunkSize = true;
    boolean that_present_chunkSize = true;
    if (this_present_chunkSize || that_present_chunkSize) {
      if (!(this_present_chunkSize && that_present_chunkSize))
        return false;
      if (this.chunkSize != that.chunkSize)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_resolution = true && (isSetResolution());
    builder.append(present_resolution);
    if (present_resolution)
      builder.append(resolution);

    boolean present_tileH = true;
    builder.append(present_tileH);
    if (present_tileH)
      builder.append(tileH);

    boolean present_tileV = true;
    builder.append(present_tileV);
    if (present_tileV)
      builder.append(tileV);

    boolean present_chunkID = true;
    builder.append(present_chunkID);
    if (present_chunkID)
      builder.append(chunkID);

    boolean present_chunkSize = true;
    builder.append(present_chunkSize);
    if (present_chunkSize)
      builder.append(chunkSize);

    return builder.toHashCode();
  }

  public int compareTo(ModisChunkLocation other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;
    ModisChunkLocation typedOther = (ModisChunkLocation)other;

    lastComparison = Boolean.valueOf(isSetResolution()).compareTo(typedOther.isSetResolution());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResolution()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resolution, typedOther.resolution);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTileH()).compareTo(typedOther.isSetTileH());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTileH()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tileH, typedOther.tileH);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTileV()).compareTo(typedOther.isSetTileV());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTileV()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tileV, typedOther.tileV);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChunkID()).compareTo(typedOther.isSetChunkID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChunkID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chunkID, typedOther.chunkID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetChunkSize()).compareTo(typedOther.isSetChunkSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetChunkSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.chunkSize, typedOther.chunkSize);
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
    StringBuilder sb = new StringBuilder("ModisChunkLocation(");
    boolean first = true;

    sb.append("resolution:");
    if (this.resolution == null) {
      sb.append("null");
    } else {
      sb.append(this.resolution);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tileH:");
    sb.append(this.tileH);
    first = false;
    if (!first) sb.append(", ");
    sb.append("tileV:");
    sb.append(this.tileV);
    first = false;
    if (!first) sb.append(", ");
    sb.append("chunkID:");
    sb.append(this.chunkID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("chunkSize:");
    sb.append(this.chunkSize);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
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
      __isset_bit_vector = new BitSet(1);
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ModisChunkLocationStandardSchemeFactory implements SchemeFactory {
    public ModisChunkLocationStandardScheme getScheme() {
      return new ModisChunkLocationStandardScheme();
    }
  }

  private static class ModisChunkLocationStandardScheme extends StandardScheme<ModisChunkLocation> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ModisChunkLocation struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESOLUTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.resolution = iprot.readString();
              struct.setResolutionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TILE_H
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tileH = iprot.readI32();
              struct.setTileHIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TILE_V
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tileV = iprot.readI32();
              struct.setTileVIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CHUNK_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.chunkID = iprot.readI32();
              struct.setChunkIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CHUNK_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.chunkSize = iprot.readI32();
              struct.setChunkSizeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ModisChunkLocation struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.resolution != null) {
        oprot.writeFieldBegin(RESOLUTION_FIELD_DESC);
        oprot.writeString(struct.resolution);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TILE_H_FIELD_DESC);
      oprot.writeI32(struct.tileH);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TILE_V_FIELD_DESC);
      oprot.writeI32(struct.tileV);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CHUNK_ID_FIELD_DESC);
      oprot.writeI32(struct.chunkID);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CHUNK_SIZE_FIELD_DESC);
      oprot.writeI32(struct.chunkSize);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ModisChunkLocationTupleSchemeFactory implements SchemeFactory {
    public ModisChunkLocationTupleScheme getScheme() {
      return new ModisChunkLocationTupleScheme();
    }
  }

  private static class ModisChunkLocationTupleScheme extends TupleScheme<ModisChunkLocation> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ModisChunkLocation struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetResolution()) {
        optionals.set(0);
      }
      if (struct.isSetTileH()) {
        optionals.set(1);
      }
      if (struct.isSetTileV()) {
        optionals.set(2);
      }
      if (struct.isSetChunkID()) {
        optionals.set(3);
      }
      if (struct.isSetChunkSize()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetResolution()) {
        oprot.writeString(struct.resolution);
      }
      if (struct.isSetTileH()) {
        oprot.writeI32(struct.tileH);
      }
      if (struct.isSetTileV()) {
        oprot.writeI32(struct.tileV);
      }
      if (struct.isSetChunkID()) {
        oprot.writeI32(struct.chunkID);
      }
      if (struct.isSetChunkSize()) {
        oprot.writeI32(struct.chunkSize);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ModisChunkLocation struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.resolution = iprot.readString();
        struct.setResolutionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.tileH = iprot.readI32();
        struct.setTileHIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tileV = iprot.readI32();
        struct.setTileVIsSet(true);
      }
      if (incoming.get(3)) {
        struct.chunkID = iprot.readI32();
        struct.setChunkIDIsSet(true);
      }
      if (incoming.get(4)) {
        struct.chunkSize = iprot.readI32();
        struct.setChunkSizeIsSet(true);
      }
    }
  }

}
