// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCRoleBattleSelect.proto

package buffer;

public final class GCRoleBattleSelect {
  private GCRoleBattleSelect() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCRoleBattleSelectProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 24101];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 status = 2;
    boolean hasStatus();
    int getStatus();
    
    // optional int32 battleType = 3;
    boolean hasBattleType();
    int getBattleType();
  }
  public static final class GCRoleBattleSelectProto extends
      com.google.protobuf.GeneratedMessage
      implements GCRoleBattleSelectProtoOrBuilder {
    // Use GCRoleBattleSelectProto.newBuilder() to construct.
    private GCRoleBattleSelectProto(Builder builder) {
      super(builder);
    }
    private GCRoleBattleSelectProto(boolean noInit) {}
    
    private static final GCRoleBattleSelectProto defaultInstance;
    public static GCRoleBattleSelectProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCRoleBattleSelectProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCRoleBattleSelect.internal_static_buffer_GCRoleBattleSelectProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCRoleBattleSelect.internal_static_buffer_GCRoleBattleSelectProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 24101];
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
    
    // optional int32 battleType = 3;
    public static final int BATTLETYPE_FIELD_NUMBER = 3;
    private int battleType_;
    public boolean hasBattleType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getBattleType() {
      return battleType_;
    }
    
    private void initFields() {
      msgType_ = 24101;
      status_ = 0;
      battleType_ = 0;
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
        output.writeInt32(3, battleType_);
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
          .computeInt32Size(3, battleType_);
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
    
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseDelimitedFrom(
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
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCRoleBattleSelect.GCRoleBattleSelectProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCRoleBattleSelect.GCRoleBattleSelectProto prototype) {
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
       implements buffer.GCRoleBattleSelect.GCRoleBattleSelectProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCRoleBattleSelect.internal_static_buffer_GCRoleBattleSelectProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCRoleBattleSelect.internal_static_buffer_GCRoleBattleSelectProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCRoleBattleSelect.GCRoleBattleSelectProto.newBuilder()
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
        msgType_ = 24101;
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        battleType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCRoleBattleSelect.GCRoleBattleSelectProto.getDescriptor();
      }
      
      public buffer.GCRoleBattleSelect.GCRoleBattleSelectProto getDefaultInstanceForType() {
        return buffer.GCRoleBattleSelect.GCRoleBattleSelectProto.getDefaultInstance();
      }
      
      public buffer.GCRoleBattleSelect.GCRoleBattleSelectProto build() {
        buffer.GCRoleBattleSelect.GCRoleBattleSelectProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCRoleBattleSelect.GCRoleBattleSelectProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCRoleBattleSelect.GCRoleBattleSelectProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCRoleBattleSelect.GCRoleBattleSelectProto buildPartial() {
        buffer.GCRoleBattleSelect.GCRoleBattleSelectProto result = new buffer.GCRoleBattleSelect.GCRoleBattleSelectProto(this);
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
        result.battleType_ = battleType_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCRoleBattleSelect.GCRoleBattleSelectProto) {
          return mergeFrom((buffer.GCRoleBattleSelect.GCRoleBattleSelectProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCRoleBattleSelect.GCRoleBattleSelectProto other) {
        if (other == buffer.GCRoleBattleSelect.GCRoleBattleSelectProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasBattleType()) {
          setBattleType(other.getBattleType());
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
            case 24: {
              bitField0_ |= 0x00000004;
              battleType_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 24101];
      private int msgType_ = 24101;
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
        msgType_ = 24101;
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
      
      // optional int32 battleType = 3;
      private int battleType_ ;
      public boolean hasBattleType() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getBattleType() {
        return battleType_;
      }
      public Builder setBattleType(int value) {
        bitField0_ |= 0x00000004;
        battleType_ = value;
        onChanged();
        return this;
      }
      public Builder clearBattleType() {
        bitField0_ = (bitField0_ & ~0x00000004);
        battleType_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCRoleBattleSelectProto)
    }
    
    static {
      defaultInstance = new GCRoleBattleSelectProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCRoleBattleSelectProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCRoleBattleSelectProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCRoleBattleSelectProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030GCRoleBattleSelect.proto\022\006buffer\"U\n\027GC" +
      "RoleBattleSelectProto\022\026\n\007msgType\030\001 \001(\005:\005" +
      "24101\022\016\n\006status\030\002 \001(\005\022\022\n\nbattleType\030\003 \001(" +
      "\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCRoleBattleSelectProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCRoleBattleSelectProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCRoleBattleSelectProto_descriptor,
              new java.lang.String[] { "MsgType", "Status", "BattleType", },
              buffer.GCRoleBattleSelect.GCRoleBattleSelectProto.class,
              buffer.GCRoleBattleSelect.GCRoleBattleSelectProto.Builder.class);
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
