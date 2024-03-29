// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGCityInfoMsg.proto

package buffer;

public final class CGCityInfoMsg {
  private CGCityInfoMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGCityInfoProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 7015];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 infoType = 2;
    boolean hasInfoType();
    int getInfoType();
  }
  public static final class CGCityInfoProto extends
      com.google.protobuf.GeneratedMessage
      implements CGCityInfoProtoOrBuilder {
    // Use CGCityInfoProto.newBuilder() to construct.
    private CGCityInfoProto(Builder builder) {
      super(builder);
    }
    private CGCityInfoProto(boolean noInit) {}
    
    private static final CGCityInfoProto defaultInstance;
    public static CGCityInfoProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGCityInfoProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGCityInfoMsg.internal_static_buffer_CGCityInfoProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGCityInfoMsg.internal_static_buffer_CGCityInfoProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 7015];
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
    
    private void initFields() {
      msgType_ = 7015;
      infoType_ = 0;
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
    
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseDelimitedFrom(
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
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGCityInfoMsg.CGCityInfoProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGCityInfoMsg.CGCityInfoProto prototype) {
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
       implements buffer.CGCityInfoMsg.CGCityInfoProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGCityInfoMsg.internal_static_buffer_CGCityInfoProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGCityInfoMsg.internal_static_buffer_CGCityInfoProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGCityInfoMsg.CGCityInfoProto.newBuilder()
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
        msgType_ = 7015;
        bitField0_ = (bitField0_ & ~0x00000001);
        infoType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGCityInfoMsg.CGCityInfoProto.getDescriptor();
      }
      
      public buffer.CGCityInfoMsg.CGCityInfoProto getDefaultInstanceForType() {
        return buffer.CGCityInfoMsg.CGCityInfoProto.getDefaultInstance();
      }
      
      public buffer.CGCityInfoMsg.CGCityInfoProto build() {
        buffer.CGCityInfoMsg.CGCityInfoProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGCityInfoMsg.CGCityInfoProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGCityInfoMsg.CGCityInfoProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGCityInfoMsg.CGCityInfoProto buildPartial() {
        buffer.CGCityInfoMsg.CGCityInfoProto result = new buffer.CGCityInfoMsg.CGCityInfoProto(this);
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
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGCityInfoMsg.CGCityInfoProto) {
          return mergeFrom((buffer.CGCityInfoMsg.CGCityInfoProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGCityInfoMsg.CGCityInfoProto other) {
        if (other == buffer.CGCityInfoMsg.CGCityInfoProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasInfoType()) {
          setInfoType(other.getInfoType());
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
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 7015];
      private int msgType_ = 7015;
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
        msgType_ = 7015;
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
      
      // @@protoc_insertion_point(builder_scope:buffer.CGCityInfoProto)
    }
    
    static {
      defaultInstance = new CGCityInfoProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGCityInfoProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGCityInfoProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGCityInfoProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023CGCityInfoMsg.proto\022\006buffer\":\n\017CGCityI" +
      "nfoProto\022\025\n\007msgType\030\001 \001(\005:\0047015\022\020\n\010infoT" +
      "ype\030\002 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGCityInfoProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGCityInfoProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGCityInfoProto_descriptor,
              new java.lang.String[] { "MsgType", "InfoType", },
              buffer.CGCityInfoMsg.CGCityInfoProto.class,
              buffer.CGCityInfoMsg.CGCityInfoProto.Builder.class);
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
