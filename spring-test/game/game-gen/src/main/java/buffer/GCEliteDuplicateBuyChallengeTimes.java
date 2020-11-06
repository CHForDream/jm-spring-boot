// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GCEliteDuplicateBuyChallengeTimes.proto

package buffer;

public final class GCEliteDuplicateBuyChallengeTimes {
  private GCEliteDuplicateBuyChallengeTimes() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface GCEliteDuplicateBuyChallengeTimesProtoOrBuilder
      extends com.google.protobuf.MessageOrBuilder {
    
    // optional int32 msgType = 1 [default = 25202];
    boolean hasMsgType();
    int getMsgType();
    
    // optional int32 status = 2;
    boolean hasStatus();
    int getStatus();
    
    // optional int32 eliteId = 3;
    boolean hasEliteId();
    int getEliteId();
    
    // optional int32 leftChallengeTimes = 4;
    boolean hasLeftChallengeTimes();
    int getLeftChallengeTimes();
    
    // optional int32 leftBuyTimes = 5;
    boolean hasLeftBuyTimes();
    int getLeftBuyTimes();
  }
  public static final class GCEliteDuplicateBuyChallengeTimesProto extends
      com.google.protobuf.GeneratedMessage
      implements GCEliteDuplicateBuyChallengeTimesProtoOrBuilder {
    // Use GCEliteDuplicateBuyChallengeTimesProto.newBuilder() to construct.
    private GCEliteDuplicateBuyChallengeTimesProto(Builder builder) {
      super(builder);
    }
    private GCEliteDuplicateBuyChallengeTimesProto(boolean noInit) {}
    
    private static final GCEliteDuplicateBuyChallengeTimesProto defaultInstance;
    public static GCEliteDuplicateBuyChallengeTimesProto getDefaultInstance() {
      return defaultInstance;
    }
    
    public GCEliteDuplicateBuyChallengeTimesProto getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return buffer.GCEliteDuplicateBuyChallengeTimes.internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return buffer.GCEliteDuplicateBuyChallengeTimes.internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_fieldAccessorTable;
    }
    
    private int bitField0_;
    // optional int32 msgType = 1 [default = 25202];
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
    
    // optional int32 eliteId = 3;
    public static final int ELITEID_FIELD_NUMBER = 3;
    private int eliteId_;
    public boolean hasEliteId() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public int getEliteId() {
      return eliteId_;
    }
    
    // optional int32 leftChallengeTimes = 4;
    public static final int LEFTCHALLENGETIMES_FIELD_NUMBER = 4;
    private int leftChallengeTimes_;
    public boolean hasLeftChallengeTimes() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public int getLeftChallengeTimes() {
      return leftChallengeTimes_;
    }
    
    // optional int32 leftBuyTimes = 5;
    public static final int LEFTBUYTIMES_FIELD_NUMBER = 5;
    private int leftBuyTimes_;
    public boolean hasLeftBuyTimes() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    public int getLeftBuyTimes() {
      return leftBuyTimes_;
    }
    
    private void initFields() {
      msgType_ = 25202;
      status_ = 0;
      eliteId_ = 0;
      leftChallengeTimes_ = 0;
      leftBuyTimes_ = 0;
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
        output.writeInt32(3, eliteId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, leftChallengeTimes_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, leftBuyTimes_);
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
          .computeInt32Size(3, eliteId_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, leftChallengeTimes_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, leftBuyTimes_);
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
    
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseDelimitedFrom(
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
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto prototype) {
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
       implements buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return buffer.GCEliteDuplicateBuyChallengeTimes.internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_descriptor;
      }
      
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return buffer.GCEliteDuplicateBuyChallengeTimes.internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_fieldAccessorTable;
      }
      
