// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCChangeCityMsg.proto

package buffer;

public final class GCChangeCityMsg {
  private GCChangeCityMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCChangeCityProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 7017];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 cityId = 2;
    boolean hasCityId();
    int getCityId();
    
    // optional int32 status = 3;
    boolean hasStatus();
    int getStatus();
  }
  public static final class GCChangeCityProto extends
      com.google.protobuf.GeneratedMessage
      implements GCChangeCityProtoOrBuilder {
    // Use GCChangeCityProto.newBuilder() to construct.
    private GCChangeCityProto(Builder builder) {
      super(builder);
    }
    private GCChangeCityProto(boolean noInit) {}
    
    private static final GCChangeCityProto defaultInstance;
    public static GCChangeCityProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCChangeCityProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCChangeCityMsg.internal_static_buffer_GCChangeCityProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCChangeCityMsg.internal_static_buffer_GCChangeCityProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 7017];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 cityId = 2;
    public static final int CITYID_FIELD_NUMBER = 2;
    private int cityId_;
    public boolean hasCityId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getCityId() {
      return cityId_;
    }
    
    // optional int32 status = 3;
    public static final int STATUS_FIELD_NUMBER = 3;
    private int status_;
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getStatus() {
      return status_;
    }
    
    private void initFields() {
      msgType_ = 7017;
      cityId_ = 0;
      status_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, msgType_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, cityId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, status_);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, msgType_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, cityId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, status_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCChangeCityMsg.GCChangeCityProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCChangeCityMsg.GCChangeCityProto prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements buffer.GCChangeCityMsg.GCChangeCityProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCChangeCityMsg.internal_static_buffer_GCChangeCityProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCChangeCityMsg.internal_static_buffer_GCChangeCityProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCChangeCityMsg.GCChangeCityProto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        msgType_ = 7017;
        bitField0_ = (bitField0_ & ~0x00000001);
        cityId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCChangeCityMsg.GCChangeCityProto.getDescriptor();
      }
      
      public buffer.GCChangeCityMsg.GCChangeCityProto getDefaultInstanceForType() {
        return buffer.GCChangeCityMsg.GCChangeCityProto.getDefaultInstance();
      }
      
      public buffer.GCChangeCityMsg.GCChangeCityProto build() {
        buffer.GCChangeCityMsg.GCChangeCityProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCChangeCityMsg.GCChangeCityProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCChangeCityMsg.GCChangeCityProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCChangeCityMsg.GCChangeCityProto buildPartial() {
        buffer.GCChangeCityMsg.GCChangeCityProto result = new buffer.GCChangeCityMsg.GCChangeCityProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.cityId_ = cityId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.status_ = status_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCChangeCityMsg.GCChangeCityProto) {
          return mergeFrom((buffer.GCChangeCityMsg.GCChangeCityProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCChangeCityMsg.GCChangeCityProto other) {
        if (other == buffer.GCChangeCityMsg.GCChangeCityProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasCityId()) {
          setCityId(other.getCityId());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              msgType_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              cityId_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              status_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 7017];
      private int msgType_ = 7017;
      public boolean hasMsgType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getMsgType() {
        return msgType_;
      }
      public Builder setMsgType(int value) {
        bitField0_ |= 0x00000001;
        msgType_ = value;
        onChanged();
        return this;
      }
      public Builder clearMsgType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        msgType_ = 7017;
        onChanged();
        return this;
      }
      
      // optional int32 cityId = 2;
      private int cityId_ ;
      public boolean hasCityId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getCityId() {
        return cityId_;
      }
      public Builder setCityId(int value) {
        bitField0_ |= 0x00000002;
        cityId_ = value;
        onChanged();
        return this;
      }
      public Builder clearCityId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        cityId_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 status = 3;
      private int status_ ;
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getStatus() {
        return status_;
      }
      public Builder setStatus(int value) {
        bitField0_ |= 0x00000004;
        status_ = value;
        onChanged();
        return this;
      }
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000004);
        status_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCChangeCityProto)
    }
    
    static {
      defaultInstance = new GCChangeCityProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCChangeCityProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCChangeCityProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCChangeCityProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025GCChangeCityMsg.proto\022\006buffer\"J\n\021GCCha" +
      "ngeCityProto\022\025\n\007msgType\030\001 \001(\005:\0047017\022\016\n\006c" +
      "ityId\030\002 \001(\005\022\016\n\006status\030\003 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCChangeCityProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCChangeCityProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCChangeCityProto_descriptor,
              new java.lang.String[] { "MsgType", "CityId", "Status", },
              buffer.GCChangeCityMsg.GCChangeCityProto.class,
              buffer.GCChangeCityMsg.GCChangeCityProto.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}
