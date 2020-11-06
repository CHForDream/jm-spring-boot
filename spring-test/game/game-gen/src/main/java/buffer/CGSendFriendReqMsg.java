// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGSendFriendReqMsg.proto

package buffer;

public final class CGSendFriendReqMsg {
  private CGSendFriendReqMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGSendFriendReqProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 2011];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int64 uid = 2;
    boolean hasUid();
    long getUid();
    
    // optional string declaration = 3;
    boolean hasDeclaration();
    String getDeclaration();
  }
  public static final class CGSendFriendReqProto extends
      com.google.protobuf.GeneratedMessage
      implements CGSendFriendReqProtoOrBuilder {
    // Use CGSendFriendReqProto.newBuilder() to construct.
    private CGSendFriendReqProto(Builder builder) {
      super(builder);
    }
    private CGSendFriendReqProto(boolean noInit) {}
    
    private static final CGSendFriendReqProto defaultInstance;
    public static CGSendFriendReqProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGSendFriendReqProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGSendFriendReqMsg.internal_static_buffer_CGSendFriendReqProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGSendFriendReqMsg.internal_static_buffer_CGSendFriendReqProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 2011];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int64 uid = 2;
    public static final int UID_FIELD_NUMBER = 2;
    private long uid_;
    public boolean hasUid() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public long getUid() {
      return uid_;
    }
    
    // optional string declaration = 3;
    public static final int DECLARATION_FIELD_NUMBER = 3;
    private java.lang.Object declaration_;
    public boolean hasDeclaration() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getDeclaration() {
      java.lang.Object ref = declaration_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          declaration_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getDeclarationBytes() {
      java.lang.Object ref = declaration_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        declaration_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    private void initFields() {
      msgType_ = 2011;
      uid_ = 0L;
      declaration_ = "";
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
        output.writeInt64(2, uid_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getDeclarationBytes());
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
          .computeInt64Size(2, uid_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getDeclarationBytes());
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
    
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseDelimitedFrom(
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
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGSendFriendReqMsg.CGSendFriendReqProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGSendFriendReqMsg.CGSendFriendReqProto prototype) {
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
       implements buffer.CGSendFriendReqMsg.CGSendFriendReqProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGSendFriendReqMsg.internal_static_buffer_CGSendFriendReqProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGSendFriendReqMsg.internal_static_buffer_CGSendFriendReqProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGSendFriendReqMsg.CGSendFriendReqProto.newBuilder()
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
        msgType_ = 2011;
        bitField0_ = (bitField0_ & ~0x00000001);
        uid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        declaration_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGSendFriendReqMsg.CGSendFriendReqProto.getDescriptor();
      }
      
      public buffer.CGSendFriendReqMsg.CGSendFriendReqProto getDefaultInstanceForType() {
        return buffer.CGSendFriendReqMsg.CGSendFriendReqProto.getDefaultInstance();
      }
      
      public buffer.CGSendFriendReqMsg.CGSendFriendReqProto build() {
        buffer.CGSendFriendReqMsg.CGSendFriendReqProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGSendFriendReqMsg.CGSendFriendReqProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGSendFriendReqMsg.CGSendFriendReqProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGSendFriendReqMsg.CGSendFriendReqProto buildPartial() {
        buffer.CGSendFriendReqMsg.CGSendFriendReqProto result = new buffer.CGSendFriendReqMsg.CGSendFriendReqProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.uid_ = uid_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.declaration_ = declaration_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGSendFriendReqMsg.CGSendFriendReqProto) {
          return mergeFrom((buffer.CGSendFriendReqMsg.CGSendFriendReqProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGSendFriendReqMsg.CGSendFriendReqProto other) {
        if (other == buffer.CGSendFriendReqMsg.CGSendFriendReqProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasUid()) {
          setUid(other.getUid());
        }
        if (other.hasDeclaration()) {
          setDeclaration(other.getDeclaration());
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
              uid_ = input.readInt64();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              declaration_ = input.readBytes();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 2011];
      private int msgType_ = 2011;
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
        msgType_ = 2011;
        onChanged();
        return this;
      }
      
      // optional int64 uid = 2;
      private long uid_ ;
      public boolean hasUid() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public long getUid() {
        return uid_;
      }
      public Builder setUid(long value) {
        bitField0_ |= 0x00000002;
        uid_ = value;
        onChanged();
        return this;
      }
      public Builder clearUid() {
        bitField0_ = (bitField0_ & ~0x00000002);
        uid_ = 0L;
        onChanged();
        return this;
      }
      
      // optional string declaration = 3;
      private java.lang.Object declaration_ = "";
      public boolean hasDeclaration() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getDeclaration() {
        java.lang.Object ref = declaration_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          declaration_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setDeclaration(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        declaration_ = value;
        onChanged();
        return this;
      }
      public Builder clearDeclaration() {
        bitField0_ = (bitField0_ & ~0x00000004);
        declaration_ = getDefaultInstance().getDeclaration();
        onChanged();
        return this;
      }
      void setDeclaration(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        declaration_ = value;
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGSendFriendReqProto)
    }
    
    static {
      defaultInstance = new CGSendFriendReqProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGSendFriendReqProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGSendFriendReqProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGSendFriendReqProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030CGSendFriendReqMsg.proto\022\006buffer\"O\n\024CG" +
      "SendFriendReqProto\022\025\n\007msgType\030\001 \001(\005:\004201" +
      "1\022\013\n\003uid\030\002 \001(\003\022\023\n\013declaration\030\003 \001(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGSendFriendReqProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGSendFriendReqProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGSendFriendReqProto_descriptor,
              new java.lang.String[] { "MsgType", "Uid", "Declaration", },
              buffer.CGSendFriendReqMsg.CGSendFriendReqProto.class,
              buffer.CGSendFriendReqMsg.CGSendFriendReqProto.Builder.class);
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