      // Construct using buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto.newBuilder()
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
        msgType_ = 25202;
        bitField0_ = (bitField0_ & ~0x00000001);
        status_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        eliteId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        leftChallengeTimes_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        leftBuyTimes_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto.getDescriptor();
      }
      
      public buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto getDefaultInstanceForType() {
        return buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto.getDefaultInstance();
      }
      
      public buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto build() {
        buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto buildPartial() {
        buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto result = new buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto(this);
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
        result.eliteId_ = eliteId_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.leftChallengeTimes_ = leftChallengeTimes_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.leftBuyTimes_ = leftBuyTimes_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto) {
          return mergeFrom((buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto other) {
        if (other == buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto.getDefaultInstance()) return this;
        if (other.hasMsgType()) {
          setMsgType(other.getMsgType());
        }
        if (other.hasStatus()) {
          setStatus(other.getStatus());
        }
        if (other.hasEliteId()) {
          setEliteId(other.getEliteId());
        }
        if (other.hasLeftChallengeTimes()) {
          setLeftChallengeTimes(other.getLeftChallengeTimes());
        }
        if (other.hasLeftBuyTimes()) {
          setLeftBuyTimes(other.getLeftBuyTimes());
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
              eliteId_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              leftChallengeTimes_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              leftBuyTimes_ = input.readInt32();
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // optional int32 msgType = 1 [default = 25202];
      private int msgType_ = 25202;
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
        msgType_ = 25202;
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
      
      // optional int32 eliteId = 3;
      private int eliteId_ ;
      public boolean hasEliteId() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public int getEliteId() {
        return eliteId_;
      }
      public Builder setEliteId(int value) {
        bitField0_ |= 0x00000004;
        eliteId_ = value;
        onChanged();
        return this;
      }
      public Builder clearEliteId() {
        bitField0_ = (bitField0_ & ~0x00000004);
        eliteId_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 leftChallengeTimes = 4;
      private int leftChallengeTimes_ ;
      public boolean hasLeftChallengeTimes() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public int getLeftChallengeTimes() {
        return leftChallengeTimes_;
      }
      public Builder setLeftChallengeTimes(int value) {
        bitField0_ |= 0x00000008;
        leftChallengeTimes_ = value;
        onChanged();
        return this;
      }
      public Builder clearLeftChallengeTimes() {
        bitField0_ = (bitField0_ & ~0x00000008);
        leftChallengeTimes_ = 0;
        onChanged();
        return this;
      }
      
      // optional int32 leftBuyTimes = 5;
      private int leftBuyTimes_ ;
      public boolean hasLeftBuyTimes() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      public int getLeftBuyTimes() {
        return leftBuyTimes_;
      }
      public Builder setLeftBuyTimes(int value) {
        bitField0_ |= 0x00000010;
        leftBuyTimes_ = value;
        onChanged();
        return this;
      }
      public Builder clearLeftBuyTimes() {
        bitField0_ = (bitField0_ & ~0x00000010);
        leftBuyTimes_ = 0;
        onChanged();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:buffer.GCEliteDuplicateBuyChallengeTimesProto)
    }
    
    static {
      defaultInstance = new GCEliteDuplicateBuyChallengeTimesProto(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:buffer.GCEliteDuplicateBuyChallengeTimesProto)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\'GCEliteDuplicateBuyChallengeTimes.prot" +
      "o\022\006buffer\"\223\001\n&GCEliteDuplicateBuyChallen" +
      "geTimesProto\022\026\n\007msgType\030\001 \001(\005:\00525202\022\016\n\006" +
      "status\030\002 \001(\005\022\017\n\007eliteId\030\003 \001(\005\022\032\n\022leftCha" +
      "llengeTimes\030\004 \001(\005\022\024\n\014leftBuyTimes\030\005 \001(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_buffer_GCEliteDuplicateBuyChallengeTimesProto_descriptor,
              new java.lang.String[] { "MsgType", "Status", "EliteId", "LeftChallengeTimes", "LeftBuyTimes", },
              buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto.class,
              buffer.GCEliteDuplicateBuyChallengeTimes.GCEliteDuplicateBuyChallengeTimesProto.Builder.class);
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
