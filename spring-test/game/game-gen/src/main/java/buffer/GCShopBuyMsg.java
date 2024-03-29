// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCShopBuyMsg.proto

package buffer;

public final class GCShopBuyMsg {
  private GCShopBuyMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCShopBuyProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 2230];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 status = 2;
    boolean hasStatus();
    int getStatus();
    
    // optional int32 shopId = 3;
    boolean hasShopId();
    int getShopId();
    
    // optional int32 num = 4;
    boolean hasNum();
    int getNum();
    
    // optional int32 cash = 5;
    boolean hasCash();
    int getCash();
    
    // optional int32 gold = 6;
    boolean hasGold();
    int getGold();
    
    // optional int64 dbId = 7;
    boolean hasDbId();
    long getDbId();
  }
  public static final class GCShopBuyProto extends
      com.google.protobuf.GeneratedMessage
      implements GCShopBuyProtoOrBuilder {
    // Use GCShopBuyProto.newBuilder() to construct.
    private GCShopBuyProto(Builder builder) {
      super(builder);
    }
    private GCShopBuyProto(boolean noInit) {}
    
    private static final GCShopBuyProto defaultInstance;
    public static GCShopBuyProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCShopBuyProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCShopBuyMsg.internal_static_buffer_GCShopBuyProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCShopBuyMsg.internal_static_buffer_GCShopBuyProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 2230];
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
    
    // optional int32 shopId = 3;
    public static final int SHOPID_FIELD_NUMBER = 3;
    private int shopId_;
    public boolean hasShopId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getShopId() {
      return shopId_;
    }
    
    // optional int32 num = 4;
    public static final int NUM_FIELD_NUMBER = 4;
    private int num_;
    public boolean hasNum() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public int getNum() {
      return num_;
    }
    
    // optional int32 cash = 5;
    public static final int CASH_FIELD_NUMBER = 5;
    private int cash_;
    public boolean hasCash() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public int getCash() {
      return cash_;
    }
    
    // optional int32 gold = 6;
    public static final int GOLD_FIELD_NUMBER = 6;
    private int gold_;
    public boolean hasGold() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    public int getGold() {
      return gold_;
    }
    
    // optional int64 dbId = 7;
    public static final int DBID_FIELD_NUMBER = 7;
    private long dbId_;
    public boolean hasDbId() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    public long getDbId() {
      return dbId_;
    }
    
    private void initFields() {
      msgType_ = 2230;
      status_ = 0;
      shopId_ = 0;
      num_ = 0;
      cash_ = 0;
      gold_ = 0;
      dbId_ = 0L;
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
        output.writeInt32(3, shopId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, num_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, cash_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt32(6, gold_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeInt64(7, dbId_);
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
          .computeInt32Size(3, shopId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, num_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, cash_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, gold_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(7, dbId_);
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
    
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseDelimitedFrom(
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
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCShopBuyMsg.GCShopBuyProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCShopBuyMsg.GCShopBuyProto prototype) {
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
       implements buffer.GCShopBuyMsg.GCShopBuyProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCShopBuyMsg.internal_static_buffer_GCShopBuyProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCShopBuyMsg.internal_static_buffer_GCShopBuyProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCShopBuyMsg.GCShopBuyProto.newBuilder()
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
        msgType_ = 2230;
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        shopId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        num_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        cash_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        gold_ = 0;
        bitField0_ = (bitField0_ & ~0x00000020);
        dbId_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000040);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCShopBuyMsg.GCShopBuyProto.getDescriptor();
      }
      
      public buffer.GCShopBuyMsg.GCShopBuyProto getDefaultInstanceForType() {
        return buffer.GCShopBuyMsg.GCShopBuyProto.getDefaultInstance();
      }
      
      public buffer.GCShopBuyMsg.GCShopBuyProto build() {
        buffer.GCShopBuyMsg.GCShopBuyProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCShopBuyMsg.GCShopBuyProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCShopBuyMsg.GCShopBuyProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCShopBuyMsg.GCShopBuyProto buildPartial() {
        buffer.GCShopBuyMsg.GCShopBuyProto result = new buffer.GCShopBuyMsg.GCShopBuyProto(this);
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
        result.shopId_ = shopId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.num_ = num_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.cash_ = cash_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.gold_ = gold_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.dbId_ = dbId_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCShopBuyMsg.GCShopBuyProto) {
          return mergeFrom((buffer.GCShopBuyMsg.GCShopBuyProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCShopBuyMsg.GCShopBuyProto other) {
        if (other == buffer.GCShopBuyMsg.GCShopBuyProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasShopId()) {
          setShopId(other.getShopId());
        }
        if (other.hasNum()) {
          setNum(other.getNum());
        }
        if (other.hasCash()) {
          setCash(other.getCash());
        }
        if (other.hasGold()) {
          setGold(other.getGold());
        }
        if (other.hasDbId()) {
          setDbId(other.getDbId());
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
              shopId_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              num_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              cash_ = input.readInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              gold_ = input.readInt32();
              break;
            }
            case 56: {
              bitField0_ |= 0x00000040;
              dbId_ = input.readInt64();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 2230];
      private int msgType_ = 2230;
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
        msgType_ = 2230;
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
      
      // optional int32 shopId = 3;
      private int shopId_ ;
      public boolean hasShopId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getShopId() {
        return shopId_;
      }
      public Builder setShopId(int value) {
        bitField0_ |= 0x00000004;
        shopId_ = value;
        onChanged();
        return this;
      }
      public Builder clearShopId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        shopId_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 num = 4;
      private int num_ ;
      public boolean hasNum() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public int getNum() {
        return num_;
      }
      public Builder setNum(int value) {
        bitField0_ |= 0x00000008;
        num_ = value;
        onChanged();
        return this;
      }
      public Builder clearNum() {
        bitField0_ = (bitField0_ & ~0x00000008);
        num_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 cash = 5;
      private int cash_ ;
      public boolean hasCash() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public int getCash() {
        return cash_;
      }
      public Builder setCash(int value) {
        bitField0_ |= 0x00000010;
        cash_ = value;
        onChanged();
        return this;
      }
      public Builder clearCash() {
        bitField0_ = (bitField0_ & ~0x00000010);
        cash_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 gold = 6;
      private int gold_ ;
      public boolean hasGold() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      public int getGold() {
        return gold_;
      }
      public Builder setGold(int value) {
        bitField0_ |= 0x00000020;
        gold_ = value;
        onChanged();
        return this;
      }
      public Builder clearGold() {
        bitField0_ = (bitField0_ & ~0x00000020);
        gold_ = 0;
        onChanged();
        return this;
      }
      
      // optional int64 dbId = 7;
      private long dbId_ ;
      public boolean hasDbId() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      public long getDbId() {
        return dbId_;
      }
      public Builder setDbId(long value) {
        bitField0_ |= 0x00000040;
        dbId_ = value;
        onChanged();
        return this;
      }
      public Builder clearDbId() {
        bitField0_ = (bitField0_ & ~0x00000040);
        dbId_ = 0L;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCShopBuyProto)
    }
    
    static {
      defaultInstance = new GCShopBuyProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCShopBuyProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCShopBuyProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCShopBuyProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022GCShopBuyMsg.proto\022\006buffer\"~\n\016GCShopBu" +
      "yProto\022\025\n\007msgType\030\001 \001(\005:\0042230\022\016\n\006status\030" +
      "\002 \001(\005\022\016\n\006shopId\030\003 \001(\005\022\013\n\003num\030\004 \001(\005\022\014\n\004ca" +
      "sh\030\005 \001(\005\022\014\n\004gold\030\006 \001(\005\022\014\n\004dbId\030\007 \001(\003"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCShopBuyProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCShopBuyProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCShopBuyProto_descriptor,
              new java.lang.String[] { "MsgType", "Status", "ShopId", "Num", "Cash", "Gold", "DbId", },
              buffer.GCShopBuyMsg.GCShopBuyProto.class,
              buffer.GCShopBuyMsg.GCShopBuyProto.Builder.class);
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
