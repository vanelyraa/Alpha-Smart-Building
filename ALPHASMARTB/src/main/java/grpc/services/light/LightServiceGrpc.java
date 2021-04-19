package grpc.services.light;

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
    comments = "Source: LightService.proto")
public final class LightServiceGrpc {

  private LightServiceGrpc() {}

  public static final String SERVICE_NAME = "Light.LightService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.light.Empty,
      grpc.services.light.LightingResponse> getLightingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lighting",
      requestType = grpc.services.light.Empty.class,
      responseType = grpc.services.light.LightingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.light.Empty,
      grpc.services.light.LightingResponse> getLightingMethod() {
    io.grpc.MethodDescriptor<grpc.services.light.Empty, grpc.services.light.LightingResponse> getLightingMethod;
    if ((getLightingMethod = LightServiceGrpc.getLightingMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getLightingMethod = LightServiceGrpc.getLightingMethod) == null) {
          LightServiceGrpc.getLightingMethod = getLightingMethod = 
              io.grpc.MethodDescriptor.<grpc.services.light.Empty, grpc.services.light.LightingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Light.LightService", "lighting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.light.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.light.LightingResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("lighting"))
                  .build();
          }
        }
     }
     return getLightingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.light.LightsRequest,
      grpc.services.light.LightsResponse> getLightsOnOffMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LightsOnOff",
      requestType = grpc.services.light.LightsRequest.class,
      responseType = grpc.services.light.LightsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.light.LightsRequest,
      grpc.services.light.LightsResponse> getLightsOnOffMethod() {
    io.grpc.MethodDescriptor<grpc.services.light.LightsRequest, grpc.services.light.LightsResponse> getLightsOnOffMethod;
    if ((getLightsOnOffMethod = LightServiceGrpc.getLightsOnOffMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getLightsOnOffMethod = LightServiceGrpc.getLightsOnOffMethod) == null) {
          LightServiceGrpc.getLightsOnOffMethod = getLightsOnOffMethod = 
              io.grpc.MethodDescriptor.<grpc.services.light.LightsRequest, grpc.services.light.LightsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Light.LightService", "LightsOnOff"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.light.LightsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.light.LightsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("LightsOnOff"))
                  .build();
          }
        }
     }
     return getLightsOnOffMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.light.IntensityRequest,
      grpc.services.light.IntensityResponse> getLightIntensityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "lightIntensity",
      requestType = grpc.services.light.IntensityRequest.class,
      responseType = grpc.services.light.IntensityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.light.IntensityRequest,
      grpc.services.light.IntensityResponse> getLightIntensityMethod() {
    io.grpc.MethodDescriptor<grpc.services.light.IntensityRequest, grpc.services.light.IntensityResponse> getLightIntensityMethod;
    if ((getLightIntensityMethod = LightServiceGrpc.getLightIntensityMethod) == null) {
      synchronized (LightServiceGrpc.class) {
        if ((getLightIntensityMethod = LightServiceGrpc.getLightIntensityMethod) == null) {
          LightServiceGrpc.getLightIntensityMethod = getLightIntensityMethod = 
              io.grpc.MethodDescriptor.<grpc.services.light.IntensityRequest, grpc.services.light.IntensityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Light.LightService", "lightIntensity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.light.IntensityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.light.IntensityResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new LightServiceMethodDescriptorSupplier("lightIntensity"))
                  .build();
          }
        }
     }
     return getLightIntensityMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LightServiceStub newStub(io.grpc.Channel channel) {
    return new LightServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LightServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LightServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LightServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LightServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LightServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void lighting(grpc.services.light.Empty request,
        io.grpc.stub.StreamObserver<grpc.services.light.LightingResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLightingMethod(), responseObserver);
    }

    /**
     */
    public void lightsOnOff(grpc.services.light.LightsRequest request,
        io.grpc.stub.StreamObserver<grpc.services.light.LightsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getLightsOnOffMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.light.IntensityRequest> lightIntensity(
        io.grpc.stub.StreamObserver<grpc.services.light.IntensityResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getLightIntensityMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLightingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.light.Empty,
                grpc.services.light.LightingResponse>(
                  this, METHODID_LIGHTING)))
          .addMethod(
            getLightsOnOffMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.light.LightsRequest,
                grpc.services.light.LightsResponse>(
                  this, METHODID_LIGHTS_ON_OFF)))
          .addMethod(
            getLightIntensityMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                grpc.services.light.IntensityRequest,
                grpc.services.light.IntensityResponse>(
                  this, METHODID_LIGHT_INTENSITY)))
          .build();
    }
  }

  /**
   */
  public static final class LightServiceStub extends io.grpc.stub.AbstractStub<LightServiceStub> {
    private LightServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LightServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LightServiceStub(channel, callOptions);
    }

    /**
     */
    public void lighting(grpc.services.light.Empty request,
        io.grpc.stub.StreamObserver<grpc.services.light.LightingResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLightingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void lightsOnOff(grpc.services.light.LightsRequest request,
        io.grpc.stub.StreamObserver<grpc.services.light.LightsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLightsOnOffMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.light.IntensityRequest> lightIntensity(
        io.grpc.stub.StreamObserver<grpc.services.light.IntensityResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getLightIntensityMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class LightServiceBlockingStub extends io.grpc.stub.AbstractStub<LightServiceBlockingStub> {
    private LightServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LightServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LightServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.light.LightingResponse lighting(grpc.services.light.Empty request) {
      return blockingUnaryCall(
          getChannel(), getLightingMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.services.light.LightsResponse lightsOnOff(grpc.services.light.LightsRequest request) {
      return blockingUnaryCall(
          getChannel(), getLightsOnOffMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LightServiceFutureStub extends io.grpc.stub.AbstractStub<LightServiceFutureStub> {
    private LightServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LightServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LightServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LightServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.light.LightingResponse> lighting(
        grpc.services.light.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getLightingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.light.LightsResponse> lightsOnOff(
        grpc.services.light.LightsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLightsOnOffMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIGHTING = 0;
  private static final int METHODID_LIGHTS_ON_OFF = 1;
  private static final int METHODID_LIGHT_INTENSITY = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LightServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LightServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIGHTING:
          serviceImpl.lighting((grpc.services.light.Empty) request,
              (io.grpc.stub.StreamObserver<grpc.services.light.LightingResponse>) responseObserver);
          break;
        case METHODID_LIGHTS_ON_OFF:
          serviceImpl.lightsOnOff((grpc.services.light.LightsRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.light.LightsResponse>) responseObserver);
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
        case METHODID_LIGHT_INTENSITY:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.lightIntensity(
              (io.grpc.stub.StreamObserver<grpc.services.light.IntensityResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LightServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.light.LightServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LightService");
    }
  }

  private static final class LightServiceFileDescriptorSupplier
      extends LightServiceBaseDescriptorSupplier {
    LightServiceFileDescriptorSupplier() {}
  }

  private static final class LightServiceMethodDescriptorSupplier
      extends LightServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LightServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (LightServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LightServiceFileDescriptorSupplier())
              .addMethod(getLightingMethod())
              .addMethod(getLightsOnOffMethod())
              .addMethod(getLightIntensityMethod())
              .build();
        }
      }
    }
    return result;
  }
}
