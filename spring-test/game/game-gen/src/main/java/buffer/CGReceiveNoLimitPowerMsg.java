// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGReceiveNoLimitPowerMsg.proto

package buffer;

public final class CGReceiveNoLimitPowerMsg {
  private CGReceiveNoLimitPowerMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGReceiveNoLimitPowerProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 24026];
    boolean hasMsgType();
    int getMsgType();
  }
  public static final class CGReceiveNoLimitPowerProto extends
      com.google.protobuf.GeneratedMessage
      implements CGReceiveNoLimitPowerProtoOrBuilder {
    // Use CGReceiveNoLimitPowerProto.newBuilder() to construct.
    private CGReceiveNoLimitPowerProto(Builder builder) {
      super(builder);
    }
    private CGReceiveNoLimitPowerProto(boolean noInit) {}
    
    private static final CGReceiveNoLimitPowerProto defaultInstance;
    public static CGReceiveNoLimitPowerProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGReceiveNoLimitPowerProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGReceiveNoLimitPowerMsg.internal_static_buffer_CGReceiveNoLimitPowerProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGReceiveNoLimitPowerMsg.internal_static_buffer_CGReceiveNoLimitPowerProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 24026];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    private void initFields() {
      msgType_ = 24026;
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
    
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseDelimitedFrom(
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
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto prototype) {
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
       implements buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGReceiveNoLimitPowerMsg.internal_static_buffer_CGReceiveNoLimitPowerProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGReceiveNoLimitPowerMsg.internal_static_buffer_CGReceiveNoLimitPowerProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto.newBuilder()
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
        msgType_ = 24026;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto.getDescriptor();
      }
      
      public buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto getDefaultInstanceForType() {
        return buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto.getDefaultInstance();
      }
      
      public buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto build() {
        buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto buildPartial() {
        buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto result = new buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto) {
          return mergeFrom((buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto other) {
        if (other == buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
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
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 24026];
      private int msgType_ = 24026;
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
        msgType_ = 24026;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGReceiveNoLimitPowerProto)
    }
    
    static {
      defaultInstance = new CGReceiveNoLimitPowerProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGReceiveNoLimitPowerProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGReceiveNoLimitPowerProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGReceiveNoLimitPowerProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036CGReceiveNoLimitPowerMsg.proto\022\006buffer" +
      "\"4\n\032CGReceiveNoLimitPowerProto\022\026\n\007msgTyp" +
      "e\030\001 \001(\005:\00524026"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGReceiveNoLimitPowerProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGReceiveNoLimitPowerProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGReceiveNoLimitPowerProto_descriptor,
              new java.lang.String[] { "MsgType", },
              buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto.class,
              buffer.CGReceiveNoLimitPowerMsg.CGReceiveNoLimitPowerProto.Builder.class);
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