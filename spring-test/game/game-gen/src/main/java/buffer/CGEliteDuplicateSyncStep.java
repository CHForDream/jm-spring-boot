// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGEliteDuplicateSyncStep.proto

package buffer;

public final class CGEliteDuplicateSyncStep {
  private CGEliteDuplicateSyncStep() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGEliteDuplicateSyncStepProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 25203];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 eliteId = 2;
    boolean hasEliteId();
    int getEliteId();
    
    // optional int32 leftSteps = 3;
    boolean hasLeftSteps();
    int getLeftSteps();
  }
  public static final class CGEliteDuplicateSyncStepProto extends
      com.google.protobuf.GeneratedMessage
      implements CGEliteDuplicateSyncStepProtoOrBuilder {
    // Use CGEliteDuplicateSyncStepProto.newBuilder() to construct.
    private CGEliteDuplicateSyncStepProto(Builder builder) {
      super(builder);
    }
    private CGEliteDuplicateSyncStepProto(boolean noInit) {}
    
    private static final CGEliteDuplicateSyncStepProto defaultInstance;
    public static CGEliteDuplicateSyncStepProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGEliteDuplicateSyncStepProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGEliteDuplicateSyncStep.internal_static_buffer_CGEliteDuplicateSyncStepProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGEliteDuplicateSyncStep.internal_static_buffer_CGEliteDuplicateSyncStepProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 25203];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 eliteId = 2;
    public static final int ELITEID_FIELD_NUMBER = 2;
    private int eliteId_;
    public boolean hasEliteId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getEliteId() {
      return eliteId_;
    }
    
    // optional int32 leftSteps = 3;
    public static final int LEFTSTEPS_FIELD_NUMBER = 3;
    private int leftSteps_;
    public boolean hasLeftSteps() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getLeftSteps() {
      return leftSteps_;
    }
    
    private void initFields() {
      msgType_ = 25203;
      eliteId_ = 0;
      leftSteps_ = 0;
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
        output.writeInt32(2, eliteId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, leftSteps_);
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
          .computeInt32Size(2, eliteId_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, leftSteps_);
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
    
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseDelimitedFrom(
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
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto prototype) {
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
       implements buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGEliteDuplicateSyncStep.internal_static_buffer_CGEliteDuplicateSyncStepProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGEliteDuplicateSyncStep.internal_static_buffer_CGEliteDuplicateSyncStepProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto.newBuilder()
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
        msgType_ = 25203;
        bitField0_ = (bitField0_ & ~0x00000001);
        eliteId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        leftSteps_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto.getDescriptor();
      }
      
      public buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto getDefaultInstanceForType() {
        return buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto.getDefaultInstance();
      }
      
      public buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto build() {
        buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto buildPartial() {
        buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto result = new buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.eliteId_ = eliteId_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.leftSteps_ = leftSteps_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto) {
          return mergeFrom((buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto other) {
        if (other == buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasEliteId()) {
          setEliteId(other.getEliteId());
        }
        if (other.hasLeftSteps()) {
          setLeftSteps(other.getLeftSteps());
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
              eliteId_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              leftSteps_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 25203];
      private int msgType_ = 25203;
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
        msgType_ = 25203;
        onChanged();
        return this;
      }
      
      // optional int32 eliteId = 2;
      private int eliteId_ ;
      public boolean hasEliteId() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getEliteId() {
        return eliteId_;
      }
      public Builder setEliteId(int value) {
        bitField0_ |= 0x00000002;
        eliteId_ = value;
        onChanged();
        return this;
      }
      public Builder clearEliteId() {
        bitField0_ = (bitField0_ & ~0x00000002);
        eliteId_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 leftSteps = 3;
      private int leftSteps_ ;
      public boolean hasLeftSteps() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getLeftSteps() {
        return leftSteps_;
      }
      public Builder setLeftSteps(int value) {
        bitField0_ |= 0x00000004;
        leftSteps_ = value;
        onChanged();
        return this;
      }
      public Builder clearLeftSteps() {
        bitField0_ = (bitField0_ & ~0x00000004);
        leftSteps_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGEliteDuplicateSyncStepProto)
    }
    
    static {
      defaultInstance = new CGEliteDuplicateSyncStepProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGEliteDuplicateSyncStepProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGEliteDuplicateSyncStepProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGEliteDuplicateSyncStepProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\036CGEliteDuplicateSyncStep.proto\022\006buffer" +
      "\"[\n\035CGEliteDuplicateSyncStepProto\022\026\n\007msg" +
      "Type\030\001 \001(\005:\00525203\022\017\n\007eliteId\030\002 \001(\005\022\021\n\tle" +
      "ftSteps\030\003 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGEliteDuplicateSyncStepProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGEliteDuplicateSyncStepProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGEliteDuplicateSyncStepProto_descriptor,
              new java.lang.String[] { "MsgType", "EliteId", "LeftSteps", },
              buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto.class,
              buffer.CGEliteDuplicateSyncStep.CGEliteDuplicateSyncStepProto.Builder.class);
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
