// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGPveCityUnlockMsg.proto

package buffer;

public final class CGPveCityUnlockMsg {
  private CGPveCityUnlockMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGPveCityUnlockProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 7012];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 cityId = 2;
    boolean hasCityId();
    int getCityId();
    
    // optional int32 pveId = 3;
    boolean hasPveId();
    int getPveId();
  }
  public static final class CGPveCityUnlockProto extends
      com.google.protobuf.GeneratedMessage
      implements CGPveCityUnlockProtoOrBuilder {
    // Use CGPveCityUnlockProto.newBuilder() to construct.
    private CGPveCityUnlockProto(Builder builder) {
      super(builder);
    }
    private CGPveCityUnlockProto(boolean noInit) {}
    
    private static final CGPveCityUnlockProto defaultInstance;
    public static CGPveCityUnlockProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGPveCityUnlockProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGPveCityUnlockMsg.internal_static_buffer_CGPveCityUnlockProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGPveCityUnlockMsg.internal_static_buffer_CGPveCityUnlockProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 7012];
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
    
    // optional int32 pveId = 3;
    public static final int PVEID_FIELD_NUMBER = 3;
    private int pveId_;
    public boolean hasPveId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getPveId() {
      return pveId_;
    }
    
    private void initFields() {
      msgType_ = 7012;
      cityId_ = 0;
      pveId_ = 0;
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
        output.writeInt32(3, pveId_);
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
          .computeInt32Size(3, pveId_);
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
    
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseDelimitedFrom(
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
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto prototype) {
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
       implements buffer.CGPveCityUnlockMsg.CGPveCityUnlockProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGPveCityUnlockMsg.internal_static_buffer_CGPveCityUnlockProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGPveCityUnlockMsg.internal_static_buffer_CGPveCityUnlockProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto.newBuilder()
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
        msgType_ = 7012;
        bitField0_ = (bitField0_ & ~0x00000001);
        cityId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        pveId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto.getDescriptor();
      }
      
      public buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto getDefaultInstanceForType() {
        return buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto.getDefaultInstance();
      }
      
      public buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto build() {
        buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto buildPartial() {
        buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto result = new buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto(this);
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
        result.pveId_ = pveId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto) {
          return mergeFrom((buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto other) {
        if (other == buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasCityId()) {
          setCityId(other.getCityId());
        }
        if (other.hasPveId()) {
          setPveId(other.getPveId());
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
              pveId_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 7012];
      private int msgType_ = 7012;
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
        msgType_ = 7012;
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
      
      // optional int32 pveId = 3;
      private int pveId_ ;
      public boolean hasPveId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getPveId() {
        return pveId_;
      }
      public Builder setPveId(int value) {
        bitField0_ |= 0x00000004;
        pveId_ = value;
        onChanged();
        return this;
      }
      public Builder clearPveId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        pveId_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGPveCityUnlockProto)
    }
    
    static {
      defaultInstance = new CGPveCityUnlockProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGPveCityUnlockProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGPveCityUnlockProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGPveCityUnlockProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030CGPveCityUnlockMsg.proto\022\006buffer\"L\n\024CG" +
      "PveCityUnlockProto\022\025\n\007msgType\030\001 \001(\005:\004701" +
      "2\022\016\n\006cityId\030\002 \001(\005\022\r\n\005pveId\030\003 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGPveCityUnlockProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGPveCityUnlockProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGPveCityUnlockProto_descriptor,
              new java.lang.String[] { "MsgType", "CityId", "PveId", },
              buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto.class,
              buffer.CGPveCityUnlockMsg.CGPveCityUnlockProto.Builder.class);
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
