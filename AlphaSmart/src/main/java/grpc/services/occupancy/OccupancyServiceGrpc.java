package grpc.services.occupancy;

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
    comments = "Source: personnelOccupancy.proto")
public final class OccupancyServiceGrpc {

  private OccupancyServiceGrpc() {}

  public static final String SERVICE_NAME = "occupancy.OccupancyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.occupancy.LocalRequest,
      grpc.services.occupancy.OccupancyResponse> getGetOccupancyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOccupancy",
      requestType = grpc.services.occupancy.LocalRequest.class,
      responseType = grpc.services.occupancy.OccupancyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.occupancy.LocalRequest,
      grpc.services.occupancy.OccupancyResponse> getGetOccupancyMethod() {
    io.grpc.MethodDescriptor<grpc.services.occupancy.LocalRequest, grpc.services.occupancy.OccupancyResponse> getGetOccupancyMethod;
    if ((getGetOccupancyMethod = OccupancyServiceGrpc.getGetOccupancyMethod) == null) {
      synchronized (OccupancyServiceGrpc.class) {
        if ((getGetOccupancyMethod = OccupancyServiceGrpc.getGetOccupancyMethod) == null) {
          OccupancyServiceGrpc.getGetOccupancyMethod = getGetOccupancyMethod = 
              io.grpc.MethodDescriptor.<grpc.services.occupancy.LocalRequest, grpc.services.occupancy.OccupancyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "occupancy.OccupancyService", "getOccupancy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.occupancy.LocalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.occupancy.OccupancyResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new OccupancyServiceMethodDescriptorSupplier("getOccupancy"))
                  .build();
          }
        }
     }
     return getGetOccupancyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.occupancy.CameraSwitchRequest,
      grpc.services.occupancy.CameraResponse> getSwitchCameraOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchCameraOn",
      requestType = grpc.services.occupancy.CameraSwitchRequest.class,
      responseType = grpc.services.occupancy.CameraResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.occupancy.CameraSwitchRequest,
      grpc.services.occupancy.CameraResponse> getSwitchCameraOnMethod() {
    io.grpc.MethodDescriptor<grpc.services.occupancy.CameraSwitchRequest, grpc.services.occupancy.CameraResponse> getSwitchCameraOnMethod;
    if ((getSwitchCameraOnMethod = OccupancyServiceGrpc.getSwitchCameraOnMethod) == null) {
      synchronized (OccupancyServiceGrpc.class) {
        if ((getSwitchCameraOnMethod = OccupancyServiceGrpc.getSwitchCameraOnMethod) == null) {
          OccupancyServiceGrpc.getSwitchCameraOnMethod = getSwitchCameraOnMethod = 
              io.grpc.MethodDescriptor.<grpc.services.occupancy.CameraSwitchRequest, grpc.services.occupancy.CameraResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "occupancy.OccupancyService", "switchCameraOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.occupancy.CameraSwitchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.occupancy.CameraResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new OccupancyServiceMethodDescriptorSupplier("switchCameraOn"))
                  .build();
          }
        }
     }
     return getSwitchCameraOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.occupancy.FindListDay,
      grpc.services.occupancy.ListResponse> getGetVisitListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getVisitList",
      requestType = grpc.services.occupancy.FindListDay.class,
      responseType = grpc.services.occupancy.ListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.occupancy.FindListDay,
      grpc.services.occupancy.ListResponse> getGetVisitListMethod() {
    io.grpc.MethodDescriptor<grpc.services.occupancy.FindListDay, grpc.services.occupancy.ListResponse> getGetVisitListMethod;
    if ((getGetVisitListMethod = OccupancyServiceGrpc.getGetVisitListMethod) == null) {
      synchronized (OccupancyServiceGrpc.class) {
        if ((getGetVisitListMethod = OccupancyServiceGrpc.getGetVisitListMethod) == null) {
          OccupancyServiceGrpc.getGetVisitListMethod = getGetVisitListMethod = 
              io.grpc.MethodDescriptor.<grpc.services.occupancy.FindListDay, grpc.services.occupancy.ListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "occupancy.OccupancyService", "getVisitList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.occupancy.FindListDay.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.occupancy.ListResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new OccupancyServiceMethodDescriptorSupplier("getVisitList"))
                  .build();
          }
        }
     }
     return getGetVisitListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OccupancyServiceStub newStub(io.grpc.Channel channel) {
    return new OccupancyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OccupancyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new OccupancyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OccupancyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new OccupancyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class OccupancyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getOccupancy(grpc.services.occupancy.LocalRequest request,
        io.grpc.stub.StreamObserver<grpc.services.occupancy.OccupancyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOccupancyMethod(), responseObserver);
    }

    /**
     */
    public void switchCameraOn(grpc.services.occupancy.CameraSwitchRequest request,
        io.grpc.stub.StreamObserver<grpc.services.occupancy.CameraResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchCameraOnMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.occupancy.FindListDay> getVisitList(
        io.grpc.stub.StreamObserver<grpc.services.occupancy.ListResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getGetVisitListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetOccupancyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.occupancy.LocalRequest,
                grpc.services.occupancy.OccupancyResponse>(
                  this, METHODID_GET_OCCUPANCY)))
          .addMethod(
            getSwitchCameraOnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.occupancy.CameraSwitchRequest,
                grpc.services.occupancy.CameraResponse>(
                  this, METHODID_SWITCH_CAMERA_ON)))
          .addMethod(
            getGetVisitListMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.services.occupancy.FindListDay,
                grpc.services.occupancy.ListResponse>(
                  this, METHODID_GET_VISIT_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class OccupancyServiceStub extends io.grpc.stub.AbstractStub<OccupancyServiceStub> {
    private OccupancyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OccupancyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OccupancyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OccupancyServiceStub(channel, callOptions);
    }

    /**
     */
    public void getOccupancy(grpc.services.occupancy.LocalRequest request,
        io.grpc.stub.StreamObserver<grpc.services.occupancy.OccupancyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOccupancyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void switchCameraOn(grpc.services.occupancy.CameraSwitchRequest request,
        io.grpc.stub.StreamObserver<grpc.services.occupancy.CameraResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchCameraOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.occupancy.FindListDay> getVisitList(
        io.grpc.stub.StreamObserver<grpc.services.occupancy.ListResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getGetVisitListMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class OccupancyServiceBlockingStub extends io.grpc.stub.AbstractStub<OccupancyServiceBlockingStub> {
    private OccupancyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OccupancyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OccupancyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OccupancyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.occupancy.OccupancyResponse getOccupancy(grpc.services.occupancy.LocalRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOccupancyMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.services.occupancy.CameraResponse switchCameraOn(grpc.services.occupancy.CameraSwitchRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchCameraOnMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OccupancyServiceFutureStub extends io.grpc.stub.AbstractStub<OccupancyServiceFutureStub> {
    private OccupancyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private OccupancyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OccupancyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new OccupancyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.occupancy.OccupancyResponse> getOccupancy(
        grpc.services.occupancy.LocalRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOccupancyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.occupancy.CameraResponse> switchCameraOn(
        grpc.services.occupancy.CameraSwitchRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchCameraOnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_OCCUPANCY = 0;
  private static final int METHODID_SWITCH_CAMERA_ON = 1;
  private static final int METHODID_GET_VISIT_LIST = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OccupancyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OccupancyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_OCCUPANCY:
          serviceImpl.getOccupancy((grpc.services.occupancy.LocalRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.occupancy.OccupancyResponse>) responseObserver);
          break;
        case METHODID_SWITCH_CAMERA_ON:
          serviceImpl.switchCameraOn((grpc.services.occupancy.CameraSwitchRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.occupancy.CameraResponse>) responseObserver);
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
        case METHODID_GET_VISIT_LIST:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getVisitList(
              (io.grpc.stub.StreamObserver<grpc.services.occupancy.ListResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OccupancyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OccupancyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.occupancy.OccupancyServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OccupancyService");
    }
  }

  private static final class OccupancyServiceFileDescriptorSupplier
      extends OccupancyServiceBaseDescriptorSupplier {
    OccupancyServiceFileDescriptorSupplier() {}
  }

  private static final class OccupancyServiceMethodDescriptorSupplier
      extends OccupancyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OccupancyServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (OccupancyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OccupancyServiceFileDescriptorSupplier())
              .addMethod(getGetOccupancyMethod())
              .addMethod(getSwitchCameraOnMethod())
              .addMethod(getGetVisitListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
