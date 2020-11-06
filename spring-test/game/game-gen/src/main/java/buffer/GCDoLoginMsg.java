// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCDoLoginMsg.proto

package buffer;

public final class GCDoLoginMsg {
  private GCDoLoginMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCDoLoginProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 2001];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 status = 2;
    boolean hasStatus();
    int getStatus();
    
    // optional int32 sessionKey = 3;
    boolean hasSessionKey();
    int getSessionKey();
    
    // optional int64 uid = 4;
    boolean hasUid();
    long getUid();
    
    // optional int32 isnew = 5;
    boolean hasIsnew();
    int getIsnew();
    
    // optional int32 isdebug = 6;
    boolean hasIsdebug();
    int getIsdebug();
    
    // optional int64 timeMill = 7;
    boolean hasTimeMill();
    long getTimeMill();
    
    // optional string chatServer = 8;
    boolean hasChatServer();
    String getChatServer();
    
    // optional int32 chatPort = 9;
    boolean hasChatPort();
    int getChatPort();
    
    // optional bool isReconnect = 10;
    boolean hasIsReconnect();
    boolean getIsReconnect();
  }
  public static final class GCDoLoginProto extends
      com.google.protobuf.GeneratedMessage
      implements GCDoLoginProtoOrBuilder {
    // Use GCDoLoginProto.newBuilder() to construct.
    private GCDoLoginProto(Builder builder) {
      super(builder);
    }
    private GCDoLoginProto(boolean noInit) {}
    
    private static final GCDoLoginProto defaultInstance;
    public static GCDoLoginProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCDoLoginProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCDoLoginMsg.internal_static_buffer_GCDoLoginProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCDoLoginMsg.internal_static_buffer_GCDoLoginProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 2001];
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
    
    // optional int32 sessionKey = 3;
    public static final int SESSIONKEY_FIELD_NUMBER = 3;
    private int sessionKey_;
    public boolean hasSessionKey() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getSessionKey() {
      return sessionKey_;
    }
    
    // optional int64 uid = 4;
    public static final int UID_FIELD_NUMBER = 4;
    private long uid_;
    public boolean hasUid() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public long getUid() {
      return uid_;
    }
    
    // optional int32 isnew = 5;
    public static final int ISNEW_FIELD_NUMBER = 5;
    private int isnew_;
    public boolean hasIsnew() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public int getIsnew() {
      return isnew_;
    }
    
    // optional int32 isdebug = 6;
    public static final int ISDEBUG_FIELD_NUMBER = 6;
    private int isdebug_;
    public boolean hasIsdebug() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    public int getIsdebug() {
      return isdebug_;
    }
    
    // optional int64 timeMill = 7;
    public static final int TIMEMILL_FIELD_NUMBER = 7;
    private long timeMill_;
    public boolean hasTimeMill() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    public long getTimeMill() {
      return timeMill_;
    }
    
    // optional string chatServer = 8;
    public static final int CHATSERVER_FIELD_NUMBER = 8;
    private java.lang.Object chatServer_;
    public boolean hasChatServer() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    public String getChatServer() {
      java.lang.Object ref = chatServer_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          chatServer_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getChatServerBytes() {
      java.lang.Object ref = chatServer_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        chatServer_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional int32 chatPort = 9;
    public static final int CHATPORT_FIELD_NUMBER = 9;
    private int chatPort_;
    public boolean hasChatPort() {
      return ((bitField0_ & 0x00000100) == 0x00000100);
    }
    public int getChatPort() {
      return chatPort_;
    }
    
    // optional bool isReconnect = 10;
    public static final int ISRECONNECT_FIELD_NUMBER = 10;
    private boolean isReconnect_;
    public boolean hasIsReconnect() {
      return ((bitField0_ & 0x00000200) == 0x00000200);
    }
    public boolean getIsReconnect() {
      return isReconnect_;
    }
    
    private void initFields() {
      msgType_ = 2001;
      status_ = 0;
      sessionKey_ = 0;
      uid_ = 0L;
      isnew_ = 0;
      isdebug_ = 0;
      timeMill_ = 0L;
      chatServer_ = "";
      chatPort_ = 0;
      isReconnect_ = false;
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
        output.writeInt32(3, sessionKey_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt64(4, uid_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, isnew_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        output.writeInt32(6, isdebug_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        output.writeInt64(7, timeMill_);
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        output.writeBytes(8, getChatServerBytes());
      }
      if (((bitField0_ & 0x00000100) == 0x00000100)) {
        output.writeInt32(9, chatPort_);
      }
      if (((bitField0_ & 0x00000200) == 0x00000200)) {
        output.writeBool(10, isReconnect_);
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
          .computeInt32Size(3, sessionKey_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(4, uid_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, isnew_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(6, isdebug_);
      }
      if (((bitField0_ & 0x00000040) == 0x00000040)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(7, timeMill_);
      }
      if (((bitField0_ & 0x00000080) == 0x00000080)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(8, getChatServerBytes());
      }
      if (((bitField0_ & 0x00000100) == 0x00000100)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(9, chatPort_);
      }
      if (((bitField0_ & 0x00000200) == 0x00000200)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(10, isReconnect_);
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
    
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseDelimitedFrom(
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
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCDoLoginMsg.GCDoLoginProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCDoLoginMsg.GCDoLoginProto prototype) {
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
       implements buffer.GCDoLoginMsg.GCDoLoginProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCDoLoginMsg.internal_static_buffer_GCDoLoginProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCDoLoginMsg.internal_static_buffer_GCDoLoginProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCDoLoginMsg.GCDoLoginProto.newBuilder()
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
        msgType_ = 2001;
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        sessionKey_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        uid_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000008);
        isnew_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        isdebug_ = 0;
        bitField0_ = (bitField0_ & ~0x00000020);
        timeMill_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000040);
        chatServer_ = "";
        bitField0_ = (bitField0_ & ~0x00000080);
        chatPort_ = 0;
        bitField0_ = (bitField0_ & ~0x00000100);
        isReconnect_ = false;
        bitField0_ = (bitField0_ & ~0x00000200);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCDoLoginMsg.GCDoLoginProto.getDescriptor();
      }
      
      public buffer.GCDoLoginMsg.GCDoLoginProto getDefaultInstanceForType() {
        return buffer.GCDoLoginMsg.GCDoLoginProto.getDefaultInstance();
      }
      
      public buffer.GCDoLoginMsg.GCDoLoginProto build() {
        buffer.GCDoLoginMsg.GCDoLoginProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCDoLoginMsg.GCDoLoginProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCDoLoginMsg.GCDoLoginProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCDoLoginMsg.GCDoLoginProto buildPartial() {
        buffer.GCDoLoginMsg.GCDoLoginProto result = new buffer.GCDoLoginMsg.GCDoLoginProto(this);
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
        result.sessionKey_ = sessionKey_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.uid_ = uid_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.isnew_ = isnew_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.isdebug_ = isdebug_;
        if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
          to_bitField0_ |= 0x00000040;
        }
        result.timeMill_ = timeMill_;
        if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
          to_bitField0_ |= 0x00000080;
        }
        result.chatServer_ = chatServer_;
        if (((from_bitField0_ & 0x00000100) == 0x00000100)) {
          to_bitField0_ |= 0x00000100;
        }
        result.chatPort_ = chatPort_;
        if (((from_bitField0_ & 0x00000200) == 0x00000200)) {
          to_bitField0_ |= 0x00000200;
        }
        result.isReconnect_ = isReconnect_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCDoLoginMsg.GCDoLoginProto) {
          return mergeFrom((buffer.GCDoLoginMsg.GCDoLoginProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCDoLoginMsg.GCDoLoginProto other) {
        if (other == buffer.GCDoLoginMsg.GCDoLoginProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasSessionKey()) {
          setSessionKey(other.getSessionKey());
        }
        if (other.hasUid()) {
          setUid(other.getUid());
        }
        if (other.hasIsnew()) {
          setIsnew(other.getIsnew());
        }
        if (other.hasIsdebug()) {
          setIsdebug(other.getIsdebug());
        }
        if (other.hasTimeMill()) {
          setTimeMill(other.getTimeMill());
        }
        if (other.hasChatServer()) {
          setChatServer(other.getChatServer());
        }
        if (other.hasChatPort()) {
          setChatPort(other.getChatPort());
        }
        if (other.hasIsReconnect()) {
          setIsReconnect(other.getIsReconnect());
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
              sessionKey_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              uid_ = input.readInt64();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              isnew_ = input.readInt32();
              break;
            }
            case 48: {
              bitField0_ |= 0x00000020;
              isdebug_ = input.readInt32();
              break;
            }
            case 56: {
              bitField0_ |= 0x00000040;
              timeMill_ = input.readInt64();
              break;
            }
            case 66: {
              bitField0_ |= 0x00000080;
              chatServer_ = input.readBytes();
              break;
            }
            case 72: {
              bitField0_ |= 0x00000100;
              chatPort_ = input.readInt32();
              break;
            }
            case 80: {
              bitField0_ |= 0x00000200;
              isReconnect_ = input.readBool();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 2001];
      private int msgType_ = 2001;
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
        msgType_ = 2001;
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
      
      // optional int32 sessionKey = 3;
      private int sessionKey_ ;
      public boolean hasSessionKey() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getSessionKey() {
        return sessionKey_;
      }
      public Builder setSessionKey(int value) {
        bitField0_ |= 0x00000004;
        sessionKey_ = value;
        onChanged();
        return this;
      }
      public Builder clearSessionKey() {
        bitField0_ = (bitField0_ & ~0x00000004);
        sessionKey_ = 0;
        onChanged();
        return this;
      }
      
      // optional int64 uid = 4;
      private long uid_ ;
      public boolean hasUid() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public long getUid() {
        return uid_;
      }
      public Builder setUid(long value) {
        bitField0_ |= 0x00000008;
        uid_ = value;
        onChanged();
        return this;
      }
      public Builder clearUid() {
        bitField0_ = (bitField0_ & ~0x00000008);
        uid_ = 0L;
        onChanged();
        return this;
      }
      
      // optional int32 isnew = 5;
      private int isnew_ ;
      public boolean hasIsnew() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public int getIsnew() {
        return isnew_;
      }
      public Builder setIsnew(int value) {
        bitField0_ |= 0x00000010;
        isnew_ = value;
        onChanged();
        return this;
      }
      public Builder clearIsnew() {
        bitField0_ = (bitField0_ & ~0x00000010);
        isnew_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 isdebug = 6;
      private int isdebug_ ;
      public boolean hasIsdebug() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      public int getIsdebug() {
        return isdebug_;
      }
      public Builder setIsdebug(int value) {
        bitField0_ |= 0x00000020;
        isdebug_ = value;
        onChanged();
        return this;
      }
      public Builder clearIsdebug() {
        bitField0_ = (bitField0_ & ~0x00000020);
        isdebug_ = 0;
        onChanged();
        return this;
      }
      
      // optional int64 timeMill = 7;
      private long timeMill_ ;
      public boolean hasTimeMill() {
        return ((bitField0_ & 0x00000040) == 0x00000040);
      }
      public long getTimeMill() {
        return timeMill_;
      }
      public Builder setTimeMill(long value) {
        bitField0_ |= 0x00000040;
        timeMill_ = value;
        onChanged();
        return this;
      }
      public Builder clearTimeMill() {
        bitField0_ = (bitField0_ & ~0x00000040);
        timeMill_ = 0L;
        onChanged();
        return this;
      }
      
      // optional string chatServer = 8;
      private java.lang.Object chatServer_ = "";
      public boolean hasChatServer() {
        return ((bitField0_ & 0x00000080) == 0x00000080);
      }
      public String getChatServer() {
        java.lang.Object ref = chatServer_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          chatServer_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setChatServer(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000080;
        chatServer_ = value;
        onChanged();
        return this;
      }
      public Builder clearChatServer() {
        bitField0_ = (bitField0_ & ~0x00000080);
        chatServer_ = getDefaultInstance().getChatServer();
        onChanged();
        return this;
      }
      void setChatServer(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000080;
        chatServer_ = value;
        onChanged();
      }
      
      // optional int32 chatPort = 9;
      private int chatPort_ ;
      public boolean hasChatPort() {
        return ((bitField0_ & 0x00000100) == 0x00000100);
      }
      public int getChatPort() {
        return chatPort_;
      }
      public Builder setChatPort(int value) {
        bitField0_ |= 0x00000100;
        chatPort_ = value;
        onChanged();
        return this;
      }
      public Builder clearChatPort() {
        bitField0_ = (bitField0_ & ~0x00000100);
        chatPort_ = 0;
        onChanged();
        return this;
      }
      
      // optional bool isReconnect = 10;
      private boolean isReconnect_ ;
      public boolean hasIsReconnect() {
        return ((bitField0_ & 0x00000200) == 0x00000200);
      }
      public boolean getIsReconnect() {
        return isReconnect_;
      }
      public Builder setIsReconnect(boolean value) {
        bitField0_ |= 0x00000200;
        isReconnect_ = value;
        onChanged();
        return this;
      }
      public Builder clearIsReconnect() {
        bitField0_ = (bitField0_ & ~0x00000200);
        isReconnect_ = false;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCDoLoginProto)
    }
    
    static {
      defaultInstance = new GCDoLoginProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCDoLoginProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCDoLoginProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCDoLoginProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022GCDoLoginMsg.proto\022\006buffer\"\305\001\n\016GCDoLog" +
      "inProto\022\025\n\007msgType\030\001 \001(\005:\0042001\022\016\n\006status" +
      "\030\002 \001(\005\022\022\n\nsessionKey\030\003 \001(\005\022\013\n\003uid\030\004 \001(\003\022" +
      "\r\n\005isnew\030\005 \001(\005\022\017\n\007isdebug\030\006 \001(\005\022\020\n\010timeM" +
      "ill\030\007 \001(\003\022\022\n\nchatServer\030\010 \001(\t\022\020\n\010chatPor" +
      "t\030\t \001(\005\022\023\n\013isReconnect\030\n \001(\010"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCDoLoginProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCDoLoginProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCDoLoginProto_descriptor,
              new java.lang.String[] { "MsgType", "Status", "SessionKey", "Uid", "Isnew", "Isdebug", "TimeMill", "ChatServer", "ChatPort", "IsReconnect", },
              buffer.GCDoLoginMsg.GCDoLoginProto.class,
              buffer.GCDoLoginMsg.GCDoLoginProto.Builder.class);
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
