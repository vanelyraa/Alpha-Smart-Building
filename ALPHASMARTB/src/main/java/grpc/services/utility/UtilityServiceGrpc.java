package grpc.services.utility;

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
    comments = "Source: UtilityService.proto")
public final class UtilityServiceGrpc {

  private UtilityServiceGrpc() {}

  public static final String SERVICE_NAME = "Utility.UtilityService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.services.utility.DevicesRequest,
      grpc.services.utility.DevicesResponse> getSwitchDevicesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchDevices",
      requestType = grpc.services.utility.DevicesRequest.class,
      responseType = grpc.services.utility.DevicesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.utility.DevicesRequest,
      grpc.services.utility.DevicesResponse> getSwitchDevicesMethod() {
    io.grpc.MethodDescriptor<grpc.services.utility.DevicesRequest, grpc.services.utility.DevicesResponse> getSwitchDevicesMethod;
    if ((getSwitchDevicesMethod = UtilityServiceGrpc.getSwitchDevicesMethod) == null) {
      synchronized (UtilityServiceGrpc.class) {
        if ((getSwitchDevicesMethod = UtilityServiceGrpc.getSwitchDevicesMethod) == null) {
          UtilityServiceGrpc.getSwitchDevicesMethod = getSwitchDevicesMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utility.DevicesRequest, grpc.services.utility.DevicesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Utility.UtilityService", "switchDevices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utility.DevicesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utility.DevicesResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilityServiceMethodDescriptorSupplier("switchDevices"))
                  .build();
          }
        }
     }
     return getSwitchDevicesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.utility.CameraRequest,
      grpc.services.utility.CameraResponse> getSwitchCameraOnMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "switchCameraOn",
      requestType = grpc.services.utility.CameraRequest.class,
      responseType = grpc.services.utility.CameraResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.services.utility.CameraRequest,
      grpc.services.utility.CameraResponse> getSwitchCameraOnMethod() {
    io.grpc.MethodDescriptor<grpc.services.utility.CameraRequest, grpc.services.utility.CameraResponse> getSwitchCameraOnMethod;
    if ((getSwitchCameraOnMethod = UtilityServiceGrpc.getSwitchCameraOnMethod) == null) {
      synchronized (UtilityServiceGrpc.class) {
        if ((getSwitchCameraOnMethod = UtilityServiceGrpc.getSwitchCameraOnMethod) == null) {
          UtilityServiceGrpc.getSwitchCameraOnMethod = getSwitchCameraOnMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utility.CameraRequest, grpc.services.utility.CameraResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "Utility.UtilityService", "switchCameraOn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utility.CameraRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utility.CameraResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilityServiceMethodDescriptorSupplier("switchCameraOn"))
                  .build();
          }
        }
     }
     return getSwitchCameraOnMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.services.utility.PrinterRequest,
      grpc.services.utility.PrinterResponse> getPrintListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "printList",
      requestType = grpc.services.utility.PrinterRequest.class,
      responseType = grpc.services.utility.PrinterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.services.utility.PrinterRequest,
      grpc.services.utility.PrinterResponse> getPrintListMethod() {
    io.grpc.MethodDescriptor<grpc.services.utility.PrinterRequest, grpc.services.utility.PrinterResponse> getPrintListMethod;
    if ((getPrintListMethod = UtilityServiceGrpc.getPrintListMethod) == null) {
      synchronized (UtilityServiceGrpc.class) {
        if ((getPrintListMethod = UtilityServiceGrpc.getPrintListMethod) == null) {
          UtilityServiceGrpc.getPrintListMethod = getPrintListMethod = 
              io.grpc.MethodDescriptor.<grpc.services.utility.PrinterRequest, grpc.services.utility.PrinterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "Utility.UtilityService", "printList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utility.PrinterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.services.utility.PrinterResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UtilityServiceMethodDescriptorSupplier("printList"))
                  .build();
          }
        }
     }
     return getPrintListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UtilityServiceStub newStub(io.grpc.Channel channel) {
    return new UtilityServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UtilityServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UtilityServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UtilityServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UtilityServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class UtilityServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void switchDevices(grpc.services.utility.DevicesRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utility.DevicesResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchDevicesMethod(), responseObserver);
    }

    /**
     */
    public void switchCameraOn(grpc.services.utility.CameraRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utility.CameraResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSwitchCameraOnMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.utility.PrinterRequest> printList(
        io.grpc.stub.StreamObserver<grpc.services.utility.PrinterResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getPrintListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSwitchDevicesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.utility.DevicesRequest,
                grpc.services.utility.DevicesResponse>(
                  this, METHODID_SWITCH_DEVICES)))
          .addMethod(
            getSwitchCameraOnMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.services.utility.CameraRequest,
                grpc.services.utility.CameraResponse>(
                  this, METHODID_SWITCH_CAMERA_ON)))
          .addMethod(
            getPrintListMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.services.utility.PrinterRequest,
                grpc.services.utility.PrinterResponse>(
                  this, METHODID_PRINT_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class UtilityServiceStub extends io.grpc.stub.AbstractStub<UtilityServiceStub> {
    private UtilityServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UtilityServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UtilityServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UtilityServiceStub(channel, callOptions);
    }

    /**
     */
    public void switchDevices(grpc.services.utility.DevicesRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utility.DevicesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchDevicesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void switchCameraOn(grpc.services.utility.CameraRequest request,
        io.grpc.stub.StreamObserver<grpc.services.utility.CameraResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSwitchCameraOnMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.services.utility.PrinterRequest> printList(
        io.grpc.stub.StreamObserver<grpc.services.utility.PrinterResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getPrintListMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class UtilityServiceBlockingStub extends io.grpc.stub.AbstractStub<UtilityServiceBlockingStub> {
    private UtilityServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UtilityServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UtilityServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UtilityServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.services.utility.DevicesResponse switchDevices(grpc.services.utility.DevicesRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchDevicesMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.services.utility.CameraResponse switchCameraOn(grpc.services.utility.CameraRequest request) {
      return blockingUnaryCall(
          getChannel(), getSwitchCameraOnMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UtilityServiceFutureStub extends io.grpc.stub.AbstractStub<UtilityServiceFutureStub> {
    private UtilityServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UtilityServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UtilityServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UtilityServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.utility.DevicesResponse> switchDevices(
        grpc.services.utility.DevicesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchDevicesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.services.utility.CameraResponse> switchCameraOn(
        grpc.services.utility.CameraRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSwitchCameraOnMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SWITCH_DEVICES = 0;
  private static final int METHODID_SWITCH_CAMERA_ON = 1;
  private static final int METHODID_PRINT_LIST = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UtilityServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UtilityServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SWITCH_DEVICES:
          serviceImpl.switchDevices((grpc.services.utility.DevicesRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.utility.DevicesResponse>) responseObserver);
          break;
        case METHODID_SWITCH_CAMERA_ON:
          serviceImpl.switchCameraOn((grpc.services.utility.CameraRequest) request,
              (io.grpc.stub.StreamObserver<grpc.services.utility.CameraResponse>) responseObserver);
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
        case METHODID_PRINT_LIST:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.printList(
              (io.grpc.stub.StreamObserver<grpc.services.utility.PrinterResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UtilityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UtilityServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.services.utility.UtilityServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UtilityService");
    }
  }

  private static final class UtilityServiceFileDescriptorSupplier
      extends UtilityServiceBaseDescriptorSupplier {
    UtilityServiceFileDescriptorSupplier() {}
  }

  private static final class UtilityServiceMethodDescriptorSupplier
      extends UtilityServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UtilityServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UtilityServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UtilityServiceFileDescriptorSupplier())
              .addMethod(getSwitchDevicesMethod())
              .addMethod(getSwitchCameraOnMethod())
              .addMethod(getPrintListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
