// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCCommonResponseMsg.proto

package buffer;

public final class GCCommonResponseMsg {
  private GCCommonResponseMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCCommonResponseProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 9001];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 status = 2;
    boolean hasStatus();
    int getStatus();
    
    // optional int32 gold = 3;
    boolean hasGold();
    int getGold();
    
    // optional int32 homeMoney = 4;
    boolean hasHomeMoney();
    int getHomeMoney();
    
    // optional int32 blueCash = 5;
    boolean hasBlueCash();
    int getBlueCash();
    
    // optional int32 rewardBlueCash = 6;
    boolean hasRewardBlueCash();
    int getRewardBlueCash();
    
    // optional int32 dupStar = 7;
    boolean hasDupStar();
    int getDupStar();
  }
  public static final class GCCommonResponseProto extends
      com.google.protobuf.GeneratedMessage
      implements GCCommonResponseProtoOrBuilder {
    // Use GCCommonResponseProto.newBuilder() to construct.
    private GCCommonResponseProto(Builder builder) {
      super(builder);
    }
    private GCCommonResponseProto(boolean noInit) {}
    
    private static final GCCommonResponseProto defaultInstance;
    public static GCCommonResponseProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCCommonResponseProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCCommonResponseMsg.internal_static_buffer_GCCommonResponseProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCCommonResponseMsg.internal_static_buffer_GCCommonResponseProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 9001];
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
    
    // optional int32 gold = 3;
    public static final int GOLD_FIELD_NUMBER = 3;
    private int gold_;
    public boolean hasGold() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getGold() {
      return gold_;
    }
    
    // optional int32 homeMoney = 4;
    public static final int HOMEMONEY_FIELD_NUMBER = 4;
    private int homeMoney_;
    public boolean hasHomeMoney() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public int getHomeMoney() {
      return homeMoney_;
    }
    
    // optional int32 blueCash = 5;
    public static final int BLUECASH_FIELD_NUMBER = 5;
    private int blueCash_;
    public boolean hasBlueCash() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public int getBlueCash() {
      return blueCash_;
    }
    
    // optional int32 rewardBlueCash = 6;
    public static final int REWARDBLUECASH_FIELD_NUMBER = 6;
    private int rewardBlueCash_;
    public boolean hasRewardBlueCash() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    public int getRewardBlueCash() {
      return rewardBlueCash_;
    }
    
    // optional int32 dupStar = 7;
    public static final int DUPSTAR_FIELD_NUMBER = 7;
    private int dupStar_;
    public boolean hasDupStar() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    public int getDupStar() {
      return dupStar_;
    }
    
    private void initFields() {
      msgType_ = 9001;
      status_ = 0;
      gold_ = 0;
      homeMoney_ = 0;
      blueCash_ = 0;
      rewardBlueCash_ = 0;
      dupStar_ = 0;
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
        output.writeInt32(3, gold_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, homeMoney_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, blueCash_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt32(6, rewardBlueCash_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeInt32(7, dupStar_);
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
          .computeInt32Size(3, gold_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, homeMoney_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, blueCash_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, rewardBlueCash_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(7, dupStar_);
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
    
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseDelimitedFrom(
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
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCCommonResponseMsg.GCCommonResponseProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCCommonResponseMsg.GCCommonResponseProto prototype) {
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
       implements buffer.GCCommonResponseMsg.GCCommonResponseProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCCommonResponseMsg.internal_static_buffer_GCCommonResponseProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCCommonResponseMsg.internal_static_buffer_GCCommonResponseProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCCommonResponseMsg.GCCommonResponseProto.newBuilder()
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
        msgType_ = 9001;
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        gold_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        homeMoney_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        blueCash_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        rewardBlueCash_ = 0;
        bitField0_ = (bitField0_ & ~0x00000020);
        dupStar_ = 0;
        bitField0_ = (bitField0_ & ~0x00000040);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCCommonResponseMsg.GCCommonResponseProto.getDescriptor();
      }
      
      public buffer.GCCommonResponseMsg.GCCommonResponseProto getDefaultInstanceForType() {
        return buffer.GCCommonResponseMsg.GCCommonResponseProto.getDefaultInstance();
      }
      
      public buffer.GCCommonResponseMsg.GCCommonResponseProto build() {
        buffer.GCCommonResponseMsg.GCCommonResponseProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCCommonResponseMsg.GCCommonResponseProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCCommonResponseMsg.GCCommonResponseProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCCommonResponseMsg.GCCommonResponseProto buildPartial() {
        buffer.GCCommonResponseMsg.GCCommonResponseProto result = new buffer.GCCommonResponseMsg.GCCommonResponseProto(this);
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
        result.gold_ = gold_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.homeMoney_ = homeMoney_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.blueCash_ = blueCash_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.rewardBlueCash_ = rewardBlueCash_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.dupStar_ = dupStar_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCCommonResponseMsg.GCCommonResponseProto) {
          return mergeFrom((buffer.GCCommonResponseMsg.GCCommonResponseProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCCommonResponseMsg.GCCommonResponseProto other) {
        if (other == buffer.GCCommonResponseMsg.GCCommonResponseProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasGold()) {
          setGold(other.getGold());
        }
        if (other.hasHomeMoney()) {
          setHomeMoney(other.getHomeMoney());
        }
        if (other.hasBlueCash()) {
          setBlueCash(other.getBlueCash());
        }
        if (other.hasRewardBlueCash()) {
          setRewardBlueCash(other.getRewardBlueCash());
        }
        if (other.hasDupStar()) {
          setDupStar(other.getDupStar());
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
              gold_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              homeMoney_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              blueCash_ = input.readInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              rewardBlueCash_ = input.readInt32();
              break;
            }
            case 56: {
              bitField0_ |= 0x00000040;
              dupStar_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 9001];
      private int msgType_ = 9001;
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
        msgType_ = 9001;
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
      
      // optional int32 gold = 3;
      private int gold_ ;
      public boolean hasGold() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getGold() {
        return gold_;
      }
      public Builder setGold(int value) {
        bitField0_ |= 0x00000004;
        gold_ = value;
        onChanged();
        return this;
      }
      public Builder clearGold() {
        bitField0_ = (bitField0_ & ~0x00000004);
        gold_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 homeMoney = 4;
      private int homeMoney_ ;
      public boolean hasHomeMoney() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public int getHomeMoney() {
        return homeMoney_;
      }
      public Builder setHomeMoney(int value) {
        bitField0_ |= 0x00000008;
        homeMoney_ = value;
        onChanged();
        return this;
      }
      public Builder clearHomeMoney() {
        bitField0_ = (bitField0_ & ~0x00000008);
        homeMoney_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 blueCash = 5;
      private int blueCash_ ;
      public boolean hasBlueCash() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public int getBlueCash() {
        return blueCash_;
      }
      public Builder setBlueCash(int value) {
        bitField0_ |= 0x00000010;
        blueCash_ = value;
        onChanged();
        return this;
      }
      public Builder clearBlueCash() {
        bitField0_ = (bitField0_ & ~0x00000010);
        blueCash_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 rewardBlueCash = 6;
      private int rewardBlueCash_ ;
      public boolean hasRewardBlueCash() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      public int getRewardBlueCash() {
        return rewardBlueCash_;
      }
      public Builder setRewardBlueCash(int value) {
        bitField0_ |= 0x00000020;
        rewardBlueCash_ = value;
        onChanged();
        return this;
      }
      public Builder clearRewardBlueCash() {
        bitField0_ = (bitField0_ & ~0x00000020);
        rewardBlueCash_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 dupStar = 7;
      private int dupStar_ ;
      public boolean hasDupStar() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      public int getDupStar() {
        return dupStar_;
      }
      public Builder setDupStar(int value) {
        bitField0_ |= 0x00000040;
        dupStar_ = value;
        onChanged();
        return this;
      }
      public Builder clearDupStar() {
        bitField0_ = (bitField0_ & ~0x00000040);
        dupStar_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCCommonResponseProto)
    }
    
    static {
      defaultInstance = new GCCommonResponseProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCCommonResponseProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCCommonResponseProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCCommonResponseProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031GCCommonResponseMsg.proto\022\006buffer\"\232\001\n\025" +
      "GCCommonResponseProto\022\025\n\007msgType\030\001 \001(\005:\004" +
      "9001\022\016\n\006status\030\002 \001(\005\022\014\n\004gold\030\003 \001(\005\022\021\n\tho" +
      "meMoney\030\004 \001(\005\022\020\n\010blueCash\030\005 \001(\005\022\026\n\016rewar" +
      "dBlueCash\030\006 \001(\005\022\017\n\007dupStar\030\007 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCCommonResponseProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCCommonResponseProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCCommonResponseProto_descriptor,
              new java.lang.String[] { "MsgType", "Status", "Gold", "HomeMoney", "BlueCash", "RewardBlueCash", "DupStar", },
              buffer.GCCommonResponseMsg.GCCommonResponseProto.class,
              buffer.GCCommonResponseMsg.GCCommonResponseProto.Builder.class);
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