package grpc.services.climate;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: climateControl.proto")
public final class ClimateServiceGrpc {

  private ClimateServiceGrpc() {}

  public static final String SERVICE_NAME = "climate.ClimateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.climate.HVACStatusRequest,
      grpc.services.climate.HVACStatusResponse> getShowHVACStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "showHVACStatus",
      requestType = grpc.services.climate.HVACStatusRequest.class,
      responseType = grpc.services.climate.HVACStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.climate.HVACStatusRequest,
      grpc.services.climate.HVACStatusResponse> getShowHVACStatusMethod() {
    io.grpc.MethodDescriptor<grpc.services.climate.HVACStatusRequest, grpc.services.climate.HVACStatusResponse> getShowHVACStatusMethod;
    if ((getShowHVACStatusMethod = ClimateServiceGrpc.getShowHVACStatusMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getShowHVACStatusMethod = ClimateServiceGrpc.getShowHVACStatusMethod) == null) {
          ClimateServiceGrpc.getShowHVACStatusMethod = getShowHVACStatusMethod = 
              io.grpc.MethodDescriptor.<grpc.services.climate.HVACStatusRequest, grpc.services.climate.HVACStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "climate.ClimateService", "showHVACStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.HVACStatusRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.HVACStatusResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("showHVACStatus"))
                  .build();
          }
        }
     }
     return getShowHVACStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.climate.TempRequest,
      grpc.services.climate.TempResponse> getSetHVACtempMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setHVACtemp",
      requestType = grpc.services.climate.TempRequest.class,
      responseType = grpc.services.climate.TempResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.climate.TempRequest,
      grpc.services.climate.TempResponse> getSetHVACtempMethod() {
    io.grpc.MethodDescriptor<grpc.services.climate.TempRequest, grpc.services.climate.TempResponse> getSetHVACtempMethod;
    if ((getSetHVACtempMethod = ClimateServiceGrpc.getSetHVACtempMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getSetHVACtempMethod = ClimateServiceGrpc.getSetHVACtempMethod) == null) {
          ClimateServiceGrpc.getSetHVACtempMethod = getSetHVACtempMethod = 
              io.grpc.MethodDescriptor.<grpc.services.climate.TempRequest, grpc.services.climate.TempResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "climate.ClimateService", "setHVACtemp"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.TempRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.TempResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("setHVACtemp"))
                  .build();
          }
        }
     }
     return getSetHVACtempMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.climate.CoLevelRequest,
      grpc.services.climate.CoLevelResponse> getCheckCOMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkCO",
      requestType = grpc.services.climate.CoLevelRequest.class,
      responseType = grpc.services.climate.CoLevelResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.climate.CoLevelRequest,
      grpc.services.climate.CoLevelResponse> getCheckCOMethod() {
    io.grpc.MethodDescriptor<grpc.services.climate.CoLevelRequest, grpc.services.climate.CoLevelResponse> getCheckCOMethod;
    if ((getCheckCOMethod = ClimateServiceGrpc.getCheckCOMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getCheckCOMethod = ClimateServiceGrpc.getCheckCOMethod) == null) {
          ClimateServiceGrpc.getCheckCOMethod = getCheckCOMethod = 
              io.grpc.MethodDescriptor.<grpc.services.climate.CoLevelRequest, grpc.services.climate.CoLevelResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "climate.ClimateService", "checkCO"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.CoLevelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.CoLevelResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("checkCO"))
                  .build();
          }
        }
     }
     return getCheckCOMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClimateServiceStub newStub(io.grpc.Channel channel) {
    return new ClimateServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClimateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ClimateServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClimateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ClimateServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ClimateServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void showHVACStatus(grpc.services.climate.HVACStatusRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.HVACStatusResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getShowHVACStatusMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.climate.TempRequest> setHVACtemp(
        io.grpc.stub.StreamObserver<grpc.services.climate.TempResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getSetHVACtempMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.climate.CoLevelRequest> checkCO(
        io.grpc.stub.StreamObserver<grpc.services.climate.CoLevelResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getCheckCOMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getShowHVACStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.climate.HVACStatusRequest,
                grpc.services.climate.HVACStatusResponse>(
                  this, METHODID_SHOW_HVACSTATUS)))
          .addMethod(
            getSetHVACtempMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.services.climate.TempRequest,
                grpc.services.climate.TempResponse>(
                  this, METHODID_SET_HVACTEMP)))
          .addMethod(
            getCheckCOMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.services.climate.CoLevelRequest,
                grpc.services.climate.CoLevelResponse>(
                  this, METHODID_CHECK_CO)))
          .build();
    }
  }

  /**
   */
  public static final class ClimateServiceStub extends io.grpc.stub.AbstractStub<ClimateServiceStub> {
    private ClimateServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClimateServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClimateServiceStub(channel, callOptions);
    }

    /**
     */
    public void showHVACStatus(grpc.services.climate.HVACStatusRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.HVACStatusResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShowHVACStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.climate.TempRequest> setHVACtemp(
        io.grpc.stub.StreamObserver<grpc.services.climate.TempResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSetHVACtempMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.climate.CoLevelRequest> checkCO(
        io.grpc.stub.StreamObserver<grpc.services.climate.CoLevelResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getCheckCOMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class ClimateServiceBlockingStub extends io.grpc.stub.AbstractStub<ClimateServiceBlockingStub> {
    private ClimateServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClimateServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClimateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.climate.HVACStatusResponse showHVACStatus(grpc.services.climate.HVACStatusRequest request) {
      return blockingUnaryCall(
          getChannel(), getShowHVACStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClimateServiceFutureStub extends io.grpc.stub.AbstractStub<ClimateServiceFutureStub> {
    private ClimateServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ClimateServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClimateServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ClimateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.climate.HVACStatusResponse> showHVACStatus(
        grpc.services.climate.HVACStatusRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getShowHVACStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SHOW_HVACSTATUS = 0;
  private static final int METHODID_SET_HVACTEMP = 1;
  private static final int METHODID_CHECK_CO = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClimateServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClimateServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SHOW_HVACSTATUS:
          serviceImpl.showHVACStatus((grpc.services.climate.HVACStatusRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.climate.HVACStatusResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_HVACTEMP:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.setHVACtemp(
              (io.grpc.stub.StreamObserver<grpc.services.climate.TempResponse>) responseObserver);
        case METHODID_CHECK_CO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.checkCO(
              (io.grpc.stub.StreamObserver<grpc.services.climate.CoLevelResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ClimateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClimateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.climate.ClimateServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClimateService");
    }
  }

  private static final class ClimateServiceFileDescriptorSupplier
      extends ClimateServiceBaseDescriptorSupplier {
    ClimateServiceFileDescriptorSupplier() {}
  }

  private static final class ClimateServiceMethodDescriptorSupplier
      extends ClimateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClimateServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ClimateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClimateServiceFileDescriptorSupplier())
              .addMethod(getShowHVACStatusMethod())
              .addMethod(getSetHVACtempMethod())
              .addMethod(getCheckCOMethod())
              .build();
        }
      }
    }
    return result;
  }
}
