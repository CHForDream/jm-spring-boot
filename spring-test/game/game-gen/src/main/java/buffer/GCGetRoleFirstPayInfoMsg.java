// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCGetRoleFirstPayInfoMsg.proto

package buffer;

public final class GCGetRoleFirstPayInfoMsg {
  private GCGetRoleFirstPayInfoMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCGetRoleFirstPayInfoProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 4404];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 status = 2;
    boolean hasStatus();
    int getStatus();
    
    // optional string firstStatus = 3;
    boolean hasFirstStatus();
    String getFirstStatus();
  }
  public static final class GCGetRoleFirstPayInfoProto extends
      com.google.protobuf.GeneratedMessage
      implements GCGetRoleFirstPayInfoProtoOrBuilder {
    // Use GCGetRoleFirstPayInfoProto.newBuilder() to construct.
    private GCGetRoleFirstPayInfoProto(Builder builder) {
      super(builder);
    }
    private GCGetRoleFirstPayInfoProto(boolean noInit) {}
    
    private static final GCGetRoleFirstPayInfoProto defaultInstance;
    public static GCGetRoleFirstPayInfoProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCGetRoleFirstPayInfoProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCGetRoleFirstPayInfoMsg.internal_static_buffer_GCGetRoleFirstPayInfoProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCGetRoleFirstPayInfoMsg.internal_static_buffer_GCGetRoleFirstPayInfoProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 4404];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 status = 2;
    public static final int STATUS_FIELD_NUMBER = 2;
    private int status_;
    public boolean hasStatus() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getStatus() {
      return status_;
    }
    
    // optional string firstStatus = 3;
    public static final int FIRSTSTATUS_FIELD_NUMBER = 3;
    private java.lang.Object firstStatus_;
    public boolean hasFirstStatus() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getFirstStatus() {
      java.lang.Object ref = firstStatus_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          firstStatus_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getFirstStatusBytes() {
      java.lang.Object ref = firstStatus_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        firstStatus_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    private void initFields() {
      msgType_ = 4404;
      status_ = 0;
      firstStatus_ = "";
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
        output.writeInt32(2, status_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getFirstStatusBytes());
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
          .computeInt32Size(2, status_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getFirstStatusBytes());
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
    
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseDelimitedFrom(
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
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto prototype) {
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
       implements buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCGetRoleFirstPayInfoMsg.internal_static_buffer_GCGetRoleFirstPayInfoProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCGetRoleFirstPayInfoMsg.internal_static_buffer_GCGetRoleFirstPayInfoProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.newBuilder()
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
        msgType_ = 4404;
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        firstStatus_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.getDescriptor();
      }
      
      public buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto getDefaultInstanceForType() {
        return buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.getDefaultInstance();
      }
      
      public buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto build() {
        buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto buildPartial() {
        buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto result = new buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.status_ = status_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.firstStatus_ = firstStatus_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto) {
          return mergeFrom((buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto other) {
        if (other == buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasFirstStatus()) {
          setFirstStatus(other.getFirstStatus());
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
              status_ = input.readInt32();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              firstStatus_ = input.readBytes();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 4404];
      private int msgType_ = 4404;
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
        msgType_ = 4404;
        onChanged();
        return this;
      }
      
      // optional int32 status = 2;
      private int status_ ;
      public boolean hasStatus() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getStatus() {
        return status_;
      }
      public Builder setStatus(int value) {
        bitField0_ |= 0x00000002;
        status_ = value;
        onChanged();
        return this;
      }
      public Builder clearStatus() {
        bitField0_ = (bitField0_ & ~0x00000002);
        status_ = 0;
        onChanged();
        return this;
      }
      
      // optional string firstStatus = 3;
      private java.lang.Object firstStatus_ = "";
      public boolean hasFirstStatus() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getFirstStatus() {
        java.lang.Object ref = firstStatus_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          firstStatus_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setFirstStatus(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        firstStatus_ = value;
        onChanged();
        return this;
      }
      public Builder clearFirstStatus() {
        bitField0_ = (bitField0_ & ~0x00000004);
        firstStatus_ = getDefaultInstance().getFirstStatus();
        onChanged();
        return this;
      }
      void setFirstStatus(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        firstStatus_ = value;
        onChanged();
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCGetRoleFirstPayInfoProto)
    }
    
    static {
      defaultInstance = new GCGetRoleFirstPayInfoProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCGetRoleFirstPayInfoProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCGetRoleFirstPayInfoProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCGetRoleFirstPayInfoProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036GCGetRoleFirstPayInfoMsg.proto\022\006buffer" +
      "\"X\n\032GCGetRoleFirstPayInfoProto\022\025\n\007msgTyp" +
      "e\030\001 \001(\005:\0044404\022\016\n\006status\030\002 \001(\005\022\023\n\013firstSt" +
      "atus\030\003 \001(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCGetRoleFirstPayInfoProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCGetRoleFirstPayInfoProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCGetRoleFirstPayInfoProto_descriptor,
              new java.lang.String[] { "MsgType", "Status", "FirstStatus", },
              buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.class,
              buffer.GCGetRoleFirstPayInfoMsg.GCGetRoleFirstPayInfoProto.Builder.class);
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
