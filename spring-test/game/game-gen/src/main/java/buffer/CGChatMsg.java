// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CGChatMsg.proto

package buffer;

public final class CGChatMsg {
  private CGChatMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface CGChatProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 2004];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 chatType = 2;
    boolean hasChatType();
    int getChatType();
    
    // optional int32 chatParam = 3;
    boolean hasChatParam();
    int getChatParam();
    
    // optional int64 fromUid = 4;
    boolean hasFromUid();
    long getFromUid();
    
    // optional int64 toUid = 5;
    boolean hasToUid();
    long getToUid();
    
    // optional string name = 6;
    boolean hasName();
    String getName();
    
    // optional string aren = 7;
    boolean hasAren();
    String getAren();
    
    // optional int32 sex = 8;
    boolean hasSex();
    int getSex();
    
    // optional string head = 9;
    boolean hasHead();
    String getHead();
    
    // optional int32 lv = 10;
    boolean hasLv();
    int getLv();
    
    // optional string cont = 11;
    boolean hasCont();
    String getCont();
    
    // optional bytes voice = 12;
    boolean hasVoice();
    com.google.protobuf.ByteString getVoice();
  }
  public static final class CGChatProto extends
      com.google.protobuf.GeneratedMessage
      implements CGChatProtoOrBuilder {
    // Use CGChatProto.newBuilder() to construct.
    private CGChatProto(Builder builder) {
      super(builder);
    }
    private CGChatProto(boolean noInit) {}
    
    private static final CGChatProto defaultInstance;
    public static CGChatProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public CGChatProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.CGChatMsg.internal_static_buffer_CGChatProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.CGChatMsg.internal_static_buffer_CGChatProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 2004];
    public static final int MSGTYPE_FIELD_NUMBER = 1;
    private int msgType_;
    public boolean hasMsgType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getMsgType() {
      return msgType_;
    }
    
    // optional int32 chatType = 2;
    public static final int CHATTYPE_FIELD_NUMBER = 2;
    private int chatType_;
    public boolean hasChatType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public int getChatType() {
      return chatType_;
    }
    
    // optional int32 chatParam = 3;
    public static final int CHATPARAM_FIELD_NUMBER = 3;
    private int chatParam_;
    public boolean hasChatParam() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getChatParam() {
      return chatParam_;
    }
    
    // optional int64 fromUid = 4;
    public static final int FROMUID_FIELD_NUMBER = 4;
    private long fromUid_;
    public boolean hasFromUid() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public long getFromUid() {
      return fromUid_;
    }
    
    // optional int64 toUid = 5;
    public static final int TOUID_FIELD_NUMBER = 5;
    private long toUid_;
    public boolean hasToUid() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public long getToUid() {
      return toUid_;
    }
    
    // optional string name = 6;
    public static final int NAME_FIELD_NUMBER = 6;
    private java.lang.Object name_;
    public boolean hasName() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    public String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          name_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional string aren = 7;
    public static final int AREN_FIELD_NUMBER = 7;
    private java.lang.Object aren_;
    public boolean hasAren() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    public String getAren() {
      java.lang.Object ref = aren_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          aren_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getArenBytes() {
      java.lang.Object ref = aren_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        aren_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional int32 sex = 8;
    public static final int SEX_FIELD_NUMBER = 8;
    private int sex_;
    public boolean hasSex() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    public int getSex() {
      return sex_;
    }
    
    // optional string head = 9;
    public static final int HEAD_FIELD_NUMBER = 9;
    private java.lang.Object head_;
    public boolean hasHead() {
      return ((bitField0_ & 0x00000100) == 0x00000100);
    }
    public String getHead() {
      java.lang.Object ref = head_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          head_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getHeadBytes() {
      java.lang.Object ref = head_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        head_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional int32 lv = 10;
    public static final int LV_FIELD_NUMBER = 10;
    private int lv_;
    public boolean hasLv() {
      return ((bitField0_ & 0x00000200) == 0x00000200);
    }
    public int getLv() {
      return lv_;
    }
    
    // optional string cont = 11;
    public static final int CONT_FIELD_NUMBER = 11;
    private java.lang.Object cont_;
    public boolean hasCont() {
      return ((bitField0_ & 0x00000400) == 0x00000400);
    }
    public String getCont() {
      java.lang.Object ref = cont_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          cont_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getContBytes() {
      java.lang.Object ref = cont_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        cont_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional bytes voice = 12;
    public static final int VOICE_FIELD_NUMBER = 12;
    private com.google.protobuf.ByteString voice_;
    public boolean hasVoice() {
      return ((bitField0_ & 0x00000800) == 0x00000800);
    }
    public com.google.protobuf.ByteString getVoice() {
      return voice_;
    }
    
    private void initFields() {
      msgType_ = 2004;
      chatType_ = 0;
      chatParam_ = 0;
      fromUid_ = 0L;
      toUid_ = 0L;
      name_ = "";
      aren_ = "";
      sex_ = 0;
      head_ = "";
      lv_ = 0;
      cont_ = "";
      voice_ = com.google.protobuf.ByteString.EMPTY;
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
        output.writeInt32(2, chatType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, chatParam_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt64(4, fromUid_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt64(5, toUid_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeBytes(6, getNameBytes());
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeBytes(7, getArenBytes());
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        output.writeInt32(8, sex_);
      }
      if (((bitField0_ & 0x00000100) == 0x00000100)) {
        output.writeBytes(9, getHeadBytes());
      }
      if (((bitField0_ & 0x00000200) == 0x00000200)) {
        output.writeInt32(10, lv_);
      }
      if (((bitField0_ & 0x00000400) == 0x00000400)) {
        output.writeBytes(11, getContBytes());
      }
      if (((bitField0_ & 0x00000800) == 0x00000800)) {
        output.writeBytes(12, voice_);
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
          .computeInt32Size(2, chatType_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, chatParam_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, fromUid_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, toUid_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(6, getNameBytes());
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(7, getArenBytes());
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(8, sex_);
      }
      if (((bitField0_ & 0x00000100) == 0x00000100)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(9, getHeadBytes());
      }
      if (((bitField0_ & 0x00000200) == 0x00000200)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(10, lv_);
      }
      if (((bitField0_ & 0x00000400) == 0x00000400)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(11, getContBytes());
      }
      if (((bitField0_ & 0x00000800) == 0x00000800)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(12, voice_);
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
    
    public static buffer.CGChatMsg.CGChatProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.CGChatMsg.CGChatProto parseDelimitedFrom(
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
    public static buffer.CGChatMsg.CGChatProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.CGChatMsg.CGChatProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.CGChatMsg.CGChatProto prototype) {
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
       implements buffer.CGChatMsg.CGChatProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.CGChatMsg.internal_static_buffer_CGChatProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.CGChatMsg.internal_static_buffer_CGChatProto_fieldAccessorTable;
      }
      
      // Construct using buffer.CGChatMsg.CGChatProto.newBuilder()
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
        msgType_ = 2004;
        bitField0_ = (bitField0_ & ~0x00000001);
        chatType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        chatParam_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        fromUid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000008);
        toUid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000010);
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000020);
        aren_ = "";
        bitField0_ = (bitField0_ & ~0x00000040);
        sex_ = 0;
        bitField0_ = (bitField0_ & ~0x00000080);
        head_ = "";
        bitField0_ = (bitField0_ & ~0x00000100);
        lv_ = 0;
        bitField0_ = (bitField0_ & ~0x00000200);
        cont_ = "";
        bitField0_ = (bitField0_ & ~0x00000400);
        voice_ = com.google.protobuf.ByteString.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000800);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.CGChatMsg.CGChatProto.getDescriptor();
      }
      
      public buffer.CGChatMsg.CGChatProto getDefaultInstanceForType() {
        return buffer.CGChatMsg.CGChatProto.getDefaultInstance();
      }
      
      public buffer.CGChatMsg.CGChatProto build() {
        buffer.CGChatMsg.CGChatProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.CGChatMsg.CGChatProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.CGChatMsg.CGChatProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.CGChatMsg.CGChatProto buildPartial() {
        buffer.CGChatMsg.CGChatProto result = new buffer.CGChatMsg.CGChatProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.msgType_ = msgType_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.chatType_ = chatType_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.chatParam_ = chatParam_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.fromUid_ = fromUid_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.toUid_ = toUid_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.aren_ = aren_;
        if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
          to_bitField0_ |= 0x00000080;
        }
        result.sex_ = sex_;
        if (((from_bitField0_ & 0x00000100) == 0x00000100)) {
          to_bitField0_ |= 0x00000100;
        }
        result.head_ = head_;
        if (((from_bitField0_ & 0x00000200) == 0x00000200)) {
          to_bitField0_ |= 0x00000200;
        }
        result.lv_ = lv_;
        if (((from_bitField0_ & 0x00000400) == 0x00000400)) {
          to_bitField0_ |= 0x00000400;
        }
        result.cont_ = cont_;
        if (((from_bitField0_ & 0x00000800) == 0x00000800)) {
          to_bitField0_ |= 0x00000800;
        }
        result.voice_ = voice_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.CGChatMsg.CGChatProto) {
          return mergeFrom((buffer.CGChatMsg.CGChatProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.CGChatMsg.CGChatProto other) {
        if (other == buffer.CGChatMsg.CGChatProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasChatType()) {
          setChatType(other.getChatType());
        }
        if (other.hasChatParam()) {
          setChatParam(other.getChatParam());
        }
        if (other.hasFromUid()) {
          setFromUid(other.getFromUid());
        }
        if (other.hasToUid()) {
          setToUid(other.getToUid());
        }
        if (other.hasName()) {
          setName(other.getName());
        }
        if (other.hasAren()) {
          setAren(other.getAren());
        }
        if (other.hasSex()) {
          setSex(other.getSex());
        }
        if (other.hasHead()) {
          setHead(other.getHead());
        }
        if (other.hasLv()) {
          setLv(other.getLv());
        }
        if (other.hasCont()) {
          setCont(other.getCont());
        }
        if (other.hasVoice()) {
          setVoice(other.getVoice());
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
              chatType_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              chatParam_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              fromUid_ = input.readInt64();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              toUid_ = input.readInt64();
              break;
            }
            case 50: {
              bitField0_ |= 0x00000020;
              name_ = input.readBytes();
              break;
            }
            case 58: {
              bitField0_ |= 0x00000040;
              aren_ = input.readBytes();
              break;
            }
            case 64: {
              bitField0_ |= 0x00000080;
              sex_ = input.readInt32();
              break;
            }
            case 74: {
              bitField0_ |= 0x00000100;
              head_ = input.readBytes();
              break;
            }
            case 80: {
              bitField0_ |= 0x00000200;
              lv_ = input.readInt32();
              break;
            }
            case 90: {
              bitField0_ |= 0x00000400;
              cont_ = input.readBytes();
              break;
            }
            case 98: {
              bitField0_ |= 0x00000800;
              voice_ = input.readBytes();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 2004];
      private int msgType_ = 2004;
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
        msgType_ = 2004;
        onChanged();
        return this;
      }
      
      // optional int32 chatType = 2;
      private int chatType_ ;
      public boolean hasChatType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public int getChatType() {
        return chatType_;
      }
      public Builder setChatType(int value) {
        bitField0_ |= 0x00000002;
        chatType_ = value;
        onChanged();
        return this;
      }
      public Builder clearChatType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        chatType_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 chatParam = 3;
      private int chatParam_ ;
      public boolean hasChatParam() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getChatParam() {
        return chatParam_;
      }
      public Builder setChatParam(int value) {
        bitField0_ |= 0x00000004;
        chatParam_ = value;
        onChanged();
        return this;
      }
      public Builder clearChatParam() {
        bitField0_ = (bitField0_ & ~0x00000004);
        chatParam_ = 0;
        onChanged();
        return this;
      }
      
      // optional int64 fromUid = 4;
      private long fromUid_ ;
      public boolean hasFromUid() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public long getFromUid() {
        return fromUid_;
      }
      public Builder setFromUid(long value) {
        bitField0_ |= 0x00000008;
        fromUid_ = value;
        onChanged();
        return this;
      }
      public Builder clearFromUid() {
        bitField0_ = (bitField0_ & ~0x00000008);
        fromUid_ = 0L;
        onChanged();
        return this;
      }
      
      // optional int64 toUid = 5;
      private long toUid_ ;
      public boolean hasToUid() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public long getToUid() {
        return toUid_;
      }
      public Builder setToUid(long value) {
        bitField0_ |= 0x00000010;
        toUid_ = value;
        onChanged();
        return this;
      }
      public Builder clearToUid() {
        bitField0_ = (bitField0_ & ~0x00000010);
        toUid_ = 0L;
        onChanged();
        return this;
      }
      
      // optional string name = 6;
      private java.lang.Object name_ = "";
      public boolean hasName() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      public String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setName(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
        name_ = value;
        onChanged();
        return this;
      }
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000020);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      void setName(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000020;
        name_ = value;
        onChanged();
      }
      
      // optional string aren = 7;
      private java.lang.Object aren_ = "";
      public boolean hasAren() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      public String getAren() {
        java.lang.Object ref = aren_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          aren_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setAren(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
        aren_ = value;
        onChanged();
        return this;
      }
      public Builder clearAren() {
        bitField0_ = (bitField0_ & ~0x00000040);
        aren_ = getDefaultInstance().getAren();
        onChanged();
        return this;
      }
      void setAren(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000040;
        aren_ = value;
        onChanged();
      }
      
      // optional int32 sex = 8;
      private int sex_ ;
      public boolean hasSex() {
        return ((bitField0_ & 0x00000080) == 0x00000080);
      }
      public int getSex() {
        return sex_;
      }
      public Builder setSex(int value) {
        bitField0_ |= 0x00000080;
        sex_ = value;
        onChanged();
        return this;
      }
      public Builder clearSex() {
        bitField0_ = (bitField0_ & ~0x00000080);
        sex_ = 0;
        onChanged();
        return this;
      }
      
      // optional string head = 9;
      private java.lang.Object head_ = "";
      public boolean hasHead() {
        return ((bitField0_ & 0x00000100) == 0x00000100);
      }
      public String getHead() {
        java.lang.Object ref = head_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          head_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setHead(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000100;
        head_ = value;
        onChanged();
        return this;
      }
      public Builder clearHead() {
        bitField0_ = (bitField0_ & ~0x00000100);
        head_ = getDefaultInstance().getHead();
        onChanged();
        return this;
      }
      void setHead(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000100;
        head_ = value;
        onChanged();
      }
      
      // optional int32 lv = 10;
      private int lv_ ;
      public boolean hasLv() {
        return ((bitField0_ & 0x00000200) == 0x00000200);
      }
      public int getLv() {
        return lv_;
      }
      public Builder setLv(int value) {
        bitField0_ |= 0x00000200;
        lv_ = value;
        onChanged();
        return this;
      }
      public Builder clearLv() {
        bitField0_ = (bitField0_ & ~0x00000200);
        lv_ = 0;
        onChanged();
        return this;
      }
      
      // optional string cont = 11;
      private java.lang.Object cont_ = "";
      public boolean hasCont() {
        return ((bitField0_ & 0x00000400) == 0x00000400);
      }
      public String getCont() {
        java.lang.Object ref = cont_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          cont_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setCont(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000400;
        cont_ = value;
        onChanged();
        return this;
      }
      public Builder clearCont() {
        bitField0_ = (bitField0_ & ~0x00000400);
        cont_ = getDefaultInstance().getCont();
        onChanged();
        return this;
      }
      void setCont(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000400;
        cont_ = value;
        onChanged();
      }
      
      // optional bytes voice = 12;
      private com.google.protobuf.ByteString voice_ = com.google.protobuf.ByteString.EMPTY;
      public boolean hasVoice() {
        return ((bitField0_ & 0x00000800) == 0x00000800);
      }
      public com.google.protobuf.ByteString getVoice() {
        return voice_;
      }
      public Builder setVoice(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000800;
        voice_ = value;
        onChanged();
        return this;
      }
      public Builder clearVoice() {
        bitField0_ = (bitField0_ & ~0x00000800);
        voice_ = getDefaultInstance().getVoice();
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.CGChatProto)
    }
    
    static {
      defaultInstance = new CGChatProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.CGChatProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_CGChatProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_CGChatProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017CGChatMsg.proto\022\006buffer\"\311\001\n\013CGChatProt" +
      "o\022\025\n\007msgType\030\001 \001(\005:\0042004\022\020\n\010chatType\030\002 \001" +
      "(\005\022\021\n\tchatParam\030\003 \001(\005\022\017\n\007fromUid\030\004 \001(\003\022\r" +
      "\n\005toUid\030\005 \001(\003\022\014\n\004name\030\006 \001(\t\022\014\n\004aren\030\007 \001(" +
      "\t\022\013\n\003sex\030\010 \001(\005\022\014\n\004head\030\t \001(\t\022\n\n\002lv\030\n \001(\005" +
      "\022\014\n\004cont\030\013 \001(\t\022\r\n\005voice\030\014 \001(\014"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_CGChatProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_CGChatProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_CGChatProto_descriptor,
              new java.lang.String[] { "MsgType", "ChatType", "ChatParam", "FromUid", "ToUid", "Name", "Aren", "Sex", "Head", "Lv", "Cont", "Voice", },
              buffer.CGChatMsg.CGChatProto.class,
              buffer.CGChatMsg.CGChatProto.Builder.class);
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
