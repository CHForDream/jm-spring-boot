// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGHeroCommentDelMsg.proto

package buffer;

public final class CGHeroCommentDelMsg {
  private CGHeroCommentDelMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGHeroCommentDelProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 24016];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 heroCode = 2;
    boolean hasHeroCode();
    int getHeroCode();
    
    // optional int32 type = 3;
    boolean hasType();
    int getType();
  }
  public static final class CGHeroCommentDelProto extends
      com.google.protobuf.GeneratedMessage
      implements CGHeroCommentDelProtoOrBuilder {
    // Use CGHeroCommentDelProto.newBuilder() to construct.
    private CGHeroCommentDelProto(Builder builder) {
      super(builder);
    }
    private CGHeroCommentDelProto(boolean noInit) {}
    
    private static final CGHeroCommentDelProto defaultInstance;
    public static CGHeroCommentDelProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGHeroCommentDelProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGHeroCommentDelMsg.internal_static_buffer_CGHeroCommentDelProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGHeroCommentDelMsg.internal_static_buffer_CGHeroCommentDelProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 24016];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 heroCode = 2;
    public static final int HEROCODE_FIELD_NUMBER = 2;
    private int heroCode_;
    public boolean hasHeroCode() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getHeroCode() {
      return heroCode_;
    }
    
    // optional int32 type = 3;
    public static final int TYPE_FIELD_NUMBER = 3;
    private int type_;
    public boolean hasType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getType() {
      return type_;
    }
    
    private void initFields() {
      msgType_ = 24016;
      heroCode_ = 0;
      type_ = 0;
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
        output.writeInt32(2, heroCode_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, type_);
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
          .computeInt32Size(2, heroCode_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, type_);
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
    
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseDelimitedFrom(
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
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto prototype) {
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
       implements buffer.CGHeroCommentDelMsg.CGHeroCommentDelProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGHeroCommentDelMsg.internal_static_buffer_CGHeroCommentDelProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGHeroCommentDelMsg.internal_static_buffer_CGHeroCommentDelProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto.newBuilder()
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
        msgType_ = 24016;
        bitField0_ = (bitField0_ & ~0x00000001);
        heroCode_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto.getDescriptor();
      }
      
      public buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto getDefaultInstanceForType() {
        return buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto.getDefaultInstance();
      }
      
      public buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto build() {
        buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto buildPartial() {
        buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto result = new buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.heroCode_ = heroCode_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.type_ = type_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto) {
          return mergeFrom((buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto other) {
        if (other == buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasHeroCode()) {
          setHeroCode(other.getHeroCode());
        }
        if (other.hasType()) {
          setType(other.getType());
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
              heroCode_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              type_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 24016];
      private int msgType_ = 24016;
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
        msgType_ = 24016;
        onChanged();
        return this;
      }
      
      // optional int32 heroCode = 2;
      private int heroCode_ ;
      public boolean hasHeroCode() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getHeroCode() {
        return heroCode_;
      }
      public Builder setHeroCode(int value) {
        bitField0_ |= 0x00000002;
        heroCode_ = value;
        onChanged();
        return this;
      }
      public Builder clearHeroCode() {
        bitField0_ = (bitField0_ & ~0x00000002);
        heroCode_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 type = 3;
      private int type_ ;
      public boolean hasType() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getType() {
        return type_;
      }
      public Builder setType(int value) {
        bitField0_ |= 0x00000004;
        type_ = value;
        onChanged();
        return this;
      }
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000004);
        type_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGHeroCommentDelProto)
    }
    
    static {
      defaultInstance = new CGHeroCommentDelProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGHeroCommentDelProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGHeroCommentDelProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGHeroCommentDelProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031CGHeroCommentDelMsg.proto\022\006buffer\"O\n\025C" +
      "GHeroCommentDelProto\022\026\n\007msgType\030\001 \001(\005:\0052" +
      "4016\022\020\n\010heroCode\030\002 \001(\005\022\014\n\004type\030\003 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGHeroCommentDelProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGHeroCommentDelProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGHeroCommentDelProto_descriptor,
              new java.lang.String[] { "MsgType", "HeroCode", "Type", },
              buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto.class,
              buffer.CGHeroCommentDelMsg.CGHeroCommentDelProto.Builder.class);
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
