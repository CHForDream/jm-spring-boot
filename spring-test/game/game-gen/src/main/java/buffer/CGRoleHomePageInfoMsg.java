// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGRoleHomePageInfoMsg.proto

package buffer;

public final class CGRoleHomePageInfoMsg {
  private CGRoleHomePageInfoMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGRoleHomePageInfoProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 24022];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int64 targetUid = 2;
    boolean hasTargetUid();
    long getTargetUid();
  }
  public static final class CGRoleHomePageInfoProto extends
      com.google.protobuf.GeneratedMessage
      implements CGRoleHomePageInfoProtoOrBuilder {
    // Use CGRoleHomePageInfoProto.newBuilder() to construct.
    private CGRoleHomePageInfoProto(Builder builder) {
      super(builder);
    }
    private CGRoleHomePageInfoProto(boolean noInit) {}
    
    private static final CGRoleHomePageInfoProto defaultInstance;
    public static CGRoleHomePageInfoProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGRoleHomePageInfoProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGRoleHomePageInfoMsg.internal_static_buffer_CGRoleHomePageInfoProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGRoleHomePageInfoMsg.internal_static_buffer_CGRoleHomePageInfoProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 24022];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int64 targetUid = 2;
    public static final int TARGETUID_FIELD_NUMBER = 2;
    private long targetUid_;
    public boolean hasTargetUid() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public long getTargetUid() {
      return targetUid_;
    }
    
    private void initFields() {
      msgType_ = 24022;
      targetUid_ = 0L;
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
        output.writeInt64(2, targetUid_);
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
          .computeInt64Size(2, targetUid_);
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
    
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseDelimitedFrom(
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
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto prototype) {
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
       implements buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGRoleHomePageInfoMsg.internal_static_buffer_CGRoleHomePageInfoProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGRoleHomePageInfoMsg.internal_static_buffer_CGRoleHomePageInfoProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto.newBuilder()
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
        msgType_ = 24022;
        bitField0_ = (bitField0_ & ~0x00000001);
        targetUid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto.getDescriptor();
      }
      
      public buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto getDefaultInstanceForType() {
        return buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto.getDefaultInstance();
      }
      
      public buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto build() {
        buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto buildPartial() {
        buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto result = new buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.targetUid_ = targetUid_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto) {
          return mergeFrom((buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto other) {
        if (other == buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasTargetUid()) {
          setTargetUid(other.getTargetUid());
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
              targetUid_ = input.readInt64();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 24022];
      private int msgType_ = 24022;
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
        msgType_ = 24022;
        onChanged();
        return this;
      }
      
      // optional int64 targetUid = 2;
      private long targetUid_ ;
      public boolean hasTargetUid() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public long getTargetUid() {
        return targetUid_;
      }
      public Builder setTargetUid(long value) {
        bitField0_ |= 0x00000002;
        targetUid_ = value;
        onChanged();
        return this;
      }
      public Builder clearTargetUid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        targetUid_ = 0L;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGRoleHomePageInfoProto)
    }
    
    static {
      defaultInstance = new CGRoleHomePageInfoProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGRoleHomePageInfoProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGRoleHomePageInfoProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGRoleHomePageInfoProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033CGRoleHomePageInfoMsg.proto\022\006buffer\"D\n" +
      "\027CGRoleHomePageInfoProto\022\026\n\007msgType\030\001 \001(" +
      "\005:\00524022\022\021\n\ttargetUid\030\002 \001(\003"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGRoleHomePageInfoProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGRoleHomePageInfoProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGRoleHomePageInfoProto_descriptor,
              new java.lang.String[] { "MsgType", "TargetUid", },
              buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto.class,
              buffer.CGRoleHomePageInfoMsg.CGRoleHomePageInfoProto.Builder.class);
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
