// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCCityAwardMsg.proto

package buffer;

public final class GCCityAwardMsg {
  private GCCityAwardMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCCityAwardProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 7016];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 infoType = 2;
    boolean hasInfoType();
    int getInfoType();
    
    // optional int32 cityId = 3;
    boolean hasCityId();
    int getCityId();
    
    // optional int32 status = 4;
    boolean hasStatus();
    int getStatus();
  }
  public static final class GCCityAwardProto extends
      com.google.protobuf.GeneratedMessage
      implements GCCityAwardProtoOrBuilder {
    // Use GCCityAwardProto.newBuilder() to construct.
    private GCCityAwardProto(Builder builder) {
      super(builder);
    }
    private GCCityAwardProto(boolean noInit) {}
    
    private static final GCCityAwardProto defaultInstance;
    public static GCCityAwardProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCCityAwardProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCCityAwardMsg.internal_static_buffer_GCCityAwardProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCCityAwardMsg.internal_static_buffer_GCCityAwardProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 7016];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 infoType = 2;
    public static final int INFOTYPE_FIELD_NUMBER = 2;
    private int infoType_;
    public boolean hasInfoType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getInfoType() {
      return infoType_;
    }
    
    // optional int32 cityId = 3;
    public static final int CITYID_FIELD_NUMBER = 3;
    private int cityId_;
    public boolean hasCityId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getCityId() {
      return cityId_;
    }
    
    // optional int32 status = 4;
    public static final int STATUS_FIELD_NUMBER = 4;
    private int status_;
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public int getStatus() {
      return status_;
    }
    
    private void initFields() {
      msgType_ = 7016;
      infoType_ = 0;
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
        output.writeInt32(2, infoType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, cityId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, status_);
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
          .computeInt32Size(2, infoType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, cityId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, status_);
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
    
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseDelimitedFrom(
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
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCCityAwardMsg.GCCityAwardProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCCityAwardMsg.GCCityAwardProto prototype) {
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
       implements buffer.GCCityAwardMsg.GCCityAwardProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCCityAwardMsg.internal_static_buffer_GCCityAwardProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCCityAwardMsg.internal_static_buffer_GCCityAwardProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCCityAwardMsg.GCCityAwardProto.newBuilder()
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
        msgType_ = 7016;
        bitField0_ = (bitField0_ & ~0x00000001);
        infoType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        cityId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCCityAwardMsg.GCCityAwardProto.getDescriptor();
      }
      
      public buffer.GCCityAwardMsg.GCCityAwardProto getDefaultInstanceForType() {
        return buffer.GCCityAwardMsg.GCCityAwardProto.getDefaultInstance();
      }
      
      public buffer.GCCityAwardMsg.GCCityAwardProto build() {
        buffer.GCCityAwardMsg.GCCityAwardProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCCityAwardMsg.GCCityAwardProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCCityAwardMsg.GCCityAwardProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCCityAwardMsg.GCCityAwardProto buildPartial() {
        buffer.GCCityAwardMsg.GCCityAwardProto result = new buffer.GCCityAwardMsg.GCCityAwardProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.infoType_ = infoType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.cityId_ = cityId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.status_ = status_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCCityAwardMsg.GCCityAwardProto) {
          return mergeFrom((buffer.GCCityAwardMsg.GCCityAwardProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCCityAwardMsg.GCCityAwardProto other) {
        if (other == buffer.GCCityAwardMsg.GCCityAwardProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasInfoType()) {
          setInfoType(other.getInfoType());
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
              infoType_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              cityId_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              status_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 7016];
      private int msgType_ = 7016;
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
        msgType_ = 7016;
        onChanged();
        return this;
      }
      
      // optional int32 infoType = 2;
      private int infoType_ ;
      public boolean hasInfoType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getInfoType() {
        return infoType_;
      }
      public Builder setInfoType(int value) {
        bitField0_ |= 0x00000002;
        infoType_ = value;
        onChanged();
        return this;
      }
      public Builder clearInfoType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        infoType_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 cityId = 3;
      private int cityId_ ;
      public boolean hasCityId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getCityId() {
        return cityId_;
      }
      public Builder setCityId(int value) {
        bitField0_ |= 0x00000004;
        cityId_ = value;
        onChanged();
        return this;
      }
      public Builder clearCityId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        cityId_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 status = 4;
      private int status_ ;
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public int getStatus() {
        return status_;
      }
      public Builder setStatus(int value) {
        bitField0_ |= 0x00000008;
        status_ = value;
        onChanged();
        return this;
      }
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000008);
        status_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCCityAwardProto)
    }
    
    static {
      defaultInstance = new GCCityAwardProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCCityAwardProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCCityAwardProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCCityAwardProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\024GCCityAwardMsg.proto\022\006buffer\"[\n\020GCCity" +
      "AwardProto\022\025\n\007msgType\030\001 \001(\005:\0047016\022\020\n\010inf" +
      "oType\030\002 \001(\005\022\016\n\006cityId\030\003 \001(\005\022\016\n\006status\030\004 " +
      "\001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCCityAwardProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCCityAwardProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCCityAwardProto_descriptor,
              new java.lang.String[] { "MsgType", "InfoType", "CityId", "Status", },
              buffer.GCCityAwardMsg.GCCityAwardProto.class,
              buffer.GCCityAwardMsg.GCCityAwardProto.Builder.class);
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
