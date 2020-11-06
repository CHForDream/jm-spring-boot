// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGHeroCommentMsg.proto

package buffer;

public final class CGHeroCommentMsg {
  private CGHeroCommentMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGHeroCommentProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 24013];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 heroCode = 2;
    boolean hasHeroCode();
    int getHeroCode();
    
    // optional string comment = 3;
    boolean hasComment();
    String getComment();
    
    // optional int32 type = 4;
    boolean hasType();
    int getType();
  }
  public static final class CGHeroCommentProto extends
      com.google.protobuf.GeneratedMessage
      implements CGHeroCommentProtoOrBuilder {
    // Use CGHeroCommentProto.newBuilder() to construct.
    private CGHeroCommentProto(Builder builder) {
      super(builder);
    }
    private CGHeroCommentProto(boolean noInit) {}
    
    private static final CGHeroCommentProto defaultInstance;
    public static CGHeroCommentProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGHeroCommentProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGHeroCommentMsg.internal_static_buffer_CGHeroCommentProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGHeroCommentMsg.internal_static_buffer_CGHeroCommentProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 24013];
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
    
    // optional string comment = 3;
    public static final int COMMENT_FIELD_NUMBER = 3;
    private java.lang.Object comment_;
    public boolean hasComment() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getComment() {
      java.lang.Object ref = comment_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          comment_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getCommentBytes() {
      java.lang.Object ref = comment_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        comment_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional int32 type = 4;
    public static final int TYPE_FIELD_NUMBER = 4;
    private int type_;
    public boolean hasType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public int getType() {
      return type_;
    }
    
    private void initFields() {
      msgType_ = 24013;
      heroCode_ = 0;
      comment_ = "";
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
        output.writeBytes(3, getCommentBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, type_);
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
          .computeBytesSize(3, getCommentBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, type_);
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
    
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseDelimitedFrom(
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
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGHeroCommentMsg.CGHeroCommentProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGHeroCommentMsg.CGHeroCommentProto prototype) {
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
       implements buffer.CGHeroCommentMsg.CGHeroCommentProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGHeroCommentMsg.internal_static_buffer_CGHeroCommentProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGHeroCommentMsg.internal_static_buffer_CGHeroCommentProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGHeroCommentMsg.CGHeroCommentProto.newBuilder()
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
        msgType_ = 24013;
        bitField0_ = (bitField0_ & ~0x00000001);
        heroCode_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        comment_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        type_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGHeroCommentMsg.CGHeroCommentProto.getDescriptor();
      }
      
      public buffer.CGHeroCommentMsg.CGHeroCommentProto getDefaultInstanceForType() {
        return buffer.CGHeroCommentMsg.CGHeroCommentProto.getDefaultInstance();
      }
      
      public buffer.CGHeroCommentMsg.CGHeroCommentProto build() {
        buffer.CGHeroCommentMsg.CGHeroCommentProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGHeroCommentMsg.CGHeroCommentProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGHeroCommentMsg.CGHeroCommentProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGHeroCommentMsg.CGHeroCommentProto buildPartial() {
        buffer.CGHeroCommentMsg.CGHeroCommentProto result = new buffer.CGHeroCommentMsg.CGHeroCommentProto(this);
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
        result.comment_ = comment_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.type_ = type_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGHeroCommentMsg.CGHeroCommentProto) {
          return mergeFrom((buffer.CGHeroCommentMsg.CGHeroCommentProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGHeroCommentMsg.CGHeroCommentProto other) {
        if (other == buffer.CGHeroCommentMsg.CGHeroCommentProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasHeroCode()) {
          setHeroCode(other.getHeroCode());
        }
        if (other.hasComment()) {
          setComment(other.getComment());
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
            case 26: {
              bitField0_ |= 0x00000004;
              comment_ = input.readBytes();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              type_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 24013];
      private int msgType_ = 24013;
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
        msgType_ = 24013;
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
      
      // optional string comment = 3;
      private java.lang.Object comment_ = "";
      public boolean hasComment() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getComment() {
        java.lang.Object ref = comment_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          comment_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setComment(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        comment_ = value;
        onChanged();
        return this;
      }
      public Builder clearComment() {
        bitField0_ = (bitField0_ & ~0x00000004);
        comment_ = getDefaultInstance().getComment();
        onChanged();
        return this;
      }
      void setComment(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        comment_ = value;
        onChanged();
      }
      
      // optional int32 type = 4;
      private int type_ ;
      public boolean hasType() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public int getType() {
        return type_;
      }
      public Builder setType(int value) {
        bitField0_ |= 0x00000008;
        type_ = value;
        onChanged();
        return this;
      }
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000008);
        type_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGHeroCommentProto)
    }
    
    static {
      defaultInstance = new CGHeroCommentProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGHeroCommentProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGHeroCommentProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGHeroCommentProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026CGHeroCommentMsg.proto\022\006buffer\"]\n\022CGHe" +
      "roCommentProto\022\026\n\007msgType\030\001 \001(\005:\00524013\022\020" +
      "\n\010heroCode\030\002 \001(\005\022\017\n\007comment\030\003 \001(\t\022\014\n\004typ" +
      "e\030\004 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGHeroCommentProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGHeroCommentProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGHeroCommentProto_descriptor,
              new java.lang.String[] { "MsgType", "HeroCode", "Comment", "Type", },
              buffer.CGHeroCommentMsg.CGHeroCommentProto.class,
              buffer.CGHeroCommentMsg.CGHeroCommentProto.Builder.class);
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
