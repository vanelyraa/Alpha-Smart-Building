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
    comments = "Source: ClimateService.proto")
public final class ClimateServiceGrpc {

  private ClimateServiceGrpc() {}

  public static final String SERVICE_NAME = "Climate.ClimateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.climate.SwitchRequest,
      grpc.services.climate.SwitchResponse> getHvacOnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HvacOnOff",
      requestType = grpc.services.climate.SwitchRequest.class,
      responseType = grpc.services.climate.SwitchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.climate.SwitchRequest,
      grpc.services.climate.SwitchResponse> getHvacOnOffMethod() {
    io.grpc.MethodDescriptor<grpc.services.climate.SwitchRequest, grpc.services.climate.SwitchResponse> getHvacOnOffMethod;
    if ((getHvacOnOffMethod = ClimateServiceGrpc.getHvacOnOffMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getHvacOnOffMethod = ClimateServiceGrpc.getHvacOnOffMethod) == null) {
          ClimateServiceGrpc.getHvacOnOffMethod = getHvacOnOffMethod = 
              io.grpc.MethodDescriptor.<grpc.services.climate.SwitchRequest, grpc.services.climate.SwitchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Climate.ClimateService", "HvacOnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.SwitchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.SwitchResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("HvacOnOff"))
                  .build();
          }
        }
     }
     return getHvacOnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.climate.HvacRequest,
      grpc.services.climate.HvacResponse> getHvacTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HvacTemperature",
      requestType = grpc.services.climate.HvacRequest.class,
      responseType = grpc.services.climate.HvacResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.climate.HvacRequest,
      grpc.services.climate.HvacResponse> getHvacTemperatureMethod() {
    io.grpc.MethodDescriptor<grpc.services.climate.HvacRequest, grpc.services.climate.HvacResponse> getHvacTemperatureMethod;
    if ((getHvacTemperatureMethod = ClimateServiceGrpc.getHvacTemperatureMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getHvacTemperatureMethod = ClimateServiceGrpc.getHvacTemperatureMethod) == null) {
          ClimateServiceGrpc.getHvacTemperatureMethod = getHvacTemperatureMethod = 
              io.grpc.MethodDescriptor.<grpc.services.climate.HvacRequest, grpc.services.climate.HvacResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Climate.ClimateService", "HvacTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.HvacRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.HvacResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ClimateServiceMethodDescriptorSupplier("HvacTemperature"))
                  .build();
          }
        }
     }
     return getHvacTemperatureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.climate.CoLevelRequest,
      grpc.services.climate.ExtractionResponse> getCheckCOMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkCO",
      requestType = grpc.services.climate.CoLevelRequest.class,
      responseType = grpc.services.climate.ExtractionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.climate.CoLevelRequest,
      grpc.services.climate.ExtractionResponse> getCheckCOMethod() {
    io.grpc.MethodDescriptor<grpc.services.climate.CoLevelRequest, grpc.services.climate.ExtractionResponse> getCheckCOMethod;
    if ((getCheckCOMethod = ClimateServiceGrpc.getCheckCOMethod) == null) {
      synchronized (ClimateServiceGrpc.class) {
        if ((getCheckCOMethod = ClimateServiceGrpc.getCheckCOMethod) == null) {
          ClimateServiceGrpc.getCheckCOMethod = getCheckCOMethod = 
              io.grpc.MethodDescriptor.<grpc.services.climate.CoLevelRequest, grpc.services.climate.ExtractionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Climate.ClimateService", "checkCO"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.CoLevelRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.climate.ExtractionResponse.getDefaultInstance()))
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
    public void hvacOnOff(grpc.services.climate.SwitchRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.SwitchResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHvacOnOffMethod(), responseObserver);
    }

    /**
     */
    public void hvacTemperature(grpc.services.climate.HvacRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.HvacResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getHvacTemperatureMethod(), responseObserver);
    }

    /**
     */
    public void checkCO(grpc.services.climate.CoLevelRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.ExtractionResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckCOMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHvacOnOffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.climate.SwitchRequest,
                grpc.services.climate.SwitchResponse>(
                  this, METHODID_HVAC_ON_OFF)))
          .addMethod(
            getHvacTemperatureMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.services.climate.HvacRequest,
                grpc.services.climate.HvacResponse>(
                  this, METHODID_HVAC_TEMPERATURE)))
          .addMethod(
            getCheckCOMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.climate.CoLevelRequest,
                grpc.services.climate.ExtractionResponse>(
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
    public void hvacOnOff(grpc.services.climate.SwitchRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.SwitchResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHvacOnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void hvacTemperature(grpc.services.climate.HvacRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.HvacResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getHvacTemperatureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkCO(grpc.services.climate.CoLevelRequest request,
        io.grpc.stub.StreamObserver<grpc.services.climate.ExtractionResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckCOMethod(), getCallOptions()), request, responseObserver);
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
    public grpc.services.climate.SwitchResponse hvacOnOff(grpc.services.climate.SwitchRequest request) {
      return blockingUnaryCall(
          getChannel(), getHvacOnOffMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.services.climate.HvacResponse> hvacTemperature(
        grpc.services.climate.HvacRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getHvacTemperatureMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.services.climate.ExtractionResponse checkCO(grpc.services.climate.CoLevelRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckCOMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.climate.SwitchResponse> hvacOnOff(
        grpc.services.climate.SwitchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getHvacOnOffMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.climate.ExtractionResponse> checkCO(
        grpc.services.climate.CoLevelRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckCOMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HVAC_ON_OFF = 0;
  private static final int METHODID_HVAC_TEMPERATURE = 1;
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
        case METHODID_HVAC_ON_OFF:
          serviceImpl.hvacOnOff((grpc.services.climate.SwitchRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.climate.SwitchResponse>) responseObserver);
          break;
        case METHODID_HVAC_TEMPERATURE:
          serviceImpl.hvacTemperature((grpc.services.climate.HvacRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.climate.HvacResponse>) responseObserver);
          break;
        case METHODID_CHECK_CO:
          serviceImpl.checkCO((grpc.services.climate.CoLevelRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.climate.ExtractionResponse>) responseObserver);
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
              .addMethod(getHvacOnOffMethod())
              .addMethod(getHvacTemperatureMethod())
              .addMethod(getCheckCOMethod())
              .build();
        }
      }
    }
    return result;
  }
}
