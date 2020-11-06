// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGReceiveSevenSignRewardMsg.proto

package buffer;

public final class CGReceiveSevenSignRewardMsg {
  private CGReceiveSevenSignRewardMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGReceiveSevenSignRewardProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 3305];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 day = 2;
    boolean hasDay();
    int getDay();
  }
  public static final class CGReceiveSevenSignRewardProto extends
      com.google.protobuf.GeneratedMessage
      implements CGReceiveSevenSignRewardProtoOrBuilder {
    // Use CGReceiveSevenSignRewardProto.newBuilder() to construct.
    private CGReceiveSevenSignRewardProto(Builder builder) {
      super(builder);
    }
    private CGReceiveSevenSignRewardProto(boolean noInit) {}
    
    private static final CGReceiveSevenSignRewardProto defaultInstance;
    public static CGReceiveSevenSignRewardProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGReceiveSevenSignRewardProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGReceiveSevenSignRewardMsg.internal_static_buffer_CGReceiveSevenSignRewardProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGReceiveSevenSignRewardMsg.internal_static_buffer_CGReceiveSevenSignRewardProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 3305];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 day = 2;
    public static final int DAY_FIELD_NUMBER = 2;
    private int day_;
    public boolean hasDay() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getDay() {
      return day_;
    }
    
    private void initFields() {
      msgType_ = 3305;
      day_ = 0;
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
        output.writeInt32(2, day_);
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
          .computeInt32Size(2, day_);
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
    
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseDelimitedFrom(
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
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto prototype) {
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
       implements buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGReceiveSevenSignRewardMsg.internal_static_buffer_CGReceiveSevenSignRewardProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGReceiveSevenSignRewardMsg.internal_static_buffer_CGReceiveSevenSignRewardProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto.newBuilder()
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
        msgType_ = 3305;
        bitField0_ = (bitField0_ & ~0x00000001);
        day_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto.getDescriptor();
      }
      
      public buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto getDefaultInstanceForType() {
        return buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto.getDefaultInstance();
      }
      
      public buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto build() {
        buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto buildPartial() {
        buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto result = new buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.day_ = day_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto) {
          return mergeFrom((buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto other) {
        if (other == buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasDay()) {
          setDay(other.getDay());
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
              day_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 3305];
      private int msgType_ = 3305;
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
        msgType_ = 3305;
        onChanged();
        return this;
      }
      
      // optional int32 day = 2;
      private int day_ ;
      public boolean hasDay() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getDay() {
        return day_;
      }
      public Builder setDay(int value) {
        bitField0_ |= 0x00000002;
        day_ = value;
        onChanged();
        return this;
      }
      public Builder clearDay() {
        bitField0_ = (bitField0_ & ~0x00000002);
        day_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGReceiveSevenSignRewardProto)
    }
    
    static {
      defaultInstance = new CGReceiveSevenSignRewardProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGReceiveSevenSignRewardProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGReceiveSevenSignRewardProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGReceiveSevenSignRewardProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!CGReceiveSevenSignRewardMsg.proto\022\006buf" +
      "fer\"C\n\035CGReceiveSevenSignRewardProto\022\025\n\007" +
      "msgType\030\001 \001(\005:\0043305\022\013\n\003day\030\002 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGReceiveSevenSignRewardProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGReceiveSevenSignRewardProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGReceiveSevenSignRewardProto_descriptor,
              new java.lang.String[] { "MsgType", "Day", },
              buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto.class,
              buffer.CGReceiveSevenSignRewardMsg.CGReceiveSevenSignRewardProto.Builder.class);
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