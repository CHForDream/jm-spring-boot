// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGMailDeleteMsg.proto

package buffer;

public final class CGMailDeleteMsg {
  private CGMailDeleteMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGMailDeleteProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 2203];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 delType = 2;
    boolean hasDelType();
    int getDelType();
    
    // optional int32 mailType = 3;
    boolean hasMailType();
    int getMailType();
    
    // optional int64 params = 4;
    boolean hasParams();
    long getParams();
  }
  public static final class CGMailDeleteProto extends
      com.google.protobuf.GeneratedMessage
      implements CGMailDeleteProtoOrBuilder {
    // Use CGMailDeleteProto.newBuilder() to construct.
    private CGMailDeleteProto(Builder builder) {
      super(builder);
    }
    private CGMailDeleteProto(boolean noInit) {}
    
    private static final CGMailDeleteProto defaultInstance;
    public static CGMailDeleteProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGMailDeleteProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGMailDeleteMsg.internal_static_buffer_CGMailDeleteProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGMailDeleteMsg.internal_static_buffer_CGMailDeleteProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 2203];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 delType = 2;
    public static final int DELTYPE_FIELD_NUMBER = 2;
    private int delType_;
    public boolean hasDelType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getDelType() {
      return delType_;
    }
    
    // optional int32 mailType = 3;
    public static final int MAILTYPE_FIELD_NUMBER = 3;
    private int mailType_;
    public boolean hasMailType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getMailType() {
      return mailType_;
    }
    
    // optional int64 params = 4;
    public static final int PARAMS_FIELD_NUMBER = 4;
    private long params_;
    public boolean hasParams() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public long getParams() {
      return params_;
    }
    
    private void initFields() {
      msgType_ = 2203;
      delType_ = 0;
      mailType_ = 0;
      params_ = 0L;
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
        output.writeInt32(2, delType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, mailType_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt64(4, params_);
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
          .computeInt32Size(2, delType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, mailType_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, params_);
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
    
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseDelimitedFrom(
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
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGMailDeleteMsg.CGMailDeleteProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGMailDeleteMsg.CGMailDeleteProto prototype) {
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
       implements buffer.CGMailDeleteMsg.CGMailDeleteProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGMailDeleteMsg.internal_static_buffer_CGMailDeleteProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGMailDeleteMsg.internal_static_buffer_CGMailDeleteProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGMailDeleteMsg.CGMailDeleteProto.newBuilder()
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
        msgType_ = 2203;
        bitField0_ = (bitField0_ & ~0x00000001);
        delType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        mailType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        params_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGMailDeleteMsg.CGMailDeleteProto.getDescriptor();
      }
      
      public buffer.CGMailDeleteMsg.CGMailDeleteProto getDefaultInstanceForType() {
        return buffer.CGMailDeleteMsg.CGMailDeleteProto.getDefaultInstance();
      }
      
      public buffer.CGMailDeleteMsg.CGMailDeleteProto build() {
        buffer.CGMailDeleteMsg.CGMailDeleteProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGMailDeleteMsg.CGMailDeleteProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGMailDeleteMsg.CGMailDeleteProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGMailDeleteMsg.CGMailDeleteProto buildPartial() {
        buffer.CGMailDeleteMsg.CGMailDeleteProto result = new buffer.CGMailDeleteMsg.CGMailDeleteProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.delType_ = delType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.mailType_ = mailType_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.params_ = params_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGMailDeleteMsg.CGMailDeleteProto) {
          return mergeFrom((buffer.CGMailDeleteMsg.CGMailDeleteProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGMailDeleteMsg.CGMailDeleteProto other) {
        if (other == buffer.CGMailDeleteMsg.CGMailDeleteProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasDelType()) {
          setDelType(other.getDelType());
        }
        if (other.hasMailType()) {
          setMailType(other.getMailType());
        }
        if (other.hasParams()) {
          setParams(other.getParams());
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
              delType_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              mailType_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              params_ = input.readInt64();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 2203];
      private int msgType_ = 2203;
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
        msgType_ = 2203;
        onChanged();
        return this;
      }
      
      // optional int32 delType = 2;
      private int delType_ ;
      public boolean hasDelType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getDelType() {
        return delType_;
      }
      public Builder setDelType(int value) {
        bitField0_ |= 0x00000002;
        delType_ = value;
        onChanged();
        return this;
      }
      public Builder clearDelType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        delType_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 mailType = 3;
      private int mailType_ ;
      public boolean hasMailType() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getMailType() {
        return mailType_;
      }
      public Builder setMailType(int value) {
        bitField0_ |= 0x00000004;
        mailType_ = value;
        onChanged();
        return this;
      }
      public Builder clearMailType() {
        bitField0_ = (bitField0_ & ~0x00000004);
        mailType_ = 0;
        onChanged();
        return this;
      }
      
      // optional int64 params = 4;
      private long params_ ;
      public boolean hasParams() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public long getParams() {
        return params_;
      }
      public Builder setParams(long value) {
        bitField0_ |= 0x00000008;
        params_ = value;
        onChanged();
        return this;
      }
      public Builder clearParams() {
        bitField0_ = (bitField0_ & ~0x00000008);
        params_ = 0L;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGMailDeleteProto)
    }
    
    static {
      defaultInstance = new CGMailDeleteProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGMailDeleteProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGMailDeleteProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGMailDeleteProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025CGMailDeleteMsg.proto\022\006buffer\"]\n\021CGMai" +
      "lDeleteProto\022\025\n\007msgType\030\001 \001(\005:\0042203\022\017\n\007d" +
      "elType\030\002 \001(\005\022\020\n\010mailType\030\003 \001(\005\022\016\n\006params" +
      "\030\004 \001(\003"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGMailDeleteProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGMailDeleteProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGMailDeleteProto_descriptor,
              new java.lang.String[] { "MsgType", "DelType", "MailType", "Params", },
              buffer.CGMailDeleteMsg.CGMailDeleteProto.class,
              buffer.CGMailDeleteMsg.CGMailDeleteProto.Builder.class);
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
