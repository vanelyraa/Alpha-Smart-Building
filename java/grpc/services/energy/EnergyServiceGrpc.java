package grpc.services.energy;

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
    comments = "Source: energyEfficiency.proto")
public final class EnergyServiceGrpc {

  private EnergyServiceGrpc() {}

  public static final String SERVICE_NAME = "energy.EnergyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.energy.DevicesOffRequest,
      grpc.services.energy.DevicesOffResponse> getSwitchDevicesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchDevices",
      requestType = grpc.services.energy.DevicesOffRequest.class,
      responseType = grpc.services.energy.DevicesOffResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.energy.DevicesOffRequest,
      grpc.services.energy.DevicesOffResponse> getSwitchDevicesMethod() {
    io.grpc.MethodDescriptor<grpc.services.energy.DevicesOffRequest, grpc.services.energy.DevicesOffResponse> getSwitchDevicesMethod;
    if ((getSwitchDevicesMethod = EnergyServiceGrpc.getSwitchDevicesMethod) == null) {
      synchronized (EnergyServiceGrpc.class) {
        if ((getSwitchDevicesMethod = EnergyServiceGrpc.getSwitchDevicesMethod) == null) {
          EnergyServiceGrpc.getSwitchDevicesMethod = getSwitchDevicesMethod = 
              io.grpc.MethodDescriptor.<grpc.services.energy.DevicesOffRequest, grpc.services.energy.DevicesOffResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "energy.EnergyService", "switchDevices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.energy.DevicesOffRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.energy.DevicesOffResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EnergyServiceMethodDescriptorSupplier("switchDevices"))
                  .build();
          }
        }
     }
     return getSwitchDevicesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.energy.LightsSwitchRequest,
      grpc.services.energy.LightsResponse> getSwitchLightsOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchLightsOn",
      requestType = grpc.services.energy.LightsSwitchRequest.class,
      responseType = grpc.services.energy.LightsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.energy.LightsSwitchRequest,
      grpc.services.energy.LightsResponse> getSwitchLightsOnMethod() {
    io.grpc.MethodDescriptor<grpc.services.energy.LightsSwitchRequest, grpc.services.energy.LightsResponse> getSwitchLightsOnMethod;
    if ((getSwitchLightsOnMethod = EnergyServiceGrpc.getSwitchLightsOnMethod) == null) {
      synchronized (EnergyServiceGrpc.class) {
        if ((getSwitchLightsOnMethod = EnergyServiceGrpc.getSwitchLightsOnMethod) == null) {
          EnergyServiceGrpc.getSwitchLightsOnMethod = getSwitchLightsOnMethod = 
              io.grpc.MethodDescriptor.<grpc.services.energy.LightsSwitchRequest, grpc.services.energy.LightsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "energy.EnergyService", "switchLightsOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.energy.LightsSwitchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.energy.LightsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EnergyServiceMethodDescriptorSupplier("switchLightsOn"))
                  .build();
          }
        }
     }
     return getSwitchLightsOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.energy.LightAdjustRequest,
      grpc.services.energy.LightAdjustResponse> getLightIntensityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lightIntensity",
      requestType = grpc.services.energy.LightAdjustRequest.class,
      responseType = grpc.services.energy.LightAdjustResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.energy.LightAdjustRequest,
      grpc.services.energy.LightAdjustResponse> getLightIntensityMethod() {
    io.grpc.MethodDescriptor<grpc.services.energy.LightAdjustRequest, grpc.services.energy.LightAdjustResponse> getLightIntensityMethod;
    if ((getLightIntensityMethod = EnergyServiceGrpc.getLightIntensityMethod) == null) {
      synchronized (EnergyServiceGrpc.class) {
        if ((getLightIntensityMethod = EnergyServiceGrpc.getLightIntensityMethod) == null) {
          EnergyServiceGrpc.getLightIntensityMethod = getLightIntensityMethod = 
              io.grpc.MethodDescriptor.<grpc.services.energy.LightAdjustRequest, grpc.services.energy.LightAdjustResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "energy.EnergyService", "lightIntensity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.energy.LightAdjustRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.energy.LightAdjustResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EnergyServiceMethodDescriptorSupplier("lightIntensity"))
                  .build();
          }
        }
     }
     return getLightIntensityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EnergyServiceStub newStub(io.grpc.Channel channel) {
    return new EnergyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EnergyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EnergyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EnergyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EnergyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class EnergyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void switchDevices(grpc.services.energy.DevicesOffRequest request,
        io.grpc.stub.StreamObserver<grpc.services.energy.DevicesOffResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchDevicesMethod(), responseObserver);
    }

    /**
     */
    public void switchLightsOn(grpc.services.energy.LightsSwitchRequest request,
        io.grpc.stub.StreamObserver<grpc.services.energy.LightsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchLightsOnMethod(), responseObserver);
    }

    /**
     */
    public void lightIntensity(grpc.services.energy.LightAdjustRequest request,
        io.grpc.stub.StreamObserver<grpc.services.energy.LightAdjustResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLightIntensityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSwitchDevicesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.energy.DevicesOffRequest,
                grpc.services.energy.DevicesOffResponse>(
                  this, METHODID_SWITCH_DEVICES)))
          .addMethod(
            getSwitchLightsOnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.energy.LightsSwitchRequest,
                grpc.services.energy.LightsResponse>(
                  this, METHODID_SWITCH_LIGHTS_ON)))
          .addMethod(
            getLightIntensityMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                grpc.services.energy.LightAdjustRequest,
                grpc.services.energy.LightAdjustResponse>(
                  this, METHODID_LIGHT_INTENSITY)))
          .build();
    }
  }

  /**
   */
  public static final class EnergyServiceStub extends io.grpc.stub.AbstractStub<EnergyServiceStub> {
    private EnergyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnergyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnergyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnergyServiceStub(channel, callOptions);
    }

    /**
     */
    public void switchDevices(grpc.services.energy.DevicesOffRequest request,
        io.grpc.stub.StreamObserver<grpc.services.energy.DevicesOffResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchDevicesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void switchLightsOn(grpc.services.energy.LightsSwitchRequest request,
        io.grpc.stub.StreamObserver<grpc.services.energy.LightsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchLightsOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lightIntensity(grpc.services.energy.LightAdjustRequest request,
        io.grpc.stub.StreamObserver<grpc.services.energy.LightAdjustResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getLightIntensityMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EnergyServiceBlockingStub extends io.grpc.stub.AbstractStub<EnergyServiceBlockingStub> {
    private EnergyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnergyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnergyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnergyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.energy.DevicesOffResponse switchDevices(grpc.services.energy.DevicesOffRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchDevicesMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.services.energy.LightsResponse switchLightsOn(grpc.services.energy.LightsSwitchRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchLightsOnMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<grpc.services.energy.LightAdjustResponse> lightIntensity(
        grpc.services.energy.LightAdjustRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getLightIntensityMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EnergyServiceFutureStub extends io.grpc.stub.AbstractStub<EnergyServiceFutureStub> {
    private EnergyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EnergyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EnergyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EnergyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.energy.DevicesOffResponse> switchDevices(
        grpc.services.energy.DevicesOffRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchDevicesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.energy.LightsResponse> switchLightsOn(
        grpc.services.energy.LightsSwitchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchLightsOnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SWITCH_DEVICES = 0;
  private static final int METHODID_SWITCH_LIGHTS_ON = 1;
  private static final int METHODID_LIGHT_INTENSITY = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EnergyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EnergyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SWITCH_DEVICES:
          serviceImpl.switchDevices((grpc.services.energy.DevicesOffRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.energy.DevicesOffResponse>) responseObserver);
          break;
        case METHODID_SWITCH_LIGHTS_ON:
          serviceImpl.switchLightsOn((grpc.services.energy.LightsSwitchRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.energy.LightsResponse>) responseObserver);
          break;
        case METHODID_LIGHT_INTENSITY:
          serviceImpl.lightIntensity((grpc.services.energy.LightAdjustRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.energy.LightAdjustResponse>) responseObserver);
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

  private static abstract class EnergyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EnergyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.energy.EnergyServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EnergyService");
    }
  }

  private static final class EnergyServiceFileDescriptorSupplier
      extends EnergyServiceBaseDescriptorSupplier {
    EnergyServiceFileDescriptorSupplier() {}
  }

  private static final class EnergyServiceMethodDescriptorSupplier
      extends EnergyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EnergyServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (EnergyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EnergyServiceFileDescriptorSupplier())
              .addMethod(getSwitchDevicesMethod())
              .addMethod(getSwitchLightsOnMethod())
              .addMethod(getLightIntensityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
